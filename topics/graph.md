# Graph
A graph `G = (V, E)`, consists of `V` (set of *vertices*) and `E` (*edges* of vertices). 

There are some key properties of graph:
* There are *undirected* and *directed* graph, the pair `(x, y)` and `(y, x)` represent the same edge in a undirected graph, whereas, that are two different edges in directed graph, where `(x, y)` represents `x` (source) to `y` (destination).
* The graph can be *weighted* or *unweighted*, the edge has a *weight* in weighted graph, and the value can be *positive* or *negative*.
* The graph can be *cyclic* or *acyclic*, the graph has a cycle if there is a path from a vertex to itself.
* The graph can be *connected* or *disconnected*, the graph is connected if there is a path from every vertex to every other vertex. 
* For the edge `e = (x, y)`, we will say `e` is an *incoming* edge of `y` and an *outgoing* edge of `x`.
* The *in-degree* and *out-degree* of a vertex denotes the number of incoming and outgoing edges of the vertex. 

![Graph](../media/graph.png)

## Representation
We can represent a graph `G = (V, E)` in the following ways:

### Adjacency List
In adjacency list, we use an array of linked lists (or array, list, or set) to represent the graph, where each vertex is associated a list of its adjacent vertices by a collection of vertices.

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
    v1, // [1] -> [2, 4, 5, 6]
    v2, // [2] -> [1, 4]
    ...
)

// or using array of list
val verticesCount = ...
val graph = Array(verticesCount) { mutableListOf<Int>() }
// or using hash set
val graph = Array(verticesCount) { hashSetOf<Int>() }
graph[0].add(1) // edge 0 -> 1
graph[1].add(0) // for undirected graph
graph[1].contains(2) // O(|V|) for linked list, O(1) for hash set

// or using 2D array: index is the vertex, and the value is the adjacent vertices.
// 0 -> 1, 2, 3
// 1 -> 0, 2
// 2 -> 0, 1, 3
// 3 -> 0, 2
val graph2: Array<IntArray> = arrayOf(
    intArrayOf(1, 2, 3),    // node 0 has 1, 2, 3
    intArrayOf(0, 2),       // node 1 has 0, 2
    intArrayOf(0, 1, 3),    // node 2 has 0, 1, 3
    intArrayOf(0, 2)        // node 3 has 0, 2
)
```

* The adjacent vertices of each vertex in adjacency list are typically stored in an arbitrary order.
* We prefer adjacency list when the graph are *sparse*.
* We also can associate *weight* on the edge by storing the weight on the node of the adjacency list. `data class Node(val value: Int, val weight: Int)` (linked list node can attach extra properties)

#### Complexity
* Time complexity: `O(V + E)`, where `V` is the number of vertices and `E` is the number of edges.
* Space complexity: `O(V + E)`, where `V` is the number of vertices and `E` is the number of edges.

### Hash Tables
It's similar to adjacency list, but we use hash table to store the adjacent vertices of each vertex. It's efficient for *edge existence checks* (`O(1)` lookup) which is faster than adjacency list.

```kotlin
val graph: HashMap<Int, HashSet<Int>() = hashMapOf(
    1: setOf(2, 4, 5, 6),
    2: setOf(1, 4),
)
```

#### Complexity
* Time complexity: `O(V + E)`
* Space complexity: `O(V + E)`

### Adjacency Matrix
We define a `|V| x |V|` matrix `A` such that `A[i][j] = 1` (Or boolean) if there is an edge, `0` otherwise. Or `A[i][j] = w` for weighted graph.

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

It's simple to implement, but the space complexity is `Θ(V^2)`, which is not efficient when `V` is large. We prefer adjacency matrix when the graph are *dense* (`E ~= V^2` 稠密图：边的数量级和 `n^2` 相当的图。)
* We can update the edge or check the existence of edge in constant time.
* `A` is equal to the *transpose* of matrix `A` for undirected graph.

#### Complexity
* Time complexity: `O(V^2)`
* Space complexity: `O(V^2)`, the undirected graph has a symmetric matrix, it has additional space to store `(x, y)` and `(y, x)` of the same edge, some applications will store only in half to save memory or use *sparse* matrix.

### Edge List
We store the edges of the graph in a list, where each edge is a tuple `(u, v, weight)`. It's efficient for *edge-center* operations where the edges are processed sequentially, but inefficient for *Edge existence checks* (`O(|E|)` lookup).

```kotlin
val edges = listOf(
    Triple(1, 2, 8),    // edge 1 -> 2 with weight 8
    Triple(1, 4, 5),    // edge 1 -> 4 with weight 5
    Triple(1, 5, 6),    // edge 1 -> 5 with weight 6
    ...
)
```

#### Complexity
* Time complexity: `O(|V| * |E|)`
* Space complexity: `Θ(|E|)`

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

### Space Complexity
We use a queue to store the vertices to visit, it takes `O(|V|)` space.

### BFS Shortest Path
With explicit `source` and `destination`, we can use BFS to find the shortest path from `source` to `destination`.
```kotlin
val queue = ArrayDeque<Node>()
var distance = 0
val visited = HashSet<Node>()
queue.addLast(source)
visited.add(source)

