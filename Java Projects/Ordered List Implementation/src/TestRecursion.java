class TestRecursion {

    public static void main(String[] args) {

        int[] A = { 5, 4, 3, 2, 1};

        for (int i=0; i<A.length; i++) {
            System.out.println("sumUpTo("+i+")="+sumUpTo(A,i));
        }

        System.out.println("\nAll sum = "+sum(A,0,A.length-1));
    }

    /** Create a recursive algorithm to sum up to index n **/
    public static int sumUpTo(int[] A, int n) {
        // S(n) = A[n] + S(n-1)
        // Stopping Condition
        if (n <= 0) {
            return A[0];
        }
        return (sumUpTo(A, n-1) + A[n]);
    }

    /** Create a recursive algorithm to sum from start to end index */
    public static int sum(int[] A, int start, int end) {
        //S(a) = A[start] + A[end] + S(start + 1)
        // Stopping Condition
        if (start == end) {
            return A[end];
        }
        else {
            return (A[start] + sum(A, start+1, end));
        }

    }
}