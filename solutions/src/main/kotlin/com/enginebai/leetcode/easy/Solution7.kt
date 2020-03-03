package com.enginebai.leetcode.easy

import kotlin.math.abs
import kotlin.math.absoluteValue

object Solution7 {
    fun reverse(x: Int): Int {
        var num = x
        var reverseNum = 0
        while (num != 0) {
            reverseNum = reverseNum * 10 + num % 10
            num /= 10
        }
        return if (reverseNum > Int.MAX_VALUE || reverseNum < Int.MIN_VALUE)
            0
        else
            reverseNum
    }
}

fun main() {
    val input = -120
    println(Solution7.reverse(input))
}