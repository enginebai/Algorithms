# Shortest Path
![Weighted Graph](../media/weighted-graph.png)

* For weighted directed graph, we define the shorted path is the lowest weight path from source `s` to each vertex `v`.
* The [BFS](../topics/graph.md#breadth-first-search-bfs) is a shortest path algorithm on unweighted graph (unit weight).
* A *negative-weight cycle* is a path starting and ending at the same vertex with the weight of path is less than 0, and if there is a egative-weight cycle reachable from `s`, then the shortest-path weight are not well defined. (We can keep going from 4 -> 5 -> 6 and back to 4, the total distance keeps decreasing by -11 for this circle) So there is no the shortest path for the graph with negative-weight cycle. (But positive-weight cycle is allowed)

To find the shortest path, we maintain two attributes for every node in the graph:
* `v.distance`: upper bound weight of path from source `s` to `v`. (distance estimate of the shortest path)
* `v.predecessor`: the chain of predecessors originating at the vertex `v` runs backwards along a shortest path from `s` to `v`. (used to backtrace to find the shortest path)

> Notation: `d` means distance, `w` means weight for short in the following note.

For the most simple case, how to find the shortest path from `s` to `v` of the graph containing only three vertices `s`, `u`, `v`? The shortest path is either `w(s, v)` (arriving directly) or `w(s, u)` + `w(u, v)` (passing through `u`)

![Relaxation Example](../media/relaxation-example.png)

```js
if d(s, v) > d(s, u) + w(u, v)
    d(s, v) = d(s, u) + w(u, v)
```

If the distance of the path passing `u` is less than the current path, then we can find a shorter path through edge `(u, v)`, and we have to update the current path. This gives us the general idea of finding the shortest path: **Relaxation**.

## Relaxation
The single-source shortest path algorithm use the technique of *relaxation*, the process of *relaxing* an edge `(u, v)` consists of testing whether we can improve the current shortest path from `s` to `v` found so far by "going through the edge `(u, v)`".

![Relaxation General](../media/relaxation-general.png)

> The distance of `(s, v)` is 12, and we can reduce the distance by passing `u`, so the distance of `(s, v)` can relax to distance of `(s, u)` = 7 + edge weight of `(u, v)` = 2, that becomes 9.

That is, if we can find the shortest path of `s(s, v)` = `s(s, u)` + `w(u, v)`, then there exists a shortest path through edge `(u, v)`.

```kotlin
fun relax(s: Node<T>, u: Node<T>, v: Node<T>) {
    if (distance(s, v) > distance(s, u) + weight(u, v)) {
        // Relaxation is to update the distance estimation and the predecessor
        distance(s, v) = distance(s, u) + weight(u, v)
        v.parent = u
    }
}
```

Before relaxation, we have to initialize:

```kotlin
fun initialize(G, s) {
    val vertices = G.keys
    vertices.forEach { v ->
        v.distance = Int.MAX
        v.parent = null
    }
    s.distance = 0
}
```

The shortest path algorithms, including *Bellman-Ford*, *DAG Relaxation*, and *Dijkstra*, call the `initialize()` and repeatedly call `relax()` on edges, they differ in how many times and the order in which they relax edges.

## Bellman-Ford Algorithm
The *Bellman-Ford* algorithm solves the single source shortest path problem in the graph in which edge weights may be negative. (also might contain cycle) The algorithm can not only find the path, but also detect if there is negative-weight cycle in the graph.

The algorithm is straightforward: initialize relaxation, and then relax every edges in `|V| - 1` times.

```kotlin
/**
 * Return true means the graph does not contain negative-weight cycle.
 */
fun bellmanFord(G, s): Boolean {
    initialize(G, s)
    for (i in 1 to G.vertices - 1) {
        for (e in G.edges) {
            relax(s, u, v)
        }
    }

    // Check if any edge is still relaxable for negative-weight cycle.
    // (still violates the triangle inequality)
    for (e in G.edges) {
        if (distance(s, v) > distance(s, u) + weight(u, s)) {
            return false
        }
    }
    return true
} 
```

> Take a look at the sameple at P.589 of CLRS.

**Idea!** The shortest path contains at most `|V| - 1` edges as it traverses any vertex of the graph at most once. Here we can conduct *graph duplication*: make `|V| + 1` level of copies. (Level 0 is for initialization, Level 1 ~ (`|V|` - 1) for relaxation, and last level for check negative-weight cycle.)

![Bellman-Ford](../media/shortest-path-bellman-ford.png)
> Source: [MIT Open Courseware - Introduction To Algorithms - Bellman-Ford](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/mit6_006s20_lec12/)

For the graph copy, we run `|V| + 1` round of relaxation for every edges. At level `|V| - 1`, we will get the shortest path, and the last round will know if the graph contains negative-weight cycle. (still can relax, violates triangle inequality)

### Time Complexity
The algorithm runs `|V|` rounds and each round performs on each edges, so it takes `O(|V|*|E|)` time. However, we can obtain significatn savings by stoping relaxation round for which no edge relaxation is modifying.

### Space Complexity
We duplciate the graph `O(|V|)` times, so the space complexity will be `O(|V| * (|V| + |E|))`.

## Resources
- [ ] Fundamental of Data Structure
- [ ] CLRS
    - [ ] Single-Source Shortest Paths
    - [ ] All-Pairs Shortest Paths
- [ ] MIT
    - [ ] [Shortest Path](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-11-weighted-shortest-paths/)
    - [ ] [Bellman-Ford](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-12-bellman-ford/)
    - [ ] [Dijkstra](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-13-dijkstra/)
    - [ ] [All Pair Shortest Path](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/lecture-14-apsp-and-johnson/)
- [ ] http://alrightchiu.github.io/SecondRound/mu-lu-yan-suan-fa-yu-zi-liao-jie-gou.html // Nice introductory note
- [ ] [Stanford](http://infolab.stanford.edu/~ullman/focs/ch09.pdf) // Nice course
- [ ] [Software Engineering Interview Preparation](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/algorithms.md#shortest-paths)
- [ ] ~~[LC Learn](https://leetcode.com/explore/learn/card/graph/)~~ // Some topics are locked!! We could try to do all the problem to lock.
    * Disjoint Set
    * DFS
    * BFS
    * Minimum Spanning Tree
    * Single Source Shortest Path
    * Topological Sort
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Graph_Breadth-FS)
- [ ] ~~[Coding Interview University](https://github.com/jwasham/coding-interview-university#graphs)~~