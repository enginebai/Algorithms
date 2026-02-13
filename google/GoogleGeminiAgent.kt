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

fun replace(s: String, visited: HashSet<String>): String {
    for (c in s) {
        if (c == '%') {
            if (starting) {
                if (variable.isEmpty) throw Exception(variable is empty)
                if (variable !in dict) throw Exception(No key)
                if (variable in visited) throw Exception(cycle)

                if (variable !in memo) {
                    visited.add(variable)
                    resolvedValue = replace(dict[variable], visited)
                    memo[variable] = resolvedValue
                    visited.remove(variable)
                }
                output.append(memo[variable]!!)
                starting = false
                variable.clear()
            } else {
                starting = true
            }
        } else {
            if (starting) variable.append(c)
            else output.append(c)
        }
    }
    if (starting) throw Exception(Unmatched %)
    return output
}

dict = {XYZ: "123 %HELLO%", HELLO: "WORLD %XYZ%"}
replace('ABC, %XYZ%, X, X, %HELLO%', {})
              ^^^^^
    XYZ = replace('123 %HELLO%'', {XYZ})
                       ^^^^^^^
        HELLO = replace('WORLD %XYZ%', {XYZ, HELLO})
                               ^^^^^

fun bfsStandard(startNode: Int): IntArray {
    val distances = IntArray(n) { Int.MAX_VALUE }
    val queue = ArrayDeque<Int>()
    queue.addLast(startNode)
    distances[startNode] = 0
    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()

        graph[node].forEach { adj ->
            if (distances[adj] == Int.MAX_VALUE) {
                distances[adj] = distances[node] + 1
                queue.addLast(adj)
            }
        }
    }
    return distances
}

fun bfsLevelByLevel(startNode: Int): IntArray {
    val distances = IntArray(n) { Int.MAX_VALUE }
    val queue = ArrayDeque<Int>()
    val visited = BooleanArray(n)
    queue.addLast(startNode)
    visited[startNode] = true

    var distance = 0
    while (queue.isNotEmpty()) {
        val size = queue.size
        repeat (size) {
            val node = queue.removeFirst()
            distances[node] = distance
            for (adj in graph[node]) {
                if (visited[adj]) continue

                visited[adj] = true
                queue.addLast(adj)
            }
        }
        distance++
    }
    return distances
}

fun bfsRelaxation(startNode: Int): IntArray {
    val distances = IntArray(n) { Int.MAX_VALUE }
    val queue = ArrayDeque<Int>()
    queue.addLast(startNode)
    distances[startNode] = 0
    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        for (adj in adjList[node]) {
            if (distances[adj] > distances[node] + 1) {
                distances[adj] = distances[node] + 1
                queue.addLast(adj)
            }
        }
    }
    return distances
}

class IslandShape(private val grid: Array<IntArray>) {
    fun countDistinctIslands(): Int {
        val allVisited = HashSet<Cell>()
        val shapeSet = HashSet<String>()
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] !in allVisited) {
                    val island = HashSet<Cell>()
                    dfs(i, j, island)

                }
            }
        }
        return shapeSet
    }

    private fun normalizeIsland(island: HashSet<Cell>): String {
        val variants = Array<String>(8)
        for (i in variants.indices) {
            val newShape = HashSet<Cell>()
            for ((x, y) in island) {
                val (newX, newY) = when (i) {
                    0 -> x to y
                    1 -> (y, -x) // rotate 90
                    2 -> (-x, -y) // rotate 180
                    3 -> (-y, x) // rotate 270
                    4 -> (x, -y) // reflect horizontally
                    5 -> (-x, y) // reflect vertically
                    6 -> (-y, -x) // reflect y = -x
                    7 -> (y, x) // reflect y = x
                newShape.add(newX, newY)
            }
            newShap =
        }
        variants.sort()
        return variants.first()
    }
}

/**
(x, y)
(-1, 2)
       _   * (2, 1)
       |   
-|-|-|-|-|-|-|-
       |
   *   - (-2, -1)
       |
       _ * (1, -2)
 */

data class Node(
    val id: Long,
    val name: String, 
    val directories: List<Node>?
)

fun findSameNameAncestors(root: Node?, k: Int): List<String> {
    if (root == null) return emptyList()

    val answer = mutableListOf<String>()
    val map = HashMap<String, HashSet<Node>>()
    checkAncestors(root, map, answer)
    return answer
}

