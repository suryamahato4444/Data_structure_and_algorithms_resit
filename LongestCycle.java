//QUESTION-1A

package lang;
import java.util.*;

public class LongestCycle {
    public static List<Integer> findLongestCycle(int[] a) {
        int n = a.length;
        boolean[] visited = new boolean[n];
        boolean[] onStack = new boolean[n];
        int[] edgeTo = new int[n];
        Stack<Integer> cycle = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, a, visited, onStack, edgeTo, cycle);
            }
        }

        if (cycle.isEmpty()) {
            return Collections.singletonList(-1);
        } else {
            List<Integer> result = new ArrayList<>();
            int start = cycle.pop();
            result.add(start);
            int current = cycle.pop();
            while (current != start) {
                result.add(current);
                current = cycle.pop();
            }
            result.add(start);
            Collections.reverse(result);
            return result;
        }
    }

    private static void dfs(int v, int[] a, boolean[] visited, boolean[] onStack, int[] edgeTo, Stack<Integer> cycle) {
        visited[v] = true;
        onStack[v] = true;

        int w = a[v];
        if (w >= 0 && !visited[w]) {
            edgeTo[w] = v;
            dfs(w, a, visited, onStack, edgeTo, cycle);
        } else if (onStack[w]) {
            for (int x = v; x != w; x = edgeTo[x]) {
                cycle.push(x);
            }
            cycle.push(w);
            cycle.push(v);
        }

        onStack[v] = false;
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 1, 1, 5, 3};
        List<Integer> result = findLongestCycle(a);
        System.out.println(result);
    }
}
