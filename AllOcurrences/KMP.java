/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
    Creative Problem 5.3.24 Find all occurrence
//////////////////////////////////////////////////////////////////////////////*/

import edu.princeton.cs.algs4.*;

public class KMP {
    private final int R;       // the radix
    private int[][] dfa;       // the KMP automoton

    private char[] pattern;    // either the character array for the pattern
    private String pat;        // or the pattern string

    /*////////// FIND ALL ///////////////////////////////////////////*/
    public Iterable<Integer> findAll (String txt) {
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
        int X = 0;
        for (int i = 1; i < M; i++) 
            X = dfa[pat.charAt (i)][X];
        if (X == 0) X = M; 

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
    private int search(String txt, int i) {

        // simulate operation of DFA on text
        int M = pat.length();
        int N = txt.length();
        int j;
        for (j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) return i - M;    // found
        return N;                    // not found
    }
    /*//////////////////////////////////////////////////////////////*/

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public KMP(String pat) {
        this.R = 256;
        this.pat = pat;

        // build DFA from pattern
        int M = pat.length();
        dfa = new int[R][M]; 
        dfa[pat.charAt(0)][0] = 1; 
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) 
                dfa[c][j] = dfa[c][X];     // Copy mismatch cases. 
            dfa[pat.charAt(j)][j] = j+1;   // Set match case. 
            X = dfa[pat.charAt(j)][X];     // Update restart state. 
        } 
    } 

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     * @param R the alphabet size
     */
    public KMP(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // build DFA from pattern
        int M = pattern.length;
        dfa = new int[R][M]; 
        dfa[pattern[0]][0] = 1; 
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) 
                dfa[c][j] = dfa[c][X];     // Copy mismatch cases. 
            dfa[pattern[j]][j] = j+1;      // Set match case. 
            X = dfa[pattern[j]][X];        // Update restart state. 
        } 
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

        // simulate operation of DFA on text
        int M = pat.length();
        int N = txt.length();
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) return i - M;    // found
        return N;                    // not found
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

        // simulate operation of DFA on text
        int M = pattern.length;
        int N = text.length;
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[text[i]][j];
        }
        if (j == M) return i - M;    // found
        return N;                    // not found
    }


    /** 
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     */
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
