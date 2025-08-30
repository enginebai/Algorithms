# Key Patterns and Approaches for Heap Problems

Based on the problems in `problems/heap-problems.md`, here is a summary of the key patterns and approaches for solving heap problems.

Heaps are complete binary trees that maintain a heap property (min-heap: parent ≤ children, max-heap: parent ≥ children). They provide O(log n) insertion/deletion and O(1) access to the minimum/maximum element, making them ideal for priority-based algorithms.

### 1. Basic Heap Operations

Fundamental use cases that directly leverage heap properties.

#### 1.1 Priority Processing
*   **Core Idea:** Process elements in order of priority (highest/lowest first).
*   **Key Applications:**
    *   Simulation problems where events have priorities
    *   Resource allocation with priority constraints
    *   Greedy algorithms requiring priority-based decisions
*   **Examples:** [1046. Last Stone Weight](../leetcode/1046.last-stone-weight.md) (always process heaviest stones), [2336. Smallest Number in Infinite Set](../leetcode/2336.smallest-number-in-infinite-set.md) (always return smallest available).

#### 1.2 Dynamic Extremes
*   **Core Idea:** Maintain access to minimum/maximum elements as data changes dynamically.
*   **Key Approach:**
    *   Use min-heap to track smallest elements.
    *   Use max-heap to track largest elements.
    *   Handle insertions and deletions while maintaining heap property.
*   **Examples:** [1845. Seat Reservation Manager](../leetcode/1845.seat-reservation-manager.md) (track smallest available seat), [703. Kth Largest Element in a Stream](../leetcode/703.kth-largest-element-in-a-stream.md).

### 2. Top-K Problems

Find the K largest/smallest elements from a dataset.

#### 2.1 K Largest/Smallest Elements
*   **Core Idea:** Use a min-heap of size K to track K largest elements (or max-heap of size K for K smallest).
*   **Key Approach:**
    *   For K largest: maintain min-heap of size K
    *   If new element > heap top, replace top with new element
    *   Final heap contains K largest elements
*   **Time Complexity:** O(n log k) for n elements
*   **Examples:** [215. Kth Largest Element in an Array](../leetcode/215.kth-largest-element-in-an-array.md), [347. Top K Frequent Elements](../leetcode/347.top-k-frequent-elements.md), [973. K Closest Points to Origin](../leetcode/973.k-closest-points-to-origin.md).

#### 2.2 Frequency-Based Top-K
*   **Core Idea:** Find top K elements based on frequency or custom scoring.
*   **Key Approach:**
    *   Count frequencies using hash map
    *   Use heap to find top K based on frequency/score
    *   Consider bucket sort for frequency-only problems
*   **Examples:** [347. Top K Frequent Elements](../leetcode/347.top-k-frequent-elements.md), [692. Top K Frequent Words](../leetcode/692.top-k-frequent-words.md), [451. Sort Characters By Frequency](../leetcode/451.sort-charaters-by-frequency.md).

### 3. Merge K Sorted Sequences

Efficiently merge multiple sorted sequences using heap coordination.

*   **Core Idea:** Use min-heap to always process the smallest unprocessed element across all sequences.
*   **Key Approach:**
    *   Initialize heap with first element from each sequence
    *   Extract minimum, add to result, and add next element from same sequence
    *   Continue until all elements processed
*   **Time Complexity:** O(n log k) for n total elements and k sequences
*   **Examples:** [23. Merge k Sorted Lists](../leetcode/23.merge-k-sorted-lists.md), [264. Ugly Number II](../leetcode/264.ugly-number-ii.md), [373. Find K Pairs with Smallest Sums](../leetcode/373.find-k-pairs-with-smallest-sums.md).

### 4. Rearrangement with Constraints

Use heaps to guide the rearrangement process based on frequency or priority.

#### 4.1 Character/Task Rearrangement
*   **Core Idea:** Use max-heap to prioritize most frequent elements and avoid conflicts.
*   **Key Approach:**
    *   Count frequencies and use max-heap
    *   Always use most frequent available element
    *   Handle cooldown periods or adjacency constraints
    *   Use temporary storage for recently used elements
