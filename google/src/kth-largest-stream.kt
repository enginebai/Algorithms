/**
 * Kth Largest in Data Stream
 *
 * Problem: Design a data structure supporting:
 * - addNum(num): Add integer to stream
 * - findLargest(k): Return kth largest element (or null if k > size)
 *
 * Five implementations:
 * 1. Unsorted list - sort on every query
 * 2. Sorted list - maintain sorted order on insert
 * 3. Max heap - extract and restore
 * 4. TreeMap - balanced operations
 * 5. Quick Select - fast add, average-case fast query
 */

import java.util.TreeMap
import java.util.PriorityQueue

// ============================================================================
// OPTION 1: Unsorted List + Sort on Query
// ============================================================================

/**
 * Unsorted List Approach
 *
 * Data Structure:
 * - Simple ArrayList to store all numbers
 * - Sort entire list on every query
 *
 * Time Complexity:
 * - addNum: O(1) - just append
 * - findLargest: O(n log n) - sort entire list
 *
 * Space: O(n)
 *
 * Trade-off:
 * ✅ Simplest implementation
 * ✅ Very fast insertion
 * ❌ Very slow queries (sorts entire list every time)
 * ❌ Not suitable if findLargest is called frequently
 */
class KthLargestStreamUnsorted {
    private val stream = mutableListOf<Int>()

    /**
     * Add a number to the stream
     * O(1) - just append
     */
    fun addNum(num: Int) {
        stream.add(num)
    }

    /**
     * Find the kth largest element
     * O(n log n) - sort entire list, then access index
     *
     * How it works:
     * 1. Sort the entire stream in descending order
     * 2. Return element at index k-1
     *
     * Example: stream = [5, 3, 8, 3, 7]
     * findLargest(2):
     *   - Sort descending: [8, 7, 5, 3, 3]
     *   - Return element at index 1 → 7
     */
    fun findLargest(k: Int): Int? {
        if (k > stream.size || k <= 0) return null

        val sorted = stream.sortedDescending()
        return sorted[k - 1]
    }

    fun size(): Int = stream.size

    fun getStream(): List<Int> = stream.toList()
}


// ============================================================================
// OPTION 2: Sorted List (Always Maintain Order)
// ============================================================================

/**
 * Sorted List Approach
 *
 * Data Structure:
 * - ArrayList that always maintains descending order
 * - Use binary search to find insertion position
 *
 * Time Complexity:
 * - addNum: O(n) - binary search O(log n) + insertion O(n)
 * - findLargest: O(1) - direct index access
 *
 * Space: O(n)
 *
 * Trade-off:
 * ✅ Very fast queries (just index access)
 * ✅ Predictable performance
 * ❌ Slow insertions (need to shift elements)
 * ❌ Not suitable if addNum is called very frequently
 */
class KthLargestStreamSorted {
    // Always maintain descending order: [largest, ..., smallest]
    private val sortedStream = mutableListOf<Int>()

    /**
     * Add a number to the stream
     * O(n) - binary search O(log n) + list insertion O(n)
     *
     * How it works:
     * 1. Use binary search to find correct insertion position
     * 2. Insert at that position (shifts elements)
     *
     * Example: sortedStream = [9, 7, 5, 3], adding 6
     * - Binary search finds position 2 (between 7 and 5)
     * - Insert 6 at position 2 → [9, 7, 6, 5, 3]
     */
    fun addNum(num: Int) {
        // Binary search to find insertion position
        val index = sortedStream.binarySearch(num) { a, b -> b.compareTo(a) }
        val insertPos = if (index >= 0) index else -(index + 1)
        sortedStream.add(insertPos, num)
    }

    /**
     * Find the kth largest element
     * O(1) - direct index access
     *
     * How it works:
     * Since list is already sorted in descending order,
     * kth largest is simply at index k-1
     *
     * Example: sortedStream = [9, 7, 6, 5, 3]
     * findLargest(2) → sortedStream[1] → 7
     */
    fun findLargest(k: Int): Int? {
        if (k > sortedStream.size || k <= 0) return null
        return sortedStream[k - 1]
    }

