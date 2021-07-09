package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractGraph implements GraphInterface, Cloneable
{
    protected static final int GRAPHVIZ_IMAGE_WIDTH = 2000;
    protected static final String GRAPHVIZ_FOLDER = "example/";
    protected static final String GRAPHVIZ_FILE_EXTENSION = ".png";
    private int numberOfVertices;
    private List<Vertex> vertices;

    protected AbstractGraph(List<Vertex> vertices)
    {
        numberOfVertices = vertices.size();
        setVertices(vertices);
    }

    protected AbstractGraph()
    {
        vertices = new ArrayList<>();
        numberOfVertices = 0;
    }

    @Override
    public void addVertex(Vertex vertex)
    {
        vertices.add(vertex);
        numberOfVertices = vertices.size();
    }

    public int getNumberOfVertices()
    {
        return numberOfVertices;
    }

    public void setNumberOfVertices(int numberOfVertices)
    {
        this.numberOfVertices = numberOfVertices;
    }

    public List<Vertex> getVertices()
    {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices)
    {
        this.vertices = vertices;
    }

    public void addEdge(Vertex source, Vertex destination)
    {
        addEdge(source, destination, 1);
    }

    public Vertex getCentermostVertex(float[][] distanceMatrix)
    {
        var maxDistanceInCollumn = new float[distanceMatrix.length];
        Arrays.fill(maxDistanceInCollumn, Float.NEGATIVE_INFINITY);
        for (var i = 0; i < distanceMatrix.length; i++)
        {
            for (var j = 0; j < distanceMatrix[0].length; j++)
            {
                if (maxDistanceInCollumn[i] < distanceMatrix[i][j])
                {
                    maxDistanceInCollumn[i] = distanceMatrix[i][j];
                }
            }
        }
        int vertexIndex = getMinDistanceIndexInCollumn(maxDistanceInCollumn);
        return vertices.get(vertexIndex);
    }

    private int getMinDistanceIndexInCollumn(float[] distanceArray)
    {
        var minIndex = 0;
        float minDistance = distanceArray[0];
        for (var i = 1; i < distanceArray.length; i++)
        {
            if(minDistance > distanceArray[i])
            {
                minDistance = distanceArray[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public int getIndexOfVertex(Vertex vertex)
    {
        return getVertices().indexOf(vertex);
    }

    @Override
    protected AbstractGraph clone() throws CloneNotSupportedException
    {
        AbstractGraph graphClone = (AbstractGraph)super.clone();
        graphClone.setNumberOfVertices(this.getNumberOfVertices());
        graphClone.setVertices(new ArrayList<>(this.getVertices()));
        return graphClone;
    }
}
