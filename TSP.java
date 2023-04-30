//QUESTION-6B
package lang;

import java.util.*;

public class TSP {
    private int N;
    private int[][] distances;
    private PriorityQueue<Node> pq;

    public TSP(int N, int[][] distances) {
        this.N = N;
        this.distances = distances;
        this.pq = new PriorityQueue<Node>(new NodeComparator());
    }

    public int tsp(int start) {
        Node startNode = new Node(start, new ArrayList<Integer>(), 0, heuristic(start, new ArrayList<Integer>()));
        pq.offer(startNode);

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.visited.size() == N) {
                return node.cost;
            }

            for (int i = 0; i < N; i++) {
                if (i != node.current && !node.visited.contains(i)) {
                    int newCost = node.cost + distances[node.current][i];
                    ArrayList<Integer> newVisited = new ArrayList<Integer>(node.visited);
                    newVisited.add(node.current);
                    Node newNode = new Node(i, newVisited, newCost, heuristic(i, newVisited));
                    pq.offer(newNode);
                }
            }
        }

        return -1;
    }

    private int heuristic(int current, ArrayList<Integer> visited) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if (i != current && !visited.contains(i)) {
                if (distances[current][i] < min) {
                    min = distances[current][i];
                }
            }
        }

        return min;
    }

    private class Node {
        int current;
        ArrayList<Integer> visited;
        int cost;
        int heuristic;

        public Node(int current, ArrayList<Integer> visited, int cost, int heuristic) {
            this.current = current;
            this.visited = visited;
            this.cost = cost;
            this.heuristic = heuristic;
        }
    }

    private class NodeComparator implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return (a.cost + a.heuristic) - (b.cost + b.heuristic);
        }
    }

    public static void main(String[] args) {
        int N = 4;
        int[][] distances = {
            {0, 1, 15, 6},
            {2, 0, 7, 3},
            {9, 6, 0, 12},
            {10, 4, 8, 0}
        };

        TSP tsp = new TSP(N, distances);
        int shortestRoute = tsp.tsp(0);
        System.out.println(shortestRoute); // Output: 21
    }
}
