## [Tree Problems](../topics/tree.md)

### Top-Down
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[94. Binary Tree Inorder Traversal](../leetcode/94.binary-tree-inorder-traversal.md)|Easy|
|[965. Univalued Binary Tree](../leetcode/965.univalued-binary-tree.md)|Easy| Compare `root`, `left`, `right`. Or carry `root` value to check.|
|[872. Leaf-Similar Trees](../leetcode/872.leaf-similar-trees.md)|Easy (1287)|
|[1448. Count Good Nodes in Binary Tree](../leetcode/1448.count-good-nodes-in-binary-tree.md)|Medium (1360)|
|**[662. Maximum Width of Binary Tree](../leetcode/662.maximum-width-of-binary-tree.md)|Medium| BFS or DFS (with level parameter) + position indexing |
|**[404. Sum of Left Leaves](../leetcode/404.sum-of-left-leaves.md)|Easy|
|[1026. Maximum Difference Between Node and Ancestor](../leetcode/1026.maximum-difference-betwwen-node-and-ancestor.md)|Medium (1446)|
|[1315. Sum of Nodes with Even-Valued Grandparent](../leetcode/1315.sum-of-nodes-with-even-valued-grandparent.md)|Medium (1426)|
|~~[671. Second Minimum Node In a Binary Tree](../leetcode/671.second-minimum-node-in-a-binary-tree.md)~~|Easy|

### Bottom-Up
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[110. Balanced Binary Tree](../leetcode/110.balanced-binary-tree.md)|Easy| Understand why bottom-up DFS. |
|[3319. K-th Largest Perfect Subtree Size in Binary Tree](../leetcode/3319.k-th-largest-perfect-subtree-size-in-binary-tree.md)|Medium (1603)|
|[2331. Evaluate Boolean Binary Tree](../leetcode/2331.evaluate-boolean-binary-tree.md)|Easy|
|**[563. Binary Tree Tilt](../leetcode/563.binary-tree-tilt.md)|Easy| Multiple return values. |
|[606. Construct String from Binary Tree](../leetcode/606.construct-string-from-binary-tree.md)|Medium|
|[2265. Count Nodes Equal to Average of Subtree](../leetcode/2265.count-nodes-equal-to-average-of-subtree.md)|Medium (1472)|
|[508. Most Frequent Subtree Sum](../leetcode/508.most-frequent-subtree-sum.md)|Medium|
|[1339. Maximum Product of Splitted Binary Tree](../leetcode/1339.maximum-product-of-splitted-binary-tree.md)|Medium (1674)|
|**[1443. Minimum Time to Collect All Apples in a Tree](../leetcode/1443.minimum-time-to-collect-all-apples-in-a-tree.md)|Medium (1682)| Definition of `dfs()` and case by case analysis. |
|**[979. Distribute Coins in Binary Tree](../leetcode/979.distribute-coins-in-binary-tree.md)|Medium (1709)| Direction doesn't matter. Care +/- balance. |
|[1145. Binary Tree Coloring Game](../leetcode/1145.binary-tree-coloring-game.md)|Medium (1741)|

> * https://leetcode.com/problems/flip-equivalent-binary-trees/description/ 1477
> * https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/description/ e
> * ~~https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/description/ 1805~~

> Similar problems of `979. Distribute Coins in Binary Tree`:
> * https://leetcode.com/problems/sum-of-distances-in-tree/description/ 2197
> * https://leetcode.com/problems/binary-tree-cameras/description/ 2124

### Distance
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[104. Maximum Depth of Binary Tree](../leetcode/104.maximum-depth-of-binary-tree.md)|Easy|
|[111. Minimum Depth of Binary Tree](../leetcode/111.minimum-depth-of-binary-tree.md)|Easy|
|**[543. Diameter of Binary Tree](../leetcode/543.diameter-of-binary-tree.md)|Easy| Definition of `dfs()`, which edges are counted. |
|**[687. Longest Univalue Path](../leetcode/687.longest-univalue-path.md)|Medium| Remember to run `dfs()` in subtree even if it's not univalue. |
|[2246. Longest Path With Different Adjacent Characters](../leetcode/2246.longest-path-with-different-adjacent-characters.md)|Hard (2126)| Run `dfs()` in subtree, then check if we can extend the path. |
|**[1376. Time Needed to Inform All Employees](../leetcode/1376.time-needed-to-inform-all-employees.md)|Medium (1560)| How to use Top-down / Bottom-up DFS. |
|**[863. All Nodes Distance K in Binary Tree](../leetcode/863.all-nodes-distance-k-in-binary-tree.md)|Medium (1663)| Parent map and how to prevent revisit. |
|[2385. Amount of Time for Binary Tree to Be Infected](../leetcode/2385.amount-of-time-for-binary-tree-to-be-infected.md)|Medium (1711)|
|[1372. Longest ZigZag Path in a Binary Tree](../leetcode/1372.longest-zigzag-path-in-a-binary-tree.md)|Medium (1713)| Extend or start a new path. |
|**[1530. Number of Good Leaf Nodes Pairs](../leetcode/1530.number-of-good-leaf-nodes-pairs.md)|Medium (1745)| Return distance array or map of node to distance. |

