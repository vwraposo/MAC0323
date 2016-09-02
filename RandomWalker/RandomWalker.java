/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Exercise 3.2.47 Random walker
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class RandomWalker {
    private int rx;
    private int ry;

    public RandomWalker () {
        rx = 0; ry = 0;
    }

    public int getX () {
        return rx;
    }

    public int getY () {
        return ry;
    }

    // Faz o Walker andar para um dos quadro lados, aleatoriamente
    public void step () {
        int N = StdRandom.uniform (4);
        switch (N) {
            // Up
            case 0:
                ry += 1.0;
                break;
            // Right
            case 1:
                rx += 1.0;
                break;
            // Down
            case 2:
                ry -= 1.0;
                break;
            // Left
            case 3:
                rx -= 1.0;
                break;
        }
    }

    // Devolve a distancia (manhattam distance) entre o Walker e a origem
    public double distance () {
        return Math.abs (rx) + Math.abs(ry);
    }

    // Dado N, faz M testes e devolve a distancia percorrida media
    public static double eval (int N, int M) {
        double count = 0.0;
        for (int j = 0; j < M; j++) {
            RandomWalker R = new RandomWalker ();
            for (int i = 0; i < N; i++)
                    R.step ();
            count += R.distance ();
        }
        return count / M;
    }

    //test client
    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        StdOut.println (eval (1000, 100));
        RandomWalker R = new RandomWalker ();
        for (int i = 0; i < N; i++)
                R.step ();
        StdOut.println (R.distance ());
        StdOut.println (R.getX () + " " + R.getY ());

    }
}
