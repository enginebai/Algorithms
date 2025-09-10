/**
 * Guide: Mastering Array Difference Patterns
 *
 * Common confusion: "Should I use i-1 vs i, or i vs i+1? Should loop be i < n or i < n-1?"
 *
 * This guide provides a clear mental framework to eliminate this confusion.
 */

// ============================================================================
// MENTAL MODEL 1: ELEMENT-BASED vs EDGE-BASED
// ============================================================================

/**
 * Array visualization:
 *
 * Elements (n items):
 * Index:  0    1    2    3
 * Value: [10, 20, 30, 40]
 *         └─   └─   └─   └─
 *
 * Edges (n-1 differences):
 * Index:  0    1    2    3
 * Value: [10, 20, 30, 40]
 *          └──┬ └──┬ └──┬
 *          edge0 edge1 edge2
 *
 * edge0 = nums[1] - nums[0] = 10
 * edge1 = nums[2] - nums[1] = 10
 * edge2 = nums[3] - nums[2] = 10
 */

// ============================================================================
// PATTERN 1: EDGE-BASED PROCESSING
// ============================================================================

/**
 * When to use: You're checking differences/relationships between consecutive elements
 *
 * Key insight: There are n-1 edges in an array of n elements
 *
 * Loop structure: for (i in 0 until n-1)
 * Why? Because we access nums[i+1], and i+1 must be < n
 */
fun exampleEdgeBased(nums: IntArray): List<Pair<Int, Int>> {
    val n = nums.size
    val result = mutableListOf<Pair<Int, Int>>()

    // We're processing EDGES (differences between consecutive elements)
    for (i in 0 until n - 1) {  // Loop bound: i < n-1
        // For edge i, we compare nums[i] and nums[i+1]
        val diff = nums[i + 1] - nums[i]

        // At i=0: compare nums[0] vs nums[1] (first edge)
        // At i=n-2: compare nums[n-2] vs nums[n-1] (last edge)
        // At i=n-1: would try nums[n] - INVALID!

        if (diff == 1 || diff == -1) {
            result.add(i to i + 1)
        }
    }

    return result
}

// ============================================================================
// PATTERN 2: ELEMENT-BASED PROCESSING
// ============================================================================

/**
 * When to use: You're processing each element individually
 *
 * Loop structure: for (i in 0 until n)
 */
fun exampleElementBased(nums: IntArray): Int {
    val n = nums.size
    var sum = 0

    // We're processing ELEMENTS
    for (i in 0 until n) {  // Loop bound: i < n
        // Process nums[i]
        sum += nums[i]

        // At i=0: process nums[0]
        // At i=n-1: process nums[n-1] (last element)
        // At i=n: would try nums[n] - INVALID!
    }

    return sum
}

// ============================================================================
// PATTERN 3: ELEMENT WITH LOOKBACK
// ============================================================================

/**
 * When to use: Processing element i, but need to compare with i-1
 *
 * Loop structure: for (i in 1 until n)
 * Why start at 1? Because we look back to i-1, and i-1 must be >= 0
 */
fun exampleLookback(nums: IntArray): List<Int> {
    val n = nums.size
    val result = mutableListOf<Int>()

    // Start at index 1 because we'll look back to i-1
    for (i in 1 until n) {  // Loop bound: i < n, starting at 1
        // Compare current element with previous
        val diff = nums[i] - nums[i - 1]

        // At i=1: compare nums[1] vs nums[0]
        // At i=n-1: compare nums[n-1] vs nums[n-2]
        // At i=0: would try nums[-1] - INVALID!

        if (diff > 0) {
            result.add(diff)
        }
    }

    return result
}

// ============================================================================
// PATTERN 4: ELEMENT WITH LOOKAHEAD
// ============================================================================

/**
 * When to use: Processing element i, but need to compare with i+1
 *
 * Loop structure: for (i in 0 until n-1)
 * Why stop at n-2? Because we look ahead to i+1, and i+1 must be < n
 */
fun exampleLookahead(nums: IntArray): List<Int> {
    val n = nums.size
    val result = mutableListOf<Int>()

    // Stop at n-1 because we'll look ahead to i+1
    for (i in 0 until n - 1) {  // Loop bound: i < n-1
        // Compare current element with next
        val diff = nums[i + 1] - nums[i]

        // At i=0: compare nums[1] vs nums[0]
        // At i=n-2: compare nums[n-1] vs nums[n-2]
        // At i=n-1: would try nums[n] - INVALID!

        if (diff > 0) {
            result.add(diff)
        }
    }

    return result
}

// ============================================================================
// OUR PROBLEM: Good Arithmetic Sequences (CLEAR VERSION)
// ============================================================================

/**
 * Mental model: We're starting from each ELEMENT, then checking EDGES
 *
 * Outer loop: Element-based (trying each starting position)
 * Inner logic: Edge-based (checking differences)
 */
