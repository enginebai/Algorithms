# Binary Search
There are some different ways to search: binary search, [binary search tree](../topics/tree.md#binary-search-tree), and [hash table](../topics/hash-table.md). In this topic, we will talk about *binary search*.

## Normal
The binary search is applicable when the collection is **sorted**, so try to sort the collection if it's not sorted yet.

```kotlin
fun binarySearch(A: IntArray, target: Int): Int {
    if (A.isEmpty) return -1
    var left = 0
    var right = A.size - 1
    while (left <= right) {
        val mid = left + (right - left) / 2
        if (A[mid] == target) return mid
        if (A[middle] < target) {
            left = middle + 1
        } else {
            right = middle
        }
    }
    return -1
}
```

* **Time Complexity**: It takes `O(lg n)` time, it keeps searching the half size of subarray, that is, keeps divide `n` by 2 = `O(lg n)`.

> Sample problem: [704. Binary Search](../leetcode/704.binary-search.md)

## Variants
```kotlin
fun binarySearch(A: IntArray, target: Int): Int {
    var left = 0
    var right = A.size - 1
    while (left < right) {
        // left middle
        val middle = left + (right - left) / 2
        if (A[middle] < target) {
            left = middle + 1
        } else {
            right = middle
        }
    }
    return if (A[left] == target) left else -1
}
```

> From [Binary Search 101](https://leetcode.com/problems/binary-search/solutions/423162/Binary-Search-101-The-Ultimate-Binary-Search-Handbook/)

Some key points:

* How to shrink the searching range by moving `left` and `right`? 
```js
if (100% sure logic) {
    left = middle + 1
} else {
    right = middle
}

// or
if (100% sure logic) {
    right = middle - 1
} else {
    left = middle
}
```

We try to keep the logic as simple as possible, that is only one statement `if...else`

* Always use `while (left < right)`, so when while loop breaks, we will have `left == right`, which means there's only one element and it should be our anser, and then check if it is ehe target. (might need further check, such as `if (A[left] == target)`).
* Image the example: **only 2 element**, and what your program will run, let's take a look at the example:

Suppose we have the code, then it will lead to an infinite loop.
```kotlin
// Incorrect, infinite loop
val middle = left + (right - left) / 2
if (target < A[middle]) {
    right = middle - 1
} else {
    left = middle
}
```

```js
5, 6
L  R
M
```

When target is `5`, our code will fall into the `else` statement which `left` does not move and cause infinite loop, in this case we should move `right` to `middle`. So let's correct our code so that `right` will move to `middle`:

```kotlin
// Correct implementation
val middle = left + (right - left) / 2
if (A[middle] < target) {
    left = middle + 1
} else {
    right = middle
}

```

Another example, when target is `6`, we should move `left` to `middle + 1`, which is `6` and `left == right`, that exit the while loop.

**Idea!!** When you got stuck, think about the case of only 2 elements left, will your implementation be correct? (Shrink the boundary, exit the while loop)

## Resources
- CTCI
- [Khan Academy](https://www.khanacademy.org/computing/computer-science/algorithms/binary-search/a/binary-search)
- [LC Learn - Binary Search](https://leetcode.com/explore/learn/card/binary-search/) // A very interesting and slightly different ways to implement different binary search algorithm.
- [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Binary_Search)
- Recommended Posts:
    - [二分搜尋法（Binary Search）完整教學](https://medium.com/appworks-school/binary-search-%E9%82%A3%E4%BA%9B%E8%97%8F%E5%9C%A8%E7%B4%B0%E7%AF%80%E8%A3%A1%E7%9A%84%E9%AD%94%E9%AC%BC-%E4%B8%80-%E5%9F%BA%E7%A4%8E%E4%BB%8B%E7%B4%B9-dd2cd804aee1)
    - [Binary Search 101](https://leetcode.com/problems/binary-search/solutions/423162/Binary-Search-101-The-Ultimate-Binary-Search-Handbook/) + [Part 2](https://leetcode.com/problems/search-insert-position/solutions/423166/binary-search-101/)
    - [Powerful Ultimate Binary Search Template. Solved many problems](https://leetcode.com/discuss/general-discussion/786126/python-powerful-ultimate-binary-search-template-solved-many-problems)
- [Nice Visualization](https://vladisov.github.io/binary-search-visualisation/)