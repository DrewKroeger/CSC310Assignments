/*********************************************  
 * Drew Kroeger, CSC310- ANALYSIS OF ALGORITHMS, MARCH 26,2024- DUE APRIL 12TH,2024- PROJECT 3 
 * This is the LinkList class for graph project. This goes with Graph.java, ProjectThreeMain.java,DepthFirstPaths.java, and Link.java
 * This is used in the graph class closely, as it is an adjacency list, which is an array of linked lists, so this is needed, along with link.java
 * This was premade, and modified quite a bit
 *******************************************/
package PROJECT3;
public class LinkList
   {
   private Link first;            // ref to first link on list

// -------------------------------------------------------------
   public LinkList()              // constructor
      {
      first = null;               // no links on list yet
      }
// -------------------------------------------------------------
   public void insertFirst(int id)
      {                           // make new link
      Link newLink = new Link(id);
      newLink.next = first;       // it points to old first link
      first = newLink;            // now first points to this
      }
// -------------------------------------------------------------
   public int find(int key)      // find link with given key
      {                           // (assumes non-empty list)
      Link current = first;              // start at 'first'
      while(current.iData != key)        // while no match,
         {
         if(current.next == null)        // if end of list,
            return -1;                 // didn't find it
         else                            // not end of list,
            current = current.next;      // go to next link
         }
      return current.iData;                    // found it
      }
// -------------------------------------------------------------
   public Link delete(int key)    // delete link with given key
      {                           // (assumes non-empty list)
      Link current = first;              // search for link
      Link previous = first;
      while(current.iData != key)
         {
         if(current.next == null)
            return null;                 // didn't find it
         else
            {
            previous = current;          // go to next link
            current = current.next;
            }
         }                               // found it
      if(current == first)               // if first link,
         first = first.next;             //    change first
      else                               // otherwise,
         previous.next = current.next;   //    bypass it
      return current;
      }
// -------------------------------------------------------------
   public void displayList()      // display the list
      {
      System.out.print("List (first-->last): ");
      Link current = first;       // start at beginning of list
      while(current != null)      // until end of list,
         {
         current.displayLink();   // print data
         current = current.next;  // move to next link
         }
      System.out.println("");
      }
// -------------------------------------------------------------
    public Link getFirst()
    {
        return first;
    }
}  // end class LinkList
