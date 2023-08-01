import java.util.*;

public class Main {
    public static void main(String[] args) {

        Tree<Integer> tree = new Tree<>();
        BinaryNode n = null;

        // root
        tree.add(5);
        // all to the left
        tree.add(2); tree.add(1); tree.add(3);
        // all to the right
        tree.add(8); tree.add(6); tree.add(10);

        System.out.println("\nRoot "+tree.root());

        n = tree.find(2);
        if (null != n) {
            System.out.println("\nIterative node 2 "+n);
            System.out.println("LRP "+n.left()+" "+n.right()+" "+n.parent());
        }

        n = tree.findRecursive(2);
        if (null != n) {
            System.out.println("\nRecursive node 2 "+n);
            System.out.println("LRP "+n.left()+" "+n.right()+" "+n.parent());
        }

        System.out.print("\ntoListArray ");
        ListArray<Integer> array = tree.toListArray();
        System.out.println(array);

        System.out.println("\nPrinting BST");
        tree.print();

        System.out.println("\nVisualize BST");
        tree.visualize();

        System.out.println("\nBalance then Visualize");
        tree.balance();
        tree.visualize();

        System.out.println("\nReorder then Visualize");
        tree.reorder(comp);
        tree.visualize();


    }

    public static Comparator<Integer> comp = new Comparator<Integer>() {
        @Override
        public int compare(Integer n1, Integer n2) {
            return n1-n2;
        }
    };
}