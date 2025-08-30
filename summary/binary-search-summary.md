# Key Patterns and Approaches for Binary Search Problems

Based on the problems in `problems/binary-search-problems.md`, here is a summary of the key patterns and approaches for solving binary search problems.

Binary search is a powerful technique that works on the principle of eliminating half of the search space in each iteration, achieving O(log n) time complexity. It's not just for finding elements in sorted arrays—it can solve optimization problems and work on abstract search spaces.

### 1. Basic Binary Search

The foundation of all binary search problems: finding a target element in a sorted array.

*   **Core Idea:** Compare the middle element with the target and eliminate half of the search space based on the comparison.
*   **Key Framework:**
    ```python
    def binary_search(nums, target):
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = left + (right - left) // 2
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1
        return -1
    ```
*   **Examples:** [704. Binary Search](../leetcode/704.binary-search.md), [374. Guess Number Higher or Lower](../leetcode/374.guess-number-higher-or-lower.md).

### 2. Search First/Last Position (Lower/Upper Bound)

Find the first or last occurrence of a target element, or find where an element would be inserted.

*   **Core Idea:** Modify the basic binary search to continue searching even after finding the target, to locate the boundary positions.
*   **Key Approaches:**
    *   **Lower Bound (First Position):** When `nums[mid] >= target`, move right boundary.
    *   **Upper Bound (Last Position):** When `nums[mid] <= target`, move left boundary.
    *   **Insert Position:** Find the first position where element is greater than or equal to target.
*   **Why Return `left`:** At the end, `left` points to the first position where the condition is satisfied.
*   **Examples:** [35. Search Insert Position](../leetcode/35.search-insert-position.md), [34. Find First and Last Position of Element in Sorted Array](../leetcode/34.find-first-and-last-position-of-element-in-sorted-array.md), [278. First Bad Version](../leetcode/278.first-bad-version.md).

### 3. Search on Answer/Value Space

Instead of searching for an element, search for the optimal value in a range that satisfies certain conditions.

#### 3.1 Minimize the Maximum Value

*   **Core Idea:** Binary search on the answer space to find the minimum possible maximum value that satisfies the constraints.
*   **Key Approach:**
    *   Define the search range: `[minimum_possible_answer, maximum_possible_answer]`.
    *   For each candidate answer, check if it's feasible using a greedy or mathematical approach.
    *   If feasible, try a smaller answer (move right boundary). If not, try a larger answer (move left boundary).
*   **Monotonicity:** If answer `x` works, then any answer `> x` also works.
*   **Examples:** [875. Koko Eating Bananas](../leetcode/875.koko-eating-bananas.md) (minimize eating speed), [1011. Capacity To Ship Packages Within D Days](../leetcode/1011.capacity-to-ship-packages-within-d-days.md) (minimize ship capacity), [410. Split Array Largest Sum](../leetcode/410.split-array-largest-sum.md), [1760. Minimum Limit of Balls in a Bag](../leetcode/1760.minimum-limit-of-balls-in-a-bag.md).

#### 3.2 Maximize the Minimum Value

*   **Core Idea:** Binary search on the answer space to find the maximum possible minimum value that satisfies the constraints.
*   **Key Approach:** Similar to minimizing maximum, but the monotonicity is reversed.
*   **Monotonicity:** If answer `x` doesn't work, then any answer `> x` also doesn't work.
*   **Examples:** [2226. Maximum Candies Allocated to K Children](../leetcode/2226.maximum-candies-allocated-to-k-children.md), [1552. Magnetic Force Between Two Balls](../leetcode/1552.magnetic-force-between-two-balls.md).

### 4. Kth Smallest/Largest Element

Find the kth element in a conceptually sorted sequence without explicitly sorting.

*   **Core Idea:** Binary search on the value range and count how many elements are smaller than or equal to the current value.
*   **Key Approach:**
    *   Define the value range: `[min_possible_value, max_possible_value]`.
    *   For each candidate value, count elements `≤ value`.
    *   Find the first value where `count(≤ value) ≥ k`.
