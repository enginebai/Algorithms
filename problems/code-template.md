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

## BFS
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