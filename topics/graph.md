# Graph
A graph `G = (V, E)`, consists of `V` (set of *vertices*) and `E` (*edges* of vertices). 

* There are *undirected* and *directed* graph, the pair `(x, y)` and `(y, x)` represent the same edge in a undirected graph, whereas, that are two different edges in directed graph, where `(x, y)` represents `x` (head) to `y` (tail).
* The graph can be *weighted* or *unweighted*, the edge has a *weight* in weighted graph, and the value can be *positive* or *negative*.
* The graph can be *cyclic* or *acyclic*, the graph has a cycle if there is a path from a vertex to itself.
* The graph can be *connected* or *disconnected*, the graph is connected if there is a path from every vertex to every other vertex. 
* For the edge `e = (x, y)`, we will say `e` is an *incoming* edge of `y` and an *outgoing* edge of `x`.
* The *in-degree* and *out-degree* of a vertex denotes the number of incoming and outgoing edges of the vertex. 

> We refer to *out-degree* when mentioned witout specified.

![Graph](../media/graph.png)

## Representation
There are three criteria to determine the graph representation:
1. Space complexity to store a graph.
2. Time complexity to determine whether a given edge exists in the graph.
3. Time complexity to find the adjacent vertices (neighbors) of a given vertex.

We can represent a graph `G = (V, E)` in the following ways:

### Adjacency List
In adjacency list, we use an array of linked lists (or array or list) to represent the graph, where each vertex is associated a list of its adjacent vertices by linked list.

```kotlin
// 1 -> 2 -> 4 -> 5 -> 6
val v1 = ListNode(1)
v1.next = ListNode(2)
v1.next?.next = ListNode(4)
...

// 2 -> 1 -> 4
val v2 = ListNode(2)
v2.next = ListNode(1)
...

// Using array of linked list
val adjacencyList = arrayOf(
    v1,
    v2,
    ...
)

// or using array of list
val verticesCount = 10
val graph = Array(verticesCount) { mutableListOf<Int>() }
// or using hash set
val graph = Array(verticesCount) { hashSetOf<Int>() }
graph[0].add(1)
graph[1].add(0) // for undirected graph
graph[1].contains(2) // O(|V|)

// or using 2D array
val graph2 = Array<IntArray>() // [[1,2,3],[0,2],[0,1,3],[0,2]]
```

* The adjacent vertices of each vertex in adjacency list are typically stored in an arbitrary order.
* We prefer adjacency list when the graph are *sparse*.
* We also can associate *weight* on the edge by storing the weight on the node of the adjacency list. (linked list node can attach extra properties)

#### Complexity
1. o

> |V| means the size of V.

### Adjacency Matrix
We define a `|V| x |V|` matrix `A` such that `A[i][j] = 1` if there is an edge, `0` otherwise. (Or we can use boolean)

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

```kotlin
val directedGraph = arrayOf(
    intArrayOf(0, 1, 0, 1, 1),
    intArrayOf(1, 0, 1, 1, 0),
    intArrayOf(0, 1, 1, 1, 0),
    intArrayOf(0, 0, 1, 0, 0),
    intArrayOf(1, 0, 0, 0, 1)
)

// Or we can use boolean array
val undirectedGraph = arrayOf(
    booleanArrayOf(false, true, false, true, true),
    booleanArrayOf(true, false, false, true, false),
    ...
)

// Or weighted graph
val directedGraph = arrayOf(
    intArrayOf(0, 8, 0, 0, 5),
    intArrayOf(8, 0, 3, 1, 0),
    intArrayOf(0, 3, 2,-7, 0),
    intArrayOf(0, 0, 7, 0, 0),
    intArrayOf(5, 0, 0, 0,-1)
)
```

* We prefer adjacency matrix when the graph are *dense*.
* We can update the edge or check the existence of edge in constant time.
* `A` is equal to the *transpose* of matrix `A` for undirected graph.
* We also can define `A[i][j] = w` for weighted graph.

#### Complexity
1. Space complexity to store a graph: `Θ(|V| ^ 2)`, the undirected graph has a symmetric matrix, it has additional space to store `(x, y)` and `(y, x)` of the same edge, some applications will store only in half to save memory or use *sparse* matrix.
2. Time complexity to determine whether a given edge exists in the graph: `O(1)`
3. Time complexity to find the adjacent vertices (neighbors) of a given vertex: `O(1)`

### Hash Tables
```kotlin
val graph: HashMap<Int, HashSet<Int>() = hashMapOf(
    1: setOf(2, 4, 5, 6),
    2: setOf(1, 4),
)
```

#### Complexity
1. Space complexity to store a graph: `Θ(|V| + |E|)`
2. Time complexity to determine whether a given edge exists in the graph: `O(1)`
3. Time complexity to find the adjacent vertices (neighbors) of a given vertex: `O(1)`

## Breadth-first Search (BFS)
Given a graph `G = (V, E)` (undirected or directed) and source `s`, we "discover" every vertex that is reachable from `s`. It visits all vertices at level `k` before visiting level `k + 1`. It computes the *distance* from `s` to each reachable vertex, and produces a *breadth-first tree* (shortest path) with root `s` with all reachable vertices.

For our algorithm, we store some properties to the vertex:
* To track the visit, we color each vertex "white" (not visited yet), "gray" (enqueue to visit next) and "black" (visited). (Here we use `VisitState` enum to represent)
* We also store the distance and it predecessor (parent) in the breadth-first tree.

