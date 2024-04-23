import java.util.Scanner;

public class DirectedGraphChecker {
    /**
     * Time Complexity: O(N^2), where N is the size of the matrix.
     * The function iterates through each element of the matrix once, resulting in quadratic time complexity.
     *
     * Space Complexity: O(1)
     * The space used is constant, regardless of the size of the matrix.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the matrix: ");
        int n = scanner.nextInt();

        System.out.println("Enter the matrix elements:");
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        boolean isDirectedGraph = isDirectedGraph(matrix, n);
        if (isDirectedGraph) {
            System.out.println("The matrix represents a directed graph.");
        } else {
            System.out.println("The matrix does not represent a directed graph.");
        }
    }
    /**
     * Time Complexity: O(N^2), where N is the number of rows or columns in the matrix.
     * The function iterates through each element of the matrix once in two nested loops.
     *
     * Space Complexity: O(1), since the space used is independent of the input size.
     * The function only uses a constant amount of extra space regardless of the input size.
     */
    public static boolean isDirectedGraph(int[][] matrix, int n) {
        // Check if the matrix is square
        if (matrix.length != n || matrix[0].length != n) {
            return false;
        }

        // Check if the matrix has only 0 and 1 as elements
        for (int[] row : matrix) {
            for (int element : row) {
                if (element != 0 && element != 1) {
                    return false;
                }
            }
        }

        // Check if the matrix satisfies the directed graph properties
        // (i.e., if it represents a directed graph)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Check if there is an edge from vertex i to vertex j
                // in a directed graph, i.e., if matrix[i][j] is 1
                // and matrix[j][i] is 0
                if (matrix[i][j] == 1 && matrix[j][i] == 1) {
                    return false; // Edge in both directions, not a directed graph
                }
            }
        }

        return true;
    }
}
