# Binary Search

## Normal
The binary search is applicable when the collection is **sorted**, so try to sort the collection if it's not sorted yet.

```kotlin
fun binarySearch(A: IntArray, target: Int): Int {
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

When to update the result or return the result? It depends on the problem, but usually we update the result when we found the target, and return the result when while loop breaks. Or we are 100% sure that the middle is the target, then we can return it immediately.

```kotlin
var result = -1

while (left <= right) {
    val middle = ...
    // 100% sure that middle is the target
    if (A[middle] == target) result = middle

    ...
}

// Or we update the result when we found the target, and shrink the searching range
while (left <= right) {
    val middle = ...

    if (A[middle] <= target) {
        result = middle
        left = middle + 1
    } else {
        right = middle
    }
}

return result
```

## Find the first/left-most (last/right-most) element that satifies the condition
```js
X X X O O O O O
      ^ // The first element that satifies the condition

O O O O O O X X X
          ^ // The last element that satifies the condition
```

For example, to find the smallest (largest) number which is greater (smaller) or equal to the target: For the two problems (might contain duplicates), we can use the same conditions when `target != middle` (`<`, `>`) as normal binary search, the different part is when `target == middle`:

* To find the smallest number >= `target`, we should keep searching the left half part, and return `left` pointer when while loop breaks.
```js
          (X, _, _, _, ...)
target <=    
           L          R        
```

* To find the largest number <= `target`, we should search the right half part, and return `right` pointer when while loop breaks.
```js
(..., _, _, X)          
               <= target
 L          R 
```

> Sample problem: [35. Search Insert Position](../leetcode/35.search-insert-position.md)

## Find the First/Last Position of Element in Sorted Array
Given sorted array (might contain duplicates), we can use the same approach mentioned above to find the first/last position of element in sorted array. We apply the same conditions when `target != middle` (`<`, `>`) as normal binary search, the different part is when `target == middle`:

* To find the first position, we should keep searching the left part because we don't know if current `middle` is the first element, and return `left` pointer when while loop breaks.
```js
(X, _, _, _, ...)
 L        M    R
```

* To find the last position, we should keep searching the right part because we don't know if current `middle` is the last element, and return `right` pointer when while loop breaks.
```js
(..., _, _, X)
 L    M     R
```

> Sample problem: [34. Find First and Last Position of Element in Sorted Array](../leetcode/34.find-first-and-last-position-of-element-in-sorted-array.md)


## Variants
> TODO: Study [Binary Search 101](https://leetcode.com/problems/binary-search/solutions/423162/Binary-Search-101-The-Ultimate-Binary-Search-Handbook/)

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
    - https://www.zhihu.com/question/36132386

- [Nice Visualization](https://vladisov.github.io/binary-search-visualisation/)