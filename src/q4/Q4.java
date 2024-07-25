package q4;

import java.util.Scanner;

public class Q4 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to the Minimum Spanning Tree (MST) Calculator!");
        System.out.println("Please follow the instructions below to input the graph data.");

        // Input number of vertices
        System.out.print("Enter the number of vertices in the graph: ");
        int vertices = s.nextInt();
        
        // Input number of edges
        System.out.print("Enter the number of edges in the graph: ");
        int edgesCount = s.nextInt();

        // Validate input
        if (vertices <= 0 || edgesCount <= 0) {
            System.out.println("Number of vertices and edges must be greater than 0.");
            return;
        }

        // Initialize data structures
        myForest = new int[vertices];
        int[][] ar = new int[vertices][vertices];

        // Initialize the adjacency matrix and overallCost
        for (int i = 0; i < vertices; i++) {
            myForest[i] = i; // Initialize myForest
        }

        overallCost = 0; // Reset overallCost

        System.out.println("Enter the edges of the graph:");
        System.out.println("For each edge, input two integers representing the vertices it connects (1-based indexing).");
        System.out.println("Example: For an edge between vertex 1 and vertex 2, enter '1 2'");
        System.out.println("To end input, simply press Enter after the last edge.");

        for (int j = 0; j < edgesCount; j++) {
            System.out.print("Edge " + (j + 1) + ": ");
            int u = s.nextInt() - 1;
            int v = s.nextInt() - 1;

            // Check for valid input
            if (u < 0 || u >= vertices || v < 0 || v >= vertices) {
                System.out.println("Invalid edge between vertices " + (u + 1) + " and " + (v + 1) + ". Please enter valid vertex numbers.");
                j--; // Decrement index to re-enter the edge
                continue;
            }

            int cost = abs(u, v);
            overallCost += cost;
            ar[u][v] = cost;
            ar[v][u] = cost; // For undirected graph
        }

        // Calculate and display MST
        System.out.println("Calculating Minimum Spanning Tree (MST)...");
        MST(ar);
    }

    static int overallCost = 0;
    static int[] myForest;

    private static void union(int from, int to) {
        int x = Get(from);
        int y = Get(to);
        if (x != y) {
            myForest[x] = y;
        }
    }

    static int abs(int u, int v) {
        return Math.abs(u - v);
    }

    private static int Get(int x) {
        if (x < 0 || x >= myForest.length) {
            throw new IndexOutOfBoundsException("Index " + x + " is out of bounds.");
        }
        if (myForest[x] != x) {
            myForest[x] = Get(myForest[x]); // Path compression
        }
        return myForest[x];
    }

    private static void MST(int[][] ar) {
        int shortCost = 0;
        int edgeCount = 0;

        // Initialize the myForest array to itself
        for (int i = 0; i < myForest.length; i++) {
            myForest[i] = i;
        }

        while (edgeCount < myForest.length - 1) {
            int shortest = Integer.MAX_VALUE;
            int x = -1, y = -1;
            for (int i = 0; i < myForest.length; i++) {
                for (int j = 0; j < myForest.length; j++) {
                    if (ar[i][j] > 0 && Get(i) != Get(j) && ar[i][j] < shortest) {
                        shortest = ar[i][j];
                        x = i;
                        y = j;
                    }
                }
            }
            if (x == -1 || y == -1) {
                break; // No more edges to process
            }
            edgeCount++;
            union(x, y);
            shortCost += shortest;
        }
        System.out.println();
        System.out.println("Total cost of MST: " + shortCost);
        System.out.println("Overall cost minus MST cost: " + (overallCost - shortCost));
    }
}
