## [Problem]()

### Solution Names
Description

```kotlin
// TODO: Insert your code
```

* **Time Complexity**: `O(n)`.
* **Space Complexity**: `O(n)`.

### Two Sum in Sorted Array
Two pointers approach to check if two elements that can sum up to `k`.

```kotlin
fun twoSum(A: IntArray, k: Int): Boolean {
    var left = 0
    var right = A.size - 1
    while (left < right) {
        val sum = A[left] + A[right]
        if (sum == k) return true
        else if (sum < k) left++
        else right--
    }
    return false
}
```

### Reverse Array in Specific Range
Avoid iterating `i` from `start` to `end`, it's error-prone.

```kotlin
fun reverseArray(nums: IntArray, start: Int, end: Int) {
    var left = start
    var right = end
    while (left < right) {
        nums.swap(left, right)
        left++
        right--
    }
}
```

### Find the largest Two Numbers
```kotlin
fun findLargestTwoNumbers(nums: IntArray) {
    var first = Int.MIN_VALUE
    var second = Int.MIN_VALUE
    for (num in nums) {
        if (num > first) {
            second = first
            first = num
        } else if (num > second) {
            second = num
        }
    }
}


```