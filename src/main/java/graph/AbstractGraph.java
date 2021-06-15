package graph;

import java.util.List;

public abstract class AbstractGraph implements GraphInterface
{
    protected static final int GRAPHVIZ_IMAGE_WIDTH = 400;
    protected static final String GRAPHVIZ_FOLDER = "example/";
    protected static final String GRAPHVIZ_FILE_EXTENSION = "example/";
    protected int numberOfVertices;
    protected List<Vertex> vertices;

    protected AbstractGraph(List<Vertex> vertices)
    {
        numberOfVertices = vertices.size();
        this.vertices = vertices;
    }
}
