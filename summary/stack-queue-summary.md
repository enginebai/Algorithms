# Key Patterns and Approaches for Stack Problems

Based on the problems in `problems/stack-queue-problems.md`, here is a summary of the key patterns and approaches for solving stack-related problems.

Stacks operate on a **Last-In, First-Out (LIFO)** principle, which makes them ideal for handling problems that involve reversing order, processing nested structures, or managing sequential states.

### 1. General Applications & Simulation

This is the most direct use of a stack. If a problem requires you to process items in reverse order of their arrival or to keep track of a "history," a stack is a natural fit.

*   **Core Idea:** Use the stack to store elements as they appear. The LIFO property allows you to access the most recent items first.
*   **Key Approach:**
    *   Push elements onto the stack as you iterate through the input.
    *   Pop elements when you need to process or retrieve the most recently added item.
*   **Examples:** `Backspace String Compare`, `Design Browser History`, `Validate Stack Sequences`. In these cases, the stack simulates the described behavior directly (typing, browser navigation, or stack push/pop operations).

### 2. Nested Structures & Expression Evaluation

This pattern applies to problems where you need to parse and evaluate expressions or decode strings with nested components (e.g., parentheses, brackets).

*   **Core Idea:** Use a stack to manage different levels of nesting or to hold intermediate results. When you encounter a closing character (like `)` or `]`) or an operator, you process the contents of the current "level."
*   **Key Approach:**
    *   Iterate through the input string or expression.
    *   Push numbers, characters, or intermediate states onto the stack.
    *   When you hit an operator or a closing parenthesis, pop the necessary operands/elements, perform the calculation or decoding, and push the result back onto the stack.
*   **Examples:** `Evaluate Reverse Polish Notation`, `Decode String`, `Basic Calculator`. The stack holds numbers until an operator requires a calculation, or it holds the state (current string and repeat count) before entering a nested `[...]`.

### 3. Parentheses Validation & Balancing

This is a classic use case for stacks. These problems involve checking if a sequence of parentheses is valid, or calculating the minimum changes (additions, removals, swaps) needed to make it valid.

*   **Core Idea:** A stack is used to track unclosed opening parentheses.
*   **Key Approach:**
    *   Iterate through the string.
    *   When you see an opening parenthesis (`(`, `{`, `[`), push it onto the stack.
    *   When you see a closing parenthesis, check the top of the stack.
        *   If the stack is empty or the top is not a matching opener, the string is invalid.
        *   If it matches, pop the stack.
    *   After the loop, if the stack is empty, the string is valid. If not, it means there are unclosed opening parentheses.
*   **Examples:** `Valid Parentheses`, `Minimum Remove to Make Valid Parentheses`.

### 4. Remove Adjacent Items (Collision)

This pattern involves simplifying a sequence by repeatedly removing adjacent elements that satisfy a specific condition.

*   **Core Idea:** The stack stores the "stable" elements. The top of the stack represents the most recent element that hasn't been removed.
*   **Key Approach:**
    *   Iterate through the input sequence.
    *   For each element, compare it with the element at the top of the stack.
    *   If they form a pair that should be removed (e.g., adjacent duplicates, colliding asteroids), pop the stack.
    *   Otherwise, push the current element onto the stack.
    *   The final stack contains the elements that remain.
*   **Examples:** `Remove All Adjacent Duplicates In String`, `Asteroid Collision`.

### 5. Monotonic Stack

This is an advanced and powerful technique used to efficiently find the "next greater/smaller element" or "previous greater/smaller element" for all items in a sequence.

*   **Core Idea:** Maintain a stack where the elements are always in a sorted order (either increasing or decreasing).
*   **Key Approach:**
    *   Iterate through the input array.
    *   Before pushing the current element, look at the stack's top.
    *   While the top element violates the monotonic property (e.g., it's smaller than the current element in a decreasing stack), pop it. The element being popped has just found its "next greater element" (which is the current element).
    *   Finally, push the current element (or its index) onto the stack.
*   **Sub-patterns:**
    *   **Basic:** Find the next greater/smaller element (`Daily Temperatures`, `Next Greater Element`).
    *   **Rectangle/Area:** Calculate the largest area in a histogram. The monotonic stack helps find the nearest smaller bars on the left and right, which define the width of the rectangle for the current bar (`Largest Rectangle in Histogram`, `Trapping Rain Water`).
    *   **Contributions:** Calculate the sum of properties over all subarrays (e.g., sum of minimums). The monotonic stack helps determine for how many subarrays a given element `A[i]` is the minimum (`Sum of Subarray Minimums`).
*   **Examples:** `Daily Temperatures`, `Largest Rectangle in Histogram`, `Remove K Digits`.

---

### Summary of Key Approaches

1.  **Is order important?** If the problem involves processing things in reverse order of their appearance, think **Stack (LIFO)**. If it's in the same order, think **Queue (FIFO)**.
2.  **Is there nesting?** For problems with nested parentheses, recursive calculations, or decoding, a stack is excellent for managing the different layers or states.
3.  **Are you looking for the next/previous greater/smaller element?** This is a clear signal to use a **Monotonic Stack**. It's far more efficient (`O(n)`) than a brute-force approach.
4.  **Does the problem involve removing adjacent matching/colliding items?** A stack provides a clean and efficient way to process such sequences.
5.  **Are you dealing with a sliding window?** For finding the max/min in a sliding window, a **Monotonic Queue (Deque)** is the optimal tool (`Sliding Window Maximum`).
