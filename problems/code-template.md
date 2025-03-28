# Code Templates

## Common Code Snippets
```kotlin
// Grid directions
private val directions = arrayOf(
    intArrayOf(-1, 0),  // Up
    intArrayOf(1, 0),   // Down
    intArrayOf(0, -1),  // Left
    intArrayOf(0, 1)    // Right
)

"string".reversed()

fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

// Count the lower letters
val count = IntArray(26)
for (c in s) {
    count[c - 'a']++
}

// Check if the string is a palindrome
private fun String.isPal(): Boolean {
    var i = 0
    var j = this.length - 1
    while (i < j) {
        if (this[i] != this[j]) return false
        i++
        j--
    }
    return true
}
```

## Subarray
### Iterate K size 
* Brute force
```kotlin
fun subarray(nums: IntArray, k: Int) {
    val n = nums.size
    for (i in 0..n - k) {
        for (j in i until i + k) {
            print("${nums[j]}, ")
        }
        println()
    }
}
```
* Fixed window
```kotlin
// General sliding window
val n = nums.size
var left = 0
var right = 0
for (right in 0 until n) {
    // Expand the window

    // Shrink the window
    if (right - left + 1 > k) {
        // Shrink the window
        left++
    }
}

// Fixed-size sliding window
for (right in 0 until n) {
    // Expand the window

    if (right >= k) {
        // Shrink the window
    }

    if (right >= k - 1) {
        // Calculate the result
    }
}
```

## Binary Search
```kotlin
fun binarySearch(nums: IntArray, target: Int): Int {
    var left = TODO()
    var right = TODO()
    while (left <= right) {
        val mid = left + (right - left) / 2
        // TODO: Implement the binary search logic here
    }
    return -1
}
```

## Grid - DFS
```kotlin
val m = grid.size
val n = grid[0].size
val visited = HashSet<Pair<Int, Int>>()
for (i in 0 until m) {
    for (j in 0 until n) {
        if (grid[i][j] == TODO) {
            dfs(grid, i, j, visited)
        }
    }
}

fun dfs(grid: Array<IntArray>, x: Int, y: Int, visited: HashSet<Pair<Int, Int>>) {
    val m = grid.size
    val n = grid[0].size
    if (x !in 0 until m) return
    if (y !in 0 until n) return
    if (grid[x][y] != 1) return
    if (visited.contains(x to y)) return

    visited.add(x to y)

    for (d in directions) {
        dfs(grid, x + d[0], y + d[1]) 
    }
}
```     

## Grid - Area
Calculate the area of `1's` in the grid.
> TODO

## Grid - Component Counts
Count the number of components in the grid.
> TODO

## Build Graph from Edges
> TODO
```kotlin
// Undirected graph
private fun buildGraph(edges: Array<IntArray>): HashMap<Int, HashSet<Int>> {
    val graph = hashMapOf<Int, HashSet<Int>>()
    for (edge in edges) {
        val a = edge[0]
        val b = edge[1]
        graph.computeIfAbsent(a) { hashSetOf() }.add(b)
        // Remove the following line if the graph is directed
        graph.computeIfAbsent(b) { hashSetOf() }.add(a)
    }
    return graph
}
```

## Grid BFS
```kotlin
// in main function
val m = grid.size
val n = grid[0].size
val queue = ArrayDeque<Pair<Int, Int>>()
val visited = HashSet<Pair<Int, Int>>()
for (i in 0 until m) {
    for (j in 0 until n) {
        if (grid[i][j] == TODO) {
            queue.add(i to j)
            visited.add(i to j)
        }
    }
}

// Check after poppping out the queue
while (queue.isNotEmpty()) {
    val (x, y) = queue.removeFirst()
    if (x !in 0 until m) continue
    if (y !in 0 until n) continue
    if (grid[x][y] != 1) continue
    if (visited.contains(x to y)) continue
    visited.add(x to y)
    for (d in directions) {
        val newX = x + d[0]
        val newY = y + d[1]
        queue.addLast(newX to newY)
    }
}

while (queue.isNotEmpty()) {
    val (x, y) = queue.removeFirst()
    for (d in directions) {
        val newX = x + d[0]
        val newY = y + d[1]

        // Check at adjacent cells
        if (newX !in 0 until m) continue
        if (newY !in 0 until n) continue
        if (grid[newX][newY] != 1) continue
        if (visited.contains(newX to newY)) continue

        queue.add(newX to newY)
        visited.add(newX to newY)
    }
}
```

## BFS Shortest Path
```kotlin
```

