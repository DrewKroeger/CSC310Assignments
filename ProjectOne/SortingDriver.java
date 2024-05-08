/***************************************************************
 * Drew Kroeger- CSC310 ANALYSIS OF ALGORITHMS, 2/15/2024, PROJECT 1- SORTING ALGORITHM RUN TIME ANALYSIS
 * 
 *  This goes with Sort.java- These two files take txt files full of numbers and sort them, the driver will ask the user
 *  for a 1 or 2 and will sort either
 * elementary level sort or merge sort based on that user input
 ****************************************************************/


package PROJECT1;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class SortingDriver
{


    //---------------------------------------------------------------

    //this is the main method

    public static void main(String[] args)
    {

        File file = new File("1000000.txt");                         //this is a file object and reads from a file, either in the parent directory, or from a full directory pathname
        ArrayList<Integer> arrList = new ArrayList<>();                      //we make this just so we can capture every element in the text file dynamically
        try
        {
            Scanner fileScanner = new Scanner(file);                         //this is for reading from the actual file

            while(fileScanner.hasNextInt())                                  //while there is still a number in the txt file we keep reading and put it in the arrayList
            {
            int number= fileScanner.nextInt();
            arrList.add(number);
            }
            fileScanner.close();
        }

        catch(FileNotFoundException e)
        {
            e.addSuppressed(e);
        }
        System.out.println("Reading data from " + file.getName());




        int size = arrList.size();                                          //we put the array list into an array so we can sort faster
        int [] myArr = new int[arrList.size()];
        for (int i = 0; i<size; i++)
        {
            myArr[i] = arrList.get(i);
        }
        
        
        

        Scanner stdIn = new Scanner(System.in);                             //takes user input for sorting via elementary sort or nlogn sort
        int input;
        System.out.println("Do you want to do insertion sort(input 1) or merge sort(input 2)?");
        input = stdIn.nextInt();


        while (input != 1 && input != 2)                                                    //input valdiation so we can only put 1 or 2
        {
            System.out.println("Please input a 1 or 2. Try again");
            System.out.println("Do you want to do insertion sort(1) or merge sort(2)?");
            input = stdIn.nextInt();
        }


        if(input == 1)                                                                    //elementary sort
        {
            System.out.println("You have picked an elementary sort!");

            Sort elementarySort = new Sort();
            elementarySort.confirmSorted(myArr);

            System.out.println("Sorting using insertion sort");

            double first = System.currentTimeMillis();                                    //captures time before sort
            elementarySort.insertionSort(myArr);
            double last = System.currentTimeMillis();                                     //captures time after start

            double millisecondDifference = last - first;                                  //how long sort actually took
            System.out.println("It took " + millisecondDifference + " milliseconds");

            elementarySort.confirmSorted(myArr);                                          //makes sure the array is actually sorted
        }


        else if (input == 2)                                                              //advanced sort       
        {
            System.out.println("You have picked an advanced sort!");                    

            Sort advancedSort = new Sort();
            advancedSort.confirmSorted(myArr);

            System.out.println("Sorting using MergeSort");

            double first = System.currentTimeMillis();
            advancedSort.mergeSort(myArr);
            double last = System.currentTimeMillis();
            
            double millisecondDifference = last - first;
            System.out.println("It took " + millisecondDifference + " milliseconds");

            advancedSort.confirmSorted(myArr);
        }

        stdIn.close();

    }//end of main
}//end of class