# Key Patterns and Approaches for Design Problems

Based on the problems in `problems/design-problems.md`, here is a summary of the key patterns and approaches for solving system design problems.

Design problems require you to implement data structures or systems that support specific operations efficiently. These problems test your understanding of data structure trade-offs, API design, and optimization techniques.

### 1. Cache Design Patterns

Design caching systems with eviction policies and efficient access patterns.

#### 1.1 LRU Cache
*   **Core Idea:** Evict the Least Recently Used item when cache is full.
*   **Key Components:**
    *   **Hash Table:** O(1) access to cache entries.
    *   **Doubly Linked List:** O(1) insertion/deletion and LRU tracking.
    *   **Combined Approach:** Hash table points to list nodes for O(1) operations.
*   **Key Operations:**
    *   `get()`: Move accessed item to head (most recent).
    *   `put()`: Add to head, remove LRU item if capacity exceeded.
*   **Examples:** [146. LRU Cache](../leetcode/146.lru-cache.md).

### 2. Random Access Design

Implement data structures that support random sampling and efficient modification.

*   **Core Idea:** Combine hash table for O(1) lookup with array for O(1) random access.
*   **Key Approach:**
    *   **Array:** Store actual values for random access.
    *   **Hash Table:** Map values to indices for O(1) existence check.
    *   **Removal Trick:** Swap element to be removed with the last element to maintain array compactness.
*   **Critical Implementation Details:**
    *   When removing, update hash table for the swapped element.
    *   Handle edge cases when removing the last element.
*   **Examples:** [380. Insert Delete GetRandom O(1)](../leetcode/380.insert-delete-getrandom-o1.md).

### 3. Stream Processing Design

Handle data streams with median finding, priority management, or statistical queries.

#### 3.1 Median Finding
*   **Core Idea:** Use two heaps to maintain the median of a dynamic dataset.
*   **Key Components:**
    *   **Max Heap:** Store the smaller half of numbers.
    *   **Min Heap:** Store the larger half of numbers.
    *   **Balance Constraint:** Size difference between heaps ≤ 1.
*   **Key Operations:**
    *   Maintain heap sizes balanced after each insertion.
    *   Median is either the top of larger heap or average of both tops.
*   **Examples:** [295. Find Median from Data Stream](../leetcode/295.find-median-from-data-stream.md).

### 4. History and Navigation Systems

Implement systems that track history and support forward/backward navigation.

*   **Core Idea:** Use appropriate data structures to store history and maintain current position.
*   **Key Approaches:**
    *   **Array/List + Index:** Simple but may require memory cleanup.
    *   **Stack-based:** Use stacks for undo/redo operations.
    *   **Linked List:** For dynamic history with efficient insertion/deletion.
*   **Examples:** [1472. Design Browser History](../leetcode/1472.design-browser-history.md).

### 5. Hash-Based Data Structures

Implement fundamental hash-based structures from scratch.

*   **Core Idea:** Use arrays and handle collision resolution with chaining or open addressing.
*   **Key Components:**
    *   **Hash Function:** Convert keys to array indices.
    *   **Collision Resolution:** Handle multiple keys hashing to same index.
    *   **Dynamic Resizing:** Maintain load factor for performance.
*   **Common Approaches:**
    *   **Chaining:** Use linked lists at each array position.
    *   **Open Addressing:** Linear probing or double hashing.
*   **Examples:** [705. Design HashSet](../leetcode/705.design-hashset.md), [706. Design HashMap](../leetcode/706.design-hashmap.md).

### 6. Iterator Design

Implement iterators for complex nested data structures.

*   **Core Idea:** Flatten nested structures while maintaining lazy evaluation.
*   **Key Approach:**
    *   Use stack to maintain current traversal state.
    *   Implement `hasNext()` and `next()` methods.
    *   Handle nesting by pushing/popping from stack as needed.
*   **Examples:** [341. Flatten Nested List Iterator](../leetcode/341.flatten-nested-list-iterator.md), [173. Binary Search Tree Iterator](../leetcode/173.binary-search-tree-iterator.md).

### 7. Time-Based Systems

Design systems that handle temporal data with efficient time-based queries.

*   **Core Idea:** Organize data by time and use binary search for efficient temporal queries.
*   **Key Approach:**
    *   Store timestamps in sorted order for each key.
    *   Use binary search to find the latest timestamp ≤ query time.
    *   Consider using TreeMap or sorted containers.
*   **Examples:** [981. Time Based Key-Value Store](../leetcode/981.time-based-key-value-store.md).

### 8. Interval and Scheduling Systems

Design systems that handle intervals, scheduling, or calendar operations.

*   **Core Idea:** Maintain sorted intervals and check for overlaps efficiently.
*   **Key Approaches:**
    *   **Sorted Container:** Use TreeMap, sorted list, or binary search trees.
    *   **Overlap Detection:** Check if new interval overlaps with neighbors.
    *   **Binary Search:** Find insertion point efficiently.
*   **Examples:** [729. My Calendar I](../leetcode/729.my-calendar-i.md).

---

### Key Design Principles

1. **Identify Core Operations:** Understand which operations need to be optimized (usually mentioned in problem constraints).

2. **Choose Right Data Structures:** Combine multiple data structures to achieve desired time complexities.

3. **Handle Edge Cases:** Empty structures, single elements, capacity limits, and boundary conditions.

4. **Memory vs Time Trade-offs:** Understand when to use extra space for time optimization.

5. **API Consistency:** Ensure all operations maintain data structure invariants.

### Common Optimization Patterns

1. **Hash Table + Auxiliary Structure:** Combine hash tables with arrays, linked lists, or heaps for O(1) access plus other benefits.

2. **Two Data Structures Pattern:** Use two complementary structures (like two heaps for median, hash table + array for random access).

3. **Lazy Evaluation:** Don't process until absolutely necessary (useful in iterators).

4. **State Caching:** Cache computed results to avoid recomputation.

5. **Binary Search Integration:** Use binary search with sorted containers for efficient range operations.

### Summary of Key Approaches

1. **Analyze Time Complexity Requirements:** Usually one or more operations need to be O(1) or O(log n).

2. **Combine Data Structures Wisely:** Most design problems require combining 2-3 basic data structures.

3. **Maintain Invariants:** Ensure data structure properties are preserved across all operations.

4. **Consider Memory Constraints:** Some problems have space limitations that affect design choices.

5. **Think About Usage Patterns:** How will the data structure be used in practice? This guides optimization decisions.

6. **Plan for Scale:** Consider what happens with large datasets or high frequency operations.

Design problems are about making smart trade-offs and combining basic data structures in creative ways. The key is understanding what operations need to be fast and choosing the right combination of structures to achieve those performance goals.