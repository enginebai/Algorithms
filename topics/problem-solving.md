# Problem Solving

## Overview
* Array
* Two Pointers
* Sliding Windows
* Sorting
* Binary Search
* Hash Table / Prefix Sum
* Heap
* (Monotonic) Stack / Queue
* Dynamic Programming
* Greedy
* Specific Data Structures
    * Linked List
    * Tree
    * Graph
* Interval
* Divide and Conquer
* Backtracking

## Array
* **Sorted**? 
    * Yes:
        * [Binary Search](#binary-search)
        * [Two Pointers](#two-pointers)
        * [Greedy](#greedy)
    * No, sort first? or use [hash table](#hash-table)?
* Subarray / substring?
    * [Sliding Windows](#sliding-windows)
    * [Dynamic Programming](#dynamic-programming)
    * [Prefix Sum](#hash-table)
* Palindromic substring / subsequence?
    * [Dynamic Programming]()

## Two Pointers
### Characteristics
* A problem can be solved by two pointers when it reduces the total cases we need to consider. ([Source](https://leetcode.com/problems/subarray-sum-equals-k/solutions/301242/general-summary-of-what-kind-of-problem-can-cannot-solved-by-two-pointers/)), and also see [Sliding Windows](#sliding-windows).
* (Sorted) Sequential data, window or subarray
* Partitioning: `[even | odd]`, `[negative | positive]`...etc.
* Intersection or merge

### Approaches
* Left / right pointers
```js
[X, X, X, X, X, X]  
 L ->        <- R   // L at the beginning, R at the end
 L --- R            // Range: [L, R]
```
* Read / write pointers: Read every element and write when condition is met. (only take the element met the requirement)
* Fast / slow pointers: Cycle detection

## Sliding Windows
### Characteristics
* Subarray / Substring
* Window: valid value range, sliding window is applicable when meeting the following conditions:
    * If wider window is valid, then narrow window is also valid.
    * If narrow window is invalid, then wider window is also invalid.
### Approaches
```kotlin
fun problemSolving(str: String) {
    var left = 0
    var right = 0
    while (right < str.length) {
        val character = str[right]

        // Check if we need to shrink the window
        while (window needs shrink) {
            val d = s[start]
            // Shrink window
            start++
        }

        // Update some information of windows or result here
        if (window meets condition) {
            // Update result
        }

        // Expand the window
        end++
    }
}
```

## Binary Search
### Characteristics
* **Monotonicity**: The elements have some order or trend, such as sorted or `[X, X, X, O, O, O, O, O]` or choose larger the result become smaller and vice versa. 
* **Decision-making** or comparison or whether meet some condition in the **bounded search space**, then we can keep **reducing search space**.
* **Target or Feasibility**: Search for a specific value, peak or extremum.

> The answer is unique, and there's always another variable that changes monotonically according to the change of the answer, and we can depend on this variable to decide on which side of the search we go next step. Without the monotonicity and uniqueness, binary search is not applicable.

### Approaches
* Search on index
* Search on value: define the search space.

## Hash Table
### Characteristics
* Mapping / `O(1)` lookup
* Counting / Frequency
* Seen / Duplicates / Missing
* Grouping / Anagrams / Intersection / Union
* Subarray (Prefix) Sum

### Approaches
* Hash Map / Set / Fixed-size array: input has fixed range of value, such as lowercase letters (`IntArray(26)`), number ranges `1..n`, etc.
* Cyclic sort or use array itself as hash table and index as key.
* [Two Sum](../leetcode/1.two-sum.md): Iterate the array, check its **complement** `target - current state` exists and update the result, and store current state to hash table as you've seen.

## Sorting
### Characteristics
 (Pairwise) Comparisons: Single list or compare between two lists.
* Detect duplicates / Grouping similar elements.
* Choose greedily.

## Stack
### Characteristics
* Last in first out: Last element should be processed first.
* Nested structure / Parentheses / Balance / Parsing
* Undo / Redo operations
* Recursion / DFS / Backtracking
* Find next greater / smaller element

### Approaches
* Nested structure

* Monotonic stack template:
```kotlin
```

## Queue
### Characteristics
### Approaches

## Interval
## Heap
### Characteristics
### Approaches

## Dynamic Programming
## Greedy
## Linked List
## Tree
* Traversal
    * Pre-order
    * In-order
    * Post-order
    * Level-order
* Path / Sum
* Distance
* Insertion
* Deletion
* Lowest Common Ancestor
* Binary Search Tree

## Graph
* DFS
* BFS
* Topological Sort

## Resources
* https://docs.google.com/document/d/1RmVqlv0wPySoVrP3f5QIm_PVHmygsHd6hVO39FfaARM/edit#heading=h.vnjo3erxh3qi
* https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/
* https://leetcodethehardway.com/
* https://labuladong.github.io/algo/home/
* https://programmercarl.com/

