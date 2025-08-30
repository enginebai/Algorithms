# Key Patterns and Approaches for Tree Problems

Based on the problems in `problems/tree-problems.md`, here is a summary of the key patterns and approaches for solving tree problems.

Trees are hierarchical data structures with a root node and child nodes forming a parent-child relationship. Tree problems often involve traversal, path finding, structural analysis, and recursive solutions.

### 1. Tree Traversal Patterns

Different traversal orders provide different perspectives on tree data and enable various algorithms.

#### 1.1 Depth-First Traversals
*   **Pre-order (Root → Left → Right):** Process current node before children
    *   Applications: Tree copying, expression evaluation, file system listing
*   **In-order (Left → Root → Right):** Process left subtree, root, then right subtree
    *   Applications: BST sorted output, expression parsing
*   **Post-order (Left → Right → Root):** Process children before current node
    *   Applications: Tree deletion, directory size calculation, bottom-up algorithms
*   **Examples:** [94. Binary Tree Inorder Traversal](../leetcode/94.binary-tree-inorder-traversal.md).

#### 1.2 Breadth-First Traversal (Level Order)
*   **Core Idea:** Process nodes level by level from top to bottom.
*   **Key Applications:**
    *   Level-based analysis and modification
    *   Finding nodes at specific depths
    *   Tree width and level statistics
*   **Examples:** [102. Binary Tree Level Order Traversal](../leetcode/102.binary-tree-level-order-traversal.md), [103. Binary Tree Zigzag Level Order Traversal](../leetcode/103.binary-tree-zigzag-level-order-traversal.md).

### 2. Top-Down Recursive Patterns

Pass information from parent to children, solving problems by carrying state downward.

#### 2.1 State Propagation
*   **Core Idea:** Pass accumulated information (path, depth, constraints) from root to leaves.
*   **Key Framework:**
    ```python
    def top_down(node, state):
        if not node:
            return base_case(state)
        
        # Process current node with inherited state
        new_state = update_state(state, node.val)
        
        # Check if solution found
        if is_solution(node, new_state):
            record_solution(new_state)
        
        # Recurse to children
        top_down(node.left, new_state)
        top_down(node.right, new_state)
    ```
*   **Examples:** [1448. Count Good Nodes in Binary Tree](../leetcode/1448.count-good-nodes-in-binary-tree.md) (track max value on path), [404. Sum of Left Leaves](../leetcode/404.sum-of-left-leaves.md), [129. Sum Root to Leaf Numbers](../leetcode/129.sum-root-to-leaf-numbers.md).

### 3. Bottom-Up Recursive Patterns

Gather information from children to solve problems at the current node.

#### 3.1 Information Aggregation
*   **Core Idea:** Collect information from subtrees and combine to solve current problem.
*   **Key Framework:**
    ```python
    def bottom_up(node):
        if not node:
            return base_case
        
        # Get information from children
        left_info = bottom_up(node.left)
        right_info = bottom_up(node.right)
        
        # Process current node using children's information
        current_info = combine(left_info, right_info, node.val)
        
        # Update global result if needed
        update_global_result(current_info)
        
        return current_info
    ```
*   **Examples:** [110. Balanced Binary Tree](../leetcode/110.balanced-binary-tree.md), [543. Diameter of Binary Tree](../leetcode/543.diameter-of-binary-tree.md), [563. Binary Tree Tilt](../leetcode/563.binary-tree-tilt.md).

#### 3.2 Multiple Return Values
*   **Core Idea:** Return multiple pieces of information from each recursive call.
*   **Key Techniques:**
    *   Tuple returns for multiple values
    *   Custom classes/structs for complex information
*   **Examples:** [563. Binary Tree Tilt](../leetcode/563.binary-tree-tilt.md) (return sum and tilt), tree height and balance information.

### 4. Path Problems

Find or analyze paths between nodes in the tree.

#### 4.1 Root-to-Leaf Paths
*   **Core Idea:** Analyze paths from root to leaf nodes.
*   **Key Applications:**
    *   Path sum problems
    *   Path counting and enumeration
    *   Path-based decision making
*   **Examples:** [112. Path Sum](../leetcode/112.path-sum.md), [113. Path Sum II](../leetcode/113.path-sum-ii.md), [257. Binary Tree Paths](../leetcode/257.binary-tree-paths.md).

#### 4.2 Any-Node-to-Any-Node Paths
*   **Core Idea:** Consider paths that may not start from root or end at leaf.
*   **Key Approach:**
    *   For each node, consider paths passing through it
    *   Combine information from left and right subtrees
    *   Track both "path ending at current node" and "best path in subtree"
*   **Examples:** [124. Binary Tree Maximum Path Sum](../leetcode/124.binary-tree-maximum-path-sum.md), [687. Longest Univalue Path](../leetcode/687.longest-univalue-path.md).

### 5. Tree Structure and Modification

Analyze or modify the tree structure itself.

#### 5.1 Tree Validation and Properties
*   **Core Idea:** Check if tree satisfies certain structural properties.
*   **Key Properties:**
    *   Balance: height difference between subtrees
    *   Completeness: all levels filled except possibly last
    *   Specific ordering: BST property, heap property
