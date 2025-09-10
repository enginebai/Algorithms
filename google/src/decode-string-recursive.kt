/**
 * LeetCode 394: Decode String (Recursive Approach)
 *
 * Problem:
 * Given an encoded string, return its decoded string.
 * Encoding rule: k[encoded_string] means the encoded_string inside the brackets
 * is repeated exactly k times.
 *
 * Examples:
 * - "3[a]" → "aaa"
 * - "3[a2[c]]" → "accaccacc"
 * - "2[abc]3[cd]ef" → "abcabccdcdcdef"
 *
 * Approach:
 * Use recursion to handle nested brackets. When we encounter '[', we recursively
 * decode the content inside until we hit ']'. Use a global index to track position.
 */

class DecodeStringRecursive {

    // Global index to track position in the string
    private var index = 0

    /**
     * Main function to decode the string
     * Time: O(output length) - proportional to the decoded string size
     * Space: O(depth) - recursion stack depth equals nesting level
     */
    fun decodeString(s: String): String {
        // Reset index for each call
        index = 0
        return decode(s)
    }

    /**
     * Recursive function to decode the string
     *
     * Algorithm:
     * 1. Accumulate letters into result
     * 2. When we see a digit, accumulate the full number (handle multi-digit)
     * 3. When we see '[', recursively decode what's inside
     * 4. When we see ']', return the result (end of current recursion level)
     * 5. Repeat the decoded string 'number' times
     */
    private fun decode(s: String): String {
        val result = StringBuilder()
        var number = 0

        while (index < s.length) {
            val char = s[index]

            when {
                // Case 1: Digit - accumulate the number
                char.isDigit() -> {
                    // Handle multi-digit numbers (e.g., "10[a]")
                    number = number * 10 + (char - '0')
                    index++
                }

                // Case 2: '[' - start of nested structure, RECURSE
                char == '[' -> {
                    index++  // Move past '['

                    // RECURSIVE CALL: decode the content inside brackets
                    // This will process until it hits the matching ']'
                    val nested = decode(s)

                    // After recursion, index points past the matching ']'
                    // Repeat the nested result 'number' times
                    repeat(number) {
                        result.append(nested)
                    }

                    // Reset number for next encoded section
                    number = 0
                }

                // Case 3: ']' - end of current level, RETURN
                char == ']' -> {
                    index++  // Move past ']'
                    // Return accumulated result to caller
                    return result.toString()
                }

                // Case 4: Letter - add to result
                else -> {
                    result.append(char)
                    index++
                }
            }
        }

        // Return final result (for top-level call)
        return result.toString()
    }
}

/**
 * Visualization of recursion for "3[a2[c]]":
 *
 * decode(s, index=0)                    result=""  number=0
 * ├─ s[0]='3' → number=3, index=1
 * ├─ s[1]='[' → index=2, RECURSE ↓
 * │
 * │  decode(s, index=2)                 result=""  number=0
 * │  ├─ s[2]='a' → result="a", index=3
 * │  ├─ s[3]='2' → number=2, index=4
 * │  ├─ s[4]='[' → index=5, RECURSE ↓
 * │  │
 * │  │  decode(s, index=5)              result=""  number=0
 * │  │  ├─ s[5]='c' → result="c", index=6
 * │  │  ├─ s[6]=']' → index=7, RETURN "c"
 * │  │
 * │  ├─ nested="c"
 * │  ├─ result="a" + "c"*2 = "a" + "cc" = "acc"
 * │  ├─ s[7]=']' → index=8, RETURN "acc"
 * │
 * ├─ nested="acc"
 * ├─ result="" + "acc"*3 = "accaccacc"
 * ├─ index=8 (end of string)
 * └─ RETURN "accaccacc"
 *
 * Final result: "accaccacc"
 */

/**
 * Alternative: Without Global Index (Cleaner but less intuitive for beginners)
 */
