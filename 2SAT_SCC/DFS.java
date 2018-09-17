/*
 * File: DFS.java
 */

import java.util.ArrayList;

public class DFS {

  private ArrayList<Vertex> increasingOrder;

	/* 
   * Input: An ArrayList of vertices representing a graph
   * 
   * This method marks every vertex with a ccnum. Two vertices should share a
   * ccnum if and only if they are strongly connected.
	 */
	public void indentifySCC(ArrayList<Vertex> G) {

    DFSR(G);
    int lastIndex=G.size()-1;
    Vertex current;
    int ccNum=1;
    for(int i=lastIndex-1; i>=0;i--)
    {
      current=G.get(i);
      if(current.getCCNum()==0){
        explore(current,1);
      } 
      ccNum++;
    }

	}


  /* 
   * Standard recursive DFS explore method in the forward direction
   *
   * You may (should) modify the arguments of this function to pass
   * information around (like a ccnum)
   */
	public void explore(Vertex v, int ccnum) {

    v.setCCNum(ccnum);
    ArrayList<Vertex> neighbors=v.getOutNeighbors();
    Vertex current;
    for(int i=0;i<neighbors.size();i++){
      current=neighbors.get(i);
      if(current.getCCNum()==0)
      {
      explore(current,1);
      }    
    }

	}

	
  
  /*
   * Input: An ArrayList of vertices representing a graph
   * Output: The Arraylist of vertices of G sorted in order of *increasing*
   * post number
   *
   * Do NOT actually sort the vertices (n\log n time). 
   * You should add them to the vertex list to be returned as their process
   * completes instead of actually storing the vertex (Linear time)
   */
  public ArrayList<Vertex> DFSR(ArrayList<Vertex> G) {

    for(int i=0;i<G.size();i++)
    {
      G.get(i).reset();
    }
    for(int i=0;i<G.size();i++)
    {
      if(!G.get(i).isVisited())
      {
        exploreR(G.get(i));
      }
    }

    return null;
  }


  /* 
   * Standard recursive DFS explore method in reverse. 
   *
   * Performs the same essential operations as DFS, but looks at incoming
   * edges instead of outgoing edges.
   *
   * You may (should) modify the arguments of this function to pass
   * information around (like an Arraylist)
   */
	public void exploreR(Vertex v) {

    v.visit();
    v.setCCNum(0);
    ArrayList<Vertex> neighbors=v.getInNeighbors();
    Vertex current;
    for(int i=0;i<neighbors.size();i++){
      current=neighbors.get(i);
      if(!current.isVisited())
      {
        exploreR(current);
      }
    }
    increasingOrder.add(v);
  }

}