private fun checkAncestors(root: Node?, ancestorsMap: HashMap<String, HashSet<Node>>, answer: MutableList<String>, k: Int, window: ArrayQueue<Node>) {
    if (root == null) return
    if (root.name in ancestorsMap) {
        answer.add(root.id)
    }
    val ancestors = ancestorsMap.getOrPut(ancestors.name) { HashSet<Node> }
    ancestors.add(root)
    window.addLast(root)
    while (window.size > k) {
        val removed = window.removeFirst()
        ancestorsMap[removed.name]?.remove(removed)
        if (ancestorsMap[removed.name]?.isEmpty() == true) ancestorsMap.remove(removed.name)
    }
    for (dir in root.directories) {
        checkAncestors(dir, ancestorsMap, answer)
    }
    ancestors.remove(root)
    window.removeLast()
}

// -----------------------------------------------------------------------------------
min=14, max=oo, v1, 1
min = -oo, max = 8, v2, 2
min = 12, max = 16, v3, 3

events =         TreeSet (for line sweep) TreeMap (For query later)
(-oo, v2, 2, S)  {(v2, 2)}                {-oo: 2}
(8, v2, 2, E)    {}                       {-oo, 2, 9: null}
(12, v3, 3, S)   {(v3, 3)}                {-oo, 2, 9: null, 12: 3}
(14, v1, 1, S)   {(v3, 3)/(v1, 1)}        {-oo, 2, 9: null, 12: 3, 14: 3}
(16, v3, 3, E)   {(v1, 1)}                {-oo, 2, 9: null, 12: 3, 14: 3, 17: 1}  
(oo, v1, 1, E)   {}                       

RO = {}

OS = 3
OS = 11
OS = 14
OS = 7
OS = 20

// -----------------------------------------------------------------------------------
private var i = 0
fun eval(expression: String): Float {
    val n = expression.length
    val first = expression[0]
    if (first == '-' || first.isDigit()) {
        return parseNumber()
    } else {
        val functionName = expression.substring(i, i + 3)
        i += 3
        i++ // Skip '('

        val operand1 = eval(expression)
        i++ // Skip ','
        val operand2 = eval(expression)
        i++ // Skip ')'

        return operations[functionName]?.invoke(operand1, operand2) ?: 0.0f
    }
}

private val operations: Map<String, (Double, Double) -> Double> = mapOf(
    "ADD" to :add)

