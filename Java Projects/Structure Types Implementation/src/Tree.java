import java.util.*;

/** Binary Search Tree storing type T */
public class Tree <T extends Comparable> {

    // NEW: Using public class BinaryNode (provided).
    // In your code you can do find-and-replace Node with BinaryNode

    /** Root of binary search tree */
    private BinaryNode root = null;

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
    public BinaryNode root() { return root; }

    /** Add a node to the tree. Use sortOrder to determine BST ordering.
     * @param object : value to be stored in tree
     */
    public void add(T object) {
        // use sortOrder to maintain BST property
        // this was part of Lab 8 requirement
        // if the tree is empty then add to the first spot
        if (root == null) {
            // creating new node
            root = new BinaryNode(object);

            // updating node's attributes
            root.left(null);
            root.right(null);
            root.parent(null); // when using the root, the parent will always be null (unless you want it self referencing, in which case just change this line to "root.parent = root;")
        }
        else {
            // Initializing nodes to use for holding new data, old data, and transferring data between the two
            BinaryNode currentNode = root;
            BinaryNode node = null;

            // iterating through BST
            while (currentNode != null) {
                // if object < current data, use left branch
                if (sortOrder.compare(object, (T) (currentNode.data())) < 0) {
                    // if left branch is null, add to branch
                    if (currentNode.left() == null) {
                        // creating and initializing node's attributes (left, right, and parent)
                        currentNode.left(new BinaryNode(object));
                        node = currentNode.left();
                        node.parent(currentNode);
                        currentNode = null; // setting currentNode to null to break loop
                    }
                    // else proceed down left branch side
                    else {
                        currentNode = currentNode.left();
                    }
                }
                // else implies: object > current data, so use right branch
                else {
                    if (currentNode.right() == null) {
                        // creating and initializing node's attributes (left, right, and parent)
                        currentNode.right(new BinaryNode(object));
                        node = currentNode.right();
                        node.parent(currentNode);
                        currentNode = null; // setting currentNode to null to break loop
                    }
                    // else proceed down right branch side
                    else {
                        currentNode = currentNode.right();
                    }
                }
            }

            // adding null branches and increasing node count -- no need to add parent attribute, it is done when adding an item to BST
            node.left(null);
            node.right(null);
            numberOfNodes++;
        }
    } // end add -- Complete

    /**
     * Iterative version for finding an object in the BST
     * @param object the object to find in the BST
     * @return the Node in which that object is held at
     */
    public BinaryNode find(T object) {
        // recursive or iterative search through the tree, searching for object
        // this was part of Lab 8 requirement
        BinaryNode currentNode = root;

        // iterating throughout the tree -- (Iterative Binary Search)
        while(currentNode != null) {
            // if the object matches the current node's data, return the node
            if(object.equals(currentNode.data())) { return currentNode; }

            // else compare the object and the current node's data, if < 0, use the left side
            else if(sortOrder.compare(object, (T)(currentNode.data())) < 0) { currentNode = currentNode.left(); }

            // else, implies that object and current node's data > 0, use the right side
            else { currentNode = currentNode.right(); }
        }

        // if nothing is found return null
        return null;
    } // end find -- Complete

    /**
     * Recursive version for finding an object in the BST
     * @param object the object to find in the BST
     * @return the Node in which that object is held at
     */
    public BinaryNode findRecursive(T object) { return find(root, object); } // end findRecursive -- Complete

    // Private helper function for findRecursive
    private BinaryNode find(BinaryNode node, T object) {
        //stopping condition -- if node is null or node's data matches the object, return the node
        if(node == null || node.data().equals(object)) { return node; }

        return sortOrder.compare(object, (T)(node.data())) < 0 ? (find(node.left(), object)) : (find(node.right(), object));

    } // end find -- Complete

    /**
     * Turns tree into a ListArray
     * @return the ordered ListArray
     */
    public ListArray<T> toListArray() {
        // convert tree to SORTED array. use recursion -- looks like print.
        // do not put into an array and then sort -- it is not efficient.
        ListArray<T> array = new ListArray<>();

        // traverse the tree
        treeTraversal(array, root);

        // sort the array
        array.sort(sortOrder);

        // return the sorted array
        return array;
    } // end ListArray<T> -- Complete

    private void treeTraversal(ListArray<T> listArray, BinaryNode node) {
        if (node != null) {
            // start with left side; left side < middle for BST's
            treeTraversal(listArray, node.left());

            //if node is the next one in the order, print it
            listArray.add((T)node.data());

            // Finish with right side; right side > middle for BST's
            treeTraversal(listArray, node.right());
        }
    } // end treeTraversal -- Complete

    /**
     * Method balances a tree by converting it into a listArray
     *      then inserts data into a new tree while maintaining order
     */
    public void balance() {
        // reorganize tree to ensure it is in balance.
        // use recursion -- looks a lot like binary search
        // When complete, the height of the tree should be O(lg n)
        // Process: convert to an array, then rebuild the tree
        // this was part of Lab 8 requirement

        // call reorder and since it uses the same functionality, balance and reOrder does the same thing!
        reorder(sortOrder);
    } // end balance -- Complete

