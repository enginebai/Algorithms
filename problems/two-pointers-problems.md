# Two Pointers

## Left/Right Pointers
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|A. [344. Reverse String](../leetcode/344.reverse-string.md)|Easy|
|C3. [1750. Minimum Length of String After Deleting Similar Ends](../leetcode/1750.minimum-length-of-string-after-deleting-similar-ends.md)|Medium (1501)| How not to intersect? |
|A. [11. Container With Most Water](../leetcode/11.container-with-most-water.md)|Medium| How to move the pointer? |
|A. [658. Find K Closest Elements](../leetcode/658.find-k-closest-elements.md)|Medium| While more than `k` elements, shrink the window by moving longer distance. |
|C3. [948. Bag of Tokens](../leetcode/948.bag-of-tokens.md)|Medium (1762)| Simulate greedily. |
|[1775. Equal Sum Arrays With Minimum Number of Operations](../leetcode/1775.equal-sum-arrays-with-minimum-number-of-operations.md)|Medium (1850)|

> * Solved: https://leetcode.com/problems/valid-palindrome/description/ e
> * https://leetcode.com/problems/apply-operations-to-an-array/description/ 1223
> * https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/description/ 2276

## nSum Problems
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|A. [1. Two Sum](../leetcode/1.two-sum.md)|Easy| 
|A. [167. Two Sum II - Input Array Is Sorted](../leetcode/167.two-sum-ii-input-array-is-sorted.md)|Medium|
|C3. [1679. Max Number of K-Sum Pairs](../leetcode/1679.max-number-of-k-sum-pairs.md)|Medium (1345)| Count two sum == k. |
|A. [15. 3Sum](../leetcode/15.3sum.md)|Medium| How to avoid duplicate triplets? |
|[16. 3Sum Closest](../leetcode/16.3sum-closest.md)|Medium|
|[633. Sum of Square Numbers](../leetcode/633.sum-of-square-numbers.md)|Medium| 
|B. [923. 3Sum With Multiplicity](../leetcode/923.3sum-with-multiplicity.md)|Medium (1710)| Key intution + implementation details. |

## Read/Write Pointers
> In this section, please pay attention to the definition of write pointer. Always have a clear definition of write pointer:
> - The next position to write.
> - The current written position.

| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[27. Remove Element](../leetcode/27.remove-element.md)|Easy|
|A. [283. Move Zeroes](../leetcode/283.move-zeros.md)|Easy|
|B. [977. Squares of a Sorted Array](../leetcode/977.squares-of-a-sorted-array.md)|Easy|
|A.[26. Remove Duplicates from Sorted Array](../leetcode/26.remove-duplicates-from-sorted-array.md)|Easy|
|A.[80. Remove Duplicates from Sorted Array II](../leetcode/80.remove-duplicates-from-sorted-array-ii.md)|Medium| Generalize to allow duplicate number appear at most `k` times. |
|C2.[1089. Duplicate Zeros](../leetcode/1089.duplicate-zeros.md)|Easy (1262)|
|B. [905. Sort Array By Parity](../leetcode/905.sort-array-by-parity.md)|Easy| `Even / Odd` |
|C3. [922. Sort Array By Parity II](../leetcode/922.sort-array-by-parity-ii.md)|Easy (1173)| In-place: Find missplaced odd and even elements, and swap them. |
|[202. Happy Number](../leetcode/202.happy-number.md)|Easy|

## Same Direction
| Problem          | Difficulty |
|------------------|------------|
|C3. [1574. Shortest Subarray to be Removed to Make Array Sorted](../leetcode/1574.shortest-subarray-to-be-removed-to-make-array-sorted.md)|Medium (1931)|

## Intersection
| Problem          | Difficulty |
|------------------|------------|
|A. [350. Intersection of Two Arrays II](../leetcode/350.intersection-of-two-arrays-ii.md)|Easy|
|[986. Interval List Intersections](../leetcode/986.interval-list-intersections.md)|Medium|

> * https://leetcode.com/problems/intersection-of-two-arrays/submissions/ e
> * https://leetcode.com/problems/minimum-index-sum-of-two-lists/description/ e

