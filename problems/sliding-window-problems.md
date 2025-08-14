# Sliding Window

## Fixed Size Window
| Problem          | Difficulty |
|------------------|------------|
|[1456. Maximum Number of Vowels in a Substring of Given Length](../leetcode/1456.maximum-number-of-vowels-in-a-substring-of-given-length.md)|Medium (1263)|
|[1984. Minimum Difference Between Highest and Lowest of K Scores](../leetcode/1984.minimum-difference-between-highest-and-lowest-of-k-scores.md)|Easy (1306)| How to iterate and manipulate the index correctly? |
|[1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold](../leetcode/1343.number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold.md)|Medium (1317)|
|[2090. K Radius Subarray Averages](../leetcode/2090.k-radius-subarray-averages.md)|Medium (1358)|
|[1876. Substrings of Size Three with Distinct Characters](../leetcode/1876.substrings-of-size-three-with-distinct-characters.md)|Easy|
|[219. Contains Duplicate II](../leetcode/219.contains-duplicate-ii.md)|Easy| How to implement fixed size and general sliding window? |
|[438. Find All Anagrams in a String](../leetcode/438.find-all-anagrams-in-a-string.md)|Medium|
|[567. Permutation in String](../leetcode/567.permutation-in-string.md)|Medium|
|[1052. Grumpy Bookstore Owner](../leetcode/1052.grumpy-bookstore-owner.md)|Medium (1418)|
|[1423. Maximum Points You Can Obtain from Cards](../leetcode/1423.maximum-points-you-can-obtain-from-cards.md)|Medium (1573)|
|[1652. Defuse the Bomb](../leetcode/1652.defuse-the-bomb.md)|Easy (1416)|
|[2134. Minimum Swaps to Group All 1's Together II](../leetcode/2134.minimum-swaps-to-group-all-1s-together-ii.md)|Medium (1748)| Key observation |

> * https://leetcode.com/problems/maximum-average-subarray-i/description/ e
> * https://leetcode.com/problems/find-the-k-beauty-of-a-number/ 1279
> * https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/description/ 1306
> * https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/description/ 1360
> * https://leetcode.com/problems/maximum-sum-of-almost-unique-subarray/description/ 1545
> * https://leetcode.com/problems/maximum-sum-circular-subarray/description/ 
> * ---- Advanced (Optional) ----
> * https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/description/ 1748
> * https://leetcode.com/problems/sliding-subarray-beauty/description/ 1785
> * https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/description/ 2005

## Longest Window
| Problem          | Difficulty | Note |
|------------------|------------|------|
|[3. Longest Substring Without Repeating Characters](../leetcode/3.longest-substring-without-repeating-characters.md)|Medium| Hash set or hash table (Preferred) |
|[2461. Maximum Sum of Distinct Subarrays With Length K](../leetcode/2461.maximum-sum-of-distinct-subarrays-with-length-k.md)|Medium (1552)|
|[1695. Maximum Erasure Value](../leetcode/1695.maximum-erasure-value.md)|Medium (1528)|
|[1004. Max Consecutive Ones III](../leetcode/1004.max-consecutive-ones-iii.md)|Medium (1656)|
|[1493. Longest Subarray of 1's After Deleting One Element](../leetcode/1493.longest-subarray-of-1s-after-deleting-one-element.md)|Medium (1423)|
|[424. Longest Repeating Character Replacement](../leetcode/424.longest-repeating-character-replacement.md)|Medium| Why tracking the max frequency only? |
|[904. Fruit Into Baskets](../leetcode/904.fruit-into-baskets.md)|Medium (1516)| Remove the key from the map if the count is `0`. | 
|[2779. Maximum Beauty of an Array After Applying Operation](../leetcode/2779.maximum-beauty-of-an-array-after-applying-operation.md)|Medium (1638)|
|[1658. Minimum Operations to Reduce X to Zero](../leetcode/1658.minimum-operations-to-reduce-x-to-zero.md)|Medium (1817)|
|[1838. Frequency of the Most Frequent Element](../leetcode/1838.frequency-of-the-most-frequent-element.md)|Medium (1876)|

> * https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/description/ 1329
> * https://leetcode.com/problems/find-the-longest-semi-repetitive-substring/description/ 1501
> * https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/description/ 1535
> * ---- Advanced (Optional) ----
> * https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/description/ 1947
> * https://leetcode.com/problems/find-the-longest-equal-subarray/description/ 1976
> * TODO: https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/ 2021
> * TODO: My contest [3413. Maximum Coins From K Consecutive Bags](../leetcode/3413.maximum-coins-from-k-consecutive-bags.md) 2373

> * Solved: https://leetcode.com/problems/get-equal-substrings-within-budget/description/ 1496
> * Solved: https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/ 1643, similar to [1004. Max Consecutive Ones III](../leetcode/1004.max-consecutive-ones-iii.md) and [424. Longest Repeating Character Replacement](../leetcode/424.longest-repeating-character-replacement.md)

> ------------
> TODO: Add the definition of `* Window: ...` for each problem below.
> ------------

## Shortest Window
| Problem          | Difficulty |
|------------------|------------|
|[209. Minimum Size Subarray Sum](../leetcode/209.minimum-size-subarray-sum.md)|Medium|
|[76. Minimum Window Substring](../leetcode/76.minimum-window-substring.md)|Hard|
|[1234. Replace the Substring for Balanced String](../leetcode/1234.replace-the-substring-for-balanced-string.md)|Medium (1877)|
|[2875. Minimum Size Subarray in Infinite Array](../leetcode/2875.minimum-size-subarray-in-infinite-array.md)|Medium (1913)|

> * https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/ 1482
> * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/ 2306

## Subarray Count
### 越長越合法
一般要写 `ans += left`。

内层循环结束后，`[left,right]` 这个子数组是不满足题目要求的，但在退出循环之前的最后一轮循环，`[left−1,right]` 是满足题目要求的。

```js
 0  1  2  3  4  5
[i, j, k, a, b, a, ...]
          ^^^^^^^
             L
                R
          a, b, a
       k, a, b, a
    j, k, a, b, a
 i, j, k, a, b, a

ans += left // which is 4
```

由于子数组越长，越能满足题目要求，所以除了 `[left−1,right]`，还有 `[left−2,right]`, `[left−3,right]`, `...`, `[0,right]` 都是满足要求的。也就是说，当右端点固定在 `right` 时，左端点在 `0`, `1`, `2`, `...`, `left−1` 的所有子数组都是满足要求的，这一共有 `left` 个。

> Source: https://leetcode.cn/discuss/post/0viNMK/

| Problem          | Difficulty |
|------------------|------------|
|[3325. Count Substrings With K-Frequency Characters I](../leetcode/3325.count-substrings-with-k-frequency-characters-i.md)|Medium (1454)|
|[2799. Count Complete Subarrays in an Array](../leetcode/2799.count-complete-subarrays-in-an-array.md)|Medium (1397)|

> * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/ 1646
> * https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/description/ 1700
> * https://leetcode.com/problems/count-the-number-of-good-subarrays/description/ 1892

### 越短越合法
一般要写 `ans += right - left + 1`。

```js
[10,  5,  2,  6], k = 100
      L       R = 60 < 100 // meet the condition
     [5,  2,  6]
         [2,  6]
             [6]

ans += right - left + 1 // which is 3
```

内层循环结束后，`[left,right]` 这个子数组是满足题目要求的。由于子数组越短，越能满足题目要求，所以除了 `[left,right]`，还有 `[left+1,right]`, `[left+2,right]`, `...`, `[right,right]` 都是满足要求的。也就是说，当右端点固定在 `right` 时，左端点在 `left`, `left+1`, `left+2`, `...`, `right` 的所有子数组都是满足要求的，这一共有 `right - left + 1` 个。

> Source: https://leetcode.cn/discuss/post/0viNMK/

| Problem          | Difficulty |
|------------------|------------|
|[713. Subarray Product Less Than K](../leetcode/713.subarray-product-less-than-k.md)|Medium|

> * https://leetcode.com/problems/count-substrings-that-satisfy-k-constraint-i/description/ 1258
> * https://leetcode.com/problems/count-subarrays-with-score-less-than-k/description/ 1808
> * https://leetcode.com/problems/continuous-subarrays/description/ 1940

### 恰好型滑动窗口
例如，要计算有多少个元素和恰好等于 `k` 的子数组，可以把问题变成：

* 计算有多少个元素和 ≥`k` 的子数组。
* 计算有多少个元素和 >`k`，也就是 ≥`k+1` 的子数组。

答案就是元素和 ≥`k` 的子数组个数，减去元素和 ≥`k+1` 的子数组个数。这里把 `>` 转换成 `≥`，从而可以把滑窗逻辑封装成一个函数 `f(k)`，然后用 `f(k) - f(k + 1)` 计算，无需编写两份滑窗代码。

总结：「恰好」可以拆分成两个「至少」，也就是两个「越长越合法」的滑窗问题。注：也可以把问题变成 ≤`k` 减去 ≤`k−1`（两个至多）。可根据题目选择合适的变形方式。

> Source: https://leetcode.cn/discuss/post/0viNMK/

| Problem          | Difficulty |
|------------------|------------|

> * https://leetcode.com/problems/binary-subarrays-with-sum/description/ 1591
> * https://leetcode.com/problems/count-number-of-nice-subarrays/description/ 1623

> TODO: https://huxulm.github.io/lc-rating/list/slide_window#12212548d1984e7442fb1759acf6f690

## General Sliding Window
| Problem          | Difficulty |
|------------------|------------|
|[239. Sliding Window Maximum](../leetcode/239.sliding-window-maximium.md)|Hard|

> * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description/ 1672
> * https://leetcode.com/problems/longest-nice-subarray/ 1749
> * https://leetcode.com/problems/swap-for-longest-repeated-character-substring/description/ 1787

## Explanation
* [438. Find All Anagrams in a String](https://www.youtube.com/watch?v=I9xCo3UVomE)
* [2461. Maximum Sum of Distinct Subarrays With Length K](https://www.youtube.com/watch?v=kl9iE2tQh_A)
* [3. Longest Substring Without Repeating Characters](https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/003.Longest%20Substring%20Without%20Repeating%20Characters)
* [1004. Max Consecutive Ones III](https://www.youtube.com/watch?v=Ti9_4NVDzdg)
* [424. Longest Repeating Character Replacement](https://www.youtube.com/watch?v=wXicFFUVdd0)
* [209. Minimum Size Subarray Sum](https://www.youtube.com/watch?v=Ucepzsd2A4w)
* [76. Minimum Window Substring](https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/076.Minimum-Window-Substring)
* [1234. Replace the Substring for Balanced String](https://www.youtube.com/watch?v=XSGa1lP9vD8)
* [713. Subarray Product Less Than K](https://www.youtube.com/watch?v=WOSWdl4Fl00)
* [2875. Minimum Size Subarray in Infinite Array](https://www.youtube.com/watch?v=S1EOGabarNM)
* [239. Sliding Window Maximum](https://www.youtube.com/watch?v=b1rqOQ5p6EA)