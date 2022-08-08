## [155. Min Stack](https://leetcode.com/problems/min-stack/)
We keep the peek value and current min via using two stack, and manipulate at the same time.

```js
// 1. Value stack
// 2. Minimum stack
|-2| |-3|
|-1| |-3|
|-3| |-3|
| 0| | 0|
|__| |__|
```

```kotlin
class MinStack() {
    private val valueStack = Stack<Int>()
    private val minStack = Stack<Int>()

    fun push(`val`: Int) {
        valueStack.push(`val`)
        if (minStack.isEmpty()) {
            minStack.push(`val`)
        } else {
            minStack.push(if (minStack.peek() > `val`) `val` else minStack.peek())
        }
    }

    fun pop() {
        valueStack.pop()
        minStack.pop()
    }

    fun top(): Int = valueStack.peek()
    fun getMin(): Int = minStack.peek()
}
```

----
## [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/)

We will keep the value stack to be the "right" order in queue.

```kotlin
class MyQueue() {
    private val valueStack = Stack<Int>()
    private val helperStack = Stack<Int>()

    // Time: O(n), space: O(n)
    fun push(x: Int) {
        while (valueStack.isNotEmpty()) {
            helperStack.push(valueStack.pop())
        }
        valueStack.push(x)
        while (helperStack.isNotEmpty()) {
            valueStack.push(helperStack.pop())
        }
    }

    // Time: O(1), space: O(1)
    fun pop(): Int {
        return valueStack.pop()
    }

    // Time: O(1), space: O(1)
    fun peek(): Int {
        return valueStack.peek()
    }

    // Time: O(1), space: O(1)
    fun empty(): Boolean = valueStack.isEmpty()
}
```

----
## [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/)

```kotlin
class MyStack() {

    private var valueQueue = java.util.LinkedList<Int>()
    private var backupQueue = java.util.LinkedList<Int>()
    private var top: Int? = null

    fun push(x: Int) {
        top = x
        valueQueue.add(x)
    }

    fun pop(): Int {
        // Move all items to backup queue, and leave the rear item to pop
        while (valueQueue.size > 1) {
            top = valueQueue.remove()
            backupQueue.add(top!!)
        }
        val value = valueQueue.remove()

        // Copy backup queue back to value queue
        val temp = backupQueue
        backupQueue = valueQueue
        valueQueue = temp
        return value
    }

    fun top(): Int = top!!
    fun empty(): Boolean = valueQueue.isEmpty()
}
```

----
## [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

```kotlin
fun isValid(s: String): Boolean {
    val mapping = mapOf(')' to '(', ']' to '[', '}' to '{')
    val stack = java.util.Stack<Char>()
    for (c in s) {
        if (mapping.containsKey(c)) {
            // Only encounter right parenthese
            if (stack.isEmpty()) return false
            val left = stack.pop()
            if (mapping[c] != left) {
                return false
            }
        } else {
            stack.push(c)
        }
    }
    return stack.isEmpty()
}
```

> Mind the case: `}}{{` (right parentheses first)

----
## [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)

A valid parentheses pair meets two requirements:
1. The number of left and right parentheses are equal.
2. The left parenthese number >= right in the prefix of valid parenthese pair, such `((()`, `((())` or `((()))`.

And final result will meets the requirement: left parentheses total number is equal to right one.

### DFS (Backtracking)
We use (`left`, `right`) (the parentheses number) to represent every node, we can either choose left or right parenthese, that will form a full binary tree for every possible combination.

> (3, 2) means 3 `(` and 2 `)` in the string.

But we have to meet the valid rules (tree pruning):
1. If `left` (left parentheses number) < `n`, we can put `(`.
2. If `right` < `left`, we can put `)`.

And we can run either DFS or BFS to traverse every node to form the result string. 
Here we use DFS:
1. We start from `left` = 0 and `right` = 0, `str` is empty.
2. If `left` < `n`, we can put `(` and we increment `left`.
3. If `right` < `left` (not match), then put `)` to match and increment `right`.
4. If `left` = `right` = `n`, return the `str`. (A valid pair)

> You can draw a binary tree for `n = 2`, that would be really helpful!

