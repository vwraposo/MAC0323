import edu.princeton.cs.algs4.*;

public class DistPlot {

    // Funcao que plota recursivamente a curva
    public static void curve (double D, double avr) {
        if (StdIn.isEmpty ()) return;
        double newD = StdIn.readDouble ();
        double newAvr = StdIn.readDouble ();
        // Grafico da Distancia
        StdDraw.setPenColor (StdDraw.BLACK);
        StdDraw.line (D, avr, newD, newAvr);
        /* Grafico da Raiz
        StdDraw.setPenColor (StdDraw.RED);
        StdDraw.line (x, Math.pow (2, -x+5), x+0.01, Math.pow (2, -(x+0.01+5)));*/
        curve (newD, newAvr);
    }

    public static void main (String[] args) {
        StdDraw.setXscale (0, 1);
        StdDraw.setYscale (0, 800);
        double D = StdIn.readDouble ();
        double avr = StdIn.readDouble ();
        curve (D, avr);
        StdDraw.setPenColor (StdDraw.RED);
        StdDraw.line (0.5, -1, 0.5, 3000);
    }
}
