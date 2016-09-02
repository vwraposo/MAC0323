/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Exercise 2.4.18 Percolation in three dimensions
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class PercPlot {

    // Funcao que plota recursivamente a curva
    public static void curve (int N, int M, double x0, double y0, double x1, double y1) {
        double gap = .01;
        double err = .0025;
        double xm = (x0 + x1) / 2;
        double ym = (y0 + y1) / 2;
        double fxm = Estimate.eval (N, xm, M);
        if (x1 - x0 < gap || Math.abs (ym - fxm) < err) {
            StdDraw.line (x0, y0, x1, y1);
            return;
        }
        curve (N, M, x0, y0, xm, fxm);
        StdDraw.filledCircle (xm, fxm, .005);
        curve (N, M, xm, fxm, x1, y1);
    }

    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        int M = Integer.parseInt (args[1]);
        PercPlot.curve (N, M, 0.0, 0.0, 1.0, 1.0);
    }
}