while (queue.isNotEmpty()) {
    val size = queue.size
    repeat(size) {
        val node = queue.removeFirst()
        if (node == destination) return distance
        graph[node].forEach { v ->
            if (!visited.contains(v)) {
                queue.addLast(v)
                visited.add(v)
            }
        }
    }
}
return -1
```

Without explicit `source` and `destination`, we can use BFS to find the shortest path from `source` to `destination`.
```kotlin
val queue = ArrayDeque<Node>()
var distance = 0
val visited = HashSet<Node>()
queue.addLast(source)
visited.add(source)

while (queue.isNotEmpty()) {
    val size = queue.size
    repeat(size) {
        val node = queue.removeFirst()
        graph[node].forEach { v ->
            if (!visited.contains(v)) {
                queue.addLast(v)
                visited.add(v)
            }
        }
    }
}
return -1
```

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

### Space Complexity
We use a stack (or function call stack) to store the vertices to visit, it takes `O(|V|)` space.

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

## 0-1 BFS
It’s a specialized form of BFS used to solve shortest path in graphs where all edges weights are either `0` or `1`.

### When to Use
- Graph has `0` and `1` weight edges.
- Single source shortest path.
- Better than Dijkstra's algorithm: `O(V + E)` over `O(E log V)`.

### How It Works
We always process `0` weight edges first by enqueuing to the front. Otherwise, we enqueue to the back as normal BFS. This ensure the nodes reached with smaller cost (weight `0`) are processed first, like Dijkstra, but without priority queue.

- Use a deque to store the nodes.
- For each adjacent node:
    - If edge weight is `0`, enqueue to the front.
    - If edge weight is `1`, enqueue to the back.


```kotlin
fun zeroOneBFS(graph: Array<IntArray>, start: Int, n: Int): Array<IntArray> {
    val distance = Array(m) { IntArray(n) { Int.MAX_VALUE } }
    val deque: Deque<Int> = ArrayDeque()

    distance[start] = 0
    deque.addFirst(start)

    while (deque.isNotEmpty()) {
        val node = deque.removeFirst()
        for ((adjacent, weight) in graph[node]) {
            val newDistance = distance[node] + weight
            if (newDistance < distance[adjacent]) {
                distance[adjacent] = newDistance
                // Key points: We enqueue high weight first.
                if (weight == 0) deque.addFirst(adjacent)
                else deque.addLast(adjacent)
            }
        }
    }

    return distance
}
```

- **Time Complexity**: `O(V + E)`
- **Space Complexity**: `O(V + E)` for graph + deque


## Union Find (Disjoin Set Union, DSU)
_Union Find_ is a data structure to efficiently keep track of which elements belong to the same group (set, parent or root) and to merge groups. Union Find treats each node as a _disjoint set_ (part of a tree), each set is represented by its _root_ (parent) node.

There are two main operations:
1. `find(x)`: Find the root (representative of the set) of `x`.
2. `union(x, y)`: Merge the sets of `x` and `y` into one.

> 并查集（Union Find）结构是 *二叉树结构* 的衍生，用于高效解决无向图的连通性问题，可以在 `O(1)` 时间内合并两个连通分量，在 `O(1)` 时间内查询两个节点是否连通，在 `O(1)` 时间内查询连通分量的数量。

The pseudocode is as follows:
```js
function ConnectedComponent(G: {V, E}) {
    for each vertex v in G:
        do MakeSet(v)
    for each edge (u, v) in E:
        if FindSet(u) != FindSet(v)
            then Union(u, v)
}

function MakeSet(x) {
    parent[x] <- x
    rank[x] <- 0
}

function FindSet(x) {
    if x != parent[x]
        then parent[x] <- FindSet(parent[x])
    return parent[x]
}

