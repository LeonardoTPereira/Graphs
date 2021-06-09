import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class Digraph_Matrix extends AbstractGraph
{

    Edge[][] adjacencyMatrix;

    public Digraph_Matrix(List<Vertex> vertices)
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

    }

    @Override
    public void removeVertex(Vertex vertex)
    {

    }

    @Override
    public void addEdge(int source, int destination)
    {
        if(!edgeExists(source, destination))
        {
            adjacencyMatrix[source][destination] = new Edge(1);
        }
    }

    @Override
    public void removeEdge(int source, int destination)
    {
        if(edgeExists(source, destination))
            adjacencyMatrix[source][destination] = null;
    }

    @Override
    public boolean edgeExists(int source, int destination)
    {
        return adjacencyMatrix[source][destination] != null;
    }

    @Override
    public boolean hasAnyEdge(int vertex)
    {
        for (int i = 0; i < numberOfVertices; i++)
        {
            if(adjacencyMatrix[vertex][i] != null)
                return true;
        }
        return false;
    }

    @Override
    public int getFirstConnectedVertexIndex(int vertex)
    {
        return getNextConnectedVertexIndex(vertex, 0);
    }

    @Override
    public int getNextConnectedVertexIndex(int vertex, int currentEdge)
    {
        for (int i = currentEdge; i < numberOfVertices; i++)
        {
            if(edgeExists(vertex, i))
                return i;
        }
        return -1;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numberOfVertices; i++) {
            s.append(i).append(": ");
            for (int j = 0; j < numberOfVertices; ++j)
            {
                if(edgeExists(i, j))
                    s.append(adjacencyMatrix[i][j].value).append(" ");
                else
                    s.append(0 + " ");
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
                if(edgeExists(i, j))
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
        AbstractGraph g = new Digraph_Matrix(new ArrayList<>()
        {{
            add(new Vertex("Joao"));
            add(new Vertex("Maria"));
            add(new Vertex("José"));
            add(new Vertex("Marcos"));
            add(new Vertex("Pedro"));
        }});

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        System.out.print(g);
        g.printInGraphViz("Digraph");
    }
}
