package PROJECT1;

/***************************************
 * * Drew Kroeger- CSC310 ANALYSIS OF ALGORITHMS, 2/15/2024, PROJECT 1- SORTING ALGORITHM RUN TIME ANALYSIS
 *  This goes with SortingDriver.java- These two files take txt files full of numbers and sort them, the driver will ask the user
 *  for a 1 or 2 and will sort either
 * elementary level sort or merge sort based on that user input, it will also take the milliseconds it took to sort them
 ***************************************/

public class Sort 
{
    //--------------------------------------------------------------
    
    //this method is a simple insertion sort, I got it from one of my old assignments in CSC300

    public void insertionSort(int[] arr)
    {
        for( int i=1; i<arr.length; i++)        // out is dividing line
        {
            int temp = arr[i];                      // remove marked item
            int j = i;                             // start shifts at out
            while(j>0 && arr[j-1] >= temp)          // until one is smaller,
            {
                arr[j] = arr[j-1];                   // shift item to right
                --j;                                 // go left one position
            }
            arr[j] = temp;
        }
    }//end of insertion sort


    //--------------------------------------------------------------

    //I got this code from the MergeSort pdf slides, i renamed the methods slightly- see actualSorting, and actualMerging aswell

    public void mergeSort(int [] arr)                       
    {
        int[] aux = new int[arr.length];
        actualSorting(arr, aux, 0, arr.length - 1);
    }//end of mergeSort method

    //--------------------------------------------------------------


    public void actualSorting(int[] arr, int [] aux, int lo, int hi)
    {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        actualSorting(arr, aux, lo, mid);
        actualSorting(arr, aux, mid+1, hi);
        actualMerging(arr, aux, lo, mid, hi);
    }//end of actualSorting method

    //---------------------------------------------------------------


    private static void actualMerging(int[] arr, int[] aux, int lo, int mid, int hi)
    {
            for (int k = lo; k <= hi; k++)
            aux[k] = arr[k];
            int i = lo, j = mid+1;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid) arr[k] = aux[j++];
                else if (j > hi) arr[k] = aux[i++];
                else if (less(aux[j], aux[i])) arr[k] = aux[j++];
                else arr[k] = aux[i++];
            }  //end of for loop 
    }//end of actual Merging method

    //----------------------------------------------------------------

    //this method is needed for the actualMerging method- there was "less" in the actualMerging, so i looked at it and kind of assumed this is what they had in mind for the class, and it worked 

    private static boolean less(int a, int b)
    {
        return a < b;
    }//end of less method

    //****************************************************************

    //This is the confirmed sorted method, it reads from an array and if element+1 is ever less than element it immediately returns false, if it gets all the way through the array it returns true

    public boolean confirmSorted(int [] arr)
    {
        for(int i = 0; i<arr.length;i++)
        {
            if(arr[i]<=arr[i+1])
            {
                i++;
            }
            else if(arr[i]>arr[i+1])
            {
                System.out.println("Confirmed NOT sorted");
                return false;
            }
        } //end of for loop
        System.out.println("Confirmed Sorted");
        return true;
    }// end of confirmedSorted
}//end of Sort Class
