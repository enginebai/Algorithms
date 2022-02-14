# Complexity

## Efficiency
How could we measure how fast an algorithm runs? The *efficiency* means not only 

1) how fast the algorithm does run, but also 
2) how fast does it compare to others. 

It depends on the algorithm itself (implementation) and the machine, but we want to measure without knowing how fast the machine is, that is, want to measure the performance to be **machine independent**. 

We don't want to count how long it runs on a real machine, we don't measure the time, instead, we want to abstract the time, count the fundamental operations, expect performance to depend on **the size of our input**.

The *running time* of an algorithm on a particular inputs is **the number of statements/operations/steps executed** which is to define the notation of step so that it's machine-independent.

## Runtime Analysis
For *insertion sort*, the code is below: 
```kotlin
fun insertSort(A) {
    for (j = 2 to A.size()) {           // For loop of unsorted array
        var key = A[j]                  // Current element to sort
        var i = j - 1                   // Previous element to compare
        while (i > 0 && A[i] > key) {   // Compare key with sorted array
            A[i + 1] = A[i]             // Shift elements if greater than key
            i = i - 1                   
        }
        A[i + 1] = key                  // Insert the key to right place where the previous is less than the key
    }
}
```

We start the running time analysis by presenting the time of seach tatements:
```kotlin
fun insertSort(A) {                     // ---- Times ----
    for (j = 2 to A.size()) {           // n, without -1 means the iteration that tests to break for-loop
        var key = A[j]                  // n - 1
        var i = j - 1                   // n - 1
        while (i > 0 && A[i] > key) {   // SUM(j = 2 to n) {j}, without -1 means the iteration that tests to break while-loop
            A[i + 1] = A[i]             // SUM(j = 2 to n) {j - 1}
            i = i - 1                   // SUM(j = 2 to n) {j - 1}
        }
        A[i + 1] = key                  // n - 1
    }
}
```

In insertion sort, the best case occurs if the array is already sorted, and its running time will be:

```kotlin
fun insertSort(A) {                     // ---- Times ----
    for (j = 2 to A.size()) {           // n
        var key = A[j]                  // n - 1
        var i = j - 1                   // n - 1
        while (i > 0 && A[i] > key) {   // n - 1, 
            A[i + 1] = A[i]             // 0, not running
            i = i - 1                   // 0, not running
        }
        A[i + 1] = key                  // n - 1
    }
}
```

It is **linear function** of `n` for the best case. What about the case that the array is in reverse sorted order - the worst case results, means that we must compare each keys `A[j]` with each element in the entire sorted sbuarray `A[1..j-1]`, so the running time will be:

```kotlin
fun insertSort(A) {                     // ---- Times ----
    for (j = 2 to A.size()) {           // n
        var key = A[j]                  // n - 1
        var i = j - 1                   // n - 1
        while (i > 0 && A[i] > key) {   // SUM(j = 2 to n) {j} = n(n + 1) / 2 - 1, 
            A[i + 1] = A[i]             // SUM(j = 2 to n) {j - 1} = n(n - 1) / 2
            i = i - 1                   // SUM(j = 2 to n) {j - 1} = n(n - 1) / 2
        }
        A[i + 1] = key                  // n - 1
    }
}
```

This worst running time can expressed as `a*n^2 + b*n + c`, it's a **quadratic function** of `n`.

We shall usually conccentrate on the worst-case running time, because of the three reasons:
1. It's the upper bound of running time for any input, it gives us a guarantee that the algorithm will never tak any longer.
2. For some algorithm, the worst case occurs fairly often.
3. The "average case" is often roughly as bad as the worst case.

We expect that the same algorithm running on a fast machine will run faster than the same algorithm on a slow one, however, we'd like to be able to compare without worrying about how fast the machine is, so we compare the running time based on *asymptotic performance* (the abstraction way) relative to the input size.

## Asymptotic Notation
When look at input sizes large engough to make only the order of growth of the running time relevant, we use *asymptotic notation* to express the **rate of growth** of an algorithm's running time in terms of the input size `n`.

For the worst-case running time of *insert sort* is `a*n^2 + b*n + c`, what we care about is the **order of growth**, we therefore consider:

1. Keep only the leading terms (`a*n^2`) and drop *lower-order terms* (`b*n + c`), since the lower-order terms are relatively insignificant for large `n`.
2. Ignore the *constant coefficient* in the leading term, i.e. `a`.

Thus, we write that *insertion sort* has a worst-case running time of `Θ(n^2)`.

