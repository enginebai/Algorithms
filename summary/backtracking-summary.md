# Key Patterns and Approaches for Backtracking Problems

Based on the problems in `problems/backtracking-problems.md`, here is a summary of the key patterns and approaches for solving backtracking problems.

Backtracking is a systematic method for solving problems by exploring all possible solutions and "backtracking" (undoing choices) when a path doesn't lead to a valid solution. It's essentially a depth-first search with the ability to undo decisions.

### 1. Core Backtracking Framework

All backtracking problems follow a similar recursive structure:

*   **Core Idea:** Make a choice, explore all possibilities from that choice, then undo the choice to try other options.
*   **Key Framework:**
    ```python
    def backtrack(path, choices):
        if base_case:
            result.append(path.copy())
            return
        
        for choice in choices:
            # Make choice
            path.append(choice)
            # Recurse with updated state
            backtrack(path, next_choices)
            # Undo choice (backtrack)
            path.pop()
    ```

### 2. Combination Problems

Generate all possible combinations of elements, often with constraints on size or sum.

*   **Core Idea:** Choose elements from a set without considering order. Each element can be chosen or not chosen.
*   **Key Approach:**
    *   Use a start index to avoid duplicate combinations.
    *   For each position, decide whether to include the current element.
    *   Handle duplicates by sorting and skipping consecutive duplicate elements.
*   **Examples:** [77. Combinations](../leetcode/77.combinations.md) (choose k from n), [39. Combination Sum](../leetcode/39.combination-sum.md) (unlimited reuse), [40. Combination Sum II](../leetcode/40.combination-sum-ii.md) (each element used once), [216. Combination Sum III](../leetcode/216.combination-sum-iii.md) (fixed size with sum constraint).

### 3. Subset Generation

Generate all possible subsets (power set) of a given set.

*   **Core Idea:** For each element, you have two choices: include it in the subset or exclude it.
*   **Key Approach:**
    *   Use the combination framework but collect results at every level.
    *   Handle duplicates by sorting and skipping when current element equals previous element.
*   **Examples:** [78. Subsets](../leetcode/78.subsets.md) (all subsets), [90. Subsets II](../leetcode/90.subsets-ii.md) (subsets with duplicates).

### 4. Permutation Problems

Generate all possible arrangements of elements where order matters.

*   **Core Idea:** Arrange elements in all possible orders. Unlike combinations, the same elements in different orders are different solutions.
*   **Key Approach:**
    *   Use a visited array or remove/add elements to track used elements.
    *   For each position, try all remaining unused elements.
    *   Handle duplicates by sorting and ensuring the same element group is used in order.
*   **Examples:** [46. Permutations](../leetcode/46.permutations.md) (all permutations), [47. Permutations II](../leetcode/47.permutations-ii.md) (permutations with duplicates).

### 5. String Construction and Validation

Build strings character by character while maintaining validity constraints.

*   **Core Idea:** Construct strings incrementally, checking constraints at each step to prune invalid paths early.
*   **Key Approach:**
    *   Maintain counters or state to check validity (e.g., balance of parentheses).
    *   Add characters one by one and recurse.
    *   Prune early when constraints are violated.
*   **Examples:** [22. Generate Parentheses](../leetcode/22.generate-parentheses.md) (balanced parentheses), [17. Letter Combinations of a Phone Number](../leetcode/17.letter-combinations-of-a-phone-number.md) (phone number mapping).

### 6. Grid and Matrix Traversal

Explore paths in 2D grids, often searching for words or patterns.

*   **Core Idea:** Use DFS to explore all possible paths in a grid, marking visited cells to avoid cycles.
*   **Key Approach:**
    *   Use direction vectors for movement (up, down, left, right).
    *   Mark cells as visited before recursing and unmark them after (backtrack).
    *   Check bounds and validity constraints before making recursive calls.
*   **Examples:** [79. Word Search](../leetcode/79.word-search.md) (find word in grid), [212. Word Search II](../leetcode/212.word-search-ii.md) (find multiple words using Trie).

### 7. Tree Path Problems

Find all paths in a tree that satisfy certain conditions.

*   **Core Idea:** Traverse the tree while building paths, collecting valid paths when criteria are met.
*   **Key Approach:**
    *   Pass the current path as a parameter or maintain it globally.
    *   Add current node to path, recurse on children, then remove current node.
    *   Collect paths when reaching leaves or when conditions are satisfied.
*   **Examples:** [257. Binary Tree Paths](../leetcode/257.binary-tree-paths.md) (root to leaf paths), [113. Path Sum II](../leetcode/113.path-sum-ii.md) (paths with target sum), [437. Path Sum III](../leetcode/437.path-sum-iii.md) (any path with target sum).

### 8. Partitioning Problems

Divide a string or array into parts where each part satisfies certain conditions.

*   **Core Idea:** Try all possible ways to partition the input, checking if each partition meets the criteria.
*   **Key Approach:**
    *   For each starting position, try all possible ending positions for the current partition.
    *   Check if the current partition is valid before recursing on the remaining part.
    *   Collect solutions when the entire input has been partitioned.
*   **Examples:** [131. Palindrome Partitioning](../leetcode/131.palindrome-partitioning.md) (partition into palindromes).

---

### Key Optimization Techniques

1. **Early Pruning:** Check constraints before making recursive calls to avoid exploring invalid paths.

2. **Sorting for Duplicates:** Sort the input array to handle duplicates systematically by skipping consecutive identical elements.

3. **State Management:** Carefully manage what state needs to be passed down or maintained globally (path, visited markers, etc.).

4. **Base Case Recognition:** Identify when to collect results (leaf nodes, complete solutions, or at every level for subsets).

5. **Choice Space Reduction:** Use techniques like start indices in combinations to reduce the choice space and avoid duplicates.

### Summary of Key Approaches

1. **Understand the Problem Type:** Is it combinations, permutations, partitioning, or path finding? Each has specific patterns.

2. **Handle Duplicates Properly:** Sort input and skip duplicates systematically to avoid duplicate solutions.

3. **Choose State Representation:** Decide what state needs to be maintained and how to pass it between recursive calls.

4. **Implement Proper Backtracking:** Always undo changes made before recursive calls to maintain correctness.

5. **Optimize with Pruning:** Add constraints checking to prune invalid paths early and improve performance.

6. **Test Base Cases:** Ensure base cases are handled correctly for edge cases like empty inputs or single elements.

Backtracking problems require careful state management and systematic exploration of the solution space. The key is to recognize the pattern, implement the core framework correctly, and optimize with appropriate pruning strategies.