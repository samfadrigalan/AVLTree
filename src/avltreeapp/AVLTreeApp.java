//package avltreeapp;
import java.util.Scanner;
import java.io.*;
/**
 * @file AVLTreeApp.java
 * @author Samantha Fadrigalan and Kayla Thurman
 * Description: Tests AVL implementation
 * Course: CSC 3102
 */
public class AVLTreeApp {

    public static void main(String[] args) {
        try{
            AVLTree<Integer> tree = new AVLTree<>();
            Scanner fin = new Scanner(new File("AVLtree-input-2.txt"));
            PrintWriter fout = new PrintWriter(new File("AVLOutput.txt"));
            while(fin.hasNext()){
                String operation = fin.next();
                boolean found = false;
                Node<Integer> temp, successor, selected;
                int keyToSearch, rank;
                if(operation.compareTo("IN") == 0){//Insert
                    temp = new Node<Integer>(fin.nextInt());
                    tree.insert(temp);
                }
                else if(operation.compareTo("SR") == 0){//Search
                    keyToSearch = fin.nextInt();
                    if(tree.search(tree.root(), keyToSearch))
                        found = true;
                }
                else if(operation.compareTo("SC") == 0){//Successor
                    successor = tree.successor(tree.root(), fin.nextInt());
                    System.out.println(successor.key());
                    fout.print(successor.key());
                }
                else if(operation.compareTo("SE") == 0){//Select
                    keyToSearch = fin.nextInt();
                    selected = tree.select(tree.root(), keyToSearch);
                    System.out.println(selected.key());
                    fout.println(selected.key());
                }
                else{//Rank
                    keyToSearch = fin.nextInt();
                    rank = tree.rank(tree.root(), keyToSearch);
                    System.out.println(rank);
                    fout.println(rank);
                }
            }
            fout.close();
            fin.close();
        }
        catch(IOException e){
            System.out.println("Unable to process file");
            System.exit(90);
        }
    }
}