/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Exercise 3.2.47 Random walker
//////////////////////////////////////////////////////////////////////////////*/

import edu.princeton.cs.algs4.*;
import java.awt.Color;

public class Distribution {

    // Desenha a matriz p 2Lx2L de acordo com a frequencia de visita
    public static void show (int[][] p, int L, int max, int min) {
        StdDraw.setXscale (-L, L);
        StdDraw.setYscale (-L, L);
        StdDraw.setPenColor (StdDraw.BLUE);
        StdDraw.filledSquare (0, 0, L);

        for (int i = 0; i <= 2*L; i++)
            for (int j = 0; j <= 2*L; j++) {
                double x = i - L;
                double y = j - L;
                if (p[i][j] != 0) {
                    StdDraw.setPenColor (getColor (p[i][j], max, min));
                    StdDraw.filledSquare (x, y, 0.5);
                }
            }
    }

    // Devolve um tom de vermelho de acordo com a frequencia n de visitas,
    // quanto menos visitado mais escuro
    public static Color getColor (int n, int max, int min) {
        Color c;
        if (n <= max - min){
            int r = ((n * 235) / (max - min)) + 20;
            c = new Color (r, 0, 0);
            return c;
        }
        else {
            c = new Color (255, 0, 0);
            return c;
        }
    }

    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        int M = Integer.parseInt (args[1]);
        int L = Integer.parseInt (args[2]);

        int[][] p = new int[2*L+1][2*L+1];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            RandomWalker R = new RandomWalker ();
            p[R.getX () + L][R.getY () + L] = 1;
            for (int j = 0; j < N; j++) {
                R.step();
                int x = R.getX () + L;
                int y = R.getY () + L;
                if (x >= 0 && x < 2*L+1 && y >= 0 && y < 2*L+1) {
                    p[x][y]++;
                    if (p[x][y] > max) max = p[x][y];
                    if (p[x][y] < min) min = p[x][y];
                }
            }
        }
        show (p, L, max, min);
    }
}
