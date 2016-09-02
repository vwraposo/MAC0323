/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Exercise 2.4.18 Percolation in three dimensions
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class BooleanMatrix3D {

    private BooleanMatrix3D (){ }

    // Dada uma matriz NxNxN imprime-a atraves de planos horizontais
    public static void print3D (boolean[][][] a) {
        int N = a.length;
        for (int i = 0; i < N; i++)
            StdArrayIO. print (a[i]);
    }

    // Dado tamanho N devolve uma matriz NxNxN com campos
    // abertos dada probabilidade p de estar aberto
    public static boolean[][][] random (int N, double p) {
        boolean[][][] a = new boolean[N][N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    a[i][j][k] = StdRandom.bernoulli (p);
        return a;
    }

    // Dado tamanho N le uma matriz NxNxN e devolve-a
    public static boolean[][][] readMatrix (int N) {
        boolean[][][] a = new boolean[N][N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    a[i][j][k] = StdIn.readBoolean ();
        return a;
    }
}
