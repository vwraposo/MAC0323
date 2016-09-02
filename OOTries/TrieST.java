/*/////////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 4/06/2016
                    Exercise 5.2.8 Ordered operations for tries
/ /////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class TrieST<Value> {
    private static final int R = 256;        // extended ASCII


    private Node root;      // root of trie 
    private int N;          // number of keys in trie

    // R-way trie node
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

   /**
     * Initializes an empty string symbol table.
     */
    public TrieST() {
    }

    /* Minhas Funcoes //////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////*/
    public int rank (String s) {
        if (!contains (s)) return -1;
        return rank (root, s, 0);
    }

    private int rank (Node x, String s, int d) {
        if (x == null) return 0;
        if (s.length () == d) return 0;
        char c = s.charAt (d);
        return left_rank (x, c) + rank (x.next[c], s, d+1);
        
    }

    // Sub Tries estritamente menores que a da string 
    private int left_rank (Node x, char c) {
        int sum = 0;
        if (x.val != null) sum++;
        for (char i = 0; i < c; i++) {
                if (x.next[i] != null) {
                    sum += left_rank (x.next[i], (char) R);
                }
        }
        return sum;
    }

    public String select (int p) {
        sum = 0;
        return select (root, p+1);
    }

    private int sum;
    private String select (Node x, int p) {
        if (x.val != null) {
            sum++;
            if (sum == p) return new String ();
        }
        for (char i = 0; i < R; i++)
            if (x.next[i] != null) {
                String s = i + select (x.next[i], p);
                if (sum == p)
                    return s;
            }
        return null;
    }

    public String floor (String s) {
        return floor (root, s, 0, true); 
    }

    private String floor (Node x, String s, int d, boolean pre) {
        char c = R - 1;
        if (pre && d < s.length ())
            c = s.charAt (d);
        else if (pre && d >= s.length ())
            return null;
        for (int i = (int) c; i >= 0; i--) 
            if (x.next[(char)i] != null) {
                if (pre && d < s.length () && i == s.charAt (d))
                    pre = true;
                else 
                    pre = false;
                String tmp = floor (x.next[(char)i], s, d+1, pre);
                if (tmp != null) return (char) i + tmp;
            }
        if (x.val != null)
            return new String ();
        else 
            return null;
    }

    public String ceil (String s) {
        return ceil (root, s, 0, true); 
    }

    private String ceil (Node x, String s, int d, boolean pre) {
        if (x.val != null) 
            if (!pre || (pre && d == s.length()))
                return new String ();
        char c = 0;
        if (pre && d < s.length ())
            c = s.charAt (d);
        for (int i = (int) c; i < R; i++) 
            if (x.next[(char)i] != null) {
                if (pre && d < s.length () && i == s.charAt (d))
                    pre = true;
                else 
                    pre = false;
                String tmp = ceil (x.next[(char)i], s, d+1, pre);
                if (tmp != null) return (char) i + tmp;
            }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////*/

    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and <tt>null</tt> if the key is not in the symbol table
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
     *     <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contains(String key) {
        return get(key) != null;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is <tt>null</tt>, this effectively deletes the key from the symbol table.
     * @param key the key
     * @param val the value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void put(String key, Value val) {
        if (val == null) delete(key);
        else root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (x.val == null) N++;
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return N;
    }

    /**
     * Is this symbol table empty?
     * @return <tt>true</tt> if this symbol table is empty and <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns all keys in the symbol table as an <tt>Iterable</tt>.
     * To iterate over all of the keys in the symbol table named <tt>st</tt>,
     * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
     * @return all keys in the sybol table as an <tt>Iterable</tt>
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    /**
     * Returns all of the keys in the set that start with <tt>prefix</tt>.
     * @param prefix the prefix
     * @return all of the keys in the set that start with <tt>prefix</tt>,
     *     as an iterable
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;
        if (x.val != null) results.enqueue(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Returns all of the keys in the symbol table that match <tt>pattern</tt>,
     * where . symbol is treated as a wildcard character.
     * @param pattern the pattern
     * @return all of the keys in the symbol table that match <tt>pattern</tt>,
     *     as an iterable, where . is treated as a wildcard character.
     */
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> results = new Queue<String>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.val != null)
            results.enqueue(prefix.toString());
        if (d == pattern.length())
            return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Returns the string in the symbol table that is the longest prefix of <tt>query</tt>,
     * or <tt>null</tt>, if no such string.
     * @param query the query string
     * @return the string in the symbol table that is the longest prefix of <tt>query</tt>,
     *     or <tt>null</tt> if no such string
     * @throws NullPointerException if <tt>query</tt> is <tt>null</tt>
     */
    public String longestPrefixOf(String query) {
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        else return query.substring(0, length);
    }

    // returns the length of the longest string key in the subtrie
    // rooted at x that is a prefix of the query string,
    // assuming the first d character match and we have already
    // found a prefix match of given length (-1 if no such match)
    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }

    /**
     * Removes the key from the set if the key is present.
     * @param key the key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.val != null) N--;
            x.val = null;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }

    /**
     * Unit tests the <tt>TrieST</tt> data type.
     */
    public static void main(String[] args) {

        // build symbol table from standard input
        TrieST<Integer> st = new TrieST<Integer>();

        st.put ("ama", 0);
        st.put ("bateria", 10);
        st.put ("amanha", 20);
        st.put ("zebra", 40);

        // Select Test
        StdOut.println ("__SELECT__");
        StdOut.println (st.select (0));
        StdOut.println (st.select (1));
        StdOut.println (st.select (2));
        StdOut.println (st.select (10));
        StdOut.println (); 

        // Rank Test
        StdOut.println ("__RANK__");
        StdOut.println (st.rank ("bateria"));
        StdOut.println (st.rank ("amanha"));
        StdOut.println (st.rank ("esta"));
        StdOut.println (st.rank ("zebra"));
        StdOut.println (); 

        // Ceil Test
        StdOut.println ("__CEIL__");
        StdOut.println (st.ceil ("aman"));
        StdOut.println (st.ceil ("baterias"));
        StdOut.println (st.ceil ("bbb"));
        StdOut.println (st.ceil ("bateria"));
        StdOut.println (); 

        // Floor Test
        StdOut.println ("__FLOOR__");
        StdOut.println (st.floor ("aman"));
        StdOut.println (st.floor ("baterias"));
        StdOut.println (st.floor ("bbb"));
        StdOut.println (st.floor ("bateria"));
        StdOut.println (); 
    }
}