## Return the Answer Modulo 1e9 + 7
```kotlin
var answer = 0L // Define as Long

// Calculate the answer from the current result
answer += (...).toLong()
answer %= 1_000_000_007

return answer.toInt()
```

## Tree BFS
```kotlin
fun bfs(root: TreeNode?) {
    if (root == null) return
    val queue: ArrayDeque<TreeNode> = ArrayDeque()
    queue.offer(root)
    while (queue.isNotEmpty()) {
        val size = queue.size
        repeat(size) {
            val node = queue.poll()
            // TODO: Implement the BFS logic here
        }
    }
}
```

## Find the Largest Two Numbers
```kotlin
// Allow duplicates
fun findLargestTwoNumbers(nums: IntArray) {
    var first = Int.MIN_VALUE
    var second = Int.MIN_VALUE
    for (num in nums) {
        // We use >= to allow duplicates, if we want to skip duplicates, we can use >
        if (num >= first) {
            second = first
            first = num
        } else if (num >= second) {
            second = num
        }
    }
}
```

## Two Sum in Sorted Array
Two pointers approach to check if two elements that can sum up to `k`.

```kotlin
fun twoSum(A: IntArray, k: Int): Boolean {
    var left = 0
    var right = A.size - 1
    while (left < right) {
        val sum = A[left] + A[right]
        if (sum == k) return true
        else if (sum < k) left++
        else right--
    }
    return false
}
```

## Reverse Array in Specific Range
Avoid iterating `i` from `start` to `end`, it's error-prone.

```kotlin
fun reverseArray(nums: IntArray, start: Int, end: Int) {
    var left = start
    var right = end
    while (left < right) {
        nums.swap(left, right)
        left++
        right--
    }
}
```

## Diagonal Traversal
> Works, but need to understand how it works.
```kotlin
fun diagonalTraversal(grid: Array<IntArray>) {
    // Traverse bottom-left diagonals
    /**
    * 1
    * 2 3
    * 4 5 6
    */ 
    for (k in 0 until n) { // k represents the diagonal offset from the main diagonal
        for (i in k until n) {
            val j = i - k
            println(grid[i][j])
        }
    }

    // Traverse top-right diagonals
    /**
     * 7 8 9
     *   5 6
     *     4
     */
    for (k in 1 until n) { // k represents the diagonal offset from the main diagonal (starting from 1)
        for (j in k until n) {
            val i = j - k
            diagonal.add(grid[i][j])
        }
    }
}
```

## Get `k` Nodes
Get the first `k` nodes from the linked list and disconnect the rest. If `k` is more than the length of the linked list, return the whole linked list.
```kotlin
fun getTotalNodes(head: ListNode?, k: Int): ListNode? {
    if (head == null || k == 0) return null
    var i = 0
    var previous: ListNode? = null
    var current: ListNode? = head
    while (current != null && i < k) {
        previous = current
        current = current.next
        i++
    }
    previous?.next = null
    return head
}
```

## Special
```kotlin
/**
 Replace character with the next or previous character in the alphabet.
 For example, 'a' -> 'b' or 'a' -> 'z'.
 */
// Helper function to compute cyclic cost between two characters.
fun cost(a: Char, b: Char): Int {
    val diff = kotlin.math.abs(a - b)
    return minOf(diff, 26 - diff)
}
```

## CodeForces
```kotlin
import java.io.PrintWriter
import java.util.StringTokenizer

fun readInt() = read().toInt()
fun readDouble() = read().toDouble()
fun readLong() = read().toLong()
fun readStrings(n: Int) = List(n) { read() }
fun readLines(n: Int) = List(n) { readln() }
fun readInts(n: Int) = List(n) { read().toInt() }
fun readIntArray(n: Int) = IntArray(n) { read().toInt() }
fun readDoubles(n: Int) = List(n) { read().toDouble() }
fun readDoubleArray(n: Int) = DoubleArray(n) { read().toDouble() }
fun readLongs(n: Int) = List(n) { read().toLong() }
fun readLongArray(n: Int) = LongArray(n) { read().toLong() }

val INPUT = System.`in`
val OUTPUT = System.out

val _reader = INPUT.bufferedReader()
var _tokenizer: StringTokenizer = StringTokenizer("")

fun read(): String {
    while (_tokenizer.hasMoreTokens().not()) {
        _tokenizer = StringTokenizer(_reader.readLine() ?: return "", " ")
    }
    return _tokenizer.nextToken()
}

val _writer = PrintWriter(OUTPUT, false)
inline fun output(block: PrintWriter.() -> Unit) {
    _writer.apply(block).flush()
}

fun main() {
    output {
        // TODO: solve the problem here.
    }
}
```