package graph;

public interface GraphInterface
{
    void addVertex(Vertex vertex);

    void removeVertex(Vertex vertex);

    void addEdge(Vertex source, Vertex destination, float weight);

    void removeEdge(Vertex source, Vertex destination);

    boolean edgeExists(Vertex source, Vertex destination);

    boolean hasAnyEdge(Vertex vertex);

    Vertex getFirstConnectedVertex(Vertex vertex);

    Vertex getNextConnectedVertex(Vertex source, Vertex currentConnection);

    String toString();

    float getDistance(Vertex source, Vertex destination);

    void printInGraphViz(String fileName);

    void removeAllEdges();
}
