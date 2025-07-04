# [Binary Search](../topics/binary-search.md)
## Basic
| Problem          | Difficulty |
|------------------|------------|
|[704. Binary Search](../leetcode/704.binary-search.md)|Easy|
|[374. Guess Number Higher or Lower](../leetcode/374.guess-number-higher-or-lower.md)|Easy|

> * https://leetcode.com/problems/longest-subsequence-with-limited-sum/description/ 1387
> * https://leetcode.com/problems/range-frequency-queries/description/ 1702
> * https://leetcode.com/problems/most-beautiful-item-for-each-query/description/ 1724
> * https://leetcode.com/problems/minimum-absolute-sum-difference/description/ 1934
> * [SOLVED] https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/description/ 1195

## Search the First/Last Position
> Or find the lower/upper bound of a target.

| Problem          | Difficulty | Note |
|------------------|------------|------|
|[278. First Bad Version](../leetcode/278.first-bad-version.md)|Easy|
|[35. Search Insert Position](../leetcode/35.search-insert-position.md)|Easy| Why returning`left`? |
|[34. Find First and Last Position of Element in Sorted Array](../leetcode/34.find-first-and-last-position-of-element-in-sorted-array.md)|Medium|
|[611. Valid Triangle Number](../leetcode/611.valid-triangle-number.md)|Medium|How to iterate the 3 pointers and update count correctly?|
|[2779. Maximum Beauty of an Array After Applying Operation](../leetcode/2779.maximum-beauty-of-an-array-after-applying-operation.md)|Medium (1638)| Distance is `2 * k`|
|[1855. Maximum Distance Between a Pair of Values](../leetcode/1855.maximum-distance-between-a-pair-of-values.md)|Medium (1514)| [TODO]() |
|[2563. Count the Number of Fair Pairs](../leetcode/2563.count-the-number-of-fair-pairs.md)|Medium (1720)| [TODO]() |

### Two Sequences + Two Pointers
| Problem          | Difficulty | Note |
|------------------|------------|------|
|[2300. Successful Pairs of Spells and Potions](../leetcode/2300.successful-pairs-of-spells-and-potions.md)|Medium (1476)| Sort and binary search. |
|[826. Most Profit Assigning Work](../leetcode/826.most-profit-assigning-work.md)|Medium (1708)| Pre-compute the max profit for each difficulty. |
|[475. Heaters](../leetcode/475.heaters.md)|Medium| Find the closest heater for each house. |

## Search on Value
### Minimum Value
> - "Search the First/Last Position" is the prerequisite of "Search on Value".
> - This also includes "minimize the maximum value". I order these problems by similarity, not by difficulty.
> - **Review Notes**: Please review the lower, upper bound, monotonicity, feasibility check for all problems.

| Problem          | Difficulty | Note |
|------------------|------------|------|
|[875. Koko Eating Bananas](../leetcode/875.koko-eating-bananas.md)|Medium (1766)|Eat more, shorter total time.|
|[1011. Capacity To Ship Packages Within D Days](../leetcode/1011.capacity-to-ship-packages-within-d-days.md)|Medium (1725)| Larger capacity, less ship days. Split logic. | 
|[410. Split Array Largest Sum](../leetcode/410.split-array-largest-sum.md)|Hard| Key intuition and split logic. |
|[1760. Minimum Limit of Balls in a Bag](../leetcode/1760.minimum-limit-of-balls-in-a-bag.md)|Medium (1940)| How to calculate operations count? |
|[2187. Minimum Time to Complete Trips](../leetcode/2187.minimum-time-to-complete-trips.md)|Medium (1640)|
|[2594. Minimum Time to Repair Cars](../leetcode/2594.minimum-time-to-repair-cars.md)|Medium (1915)|
|[1283. Find the Smallest Divisor Given a Threshold](../leetcode/1283.find-the-smallest-divisor-given-a-threshold.md)|Medium (1542)|
|[2064. Minimized Maximum of Products Distributed to Any Store](../leetcode/2064.minimized-maximum-of-products-distributed-to-any-store.md)|Medium (1886)|
|[1870. Minimum Speed to Arrive on Time](../leetcode/1870.minimum-speed-to-arrive-on-time.md)|Medium (1676)|
|[1482. Minimum Number of Days to Make m Bouquets](../leetcode/1482.minimum-number-of-days-to-make-m-bouquets.md)|Medium (1945)|
|[2439. Minimize Maximum of Array](../leetcode/2439.minimize-maximum-of-array.md)|Medium (1965)|
|[2560. House Robber IV](../leetcode/2560.house-robber-iv.md)|Medium (2081)|
|[1631. Path With Minimum Effort](../leetcode/1631.path-with-minimum-effort.md)|Medium (1947)|

> * https://leetcode.com/problems/swim-in-rising-water/description/ 2096, similar to [1631. Path With Minimum Effort](../leetcode/1631.path-with-minimum-effort.md)
> * https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/description/ 2155

### Maximum Value
> This also includes maximize the minimum value.

| Problem          | Difficulty |
|------------------|------------|
|[2226. Maximum Candies Allocated to K Children](../leetcode/2226.maximum-candies-allocated-to-k-children.md)|Medium (1646)|
|[1552. Magnetic Force Between Two Balls](../leetcode/1552.magnetic-force-between-two-balls.md)|Medium (1919)|

