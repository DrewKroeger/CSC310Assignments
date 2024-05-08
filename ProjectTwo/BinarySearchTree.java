/*****************************
 * Drew Kroeger, CSC310-PROJECT 2, FEBRUARY 29,2024, Due March 10,2024
 * This goes with the ProjectTwoMain.java and Node.java
 * This class controls the methods of the binary search tree
 *****************************/

package PROJECT2;

public class BinarySearchTree 
{
    public Node root;
    public BinarySearchTree()
    {
        this.root = null;
    }
    //----------------------------------------------------------

    //This method allows the user to add a node to the binary search tree, sorting where it goes based on the string value

    public void addValue(String key, int value)
    {
      Node newNode = new Node();    // make new node
      newNode.key = key;           // insert data
      newNode.value = value;


      if(root==null)// no node in root
      {                
         root = newNode;
      }
      else                          // root occupied
      {
         Node current = root;       // start at root
         Node parent;
         while(true)                // (exits internally)
         {
            parent = current;
            if(key.compareToIgnoreCase(current.key) < 0)  // go left
            {
               current = current.leftChild;
               if(current == null)  // if end of the line,
               {                 // insert on left
                  parent.leftChild = newNode;
                  return;
               }
            }  // end if go left
            else if (key.compareToIgnoreCase(current.key) > 0)                    // or go right
            {
               current = current.rightChild;
               if(current == null)                     // if end of the line
               {                                      // insert on right
                  parent.rightChild = newNode;
                  return;
               }
            }  // end else go right
            else                                           //if the words are equal, then we just dont add the second one in the three
            {
               return;
            }
         }  // end while
      }  // end else not root
    }//end of addvalue method


    //----------------------------------------------------------

    //This method allows the user to search for a specific key, and will return what page that key is on 

    public int search(String key)
    {
        Node x = root;
        while (x != null)                 //while x still has children we are in this loop
        {
        
         int cmp = key.compareTo(x.key);   //Compare strings
        
            if (cmp < 0)                      //if less that lexographically we go left
            {
               x = x.leftChild;
            }
            else if (cmp > 0)                 //if key is greater than node lexographically we go right
            { 
               x = x.rightChild;
            }
            else if (cmp == 0)                //if they are equal we have a match
            {
               System.out.println(key + " was found on page " + x.value + "!");  //say the match and return
               return x.value;
            }//end of ifs

        }//end of while loop

        System.out.println(key + " was NOT found :(");                        //if while loop ends no match was found and we get here
        return -1;    
    }//end of search method

    //----------------------------------------------------------

    //This method prints out a pre order traversal of the binary search tree, it prints the root, then goes left child right child

    public void preOrderTraversal(Node localRoot)
    {
      Node local = localRoot;

      if(local != null)
      {
         System.out.print( local.key + ":" + local.value + "   ");
         preOrderTraversal(local.leftChild);
         preOrderTraversal(local.rightChild);
      }

    }//end of pre order traversal

    //----------------------------------------------------------

    //This method prints out a in order traversal of the binary search tree, where it goes right child, root, left child

    public void inOrderTraversal(Node localRoot)
    {
      Node local = localRoot;

      if(local != null)
      {
         inOrderTraversal(local.leftChild);
         System.out.print(local.key + ":" + local.value + "   ");
         inOrderTraversal(local.rightChild);
      }

    }//end of in order traversal

    //----------------------------------------------------------

    //this method does a post order traversal of the binary search tree, it goes left child, right child, then root

    public void postOrderTraversal(Node localRoot)
    {
      Node local = localRoot;

      if(local != null)
      {
         postOrderTraversal(local.leftChild);
         postOrderTraversal(local.rightChild);
         System.out.print(local.key + ":" + local.value + "   ");
      }

    }//end of post order traversal

}//end of BinarySearchTree class
