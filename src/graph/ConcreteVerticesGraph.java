/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    // represent a graph and all vertex is in vertices and all edge also in vertices
    // Representation invariant:
    // all vertices in graph in this vertices and all edges is also in vertices.all weight associated with edges is bigger than zero, and Vertex is not duplicated 
    // Safety from rep exposure:
    // all fields is private and make defensive for targets()
    
    // constructor
    /**
     * same as default(because data fields have member construct)
     */
    public ConcreteVerticesGraph()
    {
        
    }
    // TODO checkRep
    private void checkRep()
    {
        assert vertices != null;
        
        List<String> vertices_lable_vertex = new ArrayList<>();
        List<String> vertices_lable_vertex_target = new ArrayList<>();
        
        for(Vertex v : vertices)
        {
            vertices_lable_vertex.add(v.getVertex());
            vertices_lable_vertex_target.addAll(v.getEdge().keySet());
        }
        
        for(String s : vertices_lable_vertex_target)
        {
            assert vertices_lable_vertex.contains(s);
        }
        
        for(int i = 0; i < vertices_lable_vertex.size(); i++)
        {
            for(int j = i + 1; j < vertices_lable_vertex.size(); j++)
            {
                assert !vertices_lable_vertex.get(i).equals(vertices_lable_vertex.get(j));
            }
        }
    }
    
    @Override public boolean add(String vertex) {
        //throw new RuntimeException("not implemented");
        
        int exist_flag = 0;
        
        for(Vertex v : vertices)
        {
            if(v.getVertex().equals(vertex))
            {
                exist_flag = 1;
                break;
            }
        }
        
        if(exist_flag == 1)
                return false;
        
        Vertex v = new Vertex(vertex);
        
        vertices.add(v);
        
        checkRep();
        
        return true;
        
    }
    
    @Override public int set(String source, String target, int weight) {
        //throw new RuntimeException("not implemented");
        
        int exist_flag_source = 0;
        int exist_flag_target = 0;
        
        Vertex exist_vertex_source = null;
        Vertex exist_vertex_target = null;
        
        for(Vertex v : vertices)
        {
            if(v.getVertex().equals(source))
            {
                exist_flag_source = 1;
                
                exist_vertex_source = v;
            }
            
            if(v.getVertex().equals(target))
            {
                exist_flag_target = 1;
                
                exist_vertex_target = v;
            }
        }
        
        if(exist_flag_source == 0)
        {
            if(weight > 0)
            {
                Vertex v = new Vertex(source);
                
                v.add_edge(target, weight);
                
                vertices.add(v);
                
                if(exist_flag_target == 0)
                {
                    Vertex e = new Vertex(target);
                    
                    vertices.add(e);
                }
                
                checkRep();
                
                return 0;
            }
            else if(weight == 0)
            {                
                checkRep();
                
                return 0;
            }
        }
        else
        {
            Map<String, Integer> edges = exist_vertex_source.getEdge();
            
            int edge_exist_flag = 0;
            
            int edge_exist_weight = -1;
            
            for(Map.Entry<String, Integer> entry : edges.entrySet())
            {
                if(entry.getKey().equals(target))
                {
                    edge_exist_flag = 1;
                    
                    edge_exist_weight = entry.getValue();
                    
                    break;
                }
            }
            
            if(weight > 0)
            {
                if(exist_flag_target == 0)
                {
                    Vertex e = new Vertex(target);
                    
                    vertices.add(e);
                }  
                
                if(edge_exist_flag == 1)
                {
                    exist_vertex_source.set_edge(target, weight);
                    
                    checkRep();
                    
                    return edge_exist_weight;
                }
                else 
                {
                    exist_vertex_source.add_edge(target, weight);
                    
                    checkRep();
                    
                    return 0;
                }
            }
            else if(weight == 0)
            {
                if(edge_exist_flag == 1)
                {
                    exist_vertex_source.remove_edge(target);
               
                    checkRep();
                    
                    return edge_exist_weight;
                }
                else
                {
                    return 0;
                }
            }
        }
        
        return -1;
    }
    
    @Override public boolean remove(String vertex) {
        //throw new RuntimeException("not implemented");
        
        int exist_flag = 0;
        
        Vertex exist_vertex = null;
        
        for(Vertex v : vertices)
        {
            if(v.getVertex().equals(vertex))
            {
                exist_vertex = v;
                
                exist_flag = 1;
                
                break;
            }
        }
        
        if(exist_flag == 0)
        {
            return false;
        }
        else
        {
            vertices.remove(exist_vertex);
            
            for(Vertex v : vertices)
            {
                v.remove_edge(vertex);
            }
            
            checkRep();
            
            return true;
        }
        
    }
    
    @Override public Set<String> vertices() {
        //throw new RuntimeException("not implemented");
        
        Set<String> return_vertex = new HashSet<>();
        
        for(Vertex v : vertices)
        {
            return_vertex.add(v.getVertex());
        }
        
        checkRep();
        
        return return_vertex;
    }
    
    @Override public Map<String, Integer> sources(String target) {
        //throw new RuntimeException("not implemented");
        
        Map<String, Integer> return_source = new HashMap<>();
        
        for(Vertex v : vertices)
        {
            if(v.getEdge().keySet().contains(target))
            {
                return_source.put(v.getVertex(), v.getEdge().get(target));
            }
        }
        
        checkRep();
        
        return return_source;
        
    }
    
    @Override public Map<String, Integer> targets(String source) {
        //throw new RuntimeException("not implemented");
        
        Map<String, Integer> return_target = null;
        
        for(Vertex v : vertices)
        {
            if(v.getVertex().equals(source))
            {
                return_target = v.getEdge();
                
                break;
            } 
        }
        
        checkRep();
        
        return return_target;
    }
    
    // TODO toString()
    
}

