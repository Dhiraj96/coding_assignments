/*
 * File: Vertex.java
 */

import java.util.ArrayList;

public class Vertex {

	// adjacency list of neighbors with outgoing edges
	private ArrayList<Vertex> outneighbors = new ArrayList<Vertex>(); 

	// adjacency list of neighbors with incoming edges
	private ArrayList<Vertex> inneighbors = new ArrayList<Vertex>(); 

  // Fields go here. You may add other fields if you wish, but you should not
  // need to
  private String name;
  private int ccnum = -1; //unset
  private boolean visited = false; //"unvisited"

	/* 
   * construct a vertex to represent a given Boolean variable 
   */
	public Vertex(String name) {
    this.name = name;
	}

  public void addEdge(Vertex v) {
    this.outneighbors.add(v);
    v.inneighbors.add(this);
  }
	
	// this gives you access to adjacency list
	public ArrayList<Vertex> getInNeighbors() {
		return inneighbors;
	}

	// this gives you access to adjacency list
	public ArrayList<Vertex> getOutNeighbors() {
		return outneighbors;
	}

  public void setCCNum(int ccnum) {
    this.ccnum = ccnum;
  }

  public int getCCNum() {
    return this.ccnum;
  }

  public boolean isVisited() {
    return visited;
  }
	
  public void visit() {
    visited = true;
  }

  public void reset() {
    visited = false;
    ccnum = -1;
  }
}
