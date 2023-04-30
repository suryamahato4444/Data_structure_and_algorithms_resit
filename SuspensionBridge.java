//QUESTION-3A

package lang;

import java.util.Arrays;

public class SuspensionBridge {
    public static int getMaxHeight(int[] a) {
        Arrays.sort(a); // sort the array in ascending order
        int n = a.length;
        int maxHeight = -1;
        for (int i = n-1; i >= 0; i--) {
            int height1 = a[i];
            int height2 = -1;
            for (int j = i-1; j >= 0; j--) {
                if (a[j] < height1/2) break; // no need to consider smaller poles
                if (a[j] > height2) height2 = a[j]; // update the second highest pole
                if (height1+height2 > maxHeight) maxHeight = height1+height2; // update maximum height
            }
        }
        return maxHeight;
    }

    public static void main(String[] args) {
        int[] a = {10, 5, 18, 8, 2, 7};
        System.out.println(getMaxHeight(a)); // prints 25
    }
}
