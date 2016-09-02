/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 11/04/2016
            Exercise 3.2.37 (Level-order traversal
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class LevelTraversal {

    public static void main (String[] args) {
            int N = StdIn.readInt ();
            int M = StdIn.readInt ();
            BST st = new BST ();

            for (int i = 0; i < N; i++)
                st.put (StdIn.readInt ());

            for (int i = 0; i < M; i++) {
                st.level_order (StdIn.readInt ());
            }
    }
}
