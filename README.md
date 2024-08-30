# HW1_PA

An Algorithmic homework involvin Divide et Impera, Greedy and Dynamic Programming.

## Walsh
Obviously, it's noticeable that the problem supports decomposition into disjoint subproblems with similar structure.
This solution uses the Divide and Conquer technique and recursion (Conquer).
The idea consists of dividing the problem (matrix) into 4 subproblems (4 submatrices).
This problem solution has a small connection with the ZTraversal solution (same principle).
Of course, each 'if' branch in the function represents a 'submatrix':
First 'if' -> bottom-left submatrix
Second 'if' -> top-right submatrix
Third 'if' -> top-left submatrix
Fourth 'if' -> bottom-right submatrix. This submatrix is special because all elements are negated.
For this reason, the function is called with the 'res' argument negated (!res).
Obviously, the recursive search stops when it reaches the submatrix of dimension 1.
Moreover, the coordinates (x,y) of the sought element must be subtracted to reach the stop condition.
A 'Point' structure was created to model the elements obtained from the input file. 
These are stored in the 'points' vector.
Since the solution uses the Divide and Conquer paradigm, its complexity uses the relation T(n) = D(n)+S(n)+C(n).
In our case, the time complexity is T = O(log_base_4(2^N)*K) = O(K*N) (the get_walsh function has O(N)).

### Problems with Walsh:
-> There were no problems that required difficult debugging, the solution was written in 2 hours.

## Statistics
It's easily noticeable that this problem can be solved with Greedy algorithms.
The solution involves sorting the words in descending order, at each iteration, based on the frequency of occurrence of the current letter.
This 'frequency of occurrence of the current letter' is stored in the 'diff' vector of each 'objs[i]' at the corresponding position.
Also, this is determined as the difference between the frequency of the letter in the respective word and the sum of the frequencies of the other letters.
For example, for the word 'aabc' we have:
obj.freq['a'] = 2, obj.freq['b'] = 1 and obj.freq['c'] = 1. Moreover, obj.diff['a'] = obj.freq['a'] - (obj.freq['b'] + obj.freq['c'] ) = 0
Obviously, all these calculations are performed for letters that have relevant global frequencies (that's why the condition '(if frenq[..] > maxim)' is used).
This is meant to optimize the whole process.
After sorting, the first words are chosen and counted as long as the sum of the differences is positive (that 'total' must meet the condition).
This last part represents the 'greedy criterion' (the most favorable decision is chosen at each step, without globally evaluating the efficiency of the solution).
Obviously, at the end, the largest element from the 'aux' vector is returned (the one that stores the counts).
Since the solution uses the Greedy technique, the time complexity is O(27*N*log(N)) = O(N*log(N)) (that 27 indicates that sorting is done 27 times).
The space complexity is O(N).

### Problems with Statistics:
-> There were problems with the time limit, initially I wrote a very complicated and inefficient 'compareTo' function.
-> Obviously, I read various documents (KMP and Z search algorithms) and solutions to similar problems (codeforces, infoarena).
-> Debugging was acceptable as the solution was written in Java (exceptions are handled quickly), not in C++.

## Prinel
It's noticeable that the problem can be solved using the Dynamic Programming technique. Obviously, at first glance it looks a bit like the statement of the Knapsack problem.
It should be mentioned that I spent the most time on this problem due to TLE and WA errors (last tests).
I proposed 2 solutions for solving this, both use the DP algorithm of the knapsack at the end (obviously, this is the purpose).
These solutions calculate the minimum number of operations needed to transform a number N (in our case 1) into a number M (in our case target[i]).
The first solution applies a BFS on the tree obtained with the help of the divisors of number N. Of course, a 'visited' vector is also used to mark the visited numbers.
A queue is used for this BFS. Also, the answer is incremented at each iteration.
The divisors are traversed for each number in the queue and the sum is added -> (current_number + divisor) if it's not in the queue and (current_number + divisor) <= M
The time complexity is O((M-N)*sqrt(M)) (without the used memoization). Also, the space complexity is O(M).
'-1' is returned if the desired number cannot be reached.
The second solution uses dynamic programming to obtain the minimum number of operations. Obviously, I optimized the solution using memoization.
It's noticeable that the idea of finding the result is similar ('for' of size sqrt(number)), the 2 solutions differ by the data structures used.
The time complexity is O(N*sqrt(M)) (memoization). Moreover, the space complexity is O(M).
The final part of the problem uses the 'knapsack_dp' function which implements the solution of the Knapsack problem, if K < sum_min_op_numbers.
The last condition was used to avoid TLE. Also, the answer to the problem is in dp[N][K] (if K < sum_min_op_numbers).

### Problems with Prinel:
-> Problems with time limit, initially I didn't use memoization.
-> A lot of documentation was needed (geeks, stack), I also asked 2 female colleagues and a male colleague for some inspiration (I have inefficient ideas at the beginning).
-> Debugging was no longer trivial, at one point I had to use GDB + valgrind.

## Crypto
Again, it's noticeable that the problem can be solved using the Dynamic Programming technique. At first glance, I thought of this problem from codeforces:
https://codeforces.com/problemset/problem/1426/F
The idea of solving consists of 'step by step counting'.
More precisely, dp[i][j] means all the ways I could match the first 'i' from K with the first 'j' from S as a subsequence ('i'/'j' represent positions from the 2 strings).
The base case used is dp[0][0] = 1 (1 single character).
Time and space complexity for this solution: O(length(K)*length(S)).
I wrote certain details in comments.

### Problems with Crypto:
-> Finding the recurrence took a little time, but I took some ideas from similar problems (codeforces).
-> Debugging was okay.

## Regele
This problem can also be solved using the Dynamic Programming technique.
The distancePoint vector was considered, this represents the cost of the route (i,i+1).
For simplicity, distancePoint[n-1] = 0.
Also, dp[i][j][0 or 1] represents the maximum cost for activating 'j' cities out of 'i' (city 'i' is 'on' -> dp[i][j][1]).
The base cases are dp[1][0][0] = 0 and dp[1][1][1] = distancePoint[1]
Let the sequence dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] + distancePoint[i - 1] + distancePoint[i],dp[i - 1][j - 1][1] + distancePoint[i]);
distancePoint[i - 1] + distancePoint[i] is put in the first position because merchants need to be placed on (i-1,i) and (i,i+1) (city 'i-1' is 'off').
Only distancePoint[i] is put in the second position because there are merchants on (i-1,i) and more are needed on (i,i+1) (city 'i-1' is 'on').
The 'maxCost' vector retains all the maximum costs to put 'i' cities on 'on'.
The final part deals with finding the largest 'i' with the property that maxCost[i] <= m[j], where m represents a vector of merchants.
The time complexity is O(N^2 + Q * N).

### Problems with Regele:
-> As with Crypto, finding the recurrence relation.
-> Debugging was quite simple.

## References:
-> Geeks
-> StackOverflow
-> Codeforces
-> Infoarena
-> OCW

Also, I got small ideas from a friend to improve my solutions.
An interesting assignment.

Catalin-Alexandru Ripanu