# Key Patterns and Approaches for Linked List Problems

Based on the problems in `problems/linked-list-problems.md`, here is a summary of the key patterns and approaches for solving linked list problems.

Linked lists are linear data structures where elements are stored in nodes, each containing data and a pointer to the next node. They excel at dynamic insertion/deletion but lack random access, making pointer manipulation and traversal techniques crucial.

### 1. Basic Traversal and Manipulation

Fundamental operations that form the building blocks of more complex algorithms.

#### 1.1 Linear Traversal
*   **Core Idea:** Visit each node exactly once from head to tail.
*   **Key Applications:**
    *   Counting nodes, finding values
    *   Building new lists based on conditions
    *   Aggregating values or transforming data
*   **Examples:** [2181. Merge Nodes in Between Zeros](../leetcode/2181.merge-nodes-in-between-zeros.md), [817. Linked List Components](../leetcode/817.linked-list-components.md).

#### 1.2 Multi-Level Traversal
*   **Core Idea:** Handle lists with additional pointers or nested structures.
*   **Key Approach:**
    *   Use recursion or stack for depth-first traversal
    *   Handle multiple pointer types (next, child, random)
*   **Examples:** [430. Flatten a Multilevel Doubly Linked List](../leetcode/430.flatten-a-multilevel-doubly-linked-list.md).

### 2. Node Removal Patterns

Efficiently remove nodes while maintaining list integrity.

#### 2.1 Value-Based Removal
*   **Core Idea:** Remove nodes that match certain criteria.
*   **Key Approach:**
    *   Use dummy head to handle edge cases (removing first node)
    *   Maintain previous pointer to update links
    *   Handle consecutive removals carefully
*   **Examples:** [203. Remove Linked List Elements](../leetcode/203.remove-linked-list-elements.md), [83. Remove Duplicates from Sorted List](../leetcode/83.remove-duplicates-from-sorted-list.md).

#### 2.2 Position-Based Removal
*   **Core Idea:** Remove nodes at specific positions or based on structural properties.
*   **Key Techniques:**
    *   Two-pointer technique for nth from end
    *   Maintain prev/current pointers for precise removal
*   **Examples:** [19. Remove Nth Node From End of List](../leetcode/19.remove-nth-node-from-end-of-list.md), [237. Delete Node in a Linked List](../leetcode/237.delete-node-in-a-linked-list.md).

#### 2.3 Conditional Removal
*   **Core Idea:** Remove nodes based on future values or global conditions.
*   **Key Approach:**
    *   May require multiple passes or additional data structures
    *   Stack-based approaches for "future-aware" removal
*   **Examples:** [2487. Remove Nodes From Linked List](../leetcode/2487.remove-nodes-from-linked-list.md) (remove nodes with larger node to right).

### 3. Reversal Patterns

Reverse entire lists or portions of lists.

#### 3.1 Complete Reversal
*   **Core Idea:** Reverse the entire linked list.
*   **Key Approach:**
    *   Maintain three pointers: prev, current, next
    *   Iteratively reverse links: `current.next = prev`
    *   Handle null pointers carefully
*   **Template:**
    ```python
    def reverse(head):
        prev, curr = None, head
        while curr:
            next_temp = curr.next
            curr.next = prev
            prev, curr = curr, next_temp
        return prev
    ```
*   **Examples:** [206. Reverse Linked List](../leetcode/206.reverse-linked-list.md).

#### 3.2 Partial Reversal
*   **Core Idea:** Reverse a specific portion of the list.
*   **Key Approach:**
    *   Identify the portion to reverse (beforeLeft, afterRight)
    *   Apply standard reversal within the range
    *   Reconnect reversed portion to rest of list
*   **Examples:** [92. Reverse Linked List II](../leetcode/92.reverse-linked-list-ii.md), [25. Reverse Nodes in k-Group](../leetcode/25.reverse-nodes-in-k-group.md).

#### 3.3 Group Reversal
*   **Core Idea:** Reverse nodes in groups of fixed size.
*   **Key Approach:**
    *   Count nodes to ensure group is complete
    *   Reverse each complete group
    *   Handle incomplete final group based on requirements
*   **Examples:** [25. Reverse Nodes in k-Group](../leetcode/25.reverse-nodes-in-k-group.md), [24. Swap Nodes in Pairs](../leetcode/24.swap-nodes-in-pairs.md).

### 4. Two Pointer Techniques

Use multiple pointers moving at different speeds or positions.

#### 4.1 Fast-Slow Pointers (Floyd's Algorithm)
*   **Core Idea:** One pointer moves twice as fast as the other.
*   **Key Applications:**
    *   Cycle detection: if fast meets slow, there's a cycle
    *   Finding middle: when fast reaches end, slow is at middle
    *   Finding cycle start: reset one pointer to head after meeting
*   **Examples:** [141. Linked List Cycle](../leetcode/141.linked-list-cycle.md), [142. Linked List Cycle II](../leetcode/142.linked-list-cycle-ii.md), [876. Middle of the Linked List](../leetcode/876.middle-of-the-linked-list.md).

