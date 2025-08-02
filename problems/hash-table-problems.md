# [Hash Table](../topics/hash-table.md)

## Design
| Problem          | Difficulty | Note |
|------------------|------------|------|
|[705. Design HashSet](../leetcode/705.design-hashset.md)|Easy| Fundamental implementation of hash table. |
|[706. Design HashMap](../leetcode/706.design-hashmap.md)|Medium| Similar solution to [705. Design HashSet](../leetcode/705.design-hashset.md), might skip it.|
|[535. Encode and Decode TinyURL](../leetcode/535.encode-and-decode-tinyurl.md)|Medium| Bidirectional mapping. |
|[380. Insert Delete GetRandom O(1)](../leetcode/380.insert-delete-getrandom-o1.md)|Medium| Hash table + list + critical implementation details in `remove()`. |
|[381. Insert Delete GetRandom O(1) - Duplicates allowed](../leetcode/381.insert-delete-getrandom-o1-duplicates-allowed.md)|Hard| `HashMap<Int, Set>` + list + critical implementation details in `remove()`. |
|[355. Design Twitter](../leetcode/355.design-twitter.md)|Medium| Hash table + Heap (Pull Mode) |
|[1396. Design Underground System](../leetcode/1396.design-underground-system\.md)|Medium (1464) | Hash table for check-in and route tracking. |
|[146. LRU Cache](../leetcode/146.lru-cache.md)|Medium| Hash table + Doubly linked list for `O(1)` update/evict. |

> * https://leetcode.com/problems/all-oone-data-structure/description/ hard

## Existance
| Problem          | Difficulty | Note |
|------------------|------------|------|
|[1. Two Sum](../leetcode/1.two-sum.md)|Easy| Hash table for seen, enumerate to find complement. |
|[1679. Max Number of K-Sum Pairs](../leetcode/1679.max-number-of-k-sum-pairs.md)|Medium (1345)| Variant of [1. Two Sum](../leetcode/1.two-sum.md) |
|[1346. Check If N and Its Double Exist](../leetcode/1346.check-if-n-and-its-double-exist.md)|Easy (1225)| Check `n / 2` and `n * 2`. |
|[633. Sum of Square Numbers](../leetcode/633.sum-of-square-numbers.md)|Medium| Binary search or two pointers or hash table. |
|[532. K-diff Pairs in an Array](../leetcode/532.k-diff-pairs-in-an-array.md)|Medium| Check `n - k` and `n + k` or `n + k` only. |
|[923. 3Sum With Multiplicity](../leetcode/923.3sum-with-multiplicity.md)|Medium (1710)| ([15. 3Sum](../leetcode/15.3sum.md) two pointers) or ([1. Two Sum](../leetcode/1.two-sum.md) hash table technique). |
|[128. Longest Consecutive Sequence](../leetcode/128.longest-consecutive-sequence.md)|Medium| Set + expand only when `n - 1` not exists. |
|[36. Valid Sudoku](../leetcode/36.valid-sudoku.md)|Medium| Row, column, sub-box check. |

> * Solved: https://leetcode.com/problems/jewels-and-stones/description/ 1164
> * Solved: https://leetcode.com/problems/largest-substring-between-two-equal-characters/description/ 1281
> * Solved: https://leetcode.com/problems/most-common-word/description/ 1297
> * Solved: https://leetcode.com/problems/path-crossing/description/ e 1508

## Duplicates
| Problem          | Difficulty | Note |
|------------------|------------|------|
|[217. Contains Duplicate](../leetcode/217.contains-duplicate.md)|Easy| Set for seen. |
|[219. Contains Duplicate II](../leetcode/219.contains-duplicate-ii.md)|Easy| Sliding window `[i - k, i]` |

> * Solved: https://leetcode.com/problems/unique-email-addresses/description/ 1199
> * Solved: https://leetcode.com/problems/uncommon-words-from-two-sentences/description/ 1259
> * Solved: https://leetcode.com/problems/unique-morse-code-words/description/ 1307

