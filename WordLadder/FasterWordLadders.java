/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 14/05/2016
             Exercise 4.1.12 Faster word ladders
/ /////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;
import java.util.*;

public class FasterWordLadders {
    // Guarda a relacao entre a String e um inteiro que a identifica no grafo
    private static HashMap<String, Integer> map;
    // Vetor de Strings que sera manipulado pelos "sorts"
    private static String[] list;
    // Guarda a relacao entre os inteiros e as Strigs
    private static String[] origin;
    private static int MAX = 10;
    private static int size = 0;
    private static Graph G;
    
    private static void resize (int n) {
        String[] temp = new String[n];
        for (int i = 0; i < size; i++) 
            temp[i] = list[i];
        list = temp;
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
    
    public static boolean isNeighbor (String a, String b, int idx) {
        int l = a.length ();
        for (int i = 0; i < l-1; i++)
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
                    G.addEdge (map.get (list[j]), map.get (list[k]));
                    k++;
                }
            }
        }
    }

    public static void getGraph () {
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
                        G.addEdge (map.get (list[i]), map.get (list[j])); 
                }
                else break;
            }
        }

    }

    public static void main (String[] args) {
        In in = new In (args[0]);
        map = new HashMap<String, Integer> ();
        list = new String[MAX];

        while (!in.isEmpty ()) {
            String word = in.readString ();
            map.put (word, size);
            list[size++] = word;
            if (size == MAX) {
                MAX *= 2;
                resize (MAX);
            }
        }
        resize (size);
        origin = new String[size];
        for (int i = 0; i < size; i++)
            origin[i] = list[i]; 

        G = new Graph (size);
        getGraph ();

        while (!StdIn.isEmpty ()) {
            String from = StdIn.readString ();
            String to = StdIn.readString ();
            BreadthFirstPaths bst = new BreadthFirstPaths (G, map.get (from));
            if (bst.hasPathTo (map.get (to))) {
                StdOut.println (bst.distTo (map.get (to)));
                for (int v : bst.pathTo (map.get (to))) 
                    StdOut.println (origin[v]);
            }
            else 
                StdOut.println ("NOT CONNECTED");
        }
    }

}
