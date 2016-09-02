/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 19/06/2016
                    Exercise 5.3.32 Unique substrings 
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class UniqueLRK {
    // Variaveis para o Rolling Hash
    public static int R = 256;
    public static int Q = 12582917; 
    public static long RM;
   
    public static String randomSeq (int N) {
        String s = new  String ();
        for (int i = 0; i < N; i++) {
            Integer rand = StdRandom.uniform (10);
            s += rand.toString ();
        }
        return s;
    }

    private static int hash(String key, int M) { 
        int h = 0; 
        for (int j = 0; j < M; j++) 
            h = (R * h + key.charAt(j)) % Q; 
        return h; 
    } 

    public static void main (String[] args) {
        int L = Integer.parseInt (args[0]); 
        // Armazena o valor do hash para cada substring do texto
        if (L != 0) {
            ST<Long, Integer> st = new ST<Long, Integer> ();
            // Pre Calculando o valor de R^L
            RM = 1;
            for (int i = 1; i <= L-1; i++)
                RM = (R * RM) % Q;

            String line = StdIn.readLine ();

            long wordHash = hash (line.substring (0, L), L);
            st.put (wordHash, 0);
            int rep = 0;

            for (int i = 0; i < line.length () - L; i++) {
                wordHash = (wordHash + Q - RM * line.charAt (i) % Q) % Q; 
                wordHash = (wordHash * R + line.charAt (i + L)) % Q; 
                
                if (st.contains (wordHash)) 
                    rep ++;
                st.put (wordHash, 0);
            }

            StdOut.println ( st.size ());
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
            for (int l = 1; l < line.length (); l++) {
                ST<Long, Integer> st = new ST<Long, Integer> ();
                // Pre Calculando o valor de R^l
                RM = 1;
                for (int i = 1; i <= l-1; i++)
                    RM = (R * RM) % Q;

                long wordHash = hash (line.substring (0, l), l);
                st.put (wordHash, 0);

                for (int i = 0; i < line.length () - l; i++) {
                    wordHash = (wordHash + Q - RM * line.charAt (i) % Q) % Q; 
                    wordHash = (wordHash * R + line.charAt (i + l)) % Q; 
                    st.put (wordHash, 0);
                }

                int p = (int) Math.pow (10, l);
                if (st.size () != p) {
                    StdOut.println (l-1);
                    break;
                }
            }
        }
    }
}
