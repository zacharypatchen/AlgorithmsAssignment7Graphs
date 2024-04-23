import java.util.*;

public class SimplePathFinder {
    private static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    /**
     * Time Complexity: O(V^5), where V is the number of vertices in the graph.
     * The function calls the 'findSimplePaths' method, which explores all possible
     * paths of length 5 starting from the source vertex 'u'. Since each vertex can have at most V neighbors,
     * and the function recursively explores each neighbor at most 5 times, the time complexity is O(V^5).
     *
     * Space Complexity: O(V), where V is the number of vertices in the graph.
     * The space complexity is determined by the size of the 'path' list, which can hold at most 5 vertices
     * in the worst case.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        // Create adjacency list to represent the graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Input the edges (u, v) and their weights
        System.out.println("Enter the edges and their weights (u v weight):");
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.get(u).add(new Edge(v, weight));
        }

        // Input the source and destination vertices
        System.out.print("Enter the start vertex: ");
        int u = scanner.nextInt();
        System.out.print("Enter the destination vertex: ");
        int w = scanner.nextInt();

        // Find and print all simple paths from u to w with a length of 5
        System.out.println("Simple paths from " + u + " to " + w + " with length 5:");
        List<Integer> path = new ArrayList<>();
        path.add(u);
        findSimplePaths(graph, u, w, 5, path);
    }
    /**
     * Time Complexity: O(V^5), where V is the number of vertices in the graph.
     * The function explores all possible paths of length 5 starting from vertex 'u'.
     * Since each vertex can have at most V neighbors, and the function recursively explores each neighbor
     * at most 5 times, the time complexity is O(V^5).
     *
     * Space Complexity: O(V), where V is the number of vertices in the graph.
     * The space complexity is determined by the size of the 'path' list, which can hold at most 5 vertices in the worst case.
     */
    private static void findSimplePaths(List<List<Edge>> graph, int u, int w, int length, List<Integer> path) {
        if (length == 0 && u == w) {
            // Found a simple path of length 5 from u to w
            System.out.println(path);
            return;
        }

        if (length <= 0) {
            // Path length exceeds 5
            return;
        }

        // Explore neighbors of the current vertex
        for (Edge edge : graph.get(u)) {
            int v = edge.to;
            path.add(v);
            findSimplePaths(graph, v, w, length - 1, path);
            path.remove(path.size() - 1);
        }
    }
}
