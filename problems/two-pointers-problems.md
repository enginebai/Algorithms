# Two Pointers

## Left/Right Pointers
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[344. Reverse String](../leetcode/344.reverse-string.md)|Easy|
|[1750. Minimum Length of String After Deleting Similar Ends](../leetcode/1750.minimum-length-of-string-after-deleting-similar-ends.md)|Medium (1501)| Shrink both ends, **avoid overlapping.** |
|[11. Container With Most Water](../leetcode/11.container-with-most-water.md)|Medium| Why moving shorter pointer inward? |
|[658. Find K Closest Elements](../leetcode/658.find-k-closest-elements.md)|Medium| Shring window from longer distance. |
|[948. Bag of Tokens](../leetcode/948.bag-of-tokens.md)|Medium (1762)| Simulate greedily. |
|[1775. Equal Sum Arrays With Minimum Number of Operations](../leetcode/1775.equal-sum-arrays-with-minimum-number-of-operations.md)|Medium (1850)|

> * Solved: https://leetcode.com/problems/valid-palindrome/description/ e
> * https://leetcode.com/problems/apply-operations-to-an-array/description/ 1223
> * https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/description/ 2276

## Read/Write Pointers
> In this section, please pay attention to the definition of write pointer. Always have a clear definition of write pointer:
> - The next position to write.
> - The current written position.

| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[27. Remove Element](../leetcode/27.remove-element.md)|Easy|Overwrite.|
|[283. Move Zeroes](../leetcode/283.move-zeros.md)|Easy|Read non-zero, write to front. |
|[977. Squares of a Sorted Array](../leetcode/977.squares-of-a-sorted-array.md)|Easy|
|[26. Remove Duplicates from Sorted Array](../leetcode/26.remove-duplicates-from-sorted-array.md)|Easy| Check `nums[write - 1] != nums[read]`. |
|[80. Remove Duplicates from Sorted Array II](../leetcode/80.remove-duplicates-from-sorted-array-ii.md)|Medium| Generalize to allow duplicate, check `nums[write - k] != nums[read]`. |
|[1089. Duplicate Zeros](../leetcode/1089.duplicate-zeros.md)|Easy (1262)|
|[905. Sort Array By Parity](../leetcode/905.sort-array-by-parity.md)|Easy| Write even to front or write odd to back. |
|[922. Sort Array By Parity II](../leetcode/922.sort-array-by-parity-ii.md)|Easy (1173)| Find misplaced even/odd and swap |
|[202. Happy Number](../leetcode/202.happy-number.md)|Easy|Cycle detection. |

## nSum Problems
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[1. Two Sum](../leetcode/1.two-sum.md)|Easy| 
|[167. Two Sum II - Input Array Is Sorted](../leetcode/167.two-sum-ii-input-array-is-sorted.md)|Medium|
|[1679. Max Number of K-Sum Pairs](../leetcode/1679.max-number-of-k-sum-pairs.md)|Medium (1345)| Count two sum == k. |
|[15. 3Sum](../leetcode/15.3sum.md)|Medium| How to avoid duplicate triplets? |
|[16. 3Sum Closest](../leetcode/16.3sum-closest.md)|Medium|
|[633. Sum of Square Numbers](../leetcode/633.sum-of-square-numbers.md)|Medium| Binary search or two pointers or hash table. |
|[923. 3Sum With Multiplicity](../leetcode/923.3sum-with-multiplicity.md)|Medium (1710)| ([15. 3Sum](../leetcode/15.3sum.md) two pointers) or ([1. Two Sum](../leetcode/1.two-sum.md) hash table technique). |

## Two Pointers on Two Sorted Sequences
> - Fix one pointer and search the other from sorted sequence.
> - Most of the problems can be sovled by binary search.

| Problem          | Difficulty | Note |
|------------------|------------|------|
|[2300. Successful Pairs of Spells and Potions](../leetcode/2300.successful-pairs-of-spells-and-potions.md)|Medium (1476)| Fix `spells`, binary search on sorted `potions`. |
|[826. Most Profit Assigning Work](../leetcode/826.most-profit-assigning-work.md)|Medium (1708)| Sort + prefix max + binary search or two pointers. |
|[1574. Shortest Subarray to be Removed to Make Array Sorted](../leetcode/1574.shortest-subarray-to-be-removed-to-make-array-sorted.md)|Medium (1931)| Fix `i`, binary search `j`. |
|[475. Heaters](../leetcode/475.heaters.md)|Medium| Find the closest heater for each house. |
|[633. Sum of Square Numbers](../leetcode/633.sum-of-square-numbers.md)|Medium| Binary search, two pointers, hash table. |
|[1855. Maximum Distance Between a Pair of Values](../leetcode/1855.maximum-distance-between-a-pair-of-values.md)|Medium (1514)| Fix `i`, binary search `j`. |

