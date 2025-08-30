# Key Patterns and Approaches for Shortest Path Problems

Based on the problems in `problems/shortest-path-problems.md`, here is a summary of the key patterns and approaches for solving shortest path problems.

Shortest path problems involve finding the optimal route between nodes in a graph, considering edge weights, constraints, or multiple objectives. Different algorithms are optimal for different types of graphs and constraints.

### 1. Dijkstra's Algorithm

The most common shortest path algorithm for graphs with non-negative edge weights.

#### 1.1 Basic Dijkstra
*   **Core Idea:** Greedily select the closest unvisited vertex and relax all its neighbors.
*   **Key Components:**
    *   Priority queue (min-heap) to select minimum distance vertex
    *   Distance array to track shortest distances from source
    *   Relaxation: if `dist[u] + weight(u,v) < dist[v]`, update `dist[v]`
*   **Time Complexity:** O((V + E) log V) with binary heap
*   **When to Use:** Non-negative edge weights, single-source shortest paths
*   **Examples:** [743. Network Delay Time](../leetcode/743.network-delay-time.md), [1631. Path With Minimum Effort](../leetcode/1631.path-with-minimum-effort.md).

#### 1.2 Dijkstra Variants
*   **Multiple Objectives:** Modify state to include additional dimensions (e.g., path with minimum effort)
*   **Early Termination:** Stop when target is reached for single-target queries
*   **Path Reconstruction:** Maintain parent pointers to reconstruct actual paths

### 2. Bellman-Ford Algorithm

Handle graphs with negative edge weights (but no negative cycles).

*   **Core Idea:** Relax all edges V-1 times to find shortest paths.
*   **Key Properties:**
    *   Can detect negative cycles
    *   Works with negative edge weights
    *   Slower than Dijkstra but more versatile
*   **Time Complexity:** O(VE)
*   **When to Use:** Negative edge weights, negative cycle detection

### 3. SPFA (Shortest Path Faster Algorithm)

Optimized version of Bellman-Ford using queue-based processing.

*   **Core Idea:** Only relax edges from vertices whose distance was updated in previous iteration.
*   **Key Components:**
    *   Queue to store vertices with updated distances
    *   Only process vertices in queue (avoid unnecessary relaxations)
*   **Average Case:** Often faster than Bellman-Ford in practice
*   **Examples:** [1514. Path with Maximum Probability](../leetcode/1514.path-with-maximum-probability.md) (modified for probability maximization).

### 4. Grid-Based Shortest Paths

Special case of shortest paths on 2D grids with movement constraints.

#### 4.1 Uniform Weight Grid (BFS)
*   **Core Idea:** Use BFS when all moves have equal cost.
*   **Key Approach:**
    *   Treat grid as unweighted graph
    *   BFS guarantees shortest path in unweighted graphs
    *   Direction vectors for movement: [(0,1), (0,-1), (1,0), (-1,0)]
*   **Time Complexity:** O(rows × cols)

#### 4.2 Weighted Grid (Dijkstra)
*   **Core Idea:** Use Dijkstra when moves have different costs or there are elevation changes.
*   **Key Applications:**
    *   Elevation-based movement costs
    *   Different terrain types with varying costs
    *   Path constraints based on grid values
*   **Examples:** [1631. Path With Minimum Effort](../leetcode/1631.path-with-minimum-effort.md) (minimize maximum effort along path).

### 5. Constrained Shortest Paths

Shortest paths with additional constraints on the path properties.

#### 5.1 Limited Stops/Edges
*   **Core Idea:** Find shortest path with at most K edges or stops.
*   **Key Approach:**
    *   Extend state space to include number of stops/edges used
    *   State: (vertex, stops_used)
    *   Use BFS or modified Dijkstra with state expansion
*   **Examples:** [787. Cheapest Flights Within K Stops](../leetcode/787.cheapest-flights-within-k-stops.md).

#### 5.2 Multiple Objectives
*   **Core Idea:** Optimize multiple criteria simultaneously (distance, effort, cost).
*   **Key Approaches:**
    *   Modify state to track multiple objectives
    *   Define appropriate comparison function for priority queue
    *   May require different termination conditions

### 6. All-Pairs Shortest Paths

Find shortest paths between all pairs of vertices.

#### 6.1 Floyd-Warshall Algorithm
*   **Core Idea:** Use dynamic programming with intermediate vertices.
*   **Key Formula:** `dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])`
*   **Time Complexity:** O(V³)
*   **When to Use:** Dense graphs, need all-pairs distances, negative edges allowed

### 7. Special Graph Types

#### 7.1 DAG (Directed Acyclic Graph)
*   **Core Idea:** Use topological sorting to process vertices in dependency order.
*   **Key Advantage:** Linear time O(V + E) shortest paths
*   **Approach:**
    *   Topologically sort vertices
    *   Process vertices in topological order, relaxing outgoing edges

#### 7.2 Tree Shortest Paths
*   **Core Idea:** In trees, there's exactly one path between any two vertices.
*   **Key Techniques:**
    *   LCA (Lowest Common Ancestor) for distance queries
    *   DFS for single-source distances
    *   Faster than general graph algorithms

---

### Algorithm Selection Guide

| Graph Type | Algorithm | Time Complexity |
|------------|-----------|----------------|
| Unweighted | BFS | O(V + E) |
| Non-negative weights | Dijkstra | O((V + E) log V) |
| Negative weights | Bellman-Ford/SPFA | O(VE) avg |
| DAG | Topological + DP | O(V + E) |
| All-pairs | Floyd-Warshall | O(V³) |
| Grid (uniform) | BFS | O(rows × cols) |
| Grid (weighted) | Dijkstra | O(RC log(RC)) |

### Key Implementation Considerations

1. **Graph Representation:**
   - Adjacency list for sparse graphs
   - Adjacency matrix for dense graphs
   - Implicit representation for grids

2. **Priority Queue Choice:**
   - Binary heap for general use
   - Fibonacci heap for theoretical optimality (rarely practical)
   - Simple array for small, dense graphs

3. **State Space Design:**
   - Include all relevant information in state
   - Balance state size with problem requirements

4. **Termination Conditions:**
   - Single target: stop when target is processed
   - All targets: continue until all distances finalized
   - Constrained: may need different stopping criteria

### Common Optimizations

1. **Early Termination:** Stop when target is reached (single-target queries)
2. **Bidirectional Search:** Search from both source and target
3. **A* Search:** Use heuristics to guide search toward target
4. **Precomputation:** Cache results for repeated queries

### Summary of Key Approaches

1. **Choose Right Algorithm:** Match algorithm to graph properties (weights, cycles, density).

2. **Model the State Space:** Include all necessary information in vertex state.

3. **Handle Edge Cases:** Empty graphs, unreachable targets, single-vertex graphs.

4. **Optimize for Use Case:** Single-source vs all-pairs vs single-target queries.

5. **Consider Problem Constraints:** Time limits, memory constraints, and query patterns.

6. **Validate Results:** Ensure paths are valid and optimal for the given constraints.

Shortest path problems require careful analysis of graph properties and constraint requirements. The key is selecting the appropriate algorithm and modeling the state space correctly to capture all problem requirements while maintaining efficiency.