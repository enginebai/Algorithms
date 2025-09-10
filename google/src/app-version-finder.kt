/**
 * Problem: Application Version Finder
 *
 * Given multiple application versions with OS support ranges and release orders,
 * find the latest (highest releaseOrder) version that supports a given OS version.
 *
 * This file contains two advanced approaches:
 * - Approach C: Interval Map (Pre-computed segments)
 * - Approach D: Sweep Line Algorithm
 *
 * Both achieve O(log N) query time after pre-processing.
 */

data class AppVersion(
    val name: String,
    val minOS: Int?,  // null represents -∞
    val maxOS: Int?,  // null represents +∞
    val releaseOrder: Int
)

// ============================================================
// APPROACH C: Interval Map (Pre-computed Segments)
// ============================================================
// Pre-processing: O(N² × B) where B = number of boundaries
// Query: O(log N) - binary search
// Space: O(B) - number of segments
// ============================================================

class VersionFinderC(applications: List<AppVersion>) {
    // Pre-computed segments: each segment maps to the best app for that range
    private data class Segment(
        val start: Int,      // inclusive, Int.MIN_VALUE for -∞
        val end: Int,        // inclusive, Int.MAX_VALUE for +∞
        val bestApp: String? // null if no app supports this range
    )

    private val segments: List<Segment>

    init {
        segments = precomputeSegments(applications)
    }

    private fun precomputeSegments(applications: List<AppVersion>): List<Segment> {
        if (applications.isEmpty()) {
            return listOf(Segment(Int.MIN_VALUE, Int.MAX_VALUE, null))
        }

        // Step 1: Collect all boundary points
        val boundaries = mutableSetOf<Int>()

        for (app in applications) {
            // Add minOS boundary (or skip if unbounded below)
            app.minOS?.let { boundaries.add(it) }

            // Add maxOS + 1 boundary (where this app stops supporting)
            app.maxOS?.let {
                if (it < Int.MAX_VALUE) {
                    boundaries.add(it + 1)
                }
            }
        }

        // Step 2: Sort boundaries to create segments
        val sortedBoundaries = boundaries.sorted()

        // Step 3: Create segments between boundaries
        val segmentList = mutableListOf<Segment>()

        // First segment: from -∞ to first boundary
        if (sortedBoundaries.isNotEmpty()) {
            val firstBound = sortedBoundaries.first()
            if (firstBound > Int.MIN_VALUE) {
                val bestApp = findBestAppForOS(applications, Int.MIN_VALUE)
                segmentList.add(Segment(Int.MIN_VALUE, firstBound - 1, bestApp))
            }
        } else {
            // No boundaries, just one segment covering everything
            val bestApp = findBestAppForOS(applications, 0)
            segmentList.add(Segment(Int.MIN_VALUE, Int.MAX_VALUE, bestApp))
            return segmentList
        }

        // Middle segments: between consecutive boundaries
        for (i in 0 until sortedBoundaries.size - 1) {
            val start = sortedBoundaries[i]
            val end = sortedBoundaries[i + 1] - 1
            val bestApp = findBestAppForOS(applications, start)
            segmentList.add(Segment(start, end, bestApp))
        }

        // Last segment: from last boundary to +∞
        val lastBound = sortedBoundaries.last()
        val bestApp = findBestAppForOS(applications, lastBound)
        segmentList.add(Segment(lastBound, Int.MAX_VALUE, bestApp))

        return segmentList
    }

    private fun findBestAppForOS(applications: List<AppVersion>, os: Int): String? {
        var bestApp: String? = null
        var maxReleaseOrder = Int.MIN_VALUE

        for (app in applications) {
            // Check if this app supports this OS version
            val supportsOS = (app.minOS == null || os >= app.minOS) &&
                           (app.maxOS == null || os <= app.maxOS)

            if (supportsOS && app.releaseOrder > maxReleaseOrder) {
                bestApp = app.name
                maxReleaseOrder = app.releaseOrder
            }
        }

        return bestApp
    }