```kotlin
enum class VisitState { NOT_VISIT, DISCOVERED, VISITED }

data class Node<T>(
    val data: T
) {
    // Initialize all vertices with color, inifinite distance and null predecessor.
    var visitState: VisitState = NOT_VISIT
    // Store the distance from source to this node (optional property)
    var distance: Int = Int.MAX
    // Store to construct the shortest path (optional property)
    var predecessor: Node<T>? = null
}

fun <T> breadthFirstSearch(graph: Map<Node<T>, Set<Node<T>>>, source: Node<T>) {
    // We define a queue for each vertex to visit next, and enqueue the source vertex.
    val queue = Queue.create<Node<T>>()
    source.visitState = DISCOVERED
    source.distance = 0
    source.predecessor = null
    queue.enqueue(source)

    while (!queue.isEmpty()) {
        val vertexToVisit = queue.dequeue()

        // Visit the current vertex
        vertexToVisit.visitState = VISITED

        // Do something with the vertex

        // Enque all its non-visiting adjacent vertices.
        val adjacentVertices = graph[vertexToVisit]
        adjacentVertices.forEach { v ->
            // This check makes sure that each vertex is visited at most once.
            if (v.visitState == NOT_VISIT) {
                v.visitState = DISCOVERED
                v.distance = vertexToVisit.distance + 1
                v.predecessor = vertexToVisit
                queue.enqueue(v)
            }
        }
    }
}
```

### Time Complexity
All vertices will be enqueued and dequeued at most once, it takes `O(|V|)` for all vertices. The adjacency list of each vertex is scanned only when the vertex is dequeued, it is scanned at most once, it takes `O(|E|)`, thus the total running time if `O(|V| + |E|)` (linear time, all vertices and edges are visited at most once).

## Depth-first Search (DFS)
For graph, We will discover all vertices (fully depth-first search) and construct depth-first search tree (forest), we use the similar color scheme (to BFS) and provides some timestapms while searching. Each vertices has two timestampes: *discover* (first discovered, and grayed) and *finish* (finishing examining its adjaceny list and blacken)

The color sheme is slightly different with BFS:
* White: Not visit yet.
* Gray: Discovered = **visited**.
* Black: Finished (finish examining it adjaceny list)

```kotlin
enum class VisitState { NOT_VISIT, VISITED, FINISHED }

// We use the similar data structure of Node from BSF.
data class Node<T>(
    val data: T
) {
    // As same as BFS
    var visitState: VisitState = NOT_VISIT
    var distance: Int = Int.MAX
    var predecessor: Node<T>? = null

    // For DFS, they are also optional properties
    var discoverTime: Int? = null
    var finishTime: Int? = null
}

var time = 0

// Used for topological sort
val topologicalSortLinkedList = LinkedList()

fun dfsAllVertices(graph: Map<Node<T>, Set<Node<T>>) {
    graph.key.forEach { vertex ->
        if (vertex.visitState == NOT_VISIT) {
            dfs(graph, vertex)
        }
    }
}

private fun dfs(graph: Map<Node<T>, Set<Node<T>>, source: Node<T>) {
    source.visitState = VISITED
    source.discoverTime = ++time

    // TODO: Do something with the vertex

    val adjacentVertices = graph[source]
    adjacentVertices.forEach { node ->
        if (node.visitState == NOT_VISIT) {
            node.parent = source
            dfs(graph, node)
        }
    }
    source.visitState = FINISHED
    source.finishTime = ++time

    // Insert onto a linked list for topological sort.
    topologicalSortLinkedList.insertFirst(source)
}
```

### Time Complexity
We visit each vertex once, which takes `O(|V|)`, and for each vertex, we visit all adjacent vertices once, which takes `O(|E|)`. We visit all vertices and edges once, therefore the running time of DFS is `Θ(|V| + |E|)`.

## Topological Sort
A *topological sort* of a directed acyclic graph (DAG) is a *linear ordering* of all vertices such that `(x, y)` which `x` appears before `y` in the ordering.

> 概括来说，给出一个有向图，把这个有向图转成线性的排序就叫拓扑排序。当然拓扑排序也要检测这个有向图是否有环，即存在循环依赖的情况，因为这种情况是不能做线性排序的。所以拓扑排序也是图论中判断有向无环图的常用方法。
>
> 所以当我们做拓扑排序的时候，应该优先找 入度为 0 的节点，只有入度为0，它才是出发节点。

It's most commonly used for job scheduling a sequence of jobs which has dependencies on the others. The jobs are represented by vertices and the edge from `x` to `y` if job `y` is dependent on `x` (`x` must be finished before `y`)

```kotlin
fun topologicalSort(graph: Map) {
    // 1. call DFS(graph) and compute finish time for every vertex
    // 2. When a vertex finished, insert to first of linked list
    // 3. Return this linked list.
}
```

In another way, you can run DFS first and sort the vertices by finish time descending order, it will be topological sort as well. The topological sort of the same graph has more than one results. (DFS generates more than one orders)

> Sample problem: [207. Course Schedule](../leetcode/207.course-schedule.md)

### Time Complexity
It takes `O(|V| + |E|)` as same as depth-first search, since it takes `O(1)` to insert first of linked list.

## Union Find
>> TODO: