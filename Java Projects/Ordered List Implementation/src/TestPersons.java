/** README.md
 - Create find that is an iterative binary search.
 - Create findRecursive that is a recursive binary search.
 - Test your implementation with persons test file.
 - Finish your in-class work on Person
 - Test your implementation with Person test file.
 */

public class TestPersons {
    public static void main(String[] args) {

        testOrderLast(); // works
        testOrderAge(); // works
        testOrderID(); // works
        testFind(); // works
        testFindRecursive(); // works

    }

    /** ----------------------------------------------------------------- **/
    public static void testOrderLast() {

        System.out.println("\nTesting order by last name ...");

        // Add some people Person(last,first,age,month);
        // unique ID assigned at instantiation
        Person.orderBy("last");
        OrderedList<Person> persons= new OrderedList<Person>();
        persons.add(new Person("smith","jo",50,8));
        persons.add(new Person("lui","su",30,1));
        persons.add(new Person("nunes","mia",25,10));
        persons.add(new Person("nunes","isa",20,4));

        System.out.println("\n"+persons);

        // Testing order maintained by last name, then first when adding persons.
        Person test;
        test = persons.peek(0);
        assertEquals("lui", test.lastName(),"peek(0)");
        test = persons.peek(1);
        assertEquals("nunes",test.lastName(),"peek(1)");
        assertEquals("isa",test.firstName(),"peek(1)");
        test = persons.peek(2);
        assertEquals("nunes",test.lastName(),"peek(2)");
        assertEquals("mia",test.firstName(),"peek(2)");
        test = persons.peek(3);
        assertEquals("smith",test.lastName(),"peek(3)");
    } // end testOrderLast

    /** ----------------------------------------------------------------- **/
    public static void testOrderAge() {

        System.out.println("\nTesting order by age ...");

        Person.orderBy("age");
        OrderedList<Person> persons = new OrderedList<>();
        persons.add(new Person("nunes","mia",30,10));
        persons.add(new Person("smith","jo",20,8));
        persons.add(new Person("nunes","isa",50,4));
        persons.add(new Person("lui","su",25,1));

        System.out.println("\n"+persons);

        // Testing order maintained by age when adding persons.
        Person test;
        test = persons.peek(0);
        assertEquals(20, test.age(),"peek(0)");
        test = persons.peek(1);
        assertEquals(25,test.age(),"peek(1)");
        test = persons.peek(2);
        assertEquals(30,test.age(),"peek(2)");
        test = persons.peek(3);
        assertEquals(50,test.age(),"peek(3)");

    } // end testOrderAge

    /** ----------------------------------------------------------------- **/
    public static void testOrderID() {

        System.out.println("\nTesting order by ID ...");

        Person.orderBy("id");
        OrderedList<Person> persons = new OrderedList<>();
        persons.add(new Person("smith","jo",50,8));
        persons.add(new Person("nunes","isa",20,4));
        persons.add(new Person("lui","su",30,1));
        persons.add(new Person("nunes","mia",25,10));

        System.out.println("\n"+persons);

        // Testing order maintained by id when adding persons.
        // Not sure what ID each has, so using the age field to test order
        Person test;
        test = persons.peek(0);
        assertEquals(50, test.age(),"peek(0)");
        test = persons.peek(1);
        assertEquals(20,test.age(),"peek(1)");
        test = persons.peek(2);
        assertEquals(30,test.age(),"peek(2)");
        test = persons.peek(3);
        assertEquals(25,test.age(),"peek(3)");
    }

    /** ----------------------------------------------------------------- **/
    public static void testFind() {

        System.out.println("\nTesting find (non-recursive) ...");

        // Testing Find last name (non-recursive)
        Person.orderBy("age");
        OrderedList<Person> persons = new OrderedList<>();
        persons.add(new Person("smith","jo",60,8));
        persons.add(new Person("nunes","isa",20,4));
        persons.add(new Person("lui","su",40,1));
        persons.add(new Person("nunes","mia",30,10));

        Person test;
        test = new Person("x","y",0,0);
        assertEquals(null,persons.find(test),"find something not there");
        test = persons.peek(0);
        assertEquals(persons.peek(0),persons.find(test),"find first");
        test = persons.peek(3);
        assertEquals(persons.peek(3),persons.find(test),"find last");
        test = persons.peek(1);
        assertEquals(persons.peek(1),persons.find(test),"find middle (odd)");
        persons.add(new Person("singh","jai",35,1));
        persons.add(new Person("starr","val",45,1));
        test = persons.peek(1);
        assertEquals(persons.peek(1),persons.find(test),"find in middle");
    }

    /** ----------------------------------------------------------------- **/
    public static void testFindRecursive() {

        System.out.println("\nTesting find (recursive) ...");

        // Testing Find last name (non-recursive)
        Person.orderBy("age");
        OrderedList<Person> persons = new OrderedList<>();
        persons.add(new Person("smith","jo",50,8));
        persons.add(new Person("nunes","isa",20,4));
        persons.add(new Person("lui","su",30,1));
        persons.add(new Person("nunes","mia",25,10));

        Person test;
        test = new Person("x","y",0,0);
        assertEquals(null,persons.findRecursive(test),"find something not there");
        test = persons.peek(0);
        assertEquals(persons.peek(0),persons.findRecursive(test),"find first");
        test = persons.peek(3);
        assertEquals(persons.peek(3),persons.findRecursive(test),"find last");
        test = persons.peek(1);
        assertEquals(persons.peek(1),persons.findRecursive(test),"find middle (odd)");
        persons.add(new Person("singh","jai",35,1));
        persons.add(new Person("starr","val",45,1));
        test = persons.peek(1);
        assertEquals(persons.peek(1),persons.findRecursive(test),"find in middle");
    }

    /** ----------------------------------------------------------------- **/
    /** ----------------------------------------------------------------- **/
    public static void assertEquals(Object expect, Object result, String msg) {
        if (null==expect && null==result) {
            return;
        } else if ((null==expect && null!=result) || !expect.equals(result)) {
            System.out.println("ERROR:"+msg+". Expected "+expect+". Result "+result);
        }
    } // end assertEquals
}
