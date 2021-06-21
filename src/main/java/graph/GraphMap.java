package graph;

import java.util.List;

public class GraphMap extends DigraphMap
{
    public GraphMap(List<Vertex> vertices)
    {
        super(vertices);
    }

    @Override
    public void addVertex(Vertex vertex)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeVertex(Vertex vertex)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addEdge(Vertex source, Vertex destination)
    {
        super.addEdge(source, destination);
        super.addEdge(destination, source);
    }

    @Override
    public void removeEdge(Vertex source, Vertex destination)
    {
        super.removeEdge(source, destination);
        super.removeEdge(destination, source);
    }


    @Override
    public boolean hasAnyEdge(Vertex vertex)
    {
        return !getAdjacencyMap().get(vertex).isEmpty();
    }
}