    fun size(): Int = sortedStream.size

    fun getStream(): List<Int> = sortedStream.toList()
}


// ============================================================================
// OPTION 3: Max Heap Approach
// ============================================================================

/**
 * Max Heap Approach
 *
 * Data Structure:
 * - PriorityQueue configured as max heap
 * - Extract k elements to find kth largest, then restore
 *
 * Time Complexity:
 * - addNum: O(log n) - heap insertion
 * - findLargest: O(k log n) - extract k times, then restore
 *
 * Space: O(n)
 *
 * Trade-off:
 * ✅ Reasonable insertion time
 * ❌ Destructive query (must extract and restore)
 * ❌ Expensive for large k values
 * ❌ More complex than other approaches
 *
 * Note: This approach is more commonly used for maintaining a
 * FIXED size k and finding the kth largest efficiently.
 * For variable k queries, it's not optimal.
 */
class KthLargestStreamHeap {
    // Max heap: largest element at top
    private val maxHeap = PriorityQueue<Int>(compareByDescending { it })

    /**
     * Add a number to the stream
     * O(log n) - heap insertion
     */
    fun addNum(num: Int) {
        maxHeap.offer(num)
    }

    /**
     * Find the kth largest element
     * O(k log n) - extract k elements, then restore them
     *
     * How it works:
     * 1. Extract k elements from max heap (largest first)
     * 2. The kth extracted element is the answer
     * 3. Put all extracted elements back to restore heap
     *
     * Example: heap = [9, 7, 5, 3, 3], k=3
     * - Extract #1: 9
     * - Extract #2: 7
     * - Extract #3: 5 ← This is the answer
     * - Restore: put back 9, 7, 5
     *
     * Why destructive?
     * - Heap only gives us the max element at top
     * - To get kth largest, we must remove top k-1 elements
     * - We restore them afterward to maintain original stream
     */
    fun findLargest(k: Int): Int? {
        if (k > maxHeap.size || k <= 0) return null

        val extracted = mutableListOf<Int>()
        var result: Int? = null

        // Extract k elements
        repeat(k) {
            val element = maxHeap.poll()
            extracted.add(element)
            if (extracted.size == k) {
                result = element
            }
        }

        // Restore all extracted elements
        extracted.forEach { maxHeap.offer(it) }

        return result
    }

    fun size(): Int = maxHeap.size

    fun getStream(): List<Int> {
        // Convert heap to sorted list for display
        val copy = PriorityQueue(maxHeap)
        val result = mutableListOf<Int>()
        while (copy.isNotEmpty()) {
            result.add(copy.poll())
        }
        return result
    }
}


// ============================================================================
// OPTION 4: TreeMap (Sorted Structure) Approach
// ============================================================================

/**
 * TreeMap Approach
 *
 * Data Structure:
 * - TreeMap<Int, Int> where key=number, value=count
 * - Automatically maintains sorted order
 *
 * Time Complexity:
 * - addNum: O(log n) where n = number of distinct values
 * - findLargest: O(k) where k is the parameter
 *
 * Space: O(d) where d = distinct values in stream
 */
class KthLargestStreamTreeMap {
    // TreeMap maintains sorted order, maps number -> count
    private val sortedMap = TreeMap<Int, Int>()
    private var totalCount = 0

    /**
     * Add a number to the stream
     * O(log n) - tree insertion/update
     */
    fun addNum(num: Int) {
        sortedMap[num] = sortedMap.getOrDefault(num, 0) + 1
        totalCount++
    }

