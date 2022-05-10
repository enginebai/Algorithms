# Greedy
A *greedy algorithm* always makes the choice that looks best at the moment, it makes the *locally* optimal choice in the hope that it will lead to a *globally* optimal solution.

We shall consider a [dynamic programming](../topics/dynamic-programming.md) solution (including steps) and show that we can always make greedy choices to arrival the optimal solution.

## Steps of Greedy
1. Cast the optimization problem as one in which we make a choice and are left with one subproblem to solve. (Top-down fassion: making a greedy choice, reduce the probelm to a smaller subproblem)
2. Prove that making the greedy choice is always safe.
3. We conbine an optimal solution to the subproblem with the greedy choice, we can arrive at the optimal solution to the original problem.

## Elements of Greedy
* **Greedy choice property**: A globally optimal solution can be arrived at by making a locally optimal choice. We make the choice at each step that looks the best for now, not from the results from subproblems (DP),  and then keep solving the subproblems arising after the choice is made.

> We often say that greedy algorithm never reconsiders its choices. However, DP will, it makes decisions based on all the decisions made in the previous subproblem.

* **Optimal substructure**: As same as [DP](../topics/dynamic-programming.md#elements-of-dynamic-programming)

## Fractional Knapsack Problem
The setup is as same as [0/1 Knapsack Problem](../topics/dynamic-programming.md#0-1-knapsack-problem), the difference is that the thief can take fractions of items. To solve this problem, we can use solve this problem by greedy strategy, we take the most valuable item first, which is the highest value per weight `v(i)/w(i)`. 

We calculate `v(i)/w(i)` for each item and sort by the value, pick the most valuable item first until the knapsack is full. It take `O(n * log n)` for sorting.

## Resources
- [ ] CLRS
- [ ] https://github.com/youngyangyang04/leetcode-master#%E8%B4%AA%E5%BF%83%E7%AE%97%E6%B3%95
- [ ] https://github.com/TSiege/Tech-Interview-Cheat-Sheet