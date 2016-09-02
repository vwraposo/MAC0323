/*/////////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 14/05/2016
                    Exercise 4.2.33 Number of paths in a DAG
/ /////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;
import java.util.*;
import java.math.BigInteger;

public class WordDAG {
    private static HashMap<String, Integer> map;
    private static String[] list;
    private static String[] origin;
    private static Digraph G;
    private static Topological T;
    private static int size = 0;
    public WordDAG (String[] list) {
        this.list = list;
        size = list.length;
        map = new HashMap<String, Integer> ();
        origin = new String[size];
        G = new Digraph (size);

        for (int i = 0; i < size; i++) {
            map.put (list[i], i);
            origin[i] = list[i];
        }
        getDigraph ();
        T = new Topological (G);
    }

    public void printDag () {
        Out out = new Out ("saida.txt");
        for (int i = 0; i < size; i++) 
            for (int j : G.adj (i))
                out.println (origin[i] + " " + origin[j]);
        out.close ();
    }

    public void printPathCount (String a, String b) {
        Out out = new Out ("saida.txt");
        out.println (pathCount (a, b));
        out.close ();
    }

    private BigInteger pathCount (String a, String b) {
        int from = map.get (a);
        int to = map.get (b);
        BigInteger[] memo = new BigInteger[size];
        for (int i = 0; i < size; i++)
            memo[i] = BigInteger.ONE.negate ();
        return dfs (from, to, memo);
    }
    
    private BigInteger dfs (int w, int v, BigInteger[] memo) {
        BigInteger ans = BigInteger.ZERO;
        for (int a : G.adj (w)) {
            if (!memo[a].equals (BigInteger.ONE.negate ())) ans = ans.add (memo[a]);
            else {
                if (a == v) {
                    ans = ans.add (new BigInteger ("1"));
                }
                else {
                    memo[a] = dfs (a, v, memo); 
                    ans = ans.add (memo[a]);
                }
            }
        }
        return ans;

    }

    private static class StrComparator implements Comparator<String> {
        private int idx;
        public StrComparator (int idx) {
            this.idx = idx;
        }
        
        public int compare (String a, String b) {
            if (a.length () == b.length ()) {
                int l = a.length ();
                for (int i = 0; i < l; i++) {
                    int aux = a.charAt ((idx + i) % l) - b.charAt ((idx + i) % l);
                    if (aux != 0) return aux;
                }
            }
            return a.length () - b.length ();
            
        }
    }
    
    private static boolean isNeighbor (String a, String b, int idx) {
        int l = a.length ();
        for (int i = 0; i < q; i++)
            if (a.charAt ((idx + i) % l) != b.charAt ((idx + i) % l)) return false;
        return true;
    }

    private static void getNeighbors (int begin, int end) {
        int l = list[begin].length ();
        for (int i = 0; i < l; i++) {
            Arrays.sort (list, begin, end, new StrComparator (i)); 
            for (int j = begin; j < end; j++){ 
                int k = j + 1;
                while (k < end && isNeighbor (list[j], list[k], i)) {
                    G.addEdge (map.get (list[k]), map.get (list[j]));
                    k++;
                }
            }
        }
    }

    private static void getDigraph () {
        Arrays.sort (list, new StrComparator (0));
        int begin = 0, end;

        //Palavras de Tamanhos iguais
        while (begin < size) {
            end = begin + 1;
            while (end < size && list[begin].length () == list[end].length ()) end++;
            if (end - begin > 1) getNeighbors (begin, end);
            begin = end;
        } 

        //Palavras de tamanho diferentes
        Arrays.sort (list);
        for (int i = 0; i < size; i++) {
            for (int j = i+1; j < size; j++) {
                if (list[j].startsWith (list[i])) {
                    int aux = list[j].length () - list[i].length ();
                    if (aux == 1)  
                        G.addEdge (map.get (list[j]), map.get (list[i])); 
                }
                else break;
            }
        }

    }

    private static String[] resize (String[] v, int n, int sz) {
        String[] temp = new String[n];
        for (int i = 0; i < sz; i++) 
            temp[i] = v[i];
        v = temp;

        return v;
    }

    //test client 
    public static void main (String[] args) {
        if (args.length == 0) {
            String[] strings = {"cobra", "sobra", "sobrar", "cobro", "cobrou", "sobrando", "cobre"};
            WordDAG wd = new WordDAG (strings);
            wd.printPathCount ("cobro", "cobra"); 
        }
        else {
            In in = new In (args[0]);
            int MAX = 10;
            int sz = 0;
            String[] strings = new String[MAX];
            while (!in.isEmpty ()) {
                strings[sz++] = in.readString ();
                if (sz == MAX) {
                    MAX *= 2;
                    strings = resize (strings, MAX, sz);
                }
            }
            strings = resize (strings, sz, sz);
            WordDAG wd = new WordDAG (strings);
            wd.printPathCount ("zzz", "a");
        }
    }
}
