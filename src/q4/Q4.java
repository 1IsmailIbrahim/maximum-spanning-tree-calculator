package q4;

import java.util.Scanner;

public class Q4 {

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        vertices = s.nextInt();
        int m = s.nextInt();
        myForest = new int[vertices];
        int[][] ar = new int[vertices][vertices];
        int u, v;
        for (int j = 0; j < vertices; j++) {
            u = s.nextInt() - 1;
            v = s.nextInt() - 1;
            overallCost = overallCost + abs(u, v);
            ar[u][v] = abs(u, v);
        }
        MST(ar);
    }
    static int overallCost = 0;
    static int myForest[], vertices;

    private static void union(int from, int to) {
        int x = Get(from);
        int y = Get(to);
        myForest[x] = y;
    }

    static int abs(int u, int v) {
        if (u > v) {
            return u - v;
        } else {
            return v - u;
        }
    }

    private static int Get(int x) {
        while (myForest[x] != x) {
            x = myForest[x];
        }
        return x;
    }

    private static void MST(int[][] ar) {
        int shortCost = 0;
        for (int i = 0; i < vertices; i++) {
            myForest[i] = i;
        }
        int edgeCount = 0;
        while (edgeCount < vertices - 1) {
            int shortest = 0, x = -1, y = -1;
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (Get(i) != Get(j) && ar[i][j] > shortest) {
                        shortest = ar[i][j];
                        x = i;
                        y = j;
                    }
                }
            }
            edgeCount++;
            union(x, y);
            shortCost = shortCost + shortest;
        }
        System.out.println();
        System.out.println((overallCost - shortCost));
    }

}
