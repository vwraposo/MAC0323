/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Exercise 2.4.18 Percolation in three dimensions
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class Percolation3D {

    // Dada uma matriz NxNxN com campos abertos, devolve uma matriz NxNxN
    // de campos alcancaveis pelo topo
    public static boolean[][][] flow (boolean[][][] open) {
        int N = open.length;
        boolean[][][] full = new boolean[N][N][N];
        for (int j = 0; j < N; j++)
            for (int k = 0; k < N; k++)
                flow (open, full, 0, j, k);
        return full;
    }

    // Determina os campos alcancaveis pelo topo usando DFS
    public static void
    flow (boolean[][][] open, boolean[][][] full, int i, int j, int k) {
        int N = open.length;

        // base cases
        if (i < 0 || i >= N) return;
        if (j < 0 || j >= N) return;
        if (k < 0 || k >= N) return;
        if (!open[i][j][k]) return;
        if (full[i][j][k]) return;

        //mark i, j, k as full
        full[i][j][k] = true;

        flow (open, full, i+1, j, k); // down
        flow (open, full, i-1, j, k); // up
        flow (open, full, i, j+1, k); // right
        flow (open, full, i, j-1, k); // left
        flow (open, full, i, j, k+1); // front
        flow (open, full, i, j, k-1); // back
    }

    // Dada uma matriz NxNxN de campos abertos determina se ela e percolavel
    public static boolean percolates (boolean[][][] open) {
        int N = open.length;
        boolean[][][] full =  flow (open);
        for (int j = 0; j < N; j++)
            for (int k = 0; k < N; k++)
                if (full [N-1][j][k]) return true;
        return false;
    }

    // test client
    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        boolean[][][] open = BooleanMatrix3D.readMatrix (N);
        StdOut.println (percolates (open));

    }
}
