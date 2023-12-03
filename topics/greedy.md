# Greedy
A *greedy algorithm* always makes the choice that looks best at the moment, it makes the *locally* optimal choice in the hope that it will lead to a *globally* optimal solution.

We shall consider a [dynamic programming](../topics/dynamic-programming.md) solution (including steps) and show that we can always make greedy choices to arrival the optimal solution.

## Steps of Greedy
1. Making a greedy choice, reduce the problems to a smaller subproblems.
2. Prove that making the greedy choice is always safe.
3. We conbime an optimal solutions to the subproblems with the greedy choice, we can arrive at the optimal solution to the original problem.

## Elements of Greedy
* **Greedy choice property**: A globally optimal solution can be arrived at by making a local optimal choice. We make the choice at each step that looks the best for now, not from the results from subproblems (DP), and then keep solving the subproblems arising after the choice is made.

> We often say that greedy algorithm never reconsiders its choices. However, DP will, it makes decisions based on all the decisions made in the previous subproblem.

* **Optimal substructure**: As same as [DP](../topics/dynamic-programming.md#elements-of-dynamic-programming)

## Fractional Knapsack Problem
The setup is as same as [0/1 Knapsack Problem](../topics/dynamic-programming.md#0-1-knapsack-problem), the difference is that the thief can take fractions of items. To solve this problem, we can use solve this problem by greedy strategy, we take the most valuable item first, which is the highest value per weight `v(i)/w(i)`. 

We calculate `v(i)/w(i)` for each item and sort by the value, pick the most valuable item first until the knapsack is full. It take `O(n * log n)` for sorting.

```kotlin
data class Item(
    val value: Float,
    val weight: Float
)

val items = arrayOf(
    Item(100f, 20f),
    Item(120f, 30f),
    Item(60f, 10f),
)
val capacity = 50f

fun knapsackFractional(): Float {
    items.sortByDescending { it.value.div(it.weight) }
    var leftWeights = capacity
    var totalValues = 0f
    items.forEach { item ->
        // Take the whole item
        if (item.weight <= leftWeights) {
            totalValues += item.value
            leftWeights -= item.weight
        } else {
            // Take the partial item to fill in the knapsack
            totalValues += leftWeights * (item.value.div(item.weight))
        }
    }
    return totalValues
}
```