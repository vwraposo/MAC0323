/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            (adendo) Exercise 2.4.18. Probabilidade Critica
//////////////////////////////////////////////////////////////////////////////*/

import edu.princeton.cs.algs4.*;

public class ProbCritica3D {

    // Dado N calcula recursivamente a Probabilidade critica de N;
    public static double Bisection (double a, double b, int N, int M) {
        double err = .0025;
        double m = (a + b) / 2;
        double p = Estimate.eval (N, m, M);
        if (Math.abs (p - 0.5) < err)
            return m;
        else if (p - 0.5 < 0)
            return Bisection (m, b, N, M);
        else
            return Bisection (a, m, N, M);
    }

    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        int M = Integer.parseInt (args[1]);
        double pC = Bisection (0.0, 1.0, N, M);
        StdOut.printf ("pC(%d) = %.5f\n", N, pC);
    }
}
