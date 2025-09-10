/**
 * Good Arithmetic Sequences
 *
 * Problem:
 * Find all contiguous subarrays where consecutive elements form an arithmetic
 * progression with common difference exactly +1 or -1.
 *
 * Constraints:
 * - Subarray length >= 2
 * - Common difference must be constant and equal to +1 or -1
 * - Return all qualifying subarrays as (i, j) index pairs
 *
 * Approach: Expanding Window
 * - For each starting position i, try to extend as far as possible
 * - Once we find a valid diff (±1), keep extending while diff remains the same
 * - Generate all subarrays within this valid run
 *
 * Time Complexity: O(n²) worst case (entire array is arithmetic sequence)
 * Space Complexity: O(1) excluding output
 */

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * Find all good arithmetic sequences
 *
 * Algorithm:
 * 1. For each starting index i from 0 to n-2:
 *    - Calculate initial diff = nums[i+1] - nums[i]
 *    - If diff is not ±1, skip this starting position
 *    - If diff is ±1:
 *      - Add (i, i+1) to result (minimum length 2)
 *      - Try to extend: while next element maintains same diff:
 *        - Extend ending index j
 *        - Add (i, j) to result
 *
 * @param nums Input array
 * @return List of (start, end) index pairs for all valid subarrays
 */
fun findGoodArithmeticSequences(nums: IntArray): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()

    if (nums.size < 2) return result

    // Try each starting position
    for (i in 0 until nums.size - 1) {
        // Calculate the difference between first two elements
        val diff = nums[i + 1] - nums[i]

        // Only proceed if diff is ±1
        if (diff != 1 && diff != -1) continue

        // Add the minimum valid subarray [i, i+1]
        result.add(i to i + 1)

        // Try to extend as far as possible
        var j = i + 1
        while (j < nums.size - 1) {
            // Check if next element maintains the same diff
            val nextDiff = nums[j + 1] - nums[j]

            if (nextDiff != diff) break

            // Valid extension found
            j++
            result.add(i to j)
        }
    }

    return result
}


// ============================================================================
// JUNIT TEST CASES
// ============================================================================

class GoodArithmeticSequencesTest {

    // Helper function to compare results ignoring order
    private fun assertSamePairs(expected: List<Pair<Int, Int>>, actual: List<Pair<Int, Int>>) {
        assertEquals(expected.toSet(), actual.toSet(),
            "Expected: ${expected.toSet()}, Actual: ${actual.toSet()}")
    }

