//QUESTION-7B
package lang;

public class CountWaysToSum {
    public static int countWaysToSum(int[] arr, int n, int sum) {
        int[][] dp = new int[sum + 1][n + 1];

        // Initializing first column of dp table to 1
        for (int i = 0; i <= sum; i++) {
            dp[i][0] = 0;
        }

        // Initializing first row of dp table to 1
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }

        // Fill the dp table in bottom up manner
        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= n; j++) {
                // Excluding current integer from sum
                dp[i][j] = dp[i][j - 1];

                // Including current integer in sum if it is less than or equal to sum
                if (arr[j - 1] <= i) {
                    dp[i][j] += dp[i - arr[j - 1]][j];
                }
            }
        }

        // Return the number of ways to get the desired sum
        return dp[sum][n];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7};
        int desiredSum = 8;
        int n = arr.length;
        System.out.println("Total number of ways to get desired sum: " + countWaysToSum(arr, n, desiredSum));
    }
}
