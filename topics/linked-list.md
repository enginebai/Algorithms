# Linked List
A *linked list* is a pointer-based data structure that store each item in a *node* with two properties: `item` storing the data itself, and `next` storing the address of next item. 

![Linked List](../media/linked-list.png)

> For linked list, we don't have to store item in contiguous memory address like array anymore, we can store item anywhere in memory since we have pointer to next item.

## Singly Linked List

### ADT
```kotlin
data class Node<T>(
    val data: T,
    var next: Node<T>? = null
)

interface LinkedList<T> {
    val head: Node<T>
}
```

### Operations

### Insertion
Linked list takes `O(1)` for inserting or deleting first item simply by relinking the pointer.

![Linked List Insert](../media/linked-list-insert.png)

```kotlin
fun LinkedList.insertFirst(data: T) {
    val newNode = Node(data)
    newNode.next = this.head
    this.head = newNode
}
```

It also takes `O(1)` to insert after a specific node.

```kotlin
fun LinkedList.insertAfter(previousNode: Node, data: T) {
    val newNode = Node(data)
    newNode.next = previousNode.next
    previousNode.next = newNode
}
```

However, it takes `O(n)` to insert a new node at the end, because it has to iterate all node to find the last node.

```kotlin
fun LinkedList.insertLast(data: T) {
    val newNode = Node(data)

    var lastNode = this.head
    while (nextNode != null) {
        lastNode = lastNode.next
    }

    lastNode.next = newNode
}
```

### Deletion
![Linked List Delete](../media/linked-list-delete.png)
To delete the first node, we simply **assign the next node to head**, it takes `O(1)`.

```kotlin
fun LinkedList.deleteFirst() {
    val nextNode = this.head.next
    this.head = nextNode
}
```

To delete specific node from the linked list, we have to iterate to find the previous node before the node to delete and relink to delete. It takes `O(n)` because it iterates the list to locate the node to delete.

```kotlin
fun LinkedList.delete(node: Node) {
    var nodeToDelete = this.head
    var previousNode: Node? = null

    // We find the node to delete at the beginning.
    if (nodeToDelete != null && nodeToDelete.data == node.data) {
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

To delete at the specific index, we have to iterate the linked list to locate the node of that index, it also takes `O(n)`.

```kotlin
fun LinkedList.deleteAt(indexToDelete: Int) {
    if (indexToDelete < 0) return

    var currentIndex = 0
    var currentNode: Node? = this.head

    if (indexToDelete == 0) {
        this.head = currentNode.next
        return
    }

    var previousNode: Node? = null
    while (currentIndex < indexToDelete && currentNode != null) {
        currentIndex++
        previousNode = currentNode
        currentNode = currentNode.next
    }

    // We actually find the index to delete.
    if (currentIndex == indexToDelete) {
        previousNode.next = currentNode.next
    }
}
```

### Get Length
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

### Search
```kotlin
fun LinkedList.search(data: T): Boolean {
    var node = this.head
    while (node != null) {
        if (node.data == data) return true
        node = node.next
    }
    return false
}

fun LinkedList.searchRecursively(node: Node? = head, data: T): Boolean {
    if (node == null) return false
    return if (node.data == data) true
    else searchRecursively(node.next, data)
}
```
## Doubly Linked List

## Circular Linked List

## Resources
- [ ] Fundamental of Data Structure
- [ ] CLRS (Simple)
- [ ] CTCI
- [ ] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-2) // Simple note + simple coding problem
- [ ] [基本資料結構系列文章](http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html) // Nice introductory note
- [ ] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/linked-list // Nice introductory note + illustration
- [ ] https://github.com/youngyangyang04/leetcode-master#%E9%93%BE%E8%A1%A8 // Nice introductory note
- [ ] [LC Learn](https://leetcode.com/explore/learn/card/linked-list/) 
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Linked_List)
- [ ] [LC Top Interview Questions](https://leetcode.com/explore/interview/) // Coding questions with easy/medium/hard levels
- [ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#linked-lists) // Simple note + few videos
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/linked-list) // Simple note + some relative LC coding questions
- [ ] [Software Engineering Interview Preparation](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#linked-lists) // Simple note, like cheat sheet
- [ ] https://github.com/TSiege/Tech-Interview-Cheat-Sheet#linked-list // // Simple note, like cheat sheet