    // Example Test Cases
    @Test
    fun example1_increasingSequence() {
        val nums = intArrayOf(1, 2, 3)
        val expected = listOf(0 to 1, 0 to 2, 1 to 2)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun example2_decreasingSequence() {
        val nums = intArrayOf(3, 2, 1)
        val expected = listOf(0 to 1, 0 to 2, 1 to 2)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun example3_mixedSequence() {
        val nums = intArrayOf(1, 2, 2, 1, 0)
        // nums[0..1] = [1,2] diff +1 ✓
        // nums[1..2] = [2,2] diff 0 ✗
        // nums[2..3] = [2,1] diff -1 ✓
        // nums[3..4] = [1,0] diff -1 ✓
        // nums[2..4] = [2,1,0] diff -1 throughout ✓
        val expected = listOf(0 to 1, 2 to 3, 2 to 4, 3 to 4)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    // Edge Cases
    @Test
    fun edge1_emptyArray() {
        val nums = intArrayOf()
        val expected = emptyList<Pair<Int, Int>>()
        val result = findGoodArithmeticSequences(nums)
        assertEquals(expected, result)
    }

    @Test
    fun edge2_singleElement() {
        val nums = intArrayOf(5)
        val expected = emptyList<Pair<Int, Int>>()
        val result = findGoodArithmeticSequences(nums)
        assertEquals(expected, result)
    }

    @Test
    fun edge3_twoElementsValidIncreasing() {
        val nums = intArrayOf(1, 2)
        val expected = listOf(0 to 1)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun edge4_twoElementsValidDecreasing() {
        val nums = intArrayOf(5, 4)
        val expected = listOf(0 to 1)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun edge5_twoElementsInvalidDiff() {
        val nums = intArrayOf(1, 3)
        val expected = emptyList<Pair<Int, Int>>()
        val result = findGoodArithmeticSequences(nums)
        assertEquals(expected, result)
    }

    @Test
    fun edge6_twoElementsSameDiff() {
        val nums = intArrayOf(5, 5)
        val expected = emptyList<Pair<Int, Int>>()
        val result = findGoodArithmeticSequences(nums)
        assertEquals(expected, result)
    }

    // Long Sequences
    @Test
    fun long1_entireArrayIncreasing() {
        val nums = intArrayOf(1, 2, 3, 4, 5)
        // All subarrays of length >= 2 with diff +1
        val expected = listOf(
            0 to 1, 0 to 2, 0 to 3, 0 to 4,  // Starting at 0
            1 to 2, 1 to 3, 1 to 4,          // Starting at 1
            2 to 3, 2 to 4,                  // Starting at 2
            3 to 4                           // Starting at 3
        )
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun long2_entireArrayDecreasing() {
        val nums = intArrayOf(10, 9, 8, 7)
        val expected = listOf(
            0 to 1, 0 to 2, 0 to 3,
            1 to 2, 1 to 3,
            2 to 3
        )
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    // No Valid Sequences
    @Test
    fun none1_allSameValues() {
        val nums = intArrayOf(5, 5, 5, 5)
        val expected = emptyList<Pair<Int, Int>>()
        val result = findGoodArithmeticSequences(nums)
        assertEquals(expected, result)
    }

    @Test
    fun none2_largeDifferences() {
        val nums = intArrayOf(1, 5, 10, 20)
        val expected = emptyList<Pair<Int, Int>>()
        val result = findGoodArithmeticSequences(nums)
        assertEquals(expected, result)
    }

    @Test
    fun none3_randomPattern() {
        val nums = intArrayOf(1, 3, 2, 5, 1)
        val expected = emptyList<Pair<Int, Int>>()
        val result = findGoodArithmeticSequences(nums)
        assertEquals(expected, result)
    }

    // Mixed Patterns
    @Test
    fun mixed1_alternatingValidInvalid() {
        val nums = intArrayOf(1, 2, 5, 6, 7)
        // [1,2] diff +1 ✓
        // [2,5] diff +3 ✗
        // [5,6] diff +1 ✓
        // [6,7] diff +1 ✓
        // [5,6,7] diff +1 throughout ✓
        val expected = listOf(0 to 1, 2 to 3, 2 to 4, 3 to 4)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun mixed2_multipleDisjointRuns() {
        val nums = intArrayOf(1, 2, 3, 10, 9, 8, 20, 21)
        // Run 1: [1,2,3] indices 0-2
        // Run 2: [10,9,8] indices 3-5
        // Run 3: [20,21] indices 6-7
        val expected = listOf(
            0 to 1, 0 to 2, 1 to 2,          // Run 1
            3 to 4, 3 to 5, 4 to 5,          // Run 2
            6 to 7                           // Run 3
        )
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun mixed3_upThenDown() {
        val nums = intArrayOf(1, 2, 3, 2, 1)
        // [1,2,3] diff +1 ✓
        // [3,2,1] diff -1 ✓
        val expected = listOf(
            0 to 1, 0 to 2, 1 to 2,          // Increasing part
            2 to 3, 2 to 4, 3 to 4           // Decreasing part
        )
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun mixed4_downThenUp() {
        val nums = intArrayOf(5, 4, 3, 4, 5)
        val expected = listOf(
            0 to 1, 0 to 2, 1 to 2,          // Decreasing part
            2 to 3, 2 to 4, 3 to 4           // Increasing part
        )
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    // Special Patterns
    @Test
    fun special1_zigzag() {
        val nums = intArrayOf(1, 2, 1, 2, 1)
        // Each adjacent pair has diff ±1, but direction changes
        val expected = listOf(
            0 to 1,  // [1,2] diff +1
            1 to 2,  // [2,1] diff -1
            2 to 3,  // [1,2] diff +1
            3 to 4   // [2,1] diff -1
        )
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun special2_repeatedPairs() {
        val nums = intArrayOf(1, 2, 1, 2, 1, 2)
        val expected = listOf(
            0 to 1, 1 to 2, 2 to 3, 3 to 4, 4 to 5
        )
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    // Large Values
    @Test
    fun large1_nearIntMax() {
        val nums = intArrayOf(Int.MAX_VALUE - 2, Int.MAX_VALUE - 1, Int.MAX_VALUE)
        val expected = listOf(0 to 1, 0 to 2, 1 to 2)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun large2_nearIntMin() {
        val nums = intArrayOf(Int.MIN_VALUE, Int.MIN_VALUE + 1, Int.MIN_VALUE + 2)
        val expected = listOf(0 to 1, 0 to 2, 1 to 2)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun large3_negativeThroughZero() {
        val nums = intArrayOf(-2, -1, 0, 1, 2)
        val expected = listOf(
            0 to 1, 0 to 2, 0 to 3, 0 to 4,
            1 to 2, 1 to 3, 1 to 4,
            2 to 3, 2 to 4,
            3 to 4
        )
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    // Single Valid Pair in Larger Array
    @Test
    fun single1_onePairAtStart() {
        val nums = intArrayOf(1, 2, 5, 8, 11)
        val expected = listOf(0 to 1)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun single2_onePairInMiddle() {
        val nums = intArrayOf(1, 5, 6, 10, 15)
        val expected = listOf(1 to 2)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    @Test
    fun single3_onePairAtEnd() {
        val nums = intArrayOf(1, 5, 10, 20, 21)
        val expected = listOf(3 to 4)
        val result = findGoodArithmeticSequences(nums)
        assertSamePairs(expected, result)
    }

    // Verify Count for Long Sequences
    @Test
    fun verify1_countForLength5() {
        val nums = intArrayOf(1, 2, 3, 4, 5)
        val result = findGoodArithmeticSequences(nums)
        // For a sequence of length n, total subarrays = n*(n-1)/2
        // For n=5: 5*4/2 = 10
        assertEquals(10, result.size)
    }

    @Test
    fun verify2_countForLength10() {
        val nums = IntArray(10) { it + 1 }  // [1,2,3,4,5,6,7,8,9,10]
        val result = findGoodArithmeticSequences(nums)
        // For n=10: 10*9/2 = 45
        assertEquals(45, result.size)
    }
}


// ============================================================================
// MAIN FUNCTION - Manual Testing and Demonstration
// ============================================================================

fun main() {
    println("=".repeat(80))
    println("Good Arithmetic Sequences - Find All Valid Subarrays")
    println("=".repeat(80))

    val testCases = listOf(
        intArrayOf(1, 2, 3) to "Increasing sequence",
        intArrayOf(3, 2, 1) to "Decreasing sequence",
        intArrayOf(1, 2, 2, 1, 0) to "Mixed with invalid diff=0",
        intArrayOf(1, 2, 3, 2, 1) to "Up then down",
        intArrayOf(5, 4, 3, 4, 5) to "Down then up",
        intArrayOf(1, 2, 5, 6, 7) to "Multiple disjoint runs",
        intArrayOf(5, 5, 5, 5) to "All same (no valid sequences)",
        intArrayOf(1, 3, 5, 7) to "Diff = +2 (invalid)"
    )

    testCases.forEachIndexed { index, (nums, description) ->
        println("\nTest Case ${index + 1}: $description")
        println("-".repeat(80))
        println("Input: ${nums.joinToString(", ")}")

        val result = findGoodArithmeticSequences(nums)

        if (result.isEmpty()) {
            println("Output: No valid sequences found")
        } else {
            println("Output: ${result.size} valid sequence(s)")
            result.sortedWith(compareBy({ it.first }, { it.second })).forEach { (i, j) ->
                val subarray = nums.slice(i..j)
                val diff = if (subarray.size >= 2) subarray[1] - subarray[0] else 0
                println("  ($i, $j) → ${subarray.joinToString(",")} (diff: ${if (diff > 0) "+$diff" else "$diff"})")
            }
        }
    }

    println("\n" + "=".repeat(80))
    println("Algorithm Analysis")
    println("=".repeat(80))
    println("Time Complexity: O(n²) worst case")
    println("  - For each starting position (n iterations)")
    println("  - May extend to end of array (n iterations)")
    println("  - In worst case (entire array is valid), generates n*(n-1)/2 pairs")
    println()
    println("Space Complexity: O(1) excluding output")
    println("  - Only uses a few variables for iteration")
    println("  - Output size is O(n²) in worst case")
    println()
    println("Key Insight:")
    println("  - For each starting position, find the longest run with constant diff ±1")
    println("  - All subarrays within this run are valid")
    println("  - Different starting positions may overlap in output, but that's expected")
    println("=".repeat(80))
}
