# Zombie Grid Pathfinding - Problem Breakdown

A comprehensive breakdown of finding the safest shortest path in a zombie-infested grid.

---

## Problem Statement

You are trapped in a grid where some cells contain walls, some are occupied by zombies, and others are open paths. Your goal is to find the shortest path from the start point to the gate (exit), while staying as far away from the zombies as possible.

**Grid Symbols:**
- `S`: Start position
- `G`: Gate/Exit (destination)
- `Z`: Zombie (stay far away)
- `W`: Wall (impassable)
- `0`: Open path

**Movement Rules:**
- Move up, down, left, right (no diagonals)
- Cannot pass through walls
- Priority: Shortest path first, then maximize distance from zombies

---

## Step 1: Understand & Clarify

### Key Clarification: Two-Level Priority System

The most critical understanding is the **priority order**:

#### 🥇 Primary Objective: SHORTEST PATH
- Minimize path length (number of steps to reach G)
- This is the TOP priority

#### 🥈 Secondary Objective: ZOMBIE SAFETY (Tiebreaker only!)
- Among ALL paths with the SAME shortest length, pick the safest one
- This only matters when comparing paths of EQUAL length

### Example: Priority Resolution

**Scenario 1: Different Path Lengths**
```
Path A: distance = 10, very safe (far from zombies)
Path B: distance = 5, dangerous (close to zombies)

Answer: Path B ✓
Reason: Shorter path always wins (5 < 10)
```

**Scenario 2: Same Path Length (Tiebreaker)**
```
Path A: distance = 5, min zombie distance = 1 (dangerous)
Path B: distance = 5, min zombie distance = 4 (safe)

Answer: Path B ✓
Reason: Same length, so zombie distance breaks the tie (4 > 1)
```

### Inputs & Outputs

**Input:**
- 2D grid of characters: `S`, `G`, `Z`, `W`, `0`
- Dimensions: m × n

**Output:**
- List of coordinates representing the path from S to G
- Example: `[(0,0), (1,0), (2,0), (2,1), (2,2), (2,3)]`

### Distance Measurement

- **Manhattan distance** (number of steps along the grid)
- Respects walls (distance through walls is infinity)
- Can walk through zombie cells (but distance = 0, very dangerous)

### What is "Minimum Distance from Zombie" for a Path?

For a given path:
1. For each cell in the path, find distance to the CLOSEST zombie
2. The "safety score" is the MINIMUM of all those distances

**Example:**
```
Path cells:        (0,0) → (1,0) → (2,0) → (2,1) → (2,2) → (2,3)
Distance to nearest zombie:
                     3       2       3       4       5       1

Safety score = min(3, 2, 3, 4, 5, 1) = 1
```

**Why minimum?** It's our **bottleneck** - the closest we ever get to danger!

---

## Step 2: Think Through Examples

### Example Grid
```
  0   1   2   3
0 S   0   0   Z
1 0   W   0   0
2 0   0   0   G
```

### Possible Paths

**Path 1: Go Right Then Down**
```
S → 0 → 0 → (near Z)
            ↓
            0
            ↓
            G

Steps: (0,0)→(0,1)→(0,2)→(0,3)→(1,3)→(2,3)
Length: 5
Zombie distances: [3, 2, 1, 0, 1, 2]
Safety score: 0 (goes right next to zombie!)
```

**Path 2: Go Down Then Right**
```
S
↓
0
↓
0 → 0 → 0 → G

Steps: (0,0)→(1,0)→(2,0)→(2,1)→(2,2)→(2,3)
Length: 5
Zombie distances: [3, 4, 5, 4, 3, 2]
Safety score: 2 (safer!)
```

**Conclusion:** Both have length 5, but Path 2 is safer (2 > 0) → **Choose Path 2** ✓

---

## Step 3: Pattern Recognition

### Pattern 1: Shortest Path in Unweighted Grid → BFS ✓

