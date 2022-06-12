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
    fun isEmpty(): Boolean
}
```

### Implementation
#### By Linked List

```kotlin
class LinkedListQueue<T>: Queue<T> {

    private var head: Node<T>? = null
    private var rear: Node<T>? = null

    override fun enqueue(item: T) {
        val newNode = Node(data = item)
        if (isEmpty()) {
            head = newNode
            rear = newNode
        } else {
            rear?.next = newNode
            rear = rear?.next
        }
    }

    override fun dequeue(): T? {
        val value = head?.data
        head = head?.next
        if (isEmpty()) rear = null
        return value
    }

    override fun peek(): T? = head?.data
    override fun isEmpty(): Boolean = (head == null)
}
```

Here we have to update `head` and `rear` node when enqueue and dequeue, it's the special cases for empty queue. All operations take `O(1)` time.

#### By Array
```kotlin
class StaticArrayQueue<T>(private val capacity: Int) : Queue<T> {

    private val array = arrayOfNulls<Any>(capacity)
    private var head = 0
    private var rear = 0

    override fun enqueue(item: T) {
        if (rear == capacity) throw OverflowException("Queue is full")
        array[rear++] = item
    }

    override fun dequeue(): T? {
        if (isEmpty()) throw UnderflowException("Queue is empty")
        return array.getOrNull(head++) as T?
    }

    override fun peek(): T? = array.getOrNull(head) as T?
    override fun isEmpty(): Boolean = (head == rear)
}

class DynamicArrayQueue<T>: Queue<T> {
    private val dynamicArray = arrayListOf<T>()
    private var head = 0
    private var rear = 0

    override fun enqueue(item: T) {
        dynamicArray.add(item)
        rear++
    }

    override fun dequeue(): T? {
        val value = dynamicArray.getOrNull(head++)
        if (head >= rear) {
            head = 0
            rear = 0
        }
        return value
    }

    override fun peek(): T? = dynamicArray.getOrNull(head)
    override fun isEmpty(): Boolean = (head == rear)
}
```

> Verify if it's still true.
There is a drawback from the above implementation, our size is limited even if we dequeue all elements (we move `head` to the end of array when dequeue, but won't start from 0 again). To solve this case, we introduce *Circular Queue*. (See problem)

### Tips for Problem Solving
> TODO: 

## Resources
- [X] Fundamental of Data Structure
- [X] CLRS (Simple)
- [ ] CTCI
- [X] [Coursera: Algorithm, Princeton](https://www.coursera.org/learn/algorithms-part1/home/week/2) // Introductory videos
- [X] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-4) // Simple video + coding questions
- [X] [基本資料結構系列文章](http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html) // Introductory note + illustration
- [ ] [代码随想录 - 栈与队列](https://github.com/youngyangyang04/leetcode-master#%E6%A0%88%E4%B8%8E%E9%98%9F%E5%88%97) // Note with illustration
- [ ] [LC Learn](https://leetcode.com/explore/learn/card/queue-stack/)
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Queue)
- [ ] [LC Top Interview Questions](https://leetcode.com/explore/interview/) // Coding questions collection with easy/medium/hard levels
- [ ] ~~[Coding Interview University](https://github.com/jwasham/coding-interview-university#stack)~~ // Simple note
- [X] Tech Interview Handbook - [Queue](https://www.techinterviewhandbook.org/algorithms/queue) & [Stack](https://www.techinterviewhandbook.org/algorithms/stack) // Sample questions only
- [X] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/basic-data-structure // Simple note
- [X] https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#stacks-and-queues // Simple note