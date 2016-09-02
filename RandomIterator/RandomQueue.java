/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Problem 1.3.36 Random Iterator
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;
import java.util.*;

public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int N = 0;
    private int first = 0;
    private int last = 0;

    public RandomQueue () {
        q = (Item[]) new Object[2];
    }

    // Verifica se a fila esta vazia
    public boolean isEmpty () {
        return N == 0;
    }

    // Adiciona um elemento na fila
    public void enqueue (Item item) {
        if (q.length ==  N) resize (2*N);
        q[last++] = item;
        N++;
        last = last % q.length;
    }

    // Escolhe um elemento da fila aleatoriamente e troca de posicao
    //com o primeiro elemento
    private void randomSwap () {
        int rand = StdRandom.uniform (N);
        // Swap
        Item temp = q[first];
        q[first] = q[first+rand];
        q[first+rand] = temp;
    }

    // Retira um elemento da fila
    public Item dequeue () {
        randomSwap ();
        Item item = q[first];
        q[first] = null;
        first = (first + 1) % q.length;
        N--;
        if (N < q.length / 4) resize (q.length/2);
        return item;
    }

    // Devolve o valor de um elemento aleatorio da fila
    public Item sample () {
        int rand = StdRandom.uniform (N);
        return q[first+rand];
    }

    // Muda o tamanho da fila para o valor da variavel size
    private void resize (int size) {
        Item[] temp = (Item[]) new Object[size];
        for (int i = 0; i < N; i++)
            temp[i] = q[(first+i) % q.length];
        q = temp;
        first = 0;
        last = N;
    }

    public Iterator<Item> iterator () { return new RandomIterator (); }

    private class RandomIterator implements Iterator<Item> {
        private int current = first;
        private int n = N;

        public boolean hasNext () { return n > 0; }
        public void remove () { throw new UnsupportedOperationException (); }
        public Item next () {
            if (!hasNext ()) throw new NoSuchElementException ();
            int rand;
            if (n-1 == 0)
                rand = 0;
            else
                rand = StdRandom.uniform (n);
            Item item = q[current+rand];
            q[current+rand] = q[current];
            q[current] = item;
            current++;
            n--;
            return item;
        }
    }

    // test client
    public static void main(String[] args) {
        int N = Integer.parseInt (args[0]);
        RandomQueue<Integer> q = new RandomQueue ();
        for (int i = 0; i < N; i++)
            q.enqueue (i);
        for (Integer k : q)
            StdOut.println (k);
        for (Integer k : q)
            StdOut.println (k);
    }
}
