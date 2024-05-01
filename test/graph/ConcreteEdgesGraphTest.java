/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

//import graph.Edge;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteEdgesGraph.toString()
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    // Edge():
    // weight <= 0, weight > 0
    // getSource():
    // edge create by Edge()
    // getTarget():
    // edge create by Edge()
    // getWeight():
    // edge create by Edge()
    // toString():
    // edge create by Edge()
    // compare()
    // e1 same with e2 or e1 not same with e2
    
    // TODO tests for operations of Edge
    @Test
    public void testEdge()
    {
        Edge e = new Edge("Num1", "Num2", Integer.valueOf(1));
        
        assertEquals("expected source is Num1", "Num1", e.getSource());
        assertEquals("expected target is Num2", "Num2", e.getTarget());
        assertEquals("expected weight is 1", Integer.valueOf(1), e.getWeight());
        
        assertEquals("expected correct toString output", "Num1-1->Num2", e.toString());
        
        Edge e1 = new Edge("Num1", "Num3", Integer.valueOf(1));
        
        Edge e2 = new Edge("Num1", "Num3", Integer.valueOf(1));
        
        assertFalse("expect e not same with e1", Edge.compare(e, e1));
        assertTrue("expect e1 is same with e2", Edge.compare(e1, e2));
        
        // not to test weight <= 0 case
    }
}