    /**
     * Find the kth largest element
     * O(k) - traverse from largest to smallest, counting elements
     *
     * How it works:
     * 1. Get descending iterator (largest to smallest)
     * 2. For each distinct value, count its occurrences
     * 3. When count reaches k, return that value
     *
     * Example:
     * TreeMap: {3→2, 5→1, 8→1}  (stream: [8, 5, 3, 3])
     * findLargest(3):
     *   - Start with 8 (count=1), counted=1
     *   - Then 5 (count=1), counted=2
     *   - Then 3 (count=2), counted=3 → return 3
     */
    fun findLargest(k: Int): Int? {
        if (k > totalCount || k <= 0) return null

        var counted = 0
        // descendingMap() gives us entries from largest to smallest
        for ((num, count) in sortedMap.descendingMap()) {
            counted += count
            if (counted >= k) {
                return num
            }
        }

        return null // Should never reach here if k is valid
    }

    fun size(): Int = totalCount

    fun getStream(): List<Int> {
        val result = mutableListOf<Int>()
        for ((num, count) in sortedMap.descendingMap()) {
            repeat(count) {
                result.add(num)
            }
        }
        return result
    }
}


// ============================================================================
// OPTION 5: Quick Select Approach
// ============================================================================

/**
 * Quick Select Approach
 *
 * Data Structure:
 * - Simple ArrayList to store all numbers
 * - Use Quick Select algorithm to find kth largest on demand
 *
 * Time Complexity:
 * - addNum: O(1) - just append to list
 * - findLargest: O(n) average case, O(n²) worst case
 *   where n = total numbers in stream
 *
 * Space: O(n)
 *
 * Trade-off:
 * ✅ Very fast insertion
 * ✅ Good average-case query
 * ❌ Worst-case query can be slow
 * ❌ Query modifies internal array (need to copy)
 */
class KthLargestStreamQuickSelect {
    private val stream = mutableListOf<Int>()

    /**
     * Add a number to the stream
     * O(1) - just append
     */
    fun addNum(num: Int) {
        stream.add(num)
    }

    /**
     * Find the kth largest element using Quick Select
     * O(n) average case, O(n²) worst case
     *
     * How Quick Select works:
     * 1. Pick a pivot element
     * 2. Partition: move larger elements left, smaller/equal right
     * 3. If pivot is at position k-1: found answer
     * 4. If k-1 < pivot index: search left partition
     * 5. If k-1 > pivot index: search right partition
     *
     * Example: [5, 3, 8, 3, 7, 9, 2], k=3
     *
     * Round 1: pivot=7
     *   After partition: [9, 8] | 7 | [5, 3, 3, 2]
     *   Pivot at index 2, want index 2 (k-1) → Found! return 7
     */
    fun findLargest(k: Int): Int? {
        if (k > stream.size || k <= 0) return null

        // Copy to avoid modifying original stream
        val copy = stream.toMutableList()
        return quickSelect(copy, 0, copy.size - 1, k - 1)
    }

    /**
     * Quick Select implementation
     *
     * @param arr: array to search in
     * @param left: left boundary of current partition
     * @param right: right boundary of current partition
     * @param k: target index (0-indexed) for kth largest
     * @return: the kth largest element
     */
    private fun quickSelect(arr: MutableList<Int>, left: Int, right: Int, k: Int): Int {
        if (left == right) return arr[left]

        // Partition and get pivot's final position
        val pivotIndex = partition(arr, left, right)

        when {
            pivotIndex == k -> return arr[pivotIndex]
            pivotIndex > k -> return quickSelect(arr, left, pivotIndex - 1, k)
            else -> return quickSelect(arr, pivotIndex + 1, right, k)
        }
    }

