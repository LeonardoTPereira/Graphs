package graph;

import java.util.List;

public class GraphMap extends DigraphMap
{
    public GraphMap(List<Vertex> vertices)
    {
        super(vertices);
    }

    public GraphMap()
    {
        super();
    }

    @Override
    public void removeVertex(Vertex vertex)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addEdge(Vertex source, Vertex destination, float weight)
    {
        super.addEdge(source, destination, weight);
        super.addEdge(destination, source, weight);
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

    @Override
    protected GraphMap clone() throws CloneNotSupportedException
    {
        return (GraphMap) super.clone();
    }

    @Override
    public void lockEdge(Vertex source, Vertex destination, int lockID)
    {
        super.lockEdge(source, destination, lockID);
        super.lockEdge(destination, source, lockID);
    }
}
