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

```kotlin
fun deleteFirst(linkedList: LinkedList) {

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