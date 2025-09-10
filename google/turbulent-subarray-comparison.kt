/**
 * LC 978: Longest Turbulent Subarray - Comparison with Good Arithmetic Sequences
 *
 * This demonstrates how the same mental model (elements vs edges) applies to different problems.
 */

// ============================================================================
// PROBLEM: LC 978 - Longest Turbulent Subarray
// ============================================================================

/**
 * A subarray is turbulent if:
 * - arr[i] < arr[i+1] > arr[i+2] < arr[i+3] > ... (alternating comparison)
 * OR
 * - arr[i] > arr[i+1] < arr[i+2] > arr[i+3] < ... (alternating comparison)
 *
 * Example:
 * [9, 4, 2, 10, 7, 8, 8, 1, 9]
 *    └─┘                           9>4 (down)
 *       └──┘                       4>2 (down) - not alternating, breaks
 *          └───┘                   2<10 (up)
 *             └──┘                 10>7 (down) - alternates!
 *                └─┘               7<8 (up) - alternates!
 *                   └─┘            8=8 (equal) - breaks
 *
 * Longest turbulent: [2,10,7,8] length 4
 */

// ============================================================================
// APPROACH 1: Using i vs i+1 (Lookahead) - RECOMMENDED
// ============================================================================

/**
 * Mental model: Starting from each element, look AHEAD to see the pattern
 *
 * Why lookahead is more natural here:
 * - We're building sequences forward (extending to the right)
 * - Easier to think "what comes next?" than "what came before?"
 */
fun maxTurbulenceSizeLookahead(arr: IntArray): Int {
    val n = arr.size
    if (n <= 1) return n

    var maxLength = 1
    var start = 0

    // Process starting from each ELEMENT
    while (start < n - 1) {  // Stop at n-1 because we need at least 2 elements
        // Check first EDGE: start -> start+1
        val firstDiff = arr[start + 1] - arr[start]

        // Skip if equal (no turbulence possible)
        if (firstDiff == 0) {
            start++
            continue
        }

        // We have a valid start (diff != 0)
        var end = start + 1
        maxLength = maxOf(maxLength, 2)  // At least [start, start+1]

        // Try to extend: check if pattern alternates
        while (end < n - 1) {  // Stop at n-1 because we'll check end+1
            val currentDiff = arr[end + 1] - arr[end]
            val prevDiff = arr[end] - arr[end - 1]

            // Check if signs alternate (positive -> negative or vice versa)
            if (currentDiff * prevDiff < 0) {  // Different signs = alternating
                end++
                maxLength = maxOf(maxLength, end - start + 1)
            } else {
                break
            }
        }

        // Move to next potential start
        start = end
    }

    return maxLength
}

// ============================================================================
// APPROACH 2: Using i vs i-1 (Lookback)
// ============================================================================

/**
 * Mental model: For each element, look BACK to see if pattern continues
 *
 * Why lookback works too:
 * - Can still track the pattern
 * - More natural for DP-style solutions
 */
fun maxTurbulenceSizeLookback(arr: IntArray): Int {
    val n = arr.size
    if (n <= 1) return n

    var maxLength = 1
    var currentLength = 1

    // Start from index 1 because we look back to i-1
    for (i in 1 until n) {  // Loop bound: i < n
        val currentDiff = arr[i] - arr[i - 1]

        if (currentDiff == 0) {
            // Equal elements break turbulence
            currentLength = 1
        } else if (i == 1) {
            // First edge, automatically valid if not equal
            currentLength = 2
        } else {
            // Check if alternates with previous edge
            val prevDiff = arr[i - 1] - arr[i - 2]

            if (currentDiff * prevDiff < 0) {
                // Signs alternate, extend
                currentLength++
            } else {
                // Pattern breaks, restart from this edge
                currentLength = 2
            }
        }

        maxLength = maxOf(maxLength, currentLength)
    }

    return maxLength
}

// ============================================================================
// SIDE-BY-SIDE COMPARISON
// ============================================================================

/**
 * Good Arithmetic Sequences vs Turbulent Subarray
 *
 * Similarities:
 * - Both check relationships between consecutive elements (edges)
 * - Both try to extend sequences while pattern holds
 * - Both use sliding window / two pointer approach
 *
 * Differences:
 * - Arithmetic: Check if diff stays constant (same edge value)
 * - Turbulent: Check if signs alternate (opposite edge signs)
 *
 * - Arithmetic: Output ALL valid subarrays
 * - Turbulent: Output only the LONGEST length
 */

