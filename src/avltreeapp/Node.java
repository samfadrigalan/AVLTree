//package avltreeapp;
/**
 * @file Node.java
 * @author Samantha Fadrigalan and Kayla Thurman
 * Description: Node implementation for generic AVL Tree that supports
 * insert, search, select successor, rank functions
 */

public class Node<T extends Comparable<T>>{
    private int height;
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;
    private int key; 
    protected int bf;
    //private T data;
    
    /**
     * Constructs a node
     * @param key an integer
     */
    public Node(int key){
        this(key, null, null);
    }
    
    /**
     * Constructs a node
     * @param key an integer
     * @param left a node
     * @param right a node
     */
    public Node(int key, Node<T> left, Node<T> right){
        //super();
        this.key = key;
        this.left = left;
        this.right = right;
        this.bf = 0;
        
        if(left == null && right == null)
            this.height = 1;
        else if(left == null)
            this.height = right.height + 1;
        else if(right == null)
            this.height = left.height + 1;
        else
            this.height = Math.max(left.height, right.height) + 1;
    }

    /**
     * gets parent node
     * @return pointer to parent 
     */
    public Node<T> parent(){
        return parent;
    }
    /**
     * gets left node
     * @return pointer to left
     */
    public Node<T> left(){
        return left;
    }
    
    /**
     * gets right node
     * @return pointer to right 
     */
    public Node<T> right(){
        return right;
    }
    
    /**
     * gets key of node
     * @return key
     */
    public int key(){
        return key;
    }
    
    /**
     * determines if node has parent
     * @return true if node has parent and false if otherwise
     */
    public boolean hasParent(){
        return (parent!=null);
    }
    
    /**
     * determines if node has left child
     * @return true if node has left child and false if otherwise
     */
    public boolean hasLeft(){
        return (left!=null);
    }
    
    /**
     * determines if node has right child
     * @return true if node has right child and false if otherwise
     */
    public boolean hasRight(){
        return (right!=null);
    }
    
    /**
     * sets the parent of a node
     * @param node parent this node
     */
    public void setParent(Node<T> node){
        this.parent = node;
    }
    
    /**
     * sets the left child of a node
     * @param node left child of this node
     */
    public void setLeft(Node<T> node){
        this.left = node;
    }
    
    /**
     * sets the right child of a node
     * @param node right child of this node
     */
    public void setRight(Node<T> node){
        this.right = node;
    }
    
    /**
     * Gets height of node
     * @return height of node
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Sets height of a node
     * @param height height of node
     */
    public void setHeight(int height){
        this.height = height;
    }
    
    /**
     * Determines if node has children
     * @param node node
     * @return true if node has children and false if otherwise
     */
    public boolean isInternal(Node<T> node){
        if(node.left!=null || node.right!=null)
            return true;
        return false;
    }
    
    /**
     * Determines if node is a leaf
     * @return true if node is a leaf and false if otherwise
     */
    public boolean isExternal(){
        if(this.left==null && this.right==null)
            return true;
        return false;
    }
}