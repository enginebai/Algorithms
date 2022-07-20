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