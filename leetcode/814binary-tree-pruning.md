# [814. Binary Tree Pruning](https://leetcode.com/problems/binary-tree-pruning/description/)

## Preorder
We traverse every node, and check if the subtree of the current node contains 1. If not, we prune the subtree.

```kotlin
fun pruneTree(root: TreeNode?): TreeNode? {
    if (root == null) return null
    if (!contains1(root)) return null

    root.left = pruneTree(root.left)
    root.right = pruneTree(root.right)
    return root
}

private fun contains1(root: TreeNode?): Boolean {
    if (root == null) return false
    if (root.`val` == 1) return true
    return contains1(root.left) || contains1(root.right)
}
```
* **Time Complexity:** `O(n^2)`, we traverse every node and check if the subtree contains 1.
* **Space Complexity:** `O(h)`.

## Postorder (Optimized)
We should prune from bottom to up, if the current root is leaf and its value is 0, we should prune it. (It may not be leaf at the beginning, but it becomes leaf after pruning its children.)

We prune the subtree first, then check if root is 0 and both subtrees are null. The subtree becomes null if it doesn't contain 1, otherwise it's non-null what was not pruned. So we prune the subtree first.

```kotlin
fun pruneTree(root: TreeNode?): TreeNode? {
    if (root == null) return null
    root.left = pruneTree(root.left)
    root.right = pruneTree(root.right)
    if (root.`val` != 1 && root.left == null && root.right == null) return null
    return root
}
```

* **Time Complexity:** `O(n)`, we traverse every node once.
* **Space Complexity:** `O(h)`.
