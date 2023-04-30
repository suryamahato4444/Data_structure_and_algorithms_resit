//QUESTION NO-2b
package lang;

import java.util.*;

public class CoinCollector {
    
    public static int maxCoins(int[] coins, int[][] edges) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        int maxCoins = 0;
        for (int start = 0; start < coins.length; start++) {
            // Collect coins starting from each room
            Set<Integer> visited = new HashSet<>();
            visited.add(start);
            int coinsCollected = coins[start];
            for (int neighbor : adj.get(start)) {
                visited.add(neighbor);
                coinsCollected += coins[neighbor];
                for (int neighbor2 : adj.get(neighbor)) {
                    if (!visited.contains(neighbor2)) {
                        visited.add(neighbor2);
                        coinsCollected += coins[neighbor2];
                        for (int neighbor3 : adj.get(neighbor2)) {
                            if (!visited.contains(neighbor3)) {
                                visited.add(neighbor3);
                                coinsCollected += coins[neighbor3];
                                // Update maxCoins if better solution found
                                maxCoins = Math.max(maxCoins, coinsCollected);
                                // Backtrack
                                visited.remove(neighbor3);
                                coinsCollected -= coins[neighbor3];
                            }
                        }
                        visited.remove(neighbor2);
                        coinsCollected -= coins[neighbor2];
                    }
                }
                visited.remove(neighbor);
                coinsCollected -= coins[neighbor];
            }
        }
        return maxCoins;
    }
    
    public static void main(String[] args) {
        int[] coins = {20, 20, 5, 15, 10};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {2, 4}, {2, 3}};
        int maxCoins = maxCoins(coins, edges);
        System.out.println("Maximum coins collected: " + maxCoins);
    }

}
