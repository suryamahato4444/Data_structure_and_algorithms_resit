package lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static int[] findLongestChain(int[] a) {
        int n = a.length;
        boolean[] visited = new boolean[n];
        int[] path = new int[n];
        int[] result = new int[n];
        int maxLen = -1;
        int cycleStart = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int len = dfs(a, visited, path, i);
                if (len > 0) {
                    int startIndex = 0;
                    for (int j = 0; j < n; j++) {
                        if (path[j] == len) {
                            startIndex = j - len + 1;
                            break;
                        }
                    }
                    if (len > maxLen) {
                        maxLen = len;
                        cycleStart = startIndex;
                    }
                }
            }
        }
        if (maxLen == -1) {
            return new int[] {-1};
        } else {
            for (int i = 0; i < maxLen; i++) {
                result[i] = cycleStart + i;
            }
            return Arrays.copyOfRange(result, 0, maxLen);
        }
    }

    private static int dfs(int[] a, boolean[] visited, int[] path, int i) {
        if (visited[i]) {
            return path[i];
        }
        visited[i] = true;
        if (a[i] == -1) {
            path[i] = 0;
        } else {
            path[i] = dfs(a, visited, path, a[i]) + 1;
        }
        return path[i];
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 1, 1, 5, 3};
        int[] result = findLongestChain(a);
        if (result.length == 1 && result[0] == -1) {
            System.out.println("No cycle exists");
        } else {
            List<Integer> list = new ArrayList<>();
            for (int i : result) {
                list.add(a[i]);
            }
            System.out.println("Longest chain of connected distribution points: " + list);
        }
    }
}
