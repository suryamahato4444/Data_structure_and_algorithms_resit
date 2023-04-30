//QUESTION-3B

package lang;

public class UniqueNumbers {
    public static int countUniqueNumbers(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isUnique(i)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isUnique(int num) {
        int[] digits = new int[10];
        while (num > 0) {
            int digit = num % 10;
            if (digits[digit] == 1) {
                return false;
            }
            digits[digit] = 1;
            num /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 137;
        int count = countUniqueNumbers(n);
        System.out.println(count);
    }
}
