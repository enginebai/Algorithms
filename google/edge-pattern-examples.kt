/**
 * Edge Pattern Examples - Side-by-Side Comparisons
 *
 * Demonstrates lookahead vs lookback for several LeetCode problems
 */

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

// ============================================================================
// LC 896: Monotonic Array - Both Work Equally Well
// ============================================================================

/**
 * Check if array is entirely non-increasing or non-decreasing
 * Both lookahead and lookback work identically
 */
fun isMonotonicLookahead(nums: IntArray): Boolean {
    var increasing = true
    var decreasing = true

    // Lookahead: compare i with i+1
    for (i in 0 until nums.size - 1) {
        if (nums[i] < nums[i + 1]) decreasing = false
        if (nums[i] > nums[i + 1]) increasing = false
    }

    return increasing || decreasing
}

fun isMonotonicLookback(nums: IntArray): Boolean {
    var increasing = true
    var decreasing = true

    // Lookback: compare i-1 with i
    for (i in 1 until nums.size) {
        if (nums[i - 1] < nums[i]) decreasing = false
        if (nums[i - 1] > nums[i]) increasing = false
    }

    return increasing || decreasing
}

// ============================================================================
// LC 55: Jump Game - Lookahead More Natural
// ============================================================================

/**
 * Can you reach the last index?
 * Lookahead is more natural: "Where can I reach from here?"
 */
fun canJumpLookahead(nums: IntArray): Boolean {
    var maxReach = 0

    // Lookahead: planning forward jumps
    for (i in 0 until nums.size) {
        if (i > maxReach) return false  // Can't reach here
        maxReach = maxOf(maxReach, i + nums[i])  // How far can I go from i?
        if (maxReach >= nums.size - 1) return true
    }

    return true
}

/**
 * Lookback version (less natural but works)
 */
fun canJumpLookback(nums: IntArray): Boolean {
    val n = nums.size
    val canReach = BooleanArray(n)
    canReach[0] = true

    for (i in 1 until n) {
        // Check if any previous position can reach i
        for (j in 0 until i) {
            if (canReach[j] && j + nums[j] >= i) {
                canReach[i] = true
                break
            }
        }
    }

    return canReach[n - 1]
}

// ============================================================================
// LC 53: Maximum Subarray - Lookback More Natural
// ============================================================================

/**
 * Find contiguous subarray with maximum sum (Kadane's Algorithm)
 * Lookback is more natural: "Should I extend previous or start fresh?"
 */
fun maxSubArrayLookback(nums: IntArray): Int {
    var maxSum = nums[0]

    // Lookback: building from previous result
    for (i in 1 until nums.size) {
        // Key decision: extend previous subarray or start new?
        nums[i] = maxOf(nums[i], nums[i] + nums[i - 1])
        maxSum = maxOf(maxSum, nums[i])
    }

    return maxSum
}

/**
 * Lookahead version (less natural but works)
 */
fun maxSubArrayLookahead(nums: IntArray): Int {
    var currentSum = 0
    var maxSum = Int.MIN_VALUE

    for (i in 0 until nums.size) {
        currentSum = maxOf(nums[i], currentSum + nums[i])
        maxSum = maxOf(maxSum, currentSum)
    }

    return maxSum
}

// ============================================================================
// LC 121: Best Time to Buy and Sell Stock - Lookback More Natural
// ============================================================================

/**
 * Find max profit from one transaction
 * Lookback is natural: "What was the minimum price before today?"
 */
fun maxProfitLookback(prices: IntArray): Int {
    var minPrice = prices[0]
    var maxProfit = 0

    // Lookback: track minimum seen so far
    for (i in 1 until prices.size) {
        minPrice = minOf(minPrice, prices[i - 1])
        maxProfit = maxOf(maxProfit, prices[i] - minPrice)
    }

    return maxProfit
}

/**
 * Lookahead version (still natural for this problem)
 */
fun maxProfitLookahead(prices: IntArray): Int {
    var minPrice = Int.MAX_VALUE
    var maxProfit = 0

    for (price in prices) {
        minPrice = minOf(minPrice, price)
        maxProfit = maxOf(maxProfit, price - minPrice)
    }

    return maxProfit
}

// ============================================================================
// LC 845: Longest Mountain - Both Work Well
// ============================================================================

/**
 * Find longest subarray that strictly increases then strictly decreases
 * Both approaches work well
 */
fun longestMountainLookahead(arr: IntArray): Int {
    val n = arr.size
    if (n < 3) return 0

    var maxLength = 0

    for (i in 0 until n - 1) {
        // Try to build mountain starting from i
        if (arr[i] >= arr[i + 1]) continue  // Not uphill start

        // Count uphill
        var end = i
        while (end < n - 1 && arr[end] < arr[end + 1]) end++

        // Must have downhill to be a mountain
        if (end == n - 1 || arr[end] <= arr[end + 1]) continue

        // Count downhill
        while (end < n - 1 && arr[end] > arr[end + 1]) end++

        maxLength = maxOf(maxLength, end - i + 1)
    }

    return maxLength
}

