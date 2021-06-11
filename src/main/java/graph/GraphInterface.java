package graph;

public interface GraphInterface
{
    void addVertex(Vertex vertex);
    void removeVertex(Vertex vertex);

    void addEdge(Vertex source, Vertex destination);
    void removeEdge(Vertex source, Vertex destination);

    boolean edgeExists(Vertex source, Vertex destination);

    boolean hasAnyEdge(Vertex vertex);

    int getFirstConnectedVertexIndex(Vertex vertex);

    int getNextConnectedVertexIndex(Vertex vertex, int currentEdge);

    String toString();

    void printInGraphViz(String fileName);
}