*   **Examples:** [767. Reorganize String](../leetcode/767.reorganize-string.md), [621. Task Scheduler](../leetcode/621.task-scheduler.md), [1405. Longest Happy String](../leetcode/1405.longest-happy-string.md).

### 5. Event Simulation and Scheduling

Simulate time-based events or scheduling problems using heaps.

*   **Core Idea:** Use heap to process events in chronological order or by priority.
*   **Key Applications:**
    *   Job scheduling with deadlines or priorities
    *   Resource allocation over time
    *   Simulation of time-based processes
*   **Examples:** [1834. Single-Threaded CPU](../leetcode/1834.single-threaded-cpu.md) (process jobs by arrival then by processing time), [502. IPO](../leetcode/502.ipo.md) (select projects by profitability).

### 6. Two-Heap Pattern

Use two heaps together to maintain median or split data optimally.

#### 6.1 Median Maintenance
*   **Core Idea:** Use max-heap for smaller half and min-heap for larger half.
*   **Key Approach:**
    *   Max-heap stores smaller half of numbers
    *   Min-heap stores larger half of numbers
    *   Keep heap sizes balanced (difference ≤ 1)
    *   Median is top of larger heap or average of both tops
*   **Examples:** [295. Find Median from Data Stream](../leetcode/295.find-median-from-data-stream.md).

### 7. Retrospective/Hindsight Problems (事後諸葛)

Make optimal decisions by looking back at available choices when needed.

*   **Core Idea:** When reaching a decision point, choose the best option from previously seen choices.
*   **Key Approach:**
    *   Process elements in order
    *   When a decision point is reached, use heap to select optimal choice from past options
    *   This simulates having perfect hindsight
*   **Examples:** [1642. Furthest Building You Can Reach](../leetcode/1642.furthest-building-you-can-reach.md) (use ladders on largest gaps), [871. Minimum Number of Refueling Stops](../leetcode/871.minimum-number-of-refueling-stops.md) (refuel at best stations when needed).

### 8. Lazy Deletion Pattern

Handle deletions efficiently in heaps when elements are modified externally.

*   **Core Idea:** Mark elements as deleted rather than removing them immediately.
*   **Key Approach:**
    *   Maintain separate data structure as source of truth
    *   Heap may contain stale entries
    *   Skip stale entries when extracting from heap
    *   Clean up periodically if needed
*   **Examples:** [3408. Design Task Manager](../leetcode/3408.design-task-manager.md) (tasks can be modified/removed between heap operations).

---

### Key Decision Framework

1. **Do you need the minimum/maximum element repeatedly?** → Basic heap operations

2. **Do you need the top K elements?** → K-sized heap pattern

3. **Are you merging sorted sequences?** → Multi-way merge with heap

4. **Do you need median or split data?** → Two-heap pattern

5. **Are you scheduling or simulating events?** → Event-driven heap usage

6. **Do you need to rearrange with constraints?** → Frequency-based heap rearrangement

### Common Heap Implementation Notes

1. **Language-Specific:**
   - Python: `heapq` (min-heap only, use negative values for max-heap)
   - Java: `PriorityQueue` (min-heap default, custom comparator for max-heap)
   - C++: `priority_queue` (max-heap default, greater<> for min-heap)

2. **Custom Comparators:**
   - Define custom comparison for complex objects
   - Be consistent with comparison logic

3. **Heap vs TreeSet/TreeMap:**
   - Heap: When you only need min/max access
   - TreeSet/TreeMap: When you need ordered iteration or range queries

### Summary of Key Approaches

1. **Identify the Priority:** What determines the order of processing?

2. **Choose Heap Type:** Min-heap for smallest first, max-heap for largest first.

3. **Consider K-limited Heaps:** For top-K problems, maintain heap of size K.

4. **Handle Ties Carefully:** Define clear tie-breaking rules for consistent behavior.

5. **Combine with Other Patterns:** Heaps work well with greedy algorithms, dynamic programming, and graph algorithms.

6. **Optimize for Problem Constraints:** Sometimes simple sorting is better than heap for small datasets.

Heaps are powerful data structures for maintaining ordered access to extremal elements. They're essential for priority-based algorithms, scheduling problems, and any situation where you need efficient access to minimum/maximum elements in a dynamic dataset.