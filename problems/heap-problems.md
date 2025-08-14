# [Heap Problems](../topics/heap.md)

## Basic
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[1046. Last Stone Weight](../leetcode/1046.last-stone-weight.md)|Easy (1172)|
|[1337. The K Weakest Rows in a Matrix](../leetcode/1337.the-k-weakest-rows-in-a-matrix.md)|Easy|
|[2336. Smallest Number in Infinite Set](../leetcode/2336.smallest-number-in-infinite-set.md)|Easy|
|**[1845. Seat Reservation Manager](../leetcode/1845.seat-reservation-manager.md)|Medium| Here is optimal heap solution. |
|[2462. Total Cost to Hire K Workers](../leetcode/2462.total-cost-to-hire-k-workers.md)|Medium (1763)|
|**[1834. Single-Threaded CPU](../leetcode/1834.single-threaded-cpu.md)|Medium (1797)| Sort by `enqueueTime`, heap tracks by `processingTime`. |
|[502. IPO](../leetcode/502.ipo.md)|Hard| Push affordable projects, heap tracks by `profit`. |

> * https://leetcode.com/problems/maximum-product-after-k-increments/description/ 1685
> * https://leetcode.com/problems/minimum-number-of-seconds-to-make-mountain-height-zero/description/ 1695
> * https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/description/ 1695
> * https://leetcode.com/problems/design-task-manager/description/ 1807
> * https://leetcode.com/problems/maximum-average-pass-ratio/description/ 1817
> * https://leetcode.com/problems/meeting-rooms-iii/ 2093
>
> * Solved: https://leetcode.com/problems/take-gifts-from-the-richest-pile/description/ 1276 // A very basic heap problem. `PriorityQueue(reverseOrder<Int>())` is used to create a max heap. 2024/10/28
> * Solved: https://leetcode.com/problems/maximal-score-after-applying-k-operations/description/ 1386 // A very basic heap problem. 2024/10/29
> * Solved: https://leetcode.com/problems/remove-stones-to-minimize-the-total/description/ 1418 // A very basic heap problem. 2024/10/30
> * Solved: https://leetcode.com/problems/seat-reservation-manager/description/ 1428 // Very straightforward problem. 2024/11/02

## Advanced
| Problem          | Difficulty |
|------------------|------------|
|[857. Minimum Cost to Hire K Workers](../leetcode/857.minimum-cost-to-hire-k-workers.md)|Hard (2259)|

> * https://leetcode.com/problems/construct-target-array-with-multiple-sums/description/ 2014
> * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/ 2014
> * https://leetcode.com/problems/maximum-subsequence-score/description/ 2056
> * https://leetcode.com/problems/maximum-performance-of-a-team/description/ 2091

## Top K
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[703. Kth Largest Element in a Stream](../leetcode/703.kth-largest-element-in-a-stream.md)|Easy| Streaming = Dynamic tracking. |
|**[215. Kth Largest Element in an Array](../leetcode/215.kth-largest-element-in-an-array.md)|Medium| Heap, quick select. |
|**[347. Top K Frequent Elements](../leetcode/347.top-k-frequent-elements.md)|Medium| Bucket sort. |
|[692. Top K Frequent Words](../leetcode/692.top-k-frequent-words.md)|Medium|
|[451. Sort Characters By Frequency](../leetcode/451.sort-charaters-by-frequency.md)|Medium|
|[973. K Closest Points to Origin](../leetcode/973.k-closest-points-to-origin.md)|Medium|
|[355. Design Twitter](../leetcode/355.design-twitter.md)|Medium|

> * My contest https://leetcode.com/problems/choose-k-elements-with-maximum-sum/

## Rearrangement
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|**[767. Reorganize String](../leetcode/767.reorganize-string.md)|Medium (1681)| Heap and greedy way (0, 2, 4, ... first, then 1, 3, 5, ... second). |
|[984. String Without AAA or BBB](../leetcode/984.string-without-aaa-or-bbb.md)|Medium (1474)| `aab` or `bba`, then `ab` or `ba`. |
|[1405. Longest Happy String](../leetcode/1405.longest-happy-string.md)|Medium (1820)|
|**[621. Task Scheduler](../leetcode/621.task-scheduler.md)|Medium|

> * https://leetcode.com/problems/distant-barcodes/description/ 1701
> * https://leetcode.com/problems/maximum-number-of-weeks-for-which-you-can-work/description/ 1803

## Merge K Sorted
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[23. Merge k Sorted Lists](../leetcode/23.merge-k-sorted-lists.md)|Hard| One-by-one, divide and conquer, heap. |
|[264. Ugly Number II](../leetcode/264.ugly-number-ii.md)|Medium| Push multiples `(2, 3, 5)`, dedupe by set. |
|[313. Super Ugly Number](../leetcode/313.super-ugly-number.md)|Medium|
|**[373. Find K Pairs with Smallest Sums](../leetcode/373.find-k-pairs-with-smallest-sums.md)|Medium| 
|[1439. Find the Kth Smallest Sum of a Matrix With Sorted Rows](../leetcode/1439.find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows.md)|Hard (2133)|
|[378. Kth Smallest Element in a Sorted Matrix](../leetcode/378.kth-smallest-element-in-a-sorted-matrix.md)|Medium|

> * https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/description/ 2133
> * https://leetcode.com/problems/k-th-smallest-prime-fraction/description/ 2168
> * https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/ h
> * **https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/description/** h
> * ~~https://leetcode.com/problems/minimize-deviation-in-array/description/~~ 2533

## 事後諸葛
| Problem          | Difficulty |
|------------------|------------|
|[1642. Furthest Building You Can Reach](../leetcode/1642.furthest-building-you-can-reach.md)|Medium (1962)|
|[871. Minimum Number of Refueling Stops](../leetcode/871.minimum-number-of-refueling-stops.md)|Hard (2074)|

> * **https://leetcode.com/problems/course-schedule-iii/description/** h

## Two Heaps
| Problem          | Difficulty |
|------------------|------------|
|**[295. Find Median from Data Stream](../leetcode/295.find-median-from-data-stream.md)|Hard|

> * **https://leetcode.com/problems/kth-largest-element-in-a-stream/** e
> * **https://leetcode.com/problems/sliding-window-median/description/** h

## Lazy Deletion
| Problem          | Difficulty |
|------------------|------------|
|**[3408. Design Task Manager](../leetcode/3408.design-task-manager.md)|Medium (1806)| Hash table as source of truth. Push all update, skip stale while pop. |

> * https://leetcode.com/problems/design-a-number-container-system/description/ 1540
> * https://leetcode.com/problems/design-a-food-rating-system/description/ 1781
> * https://leetcode.com/problems/most-frequent-ids/description/ 1793
> * **https://leetcode.com/problems/stock-price-fluctuation/description/** 1831