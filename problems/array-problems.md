## [Array](../topics/array.md)

> - ✅ Problem listing: https://leetcode.cn/circle/discuss/E3yavq/#%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95%E7%AF%87
> - ✅ https://huxulm.github.io/lc-rating/list/data_structure 常用枚举技巧
> - ✅ TODO: Practice https://blog.faangshui.com/p/before-leetcode
> - TODO: Understand how to iterate in diagonal, see [Code Template](../problems/code-template.md#diagonal-traversal)

### Enumeration

#### 枚舉右，維護左

| Problem                                                                                                                              | Difficulty    | Note |
| ------------------------------------------------------------------------------------------------------------------------------------ | ------------- |------|
| [1512. Number of Good Pairs](../leetcode/1512.number-of-good-pairs.md)                                                               | Easy          | Count frequency of seen. |
|[1679. Max Number of K-Sum Pairs](../leetcode/1679.max-number-of-k-sum-pairs.md)|Medium (1345)| Variant of [1. Two Sum](../leetcode/1.two-sum.md) |
|**[1010. Pairs of Songs With Total Durations Divisible by 60](../leetcode/1010.pairs-of-songs-with-total-durations-divisible-by-60.md) | Medium (1377) | Pair `60 - (t % 60)` or `t % 60 == 0`. |
| [219. Contains Duplicate II](../leetcode/219.contains-duplicate-ii.md)                                                               | Easy          | Sliding window `[i - k, i]`. |
|**[624. Maximum Distance in Arrays](../leetcode/624.maximum-distance-in-arrays.md)                                                     | Medium        | Prefix max/min from opposite ends. |
|**[1031. Maximum Sum of Two Non-Overlapping Subarrays](../leetcode/1031.maximum-sum-of-two-non-overlapping-subarrays.md)               | Medium (1680) | `[First,Second]` or `[Second,First]` + prefix sum. |
| [1014. Best Sightseeing Pair](../leetcode/1014.best-sightseeing-pair.md)                                                             | Medium (1730) | Seen `values[i] + i`, enumerate `values[j] - j`. |
| [1814. Count Nice Pairs in an Array](../leetcode/1814.count-nice-pairs-in-an-array.md)                                               | Medium (1737) | Seen `A - rev(A)`, enumerate `B - rev(B)`. |

#### 枚舉中間

| Problem                                                                                                       | Difficulty    | Note |
| ------------------------------------------------------------------------------------------------------------- | ------------- |------|
| [238. Product of Array Except Self](../leetcode/238.product-of-array-except-self.md)                          | Medium        | Prefix / suffix product. |
| [2909. Minimum Sum of Mountain Triplets II](../leetcode/2909.minimum-sum-of-mountain-triplets-ii.md)          | Medium (1478) | Fix middle, trakc min lefet and right. |
| [2874. Maximum Value of an Ordered Triplet II](../leetcode/2874.maximum-value-of-an-ordered-triplet-ii.md)    | Medium (1583) | Prefix max, maxDiff. |

### Movement

| Problem                                                              | Difficulty |
| -------------------------------------------------------------------- | ---------- |
| [283. Move Zeroes](../leetcode/283.move-zeros.md)                    | Easy       |
| [189. Rotate Array](../leetcode/189.rotate-array.md)                 | Medium     |
| [665. Non-decreasing Array](../leetcode/665.non-decreasing-array.md) | Medium     |

### Iteration

| Problem                                                                                                        | Difficulty    |
| -------------------------------------------------------------------------------------------------------------- | ------------- |

> - https://leetcode.com/problems/teemo-attacking/description/ e
> - https://leetcode.com/problems/third-maximum-number/description/ e
> - https://leetcode.com/problems/maximum-product-of-three-numbers/description/ e

### 2D Array

| Problem | Difficulty |
| ------- | ---------- |

> - https://leetcode.com/problems/pascals-triangle/description/ e
> - https://leetcode.com/problems/pascals-triangle-ii/ e
> - https://leetcode.com/problems/reshape-the-matrix/description/ e
> - https://leetcode.com/problems/image-smoother/description/ e
> - https://leetcode.com/problems/battleships-in-a-board/description/ m
> - https://leetcode.com/problems/game-of-life/description/ m
> - https://leetcode.com/problems/diagonal-traverse/description/

### To Classify

| Problem                                                                                                | Difficulty | Note |
| ------------------------------------------------------------------------------------------------------ | ---------- |------|
| [989. Add to Array-Form of Integer](../leetcode/989.add-to-array-form-of-integer.md)                   | Easy       | Simulation. |
| [334. Increasing Triplet Subsequence](../leetcode/334.increasing-triplet-subsequence.md)               | Medium     | Mind the check order of `first` and `second`. |
| [581. Shortest Unsorted Continuous Subarray](../leetcode/581.shortest-unsorted-continuous-subarray.md) | Medium     | Prefix max + suffix min. |
| [941. Valid Mountain Array](../leetcode/941.valid-mountain-array.md)                                   | Easy       |
| [845. Longest Mountain in Array](../leetcode/845.longest-mountain-in-array.md)                         | Medium     |
| [73. Set Matrix Zeroes](../leetcode/73.set-matrix-zeros.md)                                            | Medium     | Use first row and column as markers |
| [766. Toeplitz Matrix](../leetcode/766.toeplitz-matrix.md)                                             | Easy       |
| [48. Rotate Image](../leetcode/48.rotate-image.md)                                                     | Medium     |
| [54. Spiral Matrix](../leetcode/54.spiral-matrix.md)                                                   | Medium     |
| [59. Spiral Matrix II](../leetcode/59.spiral-matrix-ii.md)                                             | Medium     |
| [189. Rotate Array](../leetcode/189.rotate-array.md)                                                   | Medium     |
| ~~[136. Single Number](../leetcode/136.single-number.md)~~                                             | Easy       |
| ~~[31. Next Permutation](../leetcode/31.next-permutation.md)~~                                         | Medium     |

### [Sorting](../topics/sorting.md)

| Problem                                             | Difficulty |
| --------------------------------------------------- | ---------- |
| [912. Sort an Array](../topics/sorting.md)          | Medium     |
| [75. Sort Colors](../leetcode/75.sort-colors.md)    | Medium     |
| [148. Sort List](../leetcode/148.sort-list.md)      | Medium     |

> Solved: https://leetcode.com/problems/height-checker/
