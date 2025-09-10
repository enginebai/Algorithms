/**
 * LeetCode 1106: Parsing A Boolean Expression (Recursive Approach)
 *
 * Problem:
 * Given a boolean expression as a string, return the result of evaluating it.
 *
 * Expression format:
 * - 't' → true
 * - 'f' → false
 * - '!(expr)' → logical NOT
 * - '&(expr1,expr2,...)' → logical AND (all must be true)
 * - '|(expr1,expr2,...)' → logical OR (at least one must be true)
 *
 * Examples:
 * - "!(f)" → true
 * - "|(f,t)" → true
 * - "&(t,f)" → false
 * - "|(&(t,f,t),!(t))" → false
 *
 * Approach:
 * Use recursion to handle nested expressions. When we encounter an operator,
 * recursively evaluate operands inside parentheses until we hit ')'.
 * Use global index to track position (similar to LC 394: Decode String).
 */

class ParseBoolExprRecursive {

    // Global index to track position in the expression string
    private var index = 0

    /**
     * Main function to parse and evaluate boolean expression
     * Time: O(n) - visit each character once
     * Space: O(depth) - recursion stack depth equals nesting level
     */
    fun parseBoolExpr(expression: String): Boolean {
        // Reset index for each call (important for multiple test cases)
        index = 0
        return evaluate(expression)
    }

    /**
     * Recursive function to evaluate the boolean expression
     *
     * Algorithm:
     * 1. Base cases: 't' returns true, 'f' returns false
     * 2. Recursive case: operator(operands)
     *    - Parse operator ('!', '&', '|')
     *    - Skip '('
     *    - Collect operands by recursively evaluating sub-expressions
     *    - Skip commas between operands
     *    - Stop when we hit ')'
     *    - Apply operator to collected operands
     */
    private fun evaluate(expr: String): Boolean {
        val char = expr[index]

        // Base case 1: 't' → true
        if (char == 't') {
            index++
            return true
        }

        // Base case 2: 'f' → false
        if (char == 'f') {
            index++
            return false
        }

        // Recursive case: operator(operands)
        val operator = char  // '!', '&', or '|'
        index++  // Move past operator
        index++  // Move past '('

        // Collect operands by recursively evaluating sub-expressions
        val operands = mutableListOf<Boolean>()

        while (expr[index] != ')') {
            if (expr[index] == ',') {
                // Skip comma separator
                index++
            } else {
                // RECURSIVE CALL: evaluate sub-expression
                // This handles nested expressions like &(t,f) inside |(...)
                val operandResult = evaluate(expr)
                operands.add(operandResult)
            }
        }

        index++  // Move past ')'

        // Apply operator to collected operands
        return applyOperator(operator, operands)
    }

    /**
     * Apply boolean operator to operands
     */
    private fun applyOperator(operator: Char, operands: List<Boolean>): Boolean {
        return when (operator) {
            '!' -> {
                // NOT: negate the single operand
                !operands[0]
            }
            '&' -> {
                // AND: true if all operands are true
                operands.all { it }
            }
            '|' -> {
                // OR: true if at least one operand is true
                operands.any { it }
            }
            else -> throw IllegalArgumentException("Unknown operator: $operator")
        }
    }
}

