
import edu.princeton.cs.algs4.*;

public class DistPlot {

    // Funcao que plota recursivamente a curva
    public static void curve (int x, double y, int n, int F, int M) {
        if (n > F) return;
        double f = RandomWalker.eval (n, M);
        // Grafico da Distancia
        StdDraw.setPenColor (StdDraw.BLACK);
        StdDraw.line (x, y, n, f);
        StdDraw.filledCircle (n, f, .005);
        // Grafico da Raiz
        StdDraw.setPenColor (StdDraw.RED);
        StdDraw.line (n-1, Math.sqrt (n-1), n, Math.sqrt (n));
        curve (n, f, n+1, F, M);
    }

    public static void main (String[] args) {
        int M = Integer.parseInt (args[0]);
        StdDraw.setXscale (0, 1000);
        StdDraw.setYscale (0, 50);
        curve (0, 0.0, 1, 1000, M);
    }
}
