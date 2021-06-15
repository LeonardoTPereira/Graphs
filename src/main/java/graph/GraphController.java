package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class GraphController
{
    private static final Logger LOGGER = Logger.getLogger("GraphController.class");

    private GraphController()
    {

    }

    public static void main(String[] args)
    {
        var vertices = createVertexList();

        TraversalStrategyInterface traversalStrategy = new BreadthFirstTraversal();

        AbstractGraph g = new DigraphMatrix( vertices );
        createTest(g);
        printTest(g, "MatrixDigraph");
        var traversalPath = traversalStrategy.traverseGraph(g, g.vertices.get(0));
        traversalPath = "\n"+ traversalPath +"\n";
        LOGGER.info(traversalPath);

        g = new GraphMatrix(vertices);
        createTest(g);
        printTest(g, "MatrixGraph");
        traversalPath = traversalStrategy.traverseGraph(g, g.vertices.get(0));
        traversalPath = "\n"+ traversalPath +"\n";
        LOGGER.info(traversalPath);

        g = new DigraphList(vertices);
        createTest(g);
        printTest(g, "ListDigraph");
        traversalPath = traversalStrategy.traverseGraph(g, g.vertices.get(0));
        traversalPath = "\n"+ traversalPath +"\n";
        LOGGER.info(traversalPath);

        g = new GraphList(vertices);
        createTest(g);
        printTest(g, "ListGraph");
        traversalPath = traversalStrategy.traverseGraph(g, g.vertices.get(0));
        traversalPath = "\n"+ traversalPath +"\n";
        LOGGER.info(traversalPath);

    }

    private static List<Vertex> createVertexList()
    {
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex("Joao"));
        vertices.add(new Vertex("Maria"));
        vertices.add(new Vertex("José"));
        vertices.add(new Vertex("Marcos"));
        vertices.add(new Vertex("Pedro"));
        vertices.add(new Vertex("Amanda"));
        vertices.add(new Vertex("Leonardo"));
        return vertices;
    }

    private static void printTest(AbstractGraph g, String fileName)
    {
        var graphString = "\n"+ g +"\n";
        LOGGER.info(graphString);
        g.printInGraphViz(fileName);
    }

    private static void createTest(AbstractGraph g)
    {
        g.addEdge(g.vertices.get(0), g.vertices.get(1));
        g.addEdge(g.vertices.get(0), g.vertices.get(2));
        g.addEdge(g.vertices.get(1), g.vertices.get(2));
        g.addEdge(g.vertices.get(2), g.vertices.get(0));
        g.addEdge(g.vertices.get(2), g.vertices.get(3));
        g.addEdge(g.vertices.get(3), g.vertices.get(4));
        g.addEdge(g.vertices.get(4), g.vertices.get(5));
        g.addEdge(g.vertices.get(5), g.vertices.get(6));
        g.addEdge(g.vertices.get(5), g.vertices.get(0));
    }
}
