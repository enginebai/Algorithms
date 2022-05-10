# Dynamic Programming
The *dynamic programming* (DP) generalizes *divide and conquer* method, solves problems by combining the solutions to subproblems.

> The word "programming" refers to the "tabular" method, not coding.

The result of the previous answers helps us in choosing the future ones, and we have to break up a problem into a series of overlapping subproblem (top-down), and build up solutions from bottom subproblems to larger sub problems, and finally to the original problem (bottom-up).

The DP algorithm is applicable when the subproblems are **dependent**, the subproblems share subsubproblems. It solves every subsubproblem once and saves its answer in a table (*programming*), thereby avoiding recompute every time the subsubproblem. (The subproblem dependencies from a directed acyclic graph instead of tree.) DP is a powerful technique that solves problems in `O(n ^ 2)` or `O(n ^ 3)` for which navie or brute-force approach would take exponential time. 

DP is typically applied to *optimization problems*, there will be many possible solutions with a *value* and we wish to find a solution with optimal value which is minimum or maximum.

## Solve Problems [Recursively](../topics/recursion.md)
Solution representation classification based on shape of graph:

| Methods             | Graph   |
|---------------------|---------|
| Brute Force         | Star    |
| Divide & Conquer    | Tree    |
| Decrease & Conquer  | Chain   |
| Dynamic Programming | Graph   |
| Greedy              | Subgraph|

Recursive function call represents a vertex in a graph, and a directed edge from `A` to `B` if `B` calls `A`. And the graph of recursive calls must be acyclic. (otherwise it never terminates) --> Function call sequence is the topological order on the graph. And DP if subproblem dependencies **overlap** in the graph.

To solve a problem using DP, we follow the *recursive algorithm design paradigm*: `SRT BOT`
* **S**ubprobem definition: 
    * Describe **in words** in terms of parameters.
    * It's often the subset of input, such as prefix/suffix/substrings of a sequence.
* **R**elate subproblem solutions recursively
* **T**opological order on subproblems
* **B**ase case
* **O**riginal problem solution via subproblem
* **T**ime analysis: The total **non-recursive** work * number of the work.

Let's take a look at how to solve *Fibonacci Numbers* using `SRT BOT` framework:
* **S**ubproblem: `F(i)`, the i-th Fibonacci number where `0 <= i <= n`.
* **R**elation: `F(i) = F(i - 1) + F(i - 2)`
* **T**opological order: Increasing `i`.
* **B**ase case: `F(i) = i` for `i` = 0 or 1.
* **O**riginal problem: `F(n)`
* **T**ime: `T(n) = T(n - 1) + T(n - 2)` by recurrence, that would be `Θ(2 ^ n)`.

The time complexity is exponential and there are some duplicate calculations, we can use *memoization* to avoid the re-calculation.

### Memoization
**Idea!** Remember and re-use the solution to subproblems, we maintain hash table mapping to subproblem, and the recursive function calls return the stored value or compute + store if not exist. For Fibonacci Numbers, we only have to calculate `F(1)`, `F(2)`...`F(n - 1)`, store and re-use for `F(n)`, that will be `O(n)` calculation.

The intuition behind DP is that we **trade space for time**. DP is the recursion with memoization:

```kotlin
fun solveProblem(subproblem) {
    // We check the memo first, return once if record exists
    if (subproblem in memo) {
        return memo[subproblem]
    } else {
        // We calculate solution to a subproblem
        val solution = if (subproblem is base case) 
            base case
        else {
            recursion via relation
        }
        // And record into a memo
        memo[subproblem] = solution
    }
}
```

We might need to maintain auxiliary information (such as parent pointer to the subproblem) in addition to reconstruct the answer we are optimizing.

### How to Relate Subproblem Solutions
1. Try to identify the queation about a subproblem.
2. Then locally brute-force the question, try all possible answers, and take the best one. The key for efficiency is that for the questions having a small number of possible answer, we can brute-force it very quickly.

## Steps of Dynamic Programming
1. Show the problem can be broken down into optimal subproblems.
2. Define the solution of original problem recursively in terms of the solution to subproblem.
3. Compute the value of optimal solution of subproblem in a bottom-up fashion.
4. Constuct the optimal solution of original problem from step 3. (memoization) and *backtrack*.

For the assembly line scheduling problem (CLRS P.324):

![Assembly Line Scheduling](../media/assembly-line-scheduling.png)
1. The fastest way is either through the previous station in the same line or a transfer from another line.
2. `f1(n) = min{f1(n - 1) + a(n), f2(n - 1) + t(n -1) + a(n)}`
3. Calculate `f1(n)` and `f2(n)` for each station, and store the *backtrack* solution.
4. Construct the fast path from the stored *backtrack* of step 3.

