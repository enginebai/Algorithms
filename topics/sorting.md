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

* **Divide**: Divide `n` elements sequence into subsequences of `n/2` elements.
* **Conquer**: Sort the two subsequences recursively using merge sort.
* **Combine**: Merge two sorted subsequences.

```kotlin
private fun merge(A, p, q, r) {
    // Copy array into two left/right arrays
    val left = q - p + 1
    val right = r - q
    // +1 for sentinel value
    val leftArray = arrayOf(left + 1)
    val rightArray = arrayOf(right + 1)
    for (i in 0 untils left) {
        leftArray[i] = A[i]
    }
    for (j in 0 until right) {
        rightArray[j] = A[j]
    }
    // We set the last element to be sentinel value
    leftArray[left] = MAX
    rightArray[right] = MAX

    // Start to merge
    left = 0
    right = 0
    for (k in p until r) {
        if (leftArray[left] < rightArray[right]) {
            A[k] = leftArray[left]
            left++
        } else {
            A[k] = rightArray[right]
            right++
        }
    }
}

fun mergeSort(A, start, end) {
    if (start < end) {
        val half = start + (end - start) / 2
        mergeSort(A, start, half)
        mergeSort(A, half + 1, end)
        // merge two sorted array
        merge(A, start, half, end)
    }
    // start == end, one element, it's already sorted, this is the base case.
    // start > end, invalid case.
}

mergeSort(A, 0, A.size - 1)
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
// | x | <= x | >= x | ?? |
//   p        i      j    r
private fun partition(A, p, r): Int {
    // We pick the first element as pivot
    val pivot = A[p]
    // We keep A[p..i] <= pivot
    var i = p
    for (j in p + 1..r) {
        if (A[j] <= pivot) {
            i++
            swap(A[j], A[i])
        }
    }
    swap(pivot, A[i])
    return i
}

fun quickSort(A, p, r) {
    if (p < r) {
        val q = partition(A, p, r)
        quickSort(A, p, q - 1)
        quickSort(A, q + 1, r)
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

Based on [recursion tree method](../topics/recursion.md#time-complexity-recurrences), we can get `cn + c(n - 1) + c(n - 2) + ... + 2c + c` = `SUM(i = 1 to n) { i }` = `O(n^2)`

#### Best Case
The partition generates a balanced two subarrays, that is `n/2` size, the recurrence will be `T(n) = T(n/2) + Θ(n)`, which is `O(n log n)`.

#### Average Case
It takes `O(n log n)` on average if we can add randomization to pick the pivot in order to obtain good average case performance over all inputs.

```kotlin
private fun partition(A, p, r): Int {
    val random = Math.random(p, r)
    swap(A[p], A[random])
    return partition(A, p, r)
}

// Other part is as same as above.
```

## Heap Sort
See [Heap](../topics/heap.md) topic.

## Resources
- [X] CLRS
- [X] [MIT](https://ocw.mit.edu/courses/6-046j-introduction-to-algorithms-sma-5503-fall-2005/video_galleries/video-lectures)
- [X] [基本資料結構系列文章](http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html)
- [ ] ~~Fundamental of Data Structure~~ // Similar to CLRS
- [ ] CTCI
- [X] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-8) // Animation of sorting algorithm with steps in the code
- [ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#sorting)
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/sorting-searching/) // Simple notes + time complexity tables
- [X] [Tech-Interview-Cheat-Sheet](https://github.com/TSiege/Tech-Interview-Cheat-Sheet#sorting-algorithms) // Simple notes
