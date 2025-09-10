# Array Loop Patterns - Quick Reference Cheat Sheet

## The Golden Rule

**Match your loop bound to what you're accessing!**

```
Accessing nums[i+1]? → Loop must ensure i+1 < n → Use i < n-1
Accessing nums[i-1]? → Loop must ensure i-1 >= 0 → Start from i = 1
Accessing only nums[i]? → Loop ensures i < n → Use i < n
```

---

## Visual Memory Aid

```
Array: [a, b, c, d]  (n=4)

ELEMENTS (4 items):
Index:  0  1  2  3
       [a, b, c, d]
        ↓  ↓  ↓  ↓   ← 4 elements
Loop: for (i in 0 until 4)  // i < n

EDGES (3 differences):
Index:  0  1  2  3
       [a, b, c, d]
         └─ └─ └─     ← 3 edges
        e0 e1 e2
Loop: for (i in 0 until 3)  // i < n-1
```

---

## Decision Tree

```
┌─────────────────────────────────────┐
│ What am I accessing in the loop?   │
└─────────────────────────────────────┘
                │
    ┌───────────┴───────────┐
    ↓                       ↓
┌─────────┐          ┌──────────────┐
│ nums[i] │          │ Relationship │
│  only   │          │ between two  │
└─────────┘          └──────────────┘
    │                       │
    ↓                       ↓
Loop: i < n        Which elements?
                           │
              ┌────────────┴─────────────┐
              ↓                          ↓
      ┌──────────────┐          ┌───────────────┐
      │ nums[i+1]    │          │ nums[i-1]     │
      │ (lookahead)  │          │ (lookback)    │
      └──────────────┘          └───────────────┘
              │                          │
              ↓                          ↓
      Loop: i < n-1            Loop: i = 1; i < n
      Start: i = 0             Start: i = 1
```

---

## Pattern Catalog

### Pattern 1: Element Processing
**When:** Processing each element individually
```kotlin
for (i in 0 until n) {
    process(nums[i])
}
```

### Pattern 2: Lookahead (Most Common)
**When:** Compare element with NEXT element
```kotlin
for (i in 0 until n - 1) {
    val diff = nums[i + 1] - nums[i]
    // or check relationship
    if (nums[i] < nums[i + 1]) { ... }
}
```

### Pattern 3: Lookback
**When:** Compare element with PREVIOUS element
```kotlin
for (i in 1 until n) {
    val diff = nums[i] - nums[i - 1]
    // or check relationship
    if (nums[i] > nums[i - 1]) { ... }
}
```

### Pattern 4: Two Pointers
**When:** Compare elements from both ends
```kotlin
var left = 0
var right = n - 1
while (left < right) {
    // Compare nums[left] and nums[right]
    left++
    right--
}
```

---

## Common Mistakes ❌ → Fixes ✅

### Mistake 1: Wrong bound with lookahead
```kotlin
❌ for (i in 0 until n) {
    val diff = nums[i + 1] - nums[i]  // CRASH when i = n-1!
}

✅ for (i in 0 until n - 1) {
    val diff = nums[i + 1] - nums[i]
}
```

### Mistake 2: Wrong bound with lookback
```kotlin
❌ for (i in 0 until n) {
    val diff = nums[i] - nums[i - 1]  // CRASH when i = 0!
}

✅ for (i in 1 until n) {
    val diff = nums[i] - nums[i - 1]
}
```

### Mistake 3: Inconsistent thinking
```kotlin
❌ for (i in 0 until n) {          // Loop suggests processing elements
    val diff = nums[i + 1] - nums[i]  // But accessing edges!
}

✅ for (i in 0 until n - 1) {      // Loop matches access pattern
    val diff = nums[i + 1] - nums[i]
}
```

---

## Problem-Specific Patterns

### Good Arithmetic Sequences (Current Problem)
```kotlin
// Want: All subarrays with diff = ±1
for (start in 0 until n - 1) {       // Try each starting position
    val firstDiff = nums[start + 1] - nums[start]  // First edge

    if (firstDiff != 1 && firstDiff != -1) continue

    var end = start + 1
    while (end < n - 1) {             // Extend while pattern holds
        val nextDiff = nums[end + 1] - nums[end]
        if (nextDiff != firstDiff) break
        end++
    }
}
```

### LC 978: Turbulent Subarray
```kotlin
// Want: Longest subarray with alternating comparison signs
for (i in 1 until n) {                // Lookback approach
    val currentDiff = nums[i] - nums[i - 1]

    if (i >= 2) {
        val prevDiff = nums[i - 1] - nums[i - 2]
        if (currentDiff * prevDiff < 0) {  // Signs alternate
            // Extend sequence
        }
    }
}
```

---

## Quick Memory Tricks

1. **"Plus one means minus one"**
   - If you access `[i + 1]`, subtract 1 from upper bound
   - `nums[i + 1]` → loop bound `i < n - 1`

2. **"Minus one means plus one"**
   - If you access `[i - 1]`, add 1 to starting point
   - `nums[i - 1]` → start from `i = 1`

3. **"Count your items"**
   - n elements → loop `i < n`
   - n-1 edges → loop `i < n-1`

4. **"Boundary check first"**
   - Before accessing `nums[i+1]`, check `i < n-1`
   - Before accessing `nums[i-1]`, check `i > 0`

---

## Practice Template

When stuck, ask yourself:

1. **What am I accessing?**
   - [ ] Just nums[i]
   - [ ] nums[i] and nums[i+1]
   - [ ] nums[i] and nums[i-1]

2. **What's the last valid access?**
   - [ ] nums[i]: last valid is i = n-1
   - [ ] nums[i+1]: last valid is i = n-2
   - [ ] nums[i-1]: first valid is i = 1

3. **Set loop bound accordingly:**
   ```kotlin
   for (i in <start> until <end>) {
       // <start> and <end> determined by step 2
   }
   ```

---

## Summary Table

| Access Pattern | Loop Bound | Start | Reason |
|----------------|-----------|-------|---------|
| `nums[i]` | `i < n` | `i = 0` | Process all elements |
| `nums[i+1]` | `i < n-1` | `i = 0` | i+1 must be < n |
| `nums[i-1]` | `i < n` | `i = 1` | i-1 must be >= 0 |
| `nums[i+k]` | `i < n-k` | `i = 0` | i+k must be < n |
| `nums[i-k]` | `i < n` | `i = k` | i-k must be >= 0 |

---

## Remember

> **"The loop bound protects the array access, not the other way around."**

Always ensure your loop bound makes your array accesses safe!
