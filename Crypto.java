import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Crypto {
	static class Task {
		public static final String INPUT_FILE = "crypto.in";
		public static final String OUTPUT_FILE = "crypto.out";
		int n, l;
		String K;
		String S;
		/* Vectorul care se ocupa de tehnica Programarii Dinamice */
		long[][] dp;

		public void solve() {
			readInput();
			writeOutput(getResult());
		}
		/* Functie care se ocupa de citire */
		private void readInput() {
			try {
				File file = new File(INPUT_FILE);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String input = br.readLine();
				String[] number = input.split(" ");
				n = Integer.parseInt(number[0]);
				l = Integer.parseInt(number[1]);
				dp = new long[n + 1][l + 1];
				K = br.readLine();
				S = br.readLine();
				br.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		/* Functie care se ocupa de scriere*/
		private void writeOutput(long result) {
			try {
				BufferedWriter pw = new BufferedWriter(new FileWriter(OUTPUT_FILE));
				pw.write(String.valueOf(result));
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private long getResult() {
			/* Cazul de baza */
			dp[0][0] = 1;
			/* Un numar folosit pentru a reduce dimensiunea operatiilor */
			int mod = (int) (Math.pow(10, 9) + 7);
			/* Parcurgerea sirurilor */
			for (int i = 1; i <= K.length(); i++) {
				for (int j = 0; j <= S.length(); j++) {
					/* Daca ne aflam pe caracterul '?' (urmeaza decizia) */
					if (K.charAt(i - 1) == '?') {
						/* Nu se potriveste nimic */
						dp[i][j] = (S.chars().distinct().summaryStatistics().getCount() 
							* dp[i - 1][j]) % mod;
						if (j > 0) {
							/* Se potriveste caracterul de pe 'j' pe 'i' */
							dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % mod;
						}
						/* Daca ne aflam pe un caracter oarecare */
					} else {
						/* Cazul in care nu se potriveste litera de pe 'i' cu cea de pe 'j' */
						if (j > 0 && K.charAt(i - 1) != S.charAt(j - 1)) {
							dp[i][j] = dp[i - 1][j];
						} else {
							/* Cazul in care se potrivesc (urmeaza decizia) */
							/* Doresc sa nu il potrivesc */
							dp[i][j] = dp[i - 1][j];
							/* Doresc sa il potrivesc */ 
							if (j > 0) {
								dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % mod;
							}
						}
					}
				}
			}
			/* Se returneaza solutia problemei */
			return dp[K.length()][S.length()];
		}

	}

	public static void main(String[] args) {
		new Task().solve();
	}
}