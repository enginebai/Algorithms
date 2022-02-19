# Array
## Sequences & Sets
### Interface
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


## Resources
- [ ] [MIT 6.006 Introduction to Algorithm - Lecture 2: Data Structures and Dynamic Arrays](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-spring-2020/lecture-videos/lecture-2-data-structures-and-dynamic-arrays/)
- [ ] Fundamental of Data Structure - Arrays
- [ ] CTCI
- [ ] https://github.com/youngyangyang04/leetcode-master // Nice note + illustration
- [ ] [Google Tech Dev Guide - Map/Dictionary](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#linear) // Note + coding questions (8)
- [ ] [LC Learn - Array 101](https://leetcode.com/explore/learn/card/fun-with-arrays/) // Note + coding questions
- [ ] [LC Learn - Array and String](https://leetcode.com/explore/learn/card/array-and-string/) // Note + coding questions
- [ ] [LC Top Interview Questions](https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/) // Coding questions collection with easy/medium/hard levels
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Array)
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/array) // Note + coding questions
- [ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#arrays) // (Old videos + **curated note**) (1)
- [ ] [Tech Interview Cheat Sheet](https://github.com/TSiege/Tech-Interview-Cheat-Sheet#array) // Note
- [ ] [Software Engineering Interview Preparation](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#arrays) // Simple note
- [ ] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/basic-data-structure // Simple DS Array note