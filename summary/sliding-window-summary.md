# Key Patterns and Approaches for Sliding Window Problems

Based on the problems in `problems/sliding-window-problems.md`, here is a summary of the key patterns and approaches for solving sliding window problems.

Sliding window is a powerful technique for solving problems involving contiguous subarrays or substrings. It converts potentially O(n²) brute force solutions into efficient O(n) algorithms by maintaining a window that slides across the data.

### 1. Fixed Size Window

The window size is predetermined and remains constant throughout the algorithm.

#### 1.1 Basic Fixed Window
*   **Core Idea:** Maintain a window of exactly k elements and slide it across the array.
*   **Key Framework:**
    ```python
    def fixed_window(arr, k):
        window_sum = sum(arr[:k])  # Initial window
        result = process(window_sum)
        
        for i in range(k, len(arr)):
            # Slide window: remove left, add right
            window_sum = window_sum - arr[i-k] + arr[i]
            result = update(result, window_sum)
        return result
    ```
*   **Examples:** [1456. Maximum Number of Vowels in a Substring of Given Length](../leetcode/1456.maximum-number-of-vowels-in-a-substring-of-given-length.md), [1984. Minimum Difference Between Highest and Lowest of K Scores](../leetcode/1984.minimum-difference-between-highest-and-lowest-of-k-scores.md), [1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold](../leetcode/1343.number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold.md).

#### 1.2 Fixed Window with Complex State
*   **Core Idea:** Track more complex information than simple sums (frequencies, distinct counts, etc.).
*   **Key Components:**
    *   Hash map for frequency tracking
    *   Additional variables for window state
    *   Careful state updates when sliding
*   **Examples:** [438. Find All Anagrams in a String](../leetcode/438.find-all-anagrams-in-a-string.md), [567. Permutation in String](../leetcode/567.permutation-in-string.md).

### 2. Variable Size Window - Longest Valid Window

Find the longest subarray/substring that satisfies certain conditions.

#### 2.1 Longest Without Constraint Violation
*   **Core Idea:** Expand window as much as possible while maintaining validity.
*   **Key Framework:**
    ```python
    def longest_window(arr):
        left = 0
        max_length = 0
        
        for right in range(len(arr)):
            # Add arr[right] to window
            add_to_window(arr[right])
            
            # Shrink window while invalid
            while not is_valid():
                remove_from_window(arr[left])
                left += 1
            
            max_length = max(max_length, right - left + 1)
        return max_length
    ```
*   **Examples:** [3. Longest Substring Without Repeating Characters](../leetcode/3.longest-substring-without-repeating-characters.md), [1004. Max Consecutive Ones III](../leetcode/1004.max-consecutive-ones-iii.md), [424. Longest Repeating Character Replacement](../leetcode/424.longest-repeating-character-replacement.md).

#### 2.2 Longest with At Most K Constraint
*   **Core Idea:** Allow at most K violations or changes while maximizing window size.
*   **Key Pattern:**
    *   Track violation count or constraint usage
    *   Shrink window when constraint is exceeded
*   **Examples:** [1004. Max Consecutive Ones III](../leetcode/1004.max-consecutive-ones-iii.md) (at most K zeros), [424. Longest Repeating Character Replacement](../leetcode/424.longest-repeating-character-replacement.md) (at most K changes).

### 3. Variable Size Window - Shortest Valid Window

Find the shortest subarray/substring that satisfies certain conditions.

#### 3.1 Shortest Covering Window
*   **Core Idea:** Find minimum window that contains all required elements.
*   **Key Framework:**
    ```python
    def shortest_window(arr, target):
        left = 0
        min_length = float('inf')
        
        for right in range(len(arr)):
            # Expand window
            add_to_window(arr[right])
            
            # Contract window while valid
            while is_valid():
                min_length = min(min_length, right - left + 1)
                remove_from_window(arr[left])
                left += 1
        
        return min_length if min_length != float('inf') else 0
    ```
*   **Examples:** [76. Minimum Window Substring](../leetcode/76.minimum-window-substring.md), [209. Minimum Size Subarray Sum](../leetcode/209.minimum-size-subarray-sum.md).

### 4. Subarray Counting Problems

Count the number of subarrays that satisfy certain conditions.

