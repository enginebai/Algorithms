package com.enginebai.leetcode.easy

object Solution1 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target)
                    return intArrayOf(i, j)
            }
        }
        return intArrayOf()
    }

    fun twoSum2(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        nums.forEachIndexed { i, num -> map[num] = i }
        nums.forEachIndexed { i, num ->
            val complement = target - num
            if (map.containsKey(complement) && i != map[complement]) return intArrayOf(i, map[complement]!!)
        }
        return intArrayOf()
    }
}

fun main() {
    val nums = intArrayOf(3, 2, 4)
    val target = 6
    Solution1.twoSum2(nums, target).forEach {
        print("$it, ")
    }
}