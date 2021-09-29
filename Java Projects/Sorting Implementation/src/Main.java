import java.io.IOException;
import java.util.Random;
import java.util.function.Function;
import java.util.Comparator;
import java.io.FileWriter;

/** Framework to test various sorting algorithms. */
public class Main {

    /************** 3 Comparators: simple alpha, simple numeric, integer ******/

    /** For some reason it can't access simple? */
    public static Comparator<Simple> byAlpha = new Comparator<Simple>() {
        @Override
        public int compare(Simple s1, Simple s2) {
            return s1.alpha().compareTo(s2.alpha());
        }
    };

    public static Comparator<Simple> byNumeric = new Comparator<Simple>() {
        @Override
        public int compare(Simple s1, Simple s2) {
            return s1.numeric().compareTo(s2.numeric());
        }
    };

    public static Comparator<Integer> defaultInts = new Comparator<Integer>() {
        @Override
        public int compare(Integer n1, Integer n2) {
            return n1 - n2;
        }
    };

    /**  random number generator, seeded with 10 for reproducability */
    static Random rng = new Random(10);

    /**  The maximum value to be stored in an array */
    static int maxValue = 100;

    /** Lambda function to get an index value from a string.
     It uses the first letter and maps it to an index in range 0-25. */
    static Function<Simple,Integer> getAlpha = obj -> Character.getNumericValue(obj.alpha().charAt(0)) - Character.getNumericValue('a');

    /** Lambda function to get value of numeric from the Simple object. */
    static Function<Simple,Integer> getNumeric = obj -> obj.numeric();

    /** Lambda function to get the Integer of an Integer, hence the name */
    static Function<Integer,Integer> identity = obj -> obj;

    /**********************************************************************/
    /**********************************************************************/
    /**********************************************************************/

    /** Example of data collection of a single comparison.
     You probably want to build a more sophisticated (and more automated) method of data collection. */
    public static void experiments() {

        System.out.println("\nEXPERIMENTING WITH SORTING INTEGERS");
        System.out.println("\nCounting Sort");

        // You can run DatasetMaker to create all necessary files prior
        // to running experiments, or you can make them as needed as shown below.

        Integer[] numbers;
        DataMaker.makeInteger("test.txt",10000,DataMaker.Config.LARGE);
        numbers = DataReader.readIntegers("test.txt",10000);

        System.out.print("PRE-SORTING  ");
        // print the first 20
        for (int i=0; i<20; i++) { System.out.print(numbers[i]+" "); }
        System.out.print("...");
        // print the last 20
        for (int i=numbers.length-21; i<numbers.length; i++) {
            System.out.print(numbers[i]+" ");
        }
        System.out.println();

        // Set up the sorting algorithm. This will display the elapsed time.
        Counting<Integer> countingAlgo = new Counting<>();

        // Use the max function to determine max value in the array
        countingAlgo.sort(numbers,max(numbers),identity);
        // Check the results, print the first 20
        System.out.print("SORTED  ");
        for (int i=0; i<20; i++) { System.out.print(numbers[i]+" ");}
        System.out.print("...");
        // print the last 20
        for (int i=numbers.length-21; i<numbers.length; i++) {
            System.out.print(numbers[i]+" ");
        }
        System.out.println();

        // ------------------------------   TEST MERGE  ---
        System.out.println("\nTESTING MERGE SORT");

        // need to reset the array because it was sorted.
        numbers = DataReader.readIntegers("test.txt",10000);

        System.out.println("PRE-SORTING ");
        // print the first 20
        for (int i=0; i<20; i++) { System.out.print(numbers[i]+" "); }
        System.out.print("...");
        // print the last 20
        for (int i=numbers.length-21; i<numbers.length; i++) {
            System.out.print(numbers[i]+" ");
        }
        System.out.println();


        // Set up the sorting algorithm
        Mergesort<Integer> mergeAlgo = new Mergesort<>();

        // sort the numbers using defaultInts comparator for Integers.
        mergeAlgo.sort(numbers,defaultInts);

        // Check the results
        System.out.print("SORTED  ");
        for (int i=0; i<20; i++) { System.out.print(numbers[i]+" "); }
        System.out.print("...");
        for (int i=numbers.length-21; i<numbers.length; i++) {
            System.out.print(numbers[i]+" ");
        }
        System.out.println();
    } // end experiments

    /**********************************************************************/
    /**********************************************************************/
    /*******     THe same main as before used to test algo correctness ****/
    /**********************************************************************/

