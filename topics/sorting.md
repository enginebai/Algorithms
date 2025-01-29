# Sorting
## Insertion 
**Idea!** Insert the `A[j]` into `A[1..j - 1]` sorted sorted sequence. It's efficient for sorting a small number of elements and the numbers are **sorted in place**.

```kotlin
fun insertionSort(A) {
    for (j in 2 untils A.size) {
        val key = A[j]
        var i = j - 1
        while (i > 0 && A[i] > key) {
            A[i + 1] = A[i]
            i--
        }
        A[i + 1] = key
    }
}
```

* **Time Complexity**: It takes `O(n^2)`, The while loop (finding the right position to insert) takes `SUM(i = 2 to n) { j - 1 }` = `O(n^2)`.
* **Space Complexity**: `O(n)` sorted in place.

## Merge Sort
**Idea!** We will use *divide-and-conquer* approach to implement merge sort.

For example, `A = [5, 2, 4, 7, 1, 3, 2, 6]`:
* **Divide**: Divide `n` elements sequence into subsequences of `n/2` elements.

```js
[5, 2, 4, 7, 1, 3, 2, 6]

[5, 2, 4, 7] [1, 3, 2, 6]

[5, 2] [4, 7] [1, 3] [2, 6]

[5] [2] [4] [7] [1] [3] [2] [6]
```
* **Conquer**: Sort the two subsequences recursively using merge sort.

```js
// It's sorted trivially for one element.
[5] [2] [4] [7] [1] [3] [2] [6]
```

* **Combine**: Merge two sorted subsequences.

```js
[5] [2] [4] [7] [1] [3] [2] [6]

[2, 5] [4, 7] [1, 3] [2, 6]

[2, 4, 5, 7] [1, 2, 3, 6]

[1, 2, 2, 3, 4, 5, 6, 7]
```

```kotlin
fun mergeSort(nums: IntArray): IntArray {
    if (nums.size > 1) {
        val middle = nums.size / 2
        // Not 0..middle / middel + 1 until nums.size, this will cause stack overflow.
        // Take a look at size 2.
        val leftArray = mergeSort(nums.sliceArray(0 until middle))
        val rightArray = mergeSort(nums.sliceArray(middle until nums.size))
        return merge(leftArray, rightArray)
    } else {
        return nums
    }
}

private fun merge(leftArray: IntArray, rightArray: IntArray): IntArray {
    var left = 0
    var right = 0
    var k = 0
    val result = IntArray(leftArray.size + rightArray.size)
    while (left < leftArray.size && right < rightArray.size) {
        if (leftArray[left] <= rightArray[right]) {
            result[k++] = leftArray[left]
            left++
        } else {
            result[k++] = rightArray[right]
            right++
        }
    }
    while (left < leftArray.size) {
        result[k++] = leftArray[left++]
    }
    while (right < rightArray.size) {
        result[k++] = rightArray[right++]
    }
    return result
}

mergeSort(nums)
```

> See the sameple at P.30 of CLRS.

* **Time Complexity**: `O(n log n)` from recurrence `T(n) = T(n/2) + Θ(n)` and solved by *[master method](../topics/recursion.md)*. (See P.35 of CLRS.)
* **Space Complexity**: `O(n)`, extra space to copy array for merging, it's still `O(n)`.

## Quick Sort
It uses *divide-and-conquer* approach, sorts in place (beats the merge sort), and is efficient (`O(n log n)`) on average.

* **Divide**: Partition the array `A` into two subarrays and pick a *pivot* value `X` such that every element in `A[p...q - 1]` is <= `X` and `A[q + 1..r] >= X`.

```
A is partition into | <= X | X | >= X |
```

* **Conquer**: Recursively sort 2 subarrays.
* **Combine**: The 2 subarrays are sorted in place, it's trivial in this step.

```kotlin
private fun partition(A: IntArray, start: Int, end: Int): Int {
    // We pick the first element as pivot
    val pivot = A[start]
    // The last index which value is less than pivot
    // We keep A[start..i] <= pivot
    var i = start
    for (j in start + 1..end) {
        if (A[j] < pivot) {
            i++
            A.swap(i, j)
        }
    }
    A.swap(start, i)
    return i
}

private fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

fun quickSort(A: IntArray, start: Int, end: Int) {
    if (start < end) {
        // Randomly pick pivot to achieve average-case O(n lg n)
        // end - start + 1 is the size of subarray
        val randomPivotIndex = (Math.random() * (end - start + 1)).toInt()
        A.swap(start, start + randomPivotIndex)

        val pivotIndex = partition(A, start, end)
        quickSort(A, start, pivotIndex - 1)
        quickSort(A, pivotIndex + 1, end)
    }
}

quickSort(A, 0, A.size - 1)
```

