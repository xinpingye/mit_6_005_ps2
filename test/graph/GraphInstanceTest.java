/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

import java.util.Map;
import java.util.Set;

import java.util.Iterator;

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
    // edge = not exist ,exist(one vertex exist), exist(two vertex exist)
    // weight = 0, not zero
    // 
    // remove():
    // vertex that be removed is not exist or exits(no edge attach) or exist(have edge to) or exist(have edge from)
    // 
    // vertices():
    // no vertex is in graph, have vertex is in graph
    //
    // sources():
    // result is empty or not empty
    // many source to same target
    //
    // targets():
    // result is empty or not empty
    // many target from same source
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
    
    @Test 
    public void testSetEdge()
    {
        Graph<String> g = emptyInstance();
        
        
        //set case 1
        assertEquals("expected 0 by set new edge Num1->Num2", 0, g.set("Num1", "Num2", 1));
        
        Set<String> st1 = g.vertices();
        
        //Iterator<String> it = st.iterator();
        
        assertEquals("expected 2 members of vertices", 2, st1.size());
        
        assertTrue("expected Num1 is in st", st1.contains("Num1"));
        
        assertTrue("expected Num2 is in st", st1.contains("Num2"));
        
        Map<String, Integer> mp1 = g.sources("Num2");
        
        assertEquals("expected mp1 size is 1", 1, mp1.size());
        
        assertTrue("expected Num1 is in mp1", mp1.containsKey("Num1"));
        
        assertEquals("expected mp1['Num1'] = 1", Integer.valueOf(1), mp1.get("Num1"));
        
        Map<String, Integer> mp2 = g.targets("Num1");
        
        assertEquals("expected mp2 size is 1", 1, mp2.size());
        
        assertTrue("expected Num2 is in mp2", mp2.containsKey("Num2"));
        
        assertEquals("expected mp2['Num2'] = 1", Integer.valueOf(1), mp2.get("Num2"));
        
        
        //set case 2
        assertEquals("expected 0 by set new edge Num3->Num2", 0, g.set("Num3", "Num2", 2));
        
        Set<String> st2 = g.vertices();
        
        assertEquals("expected st2 size is 3", 3, st2.size());
        
        assertTrue("expected Num1 is in st", st2.contains("Num1"));
        
        assertTrue("expected Num2 is in st", st2.contains("Num2"));
        
        assertTrue("expected Num3 is in st", st2.contains("Num3"));
        
        Map<String, Integer> mp3 = g.sources("Num2");
        
        assertEquals("expected mp3 size is 2", 2, mp3.size());
        
        assertTrue("expected Num1 is in mp3", mp3.containsKey("Num1"));
        
        assertEquals("expected mp3['Num1'] = 1", Integer.valueOf(1), mp3.get("Num1"));
        
        assertTrue("expected Num3 is in mp3", mp3.containsKey("Num3"));
        
        assertEquals("expected mp3['Num3'] = 2", Integer.valueOf(2), mp3.get("Num3"));
        
        assertEquals("expected corresponding source nums is 0 when target is Num1", 0, g.sources("Num1").size());
        
        Map<String, Integer> mp4 = g.targets("Num1");
        
        assertEquals("expected mp4 size is 1", 1, mp4.size());
        
        assertTrue("expected Num2 is in mp4", mp4.containsKey("Num2"));
        
        assertEquals("expected mp4['Num2'] = 1", Integer.valueOf(1), mp4.get("Num2"));
        
        assertEquals("expected corresponding target nums is 0 whrn source is Num2", 0, g.targets("Num2").size());
        
        Map<String, Integer> mp5 = g.targets("Num3");
        
        assertEquals("expected mp5 size is 1", 1, mp5.size());
        
        assertTrue("expected Num2 is in mp5", mp5.containsKey("Num2"));
        
        assertEquals("expected mp5['Num2'] = 2", Integer.valueOf(2), mp5.get("Num2"));
        
        //set case 3
        assertEquals("expected 1 by set exist edge Num1->Num2", 1, g.set("Num1", "Num2", 3));
        
        Map<String, Integer> mp6 = g.sources("Num2");
        
        assertEquals("expected mp6['Num1'] = 3", Integer.valueOf(3), mp6.get("Num1"));
        
        Map<String, Integer> mp7 = g.targets("Num1");
        
        assertEquals("expected mp7['Num2'] = 3", Integer.valueOf(3), mp7.get("Num2"));
        
        //set case4
        assertEquals("expected 3 by set exist edge Num1->Num2 and weight is zero", 3, g.set("Num1", "Num2", 0));
        
        Set<String> st3 = g.vertices();
        
        assertTrue("expected Num1 is in st3", st3.contains("Num1"));
        
        assertTrue("expected Num2 is in st3", st3.contains("Num2"));
        
        assertTrue("expected Num3 is in st3", st3.contains("Num3"));
        
        Map<String, Integer> mp8 = g.sources("Num2");
        
        assertEquals("expected mp8 size is 1", 1, mp8.size());
        
        assertFalse("expected Num1 is not in mp8", mp8.containsKey("Num1"));
        
        Map<String, Integer> mp9 = g.targets("Num1");
        
        assertEquals("expected mp9 size is 0", 0, mp9.size());
        
    }
    
    @Test
    public void testRemoveVertex()
    {
        Graph<String> g = emptyInstance();
        
        //case 1 remove vertex which is not exist
        
        assertFalse("expected false when remove vertex which is not in graph", g.remove("Num1"));
        
        //case 2 remove vertex which is exist but no edge attach
        
        g.add("Num1");
        
        assertTrue("expected true when remove vertex which is in graph", g.remove("Num1"));
        
        //case 3 remove vertex which is exist and only have edge to other vertex
        
        g.set("Num1", "Num2", 1);
        
        Map<String, Integer> mp1 = g.targets("Num1");
        
        assertEquals("expected Num1 have one edge weighted 1 and target is Num2", Integer.valueOf(1), mp1.get("Num2"));
        
        Map<String, Integer> mp2 = g.sources("Num1");
        
        assertEquals("expected the size of set which is the source set which target is Num1 is zero", 0, mp2.size());
        
        g.remove("Num1");
        
        Set<String> st1 = g.vertices();
        
        assertEquals("expected vertex set size is 1 after remove Num1", 1, st1.size());
        
        assertTrue("expected Num2 is in st", st1.contains("Num2"));
        
        assertEquals("expected source set size of Num2 is zero", 0, g.sources("Num2").size());
        assertEquals("expected target set size of Num2 is zero", 0, g.targets("Num2").size());
        
        //case 4 remove vertex which is exist and only have edge from vertex
        
        g.set("Num1", "Num2", 1);
        
        Map<String, Integer> mp3 = g.sources("Num2");
        
        assertEquals("expected Num2 have one edge weighted 1 and source is Num1", Integer.valueOf(1), mp3.get("Num1"));
        
        Map<String, Integer> mp4 = g.targets("Num2");
        
        assertEquals("expected the size of set which is the target set which source is Num2 is zero", 0, mp4.size());
        
        g.remove("Num2");
        
        Set<String> st2 = g.vertices();
        
        assertEquals("expected vertex set size is 1 after remove Num2", 1, st2.size());
        
        assertTrue("expected Num1 is in st", st2.contains("Num1"));
        
        assertEquals("expected source set size of Num1 is zero", 0, g.sources("Num1").size());
        assertEquals("expected target set size of Num1 is zero", 0, g.targets("Num1").size());
        
        // case 5 remove vertex which is exist and have edge from and to vertex
        
        g.set("Num1", "Num2", 1);
        g.set("Num2", "Num3", 2);
        
        Map<String, Integer> mp5 = g.sources("Num2");
        assertEquals("expected set which is source set when target is Num2 size is 1", 1, mp5.size());
        assertEquals("expected Num2 have one edge weighted 1 and source is Num1", Integer.valueOf(1), mp5.get("Num1"));
        
        Map<String, Integer> mp6 = g.targets("Num2");
        assertEquals("expected set which is target set when source is Num2 size is 1", 1, mp6.size());
        assertEquals("expected Num2 have one edge weighted 2 and target is Num3", Integer.valueOf(2), mp6.get("Num3"));

        Map<String, Integer> mp7 = g.targets("Num1");
        assertEquals("expected set which is target set when source is Num1 size is 1", 1, mp7.size());
        assertEquals("expected Num1 have one edge weighted 1 and target is Num2", Integer.valueOf(1), mp7.get("Num2"));
        
        Map<String, Integer> mp8 = g.sources("Num3");
        assertEquals("expected set which is source set when target is Num3 size is 1", 1, mp8.size());
        assertEquals("expected Num3 have one edge weighted 2 and source is Num2", Integer.valueOf(2), mp8.get("Num2"));
                
        // try to remove and test
        
        g.remove("Num2");
        
        Set<String> st3 = g.vertices();
        
        assertEquals("expected vertex set size is 2 after remove Num2", 2, st3.size());
        assertTrue("expected Num1 is in st3", st3.contains("Num1"));
        assertTrue("expected Num3 is in st3", st3.contains("Num3"));
        
        Map<String, Integer> mp9 = g.targets("Num1");
        assertEquals("expected set which is target set when source is Num1 size is 0", 0, mp9.size());
        
        Map<String, Integer> mp10 = g.sources("Num3");
        assertEquals("expected set which is source set when target is Num3 size is 0", 0, mp10.size());
        
    }
    
    // TODO other tests for instance methods of Graph
}
