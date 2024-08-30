import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Regele {
	static class Task {
		public static final String INPUT_FILE = "regele.in";
		public static final String OUTPUT_FILE = "regele.out";

		public static int INF = 10000007;
		/* Vectori utili in ceea ce va urma */
		int n, q;
		/* Distanta intre 2 orase */
		int[] distancePoint;
		int[] m;
		/* Matricea de vectori utilizata pentru tehnica Programarii Dinamice */
		int[][][] dp;
		/* Vector care retine costurile maxime pentru activarea a 'i' orase */
		int[] maxCost;
		int[] finalResults;

		public void solve() {
			readInput();
			getResult();
			writeOutput();
		}
		/* Functie care se ocupa de citire */
		private void readInput() {
			try {
				File file = new File(INPUT_FILE);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String input = br.readLine();
				n = Integer.parseInt(input);
				input = br.readLine();
				String[] number = input.split(" ");
				distancePoint = new int[n + 1];
				for (int i = 0; i < n - 1; i++) {
					/* Se calculeaza distantele folosind operatorul '-' */
					distancePoint[i] = Integer.parseInt(number[i + 1]) 
						- Integer.parseInt(number[i]);
				}
				/* Se foloseste aceasta egalitate pentru simplificarea implementarii */
				distancePoint[n - 1] = 0;
				input = br.readLine();
				q = Integer.parseInt(input);
				m = new int[q];
				input = br.readLine();
				/* Se retin negustorii */
				for (int i = 0; i < q; i++) {
					m[i] = Integer.parseInt(input);
					input = br.readLine();
				}
				dp = new int[n + 1][n + 1][2];
				maxCost = new int[n + 1];
				finalResults = new int[q];
				br.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		/* Functie care se ocupa de scriere */
		private void writeOutput() {
			try {
				BufferedWriter pw = new BufferedWriter(new FileWriter(OUTPUT_FILE));
				for (int i = 0; i < q; i++) {
					pw.write(String.valueOf(finalResults[i]));
					pw.newLine();
				}
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void getResult() {
			/* Se initializeaza matricea de vectori */
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= n; j++) {
					dp[i][j][1] = -Task.INF;
					dp[i][j][0] = -Task.INF;
				}
			}
			/* Cazuri de baza */
			dp[1][0][0] = 0;
			dp[1][1][1] = distancePoint[1];
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= n; j++) {
					dp[i][0][0] = 0;
					if (i > 0 && j > 0) {
						/* Se iau ambele cazuri (on/off) pentru orasul i-1 */
						dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1]);
						/* Primul termen din max: 
						Orasul i-1 este 'off' -> 
						se folosesc negustori pe rutele (i-1,i) si (i,i+1) */
						/* Al doilea termen din max:
						Orasul i-1 este 'on' -> se folosesc negustori pe ruta (i,i+1) */
						dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] + distancePoint[i - 1] 
						+ distancePoint[i],
								dp[i - 1][j - 1][1] + distancePoint[i]);
					}
				}
			}
			/* Se determina vectorul de costuri maxime */
			for (int i = 0; i <= n; i++) {
				maxCost[i] = Math.max(dp[n][i][0], dp[n][i][1]);
			}
			/* Se determina cel mai mare 'i' pentru care maxCost[i] <= m[j]*/
			/* Se putea efectua si o cautare binara (maxCost este sortat crescator) */
			int max = -1;
			for (int j = 0; j < q; j++) {
				for (int i = 0; i <= n; i++) {
					/* Mic algoritm 
					pentru a determina cel mai mare 'i' cu proprietatea mentionata mai sus */
					if (maxCost[i] <= m[j]) {
						if (i > max) {
							max = i;
						}
					}
				}
				/* Se salveaza rezultatul */
				finalResults[j] = max;
				max = -1;
			}
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}