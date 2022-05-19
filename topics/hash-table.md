# Hash Table
A dynamic set that supports the *dictionary* operations: `insert()`, `delete()` and `search()`, and there are some to fulfill this:
* **Direct access address**: it's effificent to `search()`, however, it takes lots of space.
* **Hashing**: We compute the array index from key.
* **Chaining**: It's a way to handle *collisions* for hashing.

> We use some symbols for the whole topic:
> 
> 1. `U`: Universe, represents all the possible keys.
> 2. `T`: Table, the array we represent the dynamic set.
> 3. `Slot`: `T[i]`, the position of the array.

## Direct Access Table
It works well when the universe `U` of key is small, we use array, key as index, store the value corresponds to key index.

![Direct Access Table](../media/hashing-direct-address-table.png)

```kotlin

class DirectAccessAddress<T> {
    private val table = ArrayOf<T>(UNIVERSE_SIZE)

    fun search(key: Int): T? = table[key]
    fun insert(value: T): table[key(value)] = value
    fun delete(value: T) { table[key(value)] = null }
}
```

Each operations take `O(1)` time, but it requires `O(|U|)` size to save all possible keys.



## References
- [ ] CLRS
- [ ] [MIT](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-4-hashing/)
- [ ] [基本資料結構系列文章](http://alrightchiu.github.io/SecondRound/hash-tableintrojian-jie.html)
- [ ] [LC Learn](https://leetcode.com/explore/learn/card/hash-table/)
- [ ] Fundamental of Data Structure
- [ ] CTCI
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/hash-table/)
- [ ] [Google Tech Dev Guide - Map/Dictionary](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#linear)
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#HashTable_Doubly_Linked_List)
- [ ] [Code Interview University](https://github.com/jwasham/coding-interview-university#hash-table)
- [ ] [leetcode-master](https://github.com/youngyangyang04/leetcode-master#%E5%93%88%E5%B8%8C%E8%A1%A8)
- [ ] [soft-eng-interview-prep](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#hash-tables)
- [ ] [Tech-Interview-Cheat-Sheet](https://github.com/TSiege/Tech-Interview-Cheat-Sheet#hash)