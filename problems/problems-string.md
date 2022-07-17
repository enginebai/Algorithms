## [344. Reverse String](https://leetcode.com/problems/reverse-string/)

### Strightforward
```kotlin
fun reverseString(s: CharArray): Unit {
    for (i in 0 until s.size / 2) {
        val reverseIndex = s.size - 1 - i
        val temp = s[i]
        s[i] = s[reverseIndex]
        s[reverseIndex] = temp
    }
}
```

----
## [387. First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/)

```kotlin
fun firstUniqChar(s: String): Int {
    val hash = IntArray(26) { _ -> 0 }
    for (c in s) {
        val key = c - 'a'
        hash[key] = hash[key] + 1
    }
    for (i in 0 until s.length) {
        if (hash[s[i] - 'a'] == 1) return i
    }
    return -1
}
```

We can **use the problem assumption**, it will be lower-case alphabet, that is, we can declare a index array of size 26, then run a for-loop to record the all characters with its index (override it if it's duplicate), finally, run a for-loop to see the index is equal to the record, if it's unique, the index will be equal.

----
## [242. Valid Anagram](https://leetcode.com/problems/valid-anagram/)

```kotlin
fun isAnagram(s: String, t: String): Boolean {
    val hash = IntArray(26) { _ -> 0 }
    for (c in s) {
        val key = c - 'a'
        hash[key] = hash[key] + 1
    }
    for (c in t) {
        val key = c - 'a'
        if (hash[key] == 0) return false
        hash[key] = hash[key] - 1
    }
    hash.forEach {
        if (it > 0) return false
    }
    return true
}
```

* **Time Complexity**: `O(m + n)` where `m`, `n` is the length of the `s` and `t` respectively.
* **Space Complexity**: `O(1)`.