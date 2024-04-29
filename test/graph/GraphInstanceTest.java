/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //  TODO
    // test strategy for each operation of Graph
    // add():
    // add vertex string = exists, not exists
    // existing vertex number = 0, 1, n
    // set():
    //
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    
    @Test
    public void testAddVertex()
    {
        Graph<String> g = emptyInstance();
        
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
        
        assertTrue("expected add new Vertex Num1 succeed", g.add("Num1"));
        
        assertTrue("expected add new Vertex Num2 succeed", g.add("Num2"));
        
        assertFalse("expected add new Vertex Num1 fail", g.add("Num1"));
    }
    
    
    
    // TODO other tests for instance methods of Graph
}
