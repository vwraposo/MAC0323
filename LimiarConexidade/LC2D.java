/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 27/04/2016
            Problema da Conexidade 2D
/ /////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;
import java.util.*;

public class LC2D {
    private static double D;
    private static int Q;      // Numero de quadrados em x e em y;
    private static ArrayList<Point2DIndex>[][] grid;
    private static DynamicWeightedQuickUnionUF uf; 
    
    public static void verify (Point2DIndex P, int qx, int qy) {
        for (int i = qx - 1; i <= qx + 1; i++)
            for (int j = qy - 1; j <= qy + 1; j++)
                if (i >= 0 && i <= Q && j >= 0 && j <= Q) 
                    for (Point2DIndex p : grid[i][j]) 
                        if (P.distanceS (p) <= D*D)
                            uf.union (P.index, p.index);
    }

    public static void main (String[] args) {
        D = Double.parseDouble (args[0]);
        Q = (int) (1 / (D));
        int T = Integer.parseInt (args[1]);
        int[] N  = new int[T];
        double avr = 0, var = 0;

        if (args.length == 3) { 
            long S = Long.parseLong (args[2]);
            StdRandom.setSeed (S);
        }


        grid = new ArrayList[Q+1][Q+1];
        for (int i = 0; i <= Q; i++)
            for (int j = 0; j <= Q; j++) 
                grid[i][j] = new ArrayList<Point2DIndex> ();

        for (int k = 0; k < T; k++) {  
            // Inicializacoes
            uf = new DynamicWeightedQuickUnionUF ();
            for (int i = 0; i <= Q; i++)
                for (int j = 0; j <= Q; j++) 
                    grid[i][j].clear (); 

            do {
                double x = StdRandom.uniform ();
                double y = StdRandom.uniform ();

                // Cordenadas do grid que o Ponto se localiza
                int qx = (int) (x / D);
                int qy = (int) (y / D);

                Point2DIndex P = new Point2DIndex (x, y, N[k]);
                uf.newSite ();
                verify (P, qx, qy);
                grid[qx][qy].add (P);

                N[k]++;
            } while (N[k] == 1 || uf.count () != 1);
            avr += N[k];
            var += Math.pow (N[k], 2);
        }
        avr = avr / T;
        var = (var / T) - avr*avr; 
        StdOut.println (D + " " + avr);
//        StdOut.println (avr + " " + var);
    }
}