**Standard BFS:**
- Finds shortest path in unweighted grid
- Explores level by level (guarantees shortest path)
- Time: O(m × n)
- Perfect for finding shortest path length!

### Pattern 2: Distance from All Cells to Nearest X → Multi-Source BFS ✓

**Multi-source BFS:**
- Start BFS from ALL zombies simultaneously
- Compute distance from every cell to nearest zombie
- Precompute once, use for all path evaluations

**Example: Zombie Distance Map**
```
Grid:
  0   1   2   3
0 S   0   0   Z
1 0   W   0   0
2 0   0   0   G

Zombie distance map (distance to nearest Z):
  0   1   2   3
0 3   2   1   0  ← zombie at (0,3)
1 4   ∞   2   1  ← wall at (1,1) has ∞
2 5   4   3   2
```

### Pattern 3: Multi-Objective Optimization → Modified BFS/Dijkstra ✓

Track **two metrics**:
1. Path length (primary)
2. Minimum zombie distance along path (secondary)

**Approach options:**
- Find all shortest paths, then compare safety
- Modified BFS tracking both metrics simultaneously
- Dijkstra with custom priority

---

## Step 4: Breaking Down the Solution

### Recommended Approach: Three-Phase Solution

#### Phase 1: Precompute Zombie Distances
```
Use multi-source BFS:
1. Start from ALL zombie positions
2. Expand outward, recording distance to each cell
3. Result: zombieDistance[row][col] = distance to nearest zombie
```

#### Phase 2: Find Shortest Path Length
```
Use standard BFS from S to G:
1. Queue starts with S
2. Explore neighbors level by level
3. When we first reach G, record the distance
```

#### Phase 3: Find Safest Path Among Shortest Paths
```
Use BFS with constraints:
1. Only explore paths of length ≤ shortest_length
2. For each path reaching G with shortest_length:
   - Calculate safety score (min zombie distance along path)
   - Track the safest path
3. Return the best path
```

**State we track:**
```kotlin
data class State(
    val position: (row, col),
    val pathLength: Int,
    val minZombieDist: Int,  // minimum zombie distance so far
    val path: List<(row, col)>
)
```

---

## Step 5: Detailed Algorithm Walkthrough

### Given Grid:
```
  0   1   2   3
0 S   0   0   Z
1 0   W   0   0
2 0   0   0   G
```

### Phase 1: Precompute Zombie Distances

**Multi-source BFS starting from all zombies:**

```
Initial: zombies = [(0,3)]
Queue: [(0,3, dist=0)]

Process (0,3, dist=0):
  Mark zombieDistance[0][3] = 0
  Add valid neighbors:
    - (0,2, dist=1) ← left
    - (1,3, dist=1) ← down

Queue: [(0,2, 1), (1,3, 1)]

Process (0,2, dist=1):
  Mark zombieDistance[0][2] = 1
  Add unvisited neighbors:
    - (0,1, dist=2)

Continue until all reachable cells processed...

Final zombieDistance:
  0   1   2   3
0 3   2   1   0
1 4   ∞   2   1
2 5   4   3   2
```

### Phase 2: Find Shortest Path Length

**Standard BFS from S(0,0) to G(2,3):**

```
Queue: [((0,0), dist=0)]
visited = {(0,0)}

Process (0,0):
  Neighbors: (0,1), (1,0)
  Queue: [((0,1), 1), ((1,0), 1)]

Process (0,1):
  Neighbors: (0,2) [skip visited and walls]
  Queue: [((1,0), 1), ((0,2), 2)]

Process (1,0):
  Neighbors: (2,0)
  Queue: [((0,2), 2), ((2,0), 2)]

... continue BFS ...

First time reaching G(2,3): distance = 5

shortest_length = 5
```

### Phase 3: Find Safest Path

**Modified BFS tracking (position, length, minZombieDist, path):**

