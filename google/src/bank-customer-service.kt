/**
 * Bank Customer Service - Maximum Consecutive Customers
 *
 * Problem:
 * A bank has an initial balance and an array of customers to serve.
 * Each customer either deposits (+value) or withdraws (-value) money.
 * Bank can start from any index but once started, must serve consecutive customers.
 * Bank balance must never go negative.
 * Find the maximum number of customers that can be served.
 *
 * Two implementations:
 * 1. Sliding Window - O(n) time, O(1) space (OPTIMAL)
 * 2. Brute Force - O(n²) time, O(1) space
 */

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

// ============================================================================
// APPROACH 1: Sliding Window (OPTIMAL)
// ============================================================================

/**
 * Main Function - Maximum Consecutive Customers
 *
 * Uses Sliding Window approach (optimal solution)
 *
 * Key Insight:
 * - Maintain a window [left, right] where balance never goes negative
 * - Expand right: add new customer
 * - Shrink left: when balance becomes negative, remove customers from start
 * - Track maximum window size
 *
 * Why it works:
 * - When we extend window by adding customer at right, we're adding to a known-valid prefix
 * - If balance becomes negative, we shrink from left until valid again
 * - Each element is added once and removed at most once → O(n)
 *
 * Time Complexity: O(n) - each element visited at most twice (once by right, once by left)
 * Space Complexity: O(1) - only a few variables
 */
fun maxConsecutiveCustomers(initialBalance: Int, transactions: IntArray): Int {
    var maxCustomers = 0
    var currentBalance = initialBalance.toLong()  // Use Long to prevent overflow
    var left = 0

    for (right in transactions.indices) {
        // Expand window: serve customer at right
        currentBalance += transactions[right]

        // Shrink window: if balance negative, remove customers from start
        while (currentBalance < 0 && left <= right) {
            currentBalance -= transactions[left]
            left++
        }

        // Update maximum window size
        maxCustomers = maxOf(maxCustomers, right - left + 1)
    }

    return maxCustomers
}


// ============================================================================
// APPROACH 2: Brute Force (For comparison and verification)
// ============================================================================

/**
 * Brute Force Approach
 *
 * Algorithm:
 * - Try every possible starting position
 * - From each start, extend as far as possible while balance >= 0
 * - Track maximum length found
 *
 * Time Complexity: O(n²) - nested loops
 * Space Complexity: O(1)
 */
fun maxConsecutiveCustomersBruteForce(initialBalance: Int, transactions: IntArray): Int {
    var maxCustomers = 0

    // Try each starting position
    for (start in transactions.indices) {
        var balance = initialBalance.toLong()
        var count = 0

        // Extend from this starting position
        for (end in start until transactions.size) {
            balance += transactions[end]

            // Stop if balance goes negative
            if (balance < 0) break

            count++
            maxCustomers = maxOf(maxCustomers, count)
        }
    }

    return maxCustomers
}


// ============================================================================
// JUNIT TEST CASES
// ============================================================================

class MaxConsecutiveCustomersTest {

