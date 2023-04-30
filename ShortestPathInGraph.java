//QUESTION_-5A
package lang;

import java.util.*;

public class ShortestPathInGraph {
    public static int shortestPath(int[][] edges, int src, int dest) {
        // create an adjacency list to represent the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            graph.get(from).add(to);
        }

        // perform BFS to find the shortest path
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(src);
        visited.add(src);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == dest) {
                    return level;
                }
                if (graph.containsKey(curr)) {
                    for (int neighbor : graph.get(curr)) {
                        if (!visited.contains(neighbor)) {
                            queue.offer(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
            level++;
        }

        return -1; // destination is not reachable from source
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,3},{1,2},{3,4},{4,2}};
        int src = 0, dest = 2;
        int shortestPathLength = shortestPath(edges, src, dest);
        System.out.println("Shortest path length from " + src + " to " + dest + " is " + shortestPathLength);
    }
}
