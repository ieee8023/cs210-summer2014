/*************************************************************************
 *  Compilation:  javac Distances.java
 *  Execution:    java Distances file < input.txt
 *  Dependencies: EuclideanGraph.java Dijkstra.java In.java StdIn.java
 *
 *  Reads in a map from a file, and repeatedly reads in two integers s
 *  and d from standard input, and prints the distance of the shortest
 *  path from s to d to standard output.
 *
 ****************************************************************************/


public class Distances {

    public static void main(String[] args) {

        // read in the graph from a file
        In graphin = new In(args[0]);
        EuclideanGraph G = new EuclideanGraph(graphin);
        Dijkstra dijkstra = new Dijkstra(G);
        System.err.println("Done reading the graph " + args[0]);
        System.err.println("Enter query pairs from stdin");

        // read in the s-d pairs from standard input
        while(!StdIn.isEmpty()) {
            int s = StdIn.readInt();
            int d = StdIn.readInt();
            System.out.println(dijkstra.distance(s, d));
        }
    }
}
