package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public final class BreadthFirstTraversal implements TraversalStrategyInterface
{
    @Override
    public String traverseGraph(AbstractGraph g, Vertex source)
    {
        var visited = new float[g.getNumberOfVertices()];
        Arrays.fill(visited, -1);
        visited[g.getVertices().indexOf(source)] = 0;
        Queue<Vertex> vertexesToVisit = new LinkedList<>();
        vertexesToVisit.add(source);
        var visitedPath = new StringBuilder();
        Vertex currentVisitedVertex;
        while(!vertexesToVisit.isEmpty())
        {
            currentVisitedVertex = vertexesToVisit.poll();
            if (currentVisitedVertex != null)
            {
                visitedPath.append(currentVisitedVertex).append(' ').
                        append("Distance: ").append(visited[g.getVertices().indexOf(currentVisitedVertex)]).append(' ');
                int adjacentVertexIndex = g.getFirstConnectedVertexIndex(currentVisitedVertex);
                while(adjacentVertexIndex != -1)
                {
                    if(visited[adjacentVertexIndex] < 0)
                    {
                        float previousWeight = visited[g.getVertices().indexOf(currentVisitedVertex)];
                        float newWeight = g.getDistance(currentVisitedVertex, g.getVertices().get(adjacentVertexIndex));
                        visited[adjacentVertexIndex] = previousWeight + newWeight;
                        vertexesToVisit.add(g.getVertices().get(adjacentVertexIndex));
                    }
                    adjacentVertexIndex = g.getNextConnectedVertexIndex(currentVisitedVertex, adjacentVertexIndex);
                }
            }
        }
        return visitedPath.toString();
    }
}
