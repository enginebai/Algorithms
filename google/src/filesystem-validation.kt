/**
 * File System Validation Problem
 *
 * Validates if a given file system representation forms a valid forest structure.
 *
 * Validation Rules:
 * 1. No missing references - All child IDs must exist
 * 2. No cycles - No circular references
 * 3. No shared children - Each entity has at most one parent
 * 4. At least one root - Must have ≥1 entity with no parent
 * 5. No orphans - All entities must be reachable from roots
 * 6. Files cannot have children
 * 7. Valid type field - "file" or "directory"
 * 8. Required fields exist - id, type, name must be present
 */

// ============================================================================
// Data Models
// ============================================================================

data class Entity(
    val id: Int,
    val type: String,  // "file" or "directory"
    val name: String,
    val size: Int? = null,  // For files
    val children: List<Int>? = null  // For directories (list of EntityIDs)
)

data class ValidationResult(
    val isValid: Boolean,
    val errors: List<String> = emptyList()
) {
    override fun toString(): String {
        return if (isValid) {
            "✓ File system is VALID"
        } else {
            "✗ File system is INVALID\nErrors:\n" + errors.joinToString("\n") { "  - $it" }
        }
    }
}

// ============================================================================
// File System Validator
// ============================================================================

class FileSystemValidator(private val filesystem: Map<Int, Entity>) {

    private val errors = mutableListOf<String>()

    /**
     * Main validation function - returns detailed result
     */
    fun validate(): ValidationResult {
        errors.clear()

        // Handle empty filesystem
        if (filesystem.isEmpty()) {
            errors.add("File system is empty (no roots)")
            return ValidationResult(false, errors)
        }

        // Phase 1: Entity-level validation
        if (!validateEntities()) {
            return ValidationResult(false, errors)
        }

        // Phase 2: Reference validation + parent counting
        val parentCount = validateReferencesAndCountParents()
        if (parentCount == null) {
            return ValidationResult(false, errors)
        }

        // Phase 3: Structural validation (roots, cycles, reachability)
        if (!validateStructure(parentCount)) {
            return ValidationResult(false, errors)
        }

        return ValidationResult(true, emptyList())
    }

    /**
     * Simple validation function - returns boolean only
     */
    fun isValid(): Boolean {
        return validate().isValid
    }

    // ========================================================================
    // Phase 1: Entity-Level Validation
    // ========================================================================

    private fun validateEntities(): Boolean {
        for ((entityId, entity) in filesystem) {
            // Rule 8: ID consistency (entity.id should match key)
            if (entity.id != entityId) {
                errors.add("Entity ID mismatch: key=$entityId, entity.id=${entity.id}")
                return false
            }

            // Rule 8: Required fields (type and name)
            if (entity.name.isEmpty()) {
                errors.add("Entity $entityId has empty name")
                // Note: We allow empty names for this implementation, but could enforce
            }

            // Rule 7: Valid type
            if (entity.type !in listOf("file", "directory")) {
                errors.add("Entity $entityId has invalid type: '${entity.type}' (must be 'file' or 'directory')")
                return false
            }

            // Rule 6: Files cannot have children
            if (entity.type == "file") {
                if (entity.children != null && entity.children.isNotEmpty()) {
                    errors.add("File entity $entityId ('${entity.name}') has children: ${entity.children}")
                    return false
                }
            }
        }

        return true
    }

    // ========================================================================
    // Phase 2: Reference Validation
    // ========================================================================

    private fun validateReferencesAndCountParents(): Map<Int, Int>? {
        val parentCount = mutableMapOf<Int, Int>()

        for ((entityId, entity) in filesystem) {
            if (entity.children != null) {
                for (childId in entity.children) {
                    // Rule 1: No missing references
                    if (childId !in filesystem) {
                        errors.add("Entity $entityId ('${entity.name}') references non-existent child: $childId")
                        return null
                    }

                    // Count parents for Rule 3
                    parentCount[childId] = parentCount.getOrDefault(childId, 0) + 1

                    // Rule 3: No shared children
                    if (parentCount[childId]!! > 1) {
                        val childName = filesystem[childId]?.name ?: "unknown"
                        errors.add("Entity $childId ('$childName') has multiple parents (shared child)")
                        return null
                    }
                }
            }
        }

        return parentCount
    }

    // ========================================================================
    // Phase 3: Structural Validation
    // ========================================================================

