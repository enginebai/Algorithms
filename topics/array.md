# Array
## Sequences & Sets
### Interface (ADT)
*Sequences* maintain a collection of items in an *extrinsic* order. By extrinsic, it means the first item is "first" not because of what the item is (such as it is the largest number), but because the external position puts it there. Sequances are generalizations of `array`, `stack`, `queue` and `linked list`.

| Usages    | Operations        | Descriptions                                          |
|-----------|-------------------|-------------------------------------------------------|
| Container | `create(X)`       | Given an iterable `X`, create sequence from items in `X`. |
|           | `size()`          | The number of items.                                  |
| Static    | `iterator()`      | Return items one-by-one in sequence order.            |
|           | `get_at(i)`       | Return the i-th item.                                 |
|           | `set_at(i, x)`    | Replace the i-th item with `x`.                         |
| Dynamic   | `insert_at(i, x)` | Add item `x` as the i-th item.                          |
|           | `delete_at(i, x)` | Remove and return the i-th item.                      |
|           | `insert_first(x)`  | Add item `x` to the first item.                         |
|           | `delete_first()`  | Remove and return the first item.                     |
|           | `insert_last(x)`   | Add item `x` to the last item.                          |
|           | `delete_at(i, x)` | Remove and return the last item.                      |

By contrast, *Sets* maintain a collection of items in an *intrinsic* order based on a **unique key**, each item `x` has key `x.key`. Sets are generattion of `set` and `dictionary`.

| Usages    | Operations     | Descriptions                                         |
|-----------|----------------|------------------------------------------------------|
| Container | `create(X)`    | Given an iterable `X`, create set from items in `X`. |
|           | `size()`       | The number of items.                                 |
| Static    | `find(k)`      | Return items with key `k`.                           |
|           | `set_at(i, x)` | Replace the i-th item with `x`.                      |
| Dynamic   | `insert(x)`    | Add item `x` (Replace if `x.key` exists).            |
|           | `delete(k)`    | Remove and return the i-th item.                     |
| Order     | `iterator()`   | Return items one-by-one in key order.                |
|           | `find_min()`   | Return item with smallest key.                       |
|           | `find_max()`   | Return item with largest key.                        |

### Implementation
There are two main data structure approaches:
1. Array-based
2. Pointer-based

#### Array
Implementing a sequence using an array, which index `i` is the item `i` allows `get_at(i)` and `set_at(i, x)` to be `O(1)` (*random access*, it is great for static operations!). However, when deleting or inserting, we have to reallocate the array and shift all items (by creating a new array with updated size and coping the existing items to the new array), it take `O(n)` in the worst case.

| Data Structure | Container   | Static                     | Dynamic                             | Dynamic                            | Dynamic                          |
|----------------|-------------|----------------------------|-------------------------------------|------------------------------------|----------------------------------|
|                | `create(X)` | `get_at(i)` `set_at(i, x)` | `insert_at(i, x)` `delete_at(i, x)` | `insert_first(x)` `delete_first()` | `insert_last(x)` `delete_last()` |
| Array          | `O(n)`      | **`O(1)`**                 | `O(n)`                              | `O(n)`                             | `O(n)`                           |

#### Linked List
*Linked List* is a pointer-based data structure, each item has a *node* with two properties: `node.item` (the value) and `node.next` (the link to next node), and maintain pointers to the first node, called *head*.

Linked list takes `O(1)` for inserting or deleting first item simply by relinking the pointer. However, it takes `O(n)` for the getter/setter function since it finds the i-th element through items one-by-one.

| Data Structure | Container   | Static                     | Dynamic                             | Dynamic                            | Dynamic                          |
|----------------|-------------|----------------------------|-------------------------------------|------------------------------------|----------------------------------|
|                | `create(X)` | `get_at(i)` `set_at(i, x)` | `insert_at(i, x)` `delete_at(i, x)` | `insert_first(x)` `delete_first()` | `insert_last(x)` `delete_last()` |
| Linked List    | `O(n)`      | `O(n)`                     | `O(n)`                              | **`O(1)`**                         | `O(n)`                           |

> More detail, see [Linked List](../topics/linked-list.md) topic.

#### Dynamic Array
The `insert_last(x)` takes `O(n)` for every time, however, there is way to relax constraint size of array: *over-allocate*, we reallocate `Θ(n)` extrac space (0.5n or 2n) so that **reallocation does not occur with every dynamic operation**.

Suppose we allocate new array to double size (2n) when `insert_last(x)` as array is full, and we do `n` time of `insert_last(x)` from empty array, we have to resize 1, 2, 4, 8...etc items for each round of reallocation, it takes `Θ(1 + 2 + 4 + 8 + ... + n)`, which is `Θ(SUM(i = 1 to log n) {2^i})` = `Θ(n)`, linear time for `n` times operations, then that is constant time for each opeation on average.

> SUM(i = 1 to k) {2^i} = 2^(k+1) - 1

Allocating addition space can gurantee that `n` insertions only takes `O(n)`, so insertion will take `O(1)` time per insertion **on average**, that is called **amortized constant time**, the cost of the operation is amortized (distributed) across many operations.