## Two Sequences + Two Pointers
> Some common problems are listed in [Binary Search Problems](../problems/binary-search-problems.md#two-sequences--two-pointers).

| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|A.[88. Merge Sorted Array](../leetcode/88.merge-sorted-array.md)|Easy|
|C3. [925. Long Pressed Name](../leetcode/925.long-pressed-name.md)|Easy| Implementation details. |
|B. [844. Backspace String Compare](../leetcode/844.backspace-string-compare.md)|Easy (1227)| Implementation details. |
|[475. Heaters](../leetcode/475.heaters.md)|Medium|
|[1855. Maximum Distance Between a Pair of Values](../leetcode/1855.maximum-distance-between-a-pair-of-values.md)|Medium (1514)|
|C3. [2337. Move Pieces to Obtain a String](../leetcode/2337.move-pieces-to-obtain-a-string.md)|Medium (1693)| Implementation details. |
|[777. Swap Adjacent in LR String](../leetcode/777.swap-adjacent-in-lr-string.md)|Medium (1938)|

> * https://leetcode.com/problems/multiply-strings/description/
> * https://leetcode.com/problems/minimum-common-value/description/ 1249
> * https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/description/ 1281
> * https://leetcode.com/problems/adding-spaces-to-a-string/description/ 1315
> * https://leetcode.com/problems/get-the-maximum-score/description/ 1961

### 子序列
| Problem          | Difficulty |
|------------------|------------|
|A. [392. Is Subsequence](../leetcode/392.is-subsequence.md)|Easy|
> * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/
> * https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/description/ 1362

## 3 Pointers
| Problem          | Difficulty |
|------------------|------------|
|[611. Valid Triangle Number](../leetcode/611.valid-triangle-number.md)|Medium|
|C3. [2563. Count the Number of Fair Pairs](../leetcode/2563.count-the-number-of-fair-pairs.md)|Medium (1720)|

> * https://leetcode.com/problems/number-of-arithmetic-triplets/description/ 1203
> * https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/description/ 1817
> * https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/ 2092

## 分组循环 (Group by Consecutive)
> All are C3. 

| Problem          | Difficulty |
|------------------|------------|
|[1446. Consecutive Characters](../leetcode/1446.consecutive-characters.md)|Easy (1165)|
|[1578. Minimum Time to Make Rope Colorful](../leetcode/1578.minimum-time-to-make-rope-colorful.md)|Medium (1574)|
|[228. Summary Ranges](../leetcode/228.summary-ranges.md)|Easy|
|[674. Longest Continuous Increasing Subsequence](../leetcode/674.longest-continuous-increasing-subsequence.md)|Easy|
|[978. Longest Turbulent Subarray](../leetcode/978.longest-turbulent-subarray.md)|Medium (1393)|
|[1887. Reduction Operations to Make the Array Elements Equal](../leetcode/1887.reduction-operations-to-make-the-array-elements-equal.md)|Medium (1427)|
|[845. Longest Mountain in Array](../leetcode/845.longest-mountain-in-array.md)|Medium (1436)|

> TODO: To understand [1887. Reduction Operations to Make the Array Elements Equal](../leetcode/1887.reduction-operations-to-make-the-array-elements-equal.md), and [845. Longest Mountain in Array](../leetcode/845.longest-mountain-in-array.md).
> * https://leetcode.com/problems/count-number-of-homogenous-substrings/ 1490
> * https://leetcode.com/problems/unique-substrings-in-wraparound-string/description/

## Explanation
* [11. Container With Most Water](https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/011.Container-With-Most-Water)
* [658. Find K Closest Elements](https://www.youtube.com/watch?v=gfwLpRYbCx0)
* [611. Valid Triangle Number](https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/611.Valid-Triangle-Number)
* [475. Heaters](https://www.youtube.com/watch?v=25LSSsAGLDw)
* [1775. Equal Sum Arrays With Minimum Number of Operations](https://www.youtube.com/watch?v=HK7o2pQRvMQ)
* [15. 3Sum](https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/015.3Sum)
* [16. 3Sum Closest](https://www.youtube.com/watch?v=9nG3tEJ6-8k)
* [923. 3Sum With Multiplicity](https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/923.3Sum-With-Multiplicity)
* [26. Remove Duplicates from Sorted Array](https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/026.Remove-Duplicates-from-Sorted-Array)
* [80. Remove Duplicates from Sorted Array II](https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/080.Remove-Duplicates-from-Sorted-Array-II)
* [1574. Shortest Subarray to be Removed to Make Array Sorted](https://www.youtube.com/watch?v=pNNvmjNJcu8)
