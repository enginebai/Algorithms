## The Complete Problem-Solving Framework

A systematic approach to tackle any coding problem effectively.

> This framework uses the coding problem: [Restaurant Waitlist](https://docs.google.com/document/d/1Or2i1lYtQI5HB6TKtLgqj-9WB8u16m8N8IH_syUKoGg/edit?tab=t.svb2wta3fntb) as an example.

---

### 1. Understand & Clarify

- Read the problem carefully
- **Identify inputs and outputs**
- **List all operations** the data structure must support
- **Ask clarifying questions** (in interview: ask interviewer; solo: make reasonable assumptions)
- Restate the problem in your own words

**Example:**

- Operations: `join`, `leave`, `serve`
- Key clarification: "first party that fits" - does this mean ≤ or exact match?

---

### 2. Identify Core Challenges

- What makes this problem tricky?
- What are the **conflicting requirements**?
- Which operations might be expensive?

**Example:**

- Need both: ordered access (for "first") + fast removal (for "leave")
- Need to identify parties uniquely
- "First fit" requires scanning

---

### 3. Think Through Examples

- Create a concrete scenario
- **Walk through operations step-by-step**
- This reveals hidden requirements!

**Example:**

```js
Waitlist: A(6), B(2), C(4), D(3)
serve(4) → Who gets seated?
```

This helps understand "first that fits"

---

### 4. Consider Data Structures

- What properties do we need? (order, fast access, uniqueness, etc.)
- **List candidate data structures** and their strengths
- Think: "What if I use X? What problems arise?"

**Common Data Structures:**

- **Array/List:** Maintains order, but slow removal from middle
- **HashMap:** Fast lookup, but no order
- **LinkedList:** Easy insertion/removal, but slow search
- **Queue:** FIFO order, but can't remove from middle
- **Heap:** Priority access, but no random removal
- **DDL + HashMap:** Fast lookup + ordered access + fast removal
- **Combinations:** Often the best solution uses multiple structures

---

### 5. Evaluate Trade-offs

- **Compare approaches** on key operations
- Consider time/space complexity
- Think about **when each approach is better**

**Example:**
| Approach | serve() with ≤ | serve() with exact |
|----------|----------------|-------------------|
| One DDL | `O(n)` | `O(n)` |
| Size map | `O(T)` + timestamps | `O(1)` |
ㄕ
---

### 6. Analyze Edge Cases

- Empty data structure
- Invalid inputs
- Boundary values
- No valid answer exists
- Duplicate values
- Maximum/minimum constraints

**Example:**

- What if no party fits?
- What if waitlist is empty?
- What if party size is 0 or negative?

---

### 7. Calculate Complexity

- **Time complexity** for each operation
- **Space complexity** overall
- Is this acceptable for the constraints?

---

## Patterns Recognition

Common patterns you'll encounter:

- **Fast lookup + maintain order** → HashMap + LinkedList (LRU Cache, Browser History)
- **Track min/max + insert/remove** → Heap + HashMap (Sliding Window Maximum)
- **Need to backtrack** → Stack or Recursion (Valid Parentheses, DFS)
- **Level-by-level processing** → Queue (BFS, Tree Level Order)
- **Range queries** → Prefix Sum, Segment Tree
- **Two sequences** → Two Pointers, DP
