/**
 * Java implementation of Merge Sort
 */
public class MergeSort {
    public static int counter = 0; // counter to keep track of operations -- might be an overestimate
                                                                        // - IS an overestimate

    /**
     * Merge function that merges two sub-arrays
     * @param leftArray the left portion of the array to be merged
     * @param rightArray the right portion of the array to be merged
     * @param array the array that will store the merged values
     * @param leftSize the size of the left array
     * @param rightSize the size of the right array
     */
    public void merge(int[] leftArray, int[] rightArray, int[] array, int leftSize, int rightSize) {
        int i = 0, left = 0, right = 0;
        counter += 3; // this is for the initializations of the index variables

        // initial while loop
        while(left < leftSize && right < rightSize) {
            counter += 2; // this is for the two comparisons in the while loop
            if(leftArray[left] < rightArray[right]) { array[i++] = leftArray[left++]; counter++; }
            else { array[i++] = rightArray[right++] ; counter++;}
        }

        // while loop for iteration of left array
        while (left < leftSize) { array[i++] = leftArray[left++]; counter++;}

        // while loop for iteration of right array
        while (right < rightSize) { array[i++] = rightArray[right++]; counter++;}
    }

    /**
     * Sort function which sorts an array through implementation of merge sort
     * @param array the array to be sorted
     * @param length the length of said array to be sorted
     */
   public void sort(int[] array, int length) {
       // if we have length of 1 we can just return due to it already being sorted
        if(length < 2) { return;}
        counter++;

        // finding the mid point and breaking into two smaller arrays based on that point
        int mid = length / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[length - mid];
        int j = 0;
        counter += 4;

        // iterating throughout the array and copying items into the sub arrays
        for (int i = 0; i < length; ++i) {
            counter++;
            if (i < mid) { leftArray[i] = array[i]; counter++;}
            else { rightArray[j] = array[i]; j = j + 1; counter++;}
        }

        // recursive calls to make merge sort work!
        counter++;
        sort(leftArray, mid);
        counter++;
        sort(rightArray, length - mid);
        counter++;
        merge(leftArray, rightArray, array, mid, length - mid);
   }

}
