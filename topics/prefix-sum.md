# Prefix Sum

## Overview

**Prefix sum** (also known as cumulative sum) is a technique to precompute cumulative sums of an array, enabling `O(1)` range sum queries after `O(n)` preprocessing. The same concept applies to **prefix product**.

**When to use prefix sum:**

- Range sum queries on a static array
- Finding subarrays with a specific sum
- Counting subarrays satisfying certain conditions
- Problems involving cumulative effects

## Implementations

There are **two common conventions** for prefix sum arrays:

### 0-Indexed (Inclusive)

`prefix[i]` = sum of `nums[0..i]` (inclusive of `nums[i]`)

```js
nums     = [2, 3, 1, 2]
index       0  1  2  3
prefix   = [2, 5, 6, 8]

// prefix[2] = nums[0] + nums[1] + nums[2] = 6
```

**Formula**: `sumRange(L, R) = prefix[R] - prefix[L - 1]`

**Edge case**: Need special handling when `L == 0`

```kotlin
// Build prefix sum array
fun buildPrefixSum(nums: IntArray): IntArray {
    val n = nums.size
    val prefix = IntArray(n)
    prefix[0] = nums[0]
    for (i in 1 until n) {
        prefix[i] = prefix[i - 1] + nums[i]
    }
    return prefix
}

// Build suffix sum array
fun buildSuffixSum(nums: IntArray): IntArray {
    val n = nums.size
    val suffix = IntArray(n)
    suffix[n - 1] = nums[n - 1]
    for (i in n - 2 downTo 0) {
        suffix[i] = suffix[i + 1] + nums[i]
    }
    return suffix
}

// Query range sum [left, right] inclusive
fun sumRange(prefix: IntArray, left: Int, right: Int): Int {
    return if (left == 0) prefix[right]
           else prefix[right] - prefix[left - 1]
}

```

### 1-Indexed with Sentinel (Recommended)

`prefix[i]` = sum of first `i` elements = sum of `nums[0..i-1]` (exclusive of `nums[i]`)

```js
nums     = [2, 3, 1, 2]
index       0  1  2  3

prefix   = [0, 2, 5, 6, 8]   // 1-indexed with sentinel
index       0  1  2  3  4

// prefix[0] = 0 (sentinel, sum of 0 elements)
// prefix[1] = nums[0] = 2
// prefix[2] = nums[0] + nums[1] = 5
// prefix[3] = nums[0] + nums[1] + nums[2] = 6
```

> Natural interpretation of prefix sum:
>
> - prefix[i] = "sum of first i elements"
> - prefix[0] = "sum of first 0 elements" = 0 ✓

**Formula**: `sumRange(L, R) = prefix[R + 1] - prefix[L]`

**No edge case**: Works uniformly for all ranges

```kotlin
// Build prefix sum array
fun buildPrefixSum(nums: IntArray): IntArray {
    val n = nums.size
    val prefix = IntArray(n + 1)  // prefix[0] = 0 as sentinel
    for (i in 0 until n) {
        prefix[i + 1] = prefix[i] + nums[i]
    }
    return prefix
}

fun buildSuffixSum(nums: IntArray): IntArray {
    val n = nums.size
    val suffix = IntArray(n + 1)
    suffix[n] = 0  // sentinel
    for (i in n - 1 downTo 0) {
        suffix[i] = suffix[i + 1] + nums[i]
    }
    return suffix
}

// Query range sum [left, right] inclusive
fun sumRange(prefix: IntArray, left: Int, right: Int): Int {
    return prefix[right + 1] - prefix[left]
}
```

### Comparison

| Aspect          | 0-indexed (inclusive)     | 1-indexed (with sentinel) |
| --------------- | ------------------------- | ------------------------- |
| Array size      | `n`                       | `n + 1`                   |
| `prefix[0]`     | `nums[0]`                 | `0` (sentinel)            |
| Range formula   | `prefix[R] - prefix[L-1]` | `prefix[R+1] - prefix[L]` |
| Edge case `L=0` | **Need special handling** | **No edge case**          |
| Code clarity    | More conditions           | **Cleaner**               |

> **Recommendation**: Use the **1-indexed sentinel approach** for cleaner code and fewer edge cases.

## Pitfalls

- **Off-by-one errors**: Be careful with the indexing convention you choose. Stick to one convention consistently.
- **Integer overflow**: Use `Long` for large sums or products.
- **Base case for prefix sum**: When using prefix sum with hash table, remember to initialize `countMap[0] = 1` to handle cases where `prefixSum == k`.
- **Negative numbers**: Prefix sum works with negative numbers, but sliding window often doesn't (can't shrink window reliably).
- **Empty prefix**: `prefix[0] = 0` for sum, `prefix[0] = 1` for product.
