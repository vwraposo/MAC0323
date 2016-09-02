/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 04/04/2016
            Exercise 1.5.20 Dynamic Growth
Consideraçoes:
    A funçao newSite apenas ativa um site, ou seja, seta o id e o sz do
novo site; e devolve o numero de sites ativados atualizado. Entao, ha uma
funcao resize que realiza a alocaçao de memoria.
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class DynamicWeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    //Tamanho maximo dos vetores
    private int MAX = 2;
    // Numero de sites ativados
    private int N = 0;
    private int count = 0;

    public DynamicWeightedQuickUnionUF () {
        id = new int[MAX];
        sz = new int[MAX];
    }

    public int find (int p) {
        while (id[p] != p) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public boolean connected (int p, int q) {
        return find(p) == find (q);
    }

    public void union (int p, int q) {
        // Ativando os sites
        if (p >= N | q >= N) {
            int max = Math.max (p, q);
            int n;
            do {
                n = newSite ();
            } while (n <= max);
        }
        int i = find (p);
        int j = find (q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;;
    }

    public void resize (int size) {
        int[] idT = new int[size];
        int[] szT = new int[size];
        for (int i = 0; i < N; i++) {
            idT[i] = id[i];
            szT[i] = sz[i];
        }
        MAX = size;
        sz = szT;
        id = idT;
    }

    public int newSite () {
        id[N] = N;
        sz[N] = N;
        N++;
        count++;
        if (N == MAX) resize (MAX*2);
        return N;
    }

    // Devolve o numero de site ativos
    public int size () {
        return N;
    }

    public int count () {
        return count;
    }

    //test client
    public static void main (String[] args) {
        int max;
        DynamicWeightedQuickUnionUF uf = new DynamicWeightedQuickUnionUF ();
        while (!StdIn.isEmpty ()) {
            int p = StdIn.readInt ();
            int q = StdIn.readInt ();
            uf.union (p,q);
        }
        for (int i = 0; i < uf.size (); i++) {
            int root = uf.find (i);
            if (root != i)
                StdOut.println (i + " " + root);
        }
    }
}
