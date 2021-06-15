package graph;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DigraphMatrix extends AbstractGraph
{
    protected Edge[][] adjacencyMatrix;

    public DigraphMatrix(List<Vertex> vertices)
    {
        super(vertices);
        initializeAdjacencyMatrix();
    }

    private void initializeAdjacencyMatrix()
    {
        adjacencyMatrix = new Edge[numberOfVertices][numberOfVertices];
        for (var i = 0; i < numberOfVertices; i++)
        {
            for (var j = 0; j < numberOfVertices; j++)
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
        for (var i = 0; i < numberOfVertices; i++)
        {
            if(adjacencyMatrix[vertexIndex][i] != null)
                return true;
        }
        return false;
    }

    @Override
    public int getFirstConnectedVertexIndex(Vertex vertex)
    {
        if(!hasAnyEdge(vertex))
        {
            return -1;
        }
        else
        {
            var currentVertexIndex = 0;
            while(!edgeExists(vertex, vertices.get(currentVertexIndex)))
            {
                currentVertexIndex++;
            }
            return currentVertexIndex;
        }
    }

    @Override
    public int getNextConnectedVertexIndex(Vertex vertex, int currentEdge)
    {
        for (int i = (currentEdge+1); i < numberOfVertices; i++)
        {
            if(edgeExists(vertex, vertices.get(i)))
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        var s = new StringBuilder();
        for (var i = 0; i < numberOfVertices; i++)
        {
            s.append(i).append(": ");
            for (var j = 0; j < numberOfVertices; ++j)
            {
                if(edgeExists(vertices.get(i), vertices.get(j)))
                    s.append(adjacencyMatrix[i][j].getWeight()).append(" ");
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

        for (var i = 0; i < numberOfVertices; ++i)
        {
            for (var j = 0; j < numberOfVertices; ++j)
            {
                if(edgeExists(vertices.get(i), vertices.get(j)))
                    g.add(mutNode(vertices.get(i).getName()).addLink(vertices.get(j).getName()));
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
}
