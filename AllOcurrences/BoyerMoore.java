/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
    Creative Problem 5.3.24 Find all occurrence
//////////////////////////////////////////////////////////////////////////////*/

import edu.princeton.cs.algs4.*;

public class BoyerMoore {
    private final int R;     // the radix
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String pat;      // or as a string

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
            position = search (txt, start);
            if (position < N) {
                queue.enqueue (position);
            }
            start = position + X;
        } while (start < txt.length ());
        
        return queue;
    }

    // Devolve a posicao da primeira ocorrencia da string pat em txt
    // a partir do i-esimo caracter
    private int search (String txt, int i) {
        int M = pat.length();
        int N = txt.length();
        int skip;
        for (; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
    }
    /*//////////////////////////////////////////////////////////////*/

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public BoyerMoore(String pat) {
        this.R = 256;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     * @param R the alphabet size
     */
    public BoyerMoore(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pattern.length; j++)
            right[pattern[j]] = j;
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param  txt the text string
     * @return the index of the first occurrence of the pattern string
     *         in the text string; N if no such match
     */
    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
    }


    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param  text the text string
     * @return the index of the first occurrence of the pattern string
     *         in the text string; N if no such match
     */
    public int search(char[] text) {
        int M = pattern.length;
        int N = text.length;
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
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