## 3 Pointers
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[611. Valid Triangle Number](../leetcode/611.valid-triangle-number.md)|Medium |How to iterate the 3 pointers and update count correctly? Sort + fix `i`, search with two pointers or binary search. |
|[2563. Count the Number of Fair Pairs](../leetcode/2563.count-the-number-of-fair-pairs.md)|Medium (1720)| `count(<=R) - count(<=L-1)`. Two pointers. |

> * https://leetcode.com/problems/number-of-arithmetic-triplets/description/ 1203
> * https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/description/ 1817
> * https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/ 2092

## Two Sequences Matching
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[88. Merge Sorted Array](../leetcode/88.merge-sorted-array.md)|Easy|
|[925. Long Pressed Name](../leetcode/925.long-pressed-name.md)|Easy| Implementation details. | Scan both, handl extra repeat |
|[844. Backspace String Compare](../leetcode/844.backspace-string-compare.md)|Easy (1227)| Scan from end, skip `#`. |
|[2337. Move Pieces to Obtain a String](../leetcode/2337.move-pieces-to-obtain-a-string.md)|Medium (1693)| Match L/R positionally. |
|[777. Swap Adjacent in LR String](../leetcode/777.swap-adjacent-in-lr-string.md)|Medium (1938)| (Same as [2337.](../leetcode/2337.move-pieces-to-obtain-a-string.md)) |

> * https://leetcode.com/problems/multiply-strings/description/
> * https://leetcode.com/problems/minimum-common-value/description/ 1249
> * https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/description/ 1281
> * https://leetcode.com/problems/adding-spaces-to-a-string/description/ 1315
> * https://leetcode.com/problems/get-the-maximum-score/description/ 1961

## Intersection
| Problem          | Difficulty |
|------------------|------------|
|[350. Intersection of Two Arrays II](../leetcode/350.intersection-of-two-arrays-ii.md)|Easy|
|[986. Interval List Intersections](../leetcode/986.interval-list-intersections.md)|Medium|

> * https://leetcode.com/problems/intersection-of-two-arrays/submissions/ e
> * https://leetcode.com/problems/minimum-index-sum-of-two-lists/description/ e

### Subsequence
| Problem          | Difficulty |
|------------------|------------|
|[392. Is Subsequence](../leetcode/392.is-subsequence.md)|Easy|
> * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/
> * https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/description/ 1362

## Group By Consecutive (分组循环)

| Problem          | Difficulty | Notes |
|------------------|------------|-------|
| [485. Max Consecutive Ones](../leetcode/485.max-consecutive-ones.md)| Easy          |
|[1446. Consecutive Characters](../leetcode/1446.consecutive-characters.md)|Easy (1165)| Longest same character streak. |
|[1578. Minimum Time to Make Rope Colorful](../leetcode/1578.minimum-time-to-make-rope-colorful.md)|Medium (1574)|
|[228. Summary Ranges](../leetcode/228.summary-ranges.md)|Easy| Longest increasing streak. |
|[674. Longest Continuous Increasing Subsequence](../leetcode/674.longest-continuous-increasing-subsequence.md)|Easy|
|[978. Longest Turbulent Subarray](../leetcode/978.longest-turbulent-subarray.md)|Medium (1393)|
|[1887. Reduction Operations to Make the Array Elements Equal](../leetcode/1887.reduction-operations-to-make-the-array-elements-equal.md)|Medium (1427)|
|[845. Longest Mountain in Array](../leetcode/845.longest-mountain-in-array.md)|Medium (1436)|

> TODO: To understand [1887. Reduction Operations to Make the Array Elements Equal](../leetcode/1887.reduction-operations-to-make-the-array-elements-equal.md), and [845. Longest Mountain in Array](../leetcode/845.longest-mountain-in-array.md).
> * https://leetcode.com/problems/count-number-of-homogenous-substrings/ 1490
> * Solved, https://leetcode.com/problems/count-alternating-subarrays/description/
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
