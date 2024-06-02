# Problem Solving

## Overview
* [Array](#array)
* [Two Pointers](#two-pointers)
* [Sliding Window](#sliding-windows)
* [Sorting](#sorting)
* [Binary Search](#binary-search)
* [Hash Table / Prefix Sum](#hash-table)
* [Heap](#heap)
* [(Monotonic) Stack](#stack) / [Queue](#queue)
* [Dynamic Programming](#dynamic-programming)
* [Greedy](#greedy)
* Specific Data Structures
    * [Linked List](#linked-list)
    * [Tree](#tree)
    * [Graph](#graph)
* Divide and Conquer
* Backtracking

## [Array](../topics/array.md)
* **Sorted**? 
    * Yes:
        * [Binary Search](#binary-search)
        * [Two Pointers](#two-pointers)
        * [Greedy](#greedy)
    * No, sort first? or use [hash table](#hash-table)?
* Subarray / substring?
    * [Sliding Window](#sliding-windows)
    * [Dynamic Programming](#dynamic-programming)
    * [Prefix Sum](#hash-table)
* Palindromic substring / subsequence?
    * [Dynamic Programming]()

### Approaches
* We can iterate array from left to right, also from *right to left*.
* `O(n)` time complexity **doesn't** mean you can only iterate the array **once**. Iterate the array several times might help solve the problem, for example, pre-computation (iterate array at least one time first) using hashing might be useful.

## Two Pointers
### Characteristics
* (Sorted) Sequential data, window or subarray
* Partitioning: `[even | odd]`, `[negative | positive]`...etc.
* A problem can be solved by two pointers when it reduces the total cases we need to consider. ([Source](https://leetcode.com/problems/subarray-sum-equals-k/solutions/301242/general-summary-of-what-kind-of-problem-can-cannot-solved-by-two-pointers/)), and also see [Sliding Window](#sliding-windows).
* Intersection or merge

### Approaches
* Left / right pointers
```js
[X, X, X, X, X, X]  
 L ->        <- R   // L at the beginning, R at the end, and move in opposite directions
 L/R ->             // L and R start from the beginning, and move in the same direction
 L --- R            // Range: [L, R]
```
* Read / write pointers: Read every element and write when condition is met. (only take the element met the requirement)
* Fast / slow pointers: Cycle detection

## Sliding Window
### Characteristics
* *Window*: valid value range, sliding window is applicable when meeting the following conditions:
    * If wider window is valid, then narrow window is also valid.
    * If narrow window is invalid, then wider window is also invalid.
* Consecutive problem: fixed or variable size window, to find the maximum/minimum window that meets the condition.
* Subarray / Substring

### Approaches
1. The two pointers start from the beginning of the array.
2. Try to expand the window by moving the right pointer until the window is valid.
3. Start to shrink the window by moving the left pointer when the window is invalid or to minimize the window size.
4. Update the result or some information of the window.
5. Repeat the process until the right pointer reaches the end of the array.

```kotlin
fun slidingWindowsProblem(str: String) {
    var left = 0
    var right = 0
    while (right < str.length) {
        // Update the window
        val character = str[right]

        // Window contains invalid value, shrink the window
        while (window needs shrink) {
            // Update the window
            val d = s[start]
            // Shrink window
            start++
        }

        // Update some information of windows or result here
        if (window meets condition) {
            // Update result
        }

        // Expand the window
        end++
    }
}
```
* **Time Complexity**: The every character will enter and exit the window at most once, so the time complexity is `O(n)`.

## [Binary Search](../topics/binary-search.md)
### Characteristics
* **Monotonicity**: The elements have some order or trend, such as sorted or `[X, X, X, O, O, O, O, O]` or choose larger the result become smaller and vice versa. 
* **Decision-making** or comparison or whether meet some condition in the **bounded search space**, then we can keep **reducing search space**.
* **Target or Feasibility**: Search for a specific value, peak or extremum.

> The answer is unique, and there's always another variable that changes monotonically according to the change of the answer, and we can depend on this variable to decide on which side of the search we go next step. Without the monotonicity and uniqueness, binary search is not applicable.

### Approaches
1. Define the search space: `left` and `right`.
    * Search on *index*
    * Search on *value*
2. Reduce the search space: What condition to determine which part to eliminate?
3. Common variants:
    * Feasibility:
    ```kotlin
    fun problemSolving(input): Int {
        // search space: index or value
        left, right = 0, input.size - 1
        while (left <= right) {
            val middle = left + (right - left) / 2
            if (isFeasible(input, middle)) // left or right part
            else // another part
        }
        return left
    }

    private fun isFeasible(input, target): Boolean {
        // Check if the input is feasible
    }
    ```
    * Counting:
    ```kotlin
    fun problemSolving(input, k): Int {
        left, right = 0, input.size - 1
        while (left <= right) {
            val middle = left + (right - left) / 2
            if (countEqualsToOrGreaterThan(input, middle) < k) left = middle + 1
            else right = middle - 1
        }
        return left
    }

    private fun countEqualsToOrGreaterThan(input, target): Boolean {
        // Count the number of elements <= target
    }
    ```
## [Hash Table](../topics/hash-table.md)
### Characteristics
* Mapping / `O(1)` lookup
* Counting / Frequency
* Seen / Duplicates / Missing
* Grouping / Anagrams / Intersection / Union
* Subarray (Prefix) Sum

### Approaches
* Hash Map / Set / Fixed-size array: input has fixed range of value, such as lowercase letters (`IntArray(26)`), number ranges `1..n`, etc.
* Cyclic sort or use array itself as hash table and index as key.
* [Two Sum](../leetcode/1.two-sum.md): Iterate the array, check its **complement** `target - current state` exists and update the result, and store current state to hash table as you've seen.

## [Sorting](../topics/sorting.md)
### Characteristics
 (Pairwise) Comparisons: Single list or compare between two lists.
* Detect duplicates / Grouping similar elements.
* Choose greedily.

## [Stack](../topics/stack-queue.md)
### Characteristics
* Last in first out (LIFO): Last element should be processed first.
* Nested structure / Parentheses / Balance / Parsing
* Undo / Redo operations
* Recursion / DFS / Backtracking
* Find next greater / smaller element: *Monotonic stack*

### Approaches
* For stack question, we can push the index or value, we still can get the original value from `array[stack.peek()]` when pushing the index.
* Nested structure:
```kotlin
fun stackProblem(input: XXX) {
    var result = ...
    // Iterate all elements in input
    for (i in 0 until input.size) {
        // Start of nested structure
        if (input[i] is opening) {
            // Push the current result to stack and start to process the nested structure
            stack.push(result)
            // Reset for nested usage
            result = 0
        } else if (input[i] is closing) { // End of nested structure
            // Pop the previous result and combine with current result
            result = stack.pop() + result
        }
    }
}
```

#### Monotonic Stack Template
```kotlin
fun problem(nums: XXXArray) {
    ...
    val stack = Stack<XXX>()
    for (i in 0 until nums.size) {
        // We might change the condition of value comparison.
        // And consider if the condition is strict (<) or not (<=)
        while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
            // We might not pop at once, just peek first
            val value = nums[stack.pop()]
            
            // Something we have to check if stack is empty now, and then pop item or peek.

            // calculate something and update the result
        }
        stack.push(i)
    }
}
```

* The time complexity is `O(n)`, since every item will enter/exit the stack once. Even if there is a `while` loop inside the `for` loop, the time complexity is stil `O(n)` because at most you touch each each index a maximum of 2 times, not `n` times.
* Usually, we will push index to stack rather than array value. And something, we need to push `0` or `-1` at the beginning and the end of array so that we can calculate the first / last item of array. (See [84. Largest Rectangle in Histogram](../leetcode/84.largest-rentangle-in-histogram.md))

## [Queue](../topics/stack-queue.md)
### Characteristics
* First input first out (FIFO): First element should be processed first.
* BFS / Level order traversal

### Approaches
BFS / Level order traversal:
```kotlin
val queue = ArrayDeque<XXX>()
// enqueue some first level elements
queue.addLast(xxx)
while (queue.isNotEmpty()) {
    val size = queue.size
    for (i in 0 until size) {
        // Dequeue and process
        val value = queue.removeFirst()
        // Do something

        // Enqueue next level elements
    }
}
```

## [Heap](../topics/heap.md)
### Characteristics
* Find the optimal element or extremum **dynamically**.
* Find the top / smallest K or Kth element.

### Approaches
* Top K elements: 
    * Maintain a max heap, and push all elements, then pop `k` times. --> `O(n lg n)` TC and `O(n)` SC.
    * Maintain a min heap with size `k`. --> `O(n lg k)` TC and `O(k)` SC.
    * *Bucket sort*: `O(n)` TC and `O(n)` SC.
    * *Quick select*: `O(n)` TC and `O(1)` SC.
> See problem [215. Kth Largest Element in an Array](../leetcode/215.kth-largest-element-in-an-array.md) or [347. Top K Frequent Elements](../leetcode/347.top-k-frequent-elements.md)
* Merge K ways: Maintain `k` pointers and push to heap, then pop the optimal element and push the next element from the same list.
```js
heap = [X, Y, Z]
[...,     X , ...]
[..., Y     , ...]
[...,   Z   , ...]
```
* [事後諸葛](https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/heap-2#ji-qiao-san-shi-hou-xiao-zhu-ge) 
> See problem [1642. Furthest Building You Can Reach](../leetcode/1642.furthest-building-you-can-reach.md)

## [Dynamic Programming](../topics/dynamic-programming.md)
This is a comprehensive topic, see [Dynamic Programming](../topics/dynamic-programming.md#problem-solving-techniuqes) for more details.

## [Greedy](../topics/greedy.md)
### Characteristics
* Choose the current optimal solution for every step.

More characteristics, see [Elements of Greedy](../topics/greedy.md#elements-of-greedy).

> TODO: Discover more characters from [greedy problems](../topics/leetcode-solutions.md#greedy).

### Approaches
* **Sort** first or use *heap* to get the optimal solution dynamically.

## [Linked List](../topics/linked-list.md)
* Traversal: Find i-th node, last node, etc.
* Insert / Delete: Head / tail / i-th node
* Reverse / Middle / Merge

### Characteristics
* `O(1)` insertion / deletion at head / tail, or with hash table to achieve `O(1)` lookup for every node.

> TODO: Discover more characters from [design problems](../topics/leetcode-solutions.md#design).

### Approaches
* **Drawing** could be really help!!
* *Sentinel node*: 
    * To create a new linked list.
    * To insert when head is null.
    * To deleting head and it becomes null.
```kotlin
fun solveProblem(head?: Node): Node? {
    val sentinel = Node(-1)
    var node: Node? = sentinel
    /// do something to modify the `node`

    return sentinel.next
}
```
* Pointers:
    * Fast / slow pointers
    * Previous / current / next pointers
    * **Preserve** the (next) node before modifying it.
* Corner cases:
    * **Empty linked list** (before operation or **after!**, such as deleting the only node)
    * Operation on head / tail.
    * Linked list with **one / two nodes**
    * Linked list has cycles. Clarify before solving problem! And pay attention to the result after performing the functions.

## [Tree](../topics/tree.md)
* Traversal: search or operate on every node.
    * Pre-order
    * In-order
    * Post-order
    * Level-order (BFS): Level annotation or return early (shortest path)
* Path / Sum / Height / Depth / Diameter / Width
* Distance
* Insertion / Deletion
* Lowest Common Ancestor

### Approaches
[Recursion](../topics/recursion.md) is one of the most powerful and frequently used techniques to solve tree problems. (also natural features of a tree) There are two techniques to solve tree problems with *recursion*:
1. **Traversal**: Can the answer by obtained by traversing the tree once? If yes, define a `traverse(root)` recursive function + global variable to implement it.
2. **Divide and Conquer**: Can we split the problem into sub-problems (sub-trees), and obtain the answer of original problem by combining the results of sub-problems? If yes, define a function `solve(root): T` that returns the answer of sub-problems, and use it to solve the original problem.

For the two techniques, we have to consider the following:
* What to do for current node? (Just think about what each node should do individually)
* When to do it? (pre-order, in-order, post-order)

> **NOTE**: To implement recursion correctly, just **think about what to do for current node, and don't care about the sub-problems. (leave it to recursion)**

The following are DFS general templates of recursive function, the major difference between these three traversals is the order of the **main logic**.

* Pre-order: `DLR`
```kotlin
fun dfs(root: TreeNode?): T {
    if (Some termaination condition or end of search path (base case)) {
        return result
    }

    // Main logic

    // Traverse sub-tree
    dfs(root.left)
    dfs(root.right)

    // Return result for current node
}
```
* In-order: `LDR`

```kotlin
fun dfs(root: TreeNode?): T {
    if (Some termaination condition or end of search path (base case)) {
        return result
    }
    dfs(root.left)

    // Main logic

    dfs(root.right)
    // Return result for current node
}
```

* Post-order: `LRD`
```kotlin
fun dfs(root: TreeNode?): T {
    if (Some termaination condition or end of search path (base case)) {
        return result
    }
    // Traverse sub-tree first
    val leftValue = dfs(root.left)
    val rightValue = dfs(root.right)

    // Main logic: deal with current root, and combine the result of sub-trees

    // Return result for current node
}
```

The key difference of pre-order and post-order: 
|       | Pre-order        | Post-order                      |
|-------|------------------|---------------------------------|
| Order | Top-down         | Bottom-up                       |
| Data  | Parent node only | Parent + children node together |

> *Dynamic programming*, *Graph (DFS)*, *divide-and-conquer* and *backtracking* are all the extension of binary tree, we can use the similar techniques to solve the problem.

#### Breadth First Search (BFS)
* Level traversal:
```kotlin
fun bfs(root: TreeNode?) {
    if (root == null) return
    val queue = ArrayDeque<TreeNode>()
    queue.addLast(root)
    while (!queue.isEmpty()) {
        val node = queue.removeFirst()
        // do something or return early
            
        if (node.left != null) queue.addLast(node.left!!)
        if (node.right != null) queue.addLast(node.right!!)
    }
}
```

* With level annotation:
```kotlin
fun bfs(root: TreeNode?) {
    if (root == null) return
    val queue = ArrayDeque<TreeNode>()
    queue.addLast(root)
    var level = 0
    while (!queue.isEmpty()) {
        // Do something in the same level
        val size = queue.size()
        for (i in 0 until size) {
            val node = queue.removeFirst()
            // do something or update result
            
            if (node.left != null) queue.addLast(node.left!!)
            if (node.right != null) queue.addLast(node.right!!)
        }
        level++
        
        // Do something extra
    }
}
```

#### General
* Cases to consider:
    * Empty tree (`node == null`)
    * Single root.
    * One child, or two children. 
    * Skewed tree (like a linked list), height will be `n`, not `lg n`.
* The node in problems doesn't have the parent pointer, we can run DFS/BFS once and use hash table to store its parent.

## [Binary Search Tree](../topics/tree.md#binary-search-tree)
* In-order traversal of BST is an **ascending** sorted array, this property can be helpful to solve the BST problem.

## [Graph](../topics/graph.md)
* DFS 
* BFS
* Topological Sort
* Shortest Path

### Characteristics
* We can convert the problem into the *states as nodes*, and *operations as edges* to build the graph, then use DFS /BFS to iterate all possible states / operations like traversing the graph to solve the problem. (such as [Word Ladder](../leetcode/127.word-ladder.md))
* To find the order / prerequisite / dependency, we can use **topological sort**.

### Approaches
* Figure out the **states** and **operations**, then build the graph.
* Start to traversal all possible nodes without duplicates in DFS / BFS, we have to record the visited nodes. 

> Binary tree is a special case of graph, so we can use the same approach to solve the problem, however, tree is directed graph without cycle, so we don't need to record the visited nodes.
> 
> For more detail on recursion techniques, see [tree](#tree).

* For some problems, we might start searching from the path of **invalid state** or from the entrance of the borders in the graph, rather than the valid state, it might help to reduce the time complexity.

* Build the graph from edges array:
```kotlin
fun buildGraph(edges: Array<IntArray>): HashMap<Int, HashSet<Int>> {
    val graph = hashMapOf<Int, HashSet<Int>>()
    for (edge in edges) {
        val (x, y) = edge
        if (!graph.containsKey(x)) {
            graph[x] = hashSetOf()
        }
        if (!graph.containsKey(y)) {
            graph[y] = hashSetOf()
        }
        graph[x]!!.add(y)
        graph[y]!!.add(x)
    }
    return graph
}
```

#### DFS
* General template:
```kotlin
fun dfs(input, visited) {
    if (visited.contains(input) || meet some conditions) return
    // Record visited nodes
    visited.add(input)
    // Iterate all possible next states
    for (next in input.nexts) {
        // Skip visited nodes
        if (visited.contains(next)) continue
        dfs(next, visited)
    }
}
```

* Template for matrix / 2D array:
```kotlin
// 4 directions
val directions = arrayOf(
    intArrayOf(-1, 0),  // up
    intArrayOf(1, 0),   // down
    intArrayOf(0, -1),  // left
    intArrayOf(0, 1)    // right
)

fun problemSolving(graph: Array<IntArray>) {
    val visited = hashSetOf<Pair<Int, Int>>()
    for (m in 0 until graph.size) {
        for (n in 0 until graph[m].size) {
            // Check some conditions or have not visited before 
            if (graph[m][n] = ...) {
                dfs(graph, m, n, visited)
            }
        }
    }
}

fun dfs(graph: Array<IntArray>, x: Int, y: Int, visited: HashSet<Pair<Int, Int>) {
    // Skip the positions that are out of boundary or 
    // current (x, y) is not what we want or
    // have visited before.
    if (x < 0 || x > graph.size - 1 ||
        y < 0 || y > graph[x].size ||
        graph[x][y] = ... ||
        visited.contains(x to y) ||
        ...) return

    // TODO: Do something with the current position
    
    visited.add(x to y)

    // Then dfs the adjacency vertices
    directions.forEach {
        // Or we can check the boundary here
        dfs(graph, x + it[0], y + it[1])
    }
}
```

* BFS: We enqueue initial state first, then iterate the current state popped from the queue and enqueue **next state** generated from current state.

```kotlin
fun bfs(input, visited) {
    val queue = ArrayDeque<XXX>()
    // Add initial state
    queue.addLast(input)
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val node = queue.removeFirst()

            // Skip the positions that are out of boundary or 
            // have visited before or does not meet requirement.
            if (x < 0 || x > graph.size - 1 ||
             y < 0 || y > graph[x].size ||
              visited.contains(x to y) ||
               ...) continue
               
            // Record visited nodes
            visited.add(node)
            // Do something

            // Enqueue next level elements
            for (next in node.nexts) {
                if (visited.contains(next)) continue
                queue.addLast(next)
            }
        }
    }
}
```

> **Note**: Sometime we will use *shortest distance* as visited, we only enqueue the next node with shortest distance from the current node.

```kotlin
if (distance[next] >= distance[current] + 1) {
    distance[next] = distance[current] + 1
    queue.addLast(next)
}
```

* Corner cases:
    * Empty graph
    * Graph with few nodes (1 or 2)
    * Disjoint graphs
    * Graph with cycle (might not be able to resolve recursively)

## Resources
* https://labuladong.github.io/algo/home/
* https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/

