# Quick Answer: i-1 vs i OR i vs i+1?

## The Answer: **BOTH ARE IDENTICAL!**

They compute the **exact same edges**, just with different loop indices.

---

## Visual Proof

```
Array: [10, 20, 30, 40]

Edges (differences between consecutive elements):
    0    1    2    3   ← element indices
   [10, 20, 30, 40]
     └──┬ └──┬ └──┬
      e0  e1  e2        ← 3 edges


LOOKAHEAD (i vs i+1):
for (i in 0 until n-1) {     // i = 0, 1, 2
    diff = nums[i+1] - nums[i]
}

i=0: nums[1] - nums[0] = 20 - 10 = 10  → Edge 0 ✓
i=1: nums[2] - nums[1] = 30 - 20 = 10  → Edge 1 ✓
i=2: nums[3] - nums[2] = 40 - 30 = 10  → Edge 2 ✓


LOOKBACK (i-1 vs i):
for (i in 1 until n) {       // i = 1, 2, 3
    diff = nums[i] - nums[i-1]
}

i=1: nums[1] - nums[0] = 20 - 10 = 10  → Edge 0 ✓
i=2: nums[2] - nums[1] = 30 - 20 = 10  → Edge 1 ✓
i=3: nums[3] - nums[2] = 40 - 30 = 10  → Edge 2 ✓
```

**See? Both compute Edge 0, Edge 1, Edge 2!**

The only difference: the variable `i` refers to different positions in each approach.

---

## So Which Should I Use?

**BOTH ARE CORRECT!** Choose based on clarity:

### Use **LOOKAHEAD** (i vs i+1) when:
- ✅ Building/extending sequences **forward**
- ✅ You think: "Start at position i, what comes **next**?"
- ✅ More natural for our Good Arithmetic Sequences problem

```kotlin
for (start in 0 until n - 1) {
    val diff = nums[start + 1] - nums[start]  // What comes next?

    var end = start + 1
    while (end < n - 1) {
        if (nums[end + 1] - nums[end] == diff) {  // Can we extend?
            end++
        }
    }
}
```

### Use **LOOKBACK** (i-1 vs i) when:
- ✅ Comparing current element with **previous** state
- ✅ You think: "Process element i, how does it compare to what came **before**?"
- ✅ More natural for DP-style problems

```kotlin
for (i in 1 until n) {
    val diff = nums[i] - nums[i - 1]  // How does this compare to previous?
    // Build from previous results...
}
```

---

## The Key Insight

```
Array of n elements → has n-1 edges

Both approaches visit all n-1 edges exactly once!

Lookahead: for (i in 0..n-2)  →  accesses nums[i+1]
Lookback:  for (i in 1..n-1)  →  accesses nums[i-1]

Different loop ranges, SAME edges computed!
```

---

## For Edge-Based Problems: **It Doesn't Matter!**

When your problem is about **edges** (differences/relationships):
- ✅ Both are mathematically equivalent
- ✅ Both have same time complexity
- ✅ Both visit every edge once
- ✅ Choose whichever makes your logic clearer

**Stop worrying about "which one is right" - they're BOTH right!**

---

## Quick Decision Guide

```
┌────────────────────────────────────┐
│ Am I looking at edges/differences? │
└────────────────────────────────────┘
           │
           ↓ YES
     ┌─────────────┐
     │ BOTH WORK!  │
     └─────────────┘
           │
    ┌──────┴──────┐
    ↓             ↓
Extending     Comparing
forward?      to previous?
    │             │
    ↓             ↓
Lookahead     Lookback
(i vs i+1)   (i-1 vs i)
```

---

## Stop Second-Guessing!

The confusion comes from thinking one is "more correct" than the other.

**Truth:** They're equivalent. Just pick one and stick with it!

**For our problem:** Lookahead feels more natural because we extend sequences forward.

**But:** If you prefer lookback, that's 100% fine too!
