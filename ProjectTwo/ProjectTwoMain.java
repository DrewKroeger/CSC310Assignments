/********************************************
 * Drew Kroeger- CSC310- PROEJCT 2- FEBRUARY 29,2024- DUE MARCH 10 2024
 * THIS GOES WITH BINARYSEARCHTREE.JAVA AND NODE.JAVA
 * This is the driver class for project 2, which takes numbers and text from a txt file 
 * and puts them into a binary search tree,however the words are the values, and the numbers are the data
 ********************************************/
package PROJECT2;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class ProjectTwoMain 
{
    public static void main(String[] args)
    {
        BinarySearchTree BST = new BinarySearchTree();                                     //Initalize a BST object
        ArrayList<Integer> myInt = new ArrayList<>();                                      //Create two array lists, these will be used to store words and ints
        ArrayList<String> myStr = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);                               
        System.out.println("Please input the filename of the input file:");              //picking and opening the file
        String filename = scanner.next();
        File file = new File(filename);                                                    //not any exception handling if the file is not there


        try
        {
            Scanner fileScanner = new Scanner(file);                                        //this is for reading from the actual file

            while(fileScanner.hasNext())                                                    //while the file isnt empty we keep reading
            {
                String str = fileScanner.next();                                            //we take first token and put into string arraylist
                int number= fileScanner.nextInt();                                          //take next token and put it into int arrayList
                myStr.add(str);
                myInt.add(number);
            }
            fileScanner.close();                                                            //close scanner when done
        }
        catch(FileNotFoundException e)                                                      //catch if file is not found
        {
            e.addSuppressed(e);
        }

        int size = myStr.size();                                                            //this is the size of the string arraylist, and should be same for int arraylist
        for (int i = 0; i<size; i++)                                                        //we store the values of the two arraylists, in the binary search tree
        {
            //System.out.println(myStr.get(i));
            //System.out.println(myInt.get(i));
            BST.addValue(myStr.get(i), myInt.get(i));
        }
        
        System.out.println(" \n \n Pre order traversal:");                                //we do pre order, in order, and post order traversals
        BST.preOrderTraversal(BST.root);

        System.out.println("\n \n In-order traversal:");
        BST.inOrderTraversal(BST.root);

        System.out.println(" \n \nPost-order traversal:");
        BST.postOrderTraversal(BST.root);

        
        System.out.println("\nPlease input a word:");                                      //we search for words, and get what page it is on
        String keyWord = scanner.next();
        BST.search(keyWord);

        while (!(keyWord.equals("EXIT")))                                           //if the user inputs EXIT, the program ends
        {
            System.out.println("\nPlease input a word- or 'EXIT' to exit:");
            keyWord = scanner.next();

            if(keyWord.equals("EXIT"))                                              //breaks to save a little time(not searching)
            {
                break;
            }

            BST.search(keyWord);
        }

        System.out.println("Thank you! Bye Bye!");                                         //prints a goodbye message, if the user exits
        scanner.close();                                                                     //close scanner
    }//end of Project 2 MAIN method
}//end OF project driver class
