package lang;

public class countSubarrays {
	public static int countSubarrays(int[] a) {
	    int count = 0;
	    for (int i = 0; i < a.length; i++) {
	        int sum = 0;
	        for (int j = i; j < a.length; j++) {
	            sum += a[j];
	            if (sum == a[i]) {
	                count++;
	            }
	        }
	    }
	    return count;
	}


}
