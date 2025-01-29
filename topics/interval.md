# Interval

Interval is a range of values. It is represented as a pair of integers: `[start, end]` (inclusive). The start is always less than or equal to the end.

> We always use inclusive interval in this note.

## Overlapping
To determine if two intervals are overlapped, we need to check if there is any intersection between the two intervals. That is, there is at least one value that is in both intervals.

There are several ways to check if two intervals are overlapped. The most simple way is the following:
```kotlin
private fun isOverlapped(a: IntArray, b: IntArray): Boolean {
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
// Partially overlapped
Case 1:
|----|       a
   |------|  b

Case 2:
   |------|
|----|

// The two intervals are fully overlapped.
Case 4:
   |---|
|---------|

Case 3:
|---------|
   |---|
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
// b[1] < a[0], but they are overlapped.
|-------|               a
          |-------|     b

// b[0] < a[1], but they are not overlapped.
          |-------|     a
|-------|               b
```