/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Problem 1.3.35 Random queue
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class RandomQueue<Item> {
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
        int rand = StdRandom.uniform (N-1);
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
        int rand = StdRandom.uniform (N-1);
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

    // test client
    public static void main(String[] args) {
        int N = Integer.parseInt (args[0]);
        RandomQueue<Integer> q = new RandomQueue ();
        for (int i = 0; i < N; i++)
            q.enqueue (i);
        int k = q.sample ();
        StdOut.println (k);
        k = q.dequeue ();
        StdOut.println (k);
        }
}
