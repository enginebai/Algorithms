# Linked List
A *linked list* is a pointer-based data structure that store each item in a *node* with two properties: `item` storing the data itself, and `next` storing the address of next item. 

![Linked List](../media/linked-list.png)

> For linked list, we don't have to store item in contiguous memory address like array anymore, we can store item anywhere in memory since we have pointer to next item.

## Pros. & Cons.
| Pros.                                                                   | Cons.                                                            | Usages                                                                                           |
|-------------------------------------------------------------------------|------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| 1. Fast when inserting/deleting at the beginning.<br>2. Easy to resize. | 1. Bad at random access<br>2. Extra space to store `next` field. | 1. Dynamic size<br>2. Insert/delete frequently<br>3. Don't need to access element very much. |

> Comparison to array, see [Array](../topics/array.md) topic.

## Singly Linked List

### ADT
```kotlin
data class Node<T>(
    val value: T,
    var next: Node<T>? = null
)

interface LinkedList<T> {
    val head: Node<T>
}
```

### Operations
For the following operations, we have to mind the boundary and corner cases:
* Empty linked list (`head` is null)
* Linked list with only one or two nodes.
* Head or last node.

#### Insertion
Linked list takes `O(1)` for inserting or deleting first item simply by relinking the pointer.

![Linked List Insert](../media/linked-list-insert.png)

```kotlin
fun LinkedList.insertFirst(value: T) {
    val newNode = Node(value)
    newNode.next = this.head
    this.head = newNode
}
```

It also takes `O(1)` to insert after a specific node.

```kotlin
fun LinkedList.insertAfter(node: Node, value: T) {
    val newNode = Node(value)
    newNode.next = node.next
    node.next = newNode
}
```

However, it takes `O(n)` to insert a new node at the index `i` or the end, because it has to iterate some (all) nodes to find the node to insert.

```kotlin
fun LinkedList.insertLast(value: T) {
    val newNode = Node(value)
    var lastNode = this.head

    // We have to consider the empty list since we can't find the last node, the insertion will not execute below.
    if (lastNode == null) {
        this.head = newNode
        return
    }

    while (lastNode?.next != null) {
        lastNode = lastNode.next
    }
    lastNode?.next = newNode
}

fun LinkedList.insertAt(index: Int, value: T) {
    val newNode = Node(value)
    if (index == 0) {
        newNode.next = this.head?.next
        this.head = newNode
        return
    }

    // Find the (index - 1)-th node
    var node = this.head
    for (i in 0 until index - 1) {
        node = node?.next
    }
    newNode.next = node?.next
    node?.next = newNode
}
```

#### Deletion
![Linked List Delete](../media/linked-list-delete.png)
To delete the first node, we simply **assign the next node to head**, it takes `O(1)`.

> We have to mind the boundary, such as first or last.

```kotlin
fun LinkedList.deleteFirst() {
    val nextNode = this.head?.next
    this.head = nextNode
}
```

To delete specific node from the linked list, we have to iterate to find the previous node before the node to delete and relink to delete. It takes `O(n)` because it iterates the list to locate the node to delete.

```kotlin
fun LinkedList.delete(node: Node) {
    var nodeToDelete = this.head
    var previousNode: Node? = null

    // We find the node to delete at the beginning.
    if (this.head != null && this.head.data == node.data) {
        head = null
        return
    }

    // Iterate to find the node to delete
    while (nodeToDelete != null && nodeToDelete?.data != node.data) {
        previousNode = nodeToDelete
        nodeToDelete = nodeToDelete.next
    }

    // If we found it, relink the node
    if (nodeToDelete != null) {
        previousNode = nodeToDelete.next
    }
}
```

To delete at the specific index, we have to iterate the linked list to locate the previous node of that index, it also takes `O(n)`.