```kotlin
class Solution {

    private val results = mutableListOf<String>()

    fun generateParenthesis(n: Int): List<String> {
        dfs(n, 0, 0, "")
        return result
    }

    private fun dfs(n: Int, left: Int, right: Int, str: String) {
        // Print the node
        if (left == n && right == n) {
            results.add(str)
        } else {
            // Traverse left subtree
            if (left < n) {
                dfs(n, left + 1, right, str + "(")
            } 
            // Traverse right substree
            if (right < left && right < n) {
                dfs(n, left, right + 1, str + ")")
            }
        }
    }
}
```

Or we can use remaining count:

```kotlin
private val results = mutableListOf<String>()

fun generateParenthesis(n: Int): List<String> {
    dfs(n - 1, n, "(")
    return results        
}

private fun dfs(left: Int, right: Int, str: String) {
    if (left > right) return
    if (left == 0 && right == 0) {
        results.add(str.toString())
        return
    }
    if (left > 0) dfs(left - 1, right, str + "(")
    if (right > 0) dfs(left, right - 1, str + ")")
}
```

----
## [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)

When using monotonic stack, we can reduce the time complexity down to `O(n)`.

* We push the day index into stack if the temperature is colder than previous day.
* We pop all day index if current the warner, and update the result.

```kotlin
fun dailyTemperatures(temperatures: IntArray): IntArray {
    val results = IntArray(temperatures.size)
    // We use day as item
    val monotonicStack = Stack<Int>()
    for (day in 0 until temperatures.size) {
        // Pop all item violating monotonic property
        while (!monotonicStack.isEmpty() && temperatures[monotonicStack.peek()] < temperatures[day]) {
            val previousDay = monotonicStack.pop()
            results[previousDay] = day - previousDay
        }
        monotonicStack.push(day)
    }
    while (!monotonicStack.isEmpty()) {
        results[monotonicStack.pop()] = 0
    }
    return results
}
```

----
## [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

### Dynamic Programming
We can improve the running time from brute force solution, we store every left max and right max for every position (memoization), the time complexity reduces to `O(n)`, but space compexity increase to `O(n)` as well. Then iterate to calculate the result for every position.

```kotlin
fun trap(height: IntArray): Int {
    var result = 0
    var leftMax = 0
    var rightMax = 0
    val leftMaxs = IntArray(height.size)
    val rightMaxs = IntArray(height.size)

    // Find the left max height for every position
    for (current in 0 until height.size) {
        leftMax = if (height[current] > leftMax) height[current] else leftMax
        leftMaxs[current] = leftMax
    }
    // Find the right max height for every position
    for (current in height.size - 1 downTo 0) {
        rightMax = if (height[current] > rightMax) height[current] else rightMax
        rightMaxs[current] = rightMax
    }

    // Then calculate the result for every position from above left/right max height.
    for (current in 0 until height.size) {
        result += (if (leftMaxs[current] > rightMaxs[current]) rightMaxs[current] else leftMaxs[current]) - height[current]
    }
    return result
}
```

### Monotonic Stack
We use monotonic stack to trace the maximum area for trapping.

```kotlin
fun trap(height: IntArray): Int {
    // We store the index
    val stack = Stack<Int>()
    var trap = 0
    // Loop as right wall
    for (i in 0 until height.size) {
        // When we find a valid right wall
        while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
            val groundIndex = stack.pop()
            
            // If there is not left wall, then can't trap.
            if (stack.isEmpty()) break
            
            val leftIndex = stack.peek()

            // For [7 1 4] we can trap max height to 4, not 7.
            val minHeight = (if (height[leftIndex] > height[i]) height[i] else height[leftIndex]) - height[groundIndex]
            val width = i - leftIndex - 1
            trap += minHeight * width
        }
        stack.push(i)
    }
    return trap
}
```

----
## [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/)

* We can use monotonic stack (decreasing) to track the next greater element, for example, `[3, 2, 1, 6, 5]` we will push `3`, `2`, `1` but pop `3`, `2`, `1` when encountering `6`, means that the next greater element for `3`, `2`, `1` is `6`.
* We use hash table to track the item and its next greater element.