    // Query function: O(log N) binary search
    fun findVersionForOS(os: Int): String? {
        // Binary search to find the segment containing this OS
        var left = 0
        var right = segments.size - 1

        while (left <= right) {
            val mid = left + (right - left) / 2
            val segment = segments[mid]

            when {
                os < segment.start -> right = mid - 1
                os > segment.end -> left = mid + 1
                else -> return segment.bestApp // Found the segment
            }
        }

        return null // Should never happen if segments are correct
    }

    // For debugging: print all segments
    fun printSegments() {
        println("Pre-computed segments:")
        for (seg in segments) {
            val start = if (seg.start == Int.MIN_VALUE) "-∞" else seg.start.toString()
            val end = if (seg.end == Int.MAX_VALUE) "+∞" else seg.end.toString()
            println("  [$start, $end] → ${seg.bestApp ?: "null"}")
        }
    }
}

// ============================================================
// APPROACH D: Sweep Line Algorithm
// ============================================================
// Pre-processing: O(N log N) - sort events + sweep
// Query: O(log N) - binary search
// Space: O(N) - events and segments
// ============================================================

class VersionFinderD(applications: List<AppVersion>) {

    private data class Event(
        val position: Int,     // OS position where event occurs
        val type: EventType,   // START or END
        val app: AppVersion
    )

    private enum class EventType { START, END }

    private data class Segment(
        val start: Int,        // inclusive
        val end: Int,          // inclusive
        val bestApp: String?
    )

    private val segments: List<Segment>

    init {
        segments = buildSegmentsWithSweepLine(applications)
    }

    private fun buildSegmentsWithSweepLine(applications: List<AppVersion>): List<Segment> {
        if (applications.isEmpty()) {
            return listOf(Segment(Int.MIN_VALUE, Int.MAX_VALUE, null))
        }

        // Step 1: Create events for each application
        val events = mutableListOf<Event>()

        for (app in applications) {
            // START event at minOS (or -∞)
            val startPos = app.minOS ?: Int.MIN_VALUE
            events.add(Event(startPos, EventType.START, app))

            // END event at maxOS + 1 (or +∞, which we skip)
            val endPos = app.maxOS?.let {
                if (it < Int.MAX_VALUE) it + 1 else null
            }
            if (endPos != null) {
                events.add(Event(endPos, EventType.END, app))
            }
        }

        // Step 2: Sort events by position
        // If same position: process END events before START events
        // This handles edge cases where ranges meet exactly
        events.sortWith(compareBy({ it.position }, { it.type }))

        // Step 3: Sweep through events and build segments
        val resultSegments = mutableListOf<Segment>()
        val activeApps = mutableSetOf<AppVersion>()

        // Initialize: apps that start at -∞ are initially active
        for (app in applications) {
            if (app.minOS == null) {
                activeApps.add(app)
            }
        }

        var currentPos = Int.MIN_VALUE
        var currentBest = findBestInSet(activeApps)

        for (i in events.indices) {
            val event = events[i]
            val nextPos = event.position

            // If position changed, save the previous segment
            if (nextPos != currentPos && currentPos != Int.MIN_VALUE) {
                resultSegments.add(
                    Segment(
                        start = currentPos,
                        end = nextPos - 1,
                        bestApp = currentBest
                    )
                )
            } else if (i == 0 && nextPos != Int.MIN_VALUE) {
                // Add initial segment from -∞ to first event
                resultSegments.add(
                    Segment(
                        start = Int.MIN_VALUE,
                        end = nextPos - 1,
                        bestApp = currentBest
                    )
                )
            }

            // Process all events at this position
            var j = i
            while (j < events.size && events[j].position == nextPos) {
                when (events[j].type) {
                    EventType.START -> activeApps.add(events[j].app)
                    EventType.END -> activeApps.remove(events[j].app)
                }
                j++
            }

            // Update current state
            currentPos = nextPos
            currentBest = findBestInSet(activeApps)

            // Skip ahead if we processed multiple events
            if (j > i + 1) {
                // Don't skip the last processed event
                // because the outer loop will increment i
            }
        }

        // Add final segment from last position to +∞
        if (currentPos != Int.MAX_VALUE) {
            resultSegments.add(
                Segment(
                    start = currentPos,
                    end = Int.MAX_VALUE,
                    bestApp = currentBest
                )
            )
        }

        return resultSegments
    }

