/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 14/05/2016
             Exercise 5.2.2 Unique substrings of length L
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class UniqueL {
    public static String randomSeq (int N) {
        String s = new  String ();
        for (int i = 0; i < N; i++) {
            Integer rand = StdRandom.uniform (10);
            s += rand.toString ();
        }
        return s;
    }
    
    public static void main (String[] args) {
        int L = Integer.parseInt (args[0]); 
        if (L != 0) {
            TST st = new TST ();
            String line = StdIn.readLine ();

            for (int i = 0; i <= line.length () - L; i++)
                st.put (line.substring (i, i+L), 0);

            StdOut.println (st.size ());
        }
        if (L == 0) {
            String line;
            int N;
            if (args.length == 2) {
                N = Integer.parseInt (args[1]); 
                line = randomSeq (N); 
            }
            else 
                line =  StdIn.readLine (); 
            int ans = -1;
            for (int l = 1; l < line.length (); l++) {
                TST st = new TST ();
                for (int i = 0; i <= line.length () - l; i++) 
                    st.put (line.substring (i, i + l), 0);
                if (st.size () != Math.pow (10, l)) {
                        StdOut.println (l-1);
                        break;
                }
            }
                
/*                for (Integer i = 0; i < Math.pow (10, l); i++) {
                    String number = i.toString ();
                    String prefix = new String ();
                    for (int j = 0; j < l - number.length (); j++)
                        prefix = prefix + "0";
                    number = prefix + number;
                    if (!st.contains (number)) {
                        ans = l-1;
                        break;
                    }
                }
                if (ans > -1) {
                    StdOut.println (ans);
                    break;
                }
  */              
        }
    }
}
