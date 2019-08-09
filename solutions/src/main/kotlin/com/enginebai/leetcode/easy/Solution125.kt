package com.enginebai.leetcode.easy

/**
 * https://leetcode.com/problems/valid-palindrome/
 */
object Solution125 {
    fun isPalindrome(s: String): Boolean {
        val alphaNumeric = s.replace("[^a-zA-Z0-9]".toRegex(), "").toLowerCase()
        var left = 0
        var right = alphaNumeric.length - 1
        while (left < right) {
            if (alphaNumeric[left] != alphaNumeric[right]) {
                return false
            }
            left += 1
            right -= 1
        }
        return true
    }
}

fun main() {
    val input1 = "A man, a plan, a canal: Panama"
    println(Solution125.isPalindrome(input1))
    val input2 = "race a car"
    println(Solution125.isPalindrome(input2))
}