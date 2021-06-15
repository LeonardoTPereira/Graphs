package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public final class BreadthFirstTraversal implements TraversalStrategyInterface
{
    @Override
    public String traverseGraph(AbstractGraph g, Vertex source)
    {
        var visited = new float[g.numberOfVertices];
        Arrays.fill(visited, -1);
        visited[g.vertices.indexOf(source)] = 0;
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
                        append("Distance: ").append(visited[g.vertices.indexOf(currentVisitedVertex)]).append(' ');
                int adjacentVertexIndex = g.getFirstConnectedVertexIndex(currentVisitedVertex);
                while(adjacentVertexIndex != -1)
                {
                    if(visited[adjacentVertexIndex] < 0)
                    {
                        visited[adjacentVertexIndex] = visited[g.vertices.indexOf(currentVisitedVertex)] + g.getDistance(currentVisitedVertex, g.vertices.get(adjacentVertexIndex));
                        vertexesToVisit.add(g.vertices.get(adjacentVertexIndex));
                    }
                    adjacentVertexIndex = g.getNextConnectedVertexIndex(currentVisitedVertex, adjacentVertexIndex);
                }
            }
        }
        return visitedPath.toString();
    }
}
