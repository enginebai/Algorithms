# Key Patterns and Approaches for Two Pointers Problems

Based on the problems in `problems/two-pointers-problems.md`, here is a summary of the key patterns and approaches for solving two pointers problems.

Two pointers is a powerful technique that uses two pointers moving through data structures to solve problems efficiently. It often reduces time complexity from O(n²) to O(n) by eliminating the need for nested loops.

### 1. Opposite Direction Pointers (Left/Right)

Two pointers start from opposite ends and move toward each other.

#### 1.1 Target Sum Problems
*   **Core Idea:** Use sorted array property to find pairs with specific sum.
*   **Key Framework:**
    ```python
    def two_sum(nums, target):
        left, right = 0, len(nums) - 1
        while left < right:
            current_sum = nums[left] + nums[right]
            if current_sum == target:
                return [left, right]
            elif current_sum < target:
                left += 1  # Need larger sum
            else:
                right -= 1  # Need smaller sum
    ```
*   **Examples:** [167. Two Sum II - Input Array Is Sorted](../leetcode/167.two-sum-ii-input-array-is-sorted.md), [15. 3Sum](../leetcode/15.3sum.md), [16. 3Sum Closest](../leetcode/16.3sum-closest.md), [1679. Max Number of K-Sum Pairs](../leetcode/1679.max-number-of-k-sum-pairs.md).

#### 1.2 Optimization Problems
*   **Core Idea:** Move pointers based on optimization criteria to find optimal solution.
*   **Key Applications:**
    *   Container with most water (maximize area)
    *   Minimize difference between elements
    *   Balance or equalize arrays
*   **Examples:** [11. Container With Most Water](../leetcode/11.container-with-most-water.md), [1775. Equal Sum Arrays With Minimum Number of Operations](../leetcode/1775.equal-sum-arrays-with-minimum-number-of-operations.md).

#### 1.3 Palindrome and Symmetry
*   **Core Idea:** Check symmetry by comparing elements from both ends moving inward.
*   **Key Approach:**
    *   Start from ends, compare elements
    *   Move inward while elements match
    *   Handle odd/even length differences
*   **Examples:** Palindrome checking, symmetric string validation.

### 2. Same Direction Pointers (Read/Write)

Two pointers move in the same direction, typically one for reading and one for writing.

#### 2.1 In-Place Array Modification
*   **Core Idea:** Use read pointer to scan elements and write pointer to build result array.
*   **Key Framework:**
    ```python
    def remove_elements(nums, condition):
        write = 0
        for read in range(len(nums)):
            if should_keep(nums[read]):
                nums[write] = nums[read]
                write += 1
        return write  # New length
    ```
*   **Examples:** [27. Remove Element](../leetcode/27.remove-element.md), [283. Move Zeroes](../leetcode/283.move-zeros.md), [26. Remove Duplicates from Sorted Array](../leetcode/26.remove-duplicates-from-sorted-array.md).

#### 2.2 Duplicate Removal
*   **Core Idea:** Remove duplicates while maintaining relative order.
*   **Key Patterns:**
    *   For sorted arrays: compare with previous element
    *   For allowing k duplicates: compare with element at position `write-k`
*   **Examples:** [26. Remove Duplicates from Sorted Array](../leetcode/26.remove-duplicates-from-sorted-array.md), [80. Remove Duplicates from Sorted Array II](../leetcode/80.remove-duplicates-from-sorted-array-ii.md).

#### 2.3 Partitioning
*   **Core Idea:** Partition array based on some criteria (even/odd, positive/negative).
*   **Key Approach:**
    *   Write pointer tracks next position for target elements
    *   Read pointer scans for elements to move
*   **Examples:** [905. Sort Array By Parity](../leetcode/905.sort-array-by-parity.md), [922. Sort Array By Parity II](../leetcode/922.sort-array-by-parity-ii.md).

### 3. Multiple Sequence Matching

Use pointers to traverse and compare multiple sequences simultaneously.

#### 3.1 Merge Operations
*   **Core Idea:** Merge sorted sequences by comparing current elements.
*   **Key Framework:**
    ```python
    def merge_sorted(arr1, arr2):
        i, j = 0, 0
        result = []
        while i < len(arr1) and j < len(arr2):
            if arr1[i] <= arr2[j]:
                result.append(arr1[i])
                i += 1
            else:
                result.append(arr2[j])
                j += 1
        # Add remaining elements
        result.extend(arr1[i:])
        result.extend(arr2[j:])
        return result
    ```
*   **Examples:** [88. Merge Sorted Array](../leetcode/88.merge-sorted-array.md), [350. Intersection of Two Arrays II](../leetcode/350.intersection-of-two-arrays-ii.md).

#### 3.2 Subsequence Matching
*   **Core Idea:** Check if one sequence is a subsequence of another.
*   **Key Approach:**
    *   One pointer for each sequence
    *   Advance target pointer only when characters match
    *   Success when target pointer reaches end
*   **Examples:** [392. Is Subsequence](../leetcode/392.is-subsequence.md).

#### 3.3 String/Sequence Comparison
*   **Core Idea:** Compare sequences with special rules or transformations.
*   **Key Applications:**
    *   Handle backspaces in strings
    *   Compare with skip rules or constraints
