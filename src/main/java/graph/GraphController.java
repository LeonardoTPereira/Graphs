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


        AbstractGraph g = new DigraphMatrix( vertices );
        createTest(g);

        TraversalStrategyInterface traversalStrategy = new BreadthFirstTraversal(g);

        printTest(g, "MatrixDigraph");
        traversalStrategy.traverseGraph(g.getVertices().get(0));


        g = new GraphMatrix(vertices);
        createTest(g);
        printTest(g, "MatrixGraph");
        traversalStrategy.traverseGraph(g.getVertices().get(0));


        g = new DigraphList(vertices);
        createTest(g);
        printTest(g, "ListDigraph");
        traversalStrategy.traverseGraph(g.getVertices().get(0));

        g = new GraphList(vertices);
        createTest(g);
        printTest(g, "ListGraph");
        traversalStrategy.traverseGraph(g.getVertices().get(0));

        g = new DigraphMap(vertices);
        createTest(g);
        printTest(g, "MapDigraph");
        traversalStrategy.traverseGraph(g.getVertices().get(0));

        g = new GraphMap(vertices);
        createTest(g);
        printTest(g, "MapGraph");
        traversalStrategy.traverseGraph(g.getVertices().get(0));

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
        g.addEdge(g.getVertices().get(0), g.getVertices().get(1));
        g.addEdge(g.getVertices().get(0), g.getVertices().get(2));
        g.addEdge(g.getVertices().get(1), g.getVertices().get(2));
        g.addEdge(g.getVertices().get(2), g.getVertices().get(0));
        g.addEdge(g.getVertices().get(2), g.getVertices().get(3));
        g.addEdge(g.getVertices().get(3), g.getVertices().get(4));
        g.addEdge(g.getVertices().get(4), g.getVertices().get(5));
        g.addEdge(g.getVertices().get(5), g.getVertices().get(6));
        g.addEdge(g.getVertices().get(5), g.getVertices().get(0));
    }
}
