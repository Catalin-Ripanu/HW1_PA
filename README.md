## This document covers an Algorithmic Assignment involving Divide and Conquer, Greedy, and Dynamic Programming techniques

## Walsh

The Walsh problem can be solved using the Divide and Conquer technique and recursion. The idea is to divide the problem (matrix) into 4 subproblems (4 submatrices) and solve them recursively.

The key aspects of the solution are:

- Each 'if' branch in the function represents a 'submatrix' (bottom-left, top-right, top-left, bottom-right).
- The bottom-right submatrix is special because all elements are negated, so the function is called with the 'res' argument negated.
- The recursive search stops when it reaches the submatrix of dimension 1.
- The coordinates (x,y) of the sought element must be subtracted to reach the stop condition.
- A 'Point' structure is used to model the elements obtained from the input file, stored in the 'points' vector.

The time complexity of this solution is O(K*N), where K is the number of operations and N is the size of the matrix.

### Problems with Walsh

- There were no significant problems that required difficult debugging, and the solution was written in 2 hours.

## Statistics

The Statistics problem can be solved using a Greedy approach. The key steps are:

1. Sort the words in descending order based on the frequency of occurrence of the current letter.
2. The 'frequency of occurrence of the current letter' is stored in the 'diff' vector of each 'objs[i]' at the corresponding position.
3. The 'diff' value is determined as the difference between the frequency of the letter in the respective word and the sum of the frequencies of the other letters.
4. After sorting, the first words are chosen and counted as long as the sum of the differences is positive (the 'total' must meet the condition).

The time complexity of this solution is O(N*log(N)), where N is the number of words. The space complexity is O(N).

### Problems with Statistics

- There were problems with the time limit, initially due to an inefficient 'compareTo' function.
- I read various documents (KMP and Z search algorithms) and solutions to similar problems (codeforces, infoarena) to improve the solution.
- Debugging was acceptable as the solution was written in Java (exceptions are handled quickly), not in C++.

## Prinel

The Prinel problem can be solved using Dynamic Programming. Two solutions were proposed:

1. **BFS-based solution**:
   - Applies a BFS on the tree obtained with the help of the divisors of number N.
   - Uses a 'visited' vector to mark the visited numbers.
   - Traverses the divisors for each number in the queue and adds the sum (current_number + divisor) if it's not in the queue and (current_number + divisor) <= M.
   - The time complexity is O((M-N)*sqrt(M)) (without memoization), and the space complexity is O(M).
   - Returns '-1' if the desired number cannot be reached.

2. **DP-based solution**:
   - Uses dynamic programming to obtain the minimum number of operations, optimized using memoization.
   - The time complexity is O(N*sqrt(M)) (with memoization), and the space complexity is O(M).
   - The final part uses the 'knapsack_dp' function to implement the Knapsack problem solution, if K < sum_min_op_numbers.

### Problems with Prinel

- Problems with time limit, initially the solution did not use memoization.
- Extensive documentation was needed (geeks, stack), and I also asked colleagues for inspiration.
- Debugging was not trivial, and I had to use GDB + valgrind at one point.

## Crypto

The Crypto problem can be solved using Dynamic Programming. The idea is to perform 'step-by-step counting', where `dp[i][j]` represents the number of ways to match the first `i` characters from `K` with the first `j` characters from `S` as a subsequence.

The time and space complexity for this solution is O(length(K)*length(S)).

### Problems with Crypto

- Finding the recurrence relation took a little time, but I took some ideas from similar problems (codeforces).
- Debugging was okay.

## Regele

The Regele problem can also be solved using Dynamic Programming. The key aspects of the solution are:

- The `distancePoint` vector represents the cost of the route (i, i+1).
- `dp[i][j][0 or 1]` represents the maximum cost for activating `j` cities out of `i` (city `i` is 'on' -> `dp[i][j][1]`).
- The base cases are `dp[1][0][0] = 0` and `dp[1][1][1] = distancePoint[1]`.
- The recurrence relation is:
  `dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] + distancePoint[i - 1] + distancePoint[i], dp[i - 1][j - 1][1] + distancePoint[i])`.
- The `maxCost` vector retains all the maximum costs to put `i` cities 'on'.
- The final part finds the largest `i` with the property that `maxCost[i] <= m[j]`, where `m` represents a vector of merchants.

The time complexity of this solution is O(N^2 + Q * N).

### Problems with Regele

- Finding the recurrence relation, as with the Crypto problem.
- Debugging was quite simple.

## References

- Geeks
- StackOverflow
- Codeforces
- Infoarena
- OCW
