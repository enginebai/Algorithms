# Key Patterns and Approaches for Dynamic Programming Problems

Based on the problems in `problems/dynamic-programming-problems.md`, here is a summary of the key patterns and approaches for solving dynamic programming problems.

Dynamic Programming (DP) is an optimization technique that solves complex problems by breaking them into simpler subproblems, solving each subproblem once, and storing the results to avoid redundant computations.

### 1. Fundamental DP Concepts

#### 1.1 Core Elements
*   **Optimal Substructure:** The optimal solution contains optimal solutions to subproblems.
*   **Overlapping Subproblems:** The same subproblems are solved multiple times.
*   **State Definition:** Define what each DP state represents.
*   **Transition Formula:** How to compute current state from previous states.
*   **Base Cases:** Initial conditions for the recursion.

#### 1.2 Common Approaches
*   **Top-Down (Memoization):** Recursive approach with caching.
*   **Bottom-Up (Tabulation):** Iterative approach building from base cases.

### 2. Linear DP Patterns

Problems where state depends on a single parameter (usually index or value).

#### 2.1 Fibonacci-like Sequences
*   **Core Idea:** Each state depends on a few previous states.
*   **State:** `dp[i]` = answer for position `i`.
*   **Transition:** `dp[i] = f(dp[i-1], dp[i-2], ...)`.
*   **Examples:** [70. Climbing Stairs](../leetcode/70.climbing-stairs.md), [509. Fibonacci Number](../leetcode/509.fibonacci-number.md), [746. Min Cost Climbing Stairs](../leetcode/746.min-cost-climbing-stairs.md).

#### 2.2 House Robber Pattern
*   **Core Idea:** Make optimal choices with constraints on adjacent elements.
*   **State:** `dp[i]` = maximum money that can be robbed up to house `i`.
*   **Transition:** `dp[i] = max(dp[i-1], dp[i-2] + nums[i])`.
*   **Examples:** [198. House Robber](../leetcode/198.house-robber.md), [213. House Robber II](../leetcode/213.house-robber-ii.md) (circular), [337. House Robber III](../leetcode/337.house-robber-iii.md) (tree).

#### 2.3 Maximum Subarray Patterns
*   **Core Idea:** Find optimal contiguous subsequence with certain properties.
*   **State:** `dp[i]` = optimal value ending at position `i`.
*   **Transition:** `dp[i] = max(nums[i], dp[i-1] + nums[i])`.
*   **Examples:** [53. Maximum Subarray](../leetcode/53.maximum-subarray.md), [152. Maximum Product Subarray](../leetcode/152.maximum-product-subarray.md).

### 3. Grid DP Patterns

Problems on 2D grids where you can move in certain directions.

#### 3.1 Path Counting/Finding
*   **Core Idea:** Count paths or find optimal paths in a grid.
*   **State:** `dp[i][j]` = number of ways to reach cell `(i,j)`.
*   **Transition:** `dp[i][j] = dp[i-1][j] + dp[i][j-1]` (for right/down moves).
*   **Examples:** [62. Unique Paths](../leetcode/62.unique-paths.md), [63. Unique Paths II](../leetcode/63.unique-paths.ii.md), [64. Minimum Path Sum](../leetcode/64.minimum-path-sum.md).

#### 3.2 Square/Rectangle Problems
*   **Core Idea:** Find optimal squares or rectangles in a grid.
*   **State:** `dp[i][j]` = size of largest square ending at `(i,j)`.
*   **Transition:** `dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1`.
*   **Examples:** [221. Maximal Square](../leetcode/221.maximal-square.md).

### 4. Knapsack Patterns

Problems involving selecting items with constraints on capacity or count.

#### 4.1 0/1 Knapsack (Each Item Once)
*   **Core Idea:** Include or exclude each item to maximize value within weight limit.
*   **State:** `dp[i][w]` = maximum value using first `i` items with weight limit `w`.
*   **Transition:** `dp[i][w] = max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i])`.
*   **Examples:** [416. Partition Equal Subset Sum](../leetcode/416.partition-equal-subset-sum.md).

#### 4.2 Unbounded Knapsack (Unlimited Use)
*   **Core Idea:** Items can be used multiple times.
*   **State:** `dp[w]` = maximum value with weight limit `w`.
*   **Transition:** `dp[w] = max(dp[w], dp[w-weight[i]] + value[i])` for all items.
*   **Examples:** [322. Coin Change](../leetcode/322.coin-change.md), [518. Coin Change 2](../leetcode/518.coin-change-ii.md), [279. Perfect Squares](../leetcode/279.perfect-squares.md).

