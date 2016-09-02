/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 27/04/2016
            Problema da Conexidade 2D
/ /////////////////////////////////////////////////////////////////////////////*/
import edu.princeton.cs.algs4.*;

public class Point2DIndex {
    public double x;
    public double y; 
    public int index;

    public Point2DIndex (double x, double y, int id) {
        this.x = x;
        this.y = y;
        index = id;
    }
    
    // Devole a distancia ao quadrado entre dois pontos
    public double distanceS (Point2DIndex that) {
        double dx = Math.pow (Math.abs (this.x - that.x), 2);
        double dy = Math.pow (Math.abs (this.y - that.y), 2);

        return dx + dy;
    }

    // Test client    
    public static void main (String args[]) {
        double x = StdIn.readDouble ();
        double y = StdIn.readDouble ();
        Point2DIndex P = new Point2DIndex (x, y, 0);
        
        x = StdIn.readDouble ();
        y = StdIn.readDouble ();
        Point2DIndex p = new Point2DIndex (x, y, 1);
        
        StdOut.println (P.distanceS (p));
    }
}
