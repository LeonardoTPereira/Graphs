package graph;

import java.util.List;

public class DigraphMatrix extends AbstractGraph
{
    private Edge[][] adjacencyMatrix;

    public DigraphMatrix(List<Vertex> vertices)
    {
        super(vertices);
        initializeAdjacencyMatrix();
    }

    private void initializeAdjacencyMatrix()
    {
        setAdjacencyMatrix(new Edge[getNumberOfVertices()][getNumberOfVertices()]);
        for (int i = 0; i < getNumberOfVertices(); i++)
        {
            for (int j = 0; j < getNumberOfVertices(); j++)
            {
                getAdjacencyMatrix()[i][j] = null;
            }
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
            getAdjacencyMatrix()[getVertices().indexOf(source)][getVertices().indexOf(destination)] = new Edge(weight);
        }
    }

    @Override
    public void removeEdge(Vertex source, Vertex destination)
    {
        int sourceIndex = getVertices().indexOf(source);
        int destinationIndex = getVertices().indexOf(destination);
        if(edgeExists(source, destination))
        {
            getAdjacencyMatrix()[sourceIndex][destinationIndex] = null;
        }
    }

    @Override
    public boolean edgeExists(Vertex source, Vertex destination)
    {
        int sourceIndex = getVertices().indexOf(source);
        int destinationIndex = getVertices().indexOf(destination);
        return getAdjacencyMatrix()[sourceIndex][destinationIndex] != null;
    }

    @Override
    public boolean hasAnyEdge(Vertex vertex)
    {
        int vertexIndex = getVertices().indexOf(vertex);
        for (int i = 0; i < getNumberOfVertices(); i++)
        {
            if(getAdjacencyMatrix()[vertexIndex][i] != null)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Vertex getFirstConnectedVertex(Vertex vertex)
    {
        if(!hasAnyEdge(vertex))
        {
            return null;
        }
        else
        {
            int currentVertexIndex = 0;
            Vertex connected;
            do
            {
                connected = getVertices().get(currentVertexIndex++);
            }while(!edgeExists(vertex, connected));
            return connected;
        }
    }

    @Override
    public Vertex getNextConnectedVertex(Vertex source, Vertex currentConnection)
    {
        Vertex newConnection;
        for (int i = getVertices().indexOf(currentConnection)+1; i < getNumberOfVertices(); i++)
        {
            newConnection = getVertices().get(i);
            if(edgeExists(source, newConnection))
            {
                return newConnection;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getNumberOfVertices(); i++)
        {
            s.append(i).append(": ");
            for (int j = 0; j < getNumberOfVertices(); ++j)
            {
                if(edgeExists(getVertices().get(i), getVertices().get(j)))
                {
                    s.append(getAdjacencyMatrix()[i][j].getWeight()).append(" ");
                }
                else
                {
                    s.append(0.0 + " ");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public float getDistance(Vertex source, Vertex destination)
    {
        Edge edge = adjacencyMatrix[getVertices().indexOf(source)][getVertices().indexOf(destination)];
        if(edge != null)
        {
            return edge.getWeight();
        }
        else
        {
            return Float.POSITIVE_INFINITY;
        }
    }

    public Edge[][] getAdjacencyMatrix()
    {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(Edge[][] adjacencyMatrix)
    {
        this.adjacencyMatrix = adjacencyMatrix;
    }
}
