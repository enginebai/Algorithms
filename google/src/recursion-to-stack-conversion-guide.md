# Converting Recursive Algorithms to Stack-Based Iterative Solutions

A systematic guide to transform any recursive algorithm into an iterative solution using explicit stacks.

---

## Table of Contents
1. [Why Convert Recursion to Stack?](#why-convert)
2. [The General Conversion Process](#general-process)
3. [Step-by-Step Conversion Framework](#conversion-framework)
4. [Pattern 1: Single Recursive Call](#pattern-1)
5. [Pattern 2: Multiple Recursive Calls](#pattern-2)
6. [Pattern 3: Recursive with Return Value](#pattern-3)
7. [Pattern 4: Recursive with State Accumulation](#pattern-4)
8. [Common Pitfalls and Solutions](#pitfalls)
9. [Practice Problems](#practice)

---

## Why Convert Recursion to Stack? {#why-convert}

### Pros of Stack-Based Approach
- ✅ **Avoid stack overflow** for deep recursion
- ✅ **Explicit control** over execution
- ✅ **Better performance** (no function call overhead)
- ✅ **Easier debugging** (can inspect stack state)
- ✅ **Can pause/resume** processing

### Cons
- ❌ **More complex code**
- ❌ **Manual state management**
- ❌ **Harder to understand initially**

**Rule of thumb:** Start with recursion for clarity, convert to stack if needed for performance or deep nesting.

---

## The General Conversion Process {#general-process}

### Core Principle: Recursion = Stack + Loop

```
Recursive Algorithm          Stack-Based Algorithm
───────────────────         ─────────────────────
Call stack (implicit)   →   Explicit stack (ArrayDeque)
Function calls          →   Push to stack
Function returns        →   Pop from stack
Local variables         →   Stack elements or shared variables
```

### The Pattern

```kotlin
// RECURSIVE
fun recursive(params) {
    // Base case
    if (baseCondition) return result

    // Recursive case
    val result = operation(recursive(newParams))
    return result
}

// STACK-BASED
fun iterative(params) {
    val stack = ArrayDeque<State>()
    stack.push(initialState)

    while (stack.isNotEmpty()) {
        val state = stack.pop()

        // Base case
        if (baseCondition) {
            handleResult(state)
            continue
        }

        // Recursive case → Push to stack
        stack.push(newState)
    }
}
```

---

## Step-by-Step Conversion Framework {#conversion-framework}

### Step 1: Identify What to Store in Stack

Ask yourself:
1. **What are the function parameters?** → These go in stack
2. **What local variables matter?** → These go in stack
3. **What's the return value?** → Track how to handle it
4. **What's the current position/state?** → This goes in stack

**Create a State class:**
```kotlin
data class State(
    val param1: Type1,
    val param2: Type2,
    val localVar: Type3,
    val phase: ProcessingPhase  // Optional: track where we are
)
```

### Step 2: Identify Recursion Points

Find all places where the function calls itself:
```kotlin
fun recursive(...) {
    // ...
    val result1 = recursive(newParams1)  // ← Recursion point 1
    // ...
    val result2 = recursive(newParams2)  // ← Recursion point 2
    // ...
}
```

Each recursion point needs a corresponding `stack.push()`.

### Step 3: Handle Base Cases

Base cases in recursion become conditional checks in the loop:
```kotlin
// Recursive base case
if (baseCondition) return value

// Stack-based equivalent
if (baseCondition) {
    // Store result or process immediately
    result = value
    continue  // Skip to next iteration
}
```

### Step 4: Replace Recursive Calls with Stack Operations

```kotlin
// Recursive call
val result = recursive(newParams)

// Stack-based
stack.push(State(newParams, ...))
```

### Step 5: Handle Return Values

This is the tricky part! Options:
1. **Store results in a separate stack**
2. **Use parent-child relationship in state**
3. **Process results when popping**

### Step 6: Convert Loop Structure

```kotlin
val stack = ArrayDeque<State>()
stack.push(initialState)
var result = initialValue

while (stack.isNotEmpty()) {
    val current = stack.pop()

    // Process current state
    // Push new states if needed
    // Accumulate results
}

return result
```

---

## Pattern 1: Single Recursive Call (Tail Recursion) {#pattern-1}

### Example: Factorial

**Recursive:**
```kotlin
fun factorial(n: Int): Int {
    if (n <= 1) return 1
    return n * factorial(n - 1)
}
```

**Analysis:**
- Parameter: `n`
- Base case: `n <= 1` returns 1
- Recursive call: `factorial(n - 1)`
- Operation: multiply `n` with result

**Stack-Based Conversion:**

```kotlin
// Approach 1: Store all states, compute on unwind
fun factorialStack(n: Int): Int {
    val stack = ArrayDeque<Int>()

    // Phase 1: Push all values onto stack
    var current = n
    while (current > 1) {
        stack.push(current)
        current--
    }

    // Phase 2: Pop and compute result
    var result = 1
    while (stack.isNotEmpty()) {
        result *= stack.pop()
    }

    return result
}

// Approach 2: Compute directly (since tail recursion)
fun factorialIterative(n: Int): Int {
    var result = 1
    for (i in 2..n) {
        result *= i
    }
    return result
}
```

**Note:** Tail recursion is easiest to convert - often doesn't even need a stack!

---

## Pattern 2: Multiple Recursive Calls {#pattern-2}

### Example: Fibonacci

**Recursive:**
```kotlin
fun fibonacci(n: Int): Int {
    if (n <= 1) return n
    return fibonacci(n - 1) + fibonacci(n - 2)  // Two recursive calls!
}
```

**Analysis:**
- Two recursive calls per invocation
- Need to track which call we're processing
- Need to combine results

**Stack-Based Conversion:**

```kotlin
data class FibState(
    val n: Int,
    val phase: Int = 0  // 0=initial, 1=after first call, 2=computed
)

fun fibonacciStack(n: Int): Int {
    if (n <= 1) return n

    val stack = ArrayDeque<FibState>()
    val results = mutableMapOf<Int, Int>()  // Memoization for computed values

    stack.push(FibState(n))

    while (stack.isNotEmpty()) {
        val state = stack.pop()

        // Base case
        if (state.n <= 1) {
            results[state.n] = state.n
            continue
        }

        // Check if already computed
        if (state.n in results) {
            continue
        }

        // Check if both children are computed
        val n1 = state.n - 1
        val n2 = state.n - 2

        if (n1 in results && n2 in results) {
            // Both computed, combine results
            results[state.n] = results[n1]!! + results[n2]!!
        } else {
            // Need to compute children first
            stack.push(state)  // Push back current state

            if (n1 !in results) {
                stack.push(FibState(n1))
            }
            if (n2 !in results) {
                stack.push(FibState(n2))
            }
        }
    }

    return results[n]!!
}
```

**Key insight:** Process children first, then parent. Push parent back onto stack until children are ready.

**Better approach for Fibonacci:** Use dynamic programming (iterative from bottom up):
```kotlin
fun fibonacciDP(n: Int): Int {
    if (n <= 1) return n
    var prev = 0
    var curr = 1
    repeat(n - 1) {
        val next = prev + curr
        prev = curr
        curr = next
    }
    return curr
}
```

---

## Pattern 3: Recursive with Return Value {#pattern-3}

### Example: Tree Maximum Value

**Recursive:**
```kotlin
data class TreeNode(val value: Int, val left: TreeNode? = null, val right: TreeNode? = null)

fun findMax(root: TreeNode?): Int {
    if (root == null) return Int.MIN_VALUE

    val leftMax = findMax(root.left)
    val rightMax = findMax(root.right)

    return maxOf(root.value, leftMax, rightMax)
}
```

**Stack-Based Conversion:**

```kotlin
data class TreeState(
    val node: TreeNode?,
    val phase: Phase = Phase.INITIAL
) {
    enum class Phase {
        INITIAL,          // Haven't processed children
        LEFT_DONE,        // Left child processed
        BOTH_DONE         // Both children processed
    }
}

fun findMaxStack(root: TreeNode?): Int {
    if (root == null) return Int.MIN_VALUE

    val stack = ArrayDeque<Pair<TreeState, Int>>()  // (state, accumulated max)
    stack.push(TreeState(root) to Int.MIN_VALUE)

    var globalMax = Int.MIN_VALUE

    while (stack.isNotEmpty()) {
        val (state, currentMax) = stack.pop()

        if (state.node == null) {
            continue
        }

        when (state.phase) {
            TreeState.Phase.INITIAL -> {
                // Push back with next phase
                stack.push(TreeState(state.node, TreeState.Phase.LEFT_DONE) to currentMax)
                // Process left child
                stack.push(TreeState(state.node.left) to Int.MIN_VALUE)
            }

            TreeState.Phase.LEFT_DONE -> {
                // Left is done, process right
                stack.push(TreeState(state.node, TreeState.Phase.BOTH_DONE) to currentMax)
                stack.push(TreeState(state.node.right) to Int.MIN_VALUE)
            }

            TreeState.Phase.BOTH_DONE -> {
                // Both children done, compute max
                val nodeMax = maxOf(state.node.value, currentMax)
                globalMax = maxOf(globalMax, nodeMax)
            }
        }
    }

    return globalMax
}
```

**Simpler approach using iterative traversal:**
```kotlin
fun findMaxIterative(root: TreeNode?): Int {
    if (root == null) return Int.MIN_VALUE

    val stack = ArrayDeque<TreeNode>()
    stack.push(root)
    var maxValue = Int.MIN_VALUE

    while (stack.isNotEmpty()) {
        val node = stack.pop()
        maxValue = maxOf(maxValue, node.value)

        node.left?.let { stack.push(it) }
        node.right?.let { stack.push(it) }
    }

    return maxValue
}
```

---

## Pattern 4: Recursive with State Accumulation {#pattern-4}

This is what we saw in LC 394 and LC 1106!

### Example: Decode String (Recap)

**Recursive:**
```kotlin
private var index = 0

fun decodeString(s: String): String {
    index = 0
    return decode(s)
}

private fun decode(s: String): String {
    var result = ""
    var number = 0

    while (index < s.length) {
        when (val char = s[index]) {
            in '0'..'9' -> {
                number = number * 10 + (char - '0')
                index++
            }
            '[' -> {
                index++
                val nested = decode(s)  // RECURSIVE
                result += nested.repeat(number)
                number = 0
            }
            ']' -> {
                index++
                return result
            }
            else -> {
                result += char
                index++
            }
        }
    }
    return result
}
```

**Stack-Based:**
```kotlin
fun decodeStringStack(s: String): String {
    val countStack = ArrayDeque<Int>()
    val stringStack = ArrayDeque<String>()

    var currentString = ""
    var currentNum = 0

    for (char in s) {
        when {
            char.isDigit() -> {
                currentNum = currentNum * 10 + (char - '0')
            }
            char == '[' -> {
                // PUSH: Save state (like recursive call)
                countStack.push(currentNum)
                stringStack.push(currentString)
                currentNum = 0
                currentString = ""
            }
            char == ']' -> {
                // POP: Restore state (like return)
                val prevString = stringStack.pop()
                val repeatCount = countStack.pop()
                currentString = prevString + currentString.repeat(repeatCount)
            }
            else -> {
                currentString += char
            }
        }
    }
    return currentString
}
```

**Key pattern:**
- Opening delimiter (`[`, `(`) → **PUSH** state
- Closing delimiter (`]`, `)`) → **POP** state
- Accumulate between delimiters

---

## Common Pitfalls and Solutions {#pitfalls}

### Pitfall 1: Forgetting to Save All State

**Problem:**
```kotlin
// Missing: need to save both string AND number
stack.push(currentString)  // ❌ Forgot to save number!
```

**Solution:** Create a proper state class
```kotlin
data class State(val string: String, val number: Int)
stack.push(State(currentString, currentNum))  // ✓
```

### Pitfall 2: Processing Order Issues

**Problem:**
```kotlin
// Want to process A then B, but stack is LIFO
stack.push(stateA)
stack.push(stateB)
// B gets processed first! ❌
```

**Solution:** Push in reverse order
```kotlin
stack.push(stateB)  // Second
stack.push(stateA)  // First (will be popped first)
```

### Pitfall 3: Not Handling Return Values Correctly

**Problem:**
```kotlin
// How do we know the result is for which parent?
val result = processNode()
// Which parent needs this result? ❌
```

**Solution:** Use parent-child relationship
```kotlin
data class State(
    val node: Node,
    val parentResult: MutableList<Int>?  // Reference to parent's result
)
```

Or use a results map:
```kotlin
val results = mutableMapOf<Node, Result>()
```

### Pitfall 4: Infinite Loop

**Problem:**
```kotlin
while (stack.isNotEmpty()) {
    val state = stack.pop()
    stack.push(state)  // ❌ Always push back, never makes progress
}
```

**Solution:** Track processing phase or ensure progress
```kotlin
data class State(val data: Data, val phase: Int)

// Only push back if phase changes
if (needMoreWork) {
    stack.push(state.copy(phase = phase + 1))
}
```

### Pitfall 5: Stack Overflow Still Happens

**Problem:**
```kotlin
// Converted to stack but still gets stack overflow?
// Issue: Creating too many intermediate objects
```

**Solution:** Process incrementally, don't build entire stack at once
```kotlin
// Bad: Build everything first
for (i in 0 until 1000000) {
    stack.push(State(i))  // ❌ OutOfMemoryError
}

// Good: Process as you go
var current = 0
while (current < 1000000) {
    process(current)
    current++
}
```

---

## Conversion Checklist

Use this checklist when converting recursion to stack:

- [ ] **Identify all recursive calls** - where function calls itself
- [ ] **List all parameters and local variables** - what state to save
- [ ] **Determine base cases** - when to stop/return
- [ ] **Create State class** - encapsulate all needed data
- [ ] **Choose stack structure** - single stack, multiple stacks, or stack + map
- [ ] **Handle opening trigger** - push state onto stack
- [ ] **Handle closing trigger** - pop state from stack
- [ ] **Track processing phase** - if multiple recursive calls
- [ ] **Manage return values** - how results flow back to parent
- [ ] **Test with simple cases** - verify correctness
- [ ] **Test with nested cases** - verify deep recursion works

---

## Quick Reference: Recursion → Stack Mapping

| Recursive Concept | Stack Equivalent |
|-------------------|------------------|
| `function call` | `stack.push(state)` |
| `return value` | `stack.pop()` or store in map |
| `local variables` | Fields in State class |
| `if (base case)` | `if (condition) { continue }` |
| `recursive(left)` + `recursive(right)` | Push both, track phases |
| `return result` | Store in result variable or parent state |
| Stack frame | State object |
| Call depth | Stack size |

---

## Practice Problems

Try converting these recursive solutions to stack-based:

### Easy
1. **Sum of array elements**
   ```kotlin
   fun sum(arr: IntArray, index: Int = 0): Int {
       if (index >= arr.size) return 0
       return arr[index] + sum(arr, index + 1)
   }
   ```

2. **Reverse a string**
   ```kotlin
   fun reverse(s: String, index: Int = 0): String {
       if (index >= s.length) return ""
       return reverse(s, index + 1) + s[index]
   }
   ```

### Medium
3. **Binary tree inorder traversal**
   ```kotlin
   fun inorder(root: TreeNode?): List<Int> {
       if (root == null) return emptyList()
       return inorder(root.left) + root.value + inorder(root.right)
   }
   ```

4. **Generate all subsets**
   ```kotlin
   fun subsets(nums: IntArray, index: Int = 0): List<List<Int>> {
       if (index >= nums.size) return listOf(emptyList())
       val without = subsets(nums, index + 1)
       val with = without.map { it + nums[index] }
       return without + with
   }
   ```

### Hard
5. **N-Queens problem**
6. **Sudoku solver**
7. **Expression evaluation with parentheses**

---

## Summary: The Core Strategy

```
┌─────────────────────────────────────────────────────────┐
│ Recursive Algorithm                                     │
│                                                         │
│ 1. Base case → return                                  │
│ 2. Recursive call → wait for result                    │
│ 3. Process result → continue                           │
│ 4. Return final result                                 │
└─────────────────────────────────────────────────────────┘
                        ↓
                   CONVERT TO
                        ↓
┌─────────────────────────────────────────────────────────┐
│ Stack-Based Algorithm                                   │
│                                                         │
│ 1. Base case → continue to next iteration             │
│ 2. Push state → like making recursive call            │
│ 3. Pop state → like returning from call               │
│ 4. Loop until stack empty → return final result       │
└─────────────────────────────────────────────────────────┘
```

**Golden Rule:** Every recursive algorithm can be converted to iterative with an explicit stack. The stack simulates what the call stack does automatically.

---

## Key Takeaways

1. **Recursion = Stack + Loop** - They're equivalent in power
2. **Opening delimiter → Push** - Save current state
3. **Closing delimiter → Pop** - Restore previous state
4. **Local variables → Stack elements** - Store everything needed
5. **Return value → Result accumulation** - Track and combine results
6. **Deep nesting?** Use stack to avoid stack overflow
7. **Start with recursion** - Convert to stack only if needed

Remember: **Understanding recursion deeply makes stack conversion natural!**