### Time Complexity
#### Worst Case
* Sorted or reversed sorted array.
* One subarray has no elements after partition.

The recurrence is
```
T(n) = T(0) + T(n - 1) + Θ(n)
```

Based on [recursion tree method](../topics/recursion.md#time-complexity-recurrences), we can get `cn + c(n - 1) + c(n - 2) + ... + 2c + c` = `SUM(i = 1 to n) { i }` = `O(n^2)`. For space complexity, it takes `O(n)` for recursive function call stack.

#### Best Case
The partition generates a balanced two subarrays, that is `n/2` size, the recurrence will be `T(n) = T(n/2) + Θ(n)`, which is `O(n log n)`.

#### Average Case
It takes `O(n log n)` on average if we can add randomization to pick the pivot in order to obtain good average case performance over all inputs. (See the comment of above code). For space complexity, it takes `O(lg n)` on average for recursive function call stack.

## Heap Sort
See [Heap](../topics/heap.md) topic.

## Counting Sort
> TODO

## Kotlin APIs
> All the following sorting APIs can append `Descreasing` to sort in descending order.

### `IntArray`
```kotlin
val nums = intArrayOf(...)

// Sort array in-placee
nums.sort()
// Sort array and return sorted list
val sortedList: List<Int> = nums.sorted()
val sortedListDescending: List<Int> = nums.sortedDescending()

// Sort array and return int array
val sortedArray: IntArray = nums.sortedArray()
val sortedArrayDescending: IntArray = nums.sortedArrayDescending()

// Count the frequency of each element
val countMap = HashMap<Int, Int>()

// Sort array by something else (frequency) and return sorted list
val sortedByList: List<Int> = nums.sortedBy { countMap[it] }

// Sort array by frequency increasingly, if the frequency is the same, sort by the value itself in descending order
// [1, 5, 5, 2, 2]
val sortedByList: List<Int> = nums.sortedWith(compareBy<Int> { countMap[it] }.thenByDescending { it })
// Or equivalently
val sortedByList: List<Int> = nums.sortedWith(compareBy<Int>({ countMap[it] }, { -it }))

// For 2D array
val intervals = arrayOf(
    intArrayOf(1, 3),
    intArrayOf(2, 6),
    intArrayOf(8, 10),
    intArrayOf(15, 18)
)

// Sort the intervals by the start time
intervals.sortBy { it[0] }
```

### `Array`
```kotlin
val nums: Array<IntArray> = ...

// Sort array in-place
nums.sort()
nums.sortDescending()
nums.sortBy { it[0] }
nums.sortByDescending { it[0] }
``` 

> Sample usage: [1636. Sort Array by Increasing Frequency](../leetcode/1636.sort-array-by-increasing-frequency.md)

### `Comparable`
```kotlin
data class Person(val name: String, val age: Int) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return this.age - other.age
    }
}

val people = listOf(
    Person("Alice", 30),
    Person("Bob", 25),
    Person("Charlie", 35)
)

// Sort the list of people by age
val sortedPeople = people.sorted()

// Sort the list of people by name
val sortedByName = people.sortedWith(compareBy { it.name })
```

### `List`
```kotlin
val list = mutableListOf(1, 3, 5, 2, 4, 6, 0, -1, -3, 10)

// Sort list in-place
list.sort()
// Sort list by something in-place
list.sortBy { it }

// Sort list and return sorted list
val sortedList: List<Int> = list.sortedBy { it }

// Sort by Collections API decreasingly
Collections.sort(list) { n1, n2 -> n2 - n1 }
// Or equivalently
list.sortWith { n1, n2 -> n2 - n1 }
```

## Resources
- CLRS
- [MIT](https://ocw.mit.edu/courses/6-046j-introduction-to-algorithms-sma-5503-fall-2005/video_galleries/video-lectures)
- [基本資料結構系列文章](http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html)
- ~~Fundamental of Data Structure~~ // Similar to CLRS
- CTCI
- [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-8) // Animation of sorting algorithm with steps in the code
- [Coding Interview University](https://github.com/jwasham/coding-interview-university#sorting)
- [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/sorting-searching/) // Simple notes + time complexity tables
- [Tech-Interview-Cheat-Sheet](https://github.com/TSiege/Tech-Interview-Cheat-Sheet#sorting-algorithms) // Simple notes
