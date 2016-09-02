/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Problem 1.3.35 Random queue
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class Bridge {
    public static void main (String[] args) {
        RandomQueue<Card> deck = new RandomQueue ();
        for (int i = 0; i < 52; i++) {
            Card c = new Card (i);
            deck.enqueue (c);
        }
        for (int i = 0; i < 13; i++) {
            Card c = deck.dequeue ();
            StdOut.println (c.toString());
        }
    }
}
