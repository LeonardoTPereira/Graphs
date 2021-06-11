package graph;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Logger;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class DigraphMatrix extends AbstractGraph
{
    private static final Logger LOGGER = Logger.getLogger("DigraphMatrix.class");

    Edge[][] adjacencyMatrix;

    public DigraphMatrix(List<Vertex> vertices)
    {
        super(vertices);
        initializeAdjacencyMatrix();
    }

    private void initializeAdjacencyMatrix()
    {
        adjacencyMatrix = new Edge[numberOfVertices][numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++)
        {
            for (int j = 0; j < numberOfVertices; j++)
            {
                adjacencyMatrix[i][j] = null;
            }
        }
    }

    @Override
    public void addVertex(Vertex vertex)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeVertex(Vertex vertex)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addEdge(Vertex source, Vertex destination)
    {
        if(!edgeExists(source, destination))
        {
            adjacencyMatrix[vertices.indexOf(source)][vertices.indexOf(destination)] = new Edge(1);
        }
    }

    @Override
    public void removeEdge(Vertex source, Vertex destination)
    {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);
        if(edgeExists(source, destination))
            adjacencyMatrix[sourceIndex][destinationIndex] = null;
    }

    @Override
    public boolean edgeExists(Vertex source, Vertex destination)
    {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);
        return adjacencyMatrix[sourceIndex][destinationIndex] != null;
    }

    @Override
    public boolean hasAnyEdge(Vertex vertex)
    {
        int vertexIndex = vertices.indexOf(vertex);
        for (int i = 0; i < numberOfVertices; i++)
        {
            if(adjacencyMatrix[vertexIndex][i] != null)
                return true;
        }
        return false;
    }

    @Override
    public int getFirstConnectedVertexIndex(Vertex vertex)
    {
        return getNextConnectedVertexIndex(vertex, 0);
    }

    @Override
    public int getNextConnectedVertexIndex(Vertex vertex, int currentEdge)
    {
        for (int i = currentEdge; i < numberOfVertices; i++)
        {
            if(edgeExists(vertex, vertices.get(i)))
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numberOfVertices; i++) {
            s.append(i).append(": ");
            for (int j = 0; j < numberOfVertices; ++j)
            {
                if(edgeExists(vertices.get(i), vertices.get(j)))
                    s.append(adjacencyMatrix[i][j].weight).append(" ");
                else
                    s.append(0.0 + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public void printInGraphViz(String fileName)
    {
        MutableGraph g = mutGraph("example1Digraph").setDirected(true);

        for (int i = 0; i < numberOfVertices; ++i)
        {
            for (int j = 0; j < numberOfVertices; ++j)
            {
                if(edgeExists(vertices.get(i), vertices.get(j)))
                    g.add(mutNode(vertices.get(i).name).addLink(vertices.get(j).name));
            }
        }
        try
        {
            Graphviz.fromGraph(g).width(200).render(Format.PNG).toFile(new File("example/"+fileName+".png"));
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {

        AbstractGraph g = new DigraphMatrix(
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
        g.printInGraphViz("Digraph");
    }
}
