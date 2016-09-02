/* /////////////////////////////////////////////////////////////////////////////
Nome: Victor Wichmann Raposo
Numero USP: 9298020
Data: 21/05/2016
Exercise:           No Reinado de MACOEZE
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class GreatBattleForTheKingdom {
    public static EdgeWeightedDigraph G;
    public static DijkstraSP[] knights;
    public static DijkstraSP sorcerer;
    // Tabelas de simbolos para usar quando a entrada e String
    public static SeparateChainingHashST<String, Integer> st;
    public static SeparateChainingHashST<Integer, String> ts;
    // Vetor que salva se uma cidade nao e segura 
    public static boolean[] notSafe;
    // Booleano que determina se esta recebendo como entrada String ou Inteiros
    private static boolean NAME = true;

    public static void main (String[] args) {
        int N = StdIn.readInt ();
        int M = StdIn.readInt ();
        int K = StdIn.readInt ();
        int S = N;

        G = new EdgeWeightedDigraph (N);
        notSafe = new boolean[N];
        st = new SeparateChainingHashST<String, Integer> (N);
        ts = new SeparateChainingHashST<Integer, String> (N);
        int count = 0;
        for (int i = 0; i < M; i++) {
            String s1 = StdIn.readString ();
            String s2 = StdIn.readString ();
            int T = StdIn.readInt (); 

            if (!st.contains (s1)) {
                st.put (s1, count);
                ts.put (count++, s1);
            }
            if (!st.contains (s2)) {
                st.put (s2, count);
                ts.put (count++, s2);
            }
            G.addEdge (new DirectedEdge (st.get (s1), st.get(s2), T));

        }
        knights = new DijkstraSP[K];
        for (int i = 0; i < K; i++) {
            String k = StdIn.readString ();
            knights[i] = new DijkstraSP (G, st.get (k)); 
        }

        String f = StdIn.readString ();
        sorcerer = new DijkstraSP (G, st.get (f));

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
                    if (NAME) 
                        StdOut.print (ts.get (i) + " ");
            StdOut.println ();
        }

    }

}
