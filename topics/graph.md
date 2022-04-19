# Graph
A graph `G = (V, E)`, consists of `V` (set of *vertices*) and `E` (*edges* of vertices). There are *undirected* and *directed* graph, the pair `(x, y)` and `(y, x)` represent the same edge in a undirected graph, however, it's two different edge in directed graph, where `(x, y)` represents `x` (head) to `y` (tail).

![Graph](../media/graph.png)

* G1: Undirected 
    * V(G1) = {1, 2, 3, 4, 5, 6}
    * E(G1) = {(1, 2), (1, 4), (1, 5), (1, 6), (2, 4), (3, 4), (3, 5), (3, 6)}
* G2: Directed
    * V(G2) = {1, 2, 3, 4, 5, 6}
    * E(G2) = {(1, 2), (3, 4), (4, 5), (6, 1), (6, 3)}

## Representation
We can represent a graph `G = (V, E)` in adjacency list or matrix.

### Adjacency List
We define a list that contains all the vertices such that there is an edge between the vertices.

|      | Undirected                                                                                                 | Directed                                                                 |
|------|------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------|
| List | 1 -> 2 -> 4 -> 5 -> 6<br>2 -> 1 -> 4<br>3 -> 4 -> 5 -> 6<br>4 -> 1 -> 2 -> 3<br>5 -> 1 -> 3<br>6 -> 1 -> 3 | 1 -> 2<br>2 -> null<br>3 -> 4<br>4 -> 2 -> 5<br>5 -> null<br>6 -> 1 -> 3 |

* The vertices in each adjacency list are typically stored in a arbitrary order.
* For both undirected and directed graph, the amount of memory is `Θ(V + E)` space complexity. (`|V| + |E|` for directed, `|V| + 2 * |E|` for undirected)
* We also can associate *weight* on the edge by storing the weight on the node of the adjacency list. (linked list node can attach extra properties)

> |V| means the size of V.

### Adjacency Matrix
We define a |V| x |V| matrix `A`such that `A(i, j) = 1` if there is an edge, `0` otherwise.

```
Undirected          Directed
   1 2 3 4 5 6         1 2 3 4 5 6
 -------------       -------------
1| 0 1 0 1 1 1      1| 0 1 0 0 0 0
2| 1 0 0 1 0 0      2| 0 0 0 0 0 0
3| 0 0 0 1 1 0      3| 0 0 0 1 0 0
4| 1 1 1 0 0 0      4| 0 0 0 0 1 0
5| 1 0 1 0 0 0      5| 0 0 0 0 0 0
6| 1 0 1 0 0 0      6| 1 0 1 0 0 0
```

* It requires `Θ(V^2)` space complexity. (The undirected graph has a symmetric matrix, it has additional space to store `(x, y)` and `(y, x)` of the same edge, some applications will store only in half to save memory or use sparse matrix)
* `A` is equal to the *transpose* of matrix `A` for undirected graph.
* We also can define `A(i, j) = w` for weighted graph.

## Breath-first Search (BFS)
## Depth-first Search (DFS)

## Resources
- [ ] Fundamental of Data Structure
- [ ] CLRS
- [ ] [Khan Academy](https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/describing-graphs)
- [ ] MIT
    - [ ] [DFS](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-10-depth-first-search/)
    - [ ] [BFS](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-9-breadth-first-search/)
- [ ] http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html // Nice introductory note
- [ ] [Stanford](http://infolab.stanford.edu/~ullman/focs/ch09.pdf) // Nice course
- [ ] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-6)
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/graph/) // Simple note
- [ ] Software Engineering Interview Preparation // Simple note
    - [ ] [Data Structure](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#graphs)
    - [ ] [DFS/BFS](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/algorithms.md#graph-algorithms)
- [ ] [LC Learn](https://leetcode.com/explore/learn/card/graph/) // Some topics are locked!!
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Graph_Breadth-FS)
- [ ] ~~[Coding Interview University](https://github.com/jwasham/coding-interview-university#graphs)~~