## Counting
| Problem          | Difficulty | Note |
|------------------|------------|------|
|[242. Valid Anagram](../leetcode/242.valid-anagram.md)|Easy|
|[169. Majority Element](../leetcode/169.majority-element.md)|Easy| Vote count. |
|[697. Degree of an Array](../leetcode/697.degree-of-an-array.md)|Easy|
|[1002. Find Common Characters](../leetcode/1002.find-common-characters.md)|Easy| Min freq map across all strings. |
|[554. Brick Wall](../leetcode/554.brick-wall.md)|Medium| Count edge position frequency. |
|[916. Word Subsets](../leetcode/916.word-subsets.md)|Medium (1624)| Max freq map for `B`, check `A >= B`. |

> * https://leetcode.com/problems/number-of-good-pairs/description/ 1160
> * https://leetcode.com/problems/ransom-note/description/ e
> * https://leetcode.com/problems/first-unique-character-in-a-string/description/ e
> * https://leetcode.com/problems/longest-harmonious-subsequence/description/ e
> * https://leetcode.com/problems/make-two-arrays-equal-by-reversing-subarrays/description/ 1151
> * https://leetcode.com/problems/find-lucky-integer-in-an-array/description/ 1118
> * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/description/ 1152
> * https://leetcode.com/problems/unique-number-of-occurrences/description/ 1195
> * https://leetcode.com/problems/maximum-number-of-balloons/description/ 1181
> * https://leetcode.com/problems/check-if-the-sentence-is-pangram/description/ 1166
> * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/description/ 1161
> * https://leetcode.com/problems/sum-of-unique-elements/description 1228 
> * https://leetcode.com/problems/find-common-characters/description/ 1279
> * https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/description/ 1300
> * Solved: https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal/description/ 1309
> * Solved: https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/description/ 1330
> * https://leetcode.com/problems/second-largest-digit-in-a-string/description/ 1341
> * https://leetcode.com/problems/subdomain-visit-count/description/ 1377

## Mapping
| Problem          | Difficulty | Note |
|------------------|------------|------|
|[290. Word Pattern](../leetcode/290.word-pattern.md)|Easy| Bidirectional mapping. |
|[49. Group Anagrams](../leetcode/49.group-anagrams.md)|Medium| Sort or count as key. |

> * https://leetcode.com/problems/verifying-an-alien-dictionary/description/ 1299
> * https://leetcode.com/problems/isomorphic-strings/description/ e

## Other
| Problem          | Difficulty | Note |
|------------------|------------|------|
|[138. Copy List with Random Pointer](../leetcode/138.copy-list-with-random-pointers.md)|Medium| Map old -> new. |
|[133. Clone Graph](../leetcode/133.clone-graph.md)|Medium| Map old -> new. |

## Cycle Sort
| Problem                                                                 | Difficulty | Key Difference                                                                 |
|------------------------------------------------------------------------|------------|----------------------------------------------------------------------------------|
| [41. First Missing Positive](../leetcode/41.first-missing-positive.md)         | Hard       | Finds the smallest missing **positive**; range is `[1..n]` |
| [442. Find All Duplicates in an Array](../leetcode/442.find-all-duplicates-in-an-array.md) | Medium     | Find all elements that appear **twice**; range is `[1..n]`      |
| [448. Find All Numbers Disappeared in an Array](../leetcode/448.find-all-numbers-disappeared-in-an-array.md) | Easy       | Find all numbers **missing** from `[1..n]`      |
| [645. Set Mismatch](../leetcode/645.set-mismatch.md)                           | Easy       | One number duplicated, one missing; range is `[1..n]`  |
| [268. Missing Number](../leetcode/268.missing-number.md)                          | Easy       | One number missing from `[0..n]`                        |
|[287. Find the Duplicate Number](../leetcode/287.find-the-duplicate-number.md)|Medium| Range: nums.length == `n+1`, values in `[1..n]`; exactly one duplicate |

