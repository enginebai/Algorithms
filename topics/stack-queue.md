# Stack & Queue
## Stack
A *stack* is an ordered list in which all insertions and deletions are made at one end, called the *top*. It acts as *Last In First Out (LIFO)*, the last inserted element will be removed first.

```
|A| <- Top
|B|
|C|
|D|
|_|
```

There are some common usages or applications of stack:
1. Back-tracking: **undo** action of editor, or **back** navigation of browser.
2. Compiler parser.
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
[Linked List](../topics/linked-list.md]) takes `O(1)` when inserting/deleting/getting the first item. 

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

#### By Array
```kotlin
class ArrayStack<T>: Stack<T> {

    // Here we don't consider the resize problem.
    private val internalArray = arrayOf<T>()
    private var top = 0;

    override fun create(): Stack<T> = ArrayStack()

    override fun push(item: T): Stack<T> {
        internalArray[top++] = item
        return this
    }

    override fun pop(): T? {
        return if (isEmpty()) null else {
            val itemToPop = internalArray.getOrNull(--top)
            internalArray[top] = null
            return itemToPop
        };
    }

    override fun top(): T? {
        return if (isEmpty()) null else internalArray.getOrNull(top - 1)
    }

    override fun isEmpty(): Boolean = (top == 0)
}
```

## Queue
A *queue* is an order list in which all insertions take place at one end, called the *rear*, while all deletions take place at aonther end, called the *front*. It acts as *First In First Out (FIFO)*, the first inserted element will be removed first.

``` 
        -------
Front <- ABCDE <- Rear
        -------
```

### ADT

```kotlin
interface Queue<T> {
    fun create(): Queue<T>
    fun enqueue(item: T): Queue<T>
    fun dequeue(): T?
    fun isEmpty(): Boolean
}

```

## Resources
- [ ] Fundamental of Data Structure
- [ ] CLRS (Simple)
- [ ] CTCI
- [ ] [Coursera: Algorithm, Princeton](https://www.coursera.org/learn/algorithms-part1/home/week/2) // Introductory videos
- [ ] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-4) // Simple video + coding questions
- [ ] [基本資料結構系列文章](http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html) // Introductory note + illustration
- [ ] https://github.com/youngyangyang04/leetcode-master#%E6%A0%88%E4%B8%8E%E9%98%9F%E5%88%97 // Note with illustration
- [ ] [LC Learn](https://leetcode.com/explore/learn/card/queue-stack/)
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Queue)
- [ ] [LC Top Interview Questions](https://leetcode.com/explore/interview/) // Coding questions collection with easy/medium/hard levels
- [ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#stack) // Simple note
- [ ] Tech Interview Handbook - [Queue](https://www.techinterviewhandbook.org/algorithms/queue) & [Stack](https://www.techinterviewhandbook.org/algorithms/stack) // Simple note
- [ ] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/basic-data-structure // Simple note
- [ ] https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#stacks-and-queues // Simple note