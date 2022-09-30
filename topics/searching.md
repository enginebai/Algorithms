# Searching
There are some different ways to search: binary search, [binary search tree](../topics/tree.md#binary-search-tree), and [hash table](../topics/hash-table.md). In this topic, we will talk about *binary search*.

## Binary Search
The binary search is applicable when the collection is **sorted**, so try to sort the collection if it's not sorted yet.

```kotlin
fun binarySearch(A: IntArray, target: Int): Int {
    if (A.isEmpty) return -1
    var left = 0
    var right = A.size - 1
    while (left <= right) {
        val mid = left + (right - left) / 2
        if (A[mid] == target) return mid
        else if (A[mid] > target) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    return -1
}
```

* **Time Complexity**: It takes `O(lg n)` time, it keeps searching the half size of subarray, that is, keeps divide `n` by 2 = `O(lg n)`.

## Variants
```kotlin
fun binarySearch(A: IntArray, target: Int): Int {
    var left = 0
    var right = A.size - 1
    while (left < right) {
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
* Always use `while (left < right)`, so when while loop breaks, we will have `left == right`, and then check if the target exists or not. (might need further check, such as `if (A[left] == target)`).

## Resources
- CTCI
- [Khan Academy](https://www.khanacademy.org/computing/computer-science/algorithms/binary-search/a/binary-search)
- [LC Learn - Binary Search](https://leetcode.com/explore/learn/card/binary-search/) // A very interesting and slightly different ways to implement different binary search algorithm.
- [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Binary_Search)