#### 4.1 "At Most" to "Exactly" Conversion
*   **Core Idea:** Convert "exactly K" problems to "at most K" - "at most K-1".
*   **Key Insight:** `exactly(K) = atMost(K) - atMost(K-1)`
*   **Framework:**
    ```python
    def count_exactly(arr, k):
        return count_at_most(arr, k) - count_at_most(arr, k-1)
    ```

#### 4.2 Longer is Better (越長越合法)
*   **Core Idea:** If a subarray is valid, all shorter subarrays ending at the same position are also valid.
*   **Key Pattern:** When right pointer is fixed, count valid left positions.
*   **Formula:** `ans += left` (number of valid subarrays ending at right)
*   **Examples:** [3325. Count Substrings With K-Frequency Characters I](../leetcode/3325.count-substrings-with-k-frequency-characters-i.md), [2799. Count Complete Subarrays in an Array](../leetcode/2799.count-complete-subarrays-in-an-array.md).

#### 4.3 Shorter is Better (越短越合法)
*   **Core Idea:** If a subarray is valid, all longer subarrays starting at the same position are also valid.
*   **Key Pattern:** When left pointer is fixed, count valid right positions.
*   **Formula:** `ans += right - left + 1` (number of valid subarrays starting at left)
*   **Examples:** [713. Subarray Product Less Than K](../leetcode/713.subarray-product-less-than-k.md).

### 5. Advanced Window Techniques

#### 5.1 Multiple Windows
*   **Core Idea:** Maintain multiple sliding windows simultaneously.
*   **Applications:** 
    *   Compare different window sizes
    *   Track multiple constraints
*   **Examples:** Problems requiring comparison between different window configurations.

#### 5.2 Window with Deque (Monotonic Queue)
*   **Core Idea:** Use deque to maintain min/max in sliding window efficiently.
*   **Key Properties:**
    *   Deque stores indices in monotonic order
    *   Front of deque always contains window min/max
    *   O(1) amortized updates
*   **Examples:** [239. Sliding Window Maximum](../leetcode/239.sliding-window-maximium.md).

### 6. Circular and Special Windows

#### 6.1 Circular Arrays
*   **Core Idea:** Handle wraparound by processing the array as if it were circular.
*   **Key Approaches:**
    *   Concatenate array with itself
    *   Use modular arithmetic for indices
*   **Examples:** [1652. Defuse the Bomb](../leetcode/1652.defuse-the-bomb.md), [2134. Minimum Swaps to Group All 1's Together II](../leetcode/2134.minimum-swaps-to-group-all-1s-together-ii.md).

---

### Key Decision Framework

1. **Is Window Size Fixed?**
   - Yes → Fixed size window pattern
   - No → Variable size window pattern

2. **What Are You Optimizing?**
   - Longest valid window → Expand greedily, contract when invalid
   - Shortest valid window → Contract greedily, expand when invalid
   - Count subarrays → Use at most pattern with conversion

3. **What Makes a Window Valid/Invalid?**
   - Sum constraints → Track running sum
   - Character constraints → Use frequency maps
   - Distinct element constraints → Use sets or frequency maps

### Common State Management Patterns

1. **Running Sum:** For numerical constraints
2. **Frequency Map:** For character/element frequency constraints
3. **Set/Counter:** For distinct element constraints
4. **Deque:** For min/max in window
5. **Boolean Flags:** For binary state tracking

### Template Recognition Guide

| Problem Type | Template | Key Insight |
|-------------|----------|-------------|
| Fixed size K | Slide window of size K | Maintain exactly K elements |
| Longest valid | Expand right, shrink left when invalid | Maximize window size |
| Shortest valid | Expand right, shrink left while valid | Minimize window size |
| Count "exactly K" | atMost(K) - atMost(K-1) | Convert to at most problems |
| Count "at most K" | Two pointers with constraint tracking | Count valid subarrays |

### Summary of Key Approaches

1. **Identify Window Type:** Fixed vs variable size determines the basic approach.

2. **Choose Right Data Structure:** Arrays for sums, maps for frequencies, deques for min/max.

3. **Handle State Updates Correctly:** Be careful when adding/removing elements from window state.

4. **Master the Two-Pointer Technique:** Most sliding window problems use two pointers (left and right boundaries).

5. **Convert Complex Constraints:** Use "at most" pattern to handle "exactly" constraints.

6. **Practice State Management:** The key skill is correctly maintaining window state as it slides.

Sliding window problems are among the most elegant in competitive programming, converting quadratic brute force solutions into linear algorithms. Success comes from recognizing the window pattern and correctly managing the window state.