*   **Examples:** [1539. Kth Missing Positive Number](../leetcode/1539.kth-missing-positive-number.md), [378. Kth Smallest Element in a Sorted Matrix](../leetcode/378.kth-smallest-element-in-a-sorted-matrix.md).

### 5. Search in Matrix

Apply binary search techniques to 2D matrices with sorted properties.

*   **Core Idea:** Exploit the sorted properties of the matrix to eliminate regions of the search space.
*   **Key Approaches:**
    *   **Fully Sorted Matrix:** Treat as 1D array and use standard binary search.
    *   **Row and Column Sorted:** Start from top-right or bottom-left corner to eliminate rows/columns.
    *   **Count Elements:** Use the sorted property to count elements efficiently.
*   **Examples:** [74. Search a 2D Matrix](../leetcode/74.search-a-2d-matrix.md), [240. Search a 2D Matrix II](../leetcode/240.search-a-2d-matrix-ii.md), [1351. Count Negative Numbers in a Sorted Matrix](../leetcode/1351.count-negative-numbers-in-a-sorted-matrix.md).

### 6. Rotated Array Problems

Handle arrays that have been rotated, breaking the normal sorted order.

*   **Core Idea:** Despite rotation, one half of the array is always properly sorted. Use this property to determine which half to search.
*   **Key Approach:**
    *   Compare `nums[mid]` with `nums[left]` or `nums[right]` to determine which half is sorted.
    *   If target is in the sorted half, search there. Otherwise, search the other half.
    *   Handle duplicates by incrementing/decrementing boundaries when `nums[left] == nums[mid]`.
*   **Examples:** [33. Search in Rotated Sorted Array](../leetcode/33.search-in-rotated-sorted-array.md), [153. Find Minimum in Rotated Sorted Array](../leetcode/153.find-minimum-in-rotated-sorted-array.md), [81. Search in Rotated Sorted Array II](../leetcode/81.search-in-rotated-sorted-array-ii.md).

### 7. Design Problems

Use binary search as a component in data structure design.

*   **Core Idea:** Maintain sorted data and use binary search for efficient queries.
*   **Key Approaches:**
    *   Maintain a sorted container (TreeMap, sorted list).
    *   Use binary search for insertion, deletion, and range queries.
    *   For time-based problems, search by timestamp.
*   **Examples:** [729. My Calendar I](../leetcode/729.my-calendar-i.md), [981. Time Based Key-Value Store](../leetcode/981.time-based-key-value-store.md), [1146. Snapshot Array](../leetcode/1146.snapshot-array.md).

---

### Key Decision Framework

1. **Identify the Search Space:**
   - Element in array → Basic binary search
   - Position/boundary → First/last position search  
   - Value range → Search on answer space
   - Matrix → Matrix-specific techniques

2. **Check Monotonicity:**
   - Is there a property that changes monotonically across the search space?
   - Can you define a condition that's false before some point and true after?

3. **Design the Feasibility Check:**
   - For "search on answer" problems, implement an efficient way to check if a given answer is feasible.
   - This often involves greedy algorithms or mathematical formulas.

4. **Handle Edge Cases:**
   - Empty arrays, single elements
   - All elements are the same (especially in rotated arrays)
   - Target not found scenarios

### Summary of Key Approaches

1. **Master the Boundaries:** Understand when to use `left <= right` vs `left < right` and why we return `left` in boundary problems.

2. **Think in Terms of Conditions:** Instead of searching for elements, search for the boundary where a condition changes from false to true.

3. **Exploit Monotonicity:** Binary search works when you can eliminate half the search space based on a comparison. Look for monotonic properties.

4. **Design Efficient Checks:** For optimization problems, the feasibility check is often more complex than the binary search itself.

5. **Consider Alternative Approaches:** Sometimes binary search competes with heap-based solutions (like in Kth element problems). Choose based on constraints.

6. **Practice Template Recognition:** Different problem types have standard templates. Recognize the pattern to apply the right template quickly.

Binary search problems often appear more complex than they are. The key insight is recognizing that you're not always searching for an element—sometimes you're searching for a condition boundary or an optimal value in a range.