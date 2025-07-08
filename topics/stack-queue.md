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
    val value: T,
    var next: Node<T>? = null
)

class LinkedListStack<T>: Stack<T> {

    // head is the top of the stack
    private var head: Node<T>? = null

    override fun push(item: T) {
        val newNode = Node(value = item, next = head)
        head = newNode
    }

    override fun pop(): T? {
        val item = head?.value
        head = head?.next
        return item
    }

    override fun peek(): T? = head?.value
    override fun isEmpty(): Boolean = head == null
}
```

* **Time Complexity**: All operations take `O(1)` time, since [linked list](../topics/linked-list.md]) takes `O(1)` when inserting/deleting/getting the first item, however, it needs the extra space for storing `next` node.


#### By Array

> NOTE: We can use `-1` (last used index) or `0` (first free index) for `top`.

```kotlin
class StaticArrayStack<T>(private val capacity: Int): Stack<T> {
    private val array = arrayOfNulls<T>(capacity)
    // Top is the current written index
    private var top = -1

    override fun push(item: T) {
        if (top + 1 == capacity) throw StackOverflowException("Stack is full")
        array[++top] = item
    }

    override fun pop(): T? {
        if (isEmpty()) throw StackUnderflowException("Stack is empty")
        return array.getOrNull(top--)
    }

    override fun peek(): T? {
        if (isEmpty()) throw StackUnderflowException("Stack is empty")
        return array.getOrNull(top)
    }

    override fun isEmpty(): Boolean = top == -1
}

class DynamicArrayStack<T>: Stack<T> {
    private val dynamicArray = mutableListOf<T>()

    override fun push(item: T) {
        dynamicArray.add(item)
    }

    override fun pop(): T? {
        dynamicArray.removeAt(dynamicArray.size - 1)
    }
    override fun peek(): T? = dynamicArray.lastOrNull()
    override fun isEmpty(): Boolean = dynamicArray.isEmpty()
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
        val newNode = Node(value = item)
        if (rear != null) {
            rear.next = newNode
        } else {
            // Special case when the queue is empty and then enqueue the first item
            head = newNode
        }
        rear = newNode
        size++
    }

    override fun dequeue(): T? {
        val value = head?.value
        head = head?.next
        size--
        // Special case when the queue becomes empty after dequeue the last item
        if (isEmpty()) rear = null
        return value
    }

    override fun peek(): T? = head?.value
    override fun rear(): T? = rear?.value
    override fun isEmpty(): Boolean = size == 0
}
```
All operations take `O(1)` time.

Or equivalently, we can implement using doubly linked list with a sentinel node to avoid the special cases for empty queue. We set up a fixed head and rear node to avoid the null check when enqueue and dequeue. (Same idea from [146. LRU Cache](../leetcode/146.lru-cache.md))

```js
sentinel <-> node1 <-> node2 <-> ... <-> sentinel
   ↑                                        ↑
 head                                     rear
```

```kotlin
class DoublyLinkedListQueue<T>: Queue<T> {
    private var head = Node<T>(value = null)
    private var rear = Node<T>(value = null)

    init {
        // We connect the head and rear together at the beginning
        head.next = rear
        rear.prev = head
    }

    override fun enqueue(item: T) {
        val newNode = Node(value = item)

        val last = rear.prev
        last.next = newNode
        newNode.prev = last

        newNode.next = rear
        rear.prev = newNode
    }

    override fun dequeue(): T? {
        if (isEmpty()) return null
        val first = head.next
        val value = first?.value
    
        val next = first.next
        next?.prev = head
        head.next = next

        return value
    }

    override fun peek(): T? = head.next?.value
    override fun rear(): T? = rear.prev?.value
    override fun isEmpty(): Boolean = head.next == rear
}
```

#### By Array
We can use [*Circular Queue*](../leetcode/622.design-circular-queue.md) for the implementation to reuse the space more efficiently.

## Kotlin APIs
### Stack
```kotlin
// Stack
val stack = Stack<Int>()
stack.push(1)
val item = stack.pop()
stack.peek()
stack.size
stack.isNotEmpty()

// Or we can use ArrayDeque as a stack (recommended)
val stack = ArrayDeque<Int>()
stack.addLast(1)    // push
stack.removeLast()  // pop
stack.last()        // peek
```

### Queue
```kotlin
val queue = ArrayDeque<Int>()
queue.offer(1)
queue.addFirst(1)
queue.addLast(1)

val firstValue = queue.removeFirst()
val lastValue = queue.removeLast()
queue.poll() // removeFirst()
queue.pollLast() // removeLast()

queue.first()
queue.peekFirst()
queue.last()
queue.peekLast()

queue.size
queue.isNotEmpty()
```

| Operation             | `Stack<T>` (Java) | `ArrayDeque<T>`                     | `LinkedList<T>`             | Notes                                             |
| --------------------- | ----------------- | ----------------------------------- | --------------------------- | ------------------------------------------------- |
| **Push (Stack)**      | `push(item)`      | `push(item)`                        | `push(item)`                | `ArrayDeque` & `LinkedList` use `Deque` interface |
| **Pop (Stack)**       | `pop()`           | `pop()`                             | `pop()`                     | Removes first element (top of stack)              |
| **Peek (Stack)**      | `peek()`          | `peek()`                            | `peek()`                    | Returns first without removing                    |
| **Enqueue (Queue)**   | `add(item)`       | `addLast(item)` / `offerLast(item)` | `add(item)` / `offer(item)` | Adds to the rear (tail)                           |
| **Dequeue**           | `remove()`        | `removeFirst()` / `pollFirst()`     | `remove()` / `poll()`       | Removes from the front (head)                     |
| **Peek (Queue)**      | `peek()`          | `peekFirst()`                       | `peek()` / `element()`      | Peeks at front element                            |
| **Is Empty**          | `isEmpty()`       | `isEmpty()`                         | `isEmpty()`                 | –                                                 |
| **Size**              | `size`            | `size`                              | `size`                      | –                                                 |
| **Add to Front**      | –                 | `addFirst(item)`                    | `addFirst(item)`            | For Deque behavior                                |
| **Add to Back**       | –                 | `addLast(item)`                     | `addLast(item)`             | –                                                 |
| **Remove from Front** | –                 | `removeFirst()`                     | `removeFirst()`             | –                                                 |
| **Remove from Back**  | –                 | `removeLast()`                      | `removeLast()`              | –                                                 |
| **Peek Front**        | –                 | `peekFirst()`                       | `peekFirst()`               | –                                                 |
| **Peek Back**         | –                 | `peekLast()`                        | `peekLast()`                | –                                                 |
| **Thread Safe**       | ✅ (synchronized)  | ❌ (not thread-safe)                 | ❌                           | Use `Collections.synchronized...` if needed       |
