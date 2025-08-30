# Key Patterns and Approaches for Hash Table Problems

Based on the problems in `problems/hash-table-problems.md`, here is a summary of the key patterns and approaches for solving hash table problems.

Hash tables provide O(1) average-case lookup, insertion, and deletion operations, making them ideal for problems involving fast data retrieval, counting, and relationship mapping.

### 1. Existence and Lookup Patterns

Use hash tables to quickly check if elements exist or to find complements.

#### 1.1 Complement Finding
*   **Core Idea:** For each element, check if its complement exists in previously seen elements.
*   **Key Approach:**
    *   Iterate through array/input.
    *   For current element, check if complement exists in hash table.
    *   Store current element for future lookups.
*   **Examples:** [1. Two Sum](../leetcode/1.two-sum.md) (find pair that sums to target), [633. Sum of Square Numbers](../leetcode/633.sum-of-square-numbers.md), [923. 3Sum With Multiplicity](../leetcode/923.3sum-with-multiplicity.md).

#### 1.2 Set Membership
*   **Core Idea:** Use hash set to track seen elements and check existence efficiently.
*   **Key Applications:**
    *   Duplicate detection
    *   Intersection finding
    *   Validation problems
*   **Examples:** [128. Longest Consecutive Sequence](../leetcode/128.longest-consecutive-sequence.md) (check for sequence continuity), [36. Valid Sudoku](../leetcode/36.valid-sudoku.md) (check uniqueness).

### 2. Counting and Frequency Patterns

Track frequencies of elements for statistical analysis or constraint checking.

#### 2.1 Frequency Counting
*   **Core Idea:** Count occurrences of each element using hash map.
*   **Key Applications:**
    *   Finding majorities, modes, or specific frequencies
    *   Anagram detection
    *   Character counting for string problems
*   **Examples:** [242. Valid Anagram](../leetcode/242.valid-anagram.md), [169. Majority Element](../leetcode/169.majority-element.md), [697. Degree of an Array](../leetcode/697.degree-of-an-array.md).

#### 2.2 Frequency-Based Decisions
*   **Core Idea:** Make decisions based on frequency distributions.
*   **Key Approach:**
    *   Count frequencies first.
    *   Process elements based on their frequencies.
    *   Handle edge cases like minimum frequency requirements.
*   **Examples:** [1002. Find Common Characters](../leetcode/1002.find-common-characters.md) (min frequency across strings), [554. Brick Wall](../leetcode/554.brick-wall.md) (most frequent edge position).

### 3. Mapping and Transformation

Create mappings between different representations or transform data using hash tables.

#### 3.1 Bidirectional Mapping
*   **Core Idea:** Maintain mappings in both directions to ensure consistency.
*   **Key Approach:**
    *   Use two hash maps: one for each direction.
    *   Check consistency when adding new mappings.
    *   Handle one-to-one correspondence requirements.
*   **Examples:** [290. Word Pattern](../leetcode/290.word-pattern.md) (pattern ↔ word mapping), [535. Encode and Decode TinyURL](../leetcode/535.encode-and-decode-tinyurl.md).

#### 3.2 Grouping by Key
*   **Core Idea:** Group elements based on a computed key or shared property.
*   **Key Approach:**
    *   Define key function that captures the grouping criterion.
    *   Use hash map where key maps to list/set of elements.
*   **Examples:** [49. Group Anagrams](../leetcode/49.group-anagrams.md) (group by sorted characters), hash-based clustering.

### 4. Design Patterns with Hash Tables

Implement complex data structures using hash tables as the core component.

#### 4.1 Hash Table + Auxiliary Structure
*   **Core Idea:** Combine hash table with other data structures for enhanced functionality.
*   **Common Patterns:**
    *   Hash table + Array for O(1) random access
    *   Hash table + Linked list for LRU functionality
    *   Hash table + Heap for priority operations
*   **Examples:** [380. Insert Delete GetRandom O(1)](../leetcode/380.insert-delete-getrandom-o1.md), [146. LRU Cache](../leetcode/146.lru-cache.md), [355. Design Twitter](../leetcode/355.design-twitter.md).

