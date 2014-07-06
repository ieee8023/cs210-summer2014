/*************************************************************************
 *  Compilation:  javac EuclideanGraph.java
 *  Execution:    java EuclideanGraph
 *  Dependencies: In.java IntIterator.java
 *  
 *  Undirected graph of points in the plane, where the edge weights
 *  are the Euclidean distances.
 *
 *************************************************************************/


public class EuclideanGraph {
    // for portability
    private final static String NEWLINE = System.getProperty("line.separator");

    private int V;            // number of vertices
    private int E;            // number of edges
    private Node[]  adj;      // adjacency lists
    private Point[] points;   // points in the plane
    
    // node helper class for adjacency list
    private static class Node {
        int v;
        Node next;
        Node(int v, Node next) { this.v = v; this.next = next; }
    }

    // iterator for adjacency list
    private class AdjListIterator implements IntIterator {
        private Node x;
        AdjListIterator(Node x)  { this.x = x; }
        public boolean hasNext() { return x != null; }
        public int next() { 
            int v = x.v;
            x = x.next;
            return v;
        }
    }


   /*******************************************************************
    *  Read in a graph from a file, bare bones error checking.
    *  V E
    *  node: id x y
    *  edge: from to
    *******************************************************************/
    public EuclideanGraph(In in) {
        V = Integer.parseInt(in.readString());
        E = Integer.parseInt(in.readString());

        // read in and insert vertices
        points = new Point[V];
        for (int i = 0; i < V; i++) {
            int v = Integer.parseInt(in.readString());
            int x = Integer.parseInt(in.readString());
            int y = Integer.parseInt(in.readString());
            if (v < 0 || v >= V) throw new RuntimeException("Illegal vertex number");
            points[v] = new Point(x, y);
        }

        // read in and insert edges
        adj = new Node[V];
        for (int i = 0; i < E; i++) {
            int v = Integer.parseInt(in.readString());
            int w = Integer.parseInt(in.readString());
            if (v < 0 || v >= V) throw new RuntimeException("Illegal vertex number");
            if (w < 0 || w >= V) throw new RuntimeException("Illegal vertex number");
            adj[v] = new Node(w, adj[v]);
            adj[w] = new Node(v, adj[w]);
        }
    }


    // accessor methods
    public int V() { return V; }
    public int E() { return E; }
    public Point point(int i) { return points[i]; }

    // Euclidean distance from v to w
    public double distance(int v, int w) { return points[v].distanceTo(points[w]); }


    // return iterator for list of neighbors of v
    public IntIterator neighbors(int v) {
        return new AdjListIterator(adj[v]);
    }


    // string representation - takes quadratic time because of string concat
    public String toString() {
        String s = "";
        s += "V = " + V + NEWLINE;
        s += "E = " + E + NEWLINE;
        for (int v = 0; v < V && v < 100; v++) {
            String t = v + " " + points[v] + ": ";
            for (Node x = adj[v]; x != null; x = x.next)
                t += x.v + " ";
            s += t + NEWLINE;
        }
        return s;
    }


    // draw the graph in turtle graphics
    public void draw() {
        for (int v = 0; v < V; v++) {
            points[v].draw();
            for (Node x = adj[v]; x != null; x = x.next) {
                int w = x.v;
                points[v].drawTo(points[w]);
            }
        }
       //Turtle.render();
    }



    // test client
    public static void main(String args[]) {
    	Turtle.create(900, 900);
        In in = new In("usa-1000long.txt");
        EuclideanGraph G = new EuclideanGraph(in);
        System.out.println(G);
        G.draw();
    }

}