# Stack & Queue
## Stack
A *stack* is an ordered list in which all insertions and deletions are made at one end, called the *top*. It implements a *Last In First Out (LIFO)* policy, the last inserted element will be removed first.

```
|A| <- Top
|B|
|C|
|D|
|_|
```

There are some common usages or applications of stack:
1. Backtracking: depth-first search (DFS), maze path, **undo** action of editor, or **back** navigation of browser.
2. Expression evaluation and syntax parsing.
3. Recursion or function call.

### ADT
```kotlin
interface Stack<T> {
    fun create(): Stack<T>
    // Push an item into the top of this stack.
    fun push(item: T)
    // Remove the item at the top and return that item.
    fun pop(): T?
    // Return the item at the top without removing it from stack.
    fun peek(): T?
    // Test if this stack is empty.
    fun isEmpty(): Boolean
}
```

### Implementation
#### By Linked List

```kotlin
data class Node<T>(
    val data: T,
    var next: Node<T>? = null
)

class LinkedListStack<T>: Stack<T> {

    private var head: Node<T>? = null

    override fun create(): Stack<T> = LinkedListStack()

    override fun push(item: T) {
        val newNode = Node(data = item, next = head)
        head = newNode
    }

    override fun pop(): T? {
        val item = head?.data
        head = head?.next
        return item
    }

    override fun peek(): T? = head?.data
    override fun isEmpty(): Boolean = head == null
}
```

* **Time Complexity**: All operations take `O(1)` time, since [linked list](../topics/linked-list.md]) takes `O(1)` when inserting/deleting/getting the first item, however, it needs the extra space for storing `next` node.


#### By Array

> NOTE: We can use 0 (first free index) or -1 (last used index) for `top`.

```kotlin
class StaticArrayStack<T>(private val capacity: Int): Stack<T> {
    private val array = arrayOfNulls<T>(capacity)
    private var top = 0

    override fun push(item: T) {
        if (top == capacity) throw StackOverflowException("Stack is full")
        array[top++] = item
    }

    override fun pop(): T? {
        if (isEmpty()) throw StackUnderflowException("Stack is empty")
        // Top will be ahead by one when calling push(), so we have to decrement first
        return array.getOrNull(--top)
    }

    override fun peek(): T? {
        if (isEmpty()) throw StackUnderflowException("Stack is empty")
        return array.getOrNull(top - 1)
    }

    override fun isEmpty(): Boolean = top == 0
}

class DynamicArrayStack<T>: Stack<T> {
    // We skip the amorization and ensureCapacity() + grow() functions.
    private val dynamicArray = arrayListOf<T>()
    private var top = 0

    override fun push(item: T) {
        dynamicArray.add(item)
        top++
    }

    override fun pop(): T? = dynamicArray.getOrNull(--top)
    override fun peek(): T? = dynamicArray.getOrNull(top - 1)
    override fun isEmpty(): Boolean = top == 0
}
```

Every operation takes **amortized** constant time `O(1)` (for dynamic array),and less wasted space (comparing with linked-list implementation).

### Monotonic
A *monotonic* stack is a normal stack whose elements are always increasing or decreasing.

```js
|50| | 0|
|20| |10|
|10| |20|
| 0| |50|
|__| |__|
```

What happen if we're going to push `30` into the above two monotonic stacks? Before pushing `30`, we have to pop the top element until pushing the new item no longer breaks the monotonic condition (always increasing or decreasing).

That is, we have to pop `50` / `0`, `10` respectively off from the above stacks so that we can push `30`.

```js
|30| |  |
|20| |  |
|10| |30|
| 0| |50|
|__| |__|
```

For the problems when we're looking for **the next (previous) greater (less) item than the current one** or **maintain the max/min item and keep the order of items in a range**, we can use monotonic stack, so we don't have to compare items again and again to get the max/min in that range. With one iteration, we can find the next greater/less element of item `i` in `O(n)` time. (Every items will enter/exit the stack once)

## Queue
A *queue* is an order list in which all insertions take place at one end, called the *rear* (tail), while all deletions take place at aonther end, called the *head* (front). It acts as *First In First Out (FIFO)*, the first inserted element will be removed first.

``` 
        -------
Head <- ABCDE <- Rear
        -------
```

There are some applications of queue:
* Breadth-First Search (BFS).
* Operating system job queue.

### ADT

```kotlin
interface Queue<T> {
    // Insert new item into queue
    fun enqueue(item: T)
    // Retrieve and remove the head of this queue
    fun dequeue(): T?
    // Return the head of this queue without removing it
    fun peek(): T?
    fun rear(): T?
    fun isEmpty(): Boolean
}
```

### Implementation
#### By Linked List

