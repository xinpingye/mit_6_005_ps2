/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    // represent a graph , all vertex is in vertices and all edges is in edges,edge is directed and weighted
    // Representation invariant:
    // all vertex associated with edges is in vertices, and all weight associated with edges is bigger than zero, and edges is not duplicated 
    // Safety from rep exposure:
    // all fields are private,  vertices is mutable, so ConcreteEdgesGraph() and vertices() make defensive copies to avoid sharing class members to clients 
    
    // TODO constructor
    /**
     * same as default(because data fields have member construct)
     */
    public ConcreteEdgesGraph()
    {}
    
    // TODO checkRep
    /**
     * check representation invariant is not exposure 
     */
    private void checkRep()
    {
        for(Edge<L> e : edges)
        {
            assert vertices.contains(e.getSource());
            assert vertices.contains(e.getTarget());
            assert e.getWeight() > 0;
        }
        
        for(int i = 0; i < edges.size(); i++)
        {
            for(int j = i + 1; j < edges.size(); j++)
            {
                assert !Edge.compare(edges.get(i), edges.get(j));
            }
        }
    }
    
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
        if(vertices.contains(vertex))
        {
            checkRep();
            
            return false;
        }
        else
        {
            vertices.add(vertex);
            
            checkRep();
            
            return true;
        }
    }
    
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
        
        int exist_flag = 0;
        
        for(Edge<L> e : edges)
        {
            if(e.getSource().equals(source) && e.getTarget().equals(target))
            {
                exist_flag = 1;
            }
        }
        
        if(exist_flag == 0)
        {
            if(weight == 0)
            {
                checkRep();
                
                return 0;
            }
            else
            {
                vertices.add(source);
                vertices.add(target);
                
                Edge<L> e = new Edge<L>(source, target, weight);
                
                edges.add(e);
                
                checkRep();
                
                return 0;
            }
        }
        else
        {
            if(weight == 0)
            {
                for(Edge<L> e : edges)
                {
                    if(e.getSource().equals(source) && e.getTarget().equals(target))
                    {
                        Integer return_weight = e.getWeight();
                        
                        edges.remove(e);
                       
                        checkRep();
                        
                        return return_weight.intValue();
                    }
                }
            }
            else
            {
                for(Edge<L> e : edges)
                {
                    if(e.getSource().equals(source) && e.getTarget().equals(target))
                    {   
                        Integer return_weight = e.getWeight();
                        
                        Edge<L> insert_e = new Edge<L>(e.getSource(), e.getTarget(), weight);
                        
                        edges.remove(e);
                        
                        edges.add(insert_e);
                        
                        checkRep();
                        
                        return return_weight.intValue();
                    }
                }  
            }
        }
        
        return -1;

    }
    
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
        
        if(vertices.contains(vertex))
        {
            vertices.remove(vertex);
            
            List<Edge<L>> remove_list = new ArrayList<>(); 
            
            for(Edge<L> e : edges)
            {
                if(e.getSource().equals(vertex) || e.getTarget().equals(vertex))
                    remove_list.add(e);
            }
            
            edges.removeAll(remove_list);
            
            checkRep();
            
            return true;
        }
        else
        {
            checkRep();
            
            return false;
        }
    }
    
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
        
        Set<L> return_vertices_set = new HashSet<>();
        
        for(L s : vertices)
        {
            return_vertices_set.add(s);
        }
        
        checkRep();
        
        return return_vertices_set;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
        
        Map<L, Integer> return_source_map = new HashMap<>();
        
        for(Edge<L> e : edges)
        {
            if(e.getTarget().equals(target))
                return_source_map.put(e.getSource(), e.getWeight());
        }
        
        checkRep();
        
        return return_source_map;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
        
        Map<L, Integer> return_target_map = new HashMap<>();
        
        for(Edge<L> e : edges)
        {
            if(e.getSource().equals(source))
                return_target_map.put(e.getTarget(), e.getWeight());
        }
        
        checkRep();
        
        return return_target_map;
        
    }
    
    // TODO toString()
    
    @Override public String toString()
    {
        return "test";
    }
}

/**
 * this immutable data type represent a Edge
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    //fields
    private final L  source;
    private final L  target;
    private final Integer weight;
    // Abstraction function:
    // represent a edge which source point to target vertex with the same weight
    // Representation invariant:
    // weight > 0
    // Safety from rep exposure:
    // all fields are private and all types in the rep are immutable
    
    // creator method
    /**
     * make a new Edge
     * @param source    the source vertex of the Edge
     * @param target    the target vertex of the Edge
     * @param weight    the weight of the Edge
     */
    public Edge(L source, L target, Integer weight)
    {
        this.source = source;
        this.target = target;
        this.weight = weight;
        
        checkRep();
    }
    // checkRep
    /**
     * check  representation invariant of the Edge is true 
     */
    private void checkRep()
    {
        assert source != null;
        assert target != null;
        assert weight != null;
       
        assert weight > 0;
    }
    // observer methods
    /**
     * @return return source vertex of this Edge
     */
    public L getSource()
    {
        return source;
    }
    
    /**
     * @return return target vertex of this Edge
     */
    public L getTarget()
    {
        return target;
    }
    
    /**
     * @return return weight of this Edge
     */
    public Integer getWeight()
    {
        return weight;
    }
    // toString()
    @Override public String toString()
    {
        return source + "-" + weight + "->" + target;
    }
    
    /**
     * compare ADT object e1 and e2 
     * @param e1 
     * @param e2
     * @return if e1 equal with e2 , return true, else return false
     */
    public static boolean compare(Edge e1, Edge e2)
    {
        return (e1.getSource() == e2.getSource() && e1.getTarget() ==e2.getTarget());
    }
    
}
