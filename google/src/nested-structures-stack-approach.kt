/**
 * Nested Structure Problems: Stack-Based Iterative Approach
 *
 * This file demonstrates how to solve nested structure problems using stacks
 * instead of recursion. The stack explicitly simulates the recursion call stack.
 *
 * Problems:
 * 1. LeetCode 394: Decode String
 * 2. LeetCode 1106: Parsing A Boolean Expression
 *
 * Key Insight:
 * Recursion uses the call stack implicitly.
 * Iterative approach uses explicit stack to track state at each nesting level.
 */

// ============================================================================
// LeetCode 394: Decode String (Stack Approach)
// ============================================================================

/**
 * LeetCode 394: Decode String
 *
 * Examples:
 * - "3[a]" → "aaa"
 * - "3[a2[c]]" → "accaccacc"
 * - "2[abc]3[cd]ef" → "abcabccdcdcdef"
 *
 * Stack Approach:
 * Use two stacks:
 * 1. countStack: stores the repeat count for each nesting level
 * 2. stringStack: stores the accumulated string at each nesting level
 *
 * When we see '[': push current state onto stacks, start fresh
 * When we see ']': pop state, repeat current string, append to previous level
 */
class DecodeStringStack {

    fun decodeString(s: String): String {
        val countStack = ArrayDeque<Int>()      // Stack of repeat counts
        val stringStack = ArrayDeque<String>()  // Stack of accumulated strings

        var currentString = ""  // Current string being built
        var currentNum = 0      // Current number being accumulated

        for (char in s) {
            when {
                char.isDigit() -> {
                    // Accumulate multi-digit number
                    currentNum = currentNum * 10 + (char - '0')
                }

                char == '[' -> {
                    // Push current state onto stacks (save context)
                    countStack.addLast(currentNum)
                    stringStack.addLast(currentString)

                    // Reset for new nesting level
                    currentNum = 0
                    currentString = ""
                }

                char == ']' -> {
                    // Pop previous state (restore context)
                    val prevString = stringStack.removeLast()
                    val repeatCount = countStack.removeLast()

                    // Build the repeated string
                    val repeated = currentString.repeat(repeatCount)

                    // Append to previous level's string
                    currentString = prevString + repeated
                }

                else -> {
                    // Regular letter, append to current string
                    currentString += char
                }
            }
        }

        return currentString
    }
}

/**
 * Trace for "3[a2[c]]":
 *
 * Initial state:
 *   currentString = ""
 *   currentNum = 0
 *   countStack = []
 *   stringStack = []
 *
 * char='3': currentNum = 3
 *
 * char='[': Push state onto stacks
 *   countStack = [3]
 *   stringStack = [""]
 *   currentNum = 0
 *   currentString = ""
 *
 * char='a': currentString = "a"
 *
 * char='2': currentNum = 2
 *
 * char='[': Push state onto stacks
 *   countStack = [3, 2]
 *   stringStack = ["", "a"]
 *   currentNum = 0
 *   currentString = ""
 *
 * char='c': currentString = "c"
 *
 * char=']': Pop state and repeat
 *   repeatCount = 2 (from countStack)
 *   prevString = "a" (from stringStack)
 *   repeated = "c" * 2 = "cc"
 *   currentString = "a" + "cc" = "acc"
 *   countStack = [3]
 *   stringStack = [""]
 *
 * char=']': Pop state and repeat
 *   repeatCount = 3 (from countStack)
 *   prevString = "" (from stringStack)
 *   repeated = "acc" * 3 = "accaccacc"
 *   currentString = "" + "accaccacc" = "accaccacc"
 *   countStack = []
 *   stringStack = []
 *
 * Return: "accaccacc"
 */

// ============================================================================
// LeetCode 1106: Parsing A Boolean Expression (Stack Approach)
// ============================================================================

/**
 * LeetCode 1106: Parsing A Boolean Expression
 *
 * Examples:
 * - "!(f)" → true
 * - "|(f,t)" → true
 * - "|(&(t,f,t),!(t))" → false
 *
 * Stack Approach:
 * Use two stacks:
 * 1. operatorStack: stores operators ('!', '&', '|') for each nesting level
 * 2. operandStack: stores lists of operands for each nesting level
 *
 * When we see '(': push new operand list onto stack (start new level)
 * When we see ')': evaluate current level, push result to parent level
 */
class ParseBoolExprStack {