#### 4.2 Hash Table with Complex Keys
*   **Core Idea:** Use tuples, strings, or custom objects as keys for multi-dimensional lookups.
*   **Key Considerations:**
    *   Ensure keys are immutable and hashable.
    *   Handle key collision and equality properly.
*   **Examples:** Coordinate-based problems, state-based dynamic programming with memoization.

### 5. Prefix Sum with Hash Tables

Combine prefix sums with hash tables for efficient subarray analysis.

#### 5.1 Subarray Sum Problems
*   **Core Idea:** Use hash table to store prefix sums and find target subarrays efficiently.
*   **Key Approach:**
    *   Compute running prefix sum.
    *   Check if `prefix_sum - target` exists in hash table.
    *   Store current prefix sum with its index.
*   **Examples:** [560. Subarray Sum Equals K](../leetcode/560.subarray-sum-equals-k.md), range sum queries.

#### 5.2 Range Queries
*   **Core Idea:** Precompute prefix information and store in hash table for fast range queries.
*   **Examples:** [303. Range Sum Query - Immutable](../leetcode/303.range-sum-query-immutable.md), [2559. Count Vowel Strings in Ranges](../leetcode/2559.count-vowel-strings-in-ranges.md).

### 6. Cycle Detection and Special Algorithms

Use hash tables for cycle detection and special array algorithms.

#### 6.1 Cycle Sort Pattern
*   **Core Idea:** Place elements in their "correct" positions, detecting duplicates and missing elements.
*   **Key Approach:**
    *   Use array indices as implicit hash function.
    *   Swap elements to place them in correct positions.
    *   Handle duplicates and missing elements.
*   **Examples:** [41. First Missing Positive](../leetcode/41.first-missing-positive.md), [442. Find All Duplicates in an Array](../leetcode/442.find-all-duplicates-in-an-array.md), [448. Find All Numbers Disappeared in an Array](../leetcode/448.find-all-numbers-disappeared-in-an-array.md).

#### 6.2 Fast Cycle Detection
*   **Core Idea:** Use hash table to detect when we've seen a state before.
*   **Examples:** [202. Happy Number](../leetcode/202.happy-number.md) (detect cycle in number transformation), linked list cycle detection with nodes.

### 7. Advanced Hash Table Techniques

#### 7.1 Rolling Hash
*   **Core Idea:** Efficiently update hash values for sliding windows or substrings.
*   **Applications:** String matching, duplicate substring detection.

#### 7.2 Lazy Deletion
*   **Core Idea:** Mark entries as deleted instead of immediately removing them.
*   **Benefits:** Maintain performance while handling frequent updates.

---

### Key Design Decisions

1. **Hash Function Choice:** 
   - Built-in hash for primitive types
   - Custom hash for complex objects
   - Rolling hash for strings/arrays

2. **Collision Resolution:**
   - Programming languages handle this automatically
   - Be aware of worst-case O(n) performance

3. **Load Factor Management:**
   - Most implementations automatically resize
   - Consider space-time trade-offs

### Common Hash Table Patterns Recognition

1. **Need O(1) Lookup?** → Hash table is likely the right choice.

2. **Finding Pairs/Complements?** → Store elements while iterating, check for complements.

3. **Counting Elements?** → Use hash map with element as key, count as value.

4. **Grouping Elements?** → Use computed key to group related elements.

5. **Fast Random Access + Fast Search?** → Combine hash table with array.

6. **Bidirectional Lookup?** → Use two hash tables or hash table with inverse mapping.

### Summary of Key Approaches

1. **Choose Right Data Structure:**
   - Hash Set for existence/membership queries
   - Hash Map for counting, mapping, grouping

2. **Handle Edge Cases:** Empty inputs, duplicate keys, hash collisions (rare but possible).

3. **Optimize Space vs Time:** Sometimes perfect hash functions or specialized data structures are better.

4. **Consider Key Design:** What makes a good key? Immutable, hashable, represents the right equivalence relation.

5. **Combine with Other Patterns:** Hash tables work well with two pointers, sliding window, prefix sums.

6. **Understand Limitations:** Hash tables provide average O(1) performance but worst-case can be O(n).

Hash tables are among the most versatile data structures in competitive programming. Master the basic patterns, and you'll be able to solve a wide variety of problems efficiently.