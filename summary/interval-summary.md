# Key Patterns and Approaches for Interval Problems

Based on the problems in `problems/interval-problems.md`, here is a summary of the key patterns and approaches for solving interval problems.

Interval problems deal with ranges, time periods, and overlapping segments. They often require sorting, greedy strategies, or sweep line algorithms to handle multiple intervals efficiently.

### 1. Line Sweep Algorithm

Process events chronologically to maintain state across intervals.

#### 1.1 Difference Array (Range Updates)
*   **Core Idea:** Convert range updates into point updates using difference array technique.
*   **Key Approach:**
    *   For range update [l, r] with value v: diff[l] += v, diff[r+1] -= v
    *   Compute prefix sum to get actual values
    *   Efficient for multiple range updates followed by queries
*   **Time Complexity:** O(n + m) for n elements and m updates
*   **Examples:** [1094. Car Pooling](../leetcode/1094.car-pooling.md), [2848. Points That Intersect With Cars](../leetcode/2848.points-that-intersect-with-cars.md), [1893. Check if All the Integers in a Range Are Covered](../leetcode/1893.check-if-all-the-integers-in-a-range-are-covered.md).

#### 1.2 Event-Based Line Sweep
*   **Core Idea:** Create events for interval starts/ends and process them chronologically.
*   **Key Approach:**
    *   Create events: (time, type, value) where type is start/end
    *   Sort events by time
    *   Process events maintaining current state
    *   Handle simultaneous events carefully (starts before ends)
*   **Examples:** [2406. Divide Intervals Into Minimum Number of Groups](../leetcode/2406.divide-intervals-into-minimum-number-of-groups.md) (track maximum overlap).

### 2. Basic Interval Operations

Fundamental operations on individual intervals or simple collections.

#### 2.1 Merge Intervals
*   **Core Idea:** Combine overlapping intervals into non-overlapping ones.
*   **Key Approach:**
    *   Sort intervals by start time
    *   Iterate and merge when current interval overlaps with previous
    *   Two intervals [a,b] and [c,d] overlap if b ≥ c
*   **Examples:** [56. Merge Intervals](../leetcode/56.merge-intervals.md).

#### 2.2 Insert Interval
*   **Core Idea:** Insert a new interval into a sorted list of non-overlapping intervals.
*   **Key Approach:**
    *   Find position where new interval should be inserted
    *   Merge with all overlapping intervals
    *   Handle three phases: before overlap, during overlap, after overlap
*   **Examples:** [57. Insert Interval](../leetcode/57.insert-interval.md).

### 3. Greedy Interval Selection

Select optimal subset of intervals based on greedy criteria.

#### 3.1 Non-overlapping Interval Selection
*   **Core Idea:** Select maximum number of non-overlapping intervals.
*   **Greedy Strategy:** Always choose the interval that ends earliest among available options.
*   **Key Approach:**
    *   Sort intervals by end time
    *   Greedily select intervals that don't overlap with previously selected ones
*   **Correctness:** Earliest ending interval leaves most room for future selections
*   **Examples:** [435. Non-overlapping Intervals](../leetcode/435.non-overlapping-intervals.md), [252. Meeting Rooms](../leetcode/252.meeting-rooms-i.md).

#### 3.2 Interval Point Coverage
*   **Core Idea:** Find minimum number of points to cover all intervals.
*   **Greedy Strategy:** Place points at the right end of intervals when forced to.
*   **Key Approach:**
    *   Sort intervals by end time
    *   Place point at end of first uncovered interval
    *   Skip all intervals covered by this point
*   **Examples:** [452. Minimum Number of Arrows to Burst Balloons](../leetcode/452.minimum-number-of-arrows-to-burst-balloons.md).

#### 3.3 Interval Coverage Problems
*   **Core Idea:** Use minimum number of intervals to cover a target range.
*   **Greedy Strategy:** Among intervals covering current position, choose the one extending furthest.
*   **Key Approach:**
    *   Sort intervals by start position
    *   Greedily extend coverage as far as possible
    *   Jump to next uncovered position
*   **Examples:** [45. Jump Game II](../leetcode/45.jump-game-ii.md), [1024. Video Stitching](../leetcode/1024.video-stitching.md), [1326. Minimum Number of Taps to Open to Water a Garden](../leetcode/1326.minimum-number-of-taps-to-open-to-water-a-garden.md).