private fun parseNumber(): Float {
    var sign = 1
    if (i < n && expression[i] == '-') {
        sign = -1
        i++
    }
    var num = 0
    while (i < n && expression[i].isDigit()) {
        num = num * 10 + (expression[i] - '0')
        i++
    }
    if (i < n && expression[i] == '.') {
        i++
        var decimal = 1f
        while (i < 0 && expression[i].isDigit() {
            decimal /= 10f
            num += decimal * (expression[i] - '0')
            i++
        }
    }
    return sign * num
}

fun simplifyExpressionOneLevel(exp: String): String {
    var globalSign = 1
    var localSign = 1
    var letter = 'a'
    val coef = IntArray(26)
    for (c in exp) {
        if (c.isLetter()) {
            coef[c - 'a'] += globalSign * localSign
        } else if (c == '+') {
            localSign = 1 * globalSign
        } else if (c == '-') {
            localSign = -1 * globalSign
        } else if (c == '(') {
            globalSign = localSign
            localSign = 1
        } else if (c == ')') {
            globalSign = 1
            localSign = 1
        }
    }
    return output(coef)
}

fun simplifyExpression(exp: String): String {
    val globalSign = ArrayDeque<Int>()
    globalSign.addLast(1)
    val coef = IntArray(26)
    var localSign = 1
    for (c in exp) {
        when {
            c.isLetter -> {
                coef[c - 'a'] = globalSign.last() * localSign
            }
            c == '+' -> {
                localSign = 1
            }   
            c == '-' -> {
                localSign = -1
            }
            c == '(' -> {
                globalSign.addLast(globalSign.last() * localSign)
                localSign = 1
            }
            else -> {
                globalSign.removeLast()
                localSign = 1
            }
        }
    }
    return output(coef)
}



fun getShipDays(weights: IntArray, capacity: Int): Int {
    var day = 1
    val load = 0
    for (w in weights) {
        if (load + w > capacity) {
            day++
            load = 0
        }
        load += w
    }
    return day
}

/**
altitudes = [5, 4, 1, 6, 2]
                ^        ^
fountains = [1, 4]
 */

fun getFlooded(altitudes: IntArray, f: IntArray): BooleanArray {
    val n = altitudes.size
    val answers = BooleanArray(n)

    f.sort()
    for (i in altitudes.indices) {
        val a = altitudes[i]

        val prev = findPrev(a, f)
        val next = findNext(a, f)
        if (prev != -1 && next != -1) {
            val d1 = abs(i - prev)
            val d2 = abs(i - next)
            if (d1 == d2) {
                if (f[prev] >= a || f[next] >= a) answer[i] = true
            } else if (d1 < d2) {
                if (f[prev] >= a) answer[i] = true
            } else if (d1 > d2) {
                if (f[next] >= a) answer[i] = true
            }
        }
    }
    return answers
}

// Find the last position which f[i] <= a
private fun findPrev(a: Int, f: IntArray): Int {
    var left = 0
    var right = f.size - 1
    while (left <= right) {
        val middle = left + (right - left) / 2
        if (f[middle] <= a) {
            left = middle + 1
        } else {
            right = middle - 1
        }
    }
    return if (right in 0 until f.size) right else -1
}

// Find the first position which a <= f[i]
private fun findNext(a: Int, f: IntArray): Int {
    var left = 0
    var right = f.size - 1
    while (left <= right) {
        val middle = left + (right - left) / 2
        if (a <= f[middle]) {
            right = middle - 1
        } else {
            left = middle + 1
        }
    }
    return if (left in 0 until f.size) left else -1
}

fun getFlooded(altitudes: IntArray, f: IntArray): BooleanArray {
    val m = altitudes.size
    val n = f.size
    val answers = BooleanArray(m)

    altitudes.sort()
    f.sort()
    var j = 0
    for (i in altitudes.indices) {
        val a = altitudes[i]
        // Iterate to find the next closest one
        while (j < n && f[j] < a) {
            j++
        }

        if (j == 0) { // There is no previous one [a] [f1, f2, ...fn]
        } else if (j == n) { // There is no next one, [f1, f2, ...fn] [a]
        } else {
        }


    }
    return answers
}

fun groupByConsecutive(nums: IntArray) {
    val n = nums.size
    var i = 0
    var answer = 0
    while (i < n) {
        var start = i
        i++
        while (i < n && nums[i - 1] + 1 == nums[i]) {
            i++
        }
        answer = maxOf(ans, i - start)
    }
    return answer
}

/**
 k is odd, k = 3
    [-, - , ..., -, -]
    [-, -, ...., -, +]
 k is even, 
 */
fun maxKProduct(nums: IntArray, k: Int): Long {
    var maxProduct = 1L
    nums.sort()
    var left = 0
    var right = nums.lastIndex
    var kk = k

    /**
    [0, 1, 2, 3, 4], k = 2
     */
    if (k % 2 == 1) {
        if (nums.last() < 0) {
            // Find the less negative product
            for (i in nums.lastIndex downTo nums.lastIndex - k + 1) {
                maxProduct *= nums[i]
            }
            return maxProduct
        }

        if (nums[left] <= nums[right]) {
            maxProduct *= nums[right--]
        } else {
            maxProduct *= nums[left++]
        }
        kk--
    }
    while (kk > 0) {
        val leftPart = nums[left] * nums[left + 1]
        var rightPart = nums[right - 1] * nums[right]
        if (leftPart <= rightPart) {
            maxProduct *= rightPart
            right -= 2
        } else {
            maxProduct *= leftPart
            left += 2
        }
        kk -= 2
    }
    return maxProduct
}

/**
n = 6
0  1  2  3  4  5
O, O, O, _, _, _ i = 0 = p[2] * 1L
O, O, _, _, _, O i = 1 = p[1] * s[0]
O, _, _, _, O, O i = 2 = p[0] * s[1]
_, _, _, O, O, O i = 3 = 1L * s[2]
            <- i
         2, 1, 0
k = 3
 */
fun maxKProduct(nums: IntArray, k: Int): Long {
    val n = nums.size
    nums.sort()
    val prefix = LongArray(n)
    val suffix = LongArray(n)

    prefix[0] = nums[0]
    for (i in 1 until n) {
        prefix[i] = nums[i] * prefix[i - 1]
    }
    suffix[0] = nums[n - 1]
    for (i in 1 until n) {
        suffix[i] = nums[n - 1 - i] * suffix[i - 1]
    }

    var maxProduct = 1L
    for (i in 0..k) {
        val p = if (k - 1 - i >= 0) prefix[k - 1 - i] else 1L
        val s = if (i - 1 >= 0) suffix[i - 1] else 1L
        maxProduct = maxOf(maxProduct, p * s)
    }

    return maxProduct
}

fun isKInsertions(s1: String, s2: String, k: Int): Boolean {
    val split1 = s1.split(" ")
    val split2 = s2.split(" ")
    if (split2.size < split1.size) return isSingleInsertion(s2, s1)

    // Check if s2 is a insertion of s1
    var i = 0
    var j = 0
    while (j < split2.size) {
        if (i < split1.size && split1[i] == split2[j]) {
            i++
            j++
            inGap = false
        } else {
            if (!inGap) {
                inGap = true
                gaps++
            }
            j++
        }
    }
    return i == split1.size && gaps == k
}
    