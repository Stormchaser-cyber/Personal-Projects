public class Main {

    public static void main(String args[]) {
        int[] array = {1,3,2,5,4,7,6,8,9,10,12,11,15,14,13}; // array of 15 values

        //Testing for size 1
        System.out.println("Cutting rod 1 time value is "
                + cutRodIterative(array, 1));

        //Testing for size 2
        System.out.println("Cutting rod 2 times value is "
                + cutRodIterative(array, 2));

        //Testing for size 3
        System.out.println("Cutting rod 3 times value is "
                + cutRodIterative(array, 3));

        //Testing for size 4
        System.out.println("Cutting rod 4 times value is "
                + cutRodIterative(array, 4));

        //Testing for size 5
        System.out.println("Cutting rod 5 times value is "
                + cutRodIterative(array, 5));

        //Testing for size 6
        System.out.println("Cutting rod 6 times value is "
                + cutRodIterative(array, 6));

        //Testing for size 7
        System.out.println("Cutting rod 7 times value is "
                + cutRodIterative(array, 7));

        //Testing for size 8
        System.out.println("Cutting rod 8 times value is "
                + cutRodIterative(array, 8));

        //Testing for size 9
        System.out.println("Cutting rod 9 times value is "
                + cutRodIterative(array, 9));

        //Testing for size 10
        System.out.println("Cutting rod 10 times value is "
                + cutRodIterative(array, 10));

    }
    public static int cutRodRecursive(int[] p, int n) {
        if(n == 0) {
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            q = Math.max(q, p[i] + cutRodRecursive(p, n-1));
        }

        return q;
    }

    public static int cutRodIterative(int price[], int n) {
        int val[] = new int[n+1];
        val[0] = 0;

        // Build the table val[] in bottom up manner and return
        // the last entry from the table
        for (int i = 1; i<=n; i++)
        {
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++)
                max_val = Math.max(max_val,
                        price[j] + val[i-j-1]);
            val[i] = max_val;
        }

        return val[n];
    }
}
