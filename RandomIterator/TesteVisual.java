/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Problem 1.3.36 Random Iterator
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;
import java.util.*;

public class TesteVisual {

    // Devolve quantos numeros exitem em permutation[begin+1..N-1] que sao
    // menores que permutation[begin]
    public static int distance (Integer[] permutation, int begin, int N) {
        int distance = 0;
        int k = permutation[begin];
        for (int i = begin+1; i < N; i++)
            if (permutation[i] < k) distance++;
        return distance;
    }

    public static int fatorial (int x) {
        if (x == 0) return 1;
        return x * fatorial (x-1);
    }

    // Recebe uma permutacao e devolve o numero que representa
    public static int number (Integer[] permutation, int begin, int N) {
        if (begin == N) return 0;
        return (distance (permutation, begin, N) * fatorial (N-1-begin)
            + number (permutation, begin + 1, N));
    }

    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        int T = Integer.parseInt (args[1]);
        RandomQueue<Integer> q = new RandomQueue ();
        Histogram histogram = new Histogram (fatorial (N));

        for (int i = 0; i < N; i++)
            q.enqueue (i);

        for (int i = 0; i < T; i++) {
            Integer[] permutation = new Integer[N];
            int j = 0;
            for (Integer k : q) {
                permutation[j++] = k;
            }
            histogram.addDataPoint (number (permutation, 0, N));
        }
        histogram.draw ();
    }
}
