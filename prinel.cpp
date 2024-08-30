#include <bits/stdc++.h>
#include <queue>
#define BIG 100001
using namespace std;
class Task {
 public:
    void solve() {
        read_input();
        print_output(get_result());
    }

 private:
    int n, k;
	/* Vector folosit pentru memoizare*/
    vector<int> count;
    vector<int> target;
    vector<int> weight;
    vector<int> p;
	vector<int> dp;
    void read_input() {
        ifstream fin("prinel.in");
        fin >> n >> k;
		int value;
        for (int i = 0; i < n; i++) {
            fin >> value;
			target.push_back(value);
        }
        for (int i = 0; i < n; i++) {
            fin >> value;
			p.push_back(value);
        }
        count = vector<int>(*max_element(target.begin(),
		target.end()) + 1, BIG);
		count[1] = 0;
		weight = vector<int>(n);
		/* Vector folosit pentru implementarea solutiei 2 */
		dp = vector<int>(k+1);
        fin.close();
    }
	/* O functie care rezolva problema Rucsacului in O(N*W) */
    int knapsack_dp(vector<int> weights, vector<int> price, int n, int W) {
        for (int i = 1; i < n ; i++) {
            for (int w = W; w >= 0; w--) {
                if (weight[i - 1] <= w)
                    dp[w] = max(dp[w], dp[w - weight[i - 1]] + price[i - 1]);
            }
        }
        return dp[W];
		}
		/* Functie ce implementeaza solutia 2 */
		int count_minimum_operations2(int M, vector<int> &dp) {
        for (int iterator2 = 1; iterator2 <= M; iterator2++) {
			/* Partea ce se ocupa de optimizare */
            if (dp[iterator2] == BIG) {
                continue;
            }
			/* Se verifica divizorii pana la sqrt(curr_element) */
            for (int iterator1 = 1;
				iterator1 * iterator1 <= iterator2;
					iterator1++) {
				/* Daca este divizor */
                if (iterator2 % iterator1 == 0) {
					/* Daca valoarea curenta nu depaseste M */
                    if (iterator2 +
							iterator1 <= M) {
						/* Se afla numarul minim de operatii */
                        dp[iterator2 + iterator1] =
						min(dp[iterator2 + iterator1], dp[iterator2] + 1);
                    }
					/* Daca valoarea curenta nu depaseste M */
                    if (iterator2 + iterator2 / iterator1 <= M) {
						/* Se afla numarul minim de operatii */
                        dp[iterator2 + iterator2 / iterator1] =
						min(dp[iterator2 + iterator2 / iterator1], dp[iterator2] + 1);
                    }
                }
            }
        }
		/* Se returneaza rezultatul final */
		if (dp[M] == BIG) {
		return -1;
		} else {
		return dp[M];
		}
    }
	/* Functie ce implementeaza solutia 1 */
	/* Ideea se afla in README */
     int count_minimum_operations1(int n, int m) {
		    std::queue<int> queue;
			vector<bool> visited(BIG, false);
			queue.push(n);
			visited[n] = true;
			count[n] = 0;
			while (!queue.empty()) {
				int current = queue.front();
                queue.pop();
				for (int i = 1; i * i <= current; i++) {
					if (current % i == 0) {
						if (current + current / i <= m && !visited[current + current / i]) {
							queue.push(current + current / i);
							visited[current + current / i] = true;
							count[current + current / i] = count[current] + 1;
						}
						if (current + i <= m && !visited[current + i]) {
							queue.push(current + i);
							visited[current + i] = true;
							count[current + i] = count[current] + 1;
						}
					}
				}
				if (current == m) {
					return count[m];
				}
			}
			return -1;
		}

    void print_output(int result) {
        ofstream fout("prinel.out");
        fout << result;
        fout.close();
    }
    int get_result() {
		/* Se aplica functia pentru fiecare numar din vectorul target */
			for (int i = 0; i < n; i++) {
				/* Daca nu a mai fost calculat (memoizare) */
				if (count[target[i]] == BIG) {
					weight[i] = count_minimum_operations2(target[i], count);
					/* Decomenteaza pentru a rula solutia 1 (trebuie comentata solutia 2) */
					/* weight[i] = count_minimum_operations1(1,target[i]);
					if (weight[i] == -1) {
						weight[i] = k + 1; 
					}*/
				} else {
					/* Se foloseste valoarea deja calculata */
					weight[i] = count[target[i]];
				}
			}
			/* Se calculeaza suma tuturor elementelor din weight */
			int sum = accumulate(weight.begin(), weight.end(), 0);
			/* Se calculeaza suma tuturor punctelor */
			int price = accumulate(p.begin(), p.end(), 0);
			/* Daca suma nu depaseste k (se evita TLE-urile) */
			if (sum <= k) {
				/* Se returneaza suma tuturor preturilor */
				return price;
			} else {
				/* Se formeaza solutia problemei Rucsacului folosind datele obtinute */
				return knapsack_dp(weight, p, n + 1, k);
			}
		}
};

int main(int argc, char* argv[]) {
    auto* task = new (nothrow) Task();
    if (!task) {
        cerr << "Error!\n";
        return -1;
    }
    task->solve();
    delete task;
    return 0;
}