```
Initial state:
  position: (0,0)
  length: 0
  minZombieDist: zombieDistance[0][0] = 3
  path: [(0,0)]

Queue: [State((0,0), 0, 3, [(0,0)])]

Process State((0,0), 0, 3, [(0,0)]):
  Neighbors: (0,1), (1,0)

  For (0,1):
    newLength = 1
    newMinZombieDist = min(3, zombieDistance[0][1]) = min(3, 2) = 2
    newPath = [(0,0), (0,1)]
    Add State((0,1), 1, 2, newPath)

  For (1,0):
    newLength = 1
    newMinZombieDist = min(3, zombieDistance[1][0]) = min(3, 4) = 3
    newPath = [(0,0), (1,0)]
    Add State((1,0), 1, 3, newPath)

Continue exploring...

When reaching G(2,3) with length=5:
  Record path and safety score

After exploring all paths of length 5:
  Path 1: Safety = 0 (dangerous)
  Path 2: Safety = 2 (safer)

Return Path 2: [(0,0)→(1,0)→(2,0)→(2,1)→(2,2)→(2,3)]
```

---

## Step 6: Optimization Strategies

### Optimization 1: State Pruning

**Problem:** Might reach same position with different safety scores

**Solution:** Track best safety score for each (position, pathLength) pair
```kotlin
val bestSafety = mutableMapOf<Pair<Position, Int>, Int>()

// When exploring new state:
if (bestSafety.containsKey(key) && bestSafety[key]!! >= newMinZombieDist) {
    continue  // Skip worse state
}
```

### Optimization 2: Early Termination with Dijkstra

**Use priority queue ordered by (pathLength, -minZombieDist):**
```kotlin
val pq = PriorityQueue<State>(
    compareBy({ it.pathLength }, { -it.minZombieDist })
)
```

**Benefit:** First path reaching G is guaranteed optimal (no need to explore all paths)

### Optimization 3: Cycle Prevention

**Avoid revisiting cells in current path:**
```kotlin
if (newPos in state.path) {
    continue  // Skip to prevent cycles
}
```

---

## Step 7: Alternative Approach - Single-Phase Dijkstra

Instead of three phases, use Dijkstra's algorithm with custom priority:

### Algorithm
```
1. Precompute zombie distances (same as before)
2. Use priority queue with (pathLength, -safety) ordering
3. For each state:
   - If reached goal, return path (guaranteed optimal)
   - Explore neighbors, tracking both metrics
   - Priority ensures we explore optimal paths first
```

### Comparison

| Approach | Phases | Complexity | Implementation |
|----------|--------|------------|----------------|
| Three-Phase BFS | 3 | O(m×n×L) worst case | Clearer, easier to debug |
| Single-Phase Dijkstra | 2 | O(m×n×log(m×n)) | More efficient, slightly complex |

Where L = shortest path length

**Recommendation:** Use three-phase for learning, Dijkstra for production

---

## Step 8: Edge Cases

### Edge Case 1: No Path Exists
```
Grid:
['S', 'W', 'W']
['W', 'W', 'G']

Result: Return empty list or null
```

### Edge Case 2: S or G Next to Zombie
```
Grid:
['S', 'Z']
['0', 'G']

Result: Path must go through danger, accept low safety score
```

### Edge Case 3: No Zombies
```
Grid:
['S', '0', '0']
['0', '0', 'G']

Result: All paths have same safety (infinity), return any shortest path
```

### Edge Case 4: S == G
```
Start is already at goal
Result: [(start_position)]
Path length: 0
```

### Edge Case 5: Multiple Zombies
```
Grid:
['S', '0', 'Z']
['Z', '0', '0']
['0', '0', 'G']

Result: Multi-source BFS handles naturally
Each cell gets distance to NEAREST zombie
```

### Edge Case 6: All Shortest Paths Go Through Zombies
```
Grid:
['S', 'Z', 'G']

Result: Must walk through zombie, safety = 0
Still return the shortest path
```