```kotlin
class LinkedListQueue<T>: Queue<T> {

    private var head: Node<T>? = null
    private var rear: Node<T>? = null
    private var size = 0

    override fun enqueue(item: T) {
        val newNode = Node(data = item)
        if (isEmpty()) {
            head = newNode
            rear = newNode
        } else {
            rear?.next = newNode
            rear = rear?.next
        }
        size++
    }

    override fun dequeue(): T? {
        if (isEmpty()) return null
        val value = head?.data
        head = head?.next
        if (isEmpty()) rear = null
        size--
        return value
    }

    override fun peek(): T? = head?.data
    override fun rear(): T? = rear?.data
    override fun isEmpty(): Boolean = size == 0
}
```

Here we have to update `head` and `rear` node when enqueue and dequeue, it's the special cases for empty queue. All operations take `O(1)` time.

#### By Array
```kotlin
class StaticArrayQueue<T>(private val capacity: Int) : Queue<T> {

    private val array = arrayOfNulls<Any>(capacity)
    private var head = 0
    private var rear = -1
    private var size = 0

    override fun enqueue(item: T) {
        if (size == capacity) throw OverflowException("Queue is full")
        array[++rear] = item
        size++
    }

    override fun dequeue(): T? {
        if (isEmpty()) throw UnderflowException("Queue is empty")
        size--
        return array.getOrNull(head++) as T?
    }

    override fun peek(): T? = array.getOrNull(head)
    override fun rear(): T? = array.getOrNull(rear)
    override fun isEmpty(): Boolean = size == 0
}

class DynamicArrayQueue<T>: Queue<T> {
    private val dynamicArray = arrayListOf<T>()
    private var head = 0
    private var rear = -1
    private var size = 0

    override fun enqueue(item: T) {
        dynamicArray.add(item)
        rear++
        size++
    }

    override fun dequeue(): T? {
        val value = dynamicArray.getOrNull(head++)
        size--
        return value
    }

    override fun peek(): T? = dynamicArray.getOrNull(head)
    override fun rear(): T? = dynamicArray.getOrNull(rear)
    override fun isEmpty(): Boolean = size == 0
}
```

> Verify if it's still true.
There is a drawback from the above implementation, our size is limited even if we dequeue all elements (we move `head` to the end of array when dequeue, but won't start from 0 again). To solve this case, we introduce [*Circular Queue*](../leetcode/622.design-circular-queue.md).

### Tips for [Problem Solving](../topics/problems-solutions.md#stack--queue)
* For stack question, we can push the index/position or value, remember that we still can get the original value from `array[stack.peek()]` when pushing the index.
* *Monotonic stack*:

    * Code template:
        ```kotlin
        fun problem(nums: IntArray) {
            ...
            val stack = Stack<Int>()
            for (i in 0 until nums.size) {
                // We might change the condition of value comparison.
                // And consider if the condition is strict (<) or not (<=)
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    // We might not pop at once, just peek first
                    val value = stack.pop()
                    
                    // Something we have to check if stack is empty now, and then pop item or peek.

                    // calculate something and update the result
                }
                stack.push(i)
            }
        }
        ```
    * Usually, we will push index to stack rather than array value. And something, we need to push `0` or `-1` at the beginning and the end of array so that we can calculate the first / last item of array. (See [84. Largest Rectangle in Histogram](../leetcode/84.largest-rentangle-in-histogram.md))
    * Classic problems:
        * [496. Next Greater Element I](../leetcode/496.next-greater-element-i.md)
        * [503. Next Greater Element II](../leetcode/503.next-greater-element-ii.md)
        * [739. Daily Temperatures](../leetcode/739.daily-temperatures.md)
        * [42. Trapping Rain Water](../leetcode/42.trapping-rain-water.md)
        * [84. Largest Rectangle in Histogram](../leetcode/84.largest-rentangle-in-histogram.md)

* Stack for recursive call or DFS, queue for BFS.

## Resources
- [X] Fundamental of Data Structure
- [X] CLRS (Simple)
- [ ] CTCI
- [X] [Coursera: Algorithm, Princeton](https://www.coursera.org/learn/algorithms-part1/home/week/2) // Introductory videos
- [X] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-4) // Simple video + coding questions
- [X] [基本資料結構系列文章](http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html) // Introductory note + illustration
- [X] [代码随想录 - 栈与队列](https://github.com/youngyangyang04/leetcode-master#%E6%A0%88%E4%B8%8E%E9%98%9F%E5%88%97) // Note with illustration
- X] [LC Learn](https://leetcode.com/explore/learn/card/queue-stack/)
- [X] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Queue)
- [X] [LC Top Interview Questions](https://leetcode.com/explore/interview/) // Coding questions collection with easy/medium/hard levels
- [ ] ~~[Coding Interview University](https://github.com/jwasham/coding-interview-university#stack)~~ // Simple note
- [X] Tech Interview Handbook - [Queue](https://www.techinterviewhandbook.org/algorithms/queue) & [Stack](https://www.techinterviewhandbook.org/algorithms/stack) // Sample questions only
- [X] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/basic-data-structure // Simple note
- [X] https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#stacks-and-queues // Simple note