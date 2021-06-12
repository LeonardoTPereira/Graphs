package graph;

import java.util.List;

public class GraphList extends DigraphList
{
    public GraphList(List<Vertex> vertices)
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
        int vertexIndex = vertices.indexOf(vertex);
        return !adjacencyList.get(vertexIndex).isEmpty();
    }
}
