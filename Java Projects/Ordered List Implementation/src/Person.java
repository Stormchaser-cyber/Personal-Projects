/** General person class with basic information.*/
public class Person implements Comparable<Person> {

    /** static variable for creating a unique id */
    private static Integer nextID = 10001;
    private Integer getID() {
        return nextID++;
    }

    String lastName;
    String firstName;
    /** unique identification number */
    final Integer id;
    /** age in years */
    int age;
    /** birth month: 1=jan, 2=feb, ... */
    int birthMonth;

    /** static variable to determine how to order persons */
    private static String orderBy = "last";
    public static void orderBy(String field) { orderBy = field; }

    public Person(String last, String first, int age, int month) {
        lastName(last);
        firstName(first);
        age(age);
        birthMonth(month);
        id = getID();
    }

    public String toString() {
        return (id+": "+lastName+" "+firstName+" "+age+" years old. Bday month="+birthMonth);
    }

    @Override
    public int compareTo(Person other) {
        // compareTo by comparing last names -- works!
        if(orderBy.equals("last")) {
            // checking to see if the two objects are equal to each other
            if (this.equals(other)) {return 0; }

            // if the last names are the same use the first name as a tie breaker
            if (lastName.compareTo(other.lastName()) == 0) {
                // if other first name is bigger than first name at spot, return 1 to indicate it is bigger
                if(other.firstName().compareTo(firstName) < 0) { return 1; }
            }

            // if other person's last name is bigger return a 1 to indicate it is bigger
            if (other.lastName().compareTo(lastName) < 0) {return 1; }

            // otherwise the other person's last name is smaller than the current person's last name
            return -1;

        }

        // compareTo by comparing id numbers -- works!
        else if(orderBy.equals("id")) {
            // checking to see if the two objects are equal to each other
            if (this.equals(other)) {return 0; } // if the id numbers are the same - the equals function takes care of that

            // if other person's id number is bigger it should be later in the list
            if (other.getID().compareTo(id) > 0) {return 1; }

            // otherwise the other person's id number is smaller than the current person's id number
            return -1;

        }

        // compareTo by comparing age -- works!
        else if(orderBy.equals("age")) {
            // checking to see if the two objects are equal to each other
            if (this.equals(other)) {return 0; }

            // if other person's age number is bigger it should be later in the list
            if (other.age() < age) {return 1; } // I did this but like we're using numbers - other.age().compareTo(age) < 0
                                                // it's been a rough week LMAO

            // otherwise the other person's age number is smaller than the current person's age number
            return -1;

        }

        // Defaulting to last name sorting if orderBy is invalid input -- not fully tested yet
        else {
            System.out.println("Did not select a valid sorting option, it will be sorted by last name");
            orderBy("last");
            int returning_value = compareTo(other);
            return returning_value;
        }
    } // end compareTo(Person other)

    /**
     * Testing to see if objects are equal to each other or not
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        // checking to see if comparison with itself (memory location)
        if(other == this) return true;

        // checking to see if it is a person object
        if(!(other instanceof Person)) return false;

        // typecasting parameter into Person object
        Person otherPerson = (Person) other;

        // Then check if the id's are the same (and only the id's)
        if (id.equals(otherPerson.getID())) return true;


        // Look at the Song.equals method for an example.
        return false;
    }

    public void lastName(String n) { lastName = n; }
    public String lastName() { return lastName; }

    public void firstName(String n) { firstName = n; }
    public String firstName() { return firstName; }

    // getter only for ID
    public int id() { return id; }

    public void age(int a) { age = a;}
    public int age() { return age; }

    public void birthMonth(int m) {
        if (m<1 || m>12) { return;}
        birthMonth = m;
    }
    public int birthMonth() { return birthMonth; }


}
