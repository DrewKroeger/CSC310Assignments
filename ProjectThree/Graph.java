/*********************************************  
 * Drew Kroeger, CSC310- ANALYSIS OF ALGORITHMS, MARCH 26,2024- DUE APRIL 12TH,2024- PROJECT 3 
 * This is the graph class for graph project. This goes with DepthFirstPaths.java, ProjectThreeMain.java,LinkList.java, and Link.java
 * This acts as a graph object, and works closely with the linkList class, as this uses adjacency list(aka a array of linkLists)
 *******************************************/


package PROJECT3;

public class Graph 
{
    private final int V;                                        //this is the size of the LL
    private LinkList[] adj;                                     //this is an array of LL


    //--------------------------------------------------------

    //this is the constructor for the graph object

    public Graph(int V)                                                                     //need to input the size of the graph(how many vertices) as an argument
    {
        this.V = V;    
        adj = new LinkList[V];                                                              //make adjacent list- aka array of linked lists 
        for (int v = 0; v < V; v++)                                                         //makes each index a linked list, this makes some indexing confusing here and there, but it all works in the end
        {
            adj[v] = new LinkList();
        }
    }

    
    //-------------------------------------------------------

    //This is the add edge, this is used in the automated try catch in the main, there is nondirection for undirected graphs, and directional for directed graphs


    public void addEdgeNondirectional(int v, int w)                                                       
    {
        adj[v].insertFirst(w);                              //we insert a link to and from each edge
        adj[w].insertFirst(v);
    }
    public void addEdgeDirectional(int v, int w)                                                       
    {
        adj[v].insertFirst(w);                              //we insert a link only one way
        
    }

    //----------------------------------------

    //this returns a linkedlist, by asking for the index of the array of linked lists

    public LinkList adj(int v)
    {
        return adj[v]; 
    }


    //------------------------------------------

    //This displays the whole adjacency list, this does each linked list by doing display list from the linklist class in a for loop

    public void display()
    {
        for (int i = 0; i<V; i++)
        {
            System.out.println("Vertex:" + i);
            adj[i].displayList();
        }
    }

    //-------------------------------------------

    //This finds the adjacent vertices for the the input vertex


    public void adjacentVertice(int v)
    {
        if (v >= V || v < 0)                                                            //if the input is not valid, then we do this, i think this is only needed in one use case, most error is handled in the move vertex methods
        {
            System.out.println("You input an invalid vertex, try again please");
        }
        else                                                                           //normally come here. just call the adjacent vertices for the current vertex, by showing linked list at index v in adjacency list
        {
            System.out.println("You input " + v + " For your current vertex");
            System.out.println("The vertice you can travel to are:");
            adj[v].displayList();
        }
    }


    //-------------------------------------------------------------

    //This handles most of the input validations, with out of bounds and such, however its main function is to just move the vertex to a different point
    

    public int moveVertex(int v, int x) //input is the vertex we want to go to and the current vertex 
    {

        LinkList thisList = new LinkList();         //make a linklist and put the list of points adjacent to the current vertex into it
        thisList = adj[x];                                  
        int returnVertex = thisList.find(v);        //try to find the target vertex in the points adjacent to current 


        if (returnVertex == -1)                    //not founds means not adjacent,or does not exist, so we stay put
        {
            System.out.println("The new vertice is not adjacent to current vertice, staying at current vertex");
            return x;
        }
        else                                      //not a -1 means the target vertex was found so we do move 
        {
            System.out.println("Moving to adjacent vertice...");
            return returnVertex;                 //return target vertex
        } 
    }

    //------------------------------------------------

    //This just allows other classes with graph object to get the vertex count, i think this is used in the depthsfirstpaths.java class

    public int getVerticeCount()
        {
            return V;
        }

}//end of graph class