    public static void main(final String[] args) throws IOException {
//        experiments();

        FileWriter writer = new FileWriter("C:\\Users\\tedst\\Desktop\\FinalResults.csv");
        writer.close();

        testingCountingAndRadixSort();
        testingCountingMergeAndQuickSortPart1();
//        testingCountingMergeAndQuickSortPart2();
        testCountingAndMergeSort();


        int maxInt = 20;
        boolean testCounting = false;
        boolean testRadix = false;
        boolean testMerge = false;

        Simple[] array = null;


        // ------------------------------   TEST COUNTING  ---
        if (testCounting) {

            System.out.println("\nTESTING COUNTING SORT");

            // Fill the array with random Simple objects
            array = new Simple[10];
            // numerical values will be in range 0 to maxInt
            fillArray(array, maxInt);


            // The original
            System.out.print("SORTING THIS ");
            for (Simple s : array) {
                System.out.print(s+" ");
            }
            System.out.println();

            // Set up the sorting algorithm
            Counting<Simple> countingAlgo = new Counting<>();

            // Sort the array alphabetically
            // Order is set by passing the getAlpha function.
            // Notice the max value is 26 (the number of letters in alphabet)
            countingAlgo.sort(array,26,getAlpha);

            // Check the results
            System.out.print("SORTED  ");

            for (Simple s : array) {
                System.out.print(s+" ");
            }
            System.out.println();

            // sort the array numerically
            // Order is set by passing the getNumeric function.
            countingAlgo.sort(array,max(array),getNumeric);
            // Check the results
            System.out.print("SORT NUMERIC ");
            for (Simple s : array) {
                System.out.print(s+" ");
            }
            System.out.println();
        } // end testCounting

        // ------------------------------   TEST RADIX  ---
        if (testRadix) {

            System.out.println("\nTESTING RADIX SORT");

            // Fill the array
            array = new Simple[10];
            fillArray(array, 1000);

            // Check the results
            System.out.print("SORTING THIS ");
            for (Simple s : array) {
                System.out.print(s+" ");
            }
            System.out.println();

            // Set up the sorting algorithm
            Radix radixAlgo = new Radix();

            // Sort the array alphabetically
            // 5 is the length of the string -- this is hard coded based on the
            // size of the for loop when randomly generating strins for Simple objects
            radixAlgo.sortAlpha(array,5);
            // Check the results
            System.out.print("SORT ALPHA ");
            for (Simple s : array) {
                System.out.print(s+" ");
            }
            System.out.println();

            // sort the array numerically
            // 3 is the max number of digits -- this is hard coded based on the
            // maxInt (currently set to 1000, which means in range 0 to 999)
            radixAlgo.sortNumeric(array,3);
            // Check the results
            System.out.print("SORT NUMERIC ");
            for (Simple s : array) {
                System.out.print(s+" ");
            }
            System.out.println();
        } // end testRadix

        // ------------------------------   TEST MERGE  ---
        if (testMerge) {

            System.out.println("\nTESTING MERGE SORT");

            // Fill the array
            array = new Simple[10];
            fillArray(array, 20);

            // Check the results
            System.out.print("SORTING THIS ");
            for (Simple s : array) {
                System.out.print(s+" ");
            }
            System.out.println();

            // Set up the sorting algorithm
            Mergesort<Simple> algo = new Mergesort<>();

            // Sort the array alphabetically
            algo.sort(array,byAlpha);

            // Check the results
            System.out.print("SORTED  ");
            for (Simple s : array) {
                System.out.print(s+" ");
            }
            System.out.println();

            // sort the array numerically
            algo.sort(array,byNumeric);
            // Check the results
            System.out.print("SORT NUMERIC ");
            for (Simple s : array) {
                System.out.print(s+" ");
            }
            System.out.println();
        } // end testMerge

    } // end main


    /**********************************************************************/
    /**********************************************************************/
    /**********************************************************************/


    /** Find the max value in the Integer array */
    public static Integer max(Integer[] array) {
        Integer max = array[0];
        for (Integer el : array) {
            if (el > max) {
                max = el;
            }
        }
        return max;
    } // end max

    /** Find the max value (Integer) in the Simple array. */
    public static Integer max(Simple[] array) {
        Integer max = array[0].numeric();
        for (Simple el : array) {
            if (el.numeric() > max) {
                max = el.numeric();
            }
        }
        return max;
    } // end max

    /** Fill the given array with values in range determined by max.
     @param max integer for numeric component of Simple.
     */
    public static void fillArray(Simple[] A, int max) {
        // ASCII value of char a
        int a = 97;
        char r;
        for (int i=0;i<A.length;i++) {
            // create a new simple object, placed in the array
            A[i] = new Simple();
            // randomly choose value for numeric part of new object
            A[i].numeric(rng.nextInt(max));
            // randomly generate a string of length 5 (arbitrarily chose 5)
            String alpha = "";
            for (int k=0; k<5; k++) {
                // random number corresponding to a letter. 0 is a, 1 is b, ...
                // add it to the value of a to get its ASCII value, then
                // get corresponding char
                r = (char) (rng.nextInt(26)+a);
                // concatenate to string by converting char to String
                alpha += String.valueOf(r);
            }
            // set the alpha component to this randomly generated string
            A[i].alpha(alpha);
        }
    } // end fillArray

    /** Overloaded function to fill Integer arrays */
    public static void fillArray(Integer[] A, int max) {
        for(int i = 0; i < A.length; i++) {
            A[i] = rng.nextInt(max- 1) + 1;
        }
    }

    public static void testingCountingAndRadixSort() throws IOException {
// -------------------------   CSV Writer ----------------
        FileWriter writer = new FileWriter("C:\\Users\\tedst\\Desktop\\CountingAndRadixSortResults.csv");
        writer.write("Algorithm, Dataset Size, Numeric/Alphanumeric?, Number of Operations, Amount of Time Taken (in microseconds),\n");

// -------------------------   TEST COUNTING and RADIX ---
        Simple[] array1 = null;
        Simple[] array2 = null;
        Simple[] array3 = null;
        Simple[] array4 = null;
        Simple[] array5 = null;
        int maxInt = 500;
        System.out.println("\nTESTING COUNTING and RADIX sort");

        array1 = new Simple[10];
        array2 = new Simple[25];
        array3 = new Simple[50];
        array4 = new Simple[100];
        array5 = new Simple[250];

        fillArray(array1, maxInt);
        fillArray(array2, maxInt);
        fillArray(array3, maxInt);
        fillArray(array4, maxInt);
        fillArray(array5, maxInt);

// test 1 --------------------------------------------------------------
        System.out.println("SORTING THIS:  ");
//        for(Simple s: array1) {
//            System.out.println(s + " ");
//        }

        Counting<Simple> countingAlgo1 = new Counting<>();
        Radix radixAlgo1 = new Radix();

        countingAlgo1.sort(array1, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array1) {
//            System.out.println(s + " ");
//        }

        radixAlgo1.sortAlpha(array1, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array1) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array1.length + ", Alphanumeric"
                + ", " + countingAlgo1.operations() + ", " + countingAlgo1.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array1.length + ", Alphanumeric"
                + ", " + radixAlgo1.operations() + ", " + radixAlgo1.elapsedTimeUS()
                + "\n");

        countingAlgo1.sort(array1, max(array1), getNumeric);

        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array1) {
//            System.out.println(s + " ");
//        }

        radixAlgo1.sortNumeric(array1, array1.length);

        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array1) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array1.length + ", Numeric"
                + ", " + countingAlgo1.operations() + ", " + countingAlgo1.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array1.length + ", Numeric"
                + ", " + radixAlgo1.operations() + ", " + radixAlgo1.elapsedTimeUS()
                + "\n");

