package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest
{
    @Test
    void constructorWeight()
    {
        Edge vertex = new Edge(1.5f);
        assertEquals(1.5f, vertex.getWeight());
    }

    @Test
    void constructorWeightAndDestination()
    {
        Vertex vertex = new Vertex("A");
        Edge edge = new Edge(vertex, 1.5f);
        assertEquals(1.5f, edge.getWeight());
        assertEquals(vertex, edge.getDestination());
    }

    @Test
    void getDestination()
    {
        Vertex vertex = new Vertex("A");
        Edge edge = new Edge(vertex, 1.5f);
        assertEquals(vertex, edge.getDestination());
    }

    @Test
    void setDestination()
    {
        Vertex vertex = new Vertex("A");
        Edge edge = new Edge(1.5f);
        edge.setDestination(vertex);
        assertEquals(vertex, edge.getDestination());
    }

    @Test
    void getWeight()
    {
        Edge edge = new Edge(new Vertex("A"), 1.5f);
        assertEquals(1.5f, edge.getWeight());
    }

    @Test
    void setWeight()
    {
        Edge edge = new Edge(1.5f);
        edge.setWeight(2.5f);
        assertEquals(2.5f, edge.getWeight());
    }
}