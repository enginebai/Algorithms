package com.enginebai.leetcode.easy

object Solution392 {
    fun isSubsequence(s: String, t: String): Boolean {
        var index = 0
        s.forEach {
            var find = false
            for (i in index until t.length) {
                if (it == t[i]) {
                    index = i + 1
                    find = true
                    break
                }
            }
            if (!find)
                return false
        }
        return true
    }
}

fun main() {
    val s1 = "abc"
    val t1 = "ahbgdc"

    val s2 = "axc"
    val t2 = "ahbgdc"

    println(Solution392.isSubsequence(s1, t1))
    println(Solution392.isSubsequence(s2, t2))
}