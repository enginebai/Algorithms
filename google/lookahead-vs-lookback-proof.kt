/**
 * PROOF: Lookahead (i vs i+1) and Lookback (i-1 vs i) are IDENTICAL
 *
 * The confusion: "Should I use i-1 vs i or i vs i+1?"
 * The answer: BOTH COMPUTE THE EXACT SAME EDGES! Choose whichever is clearer.
 */

fun main() {
    val nums = intArrayOf(10, 20, 30, 40, 50)

    println("=".repeat(80))
    println("PROOF: Both approaches compute the same edges")
    println("=".repeat(80))
    println()
    println("Array: ${nums.joinToString(", ")}")
    println("This array has ${nums.size} elements and ${nums.size - 1} edges")
    println()

    // Show what edges exist
    println("The edges (differences) in this array:")
    println("  Edge 0: nums[1] - nums[0] = 20 - 10 = 10  (between index 0 and 1)")
    println("  Edge 1: nums[2] - nums[1] = 30 - 20 = 10  (between index 1 and 2)")
    println("  Edge 2: nums[3] - nums[2] = 40 - 30 = 10  (between index 2 and 3)")
    println("  Edge 3: nums[4] - nums[3] = 50 - 40 = 10  (between index 3 and 4)")
    println()
    println("Notice: There are 4 edges (one less than the number of elements)")
    println()

    println("=".repeat(80))
    println("APPROACH 1: Lookahead (i vs i+1)")
    println("=".repeat(80))
    println()

    for (i in 0 until nums.size - 1) {
        val diff = nums[i + 1] - nums[i]
        println("Loop iteration: i=$i")
        println("  Compute: nums[i+1] - nums[i] = nums[${i+1}] - nums[$i] = ${nums[i+1]} - ${nums[i]} = $diff")
        println("  Edge computed: Edge $i")
        println()
    }

    println("=".repeat(80))
    println("APPROACH 2: Lookback (i-1 vs i)")
    println("=".repeat(80))
    println()

    for (i in 1 until nums.size) {
        val diff = nums[i] - nums[i - 1]
        println("Loop iteration: i=$i")
        println("  Compute: nums[i] - nums[i-1] = nums[$i] - nums[${i-1}] = ${nums[i]} - ${nums[i-1]} = $diff")
        println("  Edge computed: Edge ${i-1}")
        println()
    }

    println("=".repeat(80))
    println("SIDE BY SIDE COMPARISON")
    println("=".repeat(80))
    println()
    println("Lookahead                          Lookback")
    println("─────────────────────────────────  ─────────────────────────────────")
    println("for (i in 0 until n-1)             for (i in 1 until n)")
    println()

    // Show them side by side
    val lookahead = mutableListOf<Triple<Int, String, Int>>()
    val lookback = mutableListOf<Triple<Int, String, Int>>()

    for (i in 0 until nums.size - 1) {
        val diff = nums[i + 1] - nums[i]
        lookahead.add(Triple(i, "nums[${i+1}] - nums[$i]", diff))
    }

    for (i in 1 until nums.size) {
        val diff = nums[i] - nums[i - 1]
        lookback.add(Triple(i, "nums[$i] - nums[${i-1}]", diff))
    }

    for (idx in 0 until lookahead.size) {
        val (iLeft, exprLeft, diffLeft) = lookahead[idx]
        val (iRight, exprRight, diffRight) = lookback[idx]

        println("i=$iLeft: $exprLeft = $diffLeft         i=$iRight: $exprRight = $diffRight")
    }

    println()
    println("Both compute: [10, 10, 10, 10]")
    println()

    println("=".repeat(80))
    println("THE KEY INSIGHT")
    println("=".repeat(80))
    println()
    println("Both approaches visit ALL ${nums.size - 1} edges exactly once!")
    println()
    println("The ONLY difference:")
    println("  - Lookahead: Variable 'i' refers to the LEFT element of the edge")
    println("  - Lookback:  Variable 'i' refers to the RIGHT element of the edge")
    println()
    println("But they compute the EXACT SAME SET of edges!")
    println()

    println("=".repeat(80))
    println("VISUAL REPRESENTATION")
    println("=".repeat(80))
    println()

    println("Array indices and edges:")
    println()
    println("    0    1    2    3    4   ← Element indices")
    println("   [10, 20, 30, 40, 50]")
    println("     └──┬ └──┬ └──┬ └──┬")
    println("      e0  e1  e2  e3        ← Edges")
    println()

    println("Lookahead iteration i:")
    println()
    println("    i=0  i=1  i=2  i=3      ← Loop variable 'i'")
    println("   [10, 20, 30, 40, 50]")
    println("     └──┬ └──┬ └──┬ └──┬")
    println("      e0  e1  e2  e3")
    println()
    println("  At i=0: compute edge between nums[0] and nums[1] → e0")
    println("  At i=1: compute edge between nums[1] and nums[2] → e1")
    println("  At i=2: compute edge between nums[2] and nums[3] → e2")
    println("  At i=3: compute edge between nums[3] and nums[4] → e3")
    println()

    println("Lookback iteration i:")
    println()
    println("         i=1  i=2  i=3  i=4 ← Loop variable 'i'")
    println("   [10, 20, 30, 40, 50]")
    println("     └──┬ └──┬ └──┬ └──┬")
    println("      e0  e1  e2  e3")
    println()
    println("  At i=1: compute edge between nums[0] and nums[1] → e0")
    println("  At i=2: compute edge between nums[1] and nums[2] → e1")
    println("  At i=3: compute edge between nums[2] and nums[3] → e2")
    println("  At i=4: compute edge between nums[3] and nums[4] → e3")
    println()

    println("See? Both compute e0, e1, e2, e3 - just with different 'i' values!")
    println()

    println("=".repeat(80))
    println("SO WHICH ONE SHOULD I USE?")
    println("=".repeat(80))
    println()
    println("Answer: BOTH ARE CORRECT! Choose based on what's clearer for your problem.")
    println()
    println("Use LOOKAHEAD when:")
    println("  ✓ Building sequences forward (like our problem)")
    println("  ✓ You think: \"Start here, what comes next?\"")
    println("  ✓ You're extending to the right")
    println()
    println("Use LOOKBACK when:")
    println("  ✓ Comparing with previous state")
    println("  ✓ You think: \"Process this element, how does it compare to what came before?\"")
    println("  ✓ DP-style solutions where you build from previous results")
    println()
    println("For our Good Arithmetic Sequences problem:")
    println("  → LOOKAHEAD is more natural because we're extending sequences forward")
    println()

    println("=".repeat(80))
    println("FINAL PROOF WITH CODE")
    println("=".repeat(80))
    println()

    // Prove they produce identical results
    val edgesLookahead = mutableListOf<Int>()
    for (i in 0 until nums.size - 1) {
        edgesLookahead.add(nums[i + 1] - nums[i])
    }

    val edgesLookback = mutableListOf<Int>()
    for (i in 1 until nums.size) {
        edgesLookback.add(nums[i] - nums[i - 1])
    }

    println("Edges from Lookahead:  $edgesLookahead")
    println("Edges from Lookback:   $edgesLookback")
    println()
    println("Are they equal? ${edgesLookahead == edgesLookback}")
    println()

    if (edgesLookahead == edgesLookback) {
        println("✅ PROVEN: Both approaches compute the exact same edges!")
    }

    println()
    println("=".repeat(80))
}
