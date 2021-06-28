package graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VertexTest
{
    @Test
    void constructor()
    {
        Vertex vertex = new Vertex("A");
        assertEquals("A", vertex.getName());
    }


    @Test
    void getName()
    {
        Vertex vertex = new Vertex("A");
        assertEquals("A", vertex.getName());
    }

    @Test
    void setName()
    {
        Vertex vertex = new Vertex("A");
        vertex.setName("B");
        assertEquals("B", vertex.getName());
    }

    @Test
    void testToString()
    {
        Vertex vertex = new Vertex("A");
        String expected = "Vertex{" +
                "name='" + vertex.getName() + '\'' +
                '}';
        assertEquals(expected, vertex.toString());
    }
}