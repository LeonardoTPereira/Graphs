package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

public class PageRank
{
    private static final Logger LOGGER = Logger.getLogger("PageRank.class");

    public static void rankPagesForNGenerations(AbstractGraph graph, int nGenerations)
    {
        var indexToVisit = new ArrayList<Integer>();
        for (int j = 0; j < graph.getNumberOfVertices(); j++)
        {
            indexToVisit.add(j);
        }
        for (var i = 0; i < nGenerations; i++)
        {
            Collections.shuffle(indexToVisit);
            for (Integer integer : indexToVisit)
            {
                Vertex currentVisitedVertex = graph.getVertices().get(integer);
                distributeRankToConnectedVertices(graph, currentVisitedVertex);
            }
        }
    }

    private static void distributeRankToConnectedVertices(AbstractGraph graph, Vertex currentVisitedVertex)
    {
        float rankToTransfer = ((WebPage) currentVisitedVertex).getPageRankToTransfer();
        Vertex adjacentVertex = graph.getFirstConnectedVertex(currentVisitedVertex);
        while(adjacentVertex != null)
        {
            ((WebPage) adjacentVertex).setRank(rankToTransfer);
            adjacentVertex = graph.getNextConnectedVertex(currentVisitedVertex, adjacentVertex);
        }
    }

    public static void printPageRanks(AbstractGraph graph)
    {
        for (int i = 0; i < graph.getNumberOfVertices(); i++)
        {
            LOGGER.info(graph.getVertices().get(i).toString());
        }
    }
}
