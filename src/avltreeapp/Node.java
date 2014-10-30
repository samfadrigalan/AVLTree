package avltreeapp;

/**
 * @file Node.java
 * @author Samantha Fadrigalan and Kayla Thurman
 * Description: Node implementation for generic AVL Tree that supports
 * insert, search, select successor, rank functions
 */

/**
 * 
 * Node left, right, parent
 * key, bf, height, size
 */
public class Node<T extends Comparable<T>>{
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;
    private int key; 
    protected int bf;
    private int height;
    private int size;
    //private T data;
    
    public Node(){
        left = null;
        right = null;
        parent = null;
        height = 0;
    }
    
    public Node(int key){
        height = 0;
        left = null;
        right = null;
        parent = null;
        this.key = key;
    }
    
    
    
    /**
     * Constructs Node
     * @param key key of node
     */
    /*
    public Node(int key) {
        this(key, null, null);
    }*/
    
    /**
     * * Constructs Node with left and right leaves
     * @param key key of node
     * @param left left node 
     * @param right right node
     */
    /*
    public Node(int key, Node<T> left, Node<T> right){
        //super();
        
        this.key = key;
        this.left = left;
        this.right = right;
        right.parent = this;
        left.parent = this;
        
        this.height = height(this);
        this.size = this.size();
        
    }*/

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
     * Determines the height of a node
     * @return height of a node
     */
    private int height(){
        if(this==null)
            return 0;
        else if(this.height>0)
            return this.height;
        else{
            this.height = 1 + max(this.left.height(), this.right.height());
            return this.height;
        }
    }
    
    
    public int bf(){
        int rightH = this.right.height();
        int leftH = this.left.height();
        return leftH - rightH;
        //return this.left.height() - this.right.height();
    }
    
    /**
     * gets height of node
     * @return height of node
     */
    public int getHeight(){
        return this.height();
    }
    
    /**
     * determines size of node
     * @param node node
     * @return size of node
     */
    private int size(){
        if(this==null)
            return 0;
        else{
            return 1 + this.left.size() + this.right.size();
        }
    }
    
    /**
     * gets size of a node
     * @param node node
     * @return size of node
     */
    public int getSize(){
        return this.size();
    }
    
    /**
     * sets height of a node
     * @param height height of node
     */
    public void setHeight(int height){
        this.height = height;
    }
    
    /**
     * sets the size of a node
     * @param size size of node
     */
    public void setSize(int size){
        this.size = size;
    }
    
    /**
     * determines max of two integers
     * @param num1 1st integer
     * @param num2 2nd integer
     * @return max of two numbers
     */
    private int max(int num1, int num2){
        if(num1>num2)
            return num1;
        return num2;
    }
    
    /**
     * determines if node has children
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