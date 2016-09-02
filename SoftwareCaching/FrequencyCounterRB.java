/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 11/04/2016
            Exercise 3.2.37 (Level-order traversal
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class FrequencyCounterRB {

    public static void main (String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt (args[0]);
        RedBlackBSTCC<String, Integer> st = new RedBlackBSTCC<String, Integer>();

        while (!StdIn.isEmpty ()) {
            String key = StdIn.readString ();
            key = key.replaceAll ("[^\\w]", "");
            key = key.toLowerCase ();
            if (key.length () < minlen) continue;
            words++;
            if (st.contains (key)) {
                st.put (key, st.get (key) + 1);
            }
            else {
                st.put (key, 1);
                distinct++;
            }
        }

        String max = " ";
        st.put (max, 0);
        for (String word : st.keys ()) {
            if (st.get (word) > st.get (max))
                max = word;
        }

        StdOut.println(max + " " + st.get (max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}