    /**
     * Partition array around a pivot
     *
     * Goal: Arrange so that elements > pivot are on left,
     *       elements <= pivot are on right
     *
     * Returns: final position of pivot
     *
     * Example: [5, 3, 8, 3, 7, 9, 2], pivot=7 (last element)
     *
     * Process:
     * - i tracks position for next large element
     * - j scans through array
     *
     * Initial: i=-1, j=0
     * [5, 3, 8, 3, 7, 9, 2]  pivot=2
     *
     * j=0: 5>2? Yes → swap(i+1, j) → [5, 3, 8, 3, 7, 9, 2], i=0
     * j=1: 3>2? Yes → swap(i+1, j) → [5, 3, 8, 3, 7, 9, 2], i=1
     * j=2: 8>2? Yes → swap(i+1, j) → [5, 3, 8, 3, 7, 9, 2], i=2
     * j=3: 3>2? Yes → swap(i+1, j) → [5, 3, 8, 3, 7, 9, 2], i=3
     * j=4: 7>2? Yes → swap(i+1, j) → [5, 3, 8, 3, 7, 9, 2], i=4
     * j=5: 9>2? Yes → swap(i+1, j) → [5, 3, 8, 3, 7, 9, 2], i=5
     *
     * Finally: swap(i+1, right) → [5, 3, 8, 3, 7, 9, 2]
     * All elements > pivot are on left of index 6
     */
    private fun partition(arr: MutableList<Int>, left: Int, right: Int): Int {
        val pivot = arr[right]
        var i = left - 1  // Position for next element > pivot

        for (j in left until right) {
            if (arr[j] > pivot) {  // Want larger elements on left
                i++
                // Swap arr[i] and arr[j]
                arr[i] = arr[j].also { arr[j] = arr[i] }
            }
        }

        // Place pivot in its final position
        i++
        arr[i] = arr[right].also { arr[right] = arr[i] }

        return i
    }

    fun size(): Int = stream.size

    fun getStream(): List<Int> = stream.toList()
}


// ============================================================================
// COMPARISON & TEST CASES
// ============================================================================

