import java.util.*;

/** Binary Search Tree storing type T */
public class Tree <T extends Comparable> {


    /** Node type for elements of BST. Note the inclusion of a parent pointer */
    public class Node {
        T data = null;
        Node right = null;
        Node left = null;
        Node parent = null;

        // Node constructor
        Node(T val) {
            data = val;
        }

        // Below I am using a conditional operator. It isn't the easiest to read,
        // so maybe not the best to use, but now you know about it!
        // The form is: (boolean test) ? (if true value) : (if False value)
        // the below line, rewritten:
        // T result;
        // if (right!=null) result=right.data
        // else result = null;
        // return result;

        /** Getter for right node */
        T right() { return (right!=null) ? right.data : null; }
        /** Getter for left node */
        T left() { return (left!=null) ? left.data : null; }
        /** Getter for parent node */
        T parent() { return (parent!=null) ? parent.data : null; }
    } // end class Node

    /** Root of binary search tree */
    private Node root = null;

    /** Number of nodes in the tree */
    private int numberOfNodes = 0;

    /** The order used to build the tree. Use T compareTo as default. */
    private Comparator<T> sortOrder = new Comparator<T>() {
        @Override
        public int compare(T obj1, T obj2) {
            return obj1.compareTo(obj2);
        }
    };

    /** Root getter */
    public Node root() { return root; }

    /** Add a node to the tree. Use sortOrder to determine BST ordering.
     * @param object : value to be stored in tree
     */
    public void add(T object) {
        // if the tree is empty then add to the first spot
        if (root == null) {
            // creating new node
            root = new Node(object);

            // updating node's attributes
            root.left = null;
            root.right = null;
            root.parent = null; // when using the root, the parent will always be null (unless you want it self referencing, in which case just change this line to "root.parent = root;")
        }
        else {
            // Initializing nodes to use for holding new data, old data, and transferring data between the two
            Node currentNode = root;
            Node node = null;

            // iterating through BST
            while(currentNode != null) {
                // if object < current data, use left branch
                if(sortOrder.compare(object, currentNode.data) < 0) {
                    // if left branch is null, add to branch
                    if(currentNode.left == null) {
                        // creating and initializing node's attributes (left, right, and parent)
                        currentNode.left = new Node(object);
                        node = currentNode.left;
                        node.parent = currentNode;
                        currentNode = null; // setting currentNode to null to break loop
                    }
                    // else proceed down left branch side
                    else {
                        currentNode = currentNode.left;
                    }
                }
                // else implies: object > current data, so use right branch
                else {
                    if(currentNode.right == null) {
                        // creating and initializing node's attributes (left, right, and parent)
                        currentNode.right = new Node(object);
                        node = currentNode.right;
                        node.parent = currentNode;
                        currentNode = null; // setting currentNode to null to break loop
                    }
                    // else proceed down right branch side
                    else {
                        currentNode = currentNode.right;
                    }
                }
            }

            // adding null branches and increasing node count -- no need to add parent attribute, it is done when adding an item to BST
            node.left = null;
            node.right = null;
            numberOfNodes++;

        }
    } // Complete

    /**
     * Iterative version for finding an object in the BST
     * @param object the object to find in the BST
     * @return the Node in which that object is held at
     */
    public Node find(T object) {
        // setting the current node to the root node
        Node currentNode = root;

        // iterating throughout the tree -- (Iterative Binary search)
        while(currentNode != null) {
            // if the object matches the current node's data, return the node
            if (object.equals(currentNode.data)) { return currentNode; }

            // else compare the object and the current node's data, if < 0, use the left side
            else if (sortOrder.compare(object, currentNode.data) < 0) { currentNode = currentNode.left; }

            // else, implies that object and the current node's data > 0, use the right side
            else { currentNode = currentNode.right; }
        }

        // if nothing is found, return null
        return null;
    } // Complete

    /**
     * Recursive version for finding an object in the BST
     * @param object the object to find in the BST
     * @return the Node in which that object is held at
     */
    public Node findRecursive(T object) {
        return find(root, object);
    } // Complete

    // Private helper function for findRecursive
    private Node find(Node node, T object) {

    //--Stopping Condition----------------------------------------------------------------------------------------------
        // node is null or the node's data matches the object, return the node
        if(node == null || node.data.equals(object)) { return node; }
    //------------------------------------------------------------------------------------------------------------------

        // if the object and node's data comparison < 0, use left side
        // else implies: object and node's data comparison > 0, use right side
        return(sortOrder.compare(object, node.data) < 0) ? (find(node.left, object)) : (find(node.right, object));


//        if (sortOrder.compare(object, node.data) < 0) { return find(node.left, object); }
//        return find(node.right, object);
    } // Complete