    private fun validateStructure(parentCount: Map<Int, Int>): Boolean {
        // Step 1: Find roots
        val roots = findRoots(parentCount)

        // Rule 4: At least one root
        if (roots.isEmpty()) {
            errors.add("No roots found (all entities are children of something - likely a cycle)")
            return false
        }

        // Step 2: Check for cycles - start from ALL nodes (including orphaned components)
        val color = mutableMapOf<Int, NodeColor>()

        // Initialize all nodes as unvisited
        for (entityId in filesystem.keys) {
            color[entityId] = NodeColor.WHITE
        }

        // Check cycles in ALL nodes, not just reachable ones from roots
        // This ensures we detect cycles even in orphaned components
        for (entityId in filesystem.keys) {
            if (color[entityId] == NodeColor.WHITE) {
                if (hasCycleDFS(entityId, color)) {
                    return false  // Cycle detected (error already added)
                }
            }
        }

        // Step 3: Check reachability (orphans) - start from roots only
        val visited = mutableSetOf<Int>()
        for (root in roots) {
            dfsVisitForReachability(root, visited)
        }

        // Rule 5: No orphans - all entities must be reachable from roots
        if (visited.size != filesystem.size) {
            val orphans = filesystem.keys.filter { it !in visited }
            errors.add("Found ${orphans.size} orphan(s) - not reachable from any root: $orphans")
            return false
        }

        return true
    }

    private fun findRoots(parentCount: Map<Int, Int>): List<Int> {
        // Roots are entities that are not children of anyone
        return filesystem.keys.filter { it !in parentCount }
    }

    // ========================================================================
    // Cycle Detection using DFS with Three Colors
    // ========================================================================

    enum class NodeColor {
        WHITE,  // Unvisited
        GRAY,   // Currently visiting (in recursion stack)
        BLACK   // Finished visiting
    }

    /**
     * Cycle detection using DFS with three colors
     * This checks ALL nodes, including orphaned components
     */
    private fun hasCycleDFS(
        nodeId: Int,
        color: MutableMap<Int, NodeColor>
    ): Boolean {
        // If we encounter a gray node, we found a back edge (cycle)
        if (color[nodeId] == NodeColor.GRAY) {
            errors.add("Cycle detected involving entity $nodeId")
            return true
        }

        // If already processed, no need to revisit
        if (color[nodeId] == NodeColor.BLACK) {
            return false
        }

        // Mark as visiting (in recursion stack)
        color[nodeId] = NodeColor.GRAY

        // Visit all children
        val entity = filesystem[nodeId]!!
        if (entity.children != null) {
            for (childId in entity.children) {
                if (hasCycleDFS(childId, color)) {
                    return true
                }
            }
        }

        // Mark as finished
        color[nodeId] = NodeColor.BLACK
        return false
    }

    /**
     * Reachability check using DFS
     * This starts from roots only to find which nodes are reachable
     */
    private fun dfsVisitForReachability(nodeId: Int, visited: MutableSet<Int>) {
        if (nodeId in visited) return
        visited.add(nodeId)

        val entity = filesystem[nodeId]!!
        entity.children?.forEach { childId ->
            dfsVisitForReachability(childId, visited)
        }
    }

    // ========================================================================
    // Utility Methods
    // ========================================================================

    /**
     * Print the file system structure for debugging
     */
    fun printStructure() {
        if (filesystem.isEmpty()) {
            println("Empty file system")
            return
        }

        val parentCount = mutableMapOf<Int, Int>()
        for (entity in filesystem.values) {
            entity.children?.forEach { childId ->
                parentCount[childId] = parentCount.getOrDefault(childId, 0) + 1
            }
        }

        val roots = filesystem.keys.filter { it !in parentCount }

        println("File System Structure:")
        println("Total entities: ${filesystem.size}")
        println("Roots: ${roots.size}")
        println()

        for (root in roots) {
            printTree(root, "", true)
        }

        // Print orphans if any
        val visited = mutableSetOf<Int>()
        for (root in roots) {
            collectVisited(root, visited)
        }
        val orphans = filesystem.keys.filter { it !in visited }
        if (orphans.isNotEmpty()) {
            println("\nOrphans (not connected to any root):")
            for (orphan in orphans) {
                printTree(orphan, "", true)
            }
        }
    }

    private fun printTree(nodeId: Int, prefix: String, isLast: Boolean) {
        val entity = filesystem[nodeId] ?: return

        val marker = if (isLast) "└── " else "├── "
        val typeIcon = if (entity.type == "file") "📄" else "📁"
        val sizeInfo = if (entity.type == "file" && entity.size != null) " (${entity.size} bytes)" else ""

        println("$prefix$marker$typeIcon $nodeId: ${entity.name}$sizeInfo")

        if (entity.children != null && entity.children.isNotEmpty()) {
            val newPrefix = prefix + if (isLast) "    " else "│   "
            for ((index, childId) in entity.children.withIndex()) {
                val isLastChild = index == entity.children.size - 1
                printTree(childId, newPrefix, isLastChild)
            }
        }
    }

