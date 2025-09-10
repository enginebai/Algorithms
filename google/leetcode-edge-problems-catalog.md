# LeetCode Problems: Edge-Based Patterns Catalog

Problems organized by whether lookahead, lookback, or both work well.

---

## Category 1: Edge-Based - Both Work Equally Well

These problems check relationships/differences between consecutive elements. **You can use either lookahead or lookback.**

### ⭐ LC 978: Longest Turbulent Subarray
**Pattern:** Alternating comparison signs between consecutive elements
**Difficulty:** Medium

```kotlin
// Lookahead
for (i in 0 until n - 1) {
    val diff = arr[i + 1] - arr[i]
    // Check if alternates with previous
}

// Lookback (equally good)
for (i in 1 until n) {
    val diff = arr[i] - arr[i - 1]
    // Check if alternates with previous
}
```

**Why both work:** You're checking the pattern of edges (differences).

---

### ⭐ LC 896: Monotonic Array
**Pattern:** Check if array is entirely non-increasing or non-decreasing
**Difficulty:** Easy

```kotlin
// Lookahead
for (i in 0 until n - 1) {
    if (nums[i] < nums[i + 1]) increasing = true
    if (nums[i] > nums[i + 1]) decreasing = true
}

// Lookback (equally good)
for (i in 1 until n) {
    if (nums[i - 1] < nums[i]) increasing = true
    if (nums[i - 1] > nums[i]) decreasing = true
}
```

**Why both work:** Just checking all edges for direction.

---

### ⭐ LC 845: Longest Mountain in Array
**Pattern:** Find longest subarray that goes up then down
**Difficulty:** Medium

```kotlin
// Must go up: arr[i] < arr[i+1] < arr[i+2] < ...
// Then go down: ... > arr[j] > arr[j+1] > arr[j+2]

// Lookahead approach
for (i in 0 until n - 1) {
    if (nums[i] < nums[i + 1]) {  // Found uphill start
        // Extend uphill
        while (end < n - 1 && nums[end] < nums[end + 1]) end++
        // Extend downhill
        while (end < n - 1 && nums[end] > nums[end + 1]) end++
    }
}
```

**Why both work:** Checking if edges maintain upward/downward pattern.

---

### ⭐ LC 376: Wiggle Subsequence
**Pattern:** Alternating up-down pattern (not necessarily consecutive)
**Difficulty:** Medium

```kotlin
// Lookahead
for (i in 0 until n - 1) {
    val diff = nums[i + 1] - nums[i]
    if (diff > 0 && prevDiff <= 0) length++
    if (diff < 0 && prevDiff >= 0) length++
}

// Lookback (equally good)
for (i in 1 until n) {
    val diff = nums[i] - nums[i - 1]
    if (diff > 0 && prevDiff <= 0) length++
    if (diff < 0 && prevDiff >= 0) length++
}
```

**Why both work:** Tracking pattern of differences.

---

### ⭐ LC 1800: Maximum Ascending Subarray Sum
**Pattern:** Find maximum sum of strictly increasing contiguous subarray
**Difficulty:** Easy

```kotlin
// Lookahead
for (i in 0 until n - 1) {
    if (nums[i] < nums[i + 1]) {
        currentSum += nums[i + 1]
    } else {
        currentSum = nums[i + 1]
    }
}

// Lookback (equally good)
for (i in 1 until n) {
    if (nums[i - 1] < nums[i]) {
        currentSum += nums[i]
    } else {
        currentSum = nums[i]
    }
}
```

---

## Category 2: Lookahead More Natural

These problems involve **extending forward** or **planning ahead**.

### ⭐ LC 55: Jump Game
**Pattern:** Can reach end from current position?
**Difficulty:** Medium

```kotlin
// Lookahead is natural - "Where can I reach from here?"
for (i in 0 until n) {
    if (i > maxReach) return false
    maxReach = max(maxReach, i + nums[i])  // Look ahead: how far can I go?
}
```

**Why lookahead:** You're planning forward jumps, not looking back.

---

### ⭐ LC 45: Jump Game II
**Pattern:** Minimum jumps to reach end
**Difficulty:** Medium

