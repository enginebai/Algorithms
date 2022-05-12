# Sorting
## Insertion 
**Idea!** Insert the `A[j]` into `A[1..j - 1]` sorted sorted sequence. It's efficient for sorting a small number of elements and the numbers are **sorted in place**.

```kotlin
fun insertionSort(A) {
    for (j = 2 untils A.size) {
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
