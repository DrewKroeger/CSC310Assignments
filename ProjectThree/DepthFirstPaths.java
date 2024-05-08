/*********************************************  
 * Drew Kroeger, CSC310- ANALYSIS OF ALGORITHMS, MARCH 26,2024- DUE APRIL 12TH,2024- PROJECT 3 
 * This is the graph class for graph project. This goes with Graph.java, ProjectThreeMain.java,LinkList.java, and Link.java
 * This acts as a depthfirstpath object,and works closely with the graph class, when initalized it immediatedly takes a graph and makes a chart of the depth first search
 *******************************************/
package PROJECT3;


//-----------------------------------------------------

//This is a depth first search that goes with a adjacency list graph,see header comment


public class DepthFirstPaths 
{
    private boolean[] marked;                                   //This determines if the current vertex has traveled to other vertices in the graph,true means it has traveled there, false means no    
    private int[] edgeTo;                                       //This is the vertice that was the previous current vertex
    private int s;
    private int verticeCount;                                   //This is used for the amount of vertices in the graph, this is used in the haspathto, in order to check out of bounds points
    
    
    //---------------------------------------------------------

    //This is the depthfirstpath constructor, it does most of the work

    public DepthFirstPaths(Graph G, int s) //input a graph and a current vertex
    {
        this.s = s;                                         //I do not know what this is used for, but its there
        edgeTo = new int[G.getVerticeCount()];              //this declares the size of the edge to graph, one for each vertex
        marked = new boolean[G.getVerticeCount()];          //declares marked graph
        dfs(G, s);                                          //recursive method we immediatedly do when decalring object
        verticeCount = G.getVerticeCount();                 //used for hasPathTo, and the input validation
    }


    //--------------------------------------------------------

    //This is depth first search on adjacency list, it is a recursive method

    private void dfs(Graph G, int v)//inputs are a graph, and a current vertex on that graph
    {
        marked[v] = true;                                                               //we have visited vertex v
        for (Link link = G.adj(v).getFirst(); link != null; link = link.next)           //this indexing was hardest thing, it was hard to get the typing right, basically the point we start at isthe first point of the V's linked list, we go until that linked list is over, and iterate by one
        {
            int w = link.iData;                                                         //take the iData(just a normal int we input initially)
            
            if (!marked[w])                                                             //if we have not marked adjacent point, the when do dfs on it
            {
                dfs(G, w);
                edgeTo[w] = v;                                                          //edgeTo at W becomes the current point
            }
        }
    }



    //--------------------------------------------------------

    //This is the hasPathTo, when actually searching for a path from one point to another this is called 

    public boolean hasPathTo(int v) //V is the key point here
    {
        if (v < 0 || v>= verticeCount)      //Checks the out of bounds again
        {
            System.out.println("You input a vertex that is out of bounds!returning false!");
            return false;
        }

        return marked[v];                   //if not out of bounds we just check the marked array, which stores if we have visited or 'marked' our key point from the current point
    }

    //------------------------------------------------

    //This is the extra credit portion, where we check if the graph is fully connected or not, the marked graph already stores the other points, so we just go through the marked array and check if everything is true

    public boolean connectedGraph()
    {
        for(int i = 0;i<verticeCount; i++)
        {
            if (marked[i] == false)
            {
                System.out.println("Graph is NOT fully connected :(");
                return false;
            }
        }
        System.out.println("GRAPH IS FULLY CONNECTED!!");
        return true;
    }

}//end of DepthfirstPaths.java
