/* /////////////////////////////////////////////////////////////////////////////
Nome: Victor Wichmann Raposo
Numero USP: 9298020
Data: 21/05/2016
Exercise:           No Reinado de MACOEZE
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class BattleForTheKingdom {
    public static EdgeWeightedDigraph G;
    public static DijkstraSP[] knights;
    public static DijkstraSP sorcerer;
    public static boolean[] notSafe;

    public static void main (String[] args) {
        int N = StdIn.readInt ();
        int M = StdIn.readInt ();
        int K = StdIn.readInt ();
        int S = N;

        G = new EdgeWeightedDigraph (N);
        notSafe = new boolean[N];
        for (int i = 0; i < M; i++) {
            int A = StdIn.readInt ();
            int B = StdIn.readInt ();
            int T = StdIn.readInt ();

            G.addEdge (new DirectedEdge (A, B, T));
        }

        knights = new DijkstraSP[K];
        for (int i = 0; i < K; i++) {
            int k = StdIn.readInt ();
            knights[i] = new DijkstraSP (G, k); 
        }

        int f = StdIn.readInt ();
        sorcerer = new DijkstraSP (G, f);

        for (int i = 0; i < K; i++)
            for (int j = 0; j < N; j++) {
                if (!notSafe[j])
                    if (sorcerer.distTo (j) <= knights[i].distTo (j)) {
                        notSafe[j] = true;
                        S--;
                    }
            }
        if (S == 0)
            StdOut.println ("DEMISE OF THE KINGDOM");
        else {
            StdOut.println ("VICTORY AND HAPPY EVER AFTER");
            StdOut.println (S);
            for (int i = 0; i < N; i++)
                if (!notSafe[i])
                    StdOut.print (i + " ");
            StdOut.println ();
        }

    }

}