| O Notation                                                                   | Ω Notation                                                                    | Θ Notation                                           |
|------------------------------------------------------------------------------|-------------------------------------------------------------------------------|------------------------------------------------------|
| **Upper**  bound, an algorithm takes  **at most**  a certain amount of time. | **Lower**  bound, an algorithm takes  **at least**  a certain amount of time. | Both, tight bound                                    |
| <img src='../media/o-notation.png' />                             | <img src='../media/omega-notation.png' />                          | <img src='../media/theta-notation.png'/> |
> Source: [Khan Academy - Algorithms](https://www.khanacademy.org/computing/computer-science/algorithms#asymptotic-notation)

The following table lists the common runtime from the fastest to slowest ones.

| constant | logarithmic | linear | log-linear | quadratic | polynomial | exponential | factorial |
|----------|-------------|--------|------------|-----------|------------|-------------|-----------|
| Θ(1)     | Θ(log n)    | Θ(n)   | Θ(n log n) | Θ(n^2)     | Θ(n^c)     | 2^Θ(n^c)    | Θ(n!)     |

<img src='../media/complexity.png' alt='Complexity'/>

> Source: https://www.bigocheatsheet.com/

## Runtime Analysis Techniques

### Summation Rules
Support an algorithm consists of two parts, one part takes `O(n^2)`, another take `O(n^3)`, we can simply "add" these two parts to be the running time of the entire algorithm. 

For example, an function that make `A` an n x n identity matrix (All the values are 1 in the diagonal position):

```kotlin
fun generateIdentityMatrix(n: Int) {    // Time
    for (i = 0; i < n; i++) {           // n + 1
        for (j = 0; j < n, j++) {       // n
            A[i][j] = 0                 // n * n = O(n^2)
        }
    }
    for (i = 0; i < n; i++) {           // n + 1 = O(n)
        A[i][i] = 1                     // n
    }
}
```
The running time of `generateIdentityMatrix()` is `O(n)` + `O(n^2)` by summation rule.

### For Simple Statement
There are several kinds of statements can run in `O(1)` time, that is independent of the input size.
* Arithmetic: `+` or `%`.
* Logical: `&&` or `||`.
* Comparison: `<=`.
* Structure accessing: `A[i]`
* Assigment: `A[j + 1] = A[i]`
* Function calls not evaluating arguments: `print(...)` or `logger.debug(...)`
* Jump: `break` or `return`.

### For For-Loops Statement
For example, the for-loop `for (i = 0; i < n - 1; i++)`, it iterates *((n - 1) - 0) / 1 = n - 1* times (that is *(end - start) / step* formular).

To bound the running time of for-loop, we must obtain an upper bound on **the number of loop iteration** times **the time to perform per iteration**, we can multiple the Big-O for the body by the Big-O of the loop, that is,

```kotlin
for (i = 0; i < n; i++) {           // O(n) 
    A[i][i] = 1                     // O(1)
}
```

The running time will be `O(1) * O(n) = O(n)`.

For some loops don't have an explicit count of number around the loop, we still can apply this rule:

```kotlin
fun linearSearch(A, value): Int {
    // ...
    var i = 0
    while (value != A[i]) { // Run n times at most, O(n)
        i++                 // O(1)
        // ...
    }
    // ...
}
```
The running time of function `linearSearch()` is `O(n) * O(1) = O(n)` .

### For If-Else Conditional Statement
```kotlin
fun doSomething() {
    if (...) {
        here()      // Takes f(n) 
    } else {
        there()     // Takes g(n)
    }
}
```

The running time of `doSomething()` is very straightforward, that is `O(max(f(n), g(n)))`.

### Summary
| For Loop                                        | While Loop                                        | Selection                                        | Block                                        |
|-------------------------------------------------|---------------------------------------------------|--------------------------------------------------|----------------------------------------------|
| <img src='../media/for-loop-running-time.png'/> | <img src='../media/while-loop-running-time.png'/> | <img src='../media/selection-running-time.png'/> | <img src='../media/block-running-time.png'/> |
> Source: http://infolab.stanford.edu/~ullman/focs/ch03.pdf

> // TODO: We will revisit this topic for "recursion" concept.

## Resources
- [X] [MIT 6.006 Introduction to Algorithm - Lecture 1: Algorithms and Computation](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-spring-2020/lecture-videos/lecture-1-algorithms-and-computation/)
- [X] CLRS
    - [X] Ch 2. Getting Started
    - [X] Ch 3. Growth of Functions
- [X] [Stadford Foundations of Computer Science - The Running Time of Programs](http://infolab.stanford.edu/~ullman/focs/ch03.pdf)
- [ ] CTCI
- [X] Fundamental of Data Structure
- [X] [Google Tech Dev Guide - Runtime Analysis](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-7) // Curated resources & links
- [X] [Khan Academy - Asymptotic Notation](https://www.khanacademy.org/computing/computer-science/algorithms/asymptotic-notation/a/asymptotic-notation)
- [ ] ~~[Coursera: Algorithm, Princeton](https://www.coursera.org/learn/algorithms-part1/lecture/xaxyP/analysis-of-algorithms-introduction)~~
- [ ] [Complexity：Asymptotic Notation(漸進符號)](http://alrightchiu.github.io/SecondRound/complexityasymptotic-notationjian-jin-fu-hao.html) // Nice introductory post
- [ ] [Coding Interview University - Complexity](https://github.com/jwasham/coding-interview-university#algorithmic-complexity--big-o--asymptotic-analysis) // Curated resources & links, however, they might be skipped after all since it's out-of-date.
- [ ] [Software Engineering Interview Preparation - Complexity](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/complexity.md) // Like cheat sheet
- [ ] [Tech Interview Cheat Sheet - Asymptotic Analysis](https://github.com/TSiege/Tech-Interview-Cheat-Sheet#asymptotic-notation) // Cheat sheet