fun main() {
    println("=".repeat(70))
    println("Kth Largest in Data Stream - Five Approaches Comparison")
    println("=".repeat(70))

    // Test Case 1: Basic operations
    println("\n📊 Test Case 1: Basic Operations")
    println("-".repeat(70))

    val unsorted = KthLargestStreamUnsorted()
    val sorted = KthLargestStreamSorted()
    val heap = KthLargestStreamHeap()
    val treeMap = KthLargestStreamTreeMap()
    val quickSelect = KthLargestStreamQuickSelect()

    val operations = listOf(5, 3, 8, 3, 7, 9, 2)
    println("Adding numbers: ${operations.joinToString(", ")}")
    operations.forEach { num ->
        unsorted.addNum(num)
        sorted.addNum(num)
        heap.addNum(num)
        treeMap.addNum(num)
        quickSelect.addNum(num)
    }

    println("\nStream state: ${treeMap.getStream()}")
    println("(Sorted descending: [9, 8, 7, 5, 3, 3, 2])")

    println("\nQuerying kth largest:")
    for (k in 1..7) {
        val r1 = unsorted.findLargest(k)
        val r2 = sorted.findLargest(k)
        val r3 = heap.findLargest(k)
        val r4 = treeMap.findLargest(k)
        val r5 = quickSelect.findLargest(k)
        val allMatch = (r1 == r2 && r2 == r3 && r3 == r4 && r4 == r5)
        val status = if (allMatch) "✅" else "❌"
        println("  k=$k: Unsorted=$r1, Sorted=$r2, Heap=$r3, TreeMap=$r4, QuickSelect=$r5 $status")
    }

    // Test Case 2: Edge cases
    println("\n📊 Test Case 2: Edge Cases")
    println("-".repeat(70))

    println("k=0 (invalid):")
    println("  Unsorted=${unsorted.findLargest(0)}, Sorted=${sorted.findLargest(0)}, " +
            "Heap=${heap.findLargest(0)}, TreeMap=${treeMap.findLargest(0)}, QuickSelect=${quickSelect.findLargest(0)}")
    println("k=8 (> size):")
    println("  Unsorted=${unsorted.findLargest(8)}, Sorted=${sorted.findLargest(8)}, " +
            "Heap=${heap.findLargest(8)}, TreeMap=${treeMap.findLargest(8)}, QuickSelect=${quickSelect.findLargest(8)}")

    // Test Case 3: All duplicates
    println("\n📊 Test Case 3: All Duplicates")
    println("-".repeat(70))

    val unsorted2 = KthLargestStreamUnsorted()
    val sorted2 = KthLargestStreamSorted()
    val heap2 = KthLargestStreamHeap()
    val treeMap2 = KthLargestStreamTreeMap()
    val quickSelect2 = KthLargestStreamQuickSelect()

    repeat(5) {
        unsorted2.addNum(7)
        sorted2.addNum(7)
        heap2.addNum(7)
        treeMap2.addNum(7)
        quickSelect2.addNum(7)
    }

    println("Stream: [7, 7, 7, 7, 7]")
    for (k in listOf(1, 3, 5)) {
        val r1 = unsorted2.findLargest(k)
        val r2 = sorted2.findLargest(k)
        val r3 = heap2.findLargest(k)
        val r4 = treeMap2.findLargest(k)
        val r5 = quickSelect2.findLargest(k)
        val allMatch = (r1 == r2 && r2 == r3 && r3 == r4 && r4 == r5)
        val status = if (allMatch) "✅" else "❌"
        println("k=$k: All approaches=$r1 $status")
    }

    // Test Case 4: Growing stream
    println("\n📊 Test Case 4: Growing Stream (showing TreeMap only for brevity)")
    println("-".repeat(70))

    val treeMap3 = KthLargestStreamTreeMap()

    val numbers = listOf(10, 5, 15, 3, 20)
    for (num in numbers) {
        treeMap3.addNum(num)
        val k = 2
        println("After adding $num: Stream=${treeMap3.getStream()}, 2nd largest=${treeMap3.findLargest(k)}")
    }

    // Performance comparison
    println("\n📊 Performance Characteristics Comparison")
    println("-".repeat(80))
    println("Approach       | addNum(num)      | findLargest(k)              | Space")
    println("-".repeat(80))
    println("1. Unsorted    | O(1)             | O(n log n)                  | O(n)")
    println("2. Sorted      | O(n)             | O(1)                        | O(n)")
    println("3. Heap        | O(log n)         | O(k log n)                  | O(n)")
    println("4. TreeMap     | O(log n)         | O(k)                        | O(distinct)")
    println("5. Quick Select| O(1)             | O(n) avg, O(n²) worst       | O(n)")
    println("-".repeat(80))

    println("\n💡 When to use each approach:")
    println("-".repeat(80))

    println("1. Unsorted List + Sort on Query:")
    println("  ✅ Simplest to implement")
    println("  ✅ Best for: Very frequent insertions, rare queries")
    println("  ❌ Terrible for: Frequent queries (sorts every time)")

    println("\n2. Sorted List:")
    println("  ✅ Best for: Very frequent queries, rare insertions")
    println("  ✅ O(1) query time")
    println("  ❌ Slow insertions (O(n) to maintain order)")

    println("\n3. Max Heap:")
    println("  ✅ Balanced insertion time")
    println("  ❌ Destructive queries (must extract and restore)")
    println("  ❌ Not ideal for variable k queries")
    println("  💡 Better suited for: FIXED k (like \"always find 3rd largest\")")

    println("\n4. TreeMap (RECOMMENDED for most cases):")
    println("  ✅ Best balance between insertion and query")
    println("  ✅ Predictable performance")
    println("  ✅ Space-efficient with many duplicates")
    println("  ✅ Good for: Variable k values, frequent operations on both sides")

    println("\n5. Quick Select:")
    println("  ✅ Very fast insertions")
    println("  ✅ Good average-case query")
    println("  ❌ Unpredictable performance (worst case O(n²))")
    println("  ❌ Best for: Insertion-heavy workload, infrequent queries")
}