/**
 * Visualization of recursion for "|(&(t,f,t),!(t))":
 *
 * evaluate(expr, index=0)                          operator='|'  operands=[]
 * ├─ expr[0]='|' → operator='|', index=1
 * ├─ expr[1]='(' → index=2
 * ├─ Start collecting operands:
 * │
 * │  ── Operand 1: expr[2]='&'
 * │     evaluate(expr, index=2)                    operator='&'  operands=[]
 * │     ├─ expr[2]='&' → operator='&', index=3
 * │     ├─ expr[3]='(' → index=4
 * │     ├─ Start collecting operands:
 * │     │
 * │     │  ── Operand 1: expr[4]='t'
 * │     │     evaluate(expr, index=4)
 * │     │     └─ index=5, RETURN true
 * │     │
 * │     │  ── expr[5]=',' → skip, index=6
 * │     │
 * │     │  ── Operand 2: expr[6]='f'
 * │     │     evaluate(expr, index=6)
 * │     │     └─ index=7, RETURN false
 * │     │
 * │     │  ── expr[7]=',' → skip, index=8
 * │     │
 * │     │  ── Operand 3: expr[8]='t'
 * │     │     evaluate(expr, index=8)
 * │     │     └─ index=9, RETURN true
 * │     │
 * │     ├─ expr[9]=')' → done collecting, index=10
 * │     ├─ Apply AND: all([true, false, true]) = false
 * │     └─ RETURN false
 * │
 * │  ── expr[10]=',' → skip, index=11
 * │
 * │  ── Operand 2: expr[11]='!'
 * │     evaluate(expr, index=11)                   operator='!'  operands=[]
 * │     ├─ expr[11]='!' → operator='!', index=12
 * │     ├─ expr[12]='(' → index=13
 * │     ├─ Start collecting operands:
 * │     │
 * │     │  ── Operand 1: expr[13]='t'
 * │     │     evaluate(expr, index=13)
 * │     │     └─ index=14, RETURN true
 * │     │
 * │     ├─ expr[14]=')' → done collecting, index=15
 * │     ├─ Apply NOT: !true = false
 * │     └─ RETURN false
 * │
 * ├─ expr[15]=')' → done collecting, index=16
 * ├─ Apply OR: any([false, false]) = false
 * └─ RETURN false
 *
 * Final result: false
 */

/**
 * Alternative: Explicit operand type handling
 * This version makes the logic more explicit but is essentially the same
 */
class ParseBoolExprRecursiveVerbose {

    private var index = 0

    fun parseBoolExpr(expression: String): Boolean {
        index = 0
        return evaluate(expression)
    }

    private fun evaluate(expr: String): Boolean {
        val char = expr[index]

        return when {
            char == 't' -> {
                index++
                true
            }
            char == 'f' -> {
                index++
                false
            }
            char == '!' -> evaluateNot(expr)
            char == '&' -> evaluateAnd(expr)
            char == '|' -> evaluateOr(expr)
            else -> throw IllegalArgumentException("Invalid character: $char")
        }
    }

    private fun evaluateNot(expr: String): Boolean {
        index++  // Skip '!'
        index++  // Skip '('
        val operand = evaluate(expr)  // Evaluate single operand
        index++  // Skip ')'
        return !operand
    }

    private fun evaluateAnd(expr: String): Boolean {
        index++  // Skip '&'
        index++  // Skip '('

        var result = true
        while (expr[index] != ')') {
            if (expr[index] == ',') {
                index++
            } else {
                result = result && evaluate(expr)
            }
        }

        index++  // Skip ')'
        return result
    }

    private fun evaluateOr(expr: String): Boolean {
        index++  // Skip '|'
        index++  // Skip '('

        var result = false
        while (expr[index] != ')') {
            if (expr[index] == ',') {
                index++
            } else {
                result = result || evaluate(expr)
            }
        }

        index++  // Skip ')'
        return result
    }
}

/**
 * Key Insights for Recursive Approach:
 *
 * 1. WHEN TO RECURSE:
 *    - When collecting operands inside operator(...)
 *    - Each operand might be a nested expression → recurse!
 *
 * 2. BASE CASES:
 *    - 't' → return true
 *    - 'f' → return false
 *    - No further recursion needed
 *
 * 3. RECURSIVE CASE:
 *    - operator(operands)
 *    - Collect operands by recursive evaluation
 *    - Apply operator to results
 *
 * 4. POSITION TRACKING:
 *    - Global index advances through recursive calls
 *    - No need to pass/return index
 *
 * 5. OPERAND COLLECTION:
 *    - Loop until we hit ')'
 *    - Skip commas
 *    - Recursively evaluate each operand
 */

