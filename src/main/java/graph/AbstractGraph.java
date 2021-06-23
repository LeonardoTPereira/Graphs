package graph;

import java.util.List;

public abstract class AbstractGraph implements GraphInterface
{
    protected static final int GRAPHVIZ_IMAGE_WIDTH = 400;
    protected static final String GRAPHVIZ_FOLDER = "example/";
    protected static final String GRAPHVIZ_FILE_EXTENSION = ".png";
    private int numberOfVertices;
    private List<Vertex> vertices;

    protected AbstractGraph(List<Vertex> vertices)
    {
        numberOfVertices = vertices.size();
        setVertices(vertices);
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
}
