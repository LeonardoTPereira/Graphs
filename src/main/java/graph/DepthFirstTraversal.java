package graph;

import javax.annotation.Nullable;

public class DepthFirstTraversal extends TraversalStrategyInterface
{
    StringBuilder traversedPath;

    public DepthFirstTraversal(AbstractGraph g)
    {
        super(g);
        traversedPath = new StringBuilder();
    }

    @Override
    public void traverseGraph(Vertex source, @Nullable Vertex destination)
    {
        depthFirstTraversalRecursion(source);
        printPath();
    }

    private void depthFirstTraversalRecursion(Vertex source)
    {
        markVertexAsVisited(getGraph().getVertices().indexOf(source));
        traversedPath.append(source).append('\n');
        addToPath(source);
        Vertex adjacentVertex = getGraph().getFirstConnectedVertex(source);
        while(adjacentVertex != null)
        {
            int adjacentVertexIndex = getGraph().getVertices().indexOf(adjacentVertex);
            if(!hasVertexBeenVisited(adjacentVertexIndex))
            {
                depthFirstTraversalRecursion(adjacentVertex);
            }
            adjacentVertex = getGraph().getNextConnectedVertex(source, adjacentVertex);
        }
    }
}
