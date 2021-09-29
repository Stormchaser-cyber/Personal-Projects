/** Generic TreeSearch of a binary search tree constructed with BinaryNodes
 These are static methods only! No need to declare an object of this type.
 To use, you call like this: TreeSearch.bfs(root);
 */
public class TreeSearch {

    /** Classic breadth-first-search.
     Use the code you developed in the class exercise.
     Modify it so that it does not print anything,
     and importantly, SETS THE DEPTH of each node.
     @param root Of a binary tree built with BinaryNode<T>
     */
    public static void bfs(BinaryNode root) {
        // set the depth as you descend into the tree.
        // the root is at depth 0
        // the children of the root are depth 1, etc.
        Queue queue = new Queue();
        int depth = 0;

        queue.push(root);

        while(!queue.empty()) {
            // pop off the first item
            BinaryNode p = new BinaryNode(queue.pop());

            // set the depth
            p.depth(depth);

            // increment depth
            depth++;

            // push the left and right values if they have data in them
            if(null != p.left()) { queue.push(p.left()); }
            if(null != p.right()) { queue.push(p.right()); }

        }
    } // end bfs -- Complete

    // You can put dfs in here too, but it is not required!!

    /**
     * Classic depth-first-search
     * @param root of a binary tree built with BinaryNode<T>
     */
    public static void dfs(BinaryNode root) {
        // set the depth as you descend into the tree.
        // the root is at depth 0
        // the children of the root are depth 1, etc.
        Stack stack = new Stack();
        BinaryNode p = root;
        int depth = 0;

        p.depth(depth);

        // while we have nodes left to visit
        while((null != p) || !stack.empty()) {
            if(null != p) {
                // go left towards bottom of tree
                stack.push(p);
                p = p.left();
                depth++;
                p.depth(depth);
            }
            else {
                // grab from stack
                p = new BinaryNode(stack.pop());

                // print data
                System.out.println(p.data() + " ");

                // go right
                p = p.right();

            }
        }
    } // end dfs -- Complete
}