package graph;

import java.util.List;

public class GraphMatrix extends DigraphMatrix
{

    public GraphMatrix(List<Vertex> vertices)
    {
        super(vertices);
    }

    public GraphMatrix()
    {
        super();
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
    protected GraphMatrix clone() throws CloneNotSupportedException
    {
        return (GraphMatrix)super.clone();
    }

    @Override
    public void lockEdge(Vertex source, Vertex destination, int lockID)
    {
        super.lockEdge(source, destination, lockID);
        super.lockEdge(destination, source, lockID);
    }
}
