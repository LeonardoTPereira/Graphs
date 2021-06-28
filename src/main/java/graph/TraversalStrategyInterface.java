package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public abstract class TraversalStrategyInterface
{
    private static final Logger LOGGER = Logger.getLogger("TravestalStrategyInterface.class");
    private AbstractGraph graph;
    private boolean[] visitedVertices;
    private float[] distanceToVertices;
    private int[] predecessorVertexIndices;
    private int[] successorVertexIndices;
    private List<Vertex> traversalPath;

    public void markVertexAsVisited(int vertexIndex)
    {
        visitedVertices[vertexIndex] = true;
    }

    public boolean hasVertexBeenVisited(int vertexIndex)
    {
        return visitedVertices[vertexIndex];
    }

    public void setDistanceToVertex(int vertexIndex, float distance)
    {
        distanceToVertices[vertexIndex] = distance;
    }

    public float getDistanceToVertex(int vertexIndex)
    {
        return distanceToVertices[vertexIndex];
    }

    public void setPredecessorVertexIndex(int currentVertexIndex, int predecessorIndex)
    {
        predecessorVertexIndices[currentVertexIndex] = predecessorIndex;
    }

    public int getPredecessorVertexIndex(int vertexIndex)
    {
        return predecessorVertexIndices[vertexIndex];
    }

    public void setSuccessorVertexIndex(int currentVertexIndex, int successorIndex)
    {
        successorVertexIndices[currentVertexIndex] = successorIndex;
    }

    public int getSuccessorVertexIndex(int vertexIndex)
    {
        return successorVertexIndices[vertexIndex];
    }

    protected TraversalStrategyInterface(AbstractGraph graph)
    {
        this.graph = graph;
        visitedVertices = new boolean[graph.getNumberOfVertices()];
        Arrays.fill(visitedVertices, false);
        distanceToVertices = new float[graph.getNumberOfVertices()];
        Arrays.fill(distanceToVertices, Float.POSITIVE_INFINITY);
        predecessorVertexIndices = new int[graph.getNumberOfVertices()];
        Arrays.fill(predecessorVertexIndices, -1);
        successorVertexIndices = new int[graph.getNumberOfVertices()];
        Arrays.fill(successorVertexIndices, -1);
        traversalPath = new LinkedList<>();
    }

    abstract void traverseGraph(Vertex source);

    public AbstractGraph getGraph()
    {
        return graph;
    }

    public void setGraph(AbstractGraph graph)
    {
        this.graph = graph;
    }

    protected void printPath()
    {
        var visitedPath = new StringBuilder();
        for (Vertex vertex : traversalPath)
        {
            visitedPath.append(vertex).append(' ').
                    append("Distance: ").append(getDistanceToVertex(getGraph().getVertices().indexOf(vertex))).append(' ');
        }
        var traversalPathString = "\n"+ visitedPath +"\n";
        LOGGER.info(traversalPathString);
    }

    protected void printShortestPath(Vertex source, Vertex destination)
    {
        int sourceIndex = graph.getVertices().indexOf(source);
        int destinationIndex = graph.getVertices().indexOf(destination);
        var shortestPath = new StringBuilder();
        int currentIndex = destinationIndex;
        do
        {
            shortestPath.append(graph.getVertices().get(currentIndex)).append('-');
            currentIndex = getPredecessorVertexIndex(currentIndex);
        }while(currentIndex != sourceIndex);
        shortestPath.append(graph.getVertices().get(currentIndex));
        var shortestPathString = "\n"+ shortestPath +"\n";
        LOGGER.info(shortestPathString);
    }

    protected void printDistances()
    {
        var distanceString = new StringBuilder();
        for (var i = 0; i < distanceToVertices.length; i++)
        {
            distanceString.append(i).append(": ").append(getGraph().getVertices().get(i)).append(" - ").append(getDistanceToVertex(i)).append("\n");
        }
        var finalString = distanceString.toString();
        LOGGER.info(finalString);
    }

    public void addToPath(Vertex vertex)
    {
        traversalPath.add(vertex);
    }
}