    // Basic Test Cases
    @Test
    fun example1_allCustomersCanBeServed() {
        val initial = 10
        val customers = intArrayOf(5, -15, 10, -5, 20)
        // Start at 0: 10+5=15, 15-15=0, 0+10=10, 10-5=5, 5+20=25
        assertEquals(5, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun example2_bestStartingPositionInMiddle() {
        val initial = 8
        val customers = intArrayOf(2, -5, 4, -3, 6)
        assertEquals(5, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun example3_immediateNegativePreventsServingFirstCustomer() {
        val initial = 5
        val customers = intArrayOf(-10, 20, -5)
        // Start at 0: 5-10=-5 (can't serve)
        // Start at 1: 5+20=25, 25-5=20 (serve 2 customers)
        assertEquals(2, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun example4_onlyDeposits() {
        val initial = 0
        val customers = intArrayOf(5, 10, 15, 20)
        assertEquals(4, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun example5_onlyWithdrawals() {
        val initial = 30
        val customers = intArrayOf(-5, -10, -15, -20)
        // 30-5=25, 25-10=15, 15-15=0, 0-20=-20 (stop at 3)
        assertEquals(3, maxConsecutiveCustomers(initial, customers))
    }

    // Edge Cases
    @Test
    fun edge1_emptyArray() {
        val initial = 100
        val customers = intArrayOf()
        assertEquals(0, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun edge2_singleCustomerDeposit() {
        val initial = 10
        val customers = intArrayOf(5)
        assertEquals(1, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun edge3_singleCustomerWithdrawalFits() {
        val initial = 10
        val customers = intArrayOf(-5)
        assertEquals(1, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun edge4_singleCustomerWithdrawalTooLarge() {
        val initial = 10
        val customers = intArrayOf(-15)
        assertEquals(0, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun edge5_initialBalanceIsZero() {
        val initial = 0
        val customers = intArrayOf(10, -5, 3)
        assertEquals(3, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun edge6_initialBalanceZeroWithImmediateWithdrawal() {
        val initial = 0
        val customers = intArrayOf(-5, 10, 5)
        // Start at 0: 0-5=-5 (can't serve)
        // Start at 1: 0+10=10, 10+5=15 (serve 2)
        assertEquals(2, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun edge7_noCustomersCanBeServed() {
        val initial = 5
        val customers = intArrayOf(-10, -20, -30)
        assertEquals(0, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun edge8_exactBalanceReachesZero() {
        val initial = 10
        val customers = intArrayOf(-5, -5, 10)
        assertEquals(3, maxConsecutiveCustomers(initial, customers))
    }

    // Complex Scenarios
    @Test
    fun complex1_alternatingDepositsAndWithdrawals() {
        val initial = 10
        val customers = intArrayOf(5, -3, 8, -12, 15, -20, 25)
        assertEquals(7, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun complex2_largeWithdrawalInMiddleForcesReset() {
        val initial = 10
        val customers = intArrayOf(5, 10, -30, 20, 15)
        // Best: serve customers 0,1 or customers 3,4 (both give 2)
        assertEquals(2, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun complex3_multipleValidWindowsOfDifferentSizes() {
        val initial = 15
        val customers = intArrayOf(5, -10, -20, 30, 10, -5)
        // Window [0,1]: length 2
        // Window [3,5]: length 3
        assertEquals(3, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun complex4_gradualDeclineToZero() {
        val initial = 20
        val customers = intArrayOf(-5, -5, -5, -5, 10)
        assertEquals(5, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun complex5_sumIsPositiveButIntermediateStepIsNegative() {
        val initial = 10
        val customers = intArrayOf(-15, 20, 5)
        // This is the key test case - total sum is positive but can't start at 0
        assertEquals(2, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun complex6_recoveryAfterNegative() {
        val initial = 5
        val customers = intArrayOf(-10, -10, 50, -20, 10)
        assertEquals(3, maxConsecutiveCustomers(initial, customers))
    }

    // Large Numbers
    @Test
    fun large1_veryLargeBalances() {
        val initial = 1_000_000
        val customers = intArrayOf(500_000, -800_000, 1_000_000, -500_000)
        assertEquals(4, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun large2_potentialIntegerOverflow() {
        val initial = Int.MAX_VALUE / 2
        val customers = intArrayOf(Int.MAX_VALUE / 2, -Int.MAX_VALUE / 4, 1000)
        // Using Long prevents overflow
        assertEquals(3, maxConsecutiveCustomers(initial, customers))
    }

    // Special Patterns
    @Test
    fun pattern1_allSameValueDeposits() {
        val initial = 0
        val customers = intArrayOf(10, 10, 10, 10, 10)
        assertEquals(5, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun pattern2_allSameValueWithdrawals() {
        val initial = 25
        val customers = intArrayOf(-5, -5, -5, -5, -5)
        assertEquals(5, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun pattern3_symmetricPattern() {
        val initial = 20
        val customers = intArrayOf(10, -5, -10, -5, 10)
        assertEquals(5, maxConsecutiveCustomers(initial, customers))
    }

    @Test
    fun pattern4_increasingWithdrawals() {
        val initial = 50
        val customers = intArrayOf(-5, -10, -15, -20, -25)
        assertEquals(4, maxConsecutiveCustomers(initial, customers))
    }

    // Verification with Brute Force
    @Test
    fun verify1_randomTestCase() {
        val initial = 100
        val customers = intArrayOf(20, -30, 50, -40, 10, -20, 30, -50, 40)
        val result = maxConsecutiveCustomers(initial, customers)
        val resultBF = maxConsecutiveCustomersBruteForce(initial, customers)
        assertEquals(resultBF, result, "Should match brute force result")
    }

    @Test
    fun verify2_randomTestCase() {
        val initial = 50
        val customers = intArrayOf(-10, 15, -5, 20, -25, 30, -15, 10)
        val result = maxConsecutiveCustomers(initial, customers)
        val resultBF = maxConsecutiveCustomersBruteForce(initial, customers)
        assertEquals(resultBF, result, "Should match brute force result")
    }

    @Test
    fun verify3_allNegativeThenPositive() {
        val initial = 100
        val customers = intArrayOf(-20, -30, -40, -50, 200, 50, -10)
        val result = maxConsecutiveCustomers(initial, customers)
        val resultBF = maxConsecutiveCustomersBruteForce(initial, customers)
        assertEquals(resultBF, result, "Should match brute force result")
    }
}


// ============================================================================
// MAIN FUNCTION - Manual Testing and Demonstration
// ============================================================================

fun main() {
    println("=".repeat(80))
    println("Bank Customer Service - Maximum Consecutive Customers")
    println("=".repeat(80))

    val testCases = listOf(
        Triple(10, intArrayOf(5, -15, 10, -5, 20), "All customers can be served"),
        Triple(5, intArrayOf(-10, 20, -5), "Immediate negative prevents first customer"),
        Triple(10, intArrayOf(-15, 20, 5), "Sum positive but intermediate negative"),
        Triple(0, intArrayOf(10, -5, 3), "Zero initial balance"),
        Triple(30, intArrayOf(-5, -10, -15, -20), "Only withdrawals"),
        Triple(15, intArrayOf(5, -10, -20, 30, 10, -5), "Multiple valid windows")
    )

    testCases.forEachIndexed { index, (initial, customers, description) ->
        println("\nTest Case ${index + 1}: $description")
        println("-".repeat(80))
        println("Initial balance: $initial")
        println("Customers: ${customers.joinToString(", ")}")

        val result = maxConsecutiveCustomers(initial, customers)
        val resultBF = maxConsecutiveCustomersBruteForce(initial, customers)

        println("Sliding Window result: $result customers")
        println("Brute Force result: $resultBF customers")
        println("Match: ${if (result == resultBF) "✅" else "❌"}")
    }

    println("\n" + "=".repeat(80))
    println("Algorithm Comparison")
    println("=".repeat(80))
    println("Approach         | Time Complexity | Space Complexity | Notes")
    println("-".repeat(80))
    println("Sliding Window   | O(n)            | O(1)             | OPTIMAL - Recommended")
    println("Brute Force      | O(n²)           | O(1)             | Simple but slower")
    println("=".repeat(80))
}
