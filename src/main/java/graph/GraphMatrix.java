package graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class GraphMatrix extends DigraphMatrix
{

    private static final java.util.logging.Logger LOGGER = Logger.getLogger("DigraphMatrix.class");

    public GraphMatrix(List<Vertex> vertices)
    {
        super(vertices);
    }

    @Override
    public void addEdge(Vertex source, Vertex destination)
    {
        super.addEdge(source, destination);
        super.addEdge(destination, source);
    }

    @Override
    public void removeEdge(Vertex source, Vertex destination)
    {
        super.removeEdge(source, destination);
        super.removeEdge(destination, source);
    }

    public static void main(String[] args)
    {
        AbstractGraph g = new GraphMatrix(
                new ArrayList<>(
                        Arrays.asList(new Vertex("Joao"),
                                new Vertex("Maria"),
                                new Vertex("José"),
                                new Vertex("Marcos"),
                                new Vertex("Pedro")
                        )
                )
        );

        g.addEdge(g.vertices.get(0), g.vertices.get(1));
        g.addEdge(g.vertices.get(0), g.vertices.get(2));
        g.addEdge(g.vertices.get(1), g.vertices.get(2));
        g.addEdge(g.vertices.get(2), g.vertices.get(0));
        g.addEdge(g.vertices.get(2), g.vertices.get(3));

        var graphString = "\n"+ g +"\n";
        LOGGER.info(graphString);
        g.printInGraphViz("graph");
    }

}
