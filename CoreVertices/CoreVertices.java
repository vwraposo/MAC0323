/* /////////////////////////////////////////////////////////////////////////////
Nome: Victor Wichmann Raposo
Numero USP: 9298020
Data: 11/04/2016
         Exercise 4.2.35 Core Vertices
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class CoreVertices {

    public static Digraph G;
    public static Digraph GR;
    public static Stack<Integer> s;
    public static boolean[] marked;
    public static int[] id;
    public static int count = 0;
    public static boolean[] degree0;

    // dfs que coloca a ordem em uma pilha
    public static void dfs (Digraph D, int v) {
        if (marked[v]) return;
        marked[v] = true;
        s.push (v);
        for (int a : D.adj (v))
            dfs (D, a);
    }

    // dfs que calcula as componentes fortemente conexas
    public static void dfs_S (Digraph D, int v) {
        if (marked[v]) return;
        marked[v] = true;
        id[v] = count;
        for (int a : D.adj (v))
            dfs_S (D, a);
    }

    public static void main (String[] args) {
        int N = StdIn.readInt ();
        int M = StdIn.readInt ();
        G = new Digraph (N);

        // Get Graph
        for (int i = 0; i < M; i++) {
            int v = StdIn.readInt ();
            int w = StdIn.readInt ();
            G.addEdge (v, w);
        }

        GR = G.reverse ();

        // Descobre a pos ordem
        s = new Stack<Integer> ();
        marked = new boolean[M];
        for (int i = 0; i < N; i++)
            if (!marked[i]) 
                dfs (GR, i);

        // Descobre as componentes fortementes conexas
        marked = new boolean[M];
        id = new int[N];
        while (!s.isEmpty ()) {
            int v = s.pop (); 
            if (!marked[v]) {
                dfs_S (G, v);
                count++;
            }
        }
        degree0 = new boolean[count];
        Arrays.fill (degree0, true);

        // Descobre se ha mais de uma componente com grau de entrada 0
        for (int i = 0; i < N; i++)
            for (int a : G.adj (i))
                if (id[i] != id[a])
                    degree0[id[a]] = false;
        
        int ans = 0;
        for (int i = 0; i < count; i++)
           if (degree0[i]) ans++;
            
        if (ans == 1) {
            for (int i = 0; i < N; i++) {
                if (id[i] == count - 1)
                    StdOut.print (i + " "); 
            }
            StdOut.println ();
        }


    }
}
