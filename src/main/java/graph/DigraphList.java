package graph;

import java.util.ArrayList;
import java.util.List;

public class DigraphList extends AbstractGraph
{

    private List<List<Edge>> adjacencyList;

    public DigraphList(List<Vertex> vertices)
    {
        super(vertices);
        initializeAdjacencyList();
    }

    private void initializeAdjacencyList()
    {
        setAdjacencyList(new ArrayList<>());
        for (int i = 0; i < getNumberOfVertices(); i++)
        {
            getAdjacencyList().add(new ArrayList<>());
        }
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
    public void addEdge(Vertex source, Vertex destination, float weight)
    {
        if(!edgeExists(source, destination))
        {
            int sourceIndex = getVertices().indexOf(source);
            getAdjacencyList().get(sourceIndex).add(new Edge(destination, weight));
        }
    }

    @Override
    public void removeEdge(Vertex source, Vertex destination)
    {
        int sourceIndex = getVertices().indexOf(source);
        List<Edge> sourceEdges = getAdjacencyList().get(sourceIndex);
        for (int i = sourceEdges.size()-1; i > -1; i--)
        {
            if(sourceEdges.get(i).getDestination() == destination)
            {
                sourceEdges.remove(i);
            }
        }
    }

    @Override
    public boolean edgeExists(Vertex source, Vertex destination)
    {

        int sourceIndex = getVertices().indexOf(source);
        List<Edge> sourceEdges = getAdjacencyList().get(sourceIndex);
        for (Edge sourceEdge : sourceEdges)
        {
            if (sourceEdge.getDestination() == destination)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAnyEdge(Vertex vertex)
    {
        int vertexIndex = getVertices().indexOf(vertex);

        if(!getAdjacencyList().get(vertexIndex).isEmpty())
        {
            return true;
        }

        for (int i = 0; i < getNumberOfVertices(); i++)
        {
            for (int j = 0; j < getAdjacencyList().get(i).size(); ++j)
            {
                if(getAdjacencyList().get(i).get(j).getDestination() == vertex)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Vertex getFirstConnectedVertex(Vertex vertex)
    {
        if(getAdjacencyList().get(getVertices().indexOf(vertex)).isEmpty())
        {
            return null;
        }
        else
        {
            return getAdjacencyList().get(getVertices().indexOf(vertex)).get(0).getDestination();
        }
    }

    @Override
    public Vertex getNextConnectedVertex(Vertex source, Vertex currentConnection)
    {
        int vertexIndex = getVertices().indexOf(source);
        int currentAdjacentVertexIndex = 0;
        while(getAdjacencyList().get(vertexIndex).get(currentAdjacentVertexIndex).getDestination() != currentConnection)
        {
            currentAdjacentVertexIndex++;
        }
        currentAdjacentVertexIndex++;
        if(getAdjacencyList().get(vertexIndex).size() > currentAdjacentVertexIndex)
        {
            return getAdjacencyList().get(vertexIndex).get(currentAdjacentVertexIndex).getDestination();
        }
        else
        {
            return null;
        }
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getNumberOfVertices(); i++)
        {
            s.append(i).append(": ");
            for (int j = 0; j < getAdjacencyList().get(i).size(); ++j)
            {
                s.append(getAdjacencyList().get(i).get(j).getWeight()).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public List<List<Edge>> getAdjacencyList()
    {
        return adjacencyList;
    }

    public void setAdjacencyList(List<List<Edge>> adjacencyList)
    {
        this.adjacencyList = adjacencyList;
    }

    @Override
    public float getDistance(Vertex source, Vertex destination)
    {
        int sourceIndex = getVertices().indexOf(source);
        for(int i = 0; i < adjacencyList.get(sourceIndex).size(); i++)
        {
            if(adjacencyList.get(sourceIndex).get(i).getDestination() == destination)
            {
                return adjacencyList.get(sourceIndex).get(i).getWeight();
            }
        }
        return Float.POSITIVE_INFINITY;
    }
}