    fun parseBoolExpr(expression: String): Boolean {
        val operatorStack = ArrayDeque<Char>()                    // Stack of operators
        val operandStack = ArrayDeque<MutableList<Boolean>>()     // Stack of operand lists

        for (char in expression) {
            when {
                char == '!' || char == '&' || char == '|' -> {
                    // Push operator onto stack
                    operatorStack.addLast(char)
                }

                char == '(' -> {
                    // Start new nesting level: push new operand list
                    operandStack.addLast(mutableListOf())
                }

                char == 't' -> {
                    // Add 'true' to current operand list
                    operandStack.last().add(true)
                }

                char == 'f' -> {
                    // Add 'false' to current operand list
                    operandStack.last().add(false)
                }

                char == ')' -> {
                    // End of current level: evaluate and push result up
                    val operator = operatorStack.removeLast()
                    val operands = operandStack.removeLast()

                    // Apply operator to operands
                    val result = applyOperator(operator, operands)

                    // Push result to parent level (or it's the final result)
                    if (operandStack.isNotEmpty()) {
                        operandStack.last().add(result)
                    } else {
                        // Final result
                        return result
                    }
                }

                // char == ',' → skip (separator, no action needed)
            }
        }

        // Should not reach here for valid input
        throw IllegalStateException("Invalid expression")
    }

    private fun applyOperator(operator: Char, operands: List<Boolean>): Boolean {
        return when (operator) {
            '!' -> !operands[0]
            '&' -> operands.all { it }
            '|' -> operands.any { it }
            else -> throw IllegalArgumentException("Unknown operator: $operator")
        }
    }
}

/**
 * Trace for "|(&(t,f),!(f))":
 *
 * Initial state:
 *   operatorStack = []
 *   operandStack = []
 *
 * char='|': operatorStack = ['|']
 *
 * char='(': operandStack = [[]]  // Start level 1
 *
 * char='&': operatorStack = ['|', '&']
 *
 * char='(': operandStack = [[], []]  // Start level 2
 *
 * char='t': operandStack = [[], [true]]
 *
 * char=',': (skip)
 *
 * char='f': operandStack = [[], [true, false]]
 *
 * char=')': Evaluate level 2
 *   operator = '&' (pop from operatorStack)
 *   operands = [true, false] (pop from operandStack)
 *   result = all([true, false]) = false
 *   Push to parent: operandStack = [[false]]
 *   operatorStack = ['|']
 *
 * char=',': (skip)
 *
 * char='!': operatorStack = ['|', '!']
 *
 * char='(': operandStack = [[false], []]  // Start level 2 again
 *
 * char='f': operandStack = [[false], [false]]
 *
 * char=')': Evaluate level 2
 *   operator = '!' (pop from operatorStack)
 *   operands = [false] (pop from operandStack)
 *   result = !false = true
 *   Push to parent: operandStack = [[false, true]]
 *   operatorStack = ['|']
 *
 * char=')': Evaluate level 1
 *   operator = '|' (pop from operatorStack)
 *   operands = [false, true] (pop from operandStack)
 *   result = any([false, true]) = true
 *   operandStack is empty → return true
 *
 * Return: true
 */

// ============================================================================
// Comparison: Recursive vs Stack Approach
// ============================================================================

/**
 * RECURSIVE APPROACH:
 *
 * Pros:
 * ✅ Natural and intuitive for nested structures
 * ✅ Cleaner, more readable code
 * ✅ Automatically handles state management via call stack
 * ✅ Less code to write
 *
 * Cons:
 * ❌ Uses implicit call stack (limited by stack size)
 * ❌ Stack overflow risk for very deep nesting
 * ❌ Function call overhead (slightly slower)
 * ❌ Harder to debug (multiple stack frames)
 *
 * When to use:
 * - Problem naturally fits recursive thinking
 * - Nesting depth is reasonable (< 1000 levels typically)
 * - Code clarity is priority
 *
 * ────────────────────────────────────────────────────────────────────────
 *
 * STACK APPROACH:
 *
 * Pros:
 * ✅ Explicit control over stack (can handle deep nesting)
 * ✅ No recursion overhead
 * ✅ Can add custom logic (pause/resume, debugging)
 * ✅ Easier to convert to iterative if needed
 *
 * Cons:
 * ❌ More complex code
 * ❌ Manual state management
 * ❌ Harder to understand initially
 * ❌ More code to write and maintain
 *
 * When to use:
 * - Very deep nesting possible (risk of stack overflow)
 * - Need explicit control over execution
 * - Converting existing recursive code
 * - Performance critical (avoid function calls)
 */

