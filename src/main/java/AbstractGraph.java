import java.util.List;

public abstract class AbstractGraph implements GraphInterface
{
    int numberOfVertices;
    List<Vertex> vertices;

    public AbstractGraph(List<Vertex> vertices)
    {
        numberOfVertices = vertices.size();
        this.vertices = vertices;
    }


}