fun findGoodArithmeticSequencesClear(nums: IntArray): List<Pair<Int, Int>> {
    val n = nums.size
    val result = mutableListOf<Pair<Int, Int>>()

    if (n < 2) return result

    // OUTER: Process each ELEMENT as potential starting position
    // We need at least 2 elements, so stop at n-1 (last starting position that can form a pair)
    for (start in 0 until n - 1) {  // ← Element-based, but stop early

        // Calculate the FIRST EDGE from this starting position
        // Edge: start -> start+1
        val firstDiff = nums[start + 1] - nums[start]

        // Only proceed if first diff is ±1
        if (firstDiff != 1 && firstDiff != -1) continue

        // Add the minimum valid subarray [start, start+1]
        result.add(start to start + 1)

        // INNER: Try to extend by checking subsequent EDGES
        // Current position is at start+1, try to extend to start+2, start+3, ...
        var end = start + 1

        // While there's a next element to check (end < n-1 means end+1 < n)
        while (end < n - 1) {  // ← Edge-based check from 'end'
            // Check EDGE: end -> end+1
            val nextDiff = nums[end + 1] - nums[end]

            // If this edge doesn't match, stop extending
            if (nextDiff != firstDiff) break

            // Edge matches! Extend the sequence
            end++
            result.add(start to end)
        }
    }

    return result
}

// ============================================================================
// DEMONSTRATION: Comparing i-1 vs i and i vs i+1
// ============================================================================

fun demonstrateDifferenceCalculation(nums: IntArray) {
    val n = nums.size

    println("Array: ${nums.joinToString(", ")}")
    println()

    // Approach 1: Using i vs i+1 (lookahead)
    println("Approach 1: i vs i+1 (lookahead)")
    println("Loop: for (i in 0 until n-1)")
    for (i in 0 until n - 1) {
        val diff = nums[i + 1] - nums[i]
        println("  i=$i: nums[$i+1] - nums[$i] = ${nums[i+1]} - ${nums[i]} = $diff")
    }
    println()

    // Approach 2: Using i vs i-1 (lookback)
    println("Approach 2: i vs i-1 (lookback)")
    println("Loop: for (i in 1 until n)")
    for (i in 1 until n) {
        val diff = nums[i] - nums[i - 1]
        println("  i=$i: nums[$i] - nums[$i-1] = ${nums[i]} - ${nums[i-1]} = $diff")
    }
    println()

    println("Notice: Both produce the same differences, just with different indices!")
    println("Choose based on what's more natural for your problem.")
}

// ============================================================================
// DECISION TREE: Which pattern to use?
// ============================================================================

/**
 * Decision Tree:
 *
 * Q1: Are you processing differences/relationships?
 *     YES → You're working with EDGES (n-1 of them)
 *     NO → You're working with ELEMENTS (n of them)
 *
 * Q2: Do you need to look at the NEXT element?
 *     YES → Loop: for (i in 0 until n-1), access nums[i+1]
 *     NO → Go to Q3
 *
 * Q3: Do you need to look at the PREVIOUS element?
 *     YES → Loop: for (i in 1 until n), access nums[i-1]
 *     NO → Loop: for (i in 0 until n), access nums[i]
 *
 * Q4 (for two-pointer problems): Do you process both ends?
 *     YES → Use left and right pointers
 *     NO → Use patterns above
 */

// ============================================================================
// COMMON MISTAKES
// ============================================================================

fun commonMistakes() {
    val nums = intArrayOf(1, 2, 3, 4)
    val n = nums.size

    // ❌ MISTAKE 1: Wrong loop bound with lookahead
    // for (i in 0 until n) {  // BUG: when i=n-1, nums[i+1] is out of bounds!
    //     val diff = nums[i + 1] - nums[i]
    // }

    // ✅ CORRECT:
    for (i in 0 until n - 1) {
        val diff = nums[i + 1] - nums[i]
        println(diff)
    }

    // ❌ MISTAKE 2: Wrong loop bound with lookback
    // for (i in 0 until n) {  // BUG: when i=0, nums[i-1] is out of bounds!
    //     val diff = nums[i] - nums[i - 1]
    // }

    // ✅ CORRECT:
    for (i in 1 until n) {
        val diff = nums[i] - nums[i - 1]
        println(diff)
    }

    // ❌ MISTAKE 3: Mixing element and edge thinking
    // for (i in 0 until n) {  // Loop suggests processing elements
    //     val diff = nums[i + 1] - nums[i]  // But this is edge-based!
    // }

    // ✅ CORRECT: Be consistent
    for (i in 0 until n - 1) {  // Edge-based loop
        val diff = nums[i + 1] - nums[i]  // Edge-based access
        println(diff)
    }
}

// ============================================================================
// MAIN - DEMONSTRATION
// ============================================================================

fun main() {
    println("=".repeat(80))
    println("Array Difference Patterns - Complete Guide")
    println("=".repeat(80))

    val nums = intArrayOf(10, 20, 30, 40)

    println("\nTest Array: ${nums.joinToString(", ")}")
    println("Array has ${nums.size} elements and ${nums.size - 1} edges (differences)")
    println()

    demonstrateDifferenceCalculation(nums)

    println("=".repeat(80))
    println("Key Takeaways:")
    println("=".repeat(80))
    println("1. ELEMENTS vs EDGES:")
    println("   - Array of n elements has n-1 edges (differences)")
    println()
    println("2. LOOP BOUNDS:")
    println("   - Processing elements: for (i in 0 until n)")
    println("   - Processing edges with i+1: for (i in 0 until n-1)")
    println("   - Processing elements with i-1: for (i in 1 until n)")
    println()
    println("3. CHOOSE LOOKAHEAD (i+1) OR LOOKBACK (i-1):")
    println("   - Lookahead: More natural for extending sequences")
    println("   - Lookback: More natural for comparing with previous")
    println("   - Both work! Choose what makes your logic clearer")
    println()
    println("4. BE CONSISTENT:")
    println("   - If loop is for (i in 0 until n-1), you're thinking edges")
    println("   - If loop is for (i in 0 until n), you're thinking elements")
    println("   - Don't mix them!")
    println("=".repeat(80))
}