```kotlin
// Lookahead - BFS-style level by level
for (i in 0 until n - 1) {
    farthest = max(farthest, i + nums[i])  // Look ahead: max reach from here
    if (i == currentEnd) {
        jumps++
        currentEnd = farthest
    }
}
```

**Why lookahead:** Planning future positions based on current state.

---

### ⭐ LC 134: Gas Station
**Pattern:** Find starting gas station to complete circuit
**Difficulty:** Medium

```kotlin
// Lookahead - "Can I reach the next station?"
for (i in 0 until n) {
    tank += gas[i] - cost[i]
    if (tank < 0) {
        // Can't reach next station from current start
        start = i + 1  // Try starting from next position
        tank = 0
    }
}
```

**Why lookahead:** Checking if you can reach the **next** station.

---

### ⭐ LC 1014: Best Sightseeing Pair
**Pattern:** Find i < j that maximizes values[i] + values[j] + i - j
**Difficulty:** Medium

```kotlin
// Lookahead - maintain best "left" value as we go right
for (i in 1 until n) {
    // Current position is 'j', check all previous 'i'
    maxScore = max(maxScore, maxLeft + values[i] - i)
    maxLeft = max(maxLeft, values[i] + i)  // Update for future j
}
```

**Why lookahead:** Building up best value for future comparisons.

---

### ⭐ LC 11: Container With Most Water
**Pattern:** Two lines, find max water container
**Difficulty:** Medium

```kotlin
// Two pointers (special case of lookahead/lookback)
var left = 0
var right = n - 1
while (left < right) {
    area = min(height[left], height[right]) * (right - left)
    maxArea = max(maxArea, area)

    // Move the shorter line (looking for better ahead)
    if (height[left] < height[right]) left++
    else right--
}
```

**Why lookahead:** Moving pointers forward looking for better solution.

---

## Category 3: Lookback More Natural

These problems involve **building from previous results** or **DP-style comparisons**.

### ⭐ LC 53: Maximum Subarray (Kadane's Algorithm)
**Pattern:** Find contiguous subarray with largest sum
**Difficulty:** Medium

```kotlin
// Lookback is natural - "Should I extend previous or start fresh?"
for (i in 1 until n) {
    // Compare current element with previous subarray sum
    nums[i] = max(nums[i], nums[i] + nums[i - 1])
    maxSum = max(maxSum, nums[i])
}
```

**Why lookback:** Building from previous subarray result. Decision is: "extend previous or start new?"

---

### ⭐ LC 198: House Robber
**Pattern:** Max sum of non-adjacent elements
**Difficulty:** Medium

```kotlin
// Lookback - DP building from previous results
for (i in 1 until n) {
    dp[i] = max(
        dp[i - 1],              // Don't rob this house
        nums[i] + dp[i - 2]     // Rob this house (look back 2 steps)
    )
}
```

**Why lookback:** Classic DP - current state depends on previous states.

---

### ⭐ LC 300: Longest Increasing Subsequence
**Pattern:** Find longest strictly increasing subsequence
**Difficulty:** Medium

```kotlin
// Lookback - compare with all previous elements
for (i in 1 until n) {
    for (j in 0 until i) {  // Look back at all previous
        if (nums[j] < nums[i]) {
            dp[i] = max(dp[i], dp[j] + 1)
        }
    }
}
```

**Why lookback:** Current element extends previous subsequences.

---

### ⭐ LC 746: Min Cost Climbing Stairs
**Pattern:** Minimum cost to reach top
**Difficulty:** Easy

```kotlin
// Lookback - came from previous step or two steps ago
for (i in 2..n) {
    dp[i] = min(
        dp[i - 1] + cost[i - 1],
        dp[i - 2] + cost[i - 2]
    )
}
```

**Why lookback:** Building up solution from previous steps.

---

### ⭐ LC 121: Best Time to Buy and Sell Stock
**Pattern:** Find max profit from one buy-sell transaction
**Difficulty:** Easy

```kotlin
// Lookback - "What was the min price seen so far?"
for (i in 1 until n) {
    minPrice = min(minPrice, prices[i - 1])
    maxProfit = max(maxProfit, prices[i] - minPrice)
}
```

**Why lookback:** Current profit depends on minimum seen before.

---

