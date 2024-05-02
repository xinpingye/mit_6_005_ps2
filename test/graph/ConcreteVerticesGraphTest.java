/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    // Vertex():
    // just vertex without edge
    // add_edge():
    // add edge and weight is legal
    // set_edge():
    // set edge and weight is legal  and edge is in this vertex 
    // remove_edge():
    // remove edge which target is in this vertex 
    // getVertex():
    // vertex has edge 
    // getEdge():
    // has zero or one more edge
    //
    // TODO tests for operations of Vertex
    
    @Test
    public void testVertex()
    {
        Vertex v = new Vertex("Num1");
        
        assertEquals("expect vertex is Num1", "Num1", v.getVertex());
    }
    
    @Test
    public void testAddAndSetAndRemove()
    {
        Vertex v = new Vertex("Num1");
        v.add_edge("Num2", Integer.valueOf(1));
        
        Map<String, Integer> mp1 = v.getEdge();
        assertEquals("expected edge size is 1", 1, mp1.size());
        assertEquals("expected target is Num2 and weight is 1",Integer.valueOf(1), mp1.get("Num2"));
        
        
        v.add_edge("Num3", Integer.valueOf(2));
        
        Map<String, Integer> mp2 = v.getEdge();
        assertEquals("expected edge size is 2", 2, mp2.size());
        assertEquals("expected target is Num3 and weight is 2",Integer.valueOf(2), mp2.get("Num3"));
        
        v.set_edge("Num2", 3);
        
        Map<String, Integer> mp3 = v.getEdge();
        assertEquals("expected edge size is 2", 2, mp3.size());
        assertEquals("expected target is Num2 and weight is 3",Integer.valueOf(3), mp3.get("Num2"));
        
        v.remove_edge("Num2");
        
        Map<String, Integer> mp4 = v.getEdge();
        assertEquals("expected edge size is 1", 1, mp4.size());
        assertEquals("expected target is Num3 and weight is 2",Integer.valueOf(2), mp4.get("Num3"));
        
        v.remove_edge("Num3");
        
        Map<String, Integer> mp5 = v.getEdge();
        assertEquals("expected edge size is 0", 0, mp5.size());
    }
}
