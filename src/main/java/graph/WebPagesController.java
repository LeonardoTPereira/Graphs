package graph;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class WebPagesController
{
    private static final Logger LOGGER = Logger.getLogger("WebPagesController.class");

    private static final int GENERATIONS = 10;
    private static final int TOTAL_PAGES = 20;


    public static void main(String[] args)
    {
        AbstractGraph webPages = WebPagesController.createWebPages();
        PageRank.rankPagesForNGenerations(webPages, GENERATIONS);
        PageRank.printPageRanks(webPages);
    }

    private static AbstractGraph createWebPages()
    {
        AbstractGraph webPages = new DigraphList();
        for (int i = 0; i < TOTAL_PAGES; i++)
        {
            webPages.addVertex(new WebPage("Page_"+i, 1/(float)TOTAL_PAGES));
        }
        for (int i = 0; i < (TOTAL_PAGES*2); i++)
        {
            Vertex source = webPages.getVertices().get(RandomSingleton.getInstance().nextInt(TOTAL_PAGES));
            Vertex destination;
            do
            {
                destination = webPages.getVertices().get(RandomSingleton.getInstance().nextInt(TOTAL_PAGES));
            } while (source == destination);
            webPages.addEdge(source, destination, 1);
        }
        return webPages;
    }

}
