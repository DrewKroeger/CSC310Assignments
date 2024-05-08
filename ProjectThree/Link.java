/*********************************************  
 * Drew Kroeger, CSC310- ANALYSIS OF ALGORITHMS, MARCH 26,2024- DUE APRIL 12TH,2024- PROJECT 3 
 * This is the LinkList class for graph project. This goes with Graph.java, ProjectThreeMain.java,DepthFirstPaths.java, and LinkList.java
 * This was premade,
 *******************************************/
package PROJECT3;

class Link
   {
   public int iData;              // data item (key)
   public Link next;              // next link in list
// -------------------------------------------------------------
   public Link(int id) // constructor
      {
      iData = id;
      }
// -------------------------------------------------------------
   public void displayLink()      // display ourself
      {
      System.out.print(iData + " ");
      }
   }  // end class Link