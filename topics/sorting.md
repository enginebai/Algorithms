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
fun mergeSort(A, start, end) {
    if (start < end) {
        val half = (start + end).div(2).floor()
        mergeSort(A, start, half)
        mergeSort(A, half + 1, end)
        // merge two sorted array
        merge(A, start, half, end)
    }
    // start == end, one element, it's already sorted, this is the base case.
    // start > end, invalid case.
}

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

mergeSort(A, 0, A.size - 1)
```

> See the sameple at P.30 of CLRS.

* **Time Complexity**: `O(n log n)` from recurrence and solved by *[master method](../topics/recursion.md)*. (See P.35 of CLRS.)
* **Space Complexity**: `O(n)`, extra space to copy array for merging, it's still `O(n)`.

## Quick Sort

## Heap Sort

## References
- [ ] CLRS
- [ ] [MIT](https://ocw.mit.edu/courses/6-046j-introduction-to-algorithms-sma-5503-fall-2005/video_galleries/video-lectures)
- [ ] [基本資料結構系列文章](http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html)
- [ ] ~~Fundamental of Data Structure~~ // Similar to CLRS
- [ ] CTCI
- [X] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-8) // Animation of sorting algorithm with steps in the code
- [ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#sorting)
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/sorting-searching/) // Simple notes + time complexity tables
- [ ] [Tech-Interview-Cheat-Sheet](https://github.com/TSiege/Tech-Interview-Cheat-Sheet#sorting-algorithms) // Simple notes