| Data Structure | Container   | Static                     | Dynamic                             | Dynamic                            | Dynamic                          |
|----------------|-------------|----------------------------|-------------------------------------|------------------------------------|----------------------------------|
|                | `create(X)` | `get_at(i)` `set_at(i, x)` | `insert_at(i, x)` `delete_at(i, x)` | `insert_first(x)` `delete_first()` | `insert_last(x)` `delete_last()` |
| Dynamic Array  | `O(n)`      | **`O(1)`**                     | `O(n)`                              | `O(n)`                             | **`O(1)`**                       |

### Amortization
* Operation has **amortized cost** `T(n)` if `k` operations cost at most `k * T(n)`, that is *on average* over may operations.
* Inserting into a dynamic array take `O(1)` amortized time. (It might still take `O(n)` for some worst case)

## Problems & Solutions
| Problem         | Solution | Difficulty |
|------------------|----------|------------|
|[485. Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/)|[Straightforward](../leetcode/485.max-consecutive-ones.md)|Easy|
|[1295. Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits/)|[Straightforward](../leetcode/1295.find-numbers-with-even-number-of-digits.md)|Easy|
|[977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)|[Two Pointers](../leetcode/977.squares-of-a-sorted-array.md)|Easy|
|[1089. Duplicate Zeros](https://leetcode.com/problems/duplicate-zeros/)|[Two Pointers](../leetcode/1089.duplicate-zeros.md)|Easy|
|[27. Remove Element](https://leetcode.com/problems/remove-element/)|[Two Pointers](../leetcode/27.remove-element.md)|Easy|
|[88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)|[Two Pointers](../leetcode/88.merge-sorted-array.md)|Easy|
|[26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)|[Two Pointers](../leetcode/26.remove-duplicates-from-sorted-array.md)|Easy|
|[1346. Check If N and Its Double Exist](https://leetcode.com/problems/check-if-n-and-its-double-exist/)|[Hash Table](../leetcode/1346.check-if-n-and-its-double-exist.md)|Easy|
|[941. Valid Mountain Array](https://leetcode.com/problems/valid-mountain-array/)|[Two Pointers](../leetcode/941.valid-mountain-array.md)|Easy|
|[1299. Replace Elements with Greatest Element on Right Side](https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/)|[Straightforward](../leetcode/1299.replace-elements-with-greatest-element-on-right-side.md)|Easy|
|[283. Move Zeroes](https://leetcode.com/problems/move-zeroes/)|[Two Pointers](../leetcode/283.move-zeros.md)|Easy|
|[905. Sort Array By Parity](https://leetcode.com/problems/sort-array-by-parity/)|[Two Pointers](../leetcode/905.sort-array-by-parity.md)|Easy|
|[1051. Height Checker](https://leetcode.com/problems/height-checker/)|[Straightforward](../leetcode/1051.height-checker.md)|Easy|
|[448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)|[In-place Negative Seen](../leetcode/448.find-all-numbers-disappeared-in-an-array.md)|Easy|
|[704. Binary Search](https://leetcode.com/problems/binary-search/)||Easy|

### Problems to Finish
* https://github.com/youngyangyang04/leetcode-master
* https://www.techinterviewhandbook.org/algorithms/array#recommended-leetcode-questions
* https://turingplanet.org/2020/09/18/leetcode_planning_list/#Array
* https://leetcode.com/explore/learn/card/array-and-string/
* CTCI

### Tips for Problem Solving
* Mind the bound or size of array. Think about empty array or with few element (1, 2, or less than the problem requirement).
* Think about the case that the array with the duplicate or repeated element.
* Is the array sorted (partially)? Yes, try *binary search*. No, try to sort first?
* *Two pointers* techniques.
* Swap or ignore element (only take the element met the requirement)
* We can iterate array from left to right, also from *right to left*.
* `O(n)` time complexity **doesn't** mean you can only iterate the array **once**. Iterate the array several times might help solve the problem, for example, pre-computation (iterate array at least one time first) using hashing might be useful.
* For in-place operation or `O(1)` space complexity, use array itself as a hash table. For example, the value of array ranges from 1 to `n`, where `n` is the size of array, then we can use the *index* to represent.

## Resources
- [X] [MIT 6.006 Introduction to Algorithm - Lecture 2: Data Structures and Dynamic Arrays](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-spring-2020/lecture-videos/lecture-2-data-structures-and-dynamic-arrays/)
- [ ] Kotlin document
    - [X] [Array](https://kotlinlang.org/docs/basic-types.html#arrays)
    - [ ] [Collections](https://kotlinlang.org/docs/collections-overview.html)
- [X] [LC Learn - Array 101](https://leetcode.com/explore/learn/card/fun-with-arrays/) // Note + coding questions
- [ ] [LC Learn - Array and String](https://leetcode.com/explore/learn/card/array-and-string/) // Note + coding questions
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Array)
- [ ] [LC Top Interview Questions](https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/) // Coding questions collection with easy/medium/hard levels
> Write some note or summary + do problems below
- [X] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/array) // Note + coding questions
- [ ] CTCI // Simple notes + some problems
- [ ] https://github.com/youngyangyang04/leetcode-master // Note + problems with nice illustration.
- [X] [Tech Interview Cheat Sheet](https://github.com/TSiege/Tech-Interview-Cheat-Sheet#array) // Simple note summary
- [X] [Software Engineering Interview Preparation](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#arrays) // Simple note summary
- [ ] ~~Fundamental of Data Structure - Arrays~~  // Polynomial + Sparse matrices.
- ~~[ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#arrays)~~ // Old videos + Vector