### ⭐ LC 152: Maximum Product Subarray
**Pattern:** Find contiguous subarray with largest product
**Difficulty:** Medium

```kotlin
// Lookback - track both max and min (negative numbers flip)
for (i in 1 until n) {
    if (nums[i] < 0) {
        // Swap max and min (negative flips sign)
        swap(maxProduct, minProduct)
    }

    maxProduct = max(nums[i], maxProduct * nums[i])
    minProduct = min(nums[i], minProduct * nums[i])

    result = max(result, maxProduct)
}
```

**Why lookback:** Extending previous product results.

---

## Category 4: Special Patterns

### ⭐ LC 1911: Maximum Alternating Subsequence Sum
**Pattern:** Alternate adding and subtracting elements
**Difficulty:** Medium

```kotlin
// Lookback DP approach
for (i in 1 until n) {
    // Even index: add element
    even = max(even, odd + nums[i])
    // Odd index: subtract element
    odd = max(odd, even - nums[i])
}
```

**Why lookback:** State transition from previous decisions.

---

### ⭐ LC 1567: Maximum Length of Subarray With Positive Product
**Pattern:** Find longest subarray with positive product
**Difficulty:** Medium

```kotlin
// Track positive and negative lengths
for (i in 1 until n) {
    if (nums[i] > 0) {
        positive++
        negative = if (negative > 0) negative + 1 else 0
    } else if (nums[i] < 0) {
        val temp = positive
        positive = if (negative > 0) negative + 1 else 0
        negative = temp + 1
    } else {
        positive = 0
        negative = 0
    }
}
```

**Can use both:** Tracking running lengths forward or building from previous.

---

## Summary Table

| Problem | Difficulty | Pattern | Lookahead | Lookback |
|---------|-----------|---------|-----------|----------|
| LC 978 Turbulent | Medium | Edge alternation | ✅ Equal | ✅ Equal |
| LC 896 Monotonic | Easy | Edge direction | ✅ Equal | ✅ Equal |
| LC 845 Mountain | Medium | Up then down | ✅ Equal | ✅ Equal |
| LC 376 Wiggle | Medium | Alternating | ✅ Equal | ✅ Equal |
| LC 55 Jump Game | Medium | Planning ahead | ✅ Natural | ⚠️ Works |
| LC 45 Jump Game II | Medium | BFS levels | ✅ Natural | ⚠️ Works |
| LC 134 Gas Station | Medium | Can reach next | ✅ Natural | ⚠️ Works |
| LC 53 Max Subarray | Medium | Kadane's | ⚠️ Works | ✅ Natural |
| LC 198 House Robber | Medium | DP | ⚠️ Works | ✅ Natural |
| LC 300 LIS | Medium | DP | ⚠️ Works | ✅ Natural |
| LC 746 Stairs | Easy | DP | ⚠️ Works | ✅ Natural |
| LC 121 Stock | Easy | Track min | ⚠️ Works | ✅ Natural |
| LC 152 Max Product | Medium | Track max/min | ⚠️ Works | ✅ Natural |

---

## Practice Recommendation

### Phase 1: Master Edge-Based (Both Work)
Start with these to internalize that both approaches are equivalent:
1. LC 896 (Monotonic Array) - Easy
2. LC 978 (Turbulent Subarray) - Medium
3. LC 845 (Mountain) - Medium

### Phase 2: Lookahead Practice
Then practice forward-thinking problems:
1. LC 55 (Jump Game) - Medium
2. LC 134 (Gas Station) - Medium

### Phase 3: Lookback Practice
Finally, DP-style problems:
1. LC 53 (Max Subarray) - Medium
2. LC 121 (Stock) - Easy
3. LC 746 (Stairs) - Easy

### Phase 4: Advanced
1. LC 152 (Max Product) - Medium
2. LC 300 (LIS) - Medium
3. LC 1911 (Alternating Sum) - Medium

---

## Key Takeaway

**For edge-based problems:** Both lookahead and lookback work equally well. Pick one and stick with it!

**For DP problems:** Lookback is usually more natural because you're building from previous states.

**For planning/extending problems:** Lookahead is usually more natural because you're thinking forward.

But remember: **You can solve ANY of these problems with either approach!** The "more natural" designation is just about clarity, not correctness.
