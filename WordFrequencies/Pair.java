/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Exercise 3.1.52 Word frequencies
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class Pair {
    public String first;
    public int second;

   public Pair (String str, int value) {
        first = str;
        second = value;
    }
    
    public static void main (String[] args) {
        String str = args[0];
        int x = Integer.parseInt (args[1]);
        Pair p = new Pair (str, x);
        StdOut.println (p.first + " " + p.second);
    }
}
