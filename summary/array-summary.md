# Key Patterns and Approaches for Array Problems

Based on the problems in `problems/array-problems.md`, here is a summary of the key patterns and approaches for solving array-related problems.

Arrays are fundamental data structures that store elements in contiguous memory locations with constant-time random access. They are ideal for problems involving sequential processing, enumeration, and mathematical operations.

### 1. Enumeration Patterns

This is one of the most common approaches for array problems, where you systematically examine elements and maintain running calculations or lookups.

#### 1.1 Enumerate Right, Maintain Left (枚舉右，維護左)

*   **Core Idea:** Fix the right element and use previously seen elements (typically stored in a hash table) to find valid combinations.
*   **Key Approach:**
    *   Iterate through the array from left to right.
    *   For each current element, check if its complement or related value exists in previously seen elements.
    *   Store the current element in a data structure for future lookups.
*   **Examples:** [1512. Number of Good Pairs](../leetcode/1512.number-of-good-pairs.md) (count frequency), [1010. Pairs of Songs With Total Durations Divisible by 60](../leetcode/1010.pairs-of-songs-with-total-durations-divisible-by-60.md) (modular arithmetic), [1679. Max Number of K-Sum Pairs](../leetcode/1679.max-number-of-k-sum-pairs.md) (two sum variant), [1031. Maximum Sum of Two Non-Overlapping Subarrays](../leetcode/1031.maximum-sum-of-two-non-overlapping-subarrays.md) (prefix/suffix optimization).

#### 1.2 Enumerate Middle (枚舉中間)

*   **Core Idea:** Fix the middle element and precompute information about elements to the left and right to make optimal decisions.
*   **Key Approach:**
    *   Precompute prefix and suffix information (like min, max, sum, product).
    *   For each middle position, combine the prefix and suffix information to get the result.
*   **Examples:** [238. Product of Array Except Self](../leetcode/238.product-of-array-except-self.md) (prefix/suffix products), [2909. Minimum Sum of Mountain Triplets II](../leetcode/2909.minimum-sum-of-mountain-triplets-ii.md) (track minimum left/right), [2874. Maximum Value of an Ordered Triplet II](../leetcode/2874.maximum-value-of-an-ordered-triplet-ii.md) (prefix max and maxDiff).

### 2. Movement and Rearrangement

These problems involve reorganizing or moving elements within the array to achieve a desired state.

*   **Core Idea:** Use two pointers, swapping, or careful index manipulation to rearrange elements.
*   **Key Approach:**
    *   Identify the invariant that must be maintained during movement.
    *   Use two pointers: one for reading, one for writing valid elements.
    *   Handle edge cases like boundary conditions and empty arrays.
*   **Examples:** [283. Move Zeroes](../leetcode/283.move-zeros.md) (move all zeros to end), [189. Rotate Array](../leetcode/189.rotate-array.md) (cyclic rotation), [665. Non-decreasing Array](../leetcode/665.non-decreasing-array.md) (fix at most one violation).

### 3. 2D Array Manipulation

Working with matrices requires understanding coordinate systems and traversal patterns.

*   **Core Idea:** Master different traversal patterns: row-wise, column-wise, diagonal, spiral, and boundary.
*   **Key Approach:**
    *   Choose the right coordinate system and direction vectors.
    *   Handle boundary conditions carefully.
    *   For rotation/transformation problems, understand the mathematical relationship between old and new coordinates.
*   **Common Patterns:**
    *   **Diagonal Traversal:** Use relationship between row and column indices.
    *   **Spiral Traversal:** Maintain four boundaries and shrink them as you traverse.
    *   **Matrix Rotation:** Use transpose + reflection or direct coordinate transformation.
*   **Examples:** [48. Rotate Image](../leetcode/48.rotate-image.md), [54. Spiral Matrix](../leetcode/54.spiral-matrix.md), [73. Set Matrix Zeroes](../leetcode/73.set-matrix-zeros.md).

### 4. Mathematical Operations

Array problems often involve mathematical computations, simulations, or number theory.

*   **Core Idea:** Apply mathematical formulas, simulate arithmetic operations, or use properties like modular arithmetic.
*   **Key Approach:**
    *   Break down complex operations into simpler steps.
    *   Handle carry-over, overflow, or modular arithmetic carefully.
    *   Use mathematical properties to optimize solutions.
*   **Examples:** [989. Add to Array-Form of Integer](../leetcode/989.add-to-array-form-of-integer.md) (digit-by-digit addition), [334. Increasing Triplet Subsequence](../leetcode/334.increasing-triplet-subsequence.md) (track first and second smallest).

---

### Summary of Key Approaches

1.  **Hash Table for Lookups:** When you need to find pairs, complements, or previously seen elements, use hash tables for O(1) lookups. This is essential for enumeration patterns.

2.  **Prefix/Suffix Processing:** For problems where you need information about elements before/after a certain position, precompute prefix and suffix arrays. This is common in "enumerate middle" problems.

3.  **Two Pointers:** Use when you need to process elements from different ends of the array or when rearranging elements. One pointer reads, the other writes.

4.  **Mathematical Insight:** Look for mathematical patterns, properties, or formulas that can simplify the problem. Sometimes the key is not the algorithm but the math.

5.  **Coordinate Systems:** For 2D arrays, choose the right coordinate system and understand transformation rules for rotation, reflection, or traversal.

6.  **Edge Case Handling:** Arrays can be empty, have one element, or contain duplicates. Always consider these edge cases in your solution.

The key to mastering array problems is recognizing these patterns and choosing the right approach based on the problem's constraints and requirements.