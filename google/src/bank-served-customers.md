```
Maximum Consecutive Customers Served
You are given:

An initial bank balance S (non-negative integer).
A linear array T[0..N-1] of customer transactions.

Each T[i] is an integer:
T[i] > 0 means the customer withdraws T[i] units from the bank.
T[i] < 0 means the customer deposits |T[i]| units into the bank.
The bank may choose any starting index k (0 ≤ k < N) and must then serve customers consecutively in order k, k+1, k+2, ... without skipping anyone, stopping at the first customer that would cause the balance to go negative.
When serving customer i, the balance updates as:

balance := balance - T[i]
(Withdrawals decrease the balance; deposits increase it since T[i] is negative.)
Goal: Return the maximum number of consecutive customers the bank can serve (over all choices of starting index) such that the balance never becomes negative at any time during service.
```

The longest subarray which sum is positive
```kotlin
fun getMaxServedConsumers(transactions: IntArray, initialBalance: Int): Int {
    val maxConsumers = 0
    var balance = initialBalance
    var left = 0
    for (right in transactions.indices) {
        balance -= transactions[right]

        while (balance < 0 && left <= right) {
            balance += transactions[left]
            left++
        }
        maxConsumers = maxOf(maxConsumers, right - left + 1)
    }

    return maxConsumers
}
```

   L
            R

B = 3
max = 2
1, -3, 5, -2, 1
           L
              R