```kotlin
fun LinkedList.deleteAt(indexToDelete: Int) {
    if (indexToDelete == 0) {
        this.head = this.head?.next
        return
    }

    // Equivalence to find the previous node of index-th:
    // var node = this.head
    // for (i in 0 until index - 1) {
    //    node = node?.next
    // }
    // See `insertAt()` function above.
    var currentIndex = 0
    var previousNode: Node? = null
    var currentNode: Node? = this.head
    while (currentIndex < indexToDelete && currentNode != null) {
        currentIndex++
        previousNode = currentNode
        currentNode = currentNode.next
    }

    // We actually find the index to delete. If current node is null, that means the index exceeds the size.
    if (currentIndex == indexToDelete && currentNode != null) {
        previousNode?.next = currentNode.next
    }
}

fun LinkedList.deleteLast() {
    var previous: Node<T>? = null
    var current = this.head

    // For linked list has only one node
    if (current?.next == null) {
        this.head = null
        return
    }
    while (current?.next != null) {
        previous = current
        current = current.next
    }
    if (previous != null && current != null) {
        previous.next = current.next
    }
}
```

#### Get Length
There are two ways: iterative and recursive to calculate the number of nodes, both take `O(n)` time complexity.

We start with iterative way, it's very straightforward:

```kotlin
fun LinkedList.getSize(): Int {
    var size = 0
    var node = this.head

    while (node != null) {
        size++
        node = node.next
    }
    return size
}
```

And for recursive way, suppose we define the function `getSize(node: Node): Int`:
1. If `head` is null, return 0.
2. Else return `1 + getSize(node.next)`.

```kotlin
fun LinkedList.getSize(node: Node = this.head): Int {
    if (node == null) return 0
    else return 1 + getSize(node.next)
}
```

#### Search
```kotlin
fun LinkedList.search(value: T): Boolean {
    var node = this.head
    while (node != null) {
        if (node.value == value) return true
        node = node.next
    }
    return false
}

fun LinkedList.searchRecursively(node: Node? = head, value: T): Boolean {
    if (node == null) return false
    return if (node.value == value) true
    else searchRecursively(node.next, value)
}
```
## Doubly Linked List
It contains the extra *previous* pointer and the data + *next* pointer in singly linked list.

![Doubly Linked List](../media/doubly-linked-list.png)

### ADT
```kotlin
data class Node<T>(
    var value: T? = null,
    var previous: Node<T>? = null,
    var next: Node<T>? = null
)
```

> // TODO: add `tail` node to linked list
> ```
> interface LinkedList<T> {
>    val head: Node<T>
>    val tail: Node<T>
> }
> ```
>
> Then update the following operations for this `tail` update.

### Operations

#### Insertion
```kotlin
// Origin is `head <- -> node` becomes
// `head <- newNode -> node`
fun LinkedList.insertFirst(value: T) {
    val newNode = Node(value, next = this.head, previous = null)
    this.head?.previous = newNode
    this.head = newNode
}

// Origin is `node <- -> null` becomes
// `node <- -> newNode <- -> null`
fun LinkedList.insertLast(value: T) {
    val newNode(value, next = null)
    var lastNode = this.head
    if (lastNode == null) {
        this.head = newNode
        return
    }

    while (lastNode?.next != null) {
        lastNode = lastNode.next
    }
    newNode.previous = lastNode
    lastNode?.next = newNode
}

// Origin is `node <- -> next` becomes
// `node <- -> newNode <- -> next`
fun LinkedList.insertAfter(node: Node, value: T) {
    val nextNode = node?.next
    val newNode = Node(value, next = nextNode, previous = node)
    nextNode?.previous = newNode
    node?.next = newNode
}

fun LinkedList.insertAt(index: Int, value: T) {
    if (index == 0) {
        insertFirst()
        return
    }
    var i = 0
    var node = this.head
    while (i < index && node != null) {
        i++
        node = node.next
    }

    if (i == index && node != null) {
        val nextNode = node.next
        val newNode = Node(value, next = nextNode, previous = node)
        nextNode.previous = newNode
        node.next = newNode
    }
}
```