> * My contest https://leetcode.com/problems/maximize-score-of-numbers-in-ranges/description/ 1768
> * https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/description/ 1773
> * https://leetcode.com/problems/find-the-maximum-number-of-marked-indices/description/ 1843
> * https://leetcode.com/problems/maximum-number-of-removable-characters/description/ 1912
> * https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/description/ 1929
> * https://leetcode.com/problems/maximum-number-of-alloys/description/ 1981
> * https://leetcode.com/problems/find-the-safest-path-in-a-grid/description/ 2153

### Kth Smallest/Largest Element
| Problem          | Difficulty | Note |
|------------------|------------|------|
|[1539. Kth Missing Positive Number](../leetcode/1539.kth-missing-positive-number.md)|Easy| Key intuition and what to return. |
|[378. Kth Smallest Element in a Sorted Matrix](../leetcode/378.kth-smallest-element-in-a-sorted-matrix.md)|Medium|
|[373. Find K Pairs with Smallest Sums](../leetcode/373.find-k-pairs-with-smallest-sums.md)|Medium|

> Some can be solved by heap. Consider to merge this section with heap problems.
> * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/ h
> * https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/
> * https://leetcode.com/problems/nth-magical-number/description/ 1897
> * https://leetcode.com/problems/ugly-number-iii/description/ 2039

## Search in Matrix
| Problem          | Difficulty | Note |
|------------------|------------|------|
|[74. Search a 2D Matrix](../leetcode/74.search-a-2d-matrix.md)|Medium| Key difference between 240. |
|[1351. Count Negative Numbers in a Sorted Matrix](../leetcode/1351.count-negative-numbers-in-a-sorted-matrix.md)|Easy| Z-shape iteration. |
|[240. Search a 2D Matrix II](../leetcode/240.search-a-2d-matrix-ii.md)|Medium|

> * https://leetcode.com/problems/find-a-peak-element-ii/

## Design
| Problem          | Difficulty |
|------------------|------------|
|[729. My Calendar I](../leetcode/729.my-calendar-i.md)|Medium|
|[981. Time Based Key-Value Store](../leetcode/981.time-based-key-value-store.md)|Medium|
|[1146. Snapshot Array](../leetcode/1146.snapshot-array.md)|Medium (1770)|

## Rotated Array
| Problem          | Difficulty |
|------------------|------------|
|[33. Search in Rotated Sorted Array](../leetcode/33.search-in-rotated-sorted-array.md)|Medium|
|[153. Find Minimum in Rotated Sorted Array](../leetcode/153.find-minimum-in-rotated-sorted-array.md)|Medium|

> * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/ 8k m
> * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/ h


## Other
| Problem          | Difficulty |
|------------------|------------|
|[69. Sqrt(x)](../leetcode/69.sqrt(x).md)|Easy|
|[633. Sum of Square Numbers](../leetcode/633.sum-of-square-numbers.md)|Medium|
|[162. Find Peak Element](../leetcode/162.find-peak-element.md)|Medium|
|[1095. Find in Mountain Array](../leetcode/1095.find-in-mountain-array.md)|Hard (1827)|
|[287. Find the Duplicate Number](../leetcode/287.find-the-duplicate-number.md)|Medium|
|[540. Single Element in a Sorted Array](../leetcode/540.single-element-in-a-sorted-array.md)|Medium|

> * https://leetcode.com/problems/peak-index-in-a-mountain-array/description/ m
> * https://leetcode.com/problems/median-of-two-sorted-arrays/description/

## Explanation
* [34. Find First and Last Position of Element in Sorted Array](https://www.youtube.com/watch?v=sX5IbSSNKXI)
* [475. Heaters](https://www.youtube.com/watch?v=25LSSsAGLDw)
* [875. Koko Eating Bananas](https://www.youtube.com/watch?v=yfWVWbi9pts)
* [1011. Capacity To Ship Packages Within D Days](https://www.youtube.com/watch?v=-F2ysRiSTvk)
* [1482. Minimum Number of Days to Make m Bouquets](https://www.youtube.com/watch?v=D_Pq9SqEwsc)
* [1631. Path With Minimum Effort](https://www.youtube.com/watch?v=vIJ8ue4pQtw)
* [2560. House Robber IV](https://www.youtube.com/watch?v=_-nBUCSeU98)
* [2226. Maximum Candies Allocated to K Children](https://www.youtube.com/watch?v=TBTvnyMLgng)
* [410. Split Array Largest Sum](https://www.youtube.com/watch?v=PBvgA3sV5Zs)
* [2064. Minimized Maximum of Products Distributed to Any Store](https://www.youtube.com/watch?v=0Lyu_B_Ao7k)
* [1552. Magnetic Force Between Two Balls](https://www.youtube.com/watch?v=6oSU44kkV-U)
* [240. Search a 2D Matrix II](https://www.youtube.com/watch?v=-IzCjqVsjZw)
* [378. Kth Smallest Element in a Sorted Matrix](https://www.youtube.com/watch?v=JJUv4DDLSB4)
* [729. My Calendar I](https://www.youtube.com/watch?v=g30lRE3ASiA)
* [1146. Snapshot Array](https://github.com/wisdompeak/LeetCode/tree/master/Design/1146.Snapshot-Array)
* [162. Find Peak Element](https://www.youtube.com/watch?v=esY2RWhmZ74)
* [287. Find the Duplicate Number](https://www.youtube.com/watch?v=86co28GuZ5U)
* [33. Search in Rotated Sorted Array](https://github.com/wisdompeak/LeetCode/tree/master/Binary_Search/033.Search-in-Rotated-Sorted-Array)
* [153. Find Minimum in Rotated Sorted Array](https://www.youtube.com/watch?v=JxoTDj-7tmo)