// test 2 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Simple s: array2) {
//            System.out.println(s + " ");
//        }

        Counting<Simple> countingAlgo2 = new Counting<>();
        Radix radixAlgo2 = new Radix();

        countingAlgo2.sort(array2, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array2) {
//            System.out.println(s + " ");
//        }

        radixAlgo2.sortAlpha(array2, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array2) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array2.length + ", Alphanumeric"
                + ", " + countingAlgo2.operations() + ", " + countingAlgo2.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array2.length + ", Alphanumeric"
                + ", " + radixAlgo2.operations() + ", " + radixAlgo2.elapsedTimeUS()
                + "\n");

        countingAlgo2.sort(array2, max(array2), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array2) {
//            System.out.println(s + " ");
//        }

        radixAlgo2.sortNumeric(array2, array2.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array2) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array2.length + ", Numeric"
                + ", " + countingAlgo2.operations() + ", " + countingAlgo2.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array2.length + ", Numeric"
                + ", " + radixAlgo2.operations() + ", " + radixAlgo2.elapsedTimeUS()
                + "\n");

// test 3 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Simple s: array3) {
//            System.out.println(s + " ");
//        }

        Counting<Simple> countingAlgo3 = new Counting<>();
        Radix radixAlgo3 = new Radix();

        countingAlgo3.sort(array3, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array3) {
//            System.out.println(s + " ");
//        }

        radixAlgo3.sortAlpha(array3, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array3) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array3.length + ", Alphanumeric"
                + ", " + countingAlgo3.operations() + ", " + countingAlgo3.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array3.length + ", Alphanumeric"
                + ", " + radixAlgo3.operations() + ", " + radixAlgo3.elapsedTimeUS()
                + "\n");

        countingAlgo3.sort(array3, max(array3), getNumeric);

        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array3) {
//            System.out.println(s + " ");
//        }

        radixAlgo3.sortNumeric(array3, array3.length);

        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array3) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array3.length + ", Numeric"
                + ", " + countingAlgo3.operations() + ", " + countingAlgo3.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array3.length + ", Numeric"
                + ", " + radixAlgo3.operations() + ", " + radixAlgo3.elapsedTimeUS()
                + "\n");

// test 4 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Simple s: array4) {
//            System.out.println(s + " ");
//        }

        Counting<Simple> countingAlgo4 = new Counting<>();
        Radix radixAlgo4 = new Radix();

        countingAlgo4.sort(array4, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array4) {
//            System.out.println(s + " ");
//        }

        radixAlgo4.sortAlpha(array4, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array4) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array4.length + ", Alphanumeric"
                + ", " + countingAlgo4.operations() + ", " + countingAlgo4.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array4.length + ", Alphanumeric"
                + ", " + radixAlgo4.operations() + ", " + radixAlgo4.elapsedTimeUS()
                + "\n");

        countingAlgo4.sort(array4, max(array4), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array4) {
//            System.out.println(s + " ");
//        }

        radixAlgo4.sortNumeric(array4, array4.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array4) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array4.length + ", Numeric"
                + ", " + countingAlgo4.operations() + ", " + countingAlgo4.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array4.length + ", Numeric"
                + ", " + radixAlgo4.operations() + ", " + radixAlgo4.elapsedTimeUS()
                + "\n");

// test 5 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Simple s: array5) {
//            System.out.println(s + " ");
//        }

        Counting<Simple> countingAlgo5 = new Counting<>();
        Radix radixAlgo5 = new Radix();

        countingAlgo5.sort(array5, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array5) {
//            System.out.println(s + " ");
//        }

        radixAlgo5.sortAlpha(array5, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array5) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array5.length + ", Alphanumeric"
                + ", " + countingAlgo5.operations() + ", " + countingAlgo5.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array5.length + ", Alphanumeric"
                + ", " + radixAlgo5.operations() + ", " + radixAlgo5.elapsedTimeUS()
                + "\n");

        countingAlgo5.sort(array5, max(array5), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array5) {
//            System.out.println(s + " ");
//        }

        radixAlgo5.sortNumeric(array5, array5.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array5) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array5.length + ", Numeric"
                + ", " + countingAlgo5.operations() + ", " + countingAlgo5.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array5.length + ", Numeric"
                + ", " + radixAlgo5.operations() + ", " + radixAlgo5.elapsedTimeUS()
                + "\n");

// test 6 --------------------------------------------------------------
        // random generated file
        int randomArraySize1 = rng.nextInt(1000);
        Simple[] randomArray1 = new Simple[randomArraySize1];
        fillArray(randomArray1, 1250);

        Counting<Simple> countingAlgo6 = new Counting<>();
        Radix radixAlgo6 = new Radix();

        countingAlgo6.sort(randomArray1, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray1) {
//            System.out.println(s + " ");
//        }

        radixAlgo6.sortAlpha(randomArray1, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray1) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray1.length + ", Alphanumeric"
                + ", " + countingAlgo6.operations() + ", " + countingAlgo6.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray1.length + ", Alphanumeric"
                + ", " + radixAlgo6.operations() + ", " + radixAlgo6.elapsedTimeUS()
                + "\n");

        countingAlgo6.sort(randomArray1, max(randomArray1), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: randomArray1) {
//            System.out.println(s + " ");
//        }

        radixAlgo6.sortNumeric(randomArray1, randomArray1.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: randomArray1) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray1.length + ", Numeric"
                + ", " + countingAlgo6.operations() + ", " + countingAlgo6.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray1.length + ", Numeric"
                + ", " + radixAlgo6.operations() + ", " + radixAlgo6.elapsedTimeUS()
                + "\n");

