# Code Templates

## Common Code Snippets
```kotlin
private val directions = arrayOf(
    intArrayOf(-1, 0),  // Up
    intArrayOf(1, 0),   // Down
    intArrayOf(0, -1),  // Left
    intArrayOf(0, 1)    // Right
)
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

## Grid DFS
```kotlin
private fun dfs(grid: Array<IntArray>, x: Int, y: Int, visited: HashSet<Pair<Int, Int>>) {
    val m = grid.size
    val n = grid[0].size
    if (x !in 0 until m || y !in 0 until n || grid[x][y] != 1 || visited.contains(x to y)) return 
    visited.add(x to y)
    for (dir in directions) {
        val newX = x + dir[0]
        val newY = y + dir[1]
        dfs(grid, newX, newY)
    }
}
```     

## Grid Area
Calculate the area of `1's` in the grid.
```kotlin
fun dfs(grid: Array<IntArray>, x: Int, y: Int, visited: HashSet<Pair<Int, Int>>): Int {
    val m = grid.size
    val n = grid[0].size
    if (x !in 0 until m || y !in 0 until n || grid[x][y] != 1 || visited.contains(x to y) return 0
    visited.add(x to y)
    var area = 1
    directions.forEach { d -> 
        val newX = x + d[0]
        val newY = y + d[1]
        area += dfs(grid, newX, newY, mark)
    }
    return area
}
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

## Grid BFS
```kotlin
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
while (queue.isNotEmpty()) {
    val size = queue.size
    repeat(size) {
        val (x, y) = queue.poll()
        for (dir in directions) {
            val newX = x + dir[0]
            val newY = y + dir[1]
            if (newX in 0 until m && newY in 0 until n && (newX to newY) !in visited) {
                queue.add(newX to newY)
                visited.add(newX to newY)
            }
        }
    }
}
```

## Return the Answer Modulo 1e9 + 7
```kotlin
var answer = 0L // Define as Long

// Calculate the answer from the current result
answer += (...).toLong()
answer %= 1_000_000_007

return answer.toInt()
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