public interface GraphInterface
{
    void addVertex(Vertex vertex);
    void removeVertex(Vertex vertex);

    void addEdge(int source, int destination);
    //TODO Add generic edge
    //void addEdge(Vertex source, Vertex destination);
    void removeEdge(int source, int destination);

    boolean edgeExists(int source, int destination);

    boolean hasAnyEdge(int vertex);

    int getFirstConnectedVertexIndex(int vertex);

    int getNextConnectedVertexIndex(int vertex, int currentEdge);

    String toString();

    void printInGraphViz(String fileName);
}
