# Problem Solving

## Overview
* Array
* Two Pointers
* Sliding Windows
* Sorting
* Binary Search
* Hash Table / Prefix Sum
* Interval
* Heap
* (Monotonic) Stack / Queue
* Dynamic Programming
* Greedy
* Specific Data Structures
    * Linked List
    * Tree
    * Graph
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
    * [Expand Around Center]()

### Two Pointers
#### Characteristics
* (Sorted) Sequential data, window or subarray
* Partitioning: `[even | odd]`, `[negative | positive]`...etc.
* Intersection or merge

#### Approaches
* Left / right pointers
```js
[X, X, X, X, X, X]  
 L ->        <- R   // L at the beginning, R at the end
 L --- R            // Range: [L, R]
```
* Read / write pointers: Read every element and write when condition is met. (only take the element met the requirement)
* Fast / slow pointers: Cycle detection

### Sliding Windows
#### Characteristics
* Subarray / substring
* Window: valid value range

#### Patterns


## Sorting
## Binary Search
* Search on index
* Search on value

## Hash Table
## Interval
## Heap
## (Monotonic) Stack / Queue
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

