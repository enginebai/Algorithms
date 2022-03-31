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
    fun push(item: T): Stack<T>
    fun pop(): T?
    fun top(): T?
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

class LinkedListStack<T> : Stack<T> {
    
    private var top: Node<T>? = null
    
    override fun create(): Stack<T> = LinkedListStack()

    override fun push(item: T): Stack<T> {
        val newNode = Node(data = item, next = top?.next)
        top = newNode
        return this 
    }

    override fun pop(): T? {
        val popItem = top
        top = top?.next
        return popItem?.data
    }

    override fun top(): T? = top?.data

    override fun isEmpty(): Boolean = (top == null)
}
```

[Linked List](../topics/linked-list.md]) takes `O(1)` when inserting/deleting/getting the first item, however, it needs the extra space for storing `next` node.


#### By Array
```kotlin
const val N = 10

class StaticArrayStack<T>: Stack<T> {

    private val internalArray = arrayOfNulls<T>(N)
    private var top = 0;

    override fun create(): Stack<T> = StaticArrayStack()

    override fun push(item: T): Stack<T> {
        if (top == N) throw OverflowError()
        else {
            internalArray[top++] = item
        }
        return this
    }

    override fun pop(): T? {
        return if (isEmpty()) throw UnderflowError() 
        else internalArray.getOrNull(--top);
    }

    override fun top(): T? {
        return if (isEmpty()) null else internalArray.getOrNull(top - 1)
    }

    override fun isEmpty(): Boolean = (top == 0)
}
```

Every operation takes constants **amortized** time (for dynamic array), and less wasted space.

## Queue
A *queue* is an order list in which all insertions take place at one end, called the *rear*, while all deletions take place at aonther end, called the *front*. It acts as *First In First Out (FIFO)*, the first inserted element will be removed first.

``` 
        -------
Front <- ABCDE <- Rear
        -------
```

There are some applications of queue:
* Breadth-First Search (BFS).
* Operating system job queue.

### ADT

```kotlin
interface Queue<T> {
    fun create(): Queue<T>
    fun enqueue(item: T): Queue<T>
    fun dequeue(): T?
    fun front(): T?
    fun rear(): T?
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

class LinkedListQueue<T>: Queue<T> {

    private var firstNode: Node<T>? = null
    private var lastNode: Node<T>? = null

    override fun create(): Queue<T> = LinkedListQueue<T>()

    override fun enqueue(item: T): Queue<T> {
        val oldLastNode = lastNode
        lastNode = Node(data = item)
        if (isEmpty()) {
            firstNode = lastNode
        } else {
            oldLastNode?.next = lastNode
        }
        return this
    }

    override fun dequeue(): T? {
        val node = this.firstNode
        firstNode = firstNode?.next
        if (isEmpty()) lastNode = null
        return node?.data
    }

    override fun front(): T? = this.firstNode?.data

    override fun rear(): T? = this.lastNode?.data

    override fun isEmpty(): Boolean = (this.firstNode == null)
}
```

Here we have to update `front` and `rear` node when enqueue and dequeue, it's the special cases for empty queue.

#### By Array
```kotlin
const val N = 10

class StaticArrayQueue<T>: Queue<T> {

    private val internalArray = arrayOfNulls<T>(N)
    private var front: Int = 0
    private var rear: Int = 0

    override fun create(): Queue<T> = StaticArrayQueue<T>()

    override fun enqueue(item: T): Queue<T> {
        if (rear == N) throw OverflowError()
        internalArray[rear++] = item
        return this
    }

    override fun dequeue(): T? {
        if (front == rear) throw UnderflowError()
        return internalArray[front++]
    }

    override fun front(): T? = internalArray.getOrNull(front)

    override fun rear(): T? = internalArray.getOrNull(rear - 1)

    override fun isEmpty(): Boolean = (front == rear)
}
```

There is a drawback from the above implementation, our size is limited even if we dequeue all elements (we move `front` to the end of array when dequeue, but won't start from 0 again). To solve this case, we introduce *Circular Queue*:

> TODO: Implementation [622. Design Circular Queue](https://leetcode.com/problems/design-circular-queue/)

## Resources
- [X] Fundamental of Data Structure
- [X] CLRS (Simple)
- [ ] CTCI
- [X] [Coursera: Algorithm, Princeton](https://www.coursera.org/learn/algorithms-part1/home/week/2) // Introductory videos
- [X] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-4) // Simple video + coding questions
- [X] [基本資料結構系列文章](http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html) // Introductory note + illustration
- [/] https://github.com/youngyangyang04/leetcode-master#%E6%A0%88%E4%B8%8E%E9%98%9F%E5%88%97 // Note with illustration
- [/] [LC Learn](https://leetcode.com/explore/learn/card/queue-stack/)
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Queue)
- [ ] [LC Top Interview Questions](https://leetcode.com/explore/interview/) // Coding questions collection with easy/medium/hard levels
- ~~[ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#stack)~~ // Simple note
- [X] Tech Interview Handbook - [Queue](https://www.techinterviewhandbook.org/algorithms/queue) & [Stack](https://www.techinterviewhandbook.org/algorithms/stack) // Sample questions only
- [X] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/basic-data-structure // Simple note
- [X] https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#stacks-and-queues // Simple note