#### Deletion
```kotlin
// head = `node <- -> next` (null) becomes
// head = `next <- -> next.next` (null)
fun LinkedList.deleteFirst() {
    val nextNode = this.head?.next
    nextNode?.previous = null
    this.head = nextNode
}

// `previous <- -> last <- -> null` becomes
// `previous <- -> null`
fun LinkedList.deleteLast() {
    var node = this.head
    if (node?.next == null) {
        deleteFirst()
        return
    }
    while (node?.next != null) {
        node = node.next
    }
    if (node != null) {
        val previousNode = node.previous
        previousNode?.next = null
    }
}

// Origin is `previous <- -> node <- -> next` becomes
// `previous <- -> next`
fun LinkedList.deleteAfter(node: Node) {
    val previousNode: Node<T>? = node.previous
    val nextNode: Node<T>? = node.next
    if (previousNode == null) {
        deleteFirst()
        return
    } else if (nextNode == null) {
        deleteLast()
        return
    }
    previousNode.next = nextNode
    nextNode.previous = previousNode
}

fun LinkedList.deleteAt(index: Int) {
    if (index == 0) {
        deleteFirst()
        return
    }
    
    var i = 0
    var node = this.head
    while (i < index && node != null) {
        i++
        node = node.next
    }

    if (i == index && node != null) {
        deleteAfter(node)
    }
}
```

## Comparision
Let compare the time complexity among array, singly linked list and doubly linked list:

> For doubly linked list, we store the both `head` and `tail` reference to achieve the most-efficient time complexity, otherwise, addition and deletion of last node will be `O(n)`.

| Operations |                   | Array  | Singly Linked List | Doubly Linked List                           |
|------------|-------------------|--------|--------------------|----------------------------------------------|
| Access     | by index          | `O(1)` | O(n)               | O(n)                                         |
| Addition   | before first node | O(n)   | `O(1)`             | `O(1)`                                       |
| Addition   | after given node  | O(n)   | `O(1)`             | `O(1)`                                       |
| Addition   | after last node   | `O(1)` | O(n)               | `O(1)` // optimized by storing `tail` node   |
| Deletion   | the first node    | O(n)   | `O(1)`             | `O(1)`                                       |
| Deletion   | a given node      | O(n)   | O(n)               | `O(1)`                                       |
| Deletion   | the last node     | `O(1)` | O(n)               | `O(1)` // optimized by storing  `tail`  node |
| Search     | a given node      | O(n)   | O(n)               | O(n)                                         |

## Circular Linked List
Linked list with the last node has reference to the head.

> // TODO

## Problems & Solutions
| Problem         | Solution | Difficulty |
|------------------|----------|------------|
|[707. Design Linked List](https://leetcode.com/problems/design-linked-list/)|[Implementation](../leetcode/707.design-linked-list.md)|Medium|

## Tips for Problem Solving
* Corner cases:
    * **Empty linked list**
    * Linked list with **one / two nodes**
    * Linked list has cycles. Clarify before solving problem!

```js
1. head -> null
2. head -> A -> null
3. head -> A -> B -> null
```
* Consider to operate on the specific node before or after.
* Get familiar with the following operations:
    * Count the node number
    * Reverse in-place or update the node references
    * Find the middle node using two pointers technique
    * Merge (connect) two linked lists


## Resources
- [X] Fundamental of Data Structure
- [X] CLRS (Simple)
- [ ] CTCI
- [X] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-2) // Simple note + simple coding problem
- [X] [基本資料結構系列文章](http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html) // Nice introductory note
- [/] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/linked-list // Nice introductory note + illustration
- [/] https://github.com/youngyangyang04/leetcode-master#%E9%93%BE%E8%A1%A8 // Nice introductory note
- [/] [LC Learn](https://leetcode.com/explore/learn/card/linked-list/) 
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Linked_List)
- [ ] [LC Top Interview Questions](https://leetcode.com/explore/interview/) // Coding questions with easy/medium/hard levels
- [ ] ~~[Coding Interview University](https://github.com/jwasham/coding-interview-university#linked-lists)~~ // Simple note + few videos
- [/] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/linked-list) // Simple note + some relative LC coding questions
- [X] [Software Engineering Interview Preparation](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#linked-lists) // Simple note, like cheat sheet
- [X] https://github.com/TSiege/Tech-Interview-Cheat-Sheet#linked-list // // Simple note, like cheat sheet