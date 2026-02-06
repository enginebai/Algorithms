fun merge(t1: TreeNode?, t2: TreeNode?): TreeNode? {

    if (t1 == null) return t2
    if (t2 == null) return t1

    mergedRoot = TreeNode(t2.`val`)

    t2ChildrenMap = HashMap<TreeNode, TreeNode>()
    for (child in t2.children) {
        t2ChildrenMap[child] = child
    }

    for (child in t1.children) {
        if (child in t2ChildrenMazp) {
            t2Child = t2ChildrenMap[child]
            mergedRoot.children.add(merge(child, t2Child))
            mergedT2Children.add(t2Child)
        } else {
            mergeRoot.children.add(child)
        }
    }

    for (child in t2.children) {
        if (child !in mergedT2Children) {
            mergedRoot.children.add(child)
        }
    }
    return mergedRoot
}

fun treeIsland(root: TreeNode?): Int {
    if (root == null) return 0
    return topDown(root, false)
}

fun topDown(root: TreeNode?, meet1: Boolean): Int {
    if (root == null) return 0
    val one = root.`val` == 1
    val count = if (one && meet1 == false) 1 else 0

    count += topDown(root.left, one)
    count += topDown(root.right, one)
    return count
}

var count = 0
fun treeIsland(root: TreeNode?): Int {
    if (root == null) return 0
    bottomUp(root, false)
    return topDown(root, false)
}

fun bottomUp(root: TreeNode?, parentIsOne: Boolean): Boolean {
    if (root == null) return false

    val rootIsOne = root.`val` == 1
    val leftIsOne = bottom(root.left, rootIsOne)
    val rightIsOne = bottom(root.right, rootIsOne)

    if (rootIsOne && !parentIsOne) count++
    return rootIsOne
}   

fun bottomUp(root: TreeNode?): Pair<Boolean, Int> {
    if (root == null) return false

    val rootIsOne = root.`val` == 1
    val (leftIsOne, leftCount) = bottom(root.left, rootIsOne)
    val (rightIsOne, rightCount) = bottom(root.right, rootIsOne)

    val count = if (rootIsOne) {
        if (leftIsOne || rightIsOne) {

        } else {
            1
        }
    } else {
        leftCount + rightCount
    }
    return rootIsOne to count
}   
bottomUp(root, false).second

/**
   1     1     1
  / \   / \   / \
 1  1  1  0  0  0
if root == 1, see if parent == 1?
    Yes, return true
    No, ans++, return true

if root == 0,
 */

fun calculateIslandSizes(root: TreeNode?): List<Int> {
    val sizes = mutableListOf<Int>()
    if (root == null) return sizes
    val rootSize = bottomUp(root, sizes)
    if (root.value == 1 && rootSize > 0) sizes.add(rootSize)
    return sizes
}

// Return 1. Child is 1? 2. Current accumulated size
private fun bottomUp(root: TreeNode?, sizes: MutableList<Int>): Int {
    if (root == null) return 0
    
    val (leftSize) = bottomUp(root.left, sizes)
    val (rightSize) = bottomUp(root.right, sizes)

    if (root.value == 0) {
        if (leftSize > 0) sizes.add(leftSize)
        if (rightSize > 0) sizes.add(rightSize)
        return 0
    } else {
        return (leftSize + rightSize + 1)
    }
}

/**
Operator(operand1, operand2)
"ADD(SUB(3,1),MUL(3,2))"

eval("ADD(SUB(3,1),MUL(3,2))")
    = ADD(
          eval("SUB(3,1)"),

base case: operand is a number (consists of digits)
recursive: 
    eval(operand1)
    eval(operand2)
    return operator(operand1, operand2)
 */
private val operations: Map<String, (Double, Double) -> Double> = mapOf(
        "add" to ::add,
        "sub" to ::sub,
        "mul" to ::mul,
        "div" to ::div,
        "pow" to ::pow
    )

fun eval(expression: String): Double {
    if (expression[i].isDigit()) {
    } else {
        val opBuilder = StringBuilder()
        repeat(3) {
            opBuilder.append(expression[i++])
        }
        val operatorStr = opBuilder.toString()
        i++ // Skip `(`
        val result1 = eval(expression)
        i++ // Skip `,`
        val result2 = eval(expression)
        i++ // Skip `)`
        return 
    }
}