class DecodeStringRecursiveNoGlobal {

    /**
     * Returns Pair<decoded string, new index position>
     */
    fun decodeString(s: String): String {
        val (result, _) = decode(s, 0)
        return result
    }

    private fun decode(s: String, startIndex: Int): Pair<String, Int> {
        val result = StringBuilder()
        var number = 0
        var index = startIndex

        while (index < s.length) {
            val char = s[index]

            when {
                char.isDigit() -> {
                    number = number * 10 + (char - '0')
                    index++
                }

                char == '[' -> {
                    index++  // Move past '['

                    // Recursive call returns both result and new index
                    val (nested, newIndex) = decode(s, index)
                    index = newIndex

                    repeat(number) {
                        result.append(nested)
                    }
                    number = 0
                }

                char == ']' -> {
                    index++  // Move past ']'
                    return Pair(result.toString(), index)
                }

                else -> {
                    result.append(char)
                    index++
                }
            }
        }

        return Pair(result.toString(), index)
    }
}

/**
 * Key Insights for Recursive Approach:
 *
 * 1. WHEN TO RECURSE:
 *    - When we encounter '[' → start of nested structure
 *    - The content inside brackets has the same format as the original problem
 *
 * 2. WHAT TO RETURN:
 *    - The decoded string for the current level
 *    - Return when we see ']' (end of current level) or reach string end
 *
 * 3. HOW TO TRACK POSITION:
 *    - Global index: Simple but uses mutable state
 *    - Return pair: Cleaner but more complex
 *    - Both work equally well
 *
 * 4. HANDLING MULTI-DIGIT NUMBERS:
 *    - Accumulate: number = number * 10 + digit
 *    - Continue until we hit '['
 *
 * 5. RECURSION PATTERN:
 *    decode("3[a2[c]]")
 *      = 3 * decode("a2[c]")
 *              = "a" + 2 * decode("c")
 *                          = "c"
 *                      = "a" + "cc" = "acc"
 *          = 3 * "acc" = "accaccacc"
 */

/**
 * Common Pitfalls:
 *
 * 1. Forgetting to reset 'number' after using it
 *    ✗ result += nested * number
 *    ✓ result += nested * number; number = 0
 *
 * 2. Not handling multi-digit numbers
 *    ✗ number = char - '0'
 *    ✓ number = number * 10 + (char - '0')
 *
 * 3. Not moving index after recursion
 *    - After recursive call, index should point past ']'
 *    - The recursive function handles this by incrementing before return
 *
 * 4. Building string inefficiently
 *    ✗ result = result + nested  (creates new string each time)
 *    ✓ result.append(nested)     (StringBuilder is efficient)
 */

/**
 * Comparison: Recursive vs Iterative
 *
 * RECURSIVE:
 * Pros:
 *   - Natural for nested structures
 *   - Cleaner code
 *   - Easier to understand the flow
 * Cons:
 *   - Stack space for deep nesting
 *   - Slightly slower due to function call overhead
 *
 * ITERATIVE (using stack):
 * Pros:
 *   - No recursion stack overhead
 *   - Can handle very deep nesting
 * Cons:
 *   - More complex code
 *   - Harder to understand
 *   - Need to manually manage stack
 *
 * For this problem, recursive is preferred for clarity!
 */

fun main() {
    val decoder = DecodeStringRecursive()

    // Quick examples
    println("Example 1: ${decoder.decodeString("3[a]")}")           // "aaa"
    println("Example 2: ${decoder.decodeString("3[a2[c]]")}")       // "accaccacc"
    println("Example 3: ${decoder.decodeString("2[abc]3[cd]ef")}")  // "abcabccdcdcdef"

    // Using alternative implementation
    val decoder2 = DecodeStringRecursiveNoGlobal()
    println("\nAlternative implementation:")
    println("Example: ${decoder2.decodeString("3[a2[c]]")}")        // "accaccacc"
}
