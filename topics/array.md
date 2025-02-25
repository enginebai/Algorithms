# Array
## Sequences
*Sequences* maintain a collection of items in an *extrinsic* order. By extrinsic, it means the first item is "first" not because of what the item is (such as it is the largest number), but because the external position puts it there. Sequances are generalizations of `array`, `stack`, `queue` and `linked list`.

| Usages    | Operations        | Descriptions                                          |
|-----------|-------------------|-------------------------------------------------------|
| Container | `create(X)`       | Given an iterable `X`, create sequence from items in `X`. |
|           | `size()`          | The number of items.                                  |
| Static    | `iterator()`      | Return items one-by-one in sequence order.            |
|           | `get_at(i)`       | Return the i-th item.                                 |
|           | `set_at(i, x)`    | Replace the i-th item with `x`.                         |
| Dynamic   | `insert_at(i, x)` | Add item `x` as the i-th item.                          |
|           | `delete_at(i, x)` | Remove and return the i-th item.                      |
|           | `insert_first(x)`  | Add item `x` to the first item.                         |
|           | `delete_first()`  | Remove and return the first item.                     |
|           | `insert_last(x)`   | Add item `x` to the last item.                          |
|           | `delete_at(i, x)` | Remove and return the last item.                      |

## Implementation
There are two main data structure approaches:
1. Array-based
2. Pointer-based

### Array
Implementing a sequence using an array, which index `i` is the item `i` allows `get_at(i)` and `set_at(i, x)` to be `O(1)` (*random access*, it is great for static operations!). However, when deleting or inserting, we have to reallocate the array and shift all items (by creating a new array with updated size and coping the existing items to the new array), it take `O(n)` in the worst case.

| Data Structure | Container   | Static                     | Dynamic                             | Dynamic                            | Dynamic                          |
|----------------|-------------|----------------------------|-------------------------------------|------------------------------------|----------------------------------|
|                | `create(X)` | `get_at(i)` `set_at(i, x)` | `insert_at(i, x)` `delete_at(i, x)` | `insert_first(x)` `delete_first()` | `insert_last(x)` `delete_last()` |
| Array          | `O(n)`      | **`O(1)`**                 | `O(n)`                              | `O(n)`                             | `O(n)`                           |

### Linked List
*Linked List* is a pointer-based data structure, each item has a *node* with two properties: `node.item` (the value) and `node.next` (the link to next node), and maintain pointers to the first node, called *head*.

Linked list takes `O(1)` for inserting or deleting first item simply by relinking the pointer. However, it takes `O(n)` for the getter/setter function since it finds the i-th element through items one-by-one.

| Data Structure | Container   | Static                     | Dynamic                             | Dynamic                            | Dynamic                          |
|----------------|-------------|----------------------------|-------------------------------------|------------------------------------|----------------------------------|
|                | `create(X)` | `get_at(i)` `set_at(i, x)` | `insert_at(i, x)` `delete_at(i, x)` | `insert_first(x)` `delete_first()` | `insert_last(x)` `delete_last()` |
| Linked List    | `O(n)`      | `O(n)`                     | `O(n)`                              | **`O(1)`**                         | `O(n)`                           |

> More detail, see [Linked List](../topics/linked-list.md) topic.

### Dynamic Array
The `insert_last(x)` takes `O(n)` for every time, however, there is way to relax constraint size of array: *over-allocate*, we reallocate `Θ(n)` extrac space (0.5n or 2n) so that **reallocation does not occur with every dynamic operation**.

Suppose we allocate new array to double size (2n) when `insert_last(x)` as array is full, and we do `n` time of `insert_last(x)` from empty array, we have to resize 1, 2, 4, 8...etc items for each round of reallocation, it takes `Θ(1 + 2 + 4 + 8 + ... + n)`, which is `Θ(SUM(i = 1 to log n) {2^i})` = `Θ(n)`, linear time for `n` times operations, then that is constant time for each opeation on average.

> SUM(i = 1 to k) {2^i} = 2^(k+1) - 1

Allocating addition space can gurantee that `n` insertions only takes `O(n)`, so insertion will take `O(1)` time per insertion **on average**, that is called **amortized constant time**, the cost of the operation is amortized (distributed) across many operations. Deleting the last element also take `O(1)`, since no other elements needs to be shifted.

| Data Structure | Container   | Static                     | Dynamic                             | Dynamic                            | Dynamic                          |
|----------------|-------------|----------------------------|-------------------------------------|------------------------------------|----------------------------------|
|                | `create(X)` | `get_at(i)` `set_at(i, x)` | `insert_at(i, x)` `delete_at(i, x)` | `insert_first(x)` `delete_first()` | `insert_last(x)` `delete_last()` |
| Dynamic Array  | `O(n)`      | **`O(1)`**                     | `O(n)`                              | `O(n)`                             | **`O(1)`**                       |

#### Amortization
* Operation has **amortized cost** `T(n)` if `k` operations cost at most `k * T(n)`, that is *on average* over may operations.
* Inserting into a dynamic array take `O(1)` amortized time. (It might still take `O(n)` for some worst case)

### Pros. & Cons.
Comparison to Linked List, see [Linked List](../topics/linked-list.md) topic.
| Pros.                                                  | Cons.                                                                                 | Usages                         |
|--------------------------------------------------------|---------------------------------------------------------------------------------------|--------------------------------|
| 1. Random access.<br>2. No `next` field, saving space. (vs Linked List) | 1. Bad at insert/delete. (Copy to new array)<br>2. Bad at resize. (Copy to new array) | 1. Fast access.<br>2. Fix size |

## Array & String API
### Python
```python
# Subarray or substring
array = [5] * 3
array[1:5]   # subarray of A[1:5), inclusive 1, exclusive 5
array[3:]    # subarray of A[3:)

string = "Hello"
string[1]    # 'e'
string[1:5]  # "ello"

# Replacement
string.replace("Hello", "XXX") # "XXX"
'.'.join(string) # "H.e.l.l.o"

ip = 'xxx.xxx.xxx.xxx'
# Replace all '.' with '[.]' in ip address
ip.replace('.', '[.]')
'[.]'.join(ip.split('.')) 
re.sub(r'\.', '[.]', ip)

# Count
s = 'abac'
count = collections.Counter(s)
```

### Kotlin
```kotlin
val array = IntArray(3) { 5 } // [5, 5, 5]
array.sliceArray(1..5)   // subarray of A[1:5]
array.sliceArray(3 until array.size) // subarray of A[3:]

val string = "Hello, World!"
string[1] // 'e'
string.substring(1, 5) // "ello"
```