package graph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstTraversal implements TraversalStrategyInterface
{
    @Override
    public String traverseGraph(AbstractGraph g, Vertex source)
    {
        var visited = new boolean[g.numberOfVertices];
        visited[g.vertices.indexOf(source)] = true;
        Queue<Vertex> vertexesToVisit = new LinkedList<>();
        vertexesToVisit.add(source);
        var visitedPath = new StringBuilder();

        while(!vertexesToVisit.isEmpty())
        {
            source = vertexesToVisit.poll();
            if (source != null)
            {
                visitedPath.append(source).append(' ');
            }
            int adjacentVertexIndex = g.getFirstConnectedVertexIndex(source);
            while(adjacentVertexIndex != -1)
            {
                if(!visited[adjacentVertexIndex])
                {
                    visited[adjacentVertexIndex] = true;
                    vertexesToVisit.add(g.vertices.get(adjacentVertexIndex));
                }
                adjacentVertexIndex = g.getNextConnectedVertexIndex(source, adjacentVertexIndex);
            }
        }
        return visitedPath.toString();
    }
}
