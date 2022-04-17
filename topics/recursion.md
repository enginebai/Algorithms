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

### Memoization
We might encounter the duplicate calculation in recursive call, that is undesired penalty to the performance. To solve this situation, we will **store** the intermediate results of recursive call, this is known as *memoization*.

Let's take a look at *Fibonacci Number* example, `F(4) = F(3) + F(2) = (F(2) + F(1)) + F(2)`, where `F(2)` are duplicate calculations and we can use memoization to help.

```kotlin
object Calculator {
    val cachedMap = HashMap<Int, Int>()

    fun fabonacci(n: Int): Int {
        if (cachedMap.containsKey(n)) {
            return cachedMap[n]
        }
        var result = 0
        if (n <= 1) result = n
        else result = fabonacci(n - 1) + fabonacci(n - 2)
        cachedMap.put(n, result)
        return result
    }
}
```

### Time Complexity (Recurrences)
A *recurrence* is an equation or inequality that describes a function in terms of its value on *smaller inputs*.

There are three ways to solve recurrence:
1. **Substitution method**: We make a guess and use mathentical induction to prove the guess correct.
2. **Recursion tree**: We draw all the nodes represents the cost of the subproblems, then sum all costs per levels to determine the total cost of all levels of recursion.
3. **Master method**: It provides the bounds for the recurrences of the form `T(n) = a * T(n / b) + f(n)`, where `a >= 1`, `b > 1`, `f(n)` is a given function.

> More detail, see CLRS Chapter 4.

With memoization, we can reduct the number of recursive calls, for the Fabonacci Number example, since we save every result of each `n`, it would invoke at more `n - 1` times function calls for `f(n)`, hence, the time complexity will be reduced to `O(n)`.

### Space Complexity
We have to allocate stack space to the recursive function calls which depends on the leaf counts of recursion tree and extra space for memoization.

### Relation with Dynamic Programming (DP)
Recursive algorithm involves a function calling itself with smaller instance, similarly, DP breaks down problems into subproblems to solve a larger problem, DP often implemented recursively.

## Problems & Solutions
| Problem         | Solution | Difficulty |
|------------------|----------|------------|

### Tips for Problem Solving
* Make sure to define **all** base cases to cover.
* Recursion is useful for *permutation*, it generates all combinations and tree-based problems.
* Recursion implicitly uses a [stack](../topics/stack-queue.md), hence, the recursive algorithm can be rewritten iteratively using a stack. (Mind the stack overflow issue).

## Resources
- [X] [Khan Academy](https://www.khanacademy.org/computing/computer-science/algorithms/recursive-algorithms/a/recursion)
- [X] CLRS // Recurrences
- [ ] CTCI
- [ ] LC Learn 
    - [X] [Part 1](https://leetcode.com/explore/learn/card/recursion-i/) 
    - [ ] [Part 2](https://leetcode.com/explore/learn/card/recursion-ii/)
- [X] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/recursion/)
- [X] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-9)
- [X] [Tech-Interview-Cheat-Sheet](https://github.com/TSiege/Tech-Interview-Cheat-Sheet#recursive-algorithms)
- [X] ~~[Coding Interview University](https://github.com/jwasham/coding-interview-university#recursion)~~ // Old standford videos