/**
 * State Management Comparison:
 *
 * RECURSIVE (Implicit State):
 * ────────────────────────────
 * Call Stack:
 *   evaluate("3[a2[c]]") { number=3, result="" }
 *     ↓
 *   evaluate("a2[c]") { number=2, result="a" }
 *     ↓
 *   evaluate("c") { number=0, result="c" }
 *
 * Each function call maintains its own local variables.
 * When function returns, caller's state is automatically restored.
 *
 * ────────────────────────────────────────────────────────────────────────
 *
 * STACK (Explicit State):
 * ────────────────────────────
 * Manual Stacks:
 *   countStack = [3, 2]
 *   stringStack = ["", "a"]
 *   currentNum = 0
 *   currentString = "c"
 *
 * We explicitly push/pop state to/from our own stacks.
 * State restoration is manual via pop operations.
 */

/**
 * Key Insight: The Correspondence
 *
 * Recursive Call Stack          ←→  Explicit Stack
 * ─────────────────────────────────────────────────
 * Function call                 ←→  Push onto stack
 * Local variables               ←→  Items in stack
 * Function return               ←→  Pop from stack
 * Return value                  ←→  Popped value
 * Multiple parameters           ←→  Multiple stacks
 *
 * Every recursive algorithm can be converted to iterative with explicit stack!
 */

/**
 * When '[' or '(':                 When ']' or ')':
 *
 * RECURSIVE:                       RECURSIVE:
 * - Make recursive call            - Return from function
 * - Push frame to call stack       - Pop frame from call stack
 * - New local variables            - Restore previous variables
 *                                  - Use returned value
 *
 * STACK:                           STACK:
 * - Push current state             - Pop saved state
 * - Reset current variables        - Restore variables
 * - Start fresh context            - Continue with result
 */

// ============================================================================
// Test Cases
// ============================================================================

fun main() {
    println("═".repeat(70))
    println("Nested Structure Problems: Stack vs Recursive Approach")
    println("═".repeat(70))

    // ========================================================================
    // LC 394: Decode String
    // ========================================================================
    println("\n${"─".repeat(70)}")
    println("LeetCode 394: Decode String (Stack Approach)")
    println("─".repeat(70))

    val decoder = DecodeStringStack()

    val test1 = "3[a]"
    println("Input:  \"$test1\"")
    println("Output: \"${decoder.decodeString(test1)}\"")
    println("Expected: \"aaa\"")
    println()

    val test2 = "3[a2[c]]"
    println("Input:  \"$test2\"")
    println("Output: \"${decoder.decodeString(test2)}\"")
    println("Expected: \"accaccacc\"")
    println()

    val test3 = "2[abc]3[cd]ef"
    println("Input:  \"$test3\"")
    println("Output: \"${decoder.decodeString(test3)}\"")
    println("Expected: \"abcabccdcdcdef\"")
    println()

    val test4 = "10[a]"
    println("Input:  \"$test4\"")
    println("Output: \"${decoder.decodeString(test4)}\"")
    println("Expected: \"${"a".repeat(10)}\"")
    println()

    // ========================================================================
    // LC 1106: Parsing Boolean Expression
    // ========================================================================
    println("─".repeat(70))
    println("LeetCode 1106: Parsing Boolean Expression (Stack Approach)")
    println("─".repeat(70))

    val parser = ParseBoolExprStack()

    val expr1 = "!(f)"
    println("Input:  \"$expr1\"")
    println("Output: ${parser.parseBoolExpr(expr1)}")
    println("Expected: true")
    println()

    val expr2 = "|(f,t)"
    println("Input:  \"$expr2\"")
    println("Output: ${parser.parseBoolExpr(expr2)}")
    println("Expected: true")
    println()

    val expr3 = "&(t,f)"
    println("Input:  \"$expr3\"")
    println("Output: ${parser.parseBoolExpr(expr3)}")
    println("Expected: false")
    println()

    val expr4 = "|(&(t,f,t),!(t))"
    println("Input:  \"$expr4\"")
    println("Output: ${parser.parseBoolExpr(expr4)}")
    println("Expected: false")
    println()

    val expr5 = "|(&(t,f),!(f))"
    println("Input:  \"$expr5\"")
    println("Output: ${parser.parseBoolExpr(expr5)}")
    println("Expected: true")
    println()

    println("═".repeat(70))
    println("All tests completed!")
    println("═".repeat(70))
}
