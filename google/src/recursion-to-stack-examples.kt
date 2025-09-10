/**
 * Recursion to Stack Conversion: Practical Examples
 *
 * This file contains working examples demonstrating how to convert
 * recursive algorithms to stack-based iterative solutions.
 *
 * Each example shows:
 * 1. Original recursive solution
 * 2. Step-by-step analysis
 * 3. Stack-based conversion
 * 4. Test cases
 */

import java.util.*

// ============================================================================
// Example 1: Factorial (Tail Recursion)
// ============================================================================

/**
 * Pattern: Single recursive call at the end
 * Difficulty: Easy
 * Key: Tail recursion is simplest to convert
 */
class FactorialExample {

    // RECURSIVE VERSION
    fun factorialRecursive(n: Int): Long {
        if (n <= 1) return 1
        return n * factorialRecursive(n - 1)
    }

    // STACK VERSION
    fun factorialStack(n: Int): Long {
        val stack = ArrayDeque<Int>()

        // Push all numbers onto stack
        var current = n
        while (current > 1) {
            stack.push(current)
            current--
        }

        // Pop and compute
        var result = 1L
        while (stack.isNotEmpty()) {
            result *= stack.pop()
        }

        return result
    }

    // OPTIMIZED VERSION (no stack needed for tail recursion!)
    fun factorialIterative(n: Int): Long {
        var result = 1L
        for (i in 2..n) {
            result *= i
        }
        return result
    }
}

// ============================================================================
// Example 2: Reverse String
// ============================================================================

/**
 * Pattern: Simple linear recursion with string concatenation
 * Difficulty: Easy
 */
class ReverseStringExample {

    // RECURSIVE VERSION
    fun reverseRecursive(s: String, index: Int = 0): String {
        if (index >= s.length) return ""
        return reverseRecursive(s, index + 1) + s[index]
    }

    // STACK VERSION
    fun reverseStack(s: String): String {
        val stack = ArrayDeque<Char>()

        // Push all characters
        for (char in s) {
            stack.push(char)
        }

        // Pop to build reversed string
        val result = StringBuilder()
        while (stack.isNotEmpty()) {
            result.append(stack.pop())
        }

        return result.toString()
    }

    // OPTIMIZED VERSION
    fun reverseIterative(s: String): String {
        return s.reversed()
    }
}

// ============================================================================
// Example 3: Binary Tree Traversal
// ============================================================================

data class TreeNode(
    val value: Int,
    val left: TreeNode? = null,
    val right: TreeNode? = null
)

/**
 * Pattern: Tree traversal with multiple recursive calls
 * Difficulty: Medium
 */
class TreeTraversalExample {

    // RECURSIVE INORDER TRAVERSAL
    fun inorderRecursive(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val result = mutableListOf<Int>()
        result.addAll(inorderRecursive(root.left))
        result.add(root.value)
        result.addAll(inorderRecursive(root.right))

        return result
    }

    // STACK VERSION - Explicit state tracking
    data class TraversalState(
        val node: TreeNode?,
        val phase: Phase = Phase.INITIAL
    ) {
        enum class Phase {
            INITIAL,      // Haven't visited children
            LEFT_DONE,    // Left subtree processed
            BOTH_DONE     // Both subtrees processed
        }
    }

    fun inorderStack(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val stack = ArrayDeque<TraversalState>()
        val result = mutableListOf<Int>()

        stack.push(TraversalState(root))

        while (stack.isNotEmpty()) {
            val state = stack.pop()

            if (state.node == null) continue

            when (state.phase) {
                TraversalState.Phase.INITIAL -> {
                    // Will process left, then self, then right
                    // Push in reverse order (right, self, left)
                    stack.push(TraversalState(state.node, TraversalState.Phase.BOTH_DONE))
                    stack.push(TraversalState(state.node, TraversalState.Phase.LEFT_DONE))
                    stack.push(TraversalState(state.node.left))
                }

                TraversalState.Phase.LEFT_DONE -> {
                    // Left done, process current node
                    result.add(state.node.value)
                    // Now process right
                    stack.push(TraversalState(state.node.right))
                }

                TraversalState.Phase.BOTH_DONE -> {
                    // Both children processed, nothing to do
                }
            }
        }

        return result
    }