    private fun collectVisited(nodeId: Int, visited: MutableSet<Int>) {
        if (nodeId in visited) return
        visited.add(nodeId)

        val entity = filesystem[nodeId]
        entity?.children?.forEach { childId ->
            collectVisited(childId, visited)
        }
    }
}

// ============================================================================
// Test Cases
// ============================================================================

fun main() {
    println("=".repeat(70))
    println("File System Validation - Test Cases")
    println("=".repeat(70))

    // Test Case 1: Valid Forest (Multiple Roots)
    println("\n${"-".repeat(70)}")
    println("Test 1: Valid Forest with Multiple Roots")
    println("-".repeat(70))
    val fs1 = mapOf(
        1 to Entity(1, "directory", "root1", children = listOf(2, 3)),
        2 to Entity(2, "file", "file1.txt", size = 100),
        3 to Entity(3, "directory", "subdir", children = listOf(4)),
        4 to Entity(4, "file", "file2.txt", size = 200),
        5 to Entity(5, "directory", "root2", children = listOf(6)),
        6 to Entity(6, "file", "file3.txt", size = 50)
    )

    val validator1 = FileSystemValidator(fs1)
    validator1.printStructure()
    println("\n${validator1.validate()}")

    // Test Case 2: Invalid - Missing Reference
    println("\n${"-".repeat(70)}")
    println("Test 2: Invalid - Missing Reference")
    println("-".repeat(70))
    val fs2 = mapOf(
        1 to Entity(1, "directory", "root", children = listOf(2, 99)),
        2 to Entity(2, "file", "file1.txt", size = 100)
    )

    val validator2 = FileSystemValidator(fs2)
    println(validator2.validate())

    // Test Case 3: Invalid - Cycle
    println("\n${"-".repeat(70)}")
    println("Test 3: Invalid - Cycle (A → B → C → A)")
    println("-".repeat(70))
    val fs3 = mapOf(
        1 to Entity(1, "directory", "A", children = listOf(2)),
        2 to Entity(2, "directory", "B", children = listOf(3)),
        3 to Entity(3, "directory", "C", children = listOf(1))
    )

    val validator3 = FileSystemValidator(fs3)
    println(validator3.validate())

    // Test Case 4: Invalid - Shared Child
    println("\n${"-".repeat(70)}")
    println("Test 4: Invalid - Shared Child")
    println("-".repeat(70))
    val fs4 = mapOf(
        1 to Entity(1, "directory", "root1", children = listOf(3)),
        2 to Entity(2, "directory", "root2", children = listOf(3)),
        3 to Entity(3, "file", "shared.txt", size = 100)
    )

    val validator4 = FileSystemValidator(fs4)
    println(validator4.validate())

    // Test Case 5: Invalid - No Roots (Cycle)
    println("\n${"-".repeat(70)}")
    println("Test 5: Invalid - No Roots (All nodes in cycle)")
    println("-".repeat(70))
    val fs5 = mapOf(
        1 to Entity(1, "directory", "A", children = listOf(2)),
        2 to Entity(2, "directory", "B", children = listOf(1))
    )

    val validator5 = FileSystemValidator(fs5)
    println(validator5.validate())

    // Test Case 6: Invalid - Orphan
    println("\n${"-".repeat(70)}")
    println("Test 6: Invalid - Orphan Node")
    println("-".repeat(70))
    val fs6 = mapOf(
        1 to Entity(1, "directory", "root", children = listOf(2)),
        2 to Entity(2, "file", "file1.txt", size = 100),
        3 to Entity(3, "file", "orphan.txt", size = 50)
    )

    val validator6 = FileSystemValidator(fs6)
    validator6.printStructure()
    println("\n${validator6.validate()}")

    // Test Case 7: Invalid - File with Children
    println("\n${"-".repeat(70)}")
    println("Test 7: Invalid - File with Children")
    println("-".repeat(70))
    val fs7 = mapOf(
        1 to Entity(1, "directory", "root", children = listOf(2)),
        2 to Entity(2, "file", "file1.txt", size = 100, children = listOf(3)),
        3 to Entity(3, "file", "file2.txt", size = 50)
    )

    val validator7 = FileSystemValidator(fs7)
    println(validator7.validate())

    // Test Case 8: Valid - Empty Directory
    println("\n${"-".repeat(70)}")
    println("Test 8: Valid - Empty Directory")
    println("-".repeat(70))
    val fs8 = mapOf(
        1 to Entity(1, "directory", "root", children = listOf(2)),
        2 to Entity(2, "directory", "empty", children = emptyList())
    )

    val validator8 = FileSystemValidator(fs8)
    validator8.printStructure()
    println("\n${validator8.validate()}")

    // Test Case 9: Valid - Single Root, No Children
    println("\n${"-".repeat(70)}")
    println("Test 9: Valid - Single Root, No Children")
    println("-".repeat(70))
    val fs9 = mapOf(
        1 to Entity(1, "directory", "root", children = emptyList())
    )

    val validator9 = FileSystemValidator(fs9)
    validator9.printStructure()
    println("\n${validator9.validate()}")

    // Test Case 10: Valid - Single File as Root
    println("\n${"-".repeat(70)}")
    println("Test 10: Valid - Single File as Root")
    println("-".repeat(70))
    val fs10 = mapOf(
        1 to Entity(1, "file", "standalone.txt", size = 100)
    )

    val validator10 = FileSystemValidator(fs10)
    validator10.printStructure()
    println("\n${validator10.validate()}")

    // Test Case 11: Invalid - Self Reference
    println("\n${"-".repeat(70)}")
    println("Test 11: Invalid - Self Reference")
    println("-".repeat(70))
    val fs11 = mapOf(
        1 to Entity(1, "directory", "root", children = listOf(1))
    )

    val validator11 = FileSystemValidator(fs11)
    println(validator11.validate())

    // Test Case 12: Invalid - Invalid Type
    println("\n${"-".repeat(70)}")
    println("Test 12: Invalid - Invalid Type")
    println("-".repeat(70))
    val fs12 = mapOf(
        1 to Entity(1, "folder", "root", children = listOf(2)),  // "folder" is invalid
        2 to Entity(2, "file", "file1.txt", size = 100)
    )

    val validator12 = FileSystemValidator(fs12)
    println(validator12.validate())

    // Test Case 13: Valid - Complex Forest
    println("\n${"-".repeat(70)}")
    println("Test 13: Valid - Complex Forest with Multiple Trees")
    println("-".repeat(70))
    val fs13 = mapOf(
        1 to Entity(1, "directory", "root1", children = listOf(2, 3, 4)),
        2 to Entity(2, "file", "file1.txt", size = 100),
        3 to Entity(3, "directory", "subdir1", children = listOf(5, 6)),
        4 to Entity(4, "file", "file2.txt", size = 200),
        5 to Entity(5, "file", "file3.txt", size = 50),
        6 to Entity(6, "directory", "subdir2", children = emptyList()),

        10 to Entity(10, "directory", "root2", children = listOf(11)),
        11 to Entity(11, "file", "file4.txt", size = 300),

        20 to Entity(20, "file", "root3.txt", size = 150)
    )

    val validator13 = FileSystemValidator(fs13)
    validator13.printStructure()
    println("\n${validator13.validate()}")

    // Test Case 14: Invalid - Empty Filesystem
    println("\n${"-".repeat(70)}")
    println("Test 14: Invalid - Empty Filesystem")
    println("-".repeat(70))
    val fs14 = emptyMap<Int, Entity>()

    val validator14 = FileSystemValidator(fs14)
    println(validator14.validate())

    // Test Case 15: Valid - Deep Nesting
    println("\n${"-".repeat(70)}")
    println("Test 15: Valid - Deep Nesting (10 levels)")
    println("-".repeat(70))
    val fs15 = buildMap {
        put(1, Entity(1, "directory", "root", children = listOf(2)))
        for (i in 2..9) {
            put(i, Entity(i, "directory", "level$i", children = listOf(i + 1)))
        }
        put(10, Entity(10, "file", "deep.txt", size = 100))
    }

    val validator15 = FileSystemValidator(fs15)
    validator15.printStructure()
    println("\n${validator15.validate()}")

    // Test Case 16: Invalid - Orphaned Cycle (Edge case that demonstrates the fix)
    println("\n${"-".repeat(70)}")
    println("Test 16: Invalid - Orphaned Cycle (detects cycle, not just orphan)")
    println("-".repeat(70))
    val fs16 = mapOf(
        1 to Entity(1, "directory", "root", children = listOf(2)),
        2 to Entity(2, "file", "file.txt", size = 100),
        // Orphaned cycle: 3 → 4 → 3
        3 to Entity(3, "directory", "A", children = listOf(4)),
        4 to Entity(4, "directory", "B", children = listOf(3))
    )

    val validator16 = FileSystemValidator(fs16)
    validator16.printStructure()
    println("\n${validator16.validate()}")
    println("Note: Before fix, this would report 'orphans'. After fix, it correctly reports 'cycle'.")

    println("\n" + "=".repeat(70))
    println("All tests completed!")
    println("=".repeat(70))
}
