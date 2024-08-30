import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Statistics {
	/* Valori ce vor fi folosite in ceea ce urmeaza */
	public static final int numChar = 27;
	public static final int offset = 97;

	static class ObjectProb implements Comparable<ObjectProb> {
		/* Lungimea unui cuvant */ 
		public int length;
		/* Frecventa unei litere din cuvantul respectiv */
		public int[] freq;
		/* Frecventa speciala de aparitie a unei litere din cuvant.Detalii in README */
		public int[] diff;
		public static int currChar;

		public ObjectProb(int length) {
			this.length = length;
			freq = new int[numChar];
			diff = new int[numChar];
		}
		/* Functia folosita pentru sortare */
		public int compareTo(ObjectProb m) {
			return m.diff[currChar] - this.diff[currChar];
		}
	}

	static class Task {
		public static final String INPUT_FILE = "statistics.in";
		public static final String OUTPUT_FILE = "statistics.out";
		int n, w;
		ObjectProb[] objs;
		int[] freqn;
		int maxim;

		public void solve() {
			readInput();
			writeOutput(getResult());
		}
		/* O functie care se ocupa de citire */
		private void readInput() {
			try {
				File file = new File(INPUT_FILE);
				BufferedReader br = new BufferedReader(new FileReader(file));
				n = Integer.parseInt(br.readLine());
				String s;
				objs = new ObjectProb[n];
				freqn = new int[numChar];
				for (int i = 0; i < n; i++) {
					s = br.readLine();
					ObjectProb obj = new ObjectProb(s.length());
					for (int j = 0; j < s.length(); j++) {
						/* Se calculeaza frecventele literelor din cuvant */
						obj.freq[s.charAt(j) - offset] += 1;
						/* Se determina frecventa globala a literei curente */
						freqn[s.charAt(j) - offset] += 1;
					}
					objs[i] = obj;
				}
				br.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			/* Un mic algoritm pentru a calcula diff[i] */
			maxim = Arrays.stream(freqn).max().getAsInt();
			maxim = maxim >> 1 + 1;
			int j = 0;
			for (int i = 0; i < n; i++) {
				int sum = 0;
				for (int k = 0; k < 27; k++) {
					/* Daca frecventa globala este relevanta (optimizare) */
					if (freqn[k] > maxim) {
						while (j != numChar) {
							if (j == k) {
								j++;
								continue;
							} else {
								sum += objs[i].freq[j];
							}
							j++;
						}
						/* Se determina diff-ul caracterului curent */
						objs[i].diff[k] = objs[i].freq[k] - sum;
						sum = 0;
						j = 0;
					}
				}
			}
		}

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		/* Functie care returneaza rezultatul final */
		private int getResult() {
			int count_words = 0;
			int total = 0;
			int[] aux = new int[numChar];
			int max;
			/* Se parcurge fiecare caracter */
			for (int i = 0; i < numChar; i++) {
				/* Daca frecventa globala este relevanta */
				if (freqn[i] > maxim) {
					/* Membru static -> modificarea se aplica fiecarui obiect */
					ObjectProb.currChar = i;
					/* Sorteaza in functie de caracterul curent 'i' */
					Arrays.sort(objs);
					for (ObjectProb iter : objs) {
						/* Se iau primele cuvinte pana cand 'total' devine negativ */
						total += iter.diff[i];
						/* Criteriul greedy */
						if (total > 0) {
							count_words++;
						} else {
							break;
						}
					}
					/* Se stocheaza valoarea si se reia procesul */
					aux[i] = count_words;
					count_words = 0;
					total = 0;
				}
			}
			/* Returneaza rezultatul final */
			max = Arrays.stream(aux).max().getAsInt();
			return (max == 0) ? -1 : max;
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}