fun longestMountainLookback(arr: IntArray): Int {
    val n = arr.size
    if (n < 3) return 0

    val up = IntArray(n)  // up[i] = length of increasing sequence ending at i
    val down = IntArray(n)  // down[i] = length of decreasing sequence starting at i

    // Calculate up values
    for (i in 1 until n) {
        if (arr[i] > arr[i - 1]) up[i] = up[i - 1] + 1
    }

    // Calculate down values
    for (i in n - 2 downTo 0) {
        if (arr[i] > arr[i + 1]) down[i] = down[i + 1] + 1
    }

    // Find max mountain length
    var maxLength = 0
    for (i in 0 until n) {
        if (up[i] > 0 && down[i] > 0) {  // Peak must have both up and down
            maxLength = maxOf(maxLength, up[i] + down[i] + 1)
        }
    }

    return maxLength
}

// ============================================================================
// JUNIT TESTS
// ============================================================================

class EdgePatternExamplesTest {

    @Test
    fun testMonotonic() {
        val test1 = intArrayOf(1, 2, 2, 3)  // increasing
        val test2 = intArrayOf(6, 5, 4, 4)  // decreasing
        val test3 = intArrayOf(1, 3, 2)     // neither

        assertEquals(true, isMonotonicLookahead(test1))
        assertEquals(true, isMonotonicLookback(test1))

        assertEquals(true, isMonotonicLookahead(test2))
        assertEquals(true, isMonotonicLookback(test2))

        assertEquals(false, isMonotonicLookahead(test3))
        assertEquals(false, isMonotonicLookback(test3))
    }

    @Test
    fun testJumpGame() {
        val test1 = intArrayOf(2, 3, 1, 1, 4)  // can reach
        val test2 = intArrayOf(3, 2, 1, 0, 4)  // cannot reach

        assertEquals(true, canJumpLookahead(test1))
        assertEquals(true, canJumpLookback(test1))

        assertEquals(false, canJumpLookahead(test2))
        assertEquals(false, canJumpLookback(test2))
    }

    @Test
    fun testMaxSubarray() {
        val test1 = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)  // [4,-1,2,1] = 6
        val test2 = intArrayOf(1)
        val test3 = intArrayOf(5, 4, -1, 7, 8)

        assertEquals(6, maxSubArrayLookback(test1.clone()))
        assertEquals(6, maxSubArrayLookahead(test1.clone()))

        assertEquals(1, maxSubArrayLookback(test2.clone()))
        assertEquals(1, maxSubArrayLookahead(test2.clone()))

        assertEquals(23, maxSubArrayLookback(test3.clone()))
        assertEquals(23, maxSubArrayLookahead(test3.clone()))
    }

    @Test
    fun testMaxProfit() {
        val test1 = intArrayOf(7, 1, 5, 3, 6, 4)  // buy at 1, sell at 6
        val test2 = intArrayOf(7, 6, 4, 3, 1)     // no profit

        assertEquals(5, maxProfitLookback(test1))
        assertEquals(5, maxProfitLookahead(test1))

        assertEquals(0, maxProfitLookback(test2))
        assertEquals(0, maxProfitLookahead(test2))
    }

    @Test
    fun testLongestMountain() {
        val test1 = intArrayOf(2, 1, 4, 7, 3, 2, 5)  // [1,4,7,3,2] = 5
        val test2 = intArrayOf(2, 2, 2)               // no mountain
        val test3 = intArrayOf(0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0)  // entire array = 11

        assertEquals(5, longestMountainLookahead(test1))
        assertEquals(5, longestMountainLookback(test1))

        assertEquals(0, longestMountainLookahead(test2))
        assertEquals(0, longestMountainLookback(test2))

        assertEquals(11, longestMountainLookahead(test3))
        assertEquals(11, longestMountainLookback(test3))
    }
}

// ============================================================================
// MAIN - DEMONSTRATION
// ============================================================================

fun main() {
    println("=".repeat(80))
    println("Edge Pattern Examples - Lookahead vs Lookback")
    println("=".repeat(80))
    println()

    println("Key Observations:")
    println()
    println("1. LC 896 (Monotonic Array): Both approaches identical")
    println("   - Just checking all edges for direction")
    println("   - Choose either based on preference")
    println()
    println("2. LC 55 (Jump Game): Lookahead more natural")
    println("   - Thinking forward: 'Where can I reach from here?'")
    println("   - Lookback works but requires DP tracking")
    println()
    println("3. LC 53 (Max Subarray): Lookback more natural")
    println("   - Building from previous: 'Extend or start fresh?'")
    println("   - Lookahead works but conceptually same")
    println()
    println("4. LC 121 (Stock): Both work well")
    println("   - Lookback: Track min seen before")
    println("   - Lookahead: Track min as you go")
    println()
    println("5. LC 845 (Mountain): Both work, different styles")
    println("   - Lookahead: Extend uphill then downhill")
    println("   - Lookback: Precompute up/down lengths")
    println()

    println("=".repeat(80))
    println("General Guidelines:")
    println("=".repeat(80))
    println()
    println("✓ Edge-based problems (checking differences): BOTH WORK EQUALLY")
    println("  → Just pick one and be consistent!")
    println()
    println("✓ Planning/extending forward: LOOKAHEAD MORE NATURAL")
    println("  → Jump Game, Gas Station, Container With Water")
    println()
    println("✓ Building from previous state: LOOKBACK MORE NATURAL")
    println("  → DP problems, Kadane's, House Robber")
    println()
    println("But remember: ANY problem can be solved with EITHER approach!")
    println("The 'more natural' designation is about clarity, not correctness.")
    println()
    println("=".repeat(80))
}
