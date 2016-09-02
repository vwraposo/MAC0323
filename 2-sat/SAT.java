/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 25/05/2016
            Exercise 4.2.26 2-satisfiability
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class SAT {
    public static int N;
    public static int M;
    public static Digraph G;
    public static KosarajuSharirSCC K;
    public static boolean[] setted;
    public static boolean[] answer;
    private static Digraph R;
    private static Stack<Integer> s;
    private static int[] id;
    private static int count = 0;
    public static boolean[] marked;

    // dfs que coloca a ordem em uma pilha
    private static void dfs_s (int v) {
        if (!marked[v]) {
            marked[v] = true;
            for (int a : R.adj (v))
                dfs_s (a);
            s.push (v);
        }

    }

    // dfs que calcula as componentes fortemente conexas
    private static void dfs (int v) {
        if (marked[v]) return;
        marked[v] = true;
        id[v] = count;
        if (!setted[v]) {
            int compl = (v+N)%(2*N);
            setted[v] = setted[compl] = true;
            answer[v] = true;
            answer[compl] = false;
        }
        for (int a : G.adj (v))
            dfs (a);
    }

    public static void main (String[] args) {
        N = StdIn.readInt ();
        M = StdIn.readInt ();

        G = new Digraph (2*N);
        for (int i = 0; i < M; i++) {
            int x = StdIn.readInt ();
            int y = StdIn.readInt ();

            if (x < 0) 
                x = (Math.abs (x) - 1);
            else 
                x = x - 1 + N;
            if (y < 0)
                y = (Math.abs (y) - 1);
            else
                y = y - 1 + N;

            G.addEdge ((x+N)%(2*N), y);
            G.addEdge ((y+N)%(2*N), x);
        }

        s = new Stack<Integer> ();
        R = G.reverse ();

        // Descobre a pos ordem
        marked = new boolean[2*N];
        for (int i = 0; i < N; i++)
            if (!marked[i]) 
                dfs_s (i);

        // Descobre as componentes fortementes conexas
        setted = new boolean[2*N];
        marked = new boolean[2*N];
        answer = new boolean[2*N];
        id = new int[2*N];
        while (!s.isEmpty ()) {
            int v = s.pop (); 
            if (!marked[v]) {
                dfs (v);
                count++;
            }
        }

        //  Verificando se a expressao tem solucao 
        boolean ans = true;
        for (int i = 0; i < N; i++) 
            if (id[i] == id[N+i]) {
                ans = false;
                StdOut.println ("MENTIRA");
                break;
            }

        if (ans) {
            StdOut.println ("VERDADE");
            for (int i = N; i < 2*N; i++)
                if (answer[i])
                    StdOut.print ("1 ");
                else 
                    StdOut.print ("0 ");
            StdOut.println ();
        }



    }

}
