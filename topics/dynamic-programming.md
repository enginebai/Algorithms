# Dynamic Programming
The *dynamic programming* (DP) generalizes *divide and conquer* method, solves problems by combining the solutions to subproblems.

> The word "programming" refers to the "tabular" method, not coding.

The dp algorithm is applicable when the subproblems are dependent, the subproblems share subsubproblems. It solves every subsubproblem once and saves its answer in a table, thereby avoiding recompute every time the subsubproblem. (The subproblem dependencies from a directed acyclic graph instead of tree.)

## Solve Problems [Recursively](../topics/recursion.md)
* Const-sized program solves arbitrary input
* Recursive function call represents a vertex in a graph, and a directed edge from `A` to `B` if `B` calls `A`. And the graph of recursive calls must be acyclic. (otherwise it never terminates) --> Topological order on the graph.
* Solution representation classification based on shape of graph:

| Methods             | Graph   |
|---------------------|---------|
| Brute Force         | Star    |
| Divide & Conquer    | Tree    |
| Decrease & Conquer  | Chain   |
| Dynamic Programming | Graph   |
| Greedy              | Sugraph |

To solve a problem using DP, we follow the *recursive algorithm design paradigm*: `SRT BOT`
* **S**ubprobem definition
* **R**elate subproblem solutions recursively
* **T**opological order on subproblems
* **B**ase case
* **O**riginal problem solution via subproblem
* **T**ime analysis

## Solve Pro
## References
- [ ] CLRS
- [ ] [MIT](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-spring-2020/lecture-videos/index.htm)
- [ ] CTCI
- [ ] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-9)
- [ ] [LC Learn](https://leetcode.com/explore/learn/)
- [ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#dynamic-programming)
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/dynamic-programming/) // Some references posts
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/)
- [ ] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/dynamic-programming
- [ ] https://github.com/youngyangyang04/leetcode-master#%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92