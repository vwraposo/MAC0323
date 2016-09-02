/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 11/04/2016
            Exercise 3.2.37 (Level-order traversal
//////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class BST {
    private Node root;

    private class Node {
        private int value;
        private Node right, left;

        public Node (int value) {
            this.value = value;
        }
    }
    public BST () {}

    public void put (int i) {
        root = put (root, i);
    }

    private Node put (Node x, int i) {
        if (x == null) return new Node (i);
        if (i < x.value) x.left = put (x.left, i);
        else if (i > x.value) x.right = put (x.right, i);
        else x.value = i;
        return x;
    }

    // Recebe um inteiro i e devolve um Node da BST cujo value seja i
    public Node find (int i) {
        return find (root, i);
    }

    private Node find (Node x, int i) {
        if (x == null) return null;
        if (i > x.value) return find (x.right, i);
        else if (i < x.value) return find (x.left, i);
        else return x;
    }

    // Recebe um inteiro i e imprime em level order
    // a subarvore com raiz cujo value seja i
    public void level_order (int i) {
        Queue<Node> q = new Queue<Node> ();
        Node subroot = find (i);
        q.enqueue (subroot);
        while (!q.isEmpty ()) {
            Node x = q.dequeue ();
            if (x != null) {
                StdOut.print (x.value + " ");
                q.enqueue (x.left);
                q.enqueue (x.right);
            }
        }
        StdOut.println ();
    }

}