/**
 * Comparison with LC 394 (Decode String):
 *
 * SIMILARITIES:
 * - Both have nested structures
 * - Both use global index for position tracking
 * - Both recurse on opening delimiter ('[' vs '(')
 * - Both return on closing delimiter (']' vs ')')
 *
 * DIFFERENCES:
 * ┌─────────────────┬──────────────────────┬──────────────────────────┐
 * │ Aspect          │ LC 394               │ LC 1106                  │
 * ├─────────────────┼──────────────────────┼──────────────────────────┤
 * │ Return type     │ String               │ Boolean                  │
 * │ Prefix          │ Number (repeat)      │ Operator (!, &, |)       │
 * │ Delimiter       │ [ ... ]              │ ( ... )                  │
 * │ Separator       │ None                 │ Comma                    │
 * │ Operation       │ Repeat string        │ Apply boolean operation  │
 * │ Base case       │ Letter               │ 't' or 'f'               │
 * └─────────────────┴──────────────────────┴──────────────────────────┘
 *
 * PATTERN:
 * Both follow the same recursive pattern for nested structures:
 * 1. Parse prefix (number/operator)
 * 2. Enter nested structure (skip opening delimiter)
 * 3. Recursively process contents (may have more nesting)
 * 4. Exit nested structure (skip closing delimiter)
 * 5. Apply operation (repeat/boolean operation)
 */

/**
 * Common Pitfalls:
 *
 * 1. Forgetting to skip commas
 *    ✗ while (expr[index] != ')') { result.add(evaluate(expr)) }
 *    ✓ if (expr[index] == ',') index++ else result.add(evaluate(expr))
 *
 * 2. Not moving index after ')'
 *    - After collecting operands, must skip ')'
 *    ✓ index++ after the while loop
 *
 * 3. Assuming NOT has multiple operands
 *    - NOT always has exactly 1 operand
 *    ✗ Collecting multiple operands for '!'
 *    ✓ operands[0] for NOT
 *
 * 4. Wrong operator logic
 *    ✗ AND: operands.any { it }  (should be all)
 *    ✓ AND: operands.all { it }
 *    ✗ OR: operands.all { it }   (should be any)
 *    ✓ OR: operands.any { it }
 */

/**
 * Optimization Opportunities:
 *
 * 1. Short-circuit evaluation for AND:
 *    - If any operand is false, result is false
 *    - Can stop evaluating remaining operands
 *
 * 2. Short-circuit evaluation for OR:
 *    - If any operand is true, result is true
 *    - Can stop evaluating remaining operands
 *
 * Example:
 * private fun evaluateAnd(expr: String): Boolean {
 *     while (expr[index] != ')') {
 *         if (expr[index] == ',') {
 *             index++
 *         } else {
 *             val operand = evaluate(expr)
 *             if (!operand) {
 *                 // Short-circuit: result is false, skip remaining
 *                 skipToClosingParen(expr)
 *                 return false
 *             }
 *         }
 *     }
 *     index++
 *     return true
 * }
 *
 * Note: For this problem, the optimization may not be worth the complexity
 * since we still need to parse the remaining expression to advance index.
 */

fun main() {
    val parser = ParseBoolExprRecursive()

    println("=== LeetCode 1106: Parsing A Boolean Expression ===\n")

    // Example 1: Simple NOT
    println("Example 1: !(f)")
    println("Result: ${parser.parseBoolExpr("!(f)")}")  // true
    println()

    // Example 2: Simple OR
    println("Example 2: |(f,t)")
    println("Result: ${parser.parseBoolExpr("|(f,t)")}")  // true
    println()

    // Example 3: Simple AND
    println("Example 3: &(t,f)")
    println("Result: ${parser.parseBoolExpr("&(t,f)")}")  // false
    println()

    // Example 4: Nested expression
    println("Example 4: |(&(t,f,t),!(t))")
    println("Result: ${parser.parseBoolExpr("|(&(t,f,t),!(t))")}")  // false
    println()

    // Example 5: Multiple nesting
    println("Example 5: &(|(f),&(t,t))")
    println("Result: ${parser.parseBoolExpr("&(|(f),&(t,t))")}")  // false
    println()

    // Example 6: Deep nesting
    println("Example 6: !(!(!(!t)))")
    println("Result: ${parser.parseBoolExpr("!(!(!(!t)))")}")  // true
    println()

    // Example 7: Complex expression
    println("Example 7: |(&(t,f,t),&(t,f,t,f),&(t,t))")
    println("Result: ${parser.parseBoolExpr("|(&(t,f,t),&(t,f,t,f),&(t,t))")}")  // true
    println()

    // Using verbose version
    println("=== Using Verbose Implementation ===\n")
    val parser2 = ParseBoolExprRecursiveVerbose()
    println("Example: |(&(t,f,t),!(t))")
    println("Result: ${parser2.parseBoolExpr("|(&(t,f,t),!(t))")}")  // false
}
