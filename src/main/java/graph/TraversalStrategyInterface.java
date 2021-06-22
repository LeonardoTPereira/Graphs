package graph;

import java.util.Arrays;
import java.util.logging.Logger;

public abstract class TraversalStrategyInterface
{
    private static final Logger LOGGER = Logger.getLogger("TravestalStrategyInterface.class");
    private AbstractGraph graph;
    private boolean[] visitedVertices;
    private float[] distanceToVertices;
    private int[] predecessorVertexIndices;
    private int[] successorVertexIndices;

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
        Arrays.fill(distanceToVertices, Float.NEGATIVE_INFINITY);
        predecessorVertexIndices = new int[graph.getNumberOfVertices()];
        Arrays.fill(predecessorVertexIndices, -1);
        successorVertexIndices = new int[graph.getNumberOfVertices()];
        Arrays.fill(successorVertexIndices, -1);
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

    protected void printPath(int sourceIndex)
    {
        var visitedPath = new StringBuilder();
        int currentIndex = sourceIndex;
        do
        {
            visitedPath.append(getGraph().getVertices().get(currentIndex)).append(' ').
                    append("Distance: ").append(getDistanceToVertex(currentIndex)).append(' ');
            currentIndex = getSuccessorVertexIndex(currentIndex);
        }while(currentIndex > 0);
        var traversalPath = "\n"+ visitedPath +"\n";
        LOGGER.info(traversalPath);
    }
}