### Path
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[112. Path Sum](../leetcode/112.path-sum.md)|Easy|
|[124. Binary Tree Maximum Path Sum](../leetcode/124.binary-tree-maximum-path-sum.md)|Hard| `maxPathSum` is the maximum path sum of the entire tree. |
|**[129. Sum Root to Leaf Numbers](../leetcode/129.sum-root-to-leaf-numbers.md)|Medium| Carry value down, then return results up. |
|[1022. Sum of Root To Leaf Binary Numbers](../leetcode/1022.sum-of-root-to-leaf-binary-numbers.md)|Easy (1462)|
|[1457. Pseudo-Palindromic Paths in a Binary Tree](../leetcode/1457.pseudo-palindromic-paths-in-a-binary-tree.md)|Medium (1405)|
|[988. Smallest String Starting From Leaf](../leetcode/988.smallest-string-starting-from-leaf.md)|Medium (1429)|

### Structure
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[100. Same Tree](../leetcode/100.same-tree.md)|Easy|
|[572. Subtree of Another Tree](../leetcode/572.subtree-of-another-tree.md)|Easy|
|**[652. Find Duplicate Subtrees](../leetcode/652.find-duplicate-subtrees.md)|Medium| Hashing structure. |
|[1367. Linked List in Binary Tree](../leetcode/1367.linked-list-in-binary-tree.md)|Medium| Two pointers matching. |
|[101. Symmetric Tree](../leetcode/101.symmetric-tree.md)|Easy|
|[226. Invert Binary Tree](../leetcode/226.invert-binary-tree.md)|Easy|
|**[617. Merge Two Binary Trees](../leetcode/617.merge-two-binary-trees.md)|Easy| Iterative. |
|**[114. Flatten Binary Tree to Linked List](../leetcode/114.flatten-binary-tree-to-linked-list.md)|Medium| Flatten subtree first or traverse + flatten in preorder. |

### Insertion & Deletion
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[814. Binary Tree Pruning](../leetcode/814.binary-tree-pruning.md)|Medium (1380)|
|[1325. Delete Leaves With a Given Value](../leetcode/1325.delete-leaves-with-a-given-value.md)|Medium (1407)|
|**[1110. Delete Nodes And Return Forest](../leetcode/1110.delete-nodes-and-return-forest.md)|Medium (1511)| Mind the `root` is not deleted. |

### Construction
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[2196. Create Binary Tree From Descriptions](../leetcode/2196.create-binary-tree-from-descriptions.md)|Medium (1643)|
|[105. Construct Binary Tree from Preorder and Inorder Traversal](../leetcode/105.construct-binary-tree-from-preorder-and-inorder-traversal.md)|Medium|
|[654. Maximum Binary Tree](../leetcode/654.maximum-binary-tree.md)|Medium| Monotonic stack. (Advanced) |

> * https://leetcode.com/problems/maximum-binary-tree-ii/description/ 1497
> * Solved: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/ m
> * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/ 1731
> * https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/ 1797

### BFS
| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[102. Binary Tree Level Order Traversal](../leetcode/102.binary-tree-level-order-traversal.md)|Medium|
|[103. Binary Tree Zigzag Level Order Traversal](../leetcode/103.binary-tree-zigzag-level-order-traversal.md)|Medium|
|[637. Average of Levels in Binary Tree](../leetcode/637.average-of-levels-in-binary-tree.md)|Easy|
|[513. Find Bottom Left Tree Value](../leetcode/513.find-bottom-left-tree-value.md)|Medium|
|[1161. Maximum Level Sum of a Binary Tree](../leetcode/1161.maximum-level-sum-of-a-binary-tree.md)|Medium|
|[199. Binary Tree Right Side View](../leetcode/199.binary-tree-right-side-view.md)|Medium|
|[116. Populating Next Right Pointers in Each Node](../leetcode/116.populating-next-right-pointers-in-each-node.md)|Medium|
|**[993. Cousins in Binary Tree](../leetcode/993.cousins-in-binary-tree.md)|Easy (1287)|
|**[2641. Cousins in Binary Tree II](../leetcode/2641.cousins-in-binary-tree-ii.md)|Medium (1676)| Sum at level - 1. |
|**[623. Add One Row to Tree](../leetcode/623.add-one-row-to-tree.md)|Medium|
|[2471. Minimum Number of Operations to Sort a Binary Tree by Level](../leetcode/2471.minimum-number-of-operations-to-sort-a-binary-tree-by-level.md)|Medium (1635)|
|[919. Complete Binary Tree Inserter](../leetcode/919.complete-binary-tree-inserter.md)|Medium (1691)|
|[958. Check Completeness of a Binary Tree](../leetcode/958.check-completeness-of-a-binary-tree.md)|Medium (1703)| We can't have non-null after seeing null. |

> * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/ m
> * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/ m
> * https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/ m
> * https://leetcode.com/problems/deepest-leaves-sum/description/ 1387
> * https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/description/ 1431
> * https://leetcode.com/problems/even-odd-tree/description/ 1438
> * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/ m

### Lowest Common Ancestor
> Prerequisites: [Bottom-Up](../problems/tree-problems.md#bottom-up)

| Problem          | Difficulty | Notes |
|------------------|------------|-------|
|[236. Lowest Common Ancestor of a Binary Tree](../leetcode/236.lowest-common-ancestor-of-a-binary-tree.md)|Medium|
|[865. Smallest Subtree with all the Deepest Nodes](../leetcode/865.smallest-subtree-with-all-the-deepest-nodes.md)|Medium (1607)| Find LCA and the depth at the same time. |

> * https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/description/ 1805

### Serialization
| Problem          | Difficulty |
|------------------|------------|
|[572. Subtree of Another Tree](../leetcode/572.subtree-of-another-tree.md)|Easy|
|[652. Find Duplicate Subtrees](../leetcode/652.find-duplicate-subtrees.md)|Medium|
|[297. Serialize and Deserialize Binary Tree](../leetcode/297.serialize-and-deserialize-binary-tree.md)|Hard|

> * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/description/ m

### Other
| Problem          | Difficulty |
|------------------|------------|
> * Solved: https://leetcode.com/problems/count-complete-tree-nodes/description/, special case of binary tree traversal, more references: https://labuladong.online/algo/data-structure/count-complete-tree-nodes/ + https://programmercarl.com/0222.%E5%AE%8C%E5%85%A8%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E8%8A%82%E7%82%B9%E4%B8%AA%E6%95%B0.html#%E6%80%9D%E8%B7%AF

> * https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/description/ 1439
> * https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/description/ 1544
> * https://leetcode.com/problems/print-binary-tree/ 
> * https://leetcode.com/problems/count-nodes-with-the-highest-score/ 1911
> * https://leetcode.com/problems/make-costs-of-paths-equal-in-a-binary-tree/ 1917

## 三、一般树

### 3.1 Traversal
> * https://leetcode.com/problems/reachable-nodes-with-restrictions/description/ 1476
> * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/ 1633
 
### 3.2 Top-Down
> * https://leetcode.com/problems/unit-conversion-i/description/
> * https://leetcode.com/problems/frog-position-after-t-seconds/description/
> * https://leetcode.com/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network/description/
> * https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/description/
> * https://leetcode.com/problems/most-profitable-path-in-a-tree/description/

### 3.3 Bottom-Up
> * https://leetcode.com/problems/count-the-number-of-good-nodes/description/
> * https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-i/description/
> * https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/description/
> * https://leetcode.com/problems/maximum-number-of-k-divisible-components/description/

### Other
> TODO: [Problem Listing](https://huxulm.github.io/lc-rating/list/trees#da0703de2e6fdd52e6b0e2c1e3fc5376)

### Backtracking
| Problem          | Difficulty |
|------------------|------------|
|[257. Binary Tree Paths](../leetcode/257.binary-tree-paths.md)|Easy|
|[437. Path Sum III](../leetcode/437.path-sum-iii.md)|Medium|

## Explanation
* [662. Maximum Width of Binary Tree](https://www.youtube.com/watch?v=h8ON63MU4nQ)
* [543. Diameter of Binary Tree](https://www.youtube.com/watch?v=UfPMw8zD8EY)
* [687. Longest Univalue Path](https://www.youtube.com/watch?v=ZLu_Cj4zYEY)
* [2246. Longest Path With Different Adjacent Characters](https://www.youtube.com/watch?v=KzZ_Ya-2ODk)
* [863. All Nodes Distance K in Binary Tree](https://www.youtube.com/watch?v=53yZy6BWVzc)
* [124. Binary Tree Maximum Path Sum](https://www.youtube.com/watch?v=cuneDTWejTw)
* [1022. Sum of Root To Leaf Binary Numbers](https://www.youtube.com/watch?v=ge3Q2-eElLI)
* [572. Subtree of Another Tree](https://www.youtube.com/watch?v=BHzTSN6gAaM)
* [652. Find Duplicate Subtrees](https://www.youtube.com/watch?v=YupKiFqtnsA)
* [110. Balanced Binary Tree](https://github.com/wisdompeak/LeetCode/tree/master/Tree/110.Balanced-Binary-Tree)
* [226. Invert Binary Tree](https://github.com/wisdompeak/LeetCode/tree/master/Tree/226.Invert-Binary-Tree)
* [114. Flatten Binary Tree to Linked List](https://github.com/wisdompeak/LeetCode/tree/master/Tree/114.Flatten-Binary-Tree-to-Linked-List)
* [297. Serialize and Deserialize Binary Tree](https://www.youtube.com/watch?v=6tUBiOYbYgY)
* [236. Lowest Common Ancestor of a Binary Tree](https://github.com/wisdompeak/LeetCode/tree/master/Tree/236.Lowest-Common-Ancestor-of-a-Binary-Tree)