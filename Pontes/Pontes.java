/* /////////////////////////////////////////////////////////////////////////////
Nome: Victor Wichmann Raposo
Numero USP: 9298020
Data: 11/04/2016
            Deteccao de Pontes em grafos 
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class Pontes {
    public static Graph G;
    public static Digraph D;
    private static boolean[] marked;

    private static void dfs (int v, int ant) {
        if (marked[v]) return;
        marked[v] =true;
        for (int a : G.adj (v)) {
            if (a != ant)
                D.addEdge (v, a);
            dfs (a, v);
        }

    
    } 

    public static void main (String[] args) {
        int N = StdIn.readInt ();
        int M = StdIn.readInt ();
        G = new Graph (N);
        D = new Digraph (N);
        marked = new boolean[N];

        for (int i = 0; i < M; i++) {
            int v = StdIn.readInt ();
            int w = StdIn.readInt ();
            G.addEdge (v,w);
        }
        
        // Contruindo o grafo direcionado
        for (int i = 0; i < N; i++) 
            dfs (i, i);

        KosarajuSharirSCC K = new KosarajuSharirSCC (D);

        // Encontrando as Pontes
        for (int i = 0; i < N; i++) 
            for (int a : D.adj (i))
                if (K.id (a) != K.id (i)) 
                    StdOut.println (i + " " + a);

    
    }
}