    // SIMPLIFIED ITERATIVE VERSION (Standard pattern)
    fun inorderIterative(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val stack = ArrayDeque<TreeNode>()
        var current = root

        while (current != null || stack.isNotEmpty()) {
            // Go to leftmost node
            while (current != null) {
                stack.push(current)
                current = current.left
            }

            // Process node
            current = stack.pop()
            result.add(current.value)

            // Move to right subtree
            current = current.right
        }

        return result
    }
}

// ============================================================================
// Example 4: Sum of Array (Simple Recursion)
// ============================================================================

/**
 * Pattern: Linear recursion on array
 * Difficulty: Easy
 */
class ArraySumExample {

    // RECURSIVE VERSION
    fun sumRecursive(arr: IntArray, index: Int = 0): Int {
        if (index >= arr.size) return 0
        return arr[index] + sumRecursive(arr, index + 1)
    }

    // STACK VERSION (overkill for this problem!)
    fun sumStack(arr: IntArray): Int {
        if (arr.isEmpty()) return 0

        val stack = ArrayDeque<Int>()  // Store indices

        // Push all indices
        for (i in arr.indices) {
            stack.push(i)
        }

        // Pop and sum
        var sum = 0
        while (stack.isNotEmpty()) {
            val index = stack.pop()
            sum += arr[index]
        }

        return sum
    }

    // ITERATIVE VERSION (best approach)
    fun sumIterative(arr: IntArray): Int {
        return arr.sum()
    }
}

// ============================================================================
// Example 5: Flatten Nested List
// ============================================================================

/**
 * Pattern: Recursion on nested structure
 * Difficulty: Medium
 *
 * Example input: [1, [2, 3], [[4]], 5]
 * Output: [1, 2, 3, 4, 5]
 */
sealed class NestedItem {
    data class Number(val value: Int) : NestedItem()
    data class NestedList(val items: List<NestedItem>) : NestedItem()
}

class FlattenListExample {

    // RECURSIVE VERSION
    fun flattenRecursive(items: List<NestedItem>): List<Int> {
        val result = mutableListOf<Int>()

        for (item in items) {
            when (item) {
                is NestedItem.Number -> result.add(item.value)
                is NestedItem.NestedList -> result.addAll(flattenRecursive(item.items))
            }
        }

        return result
    }

    // STACK VERSION
    fun flattenStack(items: List<NestedItem>): List<Int> {
        val stack = ArrayDeque<NestedItem>()
        val result = mutableListOf<Int>()

        // Push items in reverse order
        for (i in items.indices.reversed()) {
            stack.push(items[i])
        }

        while (stack.isNotEmpty()) {
            when (val item = stack.pop()) {
                is NestedItem.Number -> {
                    result.add(item.value)
                }
                is NestedItem.NestedList -> {
                    // Push nested items in reverse order
                    for (i in item.items.indices.reversed()) {
                        stack.push(item.items[i])
                    }
                }
            }
        }

        return result
    }
}

// ============================================================================
// Example 6: Directory Size Calculator
// ============================================================================

/**
 * Pattern: Tree structure with aggregation
 * Difficulty: Medium
 *
 * Calculate total size of all files in directory tree
 */
sealed class FileSystemNode {
    data class File(val name: String, val size: Long) : FileSystemNode()
    data class Directory(val name: String, val children: List<FileSystemNode>) : FileSystemNode()
}

class DirectorySizeExample {

    // RECURSIVE VERSION
    fun calculateSizeRecursive(node: FileSystemNode): Long {
        return when (node) {
            is FileSystemNode.File -> node.size
            is FileSystemNode.Directory -> {
                node.children.sumOf { calculateSizeRecursive(it) }
            }
        }
    }

