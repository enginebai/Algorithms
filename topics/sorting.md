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
fun merge(A: IntArray, start: Int, half: Int, end: Int) {
    // 0,1,2,3,4 => half is 2 => 0,1,2 | 3,4
    // 0,1,2,3   => half is 1 => 0,1   | 2,3
    val leftSize = half - start + 1
    val rightSize = end - half

    // We set the last element to be sentinel value
    val leftArray = IntArray(leftSize + 1)
    val rightArray = IntArray(rightSize + 1)

    for (i in 0 until leftSize) {
        leftArray[i] = A[start + i]
    }
    for (j in 0 until rightSize) {
        rightArray[j] = A[half + 1 + j]
    }

    // We set the last element to be sentinel value
    leftArray[leftSize] = Int.MAX_VALUE
    rightArray[rightSize] = Int.MAX_VALUE

    // Start to merge
    var left = 0
    var right = 0
    for (k in start..end) {
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
        // We don't write middle = (start + end) / 2
        // Source: https://ai.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html
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

Based on [recursion tree method](../topics/recursion.md#time-complexity-recurrences), we can get `cn + c(n - 1) + c(n - 2) + ... + 2c + c` = `SUM(i = 1 to n) { i }` = `O(n^2)`

#### Best Case
The partition generates a balanced two subarrays, that is `n/2` size, the recurrence will be `T(n) = T(n/2) + Θ(n)`, which is `O(n log n)`.

#### Average Case
It takes `O(n log n)` on average if we can add randomization to pick the pivot in order to obtain good average case performance over all inputs. (See the comment of above code). For space complexity, it takes `O(lg n)` on average for recursive function call stack.

## Heap Sort
See [Heap](../topics/heap.md) topic.

## Cycle Sort


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