// test 7 --------------------------------------------------------------
        // random generated file
        int randomArraySize2 = rng.nextInt(1500);
        Simple[] randomArray2 = new Simple[randomArraySize2];
        fillArray(randomArray2, 2000);

        Counting<Simple> countingAlgo7 = new Counting<>();
        Radix radixAlgo7 = new Radix();

        countingAlgo7.sort(randomArray2, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray2) {
//            System.out.println(s + " ");
//        }

        radixAlgo7.sortAlpha(randomArray2, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray2) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray2.length + ", Alphanumeric"
                + ", " + countingAlgo7.operations() + ", " + countingAlgo7.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray2.length + ", Alphanumeric"
                + ", " + radixAlgo7.operations() + ", " + radixAlgo7.elapsedTimeUS()
                + "\n");

        countingAlgo7.sort(randomArray2, max(randomArray2), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: randomArray2) {
//            System.out.println(s + " ");
//        }

        radixAlgo7.sortNumeric(randomArray2, randomArray2.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: randomArray2) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray2.length + ", Numeric"
                + ", " + countingAlgo7.operations() + ", " + countingAlgo7.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray2.length + ", Numeric"
                + ", " + radixAlgo7.operations() + ", " + radixAlgo7.elapsedTimeUS()
                + "\n");

// test 8 --------------------------------------------------------------
        // random generated file
        int randomArraySize3 = rng.nextInt(1500);
        Simple[] randomArray3 = new Simple[randomArraySize3];
        fillArray(randomArray3, 2000);

        Counting<Simple> countingAlgo8 = new Counting<>();
        Radix radixAlgo8 = new Radix();

        countingAlgo8.sort(randomArray3, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray3) {
//            System.out.println(s + " ");
//        }

        radixAlgo8.sortAlpha(randomArray3, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray3) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray3.length + ", Alphanumeric"
                + ", " + countingAlgo8.operations() + ", " + countingAlgo8.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray3.length + ", Alphanumeric"
                + ", " + radixAlgo8.operations() + ", " + radixAlgo8.elapsedTimeUS()
                + "\n");

        countingAlgo8.sort(randomArray3, max(randomArray3), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: randomArray3) {
//            System.out.println(s + " ");
//        }

        radixAlgo8.sortNumeric(randomArray3, randomArray3.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: randomArray3) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray3.length + ", Numeric"
                + ", " + countingAlgo8.operations() + ", " + countingAlgo8.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray3.length + ", Numeric"
                + ", " + radixAlgo8.operations() + ", " + radixAlgo8.elapsedTimeUS()
                + "\n");

// test 9 --------------------------------------------------------------
        // LARGE data file -- too large to include in dataset for my computer :( (but when run separately works!)
