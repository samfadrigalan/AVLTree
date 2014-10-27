package avltreeapp;
import java.util.Queue;
/**
 * @file AVLTree.java
 * @author Samantha Fadrigalan
 * Description: Generic AVL Implementation; Supports insert, search, 
 * select successor, rank functions
 * Course: CSC 3102
 */
public class AVLTree<T extends Comparable<T>> {
   Node<T> root;
    
    /**
     * Constructs an AVLTree.
     */
    public AVLTree(){
        root = null;
    }
    
    /**
     * Returns the root of the AVL tree
     * @return root of the the AVL tree
     */
    public Node<T> root(){
        return root;
    }
    
    /***NEEDS WORKING***/
    public void insert(Node<T> x, Node<T> z){
        if(x.key() > z.key()){
            if(x.hasLeft())
                insert(x.left(), z);
            else
                x.setLeft(root);
        }
        else if(x.key() < z.key()){
            if(x.hasRight())
                insert(x.right(), z);
            else
                x.setRight(z);
        }
    }
    
    /**
     * Links x's parent to y and y's parent is changed accordingly
     * @param x a node
     * @param y a node
     */
    public void transplant(Node<T> x, Node<T> y){
        if(x.parent() == this.root)
            this.root = y;
        else if(x.parent().left() == x)
            x.parent().setLeft(y);
        else
            x.parent().setRight(y);
        
        if(y!=null)
            y.setParent(x.parent());
    }
    
    /**
     * Performs a right rotation on node x
     * Assumes x has a left child
     * @param x a node
     */
    public void r_rotate(Node<T> x){
        Node<T> y = x.left();
        transplant(y, y.right());
        transplant(x, y);
        y.setRight(x);
        x.setParent(y);
    }
    
    /**
     * Performs a left rotation on node x
     * Assumes x has right child
     * @param x a node
     */
    public void l_rotate(Node<T> x){
        Node<T> y = x.right();
        transplant(y, y.left());
        transplant(x, y);
        y.setLeft(x);
        x.setParent(y);
    }
    
    /**
     * Performs a left rotate on the left child of x then a right rotate on x
     * Assumes x has left child and left child of x has a right child
     * @param x a node
     */
    public void lr_rotate(Node<T> x){
        l_rotate(x.left());
        r_rotate(x);
    }
    
    /**
     * Determines if there is a node with key k
     * @param x a node
     * @param k an integer
     * @return true if there is a node with key k and false if otherwise
     */
    public boolean search(Node<T> x, int k){
        if(x==null)
            return false;
        if(x.key() == k)
            return true;
        if(x.key() > k)
            return search(x.left(), k);
        return search(x.right(), k);
    }
    
    /**
     * Finds the successor of a node
     * @param x a node
     * @return successor of node x
     */
    public Node<T> successor(Node<T> x){
        if(x.hasRight())
            return min(x.right());
        Node<T> y = x.parent();
        while(y!=null && y.hasRight()){
            x = y;
            y = y.parent();
        }
        return y;
    }
    
    /**
     * Finds the min of a tree
     * @param x a node
     * @return node containing the smallest key
     */
    public Node<T> min(Node<T> x){
        if(x == null)
            throw new IllegalStateException("Tree underflow");
        if(x.hasLeft())
            return min(x);
        else
            return x;
    }
    
    /**
     * Finds the node containing the i-th smallest key
     * @param x a node
     * @param i an integer
     * @return 
     */
    public Node<T> select(Node<T> x, int i){
        if(x==null)
            throw new IllegalStateException("tree underflow");
        if(x.left().getSize() >= i)
            return select(x.left(), i);
        if(x.left().getSize() + 1 == i)
            return x;
        return select(x.right(), i-1-(x.left().getSize()));
    }
    
    /**
     * Finds the rank/position of key k in the liner order determined
     * by the in-order traversal tree
     * @param x a node
     * @param k an integer
     * @return rank/position of key x
     */
    public int rank(Node<T> x, int k){
        if(x==null)
            return 0;
        if(k < x.key())
            return rank(x.left(), k);
        if(k == x.key())
            return x.left().getSize() + 1;
        return x.left().getSize() + 1 + rank(x.right(), k);
    }
}
