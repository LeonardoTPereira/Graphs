package graph;

import java.util.List;

public abstract class AbstractGraph implements GraphInterface
{
    protected int numberOfVertices;
    protected List<Vertex> vertices;

    protected AbstractGraph(List<Vertex> vertices)
    {
        numberOfVertices = vertices.size();
        this.vertices = vertices;
    }
}
