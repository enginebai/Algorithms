package com.enginebai.leetcode.easy

object Solution121 {
    fun maxProfitWithBruteForce(prices: IntArray): Int {
        var maxProfit = 0
        for (i in 0..prices.size - 2) {
            for (j in i + 1 until prices.size) {
                if (prices[j] - prices[i] > maxProfit) {
                    maxProfit = prices[j] - prices[i]
                }
            }
        }
        return maxProfit
    }

    fun maxProfit(prices: IntArray): Int {
        var minPrice = Integer.MAX_VALUE
        var maxProfit = 0
        for (i in 0 until prices.size) {
            if (prices[i] < minPrice) {
                minPrice = prices[i]
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice
            }
        }
        return maxProfit
    }
}

fun main() {
    val input1 = intArrayOf(7, 1, 5, 3, 6, 4)
    val input2 = intArrayOf(7, 6, 4, 3, 1)
    println(Solution121.maxProfit(input1))
    println(Solution121.maxProfit(input2))
}