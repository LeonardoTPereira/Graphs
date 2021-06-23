package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class DijkstraTraversal extends TraversalStrategyInterface
{

    private Queue<Vertex> verticesToVisit;
    public DijkstraTraversal(AbstractGraph graph)
    {
        super(graph);
        verticesToVisit = new LinkedList<>();
    }

    @Override
    public void traverseGraph(Vertex source)
    {
        int sourceIndex = getGraph().getVertices().indexOf(source);
        markVertexAsVisited(sourceIndex);
        setDistanceToVertex(sourceIndex, 0);
        setPredecessorVertexIndex(sourceIndex, -1);

        List<Vertex> visitedVertices = new LinkedList<>();
        visitedVertices.add(source);

        var currentVisitedVertex = source;
        int currentVisitedVertexIndex = getGraph().getVertices().indexOf(currentVisitedVertex);
        while(visitedVertices.size() != getGraph().getVertices().size())
        {
            if (currentVisitedVertex != null)
            {
                var adjacentVertex = getGraph().getFirstConnectedVertex(currentVisitedVertex);
                while (adjacentVertex != null)
                {
                    int adjacentVertexIndex = getGraph().getVertices().indexOf(adjacentVertex);
                    if (!visitedVertices.contains(adjacentVertex))
                    {
                        float newDistance = getGraph().getDistance(currentVisitedVertex, adjacentVertex) + getDistanceToVertex(currentVisitedVertexIndex);
                        if(newDistance < getDistanceToVertex(adjacentVertexIndex))
                        {
                            setDistanceToVertex(adjacentVertexIndex, newDistance);
                        }
                        verticesToVisit.add(adjacentVertex);
                    }
                    adjacentVertex = getGraph().getNextConnectedVertex(currentVisitedVertex, adjacentVertex);
                }
            }
            currentVisitedVertex = getLowestDistance();
            currentVisitedVertexIndex = getGraph().getVertices().indexOf(currentVisitedVertex);
            visitedVertices.add(currentVisitedVertex);
        }
        printDistances();
    }

    private Vertex getLowestDistance()
    {
        var closestVertex = verticesToVisit.poll();
        float lowestDistance = getDistanceToVertex(getGraph().getVertices().indexOf(closestVertex));
        Vertex currentVertex;
        do
        {
            currentVertex = verticesToVisit.poll();
            if(currentVertex != null)
            {
                float newDistace = getDistanceToVertex(getGraph().getVertices().indexOf(currentVertex));
                if(newDistace < lowestDistance)
                {
                    lowestDistance = newDistace;
                    closestVertex = currentVertex;
                }
            }
        } while(currentVertex != null);
        return closestVertex;
    }
}