/**
 * Expression:
 * 
 * 1. First character:
 *  - Variable
 *  - Negative sign (Not operator)
 *  - Left parenthesis
 * 
 * 2. After variable:
 *  - End of expression. (if it's not inside the parenthesis)
 *  - Operator
 *  - Right parenthesis
 * 
 * 3. After operator:
 *  - Variable
 *  - Left parenthesis
 * 
 * 4. After left parenthesis:
 *  - Negative sign
 *  - Variable
 *  - Left parenthesis
 * 
 * 5. After right parenthesis:
 *  - End of expression
 *  - Operator
 *  - Right parthesis
 */

// Only single letter (variable), +, -, (, ) only.
// One-level parenthesis only
// a + (-b + c)
             c
context = 1
local = 1
a: 1
b:-1
c: 1
// -a - (b - c) = -a-b+c
              c
context = 1
local = 1
a: -1
b: -1
c: 1
// -(-a + c) + (-a - c) =-2c
                   c
context = 1
local = -1

a: 0
c: -2
fun simplifyExpressionOneLevel(expression: String): String {
    var contextSign = 1
    var localSign = 1

    val coefficients = IntArray(26)
    for (c in expression) {
        when {
            c.isLetter() -> {
                val index = c - 'a'
                coefficients[index] += contextSign * localSign
            }
            c == '+' -> {
                localSign = 1
            }
            c == '-' -> {
                localSign = -1
            }
            c == '(' -> {
                contextSign = localSign
                localSign = 1
            }
            c == ')' -> {
                contextSign = 1
                localSign = 1
            }
        }
    }
    return output(coefficients)
}

fun simplifyExpressionNested(expression: String): String {
    val contextStack = ArrayDeque<Int>()
    var localSign = 1
    val coefficients = IntArray(26)
    contextStack.addLast(1)
    for (c in expression) {
        when {
            c.isLetter() -> {
                val index = c - 'a'
                val finalSign = contextStack.last() * localSign
                coefficients[index] += finalSign
            }
            c == '+' -> {
                localSign = 1
            }
            c == '-' -> {
                localSign = -1
            }
            c == '(' -> {
                // A new context begins
                val cumulativeSign = contextStack.last() * localSign 
                contextStack.addLast(cumulativeSign)
                localSign = 1 // Reset and use it for the nested context
            }
            c == ')' -> {
                // End of the parenthesis context
                contextStack.removeLast()
            }
            c == ' ' -> { /* Ignore spaces */ }
        }
    }
    return output(coefficients)
}

/**
 * a: +1, b: -1, c: +1, d: +1
 * f("-(-a+(b-c)-d)", ++)
 *      c
 * local = -
 *      f("(-a+(b-c)-d)", --)
 *                   c
 *      local = -
 *      
 *            f("(b-c)-d)", --)
 *                   c
 *            local = -
 */
private var i = 0
fun simplifyExpressionRecursive(expression: String): String {
    i = 0
    val coefficients = IntArray(26)
    simplify(expression, 1, coefficients)
    return output(coefficients)
}

// Handle a single +/-(...)
fun simplify(expression: String, contextSign: Int, coefficients: IntArray) {
    var localSign = 1
    while (i < expression.length) {
        val c = expression[i]
        when {
            c.isLetter() -> {
                val index = c - 'a'
                val finalSign = contextSign * localSign
                coefficients[index] += finalSign
                i++
            }
            c == '+' -> {
                localSign = 1
                i++
            }
            c == '-' -> {
                localSign = -1
                i++
            }
            c == '(' -> {
                i++ // Skip `(`
                simplify(expression, contextSign * localSign, coefficients)
                localSign = 1
            }
            c == ')' -> {
                i++ // Skip
                return
            }
            c == ' ' -> i++
        }
    } 
}

private fun output(coefficients: IntArray): String {
    val output = StringBuilder()
    for (i in 0 until 26) {
        val c = coefficients[i]
        var variable = 'a' + i
        if (c != 0) { // X, 1, 0, -1, -X, where X > 1

            // First: kX, X, -X, -kX
            // Non-first: +kX, +X, -X, -kX
            when {
                c == 1 -> {
                    if (output.isNotEmpty()) {
                        output.append("+")
                    }
                    output.append(variable)
                }
                c == -1 -> output.append("-").append(variable)
                else -> {
                    if (output.isEmpty()) { // First term: kX or -kX
                        output.append(c).append(variable)
                    } else {
                        // Non-first: +kX or -kX
                        if (c > 0) output.append("+")
                        output.append(c).append(variable)
                    }
                }
            }
        }
    }
    return if (output.isEmpty()) "0" else output.toString()
}