## Elements of Dynamic Programming
* **Optimal substructure**: The optimal solution to the original problem contains within its optimal solutions to subproblems. That is, we can build the solution from the solutions to subproblem.
* **Overlapping subproblem**: The problem can be broken down into *overlapping subproblems". We can develop the recursive algorithm that solves the same subproblems over and over (memoization).
* **Memoization**: We maintain a table with subproblem solutions so that we can re-use to build the solution from bottom-up.

## 0-1 Knapsack Problem

```
Items: (value / weight)
X1: $1 / 2kg
X2: $10 / 5kg
X3: $7 / 3kg
X4: $13 / 8kg

Knapsack = 10kg max capacity
```

### Modeling the Problem
We have weight `w(i)`, value `v(i)` for each item, capacity = `k` for knapsack, to find the subset of items which value is max and sum of weights <= `k`. We also define `x(i)` to represent if an item is selected `1` or not `0`.

* Constraint: `SUM(i = 1 to N) {w(i) * x(i)} <= k`
* Objective: `SUM(i = 1 to N) {v(i) * x(i)}` is maximum and subject to the above problem constraint.

Optimal structure is `O(k, j)` is capacity `k` of selected items `{1...j}`, which maximize `SUM(i = 1 to N) {v(i) * x(i)}` and is subject to `SUM(i = 1 to N) {w(i) * x(i)} <= k`

For our example will be:
```
O(k, j) = max{1 * x1 + 10 * x2 + 7 * x3 + 13 * x4} for some j and is subject to 2 * x1 + 5 * x2 + 3 * x3 + 8 * x4 <= k
```

### Recursion by Brute-Force
The recursive solution is 
```
O(k, j) =
    max{v(j) + O(k - w(j), j - 1),  O(k, j - 1)} if we take or skip itmm 
    O(k, j - 1) if item j is overweighted, w(j) > k, then we skip it
```

And the base case is `O(k, 0) = 0` we don't take anything.

```kotlin
val values: IntArray = (...)
val weights: IntArray = (...)

// Here we use 1 as first index
val memo = [values.size][values.size]

fun knapsack(capacity: Int, j: Int): Int {
    // Empty or no knapsack
    if (j < 0 || capacity <= 0) return 0

    // Memoization
    if (memo[capacity][j] != null) return memo[capacity][j]

    // Overweight
    if (weights[j] > capacity) return knapsack(capacity, j - 1)
    else {
        // Take or skip j-th item
        val value = max(
            values[j] + knapsack(capacity - weights[j], j - 1),
            knapsack(capacity, j - 1)
        )
        memo[capacity][j] = value
    }
}

knapsack(10, values.size - 1)
```

* **Time Complexity**: `O(W * N)`, where `N` is the number of items, and `W` for storing every possible weights range from 1 ~ `W` of the capacity.
* **Space Complexity**: `O(W * N)` for 2D array for memoization.

## Problems & Solutions
| Problem         | Solution | Difficulty |
|------------------|----------|------------|

### Tips for Problem Solving
#### When to use DP? 
The problem meets the both two characteristics:
1. Solve the optimal solution (often, but not always), for example:
    * The minimum cost
    * The maximum profit
    * How many ways are there to do...
    * What's the longest/shortest possible...
2. The solution of original problem comes from eariler calculated solution (of the subproblem). That is, the later step will be affected by the eariler step.

Sometimes, only meets the first but not the second might be the greedy algorithm, not DP.

#### Memoization Recipe
Overall, try to think about your recursive functions call in terms of a **tree**, try to brute force all solutions, then you can recognize **where can I optimize the brute force solution.

1. Make it work.
    * Visualize the problem as a *tree*.
    * Implement the tree using *recursion*.
    * Verify your solution works.

2. Make it efficient.
    * Design the *memo* data structure.
    * Check the memo first and return it.
    * Store into memo if it doesn't exist and calculate the solution.

## References
- [X] CLRS
- [X] [MIT](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-15-dynamic-programming-part-1-srtbot-fib-dags-bowling/) // There are four videos
- [ ] CTCI
- [X] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-9)
    * There a nice articles: [Introduction to Dynamic Programming](https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/tutorial/)
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/)
- [ ] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/dynamic-programming
- [ ] https://github.com/youngyangyang04/leetcode-master#%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92
- [/] [LC Learn](https://leetcode.com/explore/learn/card/dynamic-programming/) // Some topics are locked
- ~~[ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#dynamic-programming)~~
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/dynamic-programming/) // Not too much notes, some references posts only