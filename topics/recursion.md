# Recursion
It's a method to solve problem from smaller instances of the same problem.

For factorial function `n!`, we can solve `n * (n - 1)!`, keep breaking down until `n = 0`

```
n! = 1              for n = 0, base case
   = n * (n - 1)!   for n >= 1, recursive case
```

There are some common usages or applications of stack:
1. Binary search
2. Merge sort
3. Tree traversal
4. DFS

## Key Points
1. Base case: it defines when the recursion stops, it must eventually hit the base case.
1. Recursive call: it breaks down into smaller subproblem that invokes the recursive call.

For Fibonacci sequence:
* Base case: `fib(0) = 0` and `fib(1) = 1`.
* Recursive call: `fib(n) = fib(n - 1) + fib(n - 2)` for `n > 1`

```kotlin
fun fibonacci(n: Int): Int {
    if (n <= 1) return n
    else return fibonacci(n - 1) + fibonacci(n - 2)
}
```

### Tips for Problem Solving
* Make sure to define **all** base cases to cover.
* Recursion is useful for *permutation*, it generates all combinations and tree-based problems.
* Recursion implicitly uses a [stack](../topics/stack-queue.md), hence, the recursive algorithm can be rewritten iteratively using a stack. (Mind the stack overflow issue).

## Relation with Dynamic Programming (DP)
Recursive algorithm involves a function calling itself with smaller instance, similarly, DP breaks down problems into subproblems to solve a larger problem, DP often implemented recursively.

## Resources
- [X] [Khan Academy](https://www.khanacademy.org/computing/computer-science/algorithms/recursive-algorithms/a/recursion)
- [ ] CLRS // Recurrences
- [ ] CTCI
- [ ] LC Learn [Part 1](https://leetcode.com/explore/learn/card/recursion-i/) / [Part 2](https://leetcode.com/explore/learn/card/recursion-ii/)
- [X] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/recursion/)
- [X] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-9)
- [X] [Tech-Interview-Cheat-Sheet](https://github.com/TSiege/Tech-Interview-Cheat-Sheet#recursive-algorithms)
- [X] ~~[Coding Interview University](https://github.com/jwasham/coding-interview-university#recursion)~~ // Old standford videos