function Union(x, y) {
    Link(FindSet(x), FindSet(y))
}

function Link(x, y) {
    if rank[x] > rank[y]
        then p[y] <- x
        else p[x] <- y
            if rank[x] == rank[y]
                then rank[y] <- rank[y] + 1
}
```

### Example
```js
// Graph:
a --- b --- c      e --- f      h

// Initial:
{a}, {b}, {c}, {e}, {f}, {h}

// Union: (a, b)
{a,b}   {c}   {e}   {f}   {h}
// Union: (b, c)
{a,b,c}       {e}   {f}   {h}
// Union: (e, f)
{a,b,c}       {e,f}       {h}

Final parent ≈ [0,0,0,3,3,5]
```

### Typical Use Cases
- Connectivity in graph: Check if two nodes are connected.
- Count connected components in graph.
- Cycle detection.
- Grouping / merging items.

### Optimizations
1. Path compression: In `find(x)`, we flatten the tree by making all nodes point directly to the root. This reduces the tree height and speeds up future queries.

> 讓 `find(x)` 操作中，把所有節點都直接連到 root，縮短未來查找時間。

```js
// Root of each set:
a
  \
   b // parent[b] = a
    \
     c // parent[c] = b
      \
       d // parent[d] = c

// After path compression: We change the parent of each node to `a`
        a
    /  /  \
   b  c    d // parent[b] == parent[c] == parent[d] == a
```

2. Union by rank: In `union(x, y)`, we always attach the smaller tree to the root of the larger tree. This keeps the tree balanced and speeds up future queries.

> 總是把較小（或較淺）集合接到較大（或較深）集合下，避免退化成鏈狀。

### Implementation
```kotlin
class UnionFind(n: Int) {
    // MakeSet(x)
    // We can use hash table if node count is not 0 ~ n - 1
    private val parent = IntArray(n) { it }
    private val size = IntArray(n) { 1 } // Used as rank, each set has 1 node initially.
    // Counting the number of connected components, we decrease when union two sets successfully.
    private var componentCount = n

    // FindSet(x)
    // This function will completely flattens the entire tree.
    fun find(x: Int): Int {
        if (x != parent[x]) {
            parent[x] = find(parent[x]) // Path compression
        }
        return parent[x]
    }

    // Iterative FindSet(x)
    // This function shortens the path about half way. (see below)
    fun find(x: Int): Int {
        var v = x
        while (v != parent[v]) {
            parent[v] = parent[parent[v]] // Update v.parent to grandparent
            v = parent[v]                 // Climb to grandparent
        }
        return v
    }

    // Union(x, y)
    fun union(x: Int, y: Int): Boolean {
        val parentX = find(x)
        val parentY = find(y)

        if (parentX == parentY) return false

        // Union by rank (size)
        // Link(x, y)
        if (size[parentX] < size[parentY]) { // If y is larger
            // Attach x to y
            parent[parentX] = parentY
            size[parentY] += size[parentX]
        } else { // x >= y, attach y to x
            parent[parentY] = parentX
            size[parentX] += size[parentY]
        }
        // Decrease the connected component count when union two sets successfully.
        componentCount--
        return true
    }

    // Some useful functions
    fun getComponentCount() = componentCount
    fun getSize(x: Int) = size[find(x)]
    fun isConnected(x: Int, y: Int) = find(x) == find(y)
}
```

### Complexity
- **Time Complexity**: `O(α(n))` for `find(x)` and `union(x, y)`, where `α(n)` is the inverse Ackermann function. It's near `O(1)` time.
- **Space Complexity**: `O(n)` for the parent and rank arrays.

### How iterative `find(x)` works?
Let's trace `find(3)`:
```js
parernt = [0, 0, 1, 2]
Tree structure:
           0 (root)
          /      
         1
        /
       2
      /
     3

Step 1: v = 3
    - parent[3] = 2 ≠ 3, so continue
    - parent[3] = parent[parent[3]] = parent[2] = 1 // path compression
    - v = parent[3] = 1

parent = [0, 0, 1, 1]
           0 (root)
          /      
         1 (v)
        / \
       2   3
    
Step 2: v = 1  
    - parent[1] = 0 ≠ 1, so continue
    - parent[1] = parent[parent[1]] = parent[0] = 0 (path compression)
    - v = parent[1] = 0

parent = [0, 0, 1, 1]
           0 (root) (v)
          /      
         1
        / \
       2   3

Step 3: v = 0
    - parent[0] = 0 = 0, so break
    - Return 0

```