### 4. Interval Grouping and Partitioning

Organize intervals into groups based on constraints.

#### 4.1 Minimum Groups (Maximum Overlap)
*   **Core Idea:** Find minimum number of groups such that no two intervals in same group overlap.
*   **Key Insight:** Minimum groups equals maximum overlap at any point.
*   **Key Approaches:**
    *   **Line Sweep:** Count active intervals at each point
    *   **Priority Queue:** Track earliest ending time in each group
*   **Examples:** [253. Meeting Rooms II](../leetcode/253.meeting-rooms-ii.md), [2406. Divide Intervals Into Minimum Number of Groups](../leetcode/2406.divide-intervals-into-minimum-number-of-groups.md).

### 5. Scheduling and Calendar Problems

Handle time-based scheduling with conflict detection.

*   **Core Idea:** Maintain sorted intervals and detect conflicts efficiently.
*   **Key Components:**
    *   Sorted container (TreeMap, sorted list) for efficient insertion/search
    *   Binary search to find potential conflicts
    *   Overlap detection for booking validation
*   **Examples:** [729. My Calendar I](../leetcode/729.my-calendar-i.md) (single booking conflicts).

### 6. Special Interval Properties

Handle intervals with special constraints or properties.

#### 6.1 Fixed-Length Intervals
*   **Core Idea:** All intervals have the same length, enabling special optimizations.
*   **Key Approaches:**
    *   Sliding window techniques
    *   Coordinate compression
    *   Mathematical properties of fixed-length ranges

#### 6.2 Integer vs Continuous Intervals
*   **Important Distinction:**
    *   Integer intervals: [1,3] and [4,5] don't overlap
    *   Continuous intervals: [1,3) and [3,5) don't overlap
    *   Handle boundary conditions correctly based on problem context

### 7. Advanced Interval Techniques

#### 7.1 Coordinate Compression
*   **Core Idea:** Map large coordinate space to smaller discrete space.
*   **When to Use:** When coordinate values are large but number of distinct values is small.
*   **Approach:** Sort unique coordinates, map to indices 0,1,2,...

#### 7.2 Segment Trees for Intervals
*   **Core Idea:** Use segment trees for efficient range queries and updates on intervals.
*   **Applications:** Complex interval queries, dynamic interval updates.

---

### Key Problem Recognition Patterns

1. **Multiple Range Updates → Difference Array**
2. **Maximum Overlap → Line Sweep or Priority Queue**
3. **Merge Overlapping → Sort by start time**
4. **Maximum Non-overlapping → Sort by end time**
5. **Minimum Coverage → Sort by start time, extend greedily**
6. **Scheduling Conflicts → Sorted container with binary search**

### Common Interval Algorithms

1. **Sort by Start Time:** For merging, coverage problems
2. **Sort by End Time:** For non-overlapping selection
3. **Line Sweep:** For tracking state changes over time
4. **Priority Queue:** For managing multiple active intervals
5. **Binary Search:** For finding positions in sorted interval lists

### Edge Cases to Consider

1. **Empty Intervals:** Handle zero-length intervals correctly
2. **Point Intervals:** Intervals where start equals end
3. **Touching vs Overlapping:** [1,2] and [2,3] may or may not overlap depending on problem
4. **Boundary Conditions:** Inclusive vs exclusive endpoints
5. **Single Interval:** Edge case for many algorithms

### Summary of Key Approaches

1. **Understand Interval Semantics:** Inclusive vs exclusive endpoints, discrete vs continuous.

2. **Choose Right Sorting:** Sort by start for merging/coverage, by end for selection.

3. **Master Line Sweep:** Essential for problems involving changes over time.

4. **Use Appropriate Data Structures:** Priority queues for active intervals, sorted containers for scheduling.

5. **Apply Greedy Strategies:** Many interval problems have optimal greedy solutions.

6. **Handle Edge Cases:** Empty inputs, single intervals, touching boundaries.

Interval problems often combine sorting, greedy strategies, and efficient data structures. The key insight is recognizing which interval property (start time, end time, overlap) drives the optimal strategy.