    private fun findBestInSet(apps: Set<AppVersion>): String? {
        return apps.maxByOrNull { it.releaseOrder }?.name
    }

    // Query function: O(log N) binary search
    fun findVersionForOS(os: Int): String? {
        var left = 0
        var right = segments.size - 1

        while (left <= right) {
            val mid = left + (right - left) / 2
            val segment = segments[mid]

            when {
                os < segment.start -> right = mid - 1
                os > segment.end -> left = mid + 1
                else -> return segment.bestApp
            }
        }

        return null
    }

    // For debugging
    fun printSegments() {
        println("Sweep line segments:")
        for (seg in segments) {
            val start = if (seg.start == Int.MIN_VALUE) "-∞" else seg.start.toString()
            val end = if (seg.end == Int.MAX_VALUE) "+∞" else seg.end.toString()
            println("  [$start, $end] → ${seg.bestApp ?: "null"}")
        }
    }
}

// ============================================================
// TEST CODE
// ============================================================

fun main() {
    // Create test applications
    val applications = listOf(
        AppVersion("v1", minOS = 14, maxOS = null, releaseOrder = 1),  // [14, +∞)
        AppVersion("v2", minOS = null, maxOS = 8, releaseOrder = 2),   // [-∞, 8]
        AppVersion("v3", minOS = 12, maxOS = 16, releaseOrder = 3)     // [12, 16]
    )

    val queries = listOf(3, 11, 14, 7, 20)

    println("=" .repeat(60))
    println("APPROACH C: Interval Map")
    println("=" .repeat(60))

    val finderC = VersionFinderC(applications)
    finderC.printSegments()

    println("\nQuery results:")
    for (os in queries) {
        val result = finderC.findVersionForOS(os)
        println("  OS = $os → ${result ?: "null"}")
    }

    println("\n" + "=".repeat(60))
    println("APPROACH D: Sweep Line")
    println("=" .repeat(60))

    val finderD = VersionFinderD(applications)
    finderD.printSegments()

    println("\nQuery results:")
    for (os in queries) {
        val result = finderD.findVersionForOS(os)
        println("  OS = $os → ${result ?: "null"}")
    }

    println("\n" + "=".repeat(60))
    println("Verification:")
    println("Expected: OS=3→v2, OS=11→null, OS=14→v3, OS=7→v2, OS=20→v1")
    println("=" .repeat(60))

    // Additional test cases
    println("\n" + "=".repeat(60))
    println("Additional Edge Case Tests")
    println("=" .repeat(60))

    // Test edge cases
    testEdgeCases()
}

fun testEdgeCases() {
    // Edge case 1: Single app covering everything
    println("\nTest 1: Single app with unbounded range")
    val apps1 = listOf(AppVersion("v1", null, null, 1))
    val finder1 = VersionFinderC(apps1)
    println("  OS = -1000 → ${finder1.findVersionForOS(-1000)}")
    println("  OS = 0 → ${finder1.findVersionForOS(0)}")
    println("  OS = 1000 → ${finder1.findVersionForOS(1000)}")

    // Edge case 2: No overlapping ranges
    println("\nTest 2: Non-overlapping ranges")
    val apps2 = listOf(
        AppVersion("v1", 1, 10, 1),
        AppVersion("v2", 20, 30, 2),
        AppVersion("v3", 40, 50, 3)
    )
    val finder2 = VersionFinderD(apps2)
    println("  OS = 5 → ${finder2.findVersionForOS(5)}")
    println("  OS = 15 → ${finder2.findVersionForOS(15)}")
    println("  OS = 25 → ${finder2.findVersionForOS(25)}")
    println("  OS = 35 → ${finder2.findVersionForOS(35)}")

    // Edge case 3: Exact boundary tests
    println("\nTest 3: Boundary conditions")
    val apps3 = listOf(
        AppVersion("v1", 10, 20, 1),
        AppVersion("v2", 20, 30, 2)
    )
    val finder3 = VersionFinderC(apps3)
    println("  OS = 10 → ${finder3.findVersionForOS(10)}")
    println("  OS = 20 → ${finder3.findVersionForOS(20)}")
    println("  OS = 30 → ${finder3.findVersionForOS(30)}")
}
