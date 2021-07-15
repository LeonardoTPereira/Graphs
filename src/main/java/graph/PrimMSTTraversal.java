package graph;

import javax.annotation.Nullable;

public class PrimMSTTraversal extends TraversalStrategyInterface
{

    protected PrimMSTTraversal(AbstractGraph graph)
    {
        super(graph);
    }

    @Override
    public void traverseGraph(Vertex source, @Nullable Vertex destination)
    {
        int sourceIndex = getGraph().getIndexOfVertex(source);
        setDistanceToVertex(sourceIndex, 0);
        setPredecessorVertexIndex(sourceIndex, -1);
        visitAllVertices();
        printVisitTree();
    }

    private void visitAllVertices()
    {
        for (int i = 0; i < (getGraph().getNumberOfVertices()-1); i++)
        {
            int closestVertexIndex = getClosestVertexIndex();
            if(closestVertexIndex != -1)
            {
                markVertexAsVisited(closestVertexIndex);
                Vertex closestVertex = getGraph().getVertices().get(closestVertexIndex);
                Vertex adjacentVertex = getGraph().getFirstConnectedVertex(closestVertex);
                updateAdjacentVertices(closestVertexIndex, closestVertex, adjacentVertex);
            }
        }
    }

    private void updateAdjacentVertices(int closestVertexIndex, Vertex closestVertex, Vertex adjacentVertex)
    {
        while(adjacentVertex != null)
        {
            int adjacentVertexIndex = getGraph().getVertices().indexOf(adjacentVertex);
            if (isCloserAndNotVisited(closestVertexIndex, adjacentVertexIndex))
            {
                setDistanceToVertex(adjacentVertexIndex, getGraph().getDistance(closestVertex, adjacentVertex));
                setPredecessorVertexIndex(adjacentVertexIndex, closestVertexIndex);
            }
            adjacentVertex = getGraph().getNextConnectedVertex(closestVertex, adjacentVertex);
        }
    }

    private boolean isCloserAndNotVisited(int closestVertexIndex, int adjacentVertexIndex)
    {
        Vertex adjacentVertex = getGraph().getVertices().get(adjacentVertexIndex);
        Vertex closestVertex = getGraph().getVertices().get(closestVertexIndex);
        return (!hasVertexBeenVisited(adjacentVertexIndex)
                && (getGraph().getDistance(closestVertex, adjacentVertex) < getDistanceToVertex(adjacentVertexIndex)));
    }

    private int getClosestVertexIndex()
    {
        float minDistance = Float.POSITIVE_INFINITY;
        int minIndex = -1;

        for (int i = 0; i < getGraph().getNumberOfVertices(); i++)
        {
            if(!hasVertexBeenVisited(i) && (getDistanceToVertex(i) < minDistance))
            {
                minDistance = getDistanceToVertex(i);
                minIndex = i;
            }
        }
        return minIndex;
    }
}
