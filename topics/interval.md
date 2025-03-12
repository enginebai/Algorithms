# Interval
An interval is typically represented as `[start, end]` (inclusive), which `start <= end`. Intervals can be overlapped `[1, 5], [2, 8]`, disjoint `[1, 3], [5, 8]` or nested `[1, 9], [2, 5]`. The common operations on intervals are **updating, merging, and efficient lookups**.

> We always use inclusive interval in this note.

There are some key techniques to solve interval problems:
1. Sorting: Most interval problems require sorting the intervals by the `start`. Sorting allows for efficient *iterating* or *line sweep* techniques. It's also useful when comparing two sets of intervals (e.g., "Find common free time")
2. Merging: See *Overlapping* section below and [56. Merge Intervals](../leetcode/56.merge-intervals.md).
3. Line sweep: See *Line Sweep* section below. It's great for problem like "find the maximum number of overlapping intervals".
4. Binary search: If intervals are sorted, binary search can be used to find:
    * The first interval that starts **after** a given point.
    ```js
    X X X X O O O O O O
            ^
            |-----|
    ```
    * The last interval that ends **before** a given point.
    ```js
    ... O O O O X X X
              ^
          |---|
    ```

## Overlapping
To determine if two intervals are overlapped, we need to check if there is any intersection between the two intervals. That is, there is at least one value in the intersection of the two intervals.

```kotlin
// |--------|
//     |--------|
//     s    e
fun intersection(a: IntArray, b: IntArray) {
    val start = maxOf(a[0], b[0])
    val end = minOf(a[1], b[1])
    return start to end
}
```

There are several ways to check if two intervals are overlapped. The most simple way is to check if intersection is not empty:
```kotlin
// We check if there is any intersection between the two intervals.
fun isOverlapped(a: IntArray, b: IntArray): Boolean {
    return maxOf(a[0], b[0]) <= minOf(a[1], b[1])
}
```

* `maxOf(a[0], b[0])`: The later start point of the two intervals. This is the earliest point where the two intervals could potentially overlap.
* `minOf(a[1], b[1])`: The earlier end point of the two intervals. This is the latest point where the two intervals could potentially overlap.

If there is a common point(s) `X` in the valid range of `maxOf(a[0], b[0])..minOf(a[1], b[1])` between the two intervals, the two intervals are overlapped.

### Examples
* Overlapping `[1, 3]` and `[2, 5]`:
    * `max(1, 2) = 2`
    * `min(3, 5) = 3`

Since `2 <= 3`, there are common points in `[2, 3]`, the two intervals are overlapped.

* Not overlapping `[1, 3]` and `[5, 8]`:
    * `max(1, 5) = 5`
    * `min(3, 8) = 3`

Since `5 > 3`, there is no common point in `[5, 3]`, the two intervals are not overlapped.

----
Or equivalently, we list all possible cases where the two intervals are overlapped. The two intervals are overlapped if they are partially or fully overlapped.
```js
// Partially overlapped or touching.
Case 1:
|----|       a    |---|
   |------|  b        |------|


Case 2:
   |------|           |------|
|----|           |----|

// The two intervals are fully overlapped.
Case 3:
|---------|
   |---|

Case 4:
   |---|
|---------|
```

```kotlin
fun isOverlapped(a: IntArray, b: IntArray): Boolean {
    // Above: a
    // Below: b
            // Case 1
            // |---|
            //   |------|
    return b[0] in a[0]..a[1] ||

        // Case 2
        //    |----|
        // |----|
        a[0] in b[0]..b[1] ||

        // Case 3
        // |----------|
        //   |--|
        a[0] <= b[0] && b[1] <= a[1] ||

        // Case 4
        //   |--|
        // |----------|
        b[0] <= a[0] && a[1] <= b[1]
}
```

Or we can check if the two intervals are not overlapped:
```js
|------|      |------|       
       ^      ^
     a[1] < b[0]  
     b[1] < a[0]
```
```kotlin
private fun isOverlapped(a: IntArray, b: IntArray): Boolean {
    // Not (Not overlapped) = Overlapped
    return (
        a[1] < b[0] ||
        b[1] < a[0]
    ).not()
}
```

### Pitfalls
There is a pitfall to check if two intervals are overlapped, we can't just check if `a[0] < b[1]` or `b[0] < a[1]`.

```js
// a[0] < b[1]
     |-------|  a
|-------|       b

// b[0] < a[1]
|-------|       a
     |-------|  b
```

It failed in the following cases:
```js
// a[0] < b[1], but they are not overlapped.
|-------|               a
          |-------|     b

// b[0] < a[1], but they are not overlapped.
          |-------|     a
|-------|               b
```

## Line Sweep
Think of a v**ertical line sweeping** across the number line from left to right, each event (`start` or `end` of intervals) is treated as a **point**. We process the points in order, and keep track of the number of overlapping intervals at each point.
```js
1, 2, 3, 4, 5, 6, 7, 8, 9, ...
|--|     |-----|     |------|
   |--------|     |-----|
*---> sweep line
```

### Difference Array
The difference array is a useful technique in line sweep problems. It's a way to represent the original array by the difference between adjacent elements.

Given a array `a1 = [1, 3, 3, 5, 8]`, we create a *difference array* (`A[i] - A[i - 1]`): `d1 = [1, 2, 0, 2, 3]`, we can find the original array by summing up the difference array: `[1, 3, 3, 5, 8]`.

> The prefix sum of difference array is the original array!

If we add `10` to the index range 1 ~ 3, then the array becomes `a2 = [1, 13, 13, 15, 8]`, the updated difference array is `d2 = [1, 12, 0, 2, -7]`. 

We compare the two difference arrays, only two values changes:
```js
i      0   1   2   3   4
d1 = [ 1,  2,  0,  2,  3]
d2 = [ 1, 12,  0,  2, -7]
           *           *
```
* `d[1]` increased by 10: start of the update.
* `d[4]` decreased by 10: end of the update.

To generalize this approach, if we want to add `x` to a subarray `a[i..j]`:
* Increase `d[i]` by `x`: start the increase at `i`.
* Decrease `d[j + 1]` by `x`: end the increase after `j`.
* To get the recovered array, we **calculate the prefix sum of the difference array** by adding up `d` from left to right.

Instead of updating the original array, we **only update two values** in the difference array which is `O(1)` operation. And we can recover the original array in `O(n)` time. This is useful when we need to update a range of values in the array.

> Sample problem: [1094. Car Pooling](../leetcode/1094.car-pooling.md)