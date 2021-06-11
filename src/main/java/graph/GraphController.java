package graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class GraphController
{
    private static final Logger LOGGER = Logger.getLogger("GraphController.class");


    public static void main(String[] args)
    {
        ArrayList<Vertex> vertices = new ArrayList<>(
                Arrays.asList(new Vertex("Joao"),
                        new Vertex("Maria"),
                        new Vertex("José"),
                        new Vertex("Marcos"),
                        new Vertex("Pedro")
                )
        );

        AbstractGraph g = new DigraphMatrix( vertices );

        g.addEdge(g.vertices.get(0), g.vertices.get(1));
        g.addEdge(g.vertices.get(0), g.vertices.get(2));
        g.addEdge(g.vertices.get(1), g.vertices.get(2));
        g.addEdge(g.vertices.get(2), g.vertices.get(0));
        g.addEdge(g.vertices.get(2), g.vertices.get(3));

        var graphString = "\n"+ g +"\n";
        LOGGER.info(graphString);
        g.printInGraphViz("Digraph");

        g = new GraphMatrix(vertices);

        g.addEdge(g.vertices.get(0), g.vertices.get(1));
        g.addEdge(g.vertices.get(0), g.vertices.get(2));
        g.addEdge(g.vertices.get(1), g.vertices.get(2));
        g.addEdge(g.vertices.get(2), g.vertices.get(0));
        g.addEdge(g.vertices.get(2), g.vertices.get(3));

        graphString = "\n"+ g +"\n";
        LOGGER.info(graphString);
        g.printInGraphViz("Graph");

        g = new DigraphList(vertices);

        g.addEdge(g.vertices.get(0), g.vertices.get(1));
        g.addEdge(g.vertices.get(0), g.vertices.get(2));
        g.addEdge(g.vertices.get(1), g.vertices.get(2));
        g.addEdge(g.vertices.get(2), g.vertices.get(0));
        g.addEdge(g.vertices.get(2), g.vertices.get(3));

        graphString = "\n"+ g +"\n";
        LOGGER.info(graphString);
        g.printInGraphViz("ListDigraph");

        g = new GraphList(vertices);

        g.addEdge(g.vertices.get(0), g.vertices.get(1));
        g.addEdge(g.vertices.get(0), g.vertices.get(2));
        g.addEdge(g.vertices.get(1), g.vertices.get(2));
        g.addEdge(g.vertices.get(2), g.vertices.get(0));
        g.addEdge(g.vertices.get(2), g.vertices.get(3));

        graphString = "\n"+ g +"\n";
        LOGGER.info(graphString);
        g.printInGraphViz("ListGraph");
    }
}
