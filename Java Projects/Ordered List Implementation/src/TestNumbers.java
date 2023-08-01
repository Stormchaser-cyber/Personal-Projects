/** README.md
 - Create find that is an iterative binary search.
 - Create findRecursive that is a recursive binary search.
 - Test your implementation with numbers test file.
 - Finish your in-class work on Person
 - Test your implementation with Person test file.
 */

public class TestNumbers {
    public static void main(String[] args) {
        testAdd();
        testFind();
        testFindRecursive();
        System.out.println("\nNumbers Tests Complete ----\n");
    }

    public static void testAdd() {

        System.out.println("\ntesting order for add ...");

        // Create a new list
        OrderedList<Integer> numbers = new OrderedList<Integer>();

        // Add some people
        numbers.add(30);
        numbers.add(10);
        numbers.add(40);
        numbers.add(20);

        // Testing order maintained when adding numbers.
        assertEquals(10, numbers.peek(0),"peek(0)");
        assertEquals(20, numbers.peek(1),"peek(1)");
        assertEquals(30, numbers.peek(2),"peek(2)");
        assertEquals(40, numbers.peek(3),"peek(3)");

        System.out.println("numbers\n"+numbers);
    }

    public static void testFind() {

        System.out.println("\ntesting find (non-recursive) ...");

        OrderedList<Integer> numbers = new OrderedList<Integer>();
        numbers.add(30);
        numbers.add(10);
        numbers.add(40);
        numbers.add(20);

        assertEquals(null,numbers.find(2),"find something not there");
        assertEquals(10,numbers.find(10),"find first");
        assertEquals(40,numbers.find(40),"find last");
        assertEquals(20,numbers.find(20),"find middle (even)");
        numbers.add(25);
        assertEquals(25,numbers.find(25),"find middle (odd)");
    }

    public static void testFindRecursive() {

        System.out.println("\ntesting find (recursive) ...");

        OrderedList<Integer> numbers = new OrderedList<Integer>();
        numbers.add(30);
        numbers.add(10);
        numbers.add(40);
        numbers.add(20);

        assertEquals(null,numbers.findRecursive(2),"findR something not there");
        assertEquals(10,numbers.findRecursive(10),"findR first");
        assertEquals(40,numbers.findRecursive(40),"findR last");
        assertEquals(20,numbers.findRecursive(20),"find middle (odd)");
        numbers.add(35);
        assertEquals(35,numbers.findRecursive(35),"findR middle (even)");
    }


    public static void assertEquals(Object expect, Object result, String msg) {
        if (null==expect && null==result) {
            return;
        } else if ((null==expect && null!=result) || !expect.equals(result)) {
            System.out.println("ERROR:"+msg+". Expected "+expect+". Result "+result);
        }
    } // end assertEquals
}