    /**
     * Turns tree into a ListArray
     * @return the ordered ListArray
     */
    public ListArray<T> toListArray() {
        // convert tree to SORTED array. use recursion -- looks like print.
        @SuppressWarnings("unchecked")
        ListArray<T> array = new ListArray<>();

        // traverse the tree -- created a helper function "listArrayTreeTraversal"
        treeTraversal(array, root); // -- In case it isn't clear -- This is the recursion part

        // sort the array
        array.sort(sortOrder);

        // return values added to the array
        return array;
    } // Complete

    /**
     * Method traverses the tree to add items to an array
     * @param listArray the array to use
     * @param node the current node to evaluate
     */
    public void treeTraversal(ListArray<T> listArray,Node node) {
        if (node != null) {
            // start with left side; left side < middle for BST's
            treeTraversal(listArray, node.left);

            //if node is the next one in the order, print it
            listArray.add(node.data);

            // Finish with right side; right side > middle for BST's
            treeTraversal(listArray, node.right);
        }
    } // Complete

    /**
     * Method balances a tree by converting it into a listArray
     *      then inserts data into a new tree while maintaining order
     */
    public void balance() {
        // reorganize tree to ensure it is in balance.
        // use recursion -- looks a lot like binary search
        // When complete, the height of the tree should be O(lg n)
        // Process: convert to an array, then rebuild the tree

        // Call reorder and since it makes the BST balanced, our job here is done! :)
        reorder(sortOrder);

    } // Complete

    /**
     * Helper function to build a new tree from a listArray
     * @param listArray the data to use for the nodes
     * @param start the index to start at
     * @param end the index to end with
     * @return the new node for the tree
     */
    private Node buildTree(ListArray<T> listArray, int start, int end) {

        // stopping condition
        if (start > end) {
            return null;
        }

        // finding middle element
        int mid = (start + end) / 2;

        //setting middle element to root's value
        Node node = new Node(listArray.peek(mid));

        //start with right side construction
        node.right = buildTree(listArray, mid + 1, end);

        //finish with left side construction
        node.left = buildTree(listArray, start, mid - 1);

        //return the updated node
        return node;
    } // Complete

    /**
     * Reorders the BST based on the comparator passed into it
     * @param orderBy the comparator to use for comparisons
     */
    public void reorder(Comparator<T> orderBy){
        sortOrder = orderBy;
        // Use the comparator to rebuild the tree according to this given comp.
        // it does not have to be balanced! -- I'm making it balanced, it makes life a lot easier

        // array to hold elements
        ListArray<T> array = new ListArray<>();

        // call helper function to add items in BST to array
        treeTraversal(array, root);

        // sort the items in the listArray based on the comparator
        array.sort(orderBy); // -- If you are asking yourself -- YES, I did make a sort method in ListArray! :) (I didn't use Java's built in methods so please don't kill me)

        // rebuild the balanced ordered Tree based on the comparator passed
        buildTree(array, 0, array.length());
    } // Complete


    /** Print elements of tree in order through recursion */
    public void print() {
        printRecursive(root);
    } // Complete

    /** Recursive print elements of tree in order */
    private void printRecursive(Node node) {
        // checking to see if there is anything in this node
        if(node != null) {

            // go left and print that info
            printRecursive(node.left);

            // printing info
            System.out.print(node.data + " "); // not sure which way to print, so I am putting a space between instead of newlines

            // go right and print that info
            printRecursive(node.right);
        }

    } // Complete

    /** Simple visualization of the tree.
     */
    public void visualize() {
        // copied from web -- citation provided later

        /* The width and height are hard-coded. If the tree has a lot of nodes or the data of a node needs a lot of space, it would probably be beneficial to change height and width. */

        // establish total width of tree
        // if elements too crowded, increase this -- use powers of 2.
        final int width = 32;

        // will not visualize greater than this height.
        // modify at will
        int height = numberOfNodes;

        // length of entire string holding tree -- including return characters \n
        int len = width * height * 2 + 2;
        StringBuilder sb = new StringBuilder(len);
        // put placeholders in for every char of every line of the tree string.
        for (int i = 1; i <= len; i++) {
            sb.append( ((i < len - 2) && (i % width == 0)) ? "\n" : ' ');
        }
        // build from the root
        visualizeRec(sb, width / 2, 1, width / 4, width, root, " ");
        System.out.println(sb);
    } // end visualize

    // string, center, right, depth, width, node, space or "branch" character.
    private void visualizeRec(StringBuilder sb, int c, int r, int d, int w, Node n, String edge) {
        if (n == null) {
            return;
        }
        // build left subtree
        visualizeRec(sb, c - d, r + 2, d / 2, w, n.left, " /");
        // place node value in string
        String s = n.data.toString();
        int idx1 = r * w + c - (s.length() + 1) / 2;
        int idx2 = idx1 + s.length();
        int idx3 = idx1 - w;
        if (idx2 < sb.length()) {
            sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);
        }
        // build right subtree
        visualizeRec(sb, c + d, r + 2, d / 2, w, n.right, "\\ ");
    } // end visualizeRec

} // end class Tree