## Prefix Sum 
### Basic
| Problem          | Difficulty |
|------------------|------------|
|[303. Range Sum Query - Immutable](../leetcode/303.range-sum-query-immutable.md)|Easy|
|[1588. Sum of All Odd Length Subarrays](../leetcode/1588.sum-of-all-odd-length-subarrays.md)|Easy|
|[2559. Count Vowel Strings in Ranges](../leetcode/2559.count-vowel-strings-in-ranges.md)|Medium (1435)|
|[2389. Longest Subsequence With Limited Sum](../leetcode/2389.longest-subsequence-with-limited-sum.md)|Medium (1387)|
|[53. Maximum Subarray](../leetcode/53.maximum-subarray.md)|Medium|
|[1749. Maximum Absolute Sum of Any Subarray](../leetcode/1749.maximum-absolute-sum-of-any-subarray.md)|Medium (1541)|
|[2055. Plates Between Candles](../leetcode/2055.plates-between-candle.md)|Medium (1819)|

> * TODO: |[2090. K Radius Subarray Averages](../leetcode/2090.k-radius-subarray-averages.md)|Medium (1358)|
> * https://leetcode.com/problems/special-array-ii/description/ 1523

### Prefix Sum + Hash Table
| Problem          | Difficulty |
|------------------|------------|
|[560. Subarray Sum Equals K](../leetcode/560.subarray-sum-equals-k.md)|Medium|

> * TODO: [前缀和与哈希表](https://huxulm.github.io/lc-rating/list/data_structure#57bd9b702cc3a23859ac62ef7232ab19)

## Explanation
* [535. Encode and Decode TinyURL](https://www.youtube.com/watch?v=fxLsDMiUVKg)
* [380. Insert Delete GetRandom O(1)](https://www.youtube.com/watch?v=jE6VIeQxzLU)
* [381. Insert Delete GetRandom O(1) - Duplicates allowed](https://www.youtube.com/watch?v=IqUJz-enhGA)
* [381. Insert Delete GetRandom O(1) - Duplicates allowed](https://www.youtube.com/watch?v=8mCXHQ4jpqA)
* [355. Design Twitter](https://www.youtube.com/watch?v=q6RILQAaFvc)
* [146. LRU Cache](https://www.youtube.com/watch?v=jX_k2FIhRI0)
* [460. LFU Cache](https://www.youtube.com/watch?v=EpLALCho36w)
* [532. K-diff Pairs in an Array](https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/532.K-diff-Pairs-in-an-Array)
* [128. Longest Consecutive Sequence](https://www.youtube.com/watch?v=QnBcLxgeeGs)
* [290. Word Pattern](https://www.youtube.com/watch?v=iltd05pcZMg)
* [133. Clone Graph](https://www.youtube.com/watch?v=KLKEr_MieaU)
* [923. 3Sum With Multiplicity](https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/923.3Sum-With-Multiplicity)
* [49. Group Anagrams](https://github.com/wisdompeak/LeetCode/tree/master/Hash/049.Group-Anagrams)
* [41. First Missing Positive](https://www.youtube.com/watch?v=SRsT9iHb4OE)

> The following problems are not covered in the problem listing.
* [3448. Count Substrings Divisible By Last Digit](https://youtu.be/7VDPEki9qX4)
* [2488. Count Subarrays With Median K](https://youtu.be/psT9C6ZNPa8)
* [2950. Number of Divisible Substrings](https://youtu.be/EmulJBY8UnU)
* [2949. Count Beautiful Substrings II](https://youtu.be/7wPPrfeMJ3w)
* [2025. Maximum Number of Ways to Partition an Array](https://youtu.be/Ko0qJ8u7NHQ)
* [1915. Number of Wonderful Substrings](https://youtu.be/QiiK-his0IQ)
* [1658. Minimum Operations to Reduce X to Zero](https://youtu.be/eZ-41oRyTKA)
* [1590. Make Sum Divisible by P](https://youtu.be/uCJH8rHFIbs)
* [1542. Find Longest Awesome Substring](https://youtu.be/VRIuNWjJWSU)
* [1524.  Number of Sub-arrays With Odd Sum](https://youtu.be/kxqa90g7QcM)