// ============================================================================
// VISUAL COMPARISON
// ============================================================================

fun visualComparison() {
    val nums = intArrayOf(1, 2, 3, 2, 1)

    println("Array: ${nums.joinToString(", ")}")
    println()

    // Show edges
    println("Edges (differences):")
    for (i in 0 until nums.size - 1) {
        val diff = nums[i + 1] - nums[i]
        val sign = when {
            diff > 0 -> "+"
            diff < 0 -> "-"
            else -> "0"
        }
        println("  Edge $i: nums[$i] -> nums[${i+1}] = ${nums[i]} -> ${nums[i+1]} (diff=$diff, sign=$sign)")
    }
    println()

    // Good Arithmetic Sequences analysis
    println("Good Arithmetic Sequences:")
    println("  Looking for: constant diff of ±1")
    println("  [1,2]: diff=+1 ✓")
    println("  [1,2,3]: diff=+1,+1 ✓")
    println("  [2,3]: diff=+1 ✓")
    println("  [3,2]: diff=-1 ✓")
    println("  [3,2,1]: diff=-1,-1 ✓")
    println("  [2,1]: diff=-1 ✓")
    println()

    // Turbulent Subarray analysis
    println("Turbulent Subarray:")
    println("  Looking for: alternating signs")
    println("  Edge pattern: +, +, -, -")
    println("  [1,2,3]: +,+ - NOT alternating ✗")
    println("  [2,3,2]: +,- - alternating ✓")
    println("  [3,2,1]: -,- - NOT alternating ✗")
    println("  [2,3,2,1]: +,-,- - breaks at position 2 ✗")
    println("  Longest turbulent: [2,3,2] length 3")
}

// ============================================================================
// THE UNIVERSAL PATTERN
// ============================================================================

/**
 * Template for array difference problems:
 *
 * Step 1: Identify if you're processing ELEMENTS or EDGES
 *
 * Step 2: Choose loop structure:
 *   If processing edges with lookahead:
 *     for (i in 0 until n-1) {
 *       val diff = nums[i+1] - nums[i]
 *     }
 *
 *   If processing elements with lookback:
 *     for (i in 1 until n) {
 *       val diff = nums[i] - nums[i-1]
 *     }
 *
 * Step 3: Extend/check pattern:
 *   - Arithmetic: diff must stay constant
 *   - Turbulent: signs must alternate
 *   - Increasing: diff must be positive
 *   - Etc.
 *
 * Step 4: Track result:
 *   - All subarrays: add each valid (start, end)
 *   - Longest only: track max length
 */

// ============================================================================
// MAIN - DEMONSTRATION
// ============================================================================

fun main() {
    println("=".repeat(80))
    println("Turbulent Subarray vs Good Arithmetic Sequences")
    println("=".repeat(80))
    println()

    visualComparison()

    println()
    println("=".repeat(80))
    println("Testing both approaches on LC 978:")
    println("=".repeat(80))

    val testCases = listOf(
        intArrayOf(9, 4, 2, 10, 7, 8, 8, 1, 9) to 5,
        intArrayOf(4, 8, 12, 16) to 2,
        intArrayOf(100) to 1,
        intArrayOf(1, 2, 3, 2, 1) to 3
    )

    testCases.forEach { (arr, expected) ->
        val resultLookahead = maxTurbulenceSizeLookahead(arr)
        val resultLookback = maxTurbulenceSizeLookback(arr)

        println("\nInput: ${arr.joinToString(", ")}")
        println("Expected: $expected")
        println("Lookahead approach: $resultLookahead ${if (resultLookahead == expected) "✓" else "✗"}")
        println("Lookback approach: $resultLookback ${if (resultLookback == expected) "✓" else "✗"}")
    }

    println("\n" + "=".repeat(80))
    println("Key Insight:")
    println("=".repeat(80))
    println("Both lookahead (i vs i+1) and lookback (i vs i-1) work!")
    println("Choose based on what feels more natural for the problem:")
    println("  - Lookahead: Better for extending sequences forward")
    println("  - Lookback: Better for DP-style tracking")
    println()
    println("The LOOP BOUND is what really matters:")
    println("  - Lookahead: for (i in 0 until n-1)")
    println("  - Lookback: for (i in 1 until n)")
    println("=".repeat(80))
}
