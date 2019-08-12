package com.enginebai.leetcode.easy

/**
 * https://leetcode.com/problems/valid-mountain-array/
 */
object Solution941 {
    fun validMountainArray(A: IntArray): Boolean {
        if (A.size < 3) return false
        var left = 0
        var right = A.size - 1
        while (left < right && A[left] < A[left+ 1]) {
            left++
            if (left == A.size - 1)
                return false
        }
        while (right > left && A[right - 1] > A[right]) {
            right--
            if (right == 0)
                return false
        }
        return (left == right)
    }
}

fun main() {
    val input1 = intArrayOf(2, 1)
    val input2 = intArrayOf(3, 5, 5)
    val input3 = intArrayOf(0, 3, 2 ,1)
    val input4 = intArrayOf(1, 5, 3, 4, 2)
    println(Solution941.validMountainArray(input1))
    println(Solution941.validMountainArray(input2))
    println(Solution941.validMountainArray(input3))
    println(Solution941.validMountainArray(input4))
}