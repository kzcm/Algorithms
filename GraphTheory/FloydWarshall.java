/**
 * This file contains an implementation of the Floyd-Warshall algorithm to find
 * all pairs of shortest paths between nodes in a graph. We also demonstrate how
 * to detect negative cycles.
 *
 * Time Complexity: O(V^3)
 *
 * @author Micah Stairs
 **/

public class FloydWarshall {

  /**
   * Given an adjacency matrix with edge weights between nodes, where
   * Double.POSITIVE_INFINITY is used to indicate that two nodes are not, 
   * connected, this method mutates the given matrix in order to give the
   * shortest distance between all pairs of nodes. Double.NEGATIVE_INFINITY
   * is used to indicate that the edge between node i and node j is part of a
   * negative cycle.
   *
   * NOTE: Usually the diagonal of the adjacency matrix is all  zeros (i.e.
   * distance[i][i] = 0 for all i) since there is typically no cost to go from
   * a node to itself, but this may depend on your graph and the problem you are
   * trying to solve.
   **/
  public static void floydWarshall(double[][] distance) {

    int v = distance.length;

    // Compute all pairs shortest paths
    for (int k = 0; k < v; k++)
      for (int i = 0; i < v; i++)
        for (int j = 0; j < v; j++)
          if (distance[i][k] + distance[k][j] < distance[i][j])
            distance[i][j] = distance[i][k] + distance[k][j];

    // Identify negative cycles (you can comment this block of code out if you
    // know that no negative cycles exist)
    for (int k = 0; k < v; k++)
      for (int i = 0; i < v; i++)
        for (int j = 0; j < v; j++)
          if (distance[i][k] + distance[k][j] < distance[i][j])
            distance[i][j] = Double.NEGATIVE_INFINITY;

  }

  // Examples
  public static void main(String[] args) {
    
    // Find all pairs shortest path without negative cycles:
    int v = 5;

    // Initialize matrix to positive infinity between all pairs of
    // nodes except the diagonal entires which have cost zero
    double[][] matrix = new double[v][v];
    for (double[] row : matrix) java.util.Arrays.fill(row, Double.POSITIVE_INFINITY);
    for(int i = 0; i < v; i++) matrix[i][i] = 0;

    matrix[1][0] = 1;
    matrix[1][2] = 7;
    matrix[2][1] = 3;
    matrix[3][0] = 13;
    matrix[3][2] = 4;
    matrix[4][2] = 3;
    matrix[4][3] = 0;

    floydWarshall(matrix);

    System.out.println("Regular graph: ");
    for (int i = 0; i < v; i++)
      for (int j = 0; j < v; j++)
        if (matrix[i][j] != Double.POSITIVE_INFINITY)
          System.out.printf("Cost from node %d -> %d is %.2f\n", i,j,matrix[i][j]);
    System.out.println();

    // Prints:
    // Regular graph: 
    // Cost from node 0 -> 0 is 0.00
    // Cost from node 1 -> 0 is 1.00
    // Cost from node 1 -> 1 is 0.00
    // Cost from node 1 -> 2 is 7.00
    // Cost from node 2 -> 0 is 4.00
    // Cost from node 2 -> 1 is 3.00
    // Cost from node 2 -> 2 is 0.00
    // Cost from node 3 -> 0 is 8.00
    // Cost from node 3 -> 1 is 7.00
    // Cost from node 3 -> 2 is 4.00
    // Cost from node 3 -> 3 is 0.00
    // Cost from node 4 -> 0 is 7.00
    // Cost from node 4 -> 1 is 6.00
    // Cost from node 4 -> 2 is 3.00
    // Cost from node 4 -> 3 is 0.00
    // Cost from node 4 -> 4 is 0.00

    // The following is an example of a graph with a strongly connected 
    // component which contains a negative cycle. Anywhere this cycle 
    // can reach has an edge weight of negative infinity.

    // Initialize matrix to positive infinity between all pairs of
    // nodes except the diagonal entires which have cost zero
    for (double[] row : matrix) java.util.Arrays.fill(row, Double.POSITIVE_INFINITY);
    for(int i = 0; i < v; i++) matrix[i][i] = 0;

    matrix[0][1] = 3;
    matrix[0][2] = 1;
    matrix[0][3] = 8;
    matrix[1][0] = 2;
    matrix[1][2] = 9;
    matrix[1][3] = 4;

    // Connected component with negative cycle
    matrix[2][4] = -2;
    matrix[3][2] = 1;
    matrix[4][3] = 0;

    floydWarshall(matrix);

    System.out.println("Graph with negative cycle: ");
    for (int i = 0; i < v; i++)
      for (int j = 0; j < v; j++)
        if (matrix[i][j] != Double.POSITIVE_INFINITY)
          System.out.printf("Cost from node %d -> %d is %.2f\n", i,j,matrix[i][j]);
    System.out.println();

    // Prints: 
    // Graph with negative cycle: 
    // Cost from node 0 -> 0 is 0.00
    // Cost from node 0 -> 1 is 3.00
    // Cost from node 0 -> 2 is -Infinity
    // Cost from node 0 -> 3 is -Infinity
    // Cost from node 0 -> 4 is -Infinity
    // Cost from node 1 -> 0 is 2.00
    // Cost from node 1 -> 1 is 0.00
    // Cost from node 1 -> 2 is -Infinity
    // Cost from node 1 -> 3 is -Infinity
    // Cost from node 1 -> 4 is -Infinity
    // Cost from node 2 -> 2 is -Infinity
    // Cost from node 2 -> 3 is -Infinity
    // Cost from node 2 -> 4 is -Infinity
    // Cost from node 3 -> 2 is -Infinity
    // Cost from node 3 -> 3 is -Infinity
    // Cost from node 3 -> 4 is -Infinity
    // Cost from node 4 -> 2 is -Infinity
    // Cost from node 4 -> 3 is -Infinity
    // Cost from node 4 -> 4 is -Infinity


  }

}

