import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import java.util.regex.*;

public class DirectedGraphImage {
    /**
     * Time Complexity: O(N), where N is the length of the input string 'input'.
     * Both 'parseVertexNames' and 'parsePositions' functions have a time complexity of O(N), and '
     * buildGraph' function has a time complexity of O(V), where V is the number of vertices in the graph.
     * Thus, the overall time complexity is O(N) since it dominates the time complexity.
     *
     * Space Complexity: O(N), where N is the length of the input string 'input'.
     * The space complexity is determined by the size of the arrays created by 'parseVertexNames' and
     * 'parsePositions', which hold vertex names and positions respectively. Additionally, the space used by the
     * created graph also contributes to the space complexity. In the worst case, the space required is proportional
     * to the length of the input string.
     */

    public static void main(String[] args) {
        String input = "[(I, 2) , (A, 5) , (E, 4) , (F,1) , (T, 2) , (S, 3)]";

        String[] vertices = parseVertexNames(input);
        int[] positions = parsePositions(input);

        Graph graph = buildGraph(vertices, positions);

        displayGraph(graph);
    }
    /**
     * Time Complexity: O(N), where N is the length of the input string 'input'.
     * The function iterates through the input string twice: once to count the number
     * of matches and once to extract the vertex names. Both iterations have a linear time complexity.
     *
     * Space Complexity: O(N), where N is the length of the input string 'input'.
     * The space complexity is determined by the size of the 'vertices' array, which holds
     * the extracted vertex names. In the worst case, the array will have a size proportional to the length of the input string.
     */
    public static String[] parseVertexNames(String input) {
        Pattern pattern = Pattern.compile("\\((\\w+),\\s*(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        int size = 0;
        while (matcher.find()) {
            size++;
        }

        String[] vertices = new String[size];
        int increment = 0;
        matcher.reset();
        while (matcher.find()) {
            String vertexName = matcher.group(1);
            vertices[increment++] = vertexName;
        }
        return vertices;
    }
    /**
     * Time Complexity: O(N), where N is the length of the input string 'input'.
     * The function iterates through the input string twice: once to count the number of matches
     * and once to extract the positions. Both iterations have a linear time complexity.
     *
     * Space Complexity: O(N), where N is the length of the input string 'input'.
     * The space complexity is determined by the size of the 'positions' array, which holds the extracted positions.
     * In the worst case, the array will have a size proportional to the length of the input string.
     */
    public static int[] parsePositions(String input) {
        Pattern pattern = Pattern.compile("\\((\\w+),\\s*(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        int size = 0;
        while (matcher.find()) {
            size++;
        }

        int[] positions = new int[size];
        int increment = 0;
        matcher.reset();
        while (matcher.find()) {
            int position = Integer.parseInt(matcher.group(2));
            positions[increment++] = position;
        }
        return positions;
    }
    /**
     * Time Complexity: O(V), where V is the number of vertices in the graph.
     * The function iterates through the 'vertices' array once to create nodes
     * in the graph and once to create edges between nodes. Both iterations have a linear time complexity.
     *
     * Space Complexity: O(V), where V is the number of vertices in the graph.
     * The space complexity is determined by the size of the 'vertices' array and the size of the created graph.
     * In the worst case, the space required is proportional to the number of vertices in the graph.
     */
    public static Graph buildGraph(String[] vertices, int[] positions) {
        Graph graph = new SingleGraph("DirectedGraph");

        for (String vertexName : vertices) {
            if (vertexName != null) {
                graph.addNode(vertexName);
                graph.getNode(vertexName).addAttribute("ui.label", vertexName);
            }
        }

        for (int i = 0; i < vertices.length; i++) {
            String vertexName = vertices[i];
            if (vertexName != null) {
                int leftIndex = (i - positions[i] + vertices.length) % vertices.length;
                int rightIndex = (i + positions[i]) % vertices.length;
                String leftVertexName = vertices[leftIndex];
                String rightVertexName = vertices[rightIndex];

                if (leftVertexName != null) {
                    graph.addEdge(vertexName + leftVertexName, vertexName, leftVertexName, true);
                }
                if (rightVertexName != null && !rightVertexName.equals(leftVertexName)) {
                    graph.addEdge(vertexName + rightVertexName, vertexName, rightVertexName, true);
                }
            }
        }
        return graph;
    }
    /**
     * Time Complexity: O(1)
     * The function performs a constant number of operations to set the stylesheet and display the graph.
     *
     * Space Complexity: O(1)
     * The space used is constant, regardless of the size of the graph.
     */
    public static void displayGraph(Graph graph) {
        graph.addAttribute("ui.stylesheet", "node { size: 20px; text-size: 15px; }");
        graph.display();
    }
}
