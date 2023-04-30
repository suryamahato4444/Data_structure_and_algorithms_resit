//QUESTION-5B

package lang;

public class MaxOnesRowIndex {
    public static int findMaxOnesRowIndex(int[][] matrix) {
        int maxRowIndex = 0;
        int maxOnesCount = 0;
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == 1) {
                maxRowIndex = row;
                maxOnesCount++;
                col--;
            } else {
                row++;
            }
        }
        return maxRowIndex;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {0, 0, 0, 1},
            {1, 1, 1, 1},
            {0, 0, 1, 1},
            {0, 1, 1, 1}
        };
        int maxRowIndex = findMaxOnesRowIndex(matrix);
        System.out.println("Maximum ones count row index: " + maxRowIndex);
    }
}