//        Simple[] numbers1 = new Simple[numbers.length];
//        for(Integer i: numbers) {
//            Simple simple = new Simple("a", i);
//            numbers1[i] = simple;
//        }
//
//        System.out.println("\nSORTING LARGE DATASET:  ");
//
//        Counting<Simple> countingLarge = new Counting<>();
//        Radix radixLarge = new Radix();
//
////        for(Simple s: numbers1) {
////            System.out.println(s + " ");
////        }
//
//        countingLarge.sort(numbers1, max(numbers), getNumeric);
//        radixLarge.sortNumeric(numbers1, numbers1.length);
//
//        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
////        for(Simple s: numbers1) {
////            System.out.println(s + " ");
////        }
//
//        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
////        for(Simple s: numbers1) {
////            System.out.println(s + " ");
////        }
//
//        writer.append("Counting Sort, " + numbers1.length + ", Numeric"
//                + ", " + countingLarge.operations() + ", " + countingLarge.elapsedTimeUS()
//                + "\n");
//        writer.append("Radix Sort, " + numbers1.length + ", Numeric"
//                + ", " + radixLarge.operations() + ", " + radixLarge.elapsedTimeUS()
//                + "\n");

        writer.close();

    }

    public static void testingCountingMergeAndQuickSortPart1() throws IOException {
// -------------------------   CSV Writer ----------------
        FileWriter writer = new FileWriter("C:\\Users\\tedst\\Desktop\\CountingMergeAndQuickSortResultsPart1.csv");
        writer.write("Algorithm, Dataset Size, Numeric/Alphanumeric?, Number of Operations, Amount of Time Taken (in microseconds),\n");

// -------------   TEST COUNTING, MERGE, AND QUICKSORT ---
        Simple[] array6 = null;
        Simple[] array7 = null;
        Simple[] array8 = null;
        Simple[] array9 = null;
        Simple[] array10 = null;

        System.out.println("\nTESTING COUNTING, MERGE and QUICK sort");
        // 5 Different sized arrays
        array6 = new Simple[10];
        array7 = new Simple[25];
        array8 = new Simple[50];
        array9 = new Simple[100];
        array10 = new Simple[250];

        int maxInt = 500;

        fillArray(array6, maxInt);
        fillArray(array7, maxInt);
        fillArray(array8, maxInt);
        fillArray(array9, maxInt);
        fillArray(array10, maxInt);

// test 1 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Simple s: array6) {
//            System.out.println(s + " ");
//        }

        Counting<Simple> countingAlgo11 = new Counting<>();
        Radix radixAlgo11 = new Radix();
        Mergesort mergeAlgo11 = new Mergesort();

        countingAlgo11.sort(array6, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array6) {
//            System.out.println(s + " ");
//        }

        radixAlgo11.sortAlpha(array6, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array6) {
//            System.out.println(s + " ");
//        }

        mergeAlgo11.sort(array6, byAlpha);
        System.out.println("\nMERGE SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array6) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array6.length + ", Alphanumeric"
                + ", " + countingAlgo11.operations() + ", " + countingAlgo11.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array6.length + ", Alphanumeric"
                + ", " + radixAlgo11.operations() + ", " + radixAlgo11.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array6.length + ", Alphanumeric"
                + ", " + mergeAlgo11.operations() + ", " + mergeAlgo11.elapsedTimeUS()
                + "\n");

        countingAlgo11.sort(array6, max(array6), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array6) {
//            System.out.println(s + " ");
//        }

        radixAlgo11.sortNumeric(array6, array6.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array6) {
//            System.out.println(s + " ");
//        }

        mergeAlgo11.sort(array6, byNumeric);
        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
//        for(Simple s: array6) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array6.length + ", Numeric"
                + ", " + countingAlgo11.operations() + ", " + countingAlgo11.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array6.length + ", Numeric"
                + ", " + radixAlgo11.operations() + ", " + radixAlgo11.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array6.length + ", Numeric"
                + ", " + mergeAlgo11.operations() + ", " + mergeAlgo11.elapsedTimeUS()
                + "\n");

// test 2 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Simple s: array7) {
//            System.out.println(s + " ");
//        }

        Counting<Simple> countingAlgo12 = new Counting<>();
        Radix radixAlgo12 = new Radix();
        Mergesort mergeAlgo12 = new Mergesort();

        countingAlgo12.sort(array7, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array7) {
//            System.out.println(s + " ");
//        }

        radixAlgo12.sortAlpha(array7, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array7) {
//            System.out.println(s + " ");
//        }

        mergeAlgo12.sort(array7, byAlpha);
        System.out.println("\nMERGE SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array7) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array7.length + ", Alphanumeric"
                + ", " + countingAlgo12.operations() + ", " + countingAlgo12.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array7.length + ", Alphanumeric"
                + ", " + radixAlgo12.operations() + ", " + radixAlgo12.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array7.length + ", Alphanumeric"
                + ", " + mergeAlgo12.operations() + ", " + mergeAlgo12.elapsedTimeUS()
                + "\n");

        countingAlgo12.sort(array7, max(array7), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array7) {
//            System.out.println(s + " ");
//        }

        radixAlgo12.sortNumeric(array7, array7.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array7) {
//            System.out.println(s + " ");
//        }

        mergeAlgo12.sort(array7, byNumeric);
        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
//        for(Simple s: array7) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array7.length + ", Numeric"
                + ", " + countingAlgo12.operations() + ", " + countingAlgo12.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array7.length + ", Numeric"
                + ", " + radixAlgo12.operations() + ", " + radixAlgo12.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array7.length + ", Numeric"
                + ", " + mergeAlgo12.operations() + ", " + mergeAlgo12.elapsedTimeUS()
                + "\n");

// test 3 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Simple s: array8) {
//            System.out.println(s + " ");
//        }

        Counting<Simple> countingAlgo13 = new Counting<>();
        Radix radixAlgo13 = new Radix();
        Mergesort mergeAlgo13 = new Mergesort();

        countingAlgo13.sort(array8, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array8) {
//            System.out.println(s + " ");
//        }

        radixAlgo13.sortAlpha(array8, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array8) {
//            System.out.println(s + " ");
//        }

        mergeAlgo13.sort(array8, byAlpha);
        System.out.println("\nMERGE SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array8) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array8.length + ", Alphanumeric"
                + ", " + countingAlgo13.operations() + ", " + countingAlgo13.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array8.length + ", Alphanumeric"
                + ", " + radixAlgo13.operations() + ", " + radixAlgo13.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array8.length + ", Alphanumeric"
                + ", " + mergeAlgo13.operations() + ", " + mergeAlgo13.elapsedTimeUS()
                + "\n");

        countingAlgo13.sort(array8, max(array8), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array8) {
//            System.out.println(s + " ");
//        }

        radixAlgo13.sortNumeric(array8, array8.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array8) {
//            System.out.println(s + " ");
//        }

        mergeAlgo13.sort(array8, byNumeric);
        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
//        for(Simple s: array8) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array8.length + ", Numeric"
                + ", " + countingAlgo13.operations() + ", " + countingAlgo13.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array8.length + ", Numeric"
                + ", " + radixAlgo13.operations() + ", " + radixAlgo13.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array8.length + ", Numeric"
                + ", " + mergeAlgo13.operations() + ", " + mergeAlgo13.elapsedTimeUS()
                + "\n");

// test 4 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Simple s: array9) {
//            System.out.println(s + " ");
//        }

        Counting<Simple> countingAlgo14 = new Counting<>();
        Radix radixAlgo14 = new Radix();
        Mergesort mergeAlgo14 = new Mergesort();

        countingAlgo14.sort(array9, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array9) {
//            System.out.println(s + " ");
//        }

        radixAlgo14.sortAlpha(array9, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array9) {
//            System.out.println(s + " ");
//        }

        mergeAlgo14.sort(array9, byAlpha);
        System.out.println("\nMERGE SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array9) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array9.length + ", Alphanumeric"
                + ", " + countingAlgo14.operations() + ", " + countingAlgo14.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array9.length + ", Alphanumeric"
                + ", " + radixAlgo14.operations() + ", " + radixAlgo14.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array9.length + ", Alphanumeric"
                + ", " + mergeAlgo14.operations() + ", " + mergeAlgo14.elapsedTimeUS()
                + "\n");

        countingAlgo14.sort(array9, max(array9), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array9) {
//            System.out.println(s + " ");
//        }

        radixAlgo14.sortNumeric(array9, array9.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array9) {
//            System.out.println(s + " ");
//        }

        mergeAlgo14.sort(array9, byNumeric);
        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
//        for(Simple s: array9) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array9.length + ", Numeric"
                + ", " + countingAlgo14.operations() + ", " + countingAlgo14.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array9.length + ", Numeric"
                + ", " + radixAlgo14.operations() + ", " + radixAlgo14.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array9.length + ", Numeric"
                + ", " + mergeAlgo14.operations() + ", " + mergeAlgo14.elapsedTimeUS()
                + "\n");

// test 5 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        Counting<Simple> countingAlgo15 = new Counting<>();
        Radix radixAlgo15 = new Radix();
        Mergesort mergeAlgo15 = new Mergesort();

        countingAlgo15.sort(array10, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        radixAlgo15.sortAlpha(array10, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        mergeAlgo15.sort(array10, byAlpha);
        System.out.println("\nMERGE SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array10.length + ", Alphanumeric"
                + ", " + countingAlgo15.operations() + ", " + countingAlgo15.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array10.length + ", Alphanumeric"
                + ", " + radixAlgo15.operations() + ", " + radixAlgo15.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array10.length + ", Alphanumeric"
                + ", " + mergeAlgo15.operations() + ", " + mergeAlgo15.elapsedTimeUS()
                + "\n");

        countingAlgo15.sort(array10, max(array10), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        radixAlgo15.sortNumeric(array10, array10.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        mergeAlgo15.sort(array10, byNumeric);
        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array10.length + ", Numeric"
                + ", " + countingAlgo15.operations() + ", " + countingAlgo15.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array10.length + ", Numeric"
                + ", " + radixAlgo15.operations() + ", " + radixAlgo15.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array10.length + ", Numeric"
                + ", " + mergeAlgo15.operations() + ", " + mergeAlgo15.elapsedTimeUS()
                + "\n");

// test 9 -------------------------------------------------------------
        // sort SORTED data file (sorted Alphanumerically) - resorting a sorted dataset
        countingAlgo15.sort(array10, max(array10), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        radixAlgo15.sortNumeric(array10, array10.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        mergeAlgo15.sort(array10, byNumeric);
        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array10.length + ", Numeric"
                + ", " + countingAlgo15.operations() + ", " + countingAlgo15.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array10.length + ", Numeric"
                + ", " + radixAlgo15.operations() + ", " + radixAlgo15.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array10.length + ", Numeric"
                + ", " + mergeAlgo15.operations() + ", " + mergeAlgo15.elapsedTimeUS()
                + "\n");

// test 10 ------------------------------------------------------------
        // sort REVERSE data file (sorted Numerically) - Reverse sorting a dataset
        countingAlgo15.sort(array10, max(array10), getAlpha);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        radixAlgo15.sortNumeric(array10, array10.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        mergeAlgo15.sort(array10, byAlpha);
        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
//        for(Simple s: array10) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array10.length + ", Alphanumeric"
                + ", " + countingAlgo15.operations() + ", " + countingAlgo15.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + array10.length + ", Alphanumeric"
                + ", " + radixAlgo15.operations() + ", " + radixAlgo15.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array10.length + ", Alphanumeric"
                + ", " + mergeAlgo15.operations() + ", " + mergeAlgo15.elapsedTimeUS()
                + "\n");

        writer.close();
    }

    public static void testingCountingMergeAndQuickSortPart2() throws IOException {
// -------------------------   CSV Writer ----------------
        FileWriter writer = new FileWriter("C:\\Users\\tedst\\Desktop\\CountingMergeAndQuickSortResultsPart2.csv");
        writer.write("Algorithm, Dataset Size, Numeric/Alphanumeric?, Number of Operations, Amount of Time Taken (in microseconds),\n");

// test 6 -------------------------------------------------------------
        // random generated file
        int randomArraySize11 = rng.nextInt(10000);
        Simple[] randomArray11 = new Simple[randomArraySize11];
        fillArray(randomArray11, 12500);

        Counting<Simple> countingAlgo16 = new Counting<>();
        Radix radixAlgo16 = new Radix();
        Mergesort mergeAlgo16 = new Mergesort();

        countingAlgo16.sort(randomArray11, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray11) {
//            System.out.println(s + " ");
//        }

        radixAlgo16.sortAlpha(randomArray11, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray11) {
//            System.out.println(s + " ");
//        }

        mergeAlgo16.sort(randomArray11, byAlpha);
        System.out.println("\nMERGE SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray11) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray11.length + ", Alphanumeric"
                + ", " + countingAlgo16.operations() + ", " + countingAlgo16.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray11.length + ", Alphanumeric"
                + ", " + radixAlgo16.operations() + ", " + radixAlgo16.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + randomArray11.length + ", Alphanumeric"
                + ", " + mergeAlgo16.operations() + ", " + mergeAlgo16.elapsedTimeUS()
                + "\n");

        countingAlgo16.sort(randomArray11, max(randomArray11), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: randomArray11) {
//            System.out.println(s + " ");
//        }

        radixAlgo16.sortNumeric(randomArray11, randomArray11.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: randomArray11) {
//            System.out.println(s + " ");
//        }

        mergeAlgo16.sort(randomArray11, byNumeric);
        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
//        for(Simple s: randomArray11) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray11.length + ", Numeric"
                + ", " + countingAlgo16.operations() + ", " + countingAlgo16.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray11.length + ", Numeric"
                + ", " + radixAlgo16.operations() + ", " + radixAlgo16.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + randomArray11.length + ", Numeric"
                + ", " + mergeAlgo16.operations() + ", " + mergeAlgo16.elapsedTimeUS()
                + "\n");

        // test 7 -------------------------------------------------------------
        // random generated file
        int randomArraySize12 = rng.nextInt(10000);
        Simple[] randomArray12 = new Simple[randomArraySize12];
        fillArray(randomArray12, 12500);

        Counting<Simple> countingAlgo17 = new Counting<>();
        Radix radixAlgo17 = new Radix();
        Mergesort mergeAlgo17 = new Mergesort();

        countingAlgo17.sort(randomArray12, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray12) {
//            System.out.println(s + " ");
//        }

        radixAlgo17.sortAlpha(randomArray12, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray12) {
//            System.out.println(s + " ");
//        }

        mergeAlgo17.sort(randomArray12, byAlpha);
        System.out.println("\nMERGE SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray12) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray12.length + ", Alphanumeric"
                + ", " + countingAlgo17.operations() + ", " + countingAlgo17.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray12.length + ", Alphanumeric"
                + ", " + radixAlgo17.operations() + ", " + radixAlgo17.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + randomArray12.length + ", Alphanumeric"
                + ", " + mergeAlgo17.operations() + ", " + mergeAlgo17.elapsedTimeUS()
                + "\n");

        countingAlgo17.sort(randomArray12, max(randomArray12), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: randomArray12) {
//            System.out.println(s + " ");
//        }

        radixAlgo17.sortNumeric(randomArray12, randomArray12.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: randomArray12) {
//            System.out.println(s + " ");
//        }

        mergeAlgo17.sort(randomArray12, byNumeric);
        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
//        for(Simple s: randomArray12) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray12.length + ", Numeric"
                + ", " + countingAlgo17.operations() + ", " + countingAlgo17.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray12.length + ", Numeric"
                + ", " + radixAlgo17.operations() + ", " + radixAlgo17.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + randomArray12.length + ", Numeric"
                + ", " + mergeAlgo17.operations() + ", " + mergeAlgo17.elapsedTimeUS()
                + "\n");

// test 8 -------------------------------------------------------------
        // random generated file
        int randomArraySize13 = rng.nextInt(10000);
        Simple[] randomArray13 = new Simple[randomArraySize13];
        fillArray(randomArray13, 12500);

        Counting<Simple> countingAlgo18 = new Counting<>();
        Radix radixAlgo18 = new Radix();
        Mergesort mergeAlgo18 = new Mergesort();

        countingAlgo18.sort(randomArray13, 26, getAlpha);
        System.out.println("\nCOUNTING SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray13) {
//            System.out.println(s + " ");
//        }

        radixAlgo18.sortAlpha(randomArray13, 5);
        System.out.println("\nRADIX SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray13) {
//            System.out.println(s + " ");
//        }

        mergeAlgo18.sort(randomArray13, byAlpha);
        System.out.println("\nMERGE SORTED ALPHANUMERICALLY:  ");
//        for(Simple s: randomArray13) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray13.length + ", Alphanumeric"
                + ", " + countingAlgo18.operations() + ", " + countingAlgo18.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray13.length + ", Alphanumeric"
                + ", " + radixAlgo18.operations() + ", " + radixAlgo18.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + randomArray13.length + ", Alphanumeric"
                + ", " + mergeAlgo18.operations() + ", " + mergeAlgo18.elapsedTimeUS()
                + "\n");

        countingAlgo18.sort(randomArray13, max(randomArray13), getNumeric);
        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
//        for(Simple s: randomArray13) {
//            System.out.println(s + " ");
//        }

        radixAlgo18.sortNumeric(randomArray13, randomArray13.length);
        System.out.println("\nRADIX SORTED NUMERICALLY:  ");
//        for(Simple s: randomArray13) {
//            System.out.println(s + " ");
//        }

        mergeAlgo18.sort(randomArray13, byNumeric);
        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
//        for(Simple s: randomArray13) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + randomArray13.length + ", Numeric"
                + ", " + countingAlgo18.operations() + ", " + countingAlgo18.elapsedTimeUS()
                + "\n");
        writer.append("Radix Sort, " + randomArray13.length + ", Numeric"
                + ", " + radixAlgo18.operations() + ", " + radixAlgo18.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + randomArray13.length + ", Numeric"
                + ", " + mergeAlgo18.operations() + ", " + mergeAlgo18.elapsedTimeUS()
                + "\n");

        writer.close();
    }


    public static void testCountingAndMergeSort() throws IOException {
// -------------------------   CSV Writer ----------------
        FileWriter writer = new FileWriter("C:\\Users\\tedst\\Desktop\\CountingAndMergeSortResults.csv");
        writer.write("Algorithm, Dataset Size, Numeric/Alphanumeric?, Number of Operations, Amount of Time Taken (in microseconds),\n");

// ---------------------   TEST COUNTING and MERGESORT ---
        Integer[] array11 = null;
        Integer[] array12 = null;
        Integer[] array13 = null;
        Integer[] array14 = null;
        Integer[] array15 = null;

        System.out.println("\nTESTING COUNTING, MERGE and QUICK sort");
        // 5 Different sized arrays
        array11 = new Integer[10];
        array12 = new Integer[25];
        array13 = new Integer[50];
        array14 = new Integer[100];
        array15 = new Integer[250];
        int maxInt = 500;

        fillArray(array11, maxInt);
        fillArray(array12, maxInt);
        fillArray(array13, maxInt);
        fillArray(array14, maxInt);
        fillArray(array15, maxInt);

// test 1 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Integer s: array11) {
//            System.out.println(s + " ");
//        }
        Counting<Integer> countingAlgo111 = new Counting<>();
        Mergesort mergeAlgo111 = new Mergesort();

        countingAlgo111.sort(array11, max(array11), identity);
        System.out.println("\nCOUNTING SORT INTEGERS:  ");
//        for(Integer s: array11) {
//            System.out.println(s + " ");
//        }

        mergeAlgo111.sort(array11, defaultInts);
        System.out.println("\nMERGE SORTED INTEGERS:  ");
//        for(Integer s: array11) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array11.length + ", Numeric"
                + ", " + countingAlgo111.operations() + ", " + countingAlgo111.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array11.length + ", Numeric"
                + ", " + mergeAlgo111.operations() + ", " + mergeAlgo111.elapsedTimeUS()
                + "\n");

// test 2 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Integer s: array12) {
//            System.out.println(s + " ");
//        }

        Counting<Integer> countingAlgo112 = new Counting<>();
        Mergesort mergeAlgo112 = new Mergesort();

        countingAlgo112.sort(array12, max(array12), identity);
        System.out.println("\nCOUNTING SORT INTEGERS:  ");
//        for(Integer s: array12) {
//            System.out.println(s + " ");
//        }

        mergeAlgo112.sort(array12, defaultInts);
        System.out.println("\nMERGE SORTED INTEGERS:  ");
//        for(Integer s: array12) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array12.length + ", Numeric"
                + ", " + countingAlgo112.operations() + ", " + countingAlgo112.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array12.length + ", Numeric"
                + ", " + mergeAlgo112.operations() + ", " + mergeAlgo112.elapsedTimeUS()
                + "\n");

// test 3 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Integer s: array13) {
//            System.out.println(s + " ");
//        }
        Counting<Integer> countingAlgo113 = new Counting<>();
        Mergesort mergeAlgo113 = new Mergesort();

        countingAlgo113.sort(array13, max(array13), identity);
        System.out.println("\nCOUNTING SORT INTEGERS:  ");
//        for(Integer s: array13) {
//            System.out.println(s + " ");
//        }

        mergeAlgo113.sort(array13, defaultInts);
        System.out.println("\nMERGE SORTED INTEGERS:  ");
//        for(Integer s: array13) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array13.length + ", Numeric"
                + ", " + countingAlgo113.operations() + ", " + countingAlgo113.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array13.length + ", Numeric"
                + ", " + mergeAlgo113.operations() + ", " + mergeAlgo113.elapsedTimeUS()
                + "\n");

// test 4 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Integer s: array14) {
//            System.out.println(s + " ");
//        }

        Counting<Integer> countingAlgo114 = new Counting<>();
        Mergesort mergeAlgo114 = new Mergesort();

        countingAlgo114.sort(array14, max(array14), identity);
        System.out.println("\nCOUNTING SORT INTEGERS:  ");
//        for(Integer s: array14) {
//            System.out.println(s + " ");
//        }

        mergeAlgo114.sort(array14, defaultInts);
        System.out.println("\nMERGE SORTED INTEGERS:  ");
//        for(Integer s: array14) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array14.length + ", Numeric"
                + ", " + countingAlgo114.operations() + ", " + countingAlgo114.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array14.length + ", Numeric"
                + ", " + mergeAlgo114.operations() + ", " + mergeAlgo114.elapsedTimeUS()
                + "\n");

// test 5 --------------------------------------------------------------
        System.out.println("\nSORTING THIS:  ");
//        for(Integer s: array15) {
//            System.out.println(s + " ");
//        }

        Counting<Integer> countingAlgo115 = new Counting<>();
        Mergesort mergeAlgo115 = new Mergesort();

        countingAlgo115.sort(array15, max(array15), identity);
        System.out.println("\nCOUNTING SORT INTEGERS:  ");
//        for(Integer s: array15) {
//            System.out.println(s + " ");
//        }

        mergeAlgo115.sort(array15, defaultInts);
        System.out.println("\nMERGE SORTED INTEGERS:  ");
//        for(Integer s: array15) {
//            System.out.println(s + " ");
//        }

        writer.append("Counting Sort, " + array15.length + ", Numeric"
                + ", " + countingAlgo115.operations() + ", " + countingAlgo115.elapsedTimeUS()
                + "\n");
        writer.append("Merge Sort, " + array15.length + ", Numeric"
                + ", " + mergeAlgo115.operations() + ", " + mergeAlgo115.elapsedTimeUS()
                + "\n");

// test 6 --------------------------------------------------------------
        // LARGE data file -- too large to include in dataset for my computer :( (but when run separately works!)
//        System.out.println("\nSORTING LARGE DATASET:  ");
//        Mergesort mergeLarge = new Mergesort();
//
////        for(Simple s: numbers1) {
////            System.out.println(s + " ");
////        }
//
//        countingLarge.sort(numbers1, max(numbers), getNumeric);
//        System.out.println("\nCOUNTING SORT NUMERICALLY:  ");
////        for(Simple s: numbers1) {
////            System.out.println(s + " ");
////        }
//
//        mergeLarge.sort(numbers1, defaultInts);
//        System.out.println("\nMERGE SORTED NUMERICALLY:  ");
////        for(Simple s: numbers1) {
////            System.out.println(s + " ");
////        }
//
//        writer.append("Counting Sort, " + numbers1.length + ", Numeric"
//                + ", " + countingLarge.operations() + ", " + countingLarge.elapsedTimeUS()
//                + "\n");
//        writer.append("Merge Sort, " + numbers1.length + ", Numeric"
//                + ", " + mergeLarge.operations() + ", " + mergeLarge.elapsedTimeUS()
//                + "\n");

        writer.close();
    }



} // end class Main