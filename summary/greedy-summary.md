# Key Patterns and Approaches for Greedy Problems

Based on the problems in `problems/greedy-problems.md`, here is a summary of the key patterns and approaches for solving greedy problems.

Greedy algorithms make locally optimal choices at each step, hoping to achieve a globally optimal solution. The key insight is identifying when local optimization leads to global optimality.

### 1. Optimization from Extremes

Start with minimum or maximum values and make incremental improvements.

#### 1.1 From Minimum or Maximum
*   **Core Idea:** Begin with extreme values (min/max) and adjust incrementally to satisfy constraints.
*   **Key Approach:**
    *   Sort the input to identify extremes.
    *   Process elements from smallest to largest (or vice versa).
    *   Make the minimal necessary adjustment at each step.
*   **Examples:** [945. Minimum Increment to Make Array Unique](../leetcode/945.minimum-increment-to-make-array-unique.md) (increment minimally), [1647. Minimum Deletions to Make Character Frequencies Unique](../leetcode/1647.minimum-deletions-to-make-character-frequencies-unique.md) (reduce frequencies greedily).

### 2. Pairing Strategies

Optimally pair elements to maximize/minimize some objective function.

#### 2.1 Single Sequence Pairing
*   **Core Idea:** Pair elements within the same array to optimize the result.
*   **Key Approaches:**
    *   **Sort + Greedy:** Sort array and pair adjacent or extreme elements.
    *   **Two Pointers:** Use two pointers from ends moving inward.
*   **Examples:** [561. Array Partition](../leetcode/561.array-partition-i.md) (pair adjacent after sorting), [881. Boats to Save People](../leetcode/881.boats-to-save-people.md) (pair lightest with heaviest).

#### 2.2 Double Sequence Pairing
*   **Core Idea:** Optimally match elements from two different sequences.
*   **Key Approach:**
    *   Sort both sequences appropriately.
    *   Use greedy matching strategy (often smallest with smallest or smallest with largest).
*   **Examples:** [826. Most Profit Assigning Work](../leetcode/826.most-profit-assigning-work.md) (match workers with suitable jobs).

### 3. Directional Processing

Process elements from one direction (left or right) making optimal local decisions.

#### 3.1 From Left or Right
*   **Core Idea:** Scan from one direction and make the best decision at each position based on local information.
*   **Key Approach:**
    *   Maintain necessary state while scanning.
    *   Make greedy decisions that can't be improved later.
*   **Examples:** [605. Can Place Flowers](../leetcode/605.can-place-flowers.md) (place flowers when possible), [1529. Minimum Suffix Flips](../leetcode/1529.minimum-suffix-flips.md) (flip when necessary).

### 4. Rearrangement Problems

Reorganize elements to satisfy constraints while optimizing some metric.

#### 4.1 Character/Task Rearrangement
*   **Core Idea:** Rearrange characters or tasks to satisfy distance constraints while maximizing length.
*   **Key Approach:**
    *   Use frequency counts and priority queues/heaps.
    *   Always use the most frequent available element.
    *   Handle cooldown periods or distance constraints.
*   **Examples:** [767. Reorganize String](../leetcode/767.reorganize-string.md), [621. Task Scheduler](../leetcode/621.task-scheduler.md), [1405. Longest Happy String](../leetcode/1405.longest-happy-string.md).

### 5. Interval Problems

Handle overlapping intervals by making optimal choices about which intervals to keep or merge.

#### 5.1 Non-overlapping Intervals
*   **Core Idea:** Select maximum number of non-overlapping intervals.
*   **Key Approach:**
    *   Sort intervals by end time.
    *   Greedily select intervals that end earliest and don't overlap with selected ones.
*   **Examples:** [435. Non-overlapping Intervals](../leetcode/435.non-overlapping-intervals.md), [452. Minimum Number of Arrows to Burst Balloons](../leetcode/452.minimum-number-of-arrows-to-burst-balloons.md).

#### 5.2 Interval Coverage
*   **Core Idea:** Use minimum number of intervals to cover a target range.
*   **Key Approach:**
    *   Sort intervals by start time.
    *   Greedily select interval that extends coverage the most.
*   **Examples:** [45. Jump Game II](../leetcode/45.jump-game-ii.md), [1024. Video Stitching](../leetcode/1024.video-stitching.md).

### 6. Exchange Argument

Prove optimality by showing that any other solution can be improved by exchanging elements.

*   **Core Idea:** If you can always improve a non-greedy solution by swapping elements, then greedy is optimal.
*   **Key Approach:**
    *   Sort elements by some criterion.
    *   Process in sorted order.
    *   Prove that processing out of order can be improved by swapping.
*   **Common Applications:** Activity selection, job scheduling, fractional knapsack.

### 7. Mathematical Greedy

Use mathematical insights to make optimal choices.

#### 7.1 Majority/Balance Problems
*   **Core Idea:** Use mathematical properties like majority vote or balance to make decisions.
*   **Examples:** [134. Gas Station](../leetcode/134.gas-station.md) (surplus/deficit balance), [135. Candy](../leetcode/135.candy.md) (local minima approach).

### 8. Construction Problems

Build solutions incrementally by making the best choice at each step.

*   **Core Idea:** Construct the solution piece by piece, making optimal local decisions.
*   **Key Approach:**
    *   Define what makes a choice "best" at each step.
    *   Ensure local decisions don't prevent global optimality.
*   **Examples:** Building strings, arranging numbers, or constructing sequences.

---

### Key Greedy Principles

1. **Greedy Choice Property:** A globally optimal solution can be reached by making locally optimal choices.

2. **Optimal Substructure:** An optimal solution contains optimal solutions to subproblems.

3. **No Backtracking:** Once a choice is made, it's never reconsidered.

### Common Greedy Strategies

1. **Sort First:** Many greedy problems benefit from sorting the input first.
   - Sort by end time for interval selection
   - Sort by value/weight ratio for fractional knapsack
   - Sort by frequency for character rearrangement

2. **Process Extremes:** Focus on minimum or maximum elements first.

3. **Use Data Structures:** Priority queues, heaps, and sets often support greedy decisions.

4. **Maintain Invariants:** Keep certain properties true throughout the algorithm.

### Proving Greedy Correctness

1. **Greedy Choice Property:** Show that making the greedy choice at each step leads to an optimal solution.

2. **Exchange Argument:** Prove that any optimal solution can be transformed into the greedy solution without losing optimality.

3. **Cut-and-Paste:** Show that if the greedy algorithm doesn't produce an optimal solution, we can modify an optimal solution to match the greedy choice without making it worse.

### Summary of Key Approaches

1. **Identify the Greedy Choice:** What decision should you make at each step?

2. **Prove Correctness:** Either intuitively or formally verify that greedy choices lead to optimal solutions.

3. **Handle Edge Cases:** Consider empty inputs, single elements, and boundary conditions.

4. **Choose Right Data Structures:** Often need sorting, heaps, or sets to support efficient greedy decisions.

5. **Understand the Trade-offs:** Greedy algorithms are fast but don't always work. Know when to use DP instead.

6. **Pattern Recognition:** Many greedy problems fall into standard categories (intervals, pairing, sorting-based).

Greedy problems require strong intuition about when local optimization leads to global optimality. Practice recognizing the patterns and understanding the underlying mathematical principles that make greedy choices work.