/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
    Creative Problem 5.3.24 Find all occurrence
//////////////////////////////////////////////////////////////////////////////*/

import edu.princeton.cs.algs4.*;

public class Brute {

    /*////////// FIND ALL ///////////////////////////////////////////*/
    public Iterable<Integer> findAll (String pat, String txt) {
        // Fila que armazena as posicoes nas quais foram encontradas 
        // o padrao no texto 
        Queue<Integer> queue = new Queue<Integer> ();

        int N = txt.length ();
        int M = pat.length ();
        // Armazena a posicao das ocorrencias do padrao no texto
        int position;
        // Posicao a partir do qual o texto sera processado
        int start = 0;

        // Achando o estado que devemos ficar apos encontrar um padrao no texto
        int X = 1;

        // Achando todas as ocorrencias do padrao
        do { 
            position = search (pat, txt, start);
            if (position < N) {
                queue.enqueue (position);
            }
            start = position + X;
        } while (start < txt.length ());
        
        return queue;
    }
    
    // Devolve a posicao da primeira ocorrencia da string pat em txt
    // a partir do i-esimo caracter
    private static int search (String pat, String txt, int i) {
        int M = pat.length();
        int N = txt.length();

        for (; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i+j) != pat.charAt(j))
                    break;
            }
            if (j == M) return i;            // found at offset i
        }
        return N;                            // not found
    }
    /*//////////////////////////////////////////////////////////////*/

   /***************************************************************************
    *  String versions
    ***************************************************************************/

    // return offset of first match or N if no match
    public static int search1(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i+j) != pat.charAt(j))
                    break;
            }
            if (j == M) return i;            // found at offset i
        }
        return N;                            // not found
    }

    // return offset of first match or N if no match
    public static int search2(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (txt.charAt(i) == pat.charAt(j)) j++;
            else { i -= j; j = 0;  }
        }
        if (j == M) return i - M;    // found
        else        return N;        // not found
    }


   /***************************************************************************
    *  char[] array versions
    ***************************************************************************/

    // return offset of first match or N if no match
    public static int search1(char[] pattern, char[] text) {
        int M = pattern.length;
        int N = text.length;

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (text[i+j] != pattern[j])
                    break;
            }
            if (j == M) return i;            // found at offset i
        }
        return N;                            // not found
    }

    // return offset of first match or N if no match
    public static int search2(char[] pattern, char[] text) { 
        int M = pattern.length;
        int N = text.length;
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (text[i] == pattern[j]) j++;
            else { i -= j; j = 0;  }
        }
        if (j == M) return i - M;    // found
        else        return N;        // not found
    } 

    public static void main(String[] args) {
        String pat = "abacaba";
        String txt1 = "abacaba aaaa abacabacaba jjjjjj";
        String txt2 = "abacabacaba";
        KMP kmp = new KMP (pat);
        for (int i : kmp.findAll (txt1))
            StdOut.println (i);
        for (int i : kmp.findAll (txt2))
            StdOut.println (i);
    }
}
