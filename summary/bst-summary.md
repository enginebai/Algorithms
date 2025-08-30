# Key Patterns and Approaches for Binary Search Tree Problems

Based on the problems in `problems/bst-problems.md`, here is a summary of the key patterns and approaches for solving Binary Search Tree (BST) problems.

Binary Search Trees maintain the invariant that for any node, all values in the left subtree are smaller, and all values in the right subtree are larger. This property enables efficient search, insertion, and deletion operations, as well as ordered traversals.

### 1. BST Traversal Patterns

BST traversals leverage the ordering property to solve various problems efficiently.

#### 1.1 In-Order Traversal
*   **Core Idea:** In-order traversal of a BST visits nodes in ascending sorted order.
*   **Key Approach:**
    *   Use in-order traversal to get sorted sequence.
    *   For validation, ensure the sequence is strictly increasing.
    *   Can be implemented iteratively using a stack for space optimization.
*   **Examples:** [98. Validate Binary Search Tree](../leetcode/98.validate-binary-search-tree.md), [230. Kth Smallest Element in a BST](../leetcode/230.kth-smallest-element-in-a-bst.md), [530. Minimum Absolute Difference in BST](../leetcode/530.minimum-absolute-difference-in-bst.md).

#### 1.2 Reverse In-Order Traversal
*   **Core Idea:** Process nodes in descending order by visiting right subtree first.
*   **Key Approach:** Right → Root → Left traversal gives values in decreasing order.
*   **Examples:** [1038. Binary Search Tree to Greater Sum Tree](../leetcode/1038.binary-search-tree-to-greater-sum-tree.md) (accumulate sums from largest to smallest).

### 2. BST Search Operations

Leverage the BST property for efficient searching and existence checking.

*   **Core Idea:** Use the BST property to eliminate half of the search space at each step.
*   **Key Approach:**
    *   Compare target with current node value.
    *   Go left if target is smaller, right if larger.
    *   Time complexity: O(h) where h is tree height.
*   **Examples:** [700. Search in a Binary Search Tree](../leetcode/700.search-in-a-binary-search-tree.md), [235. Lowest Common Ancestor of a Binary Search Tree](../leetcode/235.lowest-common-acestor-of-a-binary-search-tree.md), [653. Two Sum IV - Input is a BST](../leetcode/653.two-sum-iv-input-is-a-bst.md).

### 3. BST Construction and Modification

Build or modify BST structures while maintaining the BST property.

#### 3.1 Construction from Sorted Data
*   **Core Idea:** Use the middle element as root to create a balanced BST.
*   **Key Approach:**
    *   Find middle element of sorted array/list.
    *   Make it root, recursively build left and right subtrees.
    *   Results in a balanced BST with O(log n) height.
*   **Examples:** [108. Convert Sorted Array to Binary Search Tree](../leetcode/108.convert-sorted-array-to-binary-search-tree.md).

#### 3.2 Insertion and Deletion
*   **Core Idea:** Maintain BST property while modifying tree structure.
*   **Key Approaches:**
    *   **Insertion:** Find the correct leaf position and insert.
    *   **Deletion:** Three cases - leaf node, one child, two children (use predecessor/successor).
*   **Examples:** [701. Insert into a Binary Search Tree](../leetcode/701.insert-into-a-binary-search-tree.md), [450. Delete Node in a BST](../leetcode/450.delete-node-in-a-bst.md).

#### 3.3 Tree Restructuring
*   **Core Idea:** Transform the tree structure while preserving values and BST property.
*   **Key Approaches:**
    *   Use in-order traversal to get sorted values, then reconstruct.
    *   For trimming, recursively process subtrees and maintain connections.
*   **Examples:** [669. Trim a Binary Search Tree](../leetcode/669.trim-a-binary-search-tree.md), [897. Increasing Order Search Tree](../leetcode/897.increasing-order-search-tree.md), [1382. Balance a Binary Search Tree](../leetcode/1382.balance-a-binary-search-tree.md).

### 4. Range and Boundary Operations

Work with ranges of values or find boundaries in BSTs.

*   **Core Idea:** Use BST property to efficiently process ranges without visiting irrelevant nodes.
*   **Key Approach:**
    *   For range queries, only explore subtrees that intersect with the range.
    *   For boundary problems, use the ordering to find min/max efficiently.
*   **Examples:** Range sum queries, finding kth elements, closest values.

### 5. Iterator Design

Implement efficient iteration over BST elements in sorted order.

*   **Core Idea:** Simulate in-order traversal with on-demand processing.
*   **Key Approach:**
    *   Use a stack to maintain the path to the current node.
    *   Push left path, pop and process, then move to right child.
    *   Achieves O(1) amortized time per next() call.
*   **Examples:** [173. Binary Search Tree Iterator](../leetcode/173.binary-search-tree-iterator.md).

---

### Key Optimization Techniques

1. **Exploit BST Property:** Always use the ordering property to eliminate unnecessary searches or traversals.

2. **Choose Right Traversal:** In-order for sorted processing, reverse in-order for accumulation problems.

3. **Iterative vs Recursive:** Use iterative approaches with explicit stacks for better space control.

4. **Balanced Construction:** When building BSTs, use middle elements to maintain balance and optimal height.

### Common BST Patterns Recognition

1. **Validation Problems:** Use in-order traversal and check if sequence is sorted.

2. **Kth Element Problems:** Use in-order traversal with counting or implement iterator.

3. **Range Problems:** Use DFS with range checks to prune unnecessary subtree visits.

4. **Two Sum Problems:** Combine in-order traversal with hash table or two pointers.

5. **Construction Problems:** For sorted input, use divide-and-conquer with middle element as root.

6. **Modification Problems:** Understand the three cases of deletion and handle them correctly.

### Summary of Key Approaches

1. **Understand BST Invariant:** Left < Root < Right property is the foundation of all BST algorithms.

2. **Master In-Order Traversal:** It's the most frequently used traversal for BST problems.

3. **Use the Search Property:** Leverage O(log n) search capability instead of traversing entire tree.

4. **Handle Edge Cases:** Empty trees, single nodes, and unbalanced trees require special attention.

5. **Consider Balance:** Be aware of worst-case O(n) height in unbalanced BSTs.

6. **Iterator Pattern:** For sequential processing, implement iterator for memory-efficient solutions.

BST problems often combine tree traversal techniques with the unique ordering property. The key insight is to leverage this property to solve problems more efficiently than general tree algorithms would allow.