private fun output(coefficients: IntArray): String {
    val output = StringBuilder()
    for (i in 0 until 26) {
        val c = coefficients[i]
        if (c == 0) continue

        var variable = 'a' + i

        // 1. Sign
        if (c > 0) {
            if (output.isNotEmpty) {
                output.append("+")
            }
        } else {
            output.append("-")
        }

        // 2. Manitude
        val magnitude = abs(c)
        if (magnitude != 1) {
            output.append(magnitude)
        }

        // 3. Variable
        output.append(variable)

    return output.toString()
}


class Sequence(private val initial: Long) {
    private val added = HashSet<Long>()
    private val removed = TreeSet<Long>()
    private var smallest = initial

    fun add(num: Long) {
        if (num < initial || num in added) return
        added.add(num)
        removed.remove(num)
        while (smallest in added) smallest++
    }

    fun remove(num: Long) {
        // Check `num >= smallest`?
        if (num < initial || num !in added || num in removed) return
        removed.add(num)
        added.remove(num)
    }

    fun get(): Long {
        return if (removed.isNotEmpty()) minOf(removed.first(), smallest) else smallest
    }
}

class Sequence(private val initial: Long) {
    private val added = HashSet<Long>()
    private val minHeap = PriorityQueue<Long>()
    private val removed = HashSet<Long>()
    private var smallest = initial

    fun add(num: Long) {
        if (num < initial || num in added) return
        added.add(num)
        removed.remove(num)
        while (smallest in added) smallest++
    }

    fun remove(num: Long) {
        if (num < initial || num !in added || num in removed) return
        removed.add(num)
        minHeap.add(num)
        added.remove(num)
    }

    fun get(): Long {
        while (minHeap.isNotEmpty() && minHeap.peek() !in removed) minHeap.poll()
        return if (minHeap.isNotEmpty()) minOf(minHeap.peek(), smallest) else smallest
    }
}

class DutyInterval {
    data class OnCallSchedule(
        val name: String,
        val start: Int,
        val end: Int 
    )

    data class OnCallEvent(
        val time: Int,
        val isStart: Boolean,
        val schedule: OnCallSchedule
    )

    data class DutyOutput(
        val start: Int,
        val end: Int,
        val waiters: HashSet<String>
    )

    fun findDutyIntervals(duties: Array<OnCallSchedule>): List<DutyOutput> {
        val events = mutableListOf<OnCallEvent>()
        for (duty in duties) {
            events.add(OnCallEvent(
                time = duty.start,
                isStart = true,
                schedule = duty
            )
            events.add(OnCallEvent(
                time = duty.end,
                isStart = false,
                schedule = duty
            )
        }
        events.sort
    }
}

/**
 * A[i] == B[j]: i++, j++, reset `different` flag.
 * A[i] != B[j]: j++, set `different = true`, check if it's the first time to have different
 */
fun isOneInsertion(str1: String, str2: String): Boolean {
    val s1 = str1.split(" ")
    val s2 = str2.split(" ")
    
    if (s2.size > s1.size) return isOneInsertion(str2, str1)
    // Now we have s1.length <= s2.length
    val m = s1.length
    val n = s2.length
    var i = 0
    var j = 0
    var different = false
    var diffCount = 0
    while (i < m || j < n) {
        if (i == m || j == n) return i == m
        
        if (s1[i] == s2[j]) {
            i++ 
            j++
            different = false
        } else {
            if (different == false) {
                if (diffCount > 1) return false
                diffCount++
                different = true
            }
            j++
        }
    }
    return i == m
}

fun isOneInsertion(str1: String, str2: String): Boolean {
    val s1 = str1.split(" ")
    val s2 = str2.split(" ")
    
    if (s1.size > s2.size) return isOneInsertion(str2, str1)

    // 0, 1, 2, 3, 4
    // ^^^^  ^^^^^^^
    //    j  i
    val prefix = checkPrefix(s1, s2)
    val suffix = checkSuffix(s1, s2)

    return suffix < prefix
}

// A, B, C, X, Y
            i
      j
// A, B, C, I, J, K, C, X, Y
private fun checkPrefix(s1: Array<String>, s2: Array<String>): Int {
    val m = s1.length
    val n = s2.length
    var i = 0
    var j = 0
    while (i < m && j < n && s1[i] == s2[j]) {
        i++
        j++
    }
    return i
}

private fun checkSuffix(s1: Array<String>, s2: Array<String>): Int {
    val m = s1.length
    val n = s2.length
    var i = m - 1
    var j = n - 1
    while (i >= 0 && j >= 0 && s1[i] == s2[j]) {
        i--
        j--
    }
    return i
}

/**
 * n = 5
 * 0, 1, 2, 3, 4
 *    i
 * 
 * n - 1 - i
 * 
 * x - 1 + y - 1 == m
 */

fun countArithmeticSubarrays(nums: IntArray): Long {
    val n = nums.size
    var i = 0
    var diff: Int? = null

    while (i < n) {
        // Skip the invalid cases: diff != +1 or -1
        // 2, 2
        // 1, 5
        // 5, 1
        while (i + 1 < n && (nums[i] + 1 != nums[i + 1] || nums[i] - 1 != nums[i + 1]) i++

        // First element (at the beginning or the group): Not knowing if the diff is +1 or -1
        // [1, 2, 2, 1, 0]
        //  i     i
        // 1, 2
        // 2, 1

        // [1, 2, 3, 2, 1, 0]
        // 1, 1, 2, 2, 2
        //  s     e
        if (i + 1 < n) {
            val diff = nums[i + 1] - nums[i]
            val start = i
            // Else: We know the current +1 or -1, loop the current group of the same diff.
            while (i + 1 < n && nums[i + 1] - nums[i] == diff) {
                i++
            }
            val end = i

            // Update the answer

            // We don't have to advance `i` since `i` stops at the last valid position, we should use it as a new start of next group

        } else {
            break
        }
    }
}

/**
 * Given an array of meeting time intervals, find the minimum number of conference rooms required. Each time interval is defined by two elements, representing the start and end time of a meeting. Return the minimum number of conference rooms required.
 * 
 * Clarifications:
 * 1. Inclusive - exclusive
 * 2. Input: start < end
 * 3. interval size <= 10^4, time range: non-negative, fits 32-bit integer.
 * 
 * Approaches:
 * 1. Line sweep (by difference array), TC: O(N + R), SC: O(R)
 * 2. Line sweep (by TreeMap), TC: O(N log N), SC: O(N)
 * 3. Greedy + min heap, TC: O(N log N), SC: O(N)
 */
fun meetingRooms(nums: Array<IntArray>): Int {
    val n = nums.size
    val min = nums.minOf { it[0] }
    val max = nums.maxOf { it[1] }
    // min .. max + 1
    val diff = IntArray(max + 1 - min + 1) 
    for (num in nums) {
        val (start, end) = num
        diff[start - min]++
        diff[end - min]--
    }
    var value = 0
    var answer = 0
    for (v in diff) {
        value += v
        answer = maxOf(answer, value)
    }
    return answer
}

fun meetingRooms(nums: Array<IntArray>): Int {
    val treeMap = TreeMap<Int, Int>()
    for (num in nums) {
        val (start, end) = num
        treeMap[start] = (treeMap[start] ?: 0) + 1
        treeMap[end] = (treeMap[end] ?: 0) - 1
    }
    var value = 0
    var answer = 0
    for (v in treeMap.values) {
        value += v
        answer = maxOf(answer, value)
    }
    return answer
}

/**
 * 1, 2, 3, 4, 5, 6
 * |-----|
 *    |--|
 *    |-----|
 *             |--|
 *               i
 * 
 * heap = [3, 4, 6]
 */
fun meetingRooms(nums: Array<IntArray>): Int {
    val minHeap = PriorityQueue<Int>() // Keep track of `end` time of current running meetings.
    nums.sortBy { it[0] } // sort by `start` time

    // Iterate the intervals in chronological order
    for (num in nums) {
        val (start, end) = num
        if (minHeap.isNotEmpty()) {
            val minEnd = minHeap.peek()
            if (minEnd <= start) { // We can reuse the earliest-end meeting room.
                minHeap.poll()
            }
            minHeap.add(end)  
        } else {
            minHeap.add(end)
        }
    }
    return minHeap.size
}

fun hasCycle(graph: Array<List<Int>>): Boolean {
    val n = graph.size
    val visited = BooleanArray(n)
    for (i in 0 until n) {
        if (!visited[i]) {
            if (dfs(graph, i, visited)) return true
        }
    }
    return false
}

fun dfs(graph: Array<List<List>>, i: Int, parent: Int, visited: BooleanArray): Boolean {
    if (visited[i] == true) return true
    visited[i] = true
    for (adj in graph[i]) {
        // Case A: It's the path we just came from. Ignore.
        if (adj == parent) continue
        // Case B: We've visited before and it's not a turn-around, it's cycle.
        if (visited[adj]) return true
        // Case C: Visit the neighbor.
        if (dfs(graph, adj, i, visited)) return true
    }
    return false
}

/**
 * Convert [x, x, x, y, y, y, y] into [(x: 3), (y: 4)]
 */
fun compress(a: IntArray): List<Pair<Int, Int>> {
    var i = 0
    val vector = mutableListOf<Pair<Int, Int>>()
    while (i < a.size) {
        val start = i
        i++
        while (i < a.size && a[i] == a[start]) {
            i++
        }
        val count = i - start
        vector.add(a[start] to count)
    }
    return vector
}

fun product(a: IntArray, b: IntArray): Long {
    val vectorA = compress(a)
    val vectorB = compress(b)
    val m = vectorA.size
    val n = vectorB.size

    if (m == 0 || n == 0) return 0L

    var i = 0
    var j = 0
    var countA = vectorA[i].second
    var countB = vectorB[j].second
    var product = 0L
    while (i < m && j < n) {
        val minCount = minOf(countA, countB)
        product += minCount.toLong() * (vectorA[i].first * vectorB[j].first)
        countA -= minCount
        if (countA == 0) {
            i++
            if (i < m) countA = vectorA[i].second
        }
        countB -= minCount
        if (countB == 0) {
            j++
            if (j < n) countB = vectorB[j].second
        }
    }
}

/**
(2: 9) 
         i
(3: 2), (4: 3), (5: 4)
                        j
countA = 0
countB = 0
minCount = 4
product = 2 * (2 * 3) + 3 * (2 * 4) + 4 * (2 * 5)
 */

fun product(a: IntArray, b: IntArray): Long {
    val vectorA = compress(a)
    val vectorB = compress(b)
    val m = vectorA.size
    val n = vectorB.size

    var i = 0
    var j = 0

    var remainingA: Pair<Int, Int>? = null
    var remainingB: Pair<Int, Int>? = null

    var product = 0L
    while (i < m || j < n) {

        var item1: Pair<Int, Int>? = null
        var item2: Pair<Int, Int>? = null

        if (remainingA != null && j < n) {
            item1 = remainingA
            item2 = vectorB[j]
        } else if (remainingB != null && i < m) {
            item1 = vectorA[i]
            item2 = remainingB
        } else if (i < m && j < n) {
            item1 = vectorA[i]
            item2 = vectorB[j]
        }
        if (item1 != null && item2 != null) {
            if (item1.second == item2.second) {
                product += (item1.first.toLong() * item2.first) * item1.second
                i++
                j++
                remainingA = null
                remainingB = null
            } else {
                val minCount = minOf(item1.second, item2.second)
                product += (item1.first.toLong() * item2.first) * minCount
                if (item1.second > item2.second) {
                    remainingA = item1.first to (item1.second - minCount)
                    j++
                    remainingB = null
                } else {
                    remainingB = item2.first to (item2.second - minCount)
                    i++
                    remainingA = null
                }
            }
        } else {
            throw Exception()
        }
    }
    return product
}

fun findMaxConsecutiveOnes(nums: IntArray): Int {
    var count = 0
    var ans = 0
    for (i in nums.indices) {
        if (nums[i] == 1) {
            count++
        } else {
            count = 0
        }
        ans = maxOf(ans, count)
    }
    return ans
}

/**
[_, _, i, _, _, j, _]
                ^
 0  1  2              
[2, 1, 3]
    i     
[0  0  2]
 */
fun getMaximumScores(nums: IntArray): Long {
    var scores = 0L
    val n = nums.size
    val dp = LongArray(n)
    for (i in nums.indices) {
        // Take it
        val takeIt = dp[i] + arr[i]
        val nextIndex = i + arr[i]
        if (nextIndex < n) dp[nextIndex] += takeIt
        if (i + 1 < n) dp[i + 1] = maxOf(dp[i + 1], dp[i])
        maxScores = maxOf(maxScores, takeIt)
    }
    return scrores
}
