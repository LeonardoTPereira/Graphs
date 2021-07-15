package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.awt.geom.Point2D.distance;

public final class AStartPathFind extends TraversalStrategyInterface
{

    private List<Vertex> verticesToVisit;
    private float []heuristicDistanceToVertex;
    public AStartPathFind(AbstractGraph graph)
    {
        super(graph);
        verticesToVisit = new LinkedList<>();
        heuristicDistanceToVertex = new float[getGraph().getNumberOfVertices()];
        Arrays.fill(heuristicDistanceToVertex, Float.POSITIVE_INFINITY);
    }

    @Override
    public void traverseGraph(Vertex source, Vertex destination)
    {
        int sourceIndex = getGraph().getVertices().indexOf(source);
        heuristicDistanceToVertex[sourceIndex] = getHeuristicDistance((Room)source, (Room)destination);
        setPredecessorVertexIndex(sourceIndex, -1);
        setDistanceToVertex(sourceIndex, 0);
        List<Vertex> closedNodes = new LinkedList<>();
        verticesToVisit.add(source);
        Vertex currentVisitedVertex = source;
        int currentVisitedVertexIndex = getGraph().getVertices().indexOf(currentVisitedVertex);
        while(!verticesToVisit.isEmpty())
        {
            currentVisitedVertex = peekVertexWithLowestDistance();
            currentVisitedVertexIndex = getGraph().getVertices().indexOf(currentVisitedVertex);
            if (currentVisitedVertex == destination)
            {
                verticesToVisit.clear();
            }
            else
            {
                Vertex adjacentVertex = getGraph().getFirstConnectedVertex(currentVisitedVertex);
                while (adjacentVertex != null)
                {
                    int adjacentVertexIndex = getGraph().getVertices().indexOf(adjacentVertex);
                    float currentDistance = getDistanceToVertex(currentVisitedVertexIndex) + getTrueDistance((Room)currentVisitedVertex, (Room)adjacentVertex);
                    if (!closedNodes.contains(adjacentVertex) && !verticesToVisit.contains(adjacentVertex))
                    {
                        setPredecessorVertexIndex(adjacentVertexIndex, currentVisitedVertexIndex);
                        setDistanceToVertex(adjacentVertexIndex, currentDistance);
                        heuristicDistanceToVertex[adjacentVertexIndex] = currentDistance +
                                getHeuristicDistance((Room) adjacentVertex, (Room) destination);
                        verticesToVisit.add(adjacentVertex);
                    }
                    else
                    {
                        if(currentDistance < getDistanceToVertex(adjacentVertexIndex))
                        {
                            setPredecessorVertexIndex(adjacentVertexIndex, currentVisitedVertexIndex);
                            setDistanceToVertex(adjacentVertexIndex, currentDistance);
                            heuristicDistanceToVertex[adjacentVertexIndex] = getDistanceToVertex(adjacentVertexIndex)
                                    + getHeuristicDistance((Room) adjacentVertex, (Room) destination);
                            if(closedNodes.contains(adjacentVertex))
                            {
                                closedNodes.remove(adjacentVertex);
                                verticesToVisit.add(adjacentVertex);
                            }
                        }
                    }
                    adjacentVertex = getGraph().getNextConnectedVertex(currentVisitedVertex, adjacentVertex);
                }
            }
            //Guardar o anterior
            //Ver distancia real do novo
            //Atualizar dados
            closedNodes.add(currentVisitedVertex);
            verticesToVisit.remove(currentVisitedVertex);
        }
        printDistances();
        printShortestPath(source, destination);
    }

    private Vertex peekVertexWithLowestDistance()
    {
        int listIndex = 0;
        Vertex closestVertex = verticesToVisit.get(listIndex++);
        int closestVertexIndex = getGraph().getIndexOfVertex(closestVertex);
        float lowestDistance = heuristicDistanceToVertex[closestVertexIndex];
        Vertex currentVertex;
        while(listIndex < verticesToVisit.size())
        {
            currentVertex = verticesToVisit.get(listIndex++);
            int currentVertexIndex = getGraph().getIndexOfVertex(currentVertex);
            float newDistace = heuristicDistanceToVertex[currentVertexIndex];
            if(newDistace < lowestDistance)
            {
                lowestDistance = newDistace;
                closestVertex = currentVertex;
            }
        }
        return closestVertex;
    }

    private float getHeuristicDistance(Room source, Room destination)
    {
        return (float) distance(source.getPoint().getX(), source.getPoint().getY(),
                destination.getPoint().getX(), destination.getPoint().getY());
    }

    private float getTrueDistance(Room source, Room destination)
    {
        float distance = (float) Math.abs(source.getPoint().getX() - destination.getPoint().getX());
        distance += Math.abs(source.getPoint().getY() - destination.getPoint().getY());
        return distance;
    }
}
