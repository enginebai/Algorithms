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

| Data Structure | Container   | Static                     | Dynamic                             | Dynamic                            | Dynamic                            |
|----------------|-------------|----------------------------|-------------------------------------|------------------------------------|------------------------------------|
|                | `create(X)` | `get_at(i)` `set_at(i, x)` | `insert_at(i, x)` `delete_at(i, x)` | `insert_first(x)` `delete_first()` | `insert_first(x)` `delete_first()` |
| Array          | `O(n)`      | **`O(1)`**                     | `O(n)`                              | `O(n)`                             | `O(n)`                             |

#### Linked List
*Linked List* is a pointer-based data structure, each item has a *node* with two properties: `node.item` (the value) and `node.next` (the link to next node), and maintain pointers to the first node, called *head*.

Linked list takes `O(1)` for inserting or deleting first item simply by relinking the pointer. However, it takes `O(n)` for the getter/setter function since it finds the i-th element through items one-by-one.

| Data Structure | Container   | Static                     | Dynamic                             | Dynamic                            | Dynamic                            |
|----------------|-------------|----------------------------|-------------------------------------|------------------------------------|------------------------------------|
|                | `create(X)` | `get_at(i)` `set_at(i, x)` | `insert_at(i, x)` `delete_at(i, x)` | `insert_first(x)` `delete_first()` | `insert_first(x)` `delete_first()` |
| Linked List    | `O(n)`      | `O(n)`                     | `O(n)`                              | **`O(1)`**                         | `O(n)`                             |

## Dynamic Array
> // TODO: note

## Resources
- [X] [MIT 6.006 Introduction to Algorithm - Lecture 2: Data Structures and Dynamic Arrays](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-spring-2020/lecture-videos/lecture-2-data-structures-and-dynamic-arrays/)
- [ ] [LC Learn - Array 101](https://leetcode.com/explore/learn/card/fun-with-arrays/) // Note + coding questions
- [ ] [LC Learn - Array and String](https://leetcode.com/explore/learn/card/array-and-string/) // Note + coding questions
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Array)
- [ ] [LC Top Interview Questions](https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/) // Coding questions collection with easy/medium/hard levels
> Write some note or summary + do problems below
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/array) // Note + coding questions
- [ ] [Kotlin - Array](https://kotlinlang.org/docs/basic-types.html#arrays) + [Primitive type arrays](https://kotlinlang.org/docs/basic-types.html#primitive-type-arrays)
- [ ] CTCI // Simple notes + some problems
- [ ] https://github.com/youngyangyang04/leetcode-master // Note + problems with nice illustration.
- [ ] [Tech Interview Cheat Sheet](https://github.com/TSiege/Tech-Interview-Cheat-Sheet#array) // Simple note summary
- [ ] [Software Engineering Interview Preparation](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#arrays) // Simple note summary
- [ ] ~~Fundamental of Data Structure - Arrays~~  // Polynomial + Sparse matrices.
- ~~[ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#arrays)~~ // Old videos + Vector