*   **Examples:** [844. Backspace String Compare](../leetcode/844.backspace-string-compare.md), [925. Long Pressed Name](../leetcode/925.long-pressed-name.md).

### 4. Fast-Slow Pointers (Tortoise and Hare)

One pointer moves faster than the other, useful for cycle detection and finding middle elements.

#### 4.1 Cycle Detection
*   **Core Idea:** If there's a cycle, fast pointer will eventually meet slow pointer.
*   **Key Framework:**
    ```python
    def has_cycle(head):
        slow = fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                return True
        return False
    ```
*   **Applications:** Linked list cycle detection, number cycles.
*   **Examples:** [141. Linked List Cycle](../leetcode/141.linked-list-cycle.md), [202. Happy Number](../leetcode/202.happy-number.md).

#### 4.2 Finding Middle Element
*   **Core Idea:** When fast pointer reaches end, slow pointer is at middle.
*   **Key Property:** Fast moves 2 steps, slow moves 1 step.
*   **Applications:** Linked list middle, array middle for certain operations.

### 5. Sliding Window with Two Pointers

Maintain a window defined by two pointers that expand and contract based on conditions.

*   **Core Idea:** Expand right boundary to include more elements, contract left boundary when conditions are violated.
*   **Key Applications:**
    *   Longest/shortest subarray problems
    *   Count subarrays with properties
*   **Note:** This overlaps with sliding window techniques but uses two-pointer implementation.

### 6. Multiple Pointers (3+ Pointers)

Use three or more pointers for problems involving multiple elements or constraints.

#### 6.1 Three Sum Problems
*   **Core Idea:** Fix one element and use two pointers on remaining sorted array.
*   **Key Approach:**
    *   Outer loop fixes first element
    *   Inner two pointers find pairs that sum to complement
    *   Handle duplicates carefully
*   **Examples:** [15. 3Sum](../leetcode/15.3sum.md), [611. Valid Triangle Number](../leetcode/611.valid-triangle-number.md).

#### 6.2 Multiple Constraint Problems
*   **Core Idea:** Track multiple positions or boundaries simultaneously.
*   **Applications:** Range problems, multi-way comparisons.

### 7. Grouped Processing (分組循環)

Process consecutive groups of similar elements efficiently.

*   **Core Idea:** Identify and process consecutive elements with same property.
*   **Key Framework:**
    ```python
    def process_groups(arr):
        i = 0
        while i < len(arr):
            j = i
            # Find end of current group
            while j < len(arr) and same_group(arr[i], arr[j]):
                j += 1
            # Process group arr[i:j]
            process_group(arr[i:j])
            i = j
    ```
*   **Examples:** [1446. Consecutive Characters](../leetcode/1446.consecutive-characters.md), [228. Summary Ranges](../leetcode/228.summary-ranges.md), [845. Longest Mountain in Array](../leetcode/845.longest-mountain-in-array.md).

---

### Key Decision Framework

1. **What's the Array Property?**
   - Sorted → Likely opposite direction pointers
   - Unsorted → Likely same direction or fast-slow pointers

2. **What's the Goal?**
   - Find pairs/triplets → Opposite direction after sorting
   - Modify in-place → Same direction (read/write)
   - Detect cycles → Fast-slow pointers
   - Compare sequences → Multiple sequence pointers

3. **What's the Constraint?**
   - Target sum → Use two pointers on sorted array
   - Remove/keep elements → Read/write pointers
   - Optimize metric → Move pointers based on improvement

### Common Two Pointers Patterns

| Pattern | When to Use | Key Insight |
|---------|------------|-------------|
| Left/Right | Sorted array, target sum | Move based on comparison with target |
| Read/Write | In-place modification | Write pointer builds result, read scans |
| Fast/Slow | Cycle detection, middle finding | Speed difference reveals structure |
| Multi-sequence | Merge, intersection | Compare current elements, advance appropriate pointer |
| Sliding window | Subarray problems | Expand/contract window based on validity |

### Common Pitfalls and Tips

1. **Pointer Initialization:** Choose correct starting positions (0 vs len-1, same vs different).

2. **Boundary Conditions:** Handle array bounds carefully, especially when moving multiple pointers.

3. **Duplicate Handling:** For problems like 3Sum, skip duplicates to avoid duplicate results.

4. **State Management:** Keep track of what each pointer represents and when to move them.

5. **Optimization Proof:** For optimization problems, understand why the greedy pointer movement is correct.

### Summary of Key Approaches

1. **Identify the Pattern:** Determine which two-pointer pattern fits the problem structure.

2. **Choose Pointer Movement Rules:** Define when and how to move each pointer.

3. **Handle Edge Cases:** Empty arrays, single elements, equal elements require special attention.

4. **Optimize for Constraints:** Use sorting when beneficial, consider space-time tradeoffs.

5. **Practice Common Templates:** Master the standard templates for each pattern type.

6. **Prove Correctness:** Understand why the pointer movement strategy finds the optimal solution.

Two pointers is one of the most versatile techniques in competitive programming. It transforms many quadratic algorithms into linear ones and provides elegant solutions to complex problems. Success comes from recognizing the right pattern and implementing pointer movement logic correctly.