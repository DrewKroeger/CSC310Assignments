/*********************************************  
 * Drew Kroeger, CSC310- ANALYSIS OF ALGORITHMS, MARCH 26,2024- DUE APRIL 12TH,2024- PROJECT 3 
 * This is the main method driver for graph project. This goes with DepthFirstPaths.java, Graph.java,LinkList.java, and Link.java
 * This reads a certain formatted text file, where the first int is the amount of vertices in a graph, and the next ints(2 to a line) are edges connecting vertices
 * The main then enters a loop asking for a move on the graph, a search for a vertex in the graph, or a print of all connections in the graph. You can also exit the program with a Q.
 * THERE IS ALSO THE  EXTRA CREDIT ONE DIRECTIONAL FUNCTION(this is mostly done in main, one extra function in graph class), 
 * and the if the GRAPH IS fully connected or not, this is performed in the search function, and is actually found in the depthfirstpaths
 *******************************************/
package PROJECT3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ProjectThreeMain 
{
    //----------------------------------------------------------------------------

    //This is the main method for Project Three, see header comment
    public static void main(String [] args)
    {
        Scanner scanner = new Scanner(System.in); 

        int graphSize = -1;                                                             //this is later used for amount of vertices we will need in the graph,initialize it now so it is not local variable, , but -1 will not be used
        File file = new File("13vertices-disjoint.txt");                               //this includes the file we will read from, and makes the file object
        try                                                                             
        {                                                                               //we try to read the file and only grab the graph size in this try catch, so we can initalize the graph with graphSize
            Scanner fileScanner = new Scanner(file);
            graphSize = fileScanner.nextInt();
            fileScanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.addSuppressed(e);
            System.out.println("File not found!");
        }  
        Graph graph = new Graph(graphSize);                                             //we initalize graph and then read into it from next try catch




        String direction = "";
        while(!(direction.equals("U")) && (!(direction.equals("D"))))
        {
            System.out.println("Do you want undirected or directed graph, put D for directed, U for Undirected:");
            direction = scanner.next();
            if (direction.equals("U"))                                              //THIS ADDS THE UNDIRECTIONAL/Directional extra credit function, just change the graph function to go from both ways to one way
            {
                try 
                {
                    Scanner fileScanner2 = new Scanner(file);                                  //make another try catch
                    int arraySizeAgain = fileScanner2.nextInt();                               //skip the first int so we can read into vertices properly

                    while (fileScanner2.hasNext())                                             //while the file is not empty we read into the graph object
                    {
                        int verticeOne = fileScanner2.nextInt();                               //every two vertices/ints go together
                        int verticeTwo = fileScanner2.nextInt();
                        graph.addEdgeNondirectional(verticeOne, verticeTwo);                               //add the edge to the vertices, this does both ways
                    }
                    fileScanner2.close();               
                }
                catch (FileNotFoundException e)
                {
                    e.addSuppressed(e);
                    System.out.println("File not found!");
                }  
            }



            else if (direction.equals("D"))                                                            
            {
                try 
                {
                    Scanner fileScanner2 = new Scanner(file);                                  //make another try catch
                    int arraySizeAgain = fileScanner2.nextInt();                               //skip the first int so we can read into vertices properly

                    while (fileScanner2.hasNext())                                             //while the file is not empty we read into the graph object
                    {
                        int verticeOne = fileScanner2.nextInt();                               //every two vertices/ints go together
                        int verticeTwo = fileScanner2.nextInt();
                        graph.addEdgeDirectional(verticeOne, verticeTwo);                               //add the edge to the vertices, THIS IS ONLY ONE DIRECTION!!!
                    }
                    fileScanner2.close();               
                }
                catch (FileNotFoundException e)
                {
                    e.addSuppressed(e);
                    System.out.println("File not found!");
                }  
            }
            else
            {
                System.out.println("ONLY PUT U AND D PLEASE TRY AGAIN");
            }
        }//end of p or u while loop












        int currentVertex = -1;                                                         //this is used for while loop below, and also for what vertex we are on in the main, very important variable(viv)   
        while (currentVertex > graphSize || currentVertex < 0)                          //this is input validation, we do not want to enter a out of bounds variable
        { 
            System.out.println("Enter the vertex to start at:");                      //We will still enter this since orginally we made current vertex -1
            currentVertex = scanner.nextInt();
        }
        String exitString = "";                                                          //used for principal commands: M,S,P,Q


        while (!(exitString.equals ("Q")))                                     //Wont quit loop until Q for quit is entered
        {
            System.out.println("");                                                     
            System.out.println("Current vertex is: " + currentVertex);   
            graph.adjacentVertice(currentVertex);                                       //this grabs the adjacent vertices to the current vertex
            System.out.println("What would you like to do? Move(M), Print Graph(P), Search(S), or Q to quit:");
            exitString = scanner.next();                                                //this determines which command we will enter, M,S,P,Q  



            if(exitString.equals("M"))                                         //M stands for moving vertices 
            {
                System.out.println("Please enter adjacent vertex you want to move to:");    //We figure out where they want to move to
                int inputVertex = scanner.nextInt();
                currentVertex = graph.moveVertex(inputVertex,currentVertex);                  //we go into the move vertex method, note the arguments arr important




            }
            else if (exitString.equals("P"))                                         //P stands for print/display list of all connections, very easy, simple display method
            {
                graph.display();
            }

            else if(exitString.equals("S"))                                          //S stands for searching for another point in the graph, uses depth first search methods
            {

                System.out.println("Please enter value you want to search for:");           //Need to know where user wants to go
                int key = scanner.nextInt();
                DepthFirstPaths dps = new DepthFirstPaths(graph, currentVertex);              //make depth first object, this immediately starts using the graph, and depth first search, do not need to call other depth methods
                boolean connectedness = dps.hasPathTo(key); 
                dps.connectedGraph();                                  //if there is a path then we can return it as true or false, false will also return if there is out of bounds(negative too) numbers input, WILL PRINT FALSE IF GRAPH IS DISJOINT AND NOT CONNECTED(aka no path)

                if (connectedness == true)                                                    //true means path
                {
                    System.out.println(currentVertex + " Has a path to " + key);
                }
                else                                                                          //false means no path
                {
                    System.out.println("No path found from " + currentVertex + " to " + key);
                }


            }
            else                                                                              //If there is no M S P Q then we repeat until they enter one of those
            {
                System.out.println("Please only enter a M,P,S,or Q");
            }


        }//end of while loop
        System.out.println("Bye Bye!");                                                    //If they exit the loop then they are done
        scanner.close();                                                                     //close the scanner and go home
    }//end of main method   
}//end of Driver class

