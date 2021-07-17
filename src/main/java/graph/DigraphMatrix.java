package graph;

import java.util.List;
import java.util.logging.Logger;

public class DigraphMatrix extends AbstractGraph
{
    private Edge[][] adjacencyMatrix;

    private static final Logger LOGGER = Logger.getLogger("DigraphMatrix.class");

    public DigraphMatrix(List<Vertex> vertices)
    {
        super(vertices);
        initializeAdjacencyMatrix();
    }

    public DigraphMatrix()
    {
        super();
    }

    @Override
    public void addVertex(Vertex vertex)
    {
        super.addVertex(vertex);
        expandAdjacencyMatrix();
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

    private void expandAdjacencyMatrix()
    {
        Edge[][] newAdjacencyMatrix = new Edge[getNumberOfVertices()][getNumberOfVertices()];
        System.arraycopy(adjacencyMatrix, 0, newAdjacencyMatrix, 0, getNumberOfVertices()-1);
        for (int i = 0; i < (getNumberOfVertices()-1); i++)
        {
            System.arraycopy(adjacencyMatrix[i], 0, newAdjacencyMatrix[i], 0, getNumberOfVertices()-1);
            newAdjacencyMatrix[i][getNumberOfVertices()-1] = null;
            newAdjacencyMatrix[getNumberOfVertices()-1][i] = null;
        }
        newAdjacencyMatrix[getNumberOfVertices()-1][getNumberOfVertices()-1] = null;
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

    @Override
    protected DigraphMatrix clone() throws CloneNotSupportedException
    {
        DigraphMatrix cloneGraph = (DigraphMatrix) super.clone();
        cloneGraph.cloneAdjacencyMatrix(this);
        return cloneGraph;
    }

    private void cloneAdjacencyMatrix(DigraphMatrix cloneTarget)
    {
        for(int i = 0; i < cloneTarget.getAdjacencyMatrix().length; i++)
        {
            for (int j = 0; j < cloneTarget.getAdjacencyMatrix().length; j++)
            {
                if(cloneTarget.getAdjacencyMatrix()[i][j] != null)
                {
                    addEdge(getVertices().get(i), getVertices().get(j), cloneTarget.getAdjacencyMatrix()[i][j].getWeight());
                }
            }
        }
    }

    public void removeAllEdges()
    {
        for(int i = 0; i < getAdjacencyMatrix().length; i++)
        {
            for (int j = 0; j < getAdjacencyMatrix().length; j++)
            {
                getAdjacencyMatrix()[i][j] = null;
            }
        }
    }

    @Override
    public void lockEdge(Vertex source, Vertex destination, int lockID)
    {
        Edge edge = getEdge(source, destination);
        edge.setLockID(lockID);
    }

    @Override
    public Edge getEdge(Vertex source, Vertex destination)
    {
        int sourceIndex = getIndexOfVertex(source);
        int destinationIndex = getIndexOfVertex(destination);
        return getAdjacencyMatrix()[sourceIndex][destinationIndex];
    }
}