    /**
     * Reorders the BST based on the comparator passed into it
     * @param orderBy the comparator to use for comparisons
     */
    public void reorder(Comparator<T> orderBy){
        sortOrder = orderBy;
        // Use the comparator to rebuild the tree according to this given comp.
        // it does not have to be balanced!
        // This was part of Lab 8 requirement.

        // array to hold elements
        ListArray<T> array = new ListArray<>();

        // call helper function to add items in BST to array
        treeTraversal(array, root);

        // sort the items in the listArray based on the comparator
        array.sort(sortOrder);

        // rebuild the balanced ordered Tree based on comparator passed
        buildTree(array, 0, array.length());
    } // end reorder -- Complete

    private BinaryNode buildTree(ListArray<T> listArray, int start, int end) {
        // stopping condition
        if (start > end) {
            return null;
        }

        // finding middle element
        int mid = (start + end) / 2;

        //setting middle element to root's value
        BinaryNode node = new BinaryNode(listArray.peek(mid));

        //start with right side construction
        node.right(buildTree(listArray, mid + 1, end));

        //finish with left side construction
        node.left(buildTree(listArray, start, mid - 1));

        //return the updated node
        return node;
    } // end buildTree -- Complete

    /** Print elements of tree in order through recursion */
    public void print() {
        // print in order -- use recursion or iterative dfs
        // This was part of Lab 8 requirement.
        printRecursive(root);
    } // end print -- Complete

    /** Recursive print elements of tree in order */
    private void printRecursive(BinaryNode node) {
        // checking to see if there is anything in this node
        if(node != null) {

            // go left and print that info
            printRecursive(node.left());

            // printing info
            System.out.print(node.data() + " "); // not sure which way to print, so I am putting a space between instead of newlines

            // go right and print that info
            printRecursive(node.right());
        }
    } // end printRecursive -- Complete

    // NEW REQUIREMENT
    // do not iterate over the tree looking for minimum!
    /**
     * Finds the smallest object in Tree
     * @return the data of the smallest object
     */
    public T min() {
        BinaryNode currentNode = root;

        // looping the leftmost leaf to find the smallest value in BST
        while(currentNode.left() != null) { currentNode = currentNode.left(); }

        // return smallest data found
        return (T)currentNode.data();
    } // end min -- Complete

    // NEW REQUIREMENT
    // do not iterate over the tree looking for maximum!
    /**
     * Finds the largest object in Tree
     * @return the data of the largest object
     */
    public T max() {
        BinaryNode currentNode = root;

        // looping the rightmost leaf to find the largest value in BST
        while(currentNode.right() != null) { currentNode = currentNode.right(); }

        // return largest data found
        return (T)currentNode.data();
    } // end max -- Complete

    // NEW requirement
    // calculate the depth of every node of the tree.
    // This is all you need here -- finish bfs method.
    /**
     * Calculates the depth of every node in the tree
     */
    public void calculateDepth() {
        TreeSearch.bfs(root);
    } // end calculateDepth -- Complete

    // NEW REQUIREMENT
    // calculate the height of every node of the tree.
    // return the height of the tree.
    // The height of any node is the max(right.height,left.height)+1
    // imho, recursion is your best option (similar to dfs).
    /**
     * Calculates the height of the tree
     * @return
     */
    public int calculateHeight() {
        return height(root);
    } // end calculate Height -- Complete

    // private helper for calculateHeight method
    private int height(BinaryNode node) {
        // checking to see if tree is empty or not
        if(node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left()), height(node.right()));
    } // end height -- Complete

    // NEW REQUIREMENT
    // Determine if tree is balanced.
    // zyBook 9.1: left and right balance factor differs by at most 1.

    /**
     * Checking to see if the tree is balanced
     * @return tree if it is balanced and false otherwise
     */
    public boolean isBalanced() {
        // IS THE HEIGHT UP-TO-DATE?
        int height = calculateHeight();
        // if the root is null, an empty tree is balanced
        if(root == null) { return true; }

        // if the differences in the tree is -1 return false
        if(height == -1) { return false; }

        // else it is balanced, so return true
        return true;
    } // end isBalanced -- Complete

    // NEW REQUIREMENT.
    // Use the height of the tree to set the height of the visualization.
    // AND use the height of the tree to set the width (maybe +16 for each level)

    /** Simple visualization of the tree.
     */
    public void visualize() {
        // copied from web -- citation provided later

        // Now that you can determine the height of the tree,
        // you can use that to guestimate good width and height values.

        // establish total width of tree
        // if elements too crowded, increase this -- use powers of 2.
        final int width = calculateHeight() + 32;

        // will not visualize greater than this height.
        // use tree height
        int height = calculateHeight();

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
    } // end visualize -- Complete

    // string, center, right, depth, width, node, space or "branch" character.
    private void visualizeRec(StringBuilder sb, int c, int r, int d, int w, BinaryNode n, String edge) {
        if (n == null) {
            return;
        }
        // build left subtree
        visualizeRec(sb, c - d, r + 2, d / 2, w, n.left(), " /");
        // place node value in string
        String s = n.data().toString();
        int idx1 = r * w + c - (s.length() + 1) / 2;
        int idx2 = idx1 + s.length();
        int idx3 = idx1 - w;
        if (idx2 < sb.length()) {
            sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);
        }
        // build right subtree
        visualizeRec(sb, c + d, r + 2, d / 2, w, n.right(), "\\ ");
    } // end visualizeRec

} // end class Tree