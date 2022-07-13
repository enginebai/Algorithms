## [707. Design Linked List](https://leetcode.com/problems/design-linked-list/)

```kotlin
data class Node(
    val value: Int,
    var next: Node? = null
)

class MyLinkedList() {

    private var head: Node? = null

    fun get(index: Int): Int {
        if (index == 0 && head == null) return -1

        var i = 0
        var node: Node? = head
        while (i < index) {
            i++
            node = node?.next
        }
        return node?.value ?: -1
    }

    fun addAtHead(value: Int) {
        val newNode = Node(value, next = head)
        head = newNode
    }

    fun addAtTail(value: Int) {
        if (head == null) {
            addAtHead(value)
            return
        }
        var last: Node? = head
        while (last?.next != null) {
            last = last.next
        }
        val newNode = Node(value)
        last?.next = newNode
    }

    fun addAtIndex(index: Int, value: Int) {
        if (index == 0) {
            addAtHead(value)
            return
        }
        var i = 0
        var previous: Node? = null
        var current: Node? = head        
        while (i < index) {
            i++
            previous = current
            current = current?.next
        }
        val newNode = Node(value, next = current)
        previous?.next = newNode
    }

    fun deleteAtIndex(index: Int) {
        if (index == 0) {
            head = head?.next
            return 
        }
        var previous: Node? = null
        var current: Node? = head
        var i = 0
        while (i < index) {
            i++
            previous = current
            current = current?.next
        }
        previous?.next = current?.next
    }
}
```

----
## [203. Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements/)

```js
// Delete the head, and move the head
[6, 6, 6, 1], value = 6 --> [1]

// Delete non-head node
[1, 2, 3, 3], value = 3 --> [1, 2]
```

When deleting a node, we can implement in the following way:

```kotlin
var previous: ListNode? = null
var current: ListNode? = head
while (current != null) {
    if (current == nodeToDelete) {
        previous?.next = current.next
    } else {
        previous = current
        current = current.next
    }
}
```

However, the implementation will not work when delete the head, since `previous` is null and `previous?.next` will not be executed at all, we have find other way to handle the case to delete head.

We introduce *sentinel node* to help us when deleting the head node so that we can implement node deletion in the above way even for deleting head.

```kotlin
fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    val sentinel = ListNode(-1)
    sentinel.next = head
    // Here is different part.
    var previous: ListNode? = sentinel
    var current: ListNode? = head
    while (current != null) {
        if (current.`val` == `val`) {
            previous?.next = current.next
        } else {
            previous = current
        }
        current = current.next
    }
    return sentinel?.next
}
```

----
## [83. Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)

```kotlin
fun deleteDuplicates(head: ListNode?): ListNode? {
    var node: ListNode? = head
    while (node != null) {
        val nextUniqueNode = findNextUniqueNode(node.next, node.value)
        // Relink to unique node (delete duplicate nodes)
        node.next = nextUniqueNode

        // Move onto to next pointer
        node = nextUniqueNode
    }
    return head
}

private fun findNextUniqueNode(nextNode: ListNode?, value: Int): ListNode? {
    var node: ListNode? = nextNode
    while (node != null && node.value == value) {
        node = node.next
    }
    return node
}
```

----
## [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)

```kotlin
fun reverseList(head: ListNode?): ListNode? {
    var previous: ListNode? = null
    var current: ListNode? = head

    while (current != null) {
        // We have to store the next pointer first before overriding.
        val next = current.next
        current.next = previous

        previous = current
        current = next
    }
    return previous
}
```

----
## [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)

```kotlin
data class ListNode(
    val value: Int,
    var next: ListNode? = null
)

fun middleNode(head: ListNode?): ListNode? {
    val size = getSize(head)
    val middle = size / 2
    var i = 0
    var node = head
    while (i < middle) {
        node = node?.next
        i++
    }
    return node
}

private fun getSize(head: ListNode?): Int {
    var node = head
    var size = 0
    while (node != null) {
        size++
        node = node.next
    }
    return size
}
```

We also can achieve by two pointers approach, slow pointer goes 1 step, fast pointer goes 2 step:

```kotlin
fun middleNode(head: ListNode?): ListNode? {
    var slow: ListNode? = head
    var fast: ListNode? = head?.next
    while (fast != null) {
        slow = slow?.next
        fast = fast?.next?.next
    }
    return slow
}
```

----
## [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)

```kotlin
fun hasCycle(head: ListNode?): Boolean {
    val seenNode = hashSetOf<ListNode>()
    var node = head
    while (node != null) {
        if (seenNode.contains(node)) {
            return true
        }
        seenNode.add(node)
        node = node.next
    }
    return false
}
```

We can use two pointers approach to solve this with `O(1)` space:
1. Slow pointer goes 1 step, fast pointer goes 2 step.
2. Traverse the linked list.
3. The two pointer will meet at the same node if there is cycle.

```kotlin
fun hasCycle(head: ListNode?): Boolean {
    var slow: ListNode? = head
    var fast: ListNode? = head?.next
    while (slow != null) {
        if (fast == slow) return true
        show = show?.next
        fast = fast?.next?.next
    }
    return false
}
```