/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 7/05/2016
            Creative Problem 3.5.27 List

    Consideracoes:
        Criei duas estruturas uma RedBlackBST<Double, Item> que, usando a ideia 
        do exercicio Mutable String, armazena os valores na ordem correta. A 
        outra estrutura e um HashTable que armazena o double correspondente de cada 
        item na lista.
///////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;
import java.util.*;

public class List <Item> implements Iterable<Item> {
    private RedBlackBST<Double, Item> st;
    private SeparateChainingHashST<Item, Double> inverted_st;
    private Double front = 1.0;
    private Double back = 0.0;

    public List () {
        st = new RedBlackBST<Double, Item> ();
        inverted_st = new SeparateChainingHashST<Item, Double> ();
    }

    public Item get (int i) {
        return st.get (st.select (i));
    }

    public void addFront (Item item) {
        if (inverted_st.contains (item)) return;
        front--;
        st.put (front, item);
        inverted_st.put (item, front);
    } 
    
    public void addBack (Item item) {
        if (inverted_st.contains (item)) return;
        back++;
        st.put (back, item);
        inverted_st.put (item, back);
    }

    public Item deleteFront () {
        if (st.isEmpty()) throw new NoSuchElementException ("BST underflow");

        Item item = st.get (front);
        st.delete (front);
        inverted_st.delete (item);
        if (st.isEmpty ()) front = 1.0;
        else front = st.min ();


        return item;
    }

    public Item deleteBack () {
        if (st.isEmpty()) throw new NoSuchElementException("BST underflow");

        Item item = st.get (back);
        st.delete (back);
        inverted_st.delete (item);
        if (st.isEmpty ()) back = 0.0;
        else back = st.max ();

        return item;
    }

    public void delete (Item item) { 
        if (st.isEmpty()i || !inverted_st.contains (item)) throw new NoSuchElementException("BST underflow");
        
        if (st.get (front) == item) {
            item = deleteFront ();
            return;
        }
        if (st.get (back) == item) {
            item = deleteBack ();
            return;
        }
        double d = inverted_st.get (item);
        inverted_st.delete (item);
        st.delete (d);
    }

    public void add (int i, Item item) {
        if (i == 0) {
            addFront (item);
            return;
        }
        if (i >= st.size ()) {
            addBack (item);
            return;
        }
        double key = st.select (i);
        key = (key + st.select (i-1)) / 2 ;
        st.put (key, item);
        inverted_st.put (item, key);
    }
    
    public Item delete (int i) {
        if (st.isEmpty()) throw new NoSuchElementException("BST underflow");

        Item item;
        if (i  == 0) {
            item = deleteFront ();
            return item;
        }
        if (i == st.size ()) {
            item = deleteBack ();
            return item;
        } 
        double key  = st.select (i);
        item = st.get (key);
        st.delete (key);
        inverted_st.delete (item);
        
        return item;
    }

    public boolean contains (Item item) {
        return inverted_st.contains (item);
    }

    public boolean isEmpty () {
        return st.isEmpty ();
    }

    public int size () {
        return st.size ();
    }
    
    public Iterator<Item> iterator () { return new ListIterator ();}

    private class ListIterator implements Iterator<Item> {
        private int current;
        private int size = st.size ();

        public boolean hasNext () { return current != size; }
        public void remove () { throw new UnsupportedOperationException (); }
        public Item next () {
            if (!hasNext ()) throw new NoSuchElementException ();
            Item item = get (current);
            current++;

            return item;
        }
    }

    // test client
    public static void main (String[] args) {
        List<String> list = new List<String> ();
        
        // Testando as funcoes de adicionar na lista
        list.addFront ("quatro");
        list.addFront ("dois");
        list.add (1, "tres");
        list.addBack ("cinco");
        list.add (0, "um");
        list.add (5, "seis");
        for (String s : list)
            StdOut.print (s + " ");
        StdOut.println ();

        // Testando a funcao get
        StdOut.println (list.get (3));

        // Testando as funcoes de deletar um elemento
        StdOut.println (list.delete (1));
        StdOut.println (list.deleteFront ());
        StdOut.println (list.deleteBack ());
        list.delete ("quatro");
        for (String s : list)
            StdOut.print (s + " ");
        StdOut.println ();

        // Verificando se mesmo depois de esvaziar a lista continua funcionado
        list.delete ("tres");
        list.delete ("cinco");
        StdOut.println (list.isEmpty ());
        list.addFront ("oito");
        list.addFront ("nove");
        for (String s : list)
            StdOut.print (s + " ");
        StdOut.println ();
    }


}
