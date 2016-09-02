/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
             Exercise 1.6.15 - Hubs and Authorities
//////////////////////////////////////////////////////////////////////////////*/

public class RandomSurfer {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);     // number of moves
        int M = StdIn.readInt();              // number of pages  - ignore since M = N
        int N = StdIn.readInt();              // number of pages
        int H = Integer.parseInt (args[1]);
        int A = Integer.parseInt (args[2]);

        if (M != N) throw new RuntimeException("M does not equal N");

        // Read transition matrix.
        double[][] p = new double[N][N];     // p[i][j] = prob. that surfer moves from page i to page j
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                p[i][j] = StdIn.readDouble();


        int[] freq = new int[N];            // freq[i] = # times surfer hits page i

        // Start at page 0.
        int page = 0;

        for (int t = 0; t < T; t++) {

            // Make one random move.
            double r = Math.random();
            double sum = 0.0;
            for (int j = 0; j < N; j++) {
                // Find interval containing r.
                sum += p[page][j];
                if (r < sum) {
                    page = j;
                    break;
                }
            }
            freq[page]++;
        }


        // Print Hubs' page ranks
        StdOut.println ("Hubs:");
        for (int i = N-H-A; i < N-A; i++) {
            StdOut.printf("\t%8.5f", (double) freq[i] / T);
            StdOut.println();
        }

        //Print Authorities' page ranks
        StdOut.println ("Authorities:");
        for (int i = N-A; i < N; i++) {
            StdOut.printf("\t%8.5f", (double) freq[i] / T);
            StdOut.println();
        }
    }
}