    // STACK VERSION
    data class StackState(
        val node: FileSystemNode,
        val phase: Phase = Phase.INITIAL
    ) {
        enum class Phase {
            INITIAL,        // Not processed yet
            CHILDREN_DONE   // Children processed
        }
    }

    fun calculateSizeStack(root: FileSystemNode): Long {
        val stack = ArrayDeque<StackState>()
        val sizes = mutableMapOf<FileSystemNode, Long>()

        stack.push(StackState(root))

        while (stack.isNotEmpty()) {
            val state = stack.pop()

            when (state.node) {
                is FileSystemNode.File -> {
                    sizes[state.node] = state.node.size
                }

                is FileSystemNode.Directory -> {
                    if (state.phase == StackState.Phase.INITIAL) {
                        // Check if all children are processed
                        val allChildrenProcessed = state.node.children.all { it in sizes }

                        if (allChildrenProcessed) {
                            // Calculate total size
                            val totalSize = state.node.children.sumOf { sizes[it]!! }
                            sizes[state.node] = totalSize
                        } else {
                            // Push back current node (will process after children)
                            stack.push(state.copy(phase = StackState.Phase.CHILDREN_DONE))

                            // Push unprocessed children
                            for (child in state.node.children) {
                                if (child !in sizes) {
                                    stack.push(StackState(child))
                                }
                            }
                        }
                    }
                }
            }
        }

        return sizes[root] ?: 0
    }

    // SIMPLER ITERATIVE VERSION (DFS with post-processing)
    fun calculateSizeIterative(root: FileSystemNode): Long {
        val stack = ArrayDeque<FileSystemNode>()
        val postOrder = mutableListOf<FileSystemNode>()
        stack.push(root)

        // First pass: DFS to get post-order
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            postOrder.add(node)

            if (node is FileSystemNode.Directory) {
                for (child in node.children) {
                    stack.push(child)
                }
            }
        }

        // Second pass: Calculate sizes bottom-up
        val sizes = mutableMapOf<FileSystemNode, Long>()
        for (node in postOrder.reversed()) {
            sizes[node] = when (node) {
                is FileSystemNode.File -> node.size
                is FileSystemNode.Directory -> node.children.sumOf { sizes[it]!! }
            }
        }

        return sizes[root]!!
    }
}

// ============================================================================
// Example 7: Generate Parentheses
// ============================================================================

/**
 * Pattern: Backtracking with multiple branches
 * Difficulty: Medium
 *
 * Generate all combinations of n pairs of balanced parentheses
 * Example: n=2 → ["(())", "()()"]
 */
class GenerateParenthesesExample {

    // RECURSIVE VERSION (Backtracking)
    fun generateRecursive(n: Int): List<String> {
        val result = mutableListOf<String>()

        fun backtrack(current: String, open: Int, close: Int) {
            if (current.length == 2 * n) {
                result.add(current)
                return
            }

            if (open < n) {
                backtrack(current + "(", open + 1, close)
            }

            if (close < open) {
                backtrack(current + ")", open, close + 1)
            }
        }

        backtrack("", 0, 0)
        return result
    }

    // STACK VERSION
    data class ParenState(
        val current: String,
        val open: Int,
        val close: Int
    )

    fun generateStack(n: Int): List<String> {
        val result = mutableListOf<String>()
        val stack = ArrayDeque<ParenState>()

        stack.push(ParenState("", 0, 0))

        while (stack.isNotEmpty()) {
            val state = stack.pop()

            // Base case: complete string
            if (state.current.length == 2 * n) {
                result.add(state.current)
                continue
            }

            // Try adding ')'
            if (state.close < state.open) {
                stack.push(ParenState(
                    state.current + ")",
                    state.open,
                    state.close + 1
                ))
            }

            // Try adding '('
            if (state.open < n) {
                stack.push(ParenState(
                    state.current + "(",
                    state.open + 1,
                    state.close
                ))
            }
        }

        return result
    }
}

// ============================================================================
// Test Runner
// ============================================================================

