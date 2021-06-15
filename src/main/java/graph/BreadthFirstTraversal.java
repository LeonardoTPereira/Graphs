package graph;

import java.util.LinkedList;
import java.util.Queue;

public final class BreadthFirstTraversal implements TraversalStrategyInterface
{
    @Override
    public String traverseGraph(AbstractGraph g, Vertex source)
    {
        var visited = new boolean[g.numberOfVertices];
        visited[g.vertices.indexOf(source)] = true;
        Queue<Vertex> vertexesToVisit = new LinkedList<>();
        vertexesToVisit.add(source);
        var visitedPath = new StringBuilder();
        Vertex currentVisitedVertex;
        while(!vertexesToVisit.isEmpty())
        {
            currentVisitedVertex = vertexesToVisit.poll();
            if (currentVisitedVertex != null)
            {
                visitedPath.append(currentVisitedVertex).append(' ');
            }
            int adjacentVertexIndex = g.getFirstConnectedVertexIndex(currentVisitedVertex);
            while(adjacentVertexIndex != -1)
            {
                if(!visited[adjacentVertexIndex])
                {
                    visited[adjacentVertexIndex] = true;
                    vertexesToVisit.add(g.vertices.get(adjacentVertexIndex));
                }
                adjacentVertexIndex = g.getNextConnectedVertexIndex(currentVisitedVertex, adjacentVertexIndex);
            }
        }
        return visitedPath.toString();
    }
}
