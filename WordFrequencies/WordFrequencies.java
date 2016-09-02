/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Exercise 3.1.52 Word frequencies

METODO:
        Leio as palavras, coloco-nas em um TreeMap,com as palavras como keys
    e o as suas frequencias como values, que vao atualizando conforme as
    leitura. Acabando de ler todas as palavras transfiro-nas com suas
    frequencias para um ArrayList de pair (palavra e frequencia) e ordeno-o;

//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;
import java.util.*;

public class WordFrequencies {

    // Funcao que le uma linha do texto, e devolve uma sequencia de
    // strings, com carateres minusculos, separados por um token ('$');
    public static String getLine () {
        String line = StdIn.readLine ();
        String newline = new String ();
        char c;
        boolean valid = false;
        line = line.toLowerCase ();
        for (int i = 0; i < line.length (); i++) {
            c = line.charAt (i);
            if (Character.isDigit (c) || Character.isLetter (c) || c == '-') {
                valid = true;
                newline += c;
            }
            else if (valid)
                newline += '$';
        }

        return newline;
    }

    public static void main (String[] args) {
        TreeMap<String, Integer> map = new TreeMap<String, Integer> ();
        List<Pair> list = new  ArrayList<Pair> ();

        // Leitura do Texto e insersao no Map
        while (!StdIn.isEmpty ()) {
            StringTokenizer line = new StringTokenizer (getLine ());
            while (line.hasMoreTokens ()) {
                String s = line.nextToken ("$");
                if (map.containsKey (s))
                    map.put (s, map.get (s) + 1);
                else
                    map.put (s, 1);
            }
        }

        // Transferencia dos conteudos do Map para o ArrayList
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Pair p = new Pair (entry.getKey(), entry.getValue ());
            list.add (p);
        }

        // Ordena o ArrayList em relacao a frequencia das palavras (da maior
        // para a menor) e caso duas palavras tenham a mesma frequencia leva em
        // conta a ordem lexografica;
        Collections.sort (list, new Comparator<Pair> (){
            public int compare (Pair a , Pair b) {
                int value = b.second - a.second;
                if (value != 0) return value;
                return a.first.compareTo (b.first);
            }
        });

        // Imprime
        for (Pair it : list)
            StdOut.println (it.first + " " + it.second);
    }
}
