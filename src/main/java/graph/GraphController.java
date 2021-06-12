package graph;

import java.util.ArrayList;
import java.util.logging.Logger;

public class GraphController
{
    private static final Logger LOGGER = Logger.getLogger("GraphController.class");


    public static void main(String[] args)
    {
        ArrayList<Vertex> vertices = createVertexList();

        AbstractGraph g = new DigraphMatrix( vertices );
        createTest(g);
        printTest(g, "MatrixDigraph");

        g = new GraphMatrix(vertices);
        createTest(g);
        printTest(g, "MatrixGraph");

        g = new DigraphList(vertices);
        createTest(g);
        printTest(g, "ListDigraph");

        g = new GraphList(vertices);
        createTest(g);
        printTest(g, "ListGraph");

    }

    private static ArrayList<Vertex> createVertexList()
    {
        ArrayList<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex("Joao"));
        vertices.add(new Vertex("Maria"));
        vertices.add(new Vertex("José"));
        vertices.add(new Vertex("Marcos"));
        vertices.add(new Vertex("Pedro"));
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
    }
}