### Monotonic Stack
```kotlin
fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    // Store the value and its next greater element
    val hashTable = hashMapOf<Int, Int>()
    val stack = Stack<Int>()
    for (i in 0 until nums2.size) {
        val value = nums2[i]
        while (!stack.isEmpty() && stack.peek() < value) {
            hashTable[stack.pop()] = value
        }
        stack.push(value)
    }
    val results = IntArray(nums1.size)
    for (i in 0 until nums1.size) {
        results[i] = hashTable[nums1[i]] ?: -1
    }
    return results
}
```

* **Time Complexity**: `O(m + n)` for m, n is the size of two arrays.
* **Space Complexity**: `O(m + n)`, `O(n)` for stack and hash table, `O(m)` for results.

----
## [503. Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/)

```kotlin
fun nextGreaterElements(nums: IntArray): IntArray {
    val results = IntArray(nums.size) { _ -> -1 }
    val stack = Stack<Int>()
    for (i in 0 until nums.size * 2) {
        val nextGreaterElement = nums[i % nums.size]
        while (!stack.isEmpty() && nums[stack.peek()] < nextGreaterElement) {
            results[stack.pop()] = nextGreaterElement
        }
        stack.push(i % nums.size)
    }
    return results
}
```
----
## [1249. Minimum Remove to Make Valid Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/)

```kotlin
fun minRemoveToMakeValid(s: String): String {
    if (s.isEmpty()) return ""
    val toRemoveIndexes = hashSetOf<Int>()
    // left parentheses index
    val stack = Stack<Int>()
    // Scan the string to find invalid right parentheses
    for (i in 0 until s.length) {
        val c = s[i] 
        if (c == '(') {
            stack.push(i)
        } else if (c == ')') {
            if (stack.isEmpty()) { 
                toRemoveIndexes.add(i)
            } else {
                stack.pop()
            }
        }
    } 
    // Invalid left parentheses
    while (!stack.isEmpty()) {
        toRemoveIndexes.add(stack.pop())
    }
    
    val answer = StringBuilder()
    for (i in 0 until s.length) {
        if (toRemoveIndexes.contains(i)) continue
        else answer.append(s[i])
    }
    return answer.toString()
}
```

* **Time Complexity**: `O(n)`, to iterate the string for scanning and building the result.
* **Space Complexity**: `O(n)` for hash table and stack.

> Another solution, we can iterate the string from left to right, to remove invalid right parentheses, then iterate from right to left to remove the invalid left parentheses.

----
## [394. Decode String](https://leetcode.com/problems/decode-string/)

Let's use `30[a20[bc]d]efg` as example:
* We iterate all character and determine what the character is.
    * For number digit, we will iterate until we meet `[`. and push the number, for example `123[`, we will parse to be `123` and push `123`.
    * For `[` or letter, just push into stack.
    * For `]`, we will pop out until we meet the number in stack, (it might be `123 / [ / abc` in stack when meeting `]` at that moment) and we will copy that string times (it will be "adb" x 100 times and push back the result into stack.
* Once we finish all strings copy times, we pop out from stack to form the result string.

```kotlin
fun decodeString(s: String): String {
    // x100[a99[bc]y]z
    val stack = Stack<String>()
    var number = 0
    for (i in 0 until s.length) {
        if (s[i].isDigit()) {
            // We can't call `toInt()` which returns the Ascii code number.
            number = (number * 10) + (s[i] - '0')
        } else if (s[i] == '[') {
            stack.push(number.toString())
            // Remember to reset the number
            number = 0
            stack.push(s[i].toString())
        } else if (s[i] == ']') {
            val strList = mutableListOf<String>()
            while (stack.isNotEmpty() && stack.peek() != "[") {
                strList.add(0, stack.pop())
            }
            // Pop out "["
            stack.pop()
            // Pop out the number
            val copyTimes = stack.pop().toInt()
            val str = strList.joinToString("")
            val copyStr = StringBuilder()
            for (j in 0 until copyTimes) {
                copyStr.append(str)
            }
            stack.push(copyStr.toString())
        } else {
            stack.push(s[i].toString())
        }
    }

    val result = mutableListOf<String>()
    while (stack.isNotEmpty()) {
        result.add(0, stack.pop())
    }
    return result.joinToString("")
}
```