---

## Step 9: Complexity Analysis

### Time Complexity

**Phase 1: Zombie Distance (Multi-source BFS)**
- Visit each cell once: O(m × n)
- Process each cell's neighbors: O(4) per cell
- Total: **O(m × n)**

**Phase 2: Shortest Path Length (BFS)**
- **O(m × n)**

**Phase 3: Find Safest Path**
- Worst case: explore all paths of shortest length
- Number of shortest paths can be exponential in theory
- With pruning: **O(m × n × L)** where L = shortest path length
- In practice: much better with Dijkstra-style early termination

**Overall:** **O(m × n)** for typical grids

### Space Complexity
- Zombie distance map: O(m × n)
- BFS queue: O(m × n) worst case
- Path storage: O(L) where L = path length
- Best state tracking: O(m × n × L) worst case
- **Overall:** **O(m × n)**

---

## Summary: Complete Solution Strategy

### Three-Phase Approach

```
┌─────────────────────────────────┐
│ Phase 1: Precompute             │
│ Multi-source BFS from zombies   │
│ → zombieDistance[r][c]          │
│ Time: O(m × n)                  │
└────────────┬────────────────────┘
             │
             ▼
┌─────────────────────────────────┐
│ Phase 2: Find Shortest Length   │
│ Standard BFS from S to G        │
│ → shortest_length               │
│ Time: O(m × n)                  │
└────────────┬────────────────────┘
             │
             ▼
┌─────────────────────────────────┐
│ Phase 3: Find Safest Path       │
│ Modified BFS with constraints:  │
│ - Only paths of shortest_length │
│ - Track min zombie distance     │
│ - Return path with best safety  │
│ Time: O(m × n × L)              │
└─────────────────────────────────┘
```

### Key Data Structures

1. **zombieDistance[m][n]** - Precomputed distances
2. **State** - Tracks (position, pathLength, minZombieDist, path)
3. **BFS Queue** - For each phase
4. **bestSafety Map** - For pruning

### Core Insights

1. ✅ **Two-level priority:** Shortest path first, safety second
2. ✅ **Precompute zombie distances** once, reuse many times
3. ✅ **Multi-source BFS** efficiently finds distances to nearest zombie
4. ✅ **Safety score** = minimum distance along entire path
5. ✅ **State pruning** eliminates strictly worse paths
6. ✅ **Dijkstra variant** offers single-phase solution

### Implementation Checklist

- [ ] Implement multi-source BFS for zombie distances
- [ ] Implement standard BFS for shortest path length
- [ ] Implement modified BFS with state tracking
- [ ] Add state pruning optimization
- [ ] Handle edge cases (no path, no zombies, etc.)
- [ ] Add path reconstruction
- [ ] Test with various grid configurations

---

## Key Takeaways

### Priority Understanding
**Always remember:** Shortest path is PRIMARY, zombie safety is SECONDARY (tiebreaker only)

### Algorithm Choice
- **Three-phase BFS:** Clearer, easier to understand and debug
- **Dijkstra:** More efficient, single pass, slightly more complex

### Optimization Matters
- Precomputing zombie distances saves repeated calculations
- State pruning dramatically reduces search space
- Early termination with priority queue can avoid exploring all paths

### Real-World Applications
This problem pattern applies to:
- Robot navigation in hazardous environments
- Game AI pathfinding with danger zones
- Emergency evacuation route planning
- Network routing with risk assessment

---

## Practice Questions

1. What if we want to find top K safest paths (all with shortest length)?
2. How would the solution change if zombies can move?
3. What if diagonal movement is allowed?
4. How to handle weighted edges (different terrain costs)?
5. What if we want to maximize average zombie distance instead of minimum?

---

**Author's Note:** This problem beautifully combines multiple classic algorithms (BFS, multi-source BFS, Dijkstra) and demonstrates the importance of clearly understanding problem priorities before implementing a solution.