*   **Examples:** [100. Same Tree](../leetcode/100.same-tree.md), [101. Symmetric Tree](../leetcode/101.symmetric-tree.md), [110. Balanced Binary Tree](../leetcode/110.balanced-binary-tree.md).

#### 5.2 Tree Construction and Reconstruction
*   **Core Idea:** Build trees from given information or transform existing trees.
*   **Key Approaches:**
    *   Use traversal information to reconstruct tree
    *   Transform tree structure while preserving information
*   **Examples:** [105. Construct Binary Tree from Preorder and Inorder Traversal](../leetcode/105.construct-binary-tree-from-preorder-and-inorder-traversal.md), [114. Flatten Binary Tree to Linked List](../leetcode/114.flatten-binary-tree-to-linked-list.md).

### 6. Tree Distance and Relationship Problems

Analyze relationships and distances between nodes.

#### 6.1 Distance Calculation
*   **Core Idea:** Compute distances between nodes using tree structure.
*   **Key Approaches:**
    *   DFS to calculate distances from a specific node
    *   LCA (Lowest Common Ancestor) for pairwise distances
*   **Examples:** [863. All Nodes Distance K in Binary Tree](../leetcode/863.all-nodes-distance-k-in-binary-tree.md), [1376. Time Needed to Inform All Employees](../leetcode/1376.time-needed-to-inform-all-employees.md).

#### 6.2 Lowest Common Ancestor (LCA)
*   **Core Idea:** Find the deepest node that is an ancestor of both given nodes.
*   **Key Approach:**
    *   If current node is one of the targets, return it
    *   If both left and right subtrees contain targets, current node is LCA
    *   Otherwise, return the subtree that contains a target
*   **Examples:** [236. Lowest Common Ancestor of a Binary Tree](../leetcode/236.lowest-common-ancestor-of-a-binary-tree.md).

### 7. Level-Order Processing (BFS)

Process trees level by level for specific analytical or modification tasks.

#### 7.1 Level-Based Analysis
*   **Core Idea:** Analyze tree properties at each level.
*   **Key Applications:**
    *   Level sums, averages, maximums
    *   Level-based modifications
    *   Width and structure analysis
*   **Examples:** [637. Average of Levels in Binary Tree](../leetcode/637.average-of-levels-in-binary-tree.md), [1161. Maximum Level Sum of a Binary Tree](../leetcode/1161.maximum-level-sum-of-a-binary-tree.md), [199. Binary Tree Right Side View](../leetcode/199.binary-tree-right-side-view.md).

#### 7.2 Level-Based Modification
*   **Core Idea:** Modify tree structure or values based on level information.
*   **Key Techniques:**
    *   Track level information during BFS
    *   Modify nodes based on level-specific rules
*   **Examples:** [2641. Cousins in Binary Tree II](../leetcode/2641.cousins-in-binary-tree-ii.md) (replace with cousin sums).

### 8. Tree Serialization and Representation

Convert between tree structures and other representations.

*   **Core Idea:** Convert tree to/from strings, arrays, or other formats.
*   **Key Applications:**
    *   Tree storage and transmission
    *   Duplicate subtree detection
    *   Tree comparison and hashing
*   **Examples:** [297. Serialize and Deserialize Binary Tree](../leetcode/297.serialize-and-deserialize-binary-tree.md), [652. Find Duplicate Subtrees](../leetcode/652.find-duplicate-subtrees.md).

---

### Key Algorithm Selection Guide

| Problem Type | Approach | When to Use |
|-------------|----------|-------------|
| Path from root | Top-down DFS | Need to track path information |
| Tree properties | Bottom-up DFS | Need to aggregate from subtrees |
| Level analysis | BFS | Need level-by-level processing |
| Node distances | DFS + parent tracking | Distance/relationship queries |
| Tree construction | Divide and conquer | Building from traversal data |
| Structural validation | Recursive property checking | Verify tree properties |

### Common Tree Problem Patterns

1. **Single vs Multiple Values:** Decide what information to return from recursive calls.

2. **Global vs Local State:** Use global variables for results, local parameters for computation.

3. **Null Handling:** Always check for null nodes before accessing properties.

4. **Base Cases:** Define clear base cases for recursive functions (usually null nodes or leaf nodes).

5. **State Management:** Carefully manage what state is passed down vs what is returned up.

### Tree Implementation Tips

1. **Recursive Structure:** Most tree problems have elegant recursive solutions.

2. **Helper Functions:** Use helper functions to maintain clean interfaces while passing additional state.

3. **Edge Case Handling:** Empty trees, single nodes, and highly unbalanced trees require special attention.

4. **Memory Considerations:** Be aware of recursion depth limits for very deep trees.

### Summary of Key Approaches

1. **Master the Traversals:** Understanding when to use each traversal type is crucial.

2. **Choose Right Direction:** Top-down for carrying information down, bottom-up for aggregating information up.

3. **Handle Multiple Return Values:** Learn to return complex information from recursive calls.

4. **Practice Path Problems:** Path-based problems are common and have elegant recursive solutions.

5. **Understand Tree Properties:** Many problems rely on specific tree properties (balance, completeness, ordering).

6. **BFS for Level Operations:** Use BFS when the problem naturally thinks in terms of tree levels.

Tree problems test your ability to think recursively and understand hierarchical relationships. Success comes from recognizing the right traversal pattern and correctly managing information flow between recursive calls.