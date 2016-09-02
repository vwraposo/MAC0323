/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Exercise 2.4.18 Percolation in three dimensions
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class Estimate {

    // Funcao que faz M testes e devolve a probabilidade da matriz NxNxN
    // percolar dada  probabilidade p
    public static double eval (int N, double p, int M) {
        int count = 0;
        for (int k = 0; k < M; k++) {
            boolean[][][] open = BooleanMatrix3D.random (N, p);
            if (Percolation3D.percolates (open))
                count++;
        }
        return (double) count / M;
    }

    
    public static void main (String[] args) {
        int    N = Integer.parseInt (args[0]);
        double p = Double.parseDouble (args[1]);
        int    M = Integer.parseInt (args[2]);
        double q = eval (N, p, M);
        StdOut.println (q);
    }
}
