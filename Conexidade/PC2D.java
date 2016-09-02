/* //////////////////////////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 27/04/2016
            Problema da Conexidade 2D
////////////////////////////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;
import java.util.*;

public class PC2D {
    private static int N; 
    private static double D;
    private static int Q;      // Numero de quadrados em x e em y;
    private static ArrayList<Point2DIndex>[][] grid;
    private static WeightedQuickUnionUF uf; 
    
    public static void verify (Point2DIndex P, int qx, int qy) {
        for (int i = qx - 1; i <= qx + 1; i++)
            for (int j = qy - 1; j <= qy + 1; j++)
                if (i >= 0 && i < Q && j >= 0 && j < Q) 
                    for (Point2DIndex p : grid[i][j]) 
                        if (P.distanceS (p) <= D*D)
                            uf.union (P.index, p.index);
    } 
    
    public static void main (String[] args) {
        N = StdIn.readInt ();
        D = StdIn.readDouble ();
        Q = (int) (1 / (D));
        uf = new WeightedQuickUnionUF (N);
        grid = new ArrayList[Q][Q];
       
        for (int i = 0; i < Q; i++)
            for (int j = 0; j < Q; j++) 
                grid[i][j] = new ArrayList<Point2DIndex> ();

        for (int i = 0; i < N; i++) {
            double x = StdIn.readDouble ();
            double y = StdIn.readDouble ();
           
            // Cordenadas do grid que o Ponto se localiza
            int qx = (int) (x / D);
            int qy = (int) (y / D);

            // Incluindo os pontos (x,1) para todo x em [0,1] 
            if (x == 1) qx--;
            // Incluindo os pontos (1, y) para todo y em [0,1] 
            if (y == 1) qy--;

            Point2DIndex P = new Point2DIndex (x, y, i);
            verify (P, qx, qy);
            grid[qx][qy].add (P);
        } 
        if (uf.count () == 1) 
            StdOut.println ("Sim");
        else
            StdOut.println ("Nao");
    }
}
