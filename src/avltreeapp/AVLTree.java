package avltreeapp;
import java.util.Queue;
/**
 * @file AVLTree.java
 * @author Samantha Fadrigalan and Kayla Thurman
 * Description: Generic AVL Implementation; Supports insert, search, 
 * select successor, rank functions
 * Course: CSC 3102
 */
public class AVLTree<T extends Comparable<T>> {
   private Node<T> root;
   private boolean height_inc;
    
    /**
     * Constructs an AVLTree.
     */
    public AVLTree(){
        root = null;
        height_inc = false;
    }
    
    public int height(Node<T> x){
        if (x == null)
            return 0;
        return x.getHeight();
    }
    
    public void insert(Node<T> x){
        root = insert(root, x);
    }
    
    private int balanceFactor(Node<T> x){
        int leftHeight = height(x.left());
        int rightHeight = height(x.left());
        return leftHeight - rightHeight;
    }
    
    /**
     * Inserts an element x to the tree
     * @param x a node
     * @param z a node
     */
    private Node<T> insert(Node<T> x, Node<T> z){
        if(x == null){
            x = z;
            x.bf = 0;
            return x;
        }
        if(x.key() < z.key()){
            if(x.hasRight()){
                x.setRight(insert(x.right(), z));
                x.setHeight((height(x)+1));
                //x.bf = balanceFactor(x);
            }
            else{
                x.setRight(z);
                z.setParent(x);
                z.bf = 0;
                height_inc = true;
                x.setHeight((height(x)+1));
                //x.bf = balanceFactor(x);
            }
            
            if(height_inc){
            if(x.bf == 0)
                x.bf = -1;
            else if(x.bf == 1){
                x.bf = 0;
                height_inc = false;
            }
            else{
                if(x.right().bf == -1){
                    l_rotate(x);
                    x.bf = x.parent().bf = 0;
                    height_inc = false;
                }
                else if(x.right().bf == 1){
                    int b = x.right().left().bf;
                    lr_rotate(x);
                    x.parent().bf = 0;
                    if (b == 0)
                        x.bf = x.parent().right().bf = 0;
                    else if(b == 1){
                        x.bf = 0;
                        x.parent().right().bf = -1;
                    }
                    else if(b == -1){
                        x.bf = 1;
                        x.parent().right().bf = 0;
                    }
                    height_inc = false;
                }
            }
        }
            
        }
        else if(x.key() > z.key()){
            if(x.hasLeft()){
                x.setLeft(insert(x.left(), z));
                x.setHeight((height(x)+1));
                //x.bf = balanceFactor(x);
            }
            else{
                x.setLeft(z);
                z.setParent(x);
                z.bf = 0;
                height_inc = true;
                x.setHeight((height(x)+1));
                //x.bf = balanceFactor(x);
            }
            
            if(height_inc){
            if(x.bf == 0)
                x.bf = 1;
            else if(x.bf == -1){
                x.bf = 0;
                height_inc = false;
            }
            else{
                if(x.left().bf == 1){
                    r_rotate(x);
                    x.bf = x.parent().bf = 0;
                    height_inc = false;
                }
                else if(x.right().bf == 1){
                    int b = x.right().left().bf;
                    lr_rotate(x);
                    x.parent().bf = 0;
                    if (b == 0)
                        x.bf = x.parent().right().bf = 0;
                    else if(b == 1){
                        x.bf = 0;
                        x.parent().right().bf = -1;
                    }
                    else if(b == -1){
                        x.bf = 1;
                        x.parent().right().bf = 0;
                    }
                    height_inc = false;
                }
            }
        
        }
        
        
        
        if(height_inc){
            if(x.bf == 0)
                x.bf = -1;
            else if(x.bf == 1){
                x.bf = 0;
                height_inc = false;
            }
            else{
                if(x.right().bf == -1){
                    l_rotate(x);
                    x.bf = x.parent().bf = 0;
                    height_inc = false;
                }
                else if(x.right().bf == 1){
                    int b = x.right().left().bf;
                    lr_rotate(x);
                    x.parent().bf = 0;
                    if (b == 0)
                        x.bf = x.parent().right().bf = 0;
                    else if(b == 1){
                        x.bf = 0;
                        x.parent().right().bf = -1;
                    }
                    else if(b == -1){
                        x.bf = 1;
                        x.parent().right().bf = 0;
                    }
                    height_inc = false;
                }
            }
        }
        return x;
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
     * Finds the successor of a node given a key k
     * @param x an integer 
     * @param k a node
     * @return 
     */
    public Node<T> successor(Node<T> x, int k){
        System.out.println("test");
        if(x.hasLeft())
            if(x.key() > k)
                successor(x.left(), k);
        if(x.key() < k)
            successor(x.right(), k);
        
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
    
    /**
     * Returns the root of the AVL tree
     * @return root of the the AVL tree
     */
    public Node<T> root(){
        return root;
    }
}
