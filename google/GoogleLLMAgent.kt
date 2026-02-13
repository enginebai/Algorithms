fun getMaxCustomers(amounts: IntArray, initialAmount: Int): Int {
    var left = 0
    var maxCustomers = 0
    var balance = 0
    for (right in amounts.indices) {
        balance += amounts[right]
        while (balance < 0 && left <= right) {
            balance -= amounts[left]
            left++
        }
        maxCustomers = maxOf(maxCustomers, right - left + 1)
    }
    return maxCustomers
}

/**
 HH:MM
 00:00 ~ 23:59

 23:?5, 22:35
 2?:?5, 22:35
    21:55, 22:05, 22:35
 */
fun getClosestHours(givenTime: String, referenceTime: String): String {
    // generate all time, find the closest one.
    val refTotalMinutes = referenceTime.toTotalMinutes()

    var timeDiff = 24 * 60
    var closestHours = ""

    for (h in 0 until 24) {
        for (m in 0 until 60) {
            val totalMinutes = h * 60 + m
            val timeFormat = totalMinutes.toTimeFormat()
            if (match(givenTime, timeFormat) {
                val currentDiff = abs(totalMinutes - refTotalMinutes)
                if (currentDiff < refTotalMinutes) {
                    refTotalMinutes = currentDiff
                    closestHours = timeFormat
                }
            }
        }
    }
    return closestHours
}

/**
 A?:??
 AB:CD
 */
private fun match(givenTime: String, time: String): Boolean {
    for (i in givenTime.indices) {
        if (givenTime[i] == '?' || givenTime[i] == ':') continue
        if (givenTime[i] != time[i]) return false
    }
    return true
}

private fun Int.toTimeFormat(): String {
    val hours = this / 60
    val minutes = this % 60
    return String.format("%02d:%02d", hours, minutes)
}

private fun String.toTotalMinutes(): Int {
    val splits = this.splits(":")
    val hours = splits[0].toInt()
    val minutes = splits[1].toInt()
    return hours * 60 + minutes
}

fun getCommonAvailableDays(blocks: List<Block>, query: IntArray): List<Int> {
    val L = query[0]
    val R = query[1]

    val min = blocks.minOf { it.start }
    val max = blocks.maxOf { it.end }
    var diffArray = IntArray(max + 1 - min + 1)
    for ((start, end) in blocks) {
        diffArray[start - min]++
        diffArray[end + 1 - min]--
    }

    val availableDays = mutableListOf<Int>()

    // handle query before `min`
    if (L < min) {
        val right = minOf(R, min - 1)
        for (i in L..right) availableDays.add(i)
    }

    // if the entire query is at left of min..max + 1, we finish processing
    if (R < min) return availableDays

    var value = 0
    for (d in diffArray.indices) {
        val day = d + min
        value += diffArray[d]
        if (value == 0) {
            availableDays.add(day)
        }
    }

    // handle query after `max + 1`
    if (max + 1 < R) {
        val left = maxOf(max + 1, L)
        for (i in left..R) availableDays.add(i)
    }
    return availableDays
}

fun getCommonAvailableDays(blocks: List<Block>, query: IntArray): List<Int> {
    val L = query[0]
    val R = query[1]

    val diffTreeMap = TreeMap<Int, Int>()
    for ((start, end) in blocks) {
        diffTreeMap[start] = (diffTreeMap[start] ?: 0) + 1
        diffTreeMap[end + 1] = (diffTreeMap[end + 1] ?: 0) - 1
    }

    var prefixSum = 0
    val prefixSumMap = TreeMap<Int, Int>()
    for ((key, value) in diffTreeMap) {
        prefixSum += value
        prefixSumMap[key] = prefixSum
    }

    val availableDays = mutableListOf<Int>()
    for (q in L..R) {
        val unavailableCount = prefixSumMap.floorEntry(q)?.value ?: 0
        if (unavailableCount == 0) availableDays.add(d)
    }
    return availableDays
}

// Push mode, index `i` push the scores
// 1. to index `i + arr[i]` (take)
// 2. to index `i + 1` (skip)
fun getMaxScores(arr: IntArray): Long {
    var maxScores = 0L
    val dp = LongArray(arr.size)
    for (i in arr.indices) {
        // take 
        val takeIndex = i + arr[i]
        val take = dp[i] + arr[i]
        if (takeIndex < n) dp[takeIndex] = maxOf(dp[takeIndex], take)
        
        // skip
        val skipIndex + i + 1
        val skip = dp[i]
        if (skipIndex < n) dp[skipIndex] = maxOf(dp[skipIndex], skip)

        maxScores = maxOf(take, skip)
    }
    return maxScores
}

/**
from(i) = maxOf(arr[i] + from(i + arr[i]), from(i + 1))
 */
private var maxScores = 0L 
fun from(arr: IntArray, i: Int, memo: LongArray): Long {
    if (i >= arr.size) return 0L
    if (memo[i] != -1) return memo[i]

    val take = arr[i] + from(arr, i + arr[i], memo),
    val skip = from(i + 1)
    memo[i] = maxOf(take, skip)
    maxScores = maxOf(maxScores, memo[i])
    return memo
}

fun bottomUp(arr: IntArray): Long {
    val n = arr.size
    val dp = LongArray(n)
    var maxScores = 0L
    for (i in n - 1 downTo 0) {
        dp[i] = maxOf(
            dp[i + arr[i] + arr[i],
            dp[i + 1]
        )
        maxScores = maxOf(maxScores, dp[i])
    }
    return maxScores
}