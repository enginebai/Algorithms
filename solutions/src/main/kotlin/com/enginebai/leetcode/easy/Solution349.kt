package com.enginebai.leetcode.easy

object Solution349 {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        // solution1
        val set1 = nums1.toSet()
        val set2 = nums2.toSet()

        val intersectionSet = mutableSetOf<Int>()
        set1.forEach {
            if (it in set2) {
                intersectionSet.add(it)
            }
        }
        return intersectionSet.toIntArray()

        // one-line solution
//        return nums1.toSet().intersect(nums2.toSet()).toIntArray()
    }
}

fun main() {
    val input1Num1 = intArrayOf(1, 2, 2, 1)
    val input1Num2 = intArrayOf(2, 2)
    Solution349.intersection(input1Num1, input1Num2).forEach {
        print("\"$it\", ")
    }
    println()

    val input2Num1 = intArrayOf(4, 9 ,5)
    val input2Num2 = intArrayOf(9, 4, 9, 8, 4)
    Solution349.intersection(input2Num1, input2Num2).forEach {
        print("\"$it\", ")
    }
}