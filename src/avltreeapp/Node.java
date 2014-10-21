/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avltreeapp;

/**
 * @file Node.java
 * @author Samantha Fadrigalan
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
    private int bf;
    private int height;
    private int size;
    
    /**
     * Constructs Node
     * @param key key of node
     */
    public Node(int key) {
        this(key, null, null);
    }
    
    /**
     * Constructs Node with left and right leaves
     * @param key key of node
     * @param left left node 
     * @param right right node
     */
    public Node(int key, Node<T> left, Node<T> right){
        //super();
        
        this.key = key;
        this.left = left;
        this.right = right;
        right.parent = this;
        left.parent = this;
        
        this.height = height(this);
        this.size = size(this);
        
        /**not done**/
    }
    
    /**
     * gets left node
     * @return pointer to left node
     */
    public Node<T> left(){
        return left;
    }
    
    /**
     * gets right node
     * @return pointer to right node
     */
    public Node<T> right(){
        return right;
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
     * determines the height of a node
     * @param node node
     * @return height of the node
     */
    private int height(Node<T> node){
        if(node==null)
            return 0;
        else if(node.height>0)
            return node.height;
        else{
            node.height = 1 + max(height(node.left), height(node.right));
            return node.height;
        }
    }
    
    /**
     * gets height of node
     * @return height of node
     */
    public int height(){
        return height;
    }
    
    /**
     * determines size of node
     * @param node node
     * @return size of node
     */
    private int size(Node<T> node){
        if(node==null)
            return 0;
        else{
            return 1 + size(node.left) + size(node.right);
        }
    }
    
    /**
     * gets size of a node
     * @param node node
     * @return size of node
     */
    public int getSize(Node<T> node){
        return size(node);
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
     * sets the balance factor of a node
     * @param bf balance factor
     */
    public void setBf(int bf){
        this.bf = bf;
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
     * determines if node is a leaf
     * @param node node
     * @return true if node is a leaf and false if otherwise
     */
    public boolean isExternal(Node<T> node){
        if(node.left==null && node.right==null)
            return true;
        return false;
    }
}