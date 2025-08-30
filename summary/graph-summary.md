# Key Patterns and Approaches for Graph Problems

Based on the problems in `problems/graph-problems.md`, here is a summary of the key patterns and approaches for solving graph problems.

Graphs are versatile data structures consisting of nodes (vertices) and edges that connect them. Graph problems often involve traversal, pathfinding, connectivity, and optimization challenges.

### 1. Graph Traversal Patterns

The foundation of most graph algorithms is systematic traversal of nodes and edges.

#### 1.1 Depth-First Search (DFS)
*   **Core Idea:** Explore as far as possible along each branch before backtracking.
*   **Key Applications:**
    *   Connected components detection
    *   Cycle detection
    *   Path finding and counting
    *   Tree-like graph processing
*   **Implementation:** Recursive or stack-based iterative approach.
*   **Examples:** [733. Flood Fill](../leetcode/733.flood-fill.md), [200. Number of Islands](../leetcode/200.number-of-islands.md), [417. Pacific Atlantic Water Flow](../leetcode/417.pacific-atlantic-water-flow.md).

#### 1.2 Breadth-First Search (BFS)
*   **Core Idea:** Explore neighbors level by level, guarantees shortest path in unweighted graphs.
*   **Key Applications:**
    *   Shortest path in unweighted graphs
    *   Level-by-level processing
    *   Multi-source problems
*   **Implementation:** Queue-based iterative approach.
*   **Examples:** [127. Word Ladder](../leetcode/127.word-ladder.md), [994. Rotting Oranges](../leetcode/994.rotting-orange.md), [542. 01 Matrix](../leetcode/542.01-matrix.md).

### 2. Connected Components

Find and analyze connected regions in graphs.

#### 2.1 Basic Connected Components
*   **Core Idea:** Use DFS/BFS to identify all nodes reachable from a starting node.
*   **Key Approach:**
    *   Mark visited nodes to avoid revisiting.
    *   Count components by starting DFS from each unvisited node.
*   **Examples:** [200. Number of Islands](../leetcode/200.number-of-islands.md), [547. Number of Provinces](../leetcode/547.number-of-provinces.md).

#### 2.2 Boundary and Special Components
*   **Core Idea:** Handle components with special properties or boundary conditions.
*   **Key Approaches:**
    *   Start DFS from boundary nodes first to mark "safe" regions.
    *   Use different traversal strategies for different component types.
*   **Examples:** [130. Surrounded Regions](../leetcode/130.surrounded-regions.md), [1254. Number of Closed Islands](../leetcode/1254.number-of-closed-islands.md).

### 3. Shortest Path Algorithms

Find optimal paths between nodes in weighted or unweighted graphs.

#### 3.1 Single-Source BFS (Unweighted)
*   **Core Idea:** BFS naturally finds shortest paths in unweighted graphs.
*   **Key Features:**
    *   First time reaching a node gives shortest distance.
    *   Can handle multiple targets efficiently.
*   **Examples:** [1091. Shortest Path in Binary Matrix](../leetcode/1091.shortest-path-in-binary-matrix.md), [1926. Nearest Exit from Entrance in Maze](../leetcode/1926.nearest-exit-from-entrance-in-maze.md).

#### 3.2 Multi-Source BFS
*   **Core Idea:** Start BFS from multiple sources simultaneously to find shortest distances.
*   **Key Approach:**
    *   Initialize queue with all source nodes at distance 0.
    *   Process level by level to ensure optimal distances.
*   **Examples:** [994. Rotting Oranges](../leetcode/994.rotting-orange.md), [542. 01 Matrix](../leetcode/542.01-matrix.md), [1162. As Far from Land as Possible](../leetcode/1162.as-far-from-land-as-possible.md).

#### 3.3 Dijkstra's Algorithm
*   **Core Idea:** Find shortest paths in weighted graphs with non-negative edge weights.
*   **Key Components:**
    *   Priority queue to process nodes in order of current shortest distance.
    *   Relaxation of edges to update shortest distances.
*   **Examples:** [743. Network Delay Time](../leetcode/743.network-delay-time.md), [1631. Path With Minimum Effort](../leetcode/1631.path-with-minimum-effort.md).

### 4. Topological Sort

Order nodes in directed acyclic graphs (DAGs) such that dependencies are respected.