/**
 * this mutable data type represent a vertex(include all the edge from this vertex)
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
    
    // fields
    private final String vertex;
    private final Map<String, Integer> edges = new HashMap<>();
    
    // Abstraction function:
    // represent a vertex include all edges from this vertex
    // Representation invariant:
    // edges is no duplicate and weight is bigger than zero
    // Safety from rep exposure:
    // all fields is private and vertex is immutable , edges is mutable but make defensive copies in getEdge()
    
    /**
     * create vertex with empty edge set(which source is this vertex)
     * @param vertex vertex label
     */
    public Vertex(String vertex)
    {
        this.vertex = vertex;
        
        checkRep();
    }
    
    /**
     * check  representation invariant of the Vertex is true 
     */
    private void checkRep()
    {
        assert vertex != null;
        for(Map.Entry<String, Integer> entry : edges.entrySet())
        {
            assert entry.getKey() != null;
            assert entry.getValue() > 0;
            
            assert entry.getValue() != null;
        }
    }
    
    // methods
    
    /**
     * add edge(add new edge which source is this vertex)
     * @param target new edge's target (require target is not in this vertex's edge set)
     * @param weight new edge's weight (require weight is bigger than zero)
     */
    public void add_edge(String target, Integer weight)
    {
        edges.put(target, weight);
        
        checkRep();
    }
    
    
    /**
     * set edge(set new weight of edge which source is this vertex and exits in this vertex's edge set )
     * @param target the target of edge need to be set(require in this vertex's edge set)
     * @param weight the weight of edge need to be set(require weight is bigger than zero)
     */
    public void set_edge(String target, Integer weight)
    {
        edges.replace(target, weight);
        
        checkRep();
    }
    
    /**
     * remove edge from this vertex which target is special vertex 
     * @param vertex the target of edge need to be remove(require vertex is in this vertex edge set)
     */
    public void remove_edge(String vertex)
    {
        edges.remove(vertex);
        
        checkRep();
    }
    
    
    /**
     * get this vertex label
     * @return return this vertex label
     */
    public String getVertex()
    {
        return vertex;
    }
    
    /**
     * get the edge set of this vertex
     * @return edge map with key is String which represent target and value is Integer which represent weight
     */
    public Map<String, Integer> getEdge()
    {
        Map<String, Integer> return_edges = new HashMap<>();
        
        for(Map.Entry<String, Integer> entry : edges.entrySet())
        {
            return_edges.put(entry.getKey(), entry.getValue());
        }
        
        return return_edges;
    }
    
    
    // TODO toString()
    
}