#### 4.3 Counting Combinations
*   **Core Idea:** Count number of ways to make a target using given items.
*   **Examples:** [377. Combination Sum IV](../leetcode/377.combination-sum-iv.md), [494. Target Sum](../leetcode/494.target-sum.md).

### 5. Sequence DP Patterns

Problems involving sequences, subsequences, and string matching.

#### 5.1 Longest Common Subsequence (LCS)
*   **Core Idea:** Find longest subsequence common to two sequences.
*   **State:** `dp[i][j]` = LCS length for first `i` chars of string1 and first `j` chars of string2.
*   **Transition:** 
    *   If `s1[i-1] == s2[j-1]`: `dp[i][j] = dp[i-1][j-1] + 1`
    *   Else: `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`
*   **Examples:** [1143. Longest Common Subsequence](../topics/dynamic-programming.md#longest-common-subsequence-problem), [718. Maximum Length of Repeated Subarray](../leetcode/718.maximum-length-of-repeated-subarray.md).

#### 5.2 Longest Increasing Subsequence (LIS)
*   **Core Idea:** Find longest strictly increasing subsequence.
*   **State:** `dp[i]` = length of LIS ending at position `i`.
*   **Transition:** `dp[i] = max(dp[j] + 1)` for all `j < i` where `nums[j] < nums[i]`.
*   **Examples:** [300. Longest Increasing Subsequence](../leetcode/300.longest-increasing-subsequence.md), [673. Number of Longest Increasing Subsequence](../leetcode/673.number-of-longest-increasing-subsequence.md).

#### 5.3 Palindrome Problems
*   **Core Idea:** Work with palindromic properties of strings.
*   **State:** `dp[i][j]` = whether substring from `i` to `j` is palindrome.
*   **Examples:** [516. Longest Palindromic Subsequence](../leetcode/516.longest-palindromic-subsequence.md), [5. Longest Palindromic Substring](../leetcode/5.longest-palindromic-substring.md), [647. Palindromic Substrings](../leetcode/647.palindromic-substrings.md).

### 6. State Machine DP

Problems where you have different states and transition between them.

#### 6.1 Stock Trading Problems
*   **Core Idea:** Model different states (holding stock, not holding, cooldown).
*   **States:** `hold[i]`, `sold[i]`, `rest[i]` representing different actions on day `i`.
*   **Examples:** [121. Best Time to Buy and Sell Stock](../leetcode/121.best-time-to-buy-and-sell-stock.md), [122. Best Time to Buy and Sell Stock II](../leetcode/122.best-time-to-buy-and-sell-stock-ii.md), [123. Best Time to Buy and Sell Stock III](../leetcode/123.best-time-to-buy-and-sell-stock-iii.md), [309. Best Time to Buy and Sell Stock with Cooldown](../leetcode/309.best-time-to-buy-and-sell-stock-with-cooldown.md).

### 7. Partition DP

Problems involving partitioning arrays or strings optimally.

#### 7.1 Word Break Pattern
*   **Core Idea:** Determine if string can be partitioned using given dictionary.
*   **State:** `dp[i]` = whether substring `s[0...i-1]` can be broken.
*   **Transition:** `dp[i] = true` if `dp[j] && s[j...i-1] in dict` for some `j < i`.
*   **Examples:** [139. Word Break](../leetcode/139.word-break.md).

---

### Key DP Decision Framework

1. **Identify if it's a DP Problem:**
   - Optimal solution requires examining all possibilities
   - Has overlapping subproblems
   - Has optimal substructure

2. **Define the State:**
   - What parameters uniquely identify a subproblem?
   - What does `dp[...]` represent?

3. **Find the Recurrence Relation:**
   - How can you compute current state from previous states?
   - What are the base cases?

4. **Choose Implementation Approach:**
   - Top-down with memoization for complex state spaces
   - Bottom-up for better space optimization

5. **Optimize Space:**
   - Can you reduce dimensions by only keeping recent states?
   - Rolling arrays for 2D problems that only need previous row

### Summary of Key Approaches

1. **Pattern Recognition:** Most DP problems fall into standard patterns. Learn to recognize them quickly.

2. **State Design:** The hardest part is often defining the right state. Include all necessary information, but keep it minimal.

3. **Transition Formula:** Focus on the decision at each step. What choices do you have?

4. **Base Cases:** Handle empty inputs, single elements, and boundary conditions carefully.

5. **Space Optimization:** After getting correct solution, consider if space can be optimized.

6. **Practice Templates:** Each pattern has a standard template. Master these templates for quick implementation.

Dynamic Programming success comes from recognizing patterns and applying the right template. Start by identifying which category the problem belongs to, then adapt the standard approach to the specific constraints.