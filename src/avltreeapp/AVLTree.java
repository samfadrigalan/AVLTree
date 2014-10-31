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
                //x.setHeight((height(x)+1));
            }
            else{
                x.setRight(z);
                z.setParent(x);
                z.bf = 0;
                height_inc = true;
                //x.setHeight((height(x)+1));
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
                        x = l_rotate(x);
                        if(x.hasParent())
                            x.parent().bf = 0;
                        x.bf = 0;
                        height_inc = false;
                    }
                    else if(x.right().bf == 1){
                        int b = x.right().left().bf;
                        x = rl_rotate(x);
                        if(x.hasParent())
                            x.parent().bf = 0;
                        if (b == 0)
                            if(x.hasParent() && x.parent().hasRight())
                                x.bf = x.parent().right().bf = 0;
                        else if(b == 1){
                            x.bf = 0;
                            if(x.hasParent() && x.parent().hasRight())
                                x.parent().right().bf = -1;
                        }
                        else if(b == -1){
                            x.bf = 1;
                            if(x.hasParent() && x.parent().hasRight())
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
                //x.setHeight((height(x)+1));
                //x.bf = balanceFactor(x);
            }
            else{
                x.setLeft(z);
                z.setParent(x);
                z.bf = 0;
                height_inc = true;
                //x.setHeight((height(x)+1));
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
                        x = r_rotate(x);
                        if(x.hasParent())
                            x.parent().bf = 0;
                        x.bf = 0;
                        height_inc = false;
                    }
                    else if(x.left().bf == -1){
                        int b = x.left().right().bf;
                        x = lr_rotate(x);
                        if(x.hasParent())
                            x.parent().bf = 0;
                        if (b == 0){
                            x.bf = 0;
                            if(x.hasParent() && x.parent().hasRight())
                                x.parent().right().bf = 0;
                        }
                        else if(b == -1){
                            x.bf = 0;
                            if(x.hasParent() && x.parent().hasLeft())
                                x.parent().left().bf = 1;
                        }
                        else if(b == 1){
                            x.bf = -1;
                            if(x.hasParent() && x.parent().hasLeft())
                                x.parent().left().bf = 0;
                        }
                        height_inc = false;
                    }
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
    
    public Node<T> l_rotate(Node<T> x){
        Node<T> y = x.right();
        Node<T> b = y.left();
        Node<T> c = y.right();
        Node<T> a = x.left();
        x = new Node<T>(x.key(), a, b);
        y = new Node<T>(y.key(), x, c);
        return y;
    }
    
    public Node<T> r_rotate(Node<T> x){
        Node<T> y = x.left();
        Node<T> a = y.left();
        Node<T> b = y.right();
        Node<T> c = x.right();
        x = new Node<T>(x.key(), b, c);
        y = new Node<T>(y.key(), a, x);
        return y;
    }
    
    
    /**
     * Performs a left rotate on the left child of x then a right rotate on x
     * Assumes x has left child and left child of x has a right child
     * @param x a node
     */
    public Node<T> lr_rotate(Node<T> x){
        Node<T> y = l_rotate(x.left());
        x.setLeft(y);
        x = r_rotate(x);
        return x;
    }
    
    /**
     * Performs a right rotate on the right child of x then a left rotate on x
     * Assumes x has left child and left child of x has a right child
     * @param x a node
     */
    public Node<T> rl_rotate(Node<T> x){
        Node<T> y = r_rotate(x.right());
        x.setRight(y);
        x = l_rotate(x);
        return x;
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
        if(x.key() > k)
            return successor(x.left(), k);
        if(x.key() < k)
            return successor(x.right(), k);
        //put if !=key
        if(x.hasRight()){ 
            System.out.println("test hasRight() in successor");
            return min(x.right());
        }
        Node<T> y = x.parent();
        while(y!=null &&  x == y.right()){
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
            return min(x.left());
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