fun main() {
    println("=".repeat(70))
    println("Recursion to Stack Conversion: Working Examples")
    println("=".repeat(70))

    // Test 1: Factorial
    println("\n--- Example 1: Factorial ---")
    val fact = FactorialExample()
    val n = 5
    println("Recursive: factorial($n) = ${fact.factorialRecursive(n)}")
    println("Stack:     factorial($n) = ${fact.factorialStack(n)}")
    println("Iterative: factorial($n) = ${fact.factorialIterative(n)}")

    // Test 2: Reverse String
    println("\n--- Example 2: Reverse String ---")
    val reverse = ReverseStringExample()
    val str = "hello"
    println("Input: \"$str\"")
    println("Recursive: \"${reverse.reverseRecursive(str)}\"")
    println("Stack:     \"${reverse.reverseStack(str)}\"")
    println("Iterative: \"${reverse.reverseIterative(str)}\"")

    // Test 3: Tree Traversal
    println("\n--- Example 3: Binary Tree Inorder Traversal ---")
    val tree = TreeNode(
        4,
        TreeNode(2, TreeNode(1), TreeNode(3)),
        TreeNode(6, TreeNode(5), TreeNode(7))
    )
    val traversal = TreeTraversalExample()
    println("Tree structure: 4 with children 2(1,3) and 6(5,7)")
    println("Recursive: ${traversal.inorderRecursive(tree)}")
    println("Stack:     ${traversal.inorderStack(tree)}")
    println("Iterative: ${traversal.inorderIterative(tree)}")

    // Test 4: Array Sum
    println("\n--- Example 4: Array Sum ---")
    val arraySum = ArraySumExample()
    val arr = intArrayOf(1, 2, 3, 4, 5)
    println("Array: ${arr.contentToString()}")
    println("Recursive: ${arraySum.sumRecursive(arr)}")
    println("Stack:     ${arraySum.sumStack(arr)}")
    println("Iterative: ${arraySum.sumIterative(arr)}")

    // Test 5: Flatten Nested List
    println("\n--- Example 5: Flatten Nested List ---")
    val flatten = FlattenListExample()
    val nested = listOf(
        NestedItem.Number(1),
        NestedItem.NestedList(listOf(NestedItem.Number(2), NestedItem.Number(3))),
        NestedItem.NestedList(listOf(NestedItem.NestedList(listOf(NestedItem.Number(4))))),
        NestedItem.Number(5)
    )
    println("Input: [1, [2, 3], [[4]], 5]")
    println("Recursive: ${flatten.flattenRecursive(nested)}")
    println("Stack:     ${flatten.flattenStack(nested)}")

    // Test 6: Directory Size
    println("\n--- Example 6: Directory Size Calculator ---")
    val dirSize = DirectorySizeExample()
    val fileSystem = FileSystemNode.Directory(
        "root",
        listOf(
            FileSystemNode.File("file1.txt", 100),
            FileSystemNode.Directory(
                "subdir",
                listOf(
                    FileSystemNode.File("file2.txt", 200),
                    FileSystemNode.File("file3.txt", 300)
                )
            ),
            FileSystemNode.File("file4.txt", 400)
        )
    )
    println("Directory structure: root(file1=100, subdir(file2=200, file3=300), file4=400)")
    println("Recursive: ${dirSize.calculateSizeRecursive(fileSystem)} bytes")
    println("Stack:     ${dirSize.calculateSizeStack(fileSystem)} bytes")
    println("Iterative: ${dirSize.calculateSizeIterative(fileSystem)} bytes")

    // Test 7: Generate Parentheses
    println("\n--- Example 7: Generate Parentheses ---")
    val paren = GenerateParenthesesExample()
    val parenN = 3
    println("n = $parenN")
    println("Recursive: ${paren.generateRecursive(parenN)}")
    println("Stack:     ${paren.generateStack(parenN)}")

    println("\n" + "=".repeat(70))
    println("All examples completed!")
    println("=".repeat(70))
}