#### 4.2 Distance-Based Two Pointers
*   **Core Idea:** Maintain fixed distance between pointers.
*   **Key Applications:**
    *   nth from end: fast pointer goes n steps ahead
    *   Window operations: maintain window of size k
*   **Examples:** [19. Remove Nth Node From End of List](../leetcode/19.remove-nth-node-from-end-of-list.md), [61. Rotate List](../leetcode/61.rotate-list.md).

#### 4.3 Synchronized Movement
*   **Core Idea:** Move pointers together to find intersection or alignment.
*   **Key Approach:**
    *   Handle different list lengths by switching lists
    *   Walk through A→B and B→A to find intersection
*   **Examples:** [160. Intersection of Two Linked Lists](../leetcode/160.intersection-of-two-linked-lists.md).

### 5. List Reconstruction and Merging

Build new lists or merge existing lists based on certain criteria.

#### 5.1 Merging Sorted Lists
*   **Core Idea:** Combine sorted lists while maintaining sorted order.
*   **Key Approach:**
    *   Use two pointers, always advance the smaller value
    *   Handle remaining elements after one list is exhausted
*   **Examples:** [21. Merge Two Sorted Lists](../leetcode/21.merge-two-sorted-lists.md), [23. Merge k Sorted Lists](../leetcode/23.merge-k-sorted-lists.md).

#### 5.2 Arithmetic Operations
*   **Core Idea:** Perform arithmetic on numbers represented as linked lists.
*   **Key Approach:**
    *   Handle carry propagation
    *   Process digits from least significant to most significant
    *   Handle different number lengths
*   **Examples:** [2. Add Two Numbers](../leetcode/2.add-two-numbers.md).

#### 5.3 List Reordering
*   **Core Idea:** Rearrange nodes according to specific patterns.
*   **Key Approach:**
    *   Often requires finding middle, reversing portions, and merging
    *   May use auxiliary data structures for complex reordering
*   **Examples:** [143. Reorder List](../leetcode/143.reorder-list.md), [328. Odd Even Linked List](../leetcode/328.odd-even-linked-list.md), [86. Partition List](../leetcode/86.partition-list.md).

### 6. Design and Complex Operations

Implement advanced functionality using linked lists.

#### 6.1 Iterator Design
*   **Core Idea:** Provide sequential access interface for complex data structures.
*   **Key Components:**
    *   State management for current position
    *   Lookahead for hasNext() implementation
*   **Examples:** Various iterator implementations.

#### 6.2 Cache Implementation
*   **Core Idea:** Combine linked list with hash table for efficient cache operations.
*   **Key Components:**
    *   Doubly linked list for LRU ordering
    *   Hash table for O(1) access
    *   Careful pointer manipulation for updates
*   **Examples:** [146. LRU Cache](../leetcode/146.lru-cache.md).

#### 6.3 Random Access Simulation
*   **Core Idea:** Simulate random access on inherently sequential structure.
*   **Key Approaches:**
    *   Reservoir sampling for random selection
    *   Convert to array when random access is frequent
*   **Examples:** [382. Linked List Random Node](../leetcode/382.linked-list-random-node.md).

---

### Key Implementation Tips

1. **Use Dummy Head:** Simplifies edge cases when head might change.
   ```python
   dummy = ListNode(0)
   dummy.next = head
   ```

2. **Preserve Next Pointer:** Always save `next` before modifying links.
   ```python
   next_temp = curr.next
   curr.next = prev
   ```

3. **Handle Null Pointers:** Check for null before accessing node properties.

4. **Draw It Out:** Visualize pointer movements for complex operations.

### Common Edge Cases

1. **Empty List (null head)**
2. **Single Node List**
3. **Two Node List** (especially for reversal operations)
4. **Cycles** (if not expected, can cause infinite loops)
5. **Different Length Lists** (in merging operations)

### Time/Space Complexity Patterns

- **Traversal:** O(n) time, O(1) space
- **Reversal:** O(n) time, O(1) space
- **Cycle Detection:** O(n) time, O(1) space
- **Merging k lists:** O(n log k) time with heap, O(n) space

### Summary of Key Approaches

1. **Master Basic Operations:** Traversal, insertion, deletion form the foundation.

2. **Use Appropriate Pointer Techniques:** Fast-slow for cycles/middle, distance-based for positioning.

3. **Handle Edge Cases:** Dummy nodes and careful null checking prevent many bugs.

4. **Visualize Operations:** Draw linked list operations to understand pointer movements.

5. **Choose Right Data Structure:** Sometimes array conversion or auxiliary structures are better.

6. **Practice Pointer Manipulation:** The key skill is safely updating multiple pointers simultaneously.

Linked list problems test your ability to manipulate pointers correctly while maintaining data structure integrity. Success comes from mastering the fundamental patterns and being extremely careful with pointer operations.