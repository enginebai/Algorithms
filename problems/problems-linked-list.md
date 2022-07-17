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
## [92. Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/)

**Idea!** We find the part to reverse and the before / after node of reverse list, reuse modified `reverseLinkedList(node)` with length to reverse, and relink the before / after node pointer to correct.

```js
1 -> 2 -> 3 -> 4 -> 5
left = 2, right = 4

// Traverse list to find the before / after pointer
before = 1
after = 5

// Reverse from left to right
reversedHead = reverseLinkedList(2 -> 3 -> 4), return 4

// Relink before and after pointer to reverse list
before -> reversedHead -> ... -> after
```

For the example: `1 -> 2 -> 3 -> 4 -> 5`, left = 2, right = 4, we have to find `1 -> 2` and `4 -> 5`, and reverse between `2 -> 3 -> 4` and relink `1` to the new head of reversed list and relink the last node of reversed list to `5`.

```kotlin
fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    // We introduce sentinel for help
    val sentinel = ListNode(-1)
    sentinel.next = head
    
    // Looking for 1 -> 2
    var nodeBeforeLeft: ListNode? = sentinel
    var leftNode: ListNode? = head
    var i = 1
    while (i < left) {
        nodeBeforeLeft = leftNode
        leftNode = leftNode?.next
        i++
    }
    // Lookin for 4 -> 5
    var rightNode: ListNode? = nodeBeforeLeft
    var nodeAfterRight: ListNode? = rightNode?.next
    while (i <= right) {
        rightNode = rightNode?.next
        nodeAfterRight = rightNode?.next
        i++
    }
    
    val reverseHead = reverse(leftNode, right - left + 1)
    // Relink 1 -> reversed list
    nodeBeforeLeft?.next = reverseHead
    // Relink reversed list -> 5
    leftNode?.next = nodeAfterRight
    return sentinel.next
}

private fun reverse(start: ListNode?, count: Int): ListNode? {
    var previous: ListNode? = null
    var current: ListNode? = start
    var i = 0
    while (current != null && i < count) {
        val next = current?.next
        current?.next = previous
        previous = current
        current = next
        i++
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

----
## [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)

```kotlin
fun detectCycle(head: ListNode?): ListNode? {
    val seenNodeSet = hashSetOf<ListNode>()
    var node: ListNode? = head
    while (node != null) {
        if (seenNodeSet.contains(node)) {
            return node
        }
        seenNodeSet.add(node)
        node = node.next
    }
    return null
}
```

We can use two pointers approach to solve this with `O(1)` space. (like [the solution](../leetcode/141.linked-list-cycle.md) with some extra steps)

> Explanation: https://leetcode.cn/problems/linked-list-cycle-ii/solution/142-huan-xing-lian-biao-ii-jian-hua-gong-shi-jia-2/
>
> There are some equivalent two pointers implementation, but we have make sure that `slow` and `fast` pointers **start from the same node**!!.

```kotlin
fun detectCycle(head: ListNode?): ListNode? {
    var slow: ListNode? = head
    var fast: ListNode? = head

    boolean containsCycle = false
    while (slow != null) {
        slow = show.next
        fast = fast?.next?.next

        if (slow == fast) {
            containsCycle = true
            break
        }
    }

    if (!containsCycle) return null
    slow = head
    while (slow != fast) {
        slow = slow?.next
        fast = fast?.next
    }
    return slow
}
```