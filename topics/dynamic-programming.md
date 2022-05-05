# Dynamic Programming
The *dynamic programming* (DP) generalizes *divide and conquer* method, solves problems by combining the solutions to subproblems.

> The word "programming" refers to the "tabular" method, not coding.

The DP algorithm is applicable when the subproblems are **dependent**, the subproblems share subsubproblems. It solves every subsubproblem once and saves its answer in a table (*programming*), thereby avoiding recompute every time the subsubproblem. (The subproblem dependencies from a directed acyclic graph instead of tree.) 

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

DP is the recursion with memoization:

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
1. Characterize the structure of an optimal solution.
2. Define the solution of original problem recursively in terms of the solution to subproblem.
3. Compute the value of optimal solution of subproblem in a bottom-up fashion.
4. Constuct the optimal solution of original problem from step 3. (memoization).

For the assembly line scheduling problem (CLRS P.324):

![Assembly Line Scheduling](../media/assembly-line-scheduling.png)
1. The fastest way is either through the previous station in the same line or a transfer from another line.
2. `f1(n) = min{f1(n - 1) + a(n), f2(n - 1) + t(n -1) + a(n)}`
3. Calculate `f1(n)` and `f2(n)` for each station, and store the "parent pointer".
4. Construct the fast path from the stored "parent pointer" of step 3.

## References
- [ ] CLRS
- [ ] [MIT](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-15-dynamic-programming-part-1-srtbot-fib-dags-bowling/) // There are four videos
- [ ] CTCI
- [ ] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-9)
- [ ] [LC Learn](https://leetcode.com/explore/learn/)
- [ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#dynamic-programming)
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/dynamic-programming/) // Some references posts
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/)
- [ ] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/dynamic-programming
- [ ] https://github.com/youngyangyang04/leetcode-master#%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92