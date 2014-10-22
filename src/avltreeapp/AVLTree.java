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
    
    /*****CREATE LEFT ROTATE******/
    /*
     * look at the slideshow
     */
    public void l_rotate(Node<T> x){
        Node<T> y = x.right();
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
     * determines if there is a node with key k
     * @param node root node
     * @param k key to be search
     * @return true if there is node with key k and otherwise if false
     */
    public boolean search(Node<T> node, int k){
        if(node==null)
            return false;
        if(node.key() == k)
            return true;
        if(node.key() > k)
            return search(node.left(), k);
        return search(node.right(), k);
    }
    
    /**
     * finds the successor of a node
     * @param x node
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
     * finds the min of a tree
     * @param node parent node
     * @return min of tree
     */
    public Node<T> min(Node<T> node){
        if(node.hasLeft())
            return min(node);
        else
            return node;
    }
    
    /**
     * finds the node containing the ith smallest key 
     * @param node parent node
     * @param i ith smallest key
     * @return node containing the ith smallest key
     */
    public Node<T> select(Node<T> node, int i){
        if(node==null)
            throw new IllegalStateException("tree underflow");
        if(node.left().getSize() >= i)
            return select(node.left(), i);
        if(node.left().getSize() + 1 == i)
            return node;
        return select(node.right(), i-1-(node.left().getSize()));
    }
    
    /**
     * Finds the rank/position of element z in the liner order determined
     * by the inorder traversal tree
     * @param x element being searched
     * @return rank/position of element z
     */
    public int rank(Node<T> x){
        if(x==null)
            throw new IllegalStateException();
        return x.left().getSize() + 1;
    }
}