#### 4.1 Kahn's Algorithm (BFS-based)
*   **Core Idea:** Repeatedly remove nodes with in-degree 0 and update neighbors.
*   **Key Approach:**
    *   Calculate in-degrees for all nodes.
    *   Process nodes with in-degree 0, decrementing neighbors' in-degrees.
*   **Examples:** [207. Course Schedule](../leetcode/207.course-schedule.md), [210. Course Schedule II](../leetcode/210.course-schedule-ii.md).

#### 4.2 DFS-based Topological Sort
*   **Core Idea:** Use DFS post-order to get reverse topological order.
*   **Key Approach:**
    *   Mark nodes as visiting/visited to detect cycles.
    *   Add nodes to result when finishing DFS on them.

### 5. Graph Coloring and Bipartition

Assign properties to nodes such that adjacent nodes have different properties.

#### 5.1 Bipartite Graph Detection
*   **Core Idea:** Check if graph can be colored with two colors such that adjacent nodes have different colors.
*   **Key Approach:**
    *   Use BFS/DFS to assign colors alternately.
    *   If conflict arises, graph is not bipartite.
*   **Examples:** [785. Is Graph Bipartite?](../leetcode/785.is-graph-bipartite.md), [886. Possible Bipartition](../leetcode/886.possible-bipartition.md).

### 6. Advanced BFS Patterns

#### 6.1 Multi-State BFS
*   **Core Idea:** Each node can have multiple states, expanding the state space.
*   **Key Approach:**
    *   State includes position plus additional information (keys collected, obstacles removed).
    *   Use (position, state) as the node in BFS.
*   **Examples:** [1293. Shortest Path in a Grid with Obstacles Elimination](../leetcode/1293.shortest-path-in-a-grid-with-obstacles-elimination.md).

#### 6.2 Bidirectional BFS
*   **Core Idea:** Search from both source and target simultaneously to reduce search space.
*   **Key Approach:**
    *   Maintain two queues and visited sets.
    *   Stop when the search frontiers meet.

### 7. Union-Find (Disjoint Set Union)

Efficiently track connected components with dynamic connectivity queries.

*   **Core Operations:**
    *   `Find(x)`: Determine which set x belongs to.
    *   `Union(x, y)`: Merge sets containing x and y.
*   **Key Optimizations:**
    *   Path compression in Find operation.
    *   Union by rank/size for balanced trees.
*   **Applications:**
    *   Dynamic connectivity queries.
    *   Cycle detection in undirected graphs.
*   **Examples:** [547. Number of Provinces](../leetcode/547.number-of-provinces.md), [684. Redundant Connection](../leetcode/684.redundant-connection.md).

### 8. Tree-like Graph Problems

Handle graphs that have tree properties or can be converted to trees.

*   **Core Idea:** Leverage tree properties like no cycles, unique paths between nodes.
*   **Key Approaches:**
    *   Root the tree at an appropriate node.
    *   Use parent-child relationships for efficient processing.
*   **Examples:** [1376. Time Needed to Inform All Employees](../leetcode/1376.time-needed-to-inform-all-employees.md), [1443. Minimum Time to Collect All Apples in a Tree](../leetcode/1443.minimum-time-to-collect-all-apples-in-a-tree.md).

---

### Key Problem-Solving Framework

1. **Identify Graph Type:**
   - Directed vs Undirected
   - Weighted vs Unweighted
   - Grid vs General graph
   - Tree vs General graph

2. **Choose Traversal Method:**
   - DFS for deep exploration, connected components, cycle detection
   - BFS for shortest paths, level-by-level processing

3. **Handle State Management:**
   - What information needs to be tracked during traversal?
   - Visited arrays, distance arrays, parent pointers

4. **Consider Special Cases:**
   - Multiple sources/targets
   - Dynamic connectivity
   - Constraints on paths or movements

### Summary of Key Approaches

1. **Master Basic Traversals:** DFS and BFS are the building blocks of most graph algorithms.

2. **Understand When to Use Each:**
   - BFS for shortest paths and level-order processing
   - DFS for connectivity and deep exploration

3. **Handle Graph Representation:** Adjacency lists, matrices, or implicit graphs (grids).

4. **Manage Visited State:** Prevent cycles and infinite loops with proper visited tracking.

5. **Optimize for Problem Constraints:** Choose algorithms based on graph size, density, and query patterns.

6. **Practice Common Patterns:** Connected components, shortest paths, topological sort, and bipartition are frequently tested.

Graph problems often combine multiple techniques and require careful consideration of the problem constraints to choose the most efficient approach.