//QUESTION-1B
package lang;
import java.util.ArrayList;

public class Main {
    
    public static ArrayList<Integer> longestChain(int[] a) {
        int n = a.length;
        boolean[] visited = new boolean[n];
        boolean[] onStack = new boolean[n];
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, a, visited, onStack, result);
            }
        }
        
        return result.isEmpty() ? null : result;
    }
    
    private static void dfs(int i, int[] a, boolean[] visited, boolean[] onStack, ArrayList<Integer> result) {
        visited[i] = true;
        onStack[i] = true;
        
        int j = a[i];
        if (j >= 0) {
            if (!visited[j]) {
                dfs(j, a, visited, onStack, result);
            } else if (onStack[j]) {
                int k = i;
                result.add(k);
                while (k != j) {
                    k = a[k];
                    result.add(k);
                }
            }
        }
        
        onStack[i] = false;
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 1, 1, 5, 3};
        ArrayList<Integer> result = longestChain(a);
        System.out.println(result != null ? result : -1);
    }

}
