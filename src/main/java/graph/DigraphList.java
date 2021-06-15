package graph;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class DigraphList extends AbstractGraph
{
    protected List<List<Edge>> adjacencyList;

    public DigraphList(List<Vertex> vertices)
    {
        super(vertices);
        initializeAdjacencyList();
    }

    private void initializeAdjacencyList()
    {
        adjacencyList = new ArrayList<>();
        for (var i = 0; i < numberOfVertices; i++)
        {
            adjacencyList.add(new ArrayList<>());
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
            int sourceIndex = vertices.indexOf(source);
            adjacencyList.get(sourceIndex).add(new Edge(destination, 1));
        }
    }

    @Override
    public void removeEdge(Vertex source, Vertex destination)
    {
        int sourceIndex = vertices.indexOf(source);
        List<Edge> sourceEdges = adjacencyList.get(sourceIndex);
        for (int i = sourceEdges.size()-1; i > -1; i--)
        {
            if(sourceEdges.get(i).getDestination() == destination)
            {
                sourceEdges.remove(i);
            }
        }
    }

    @Override
    public boolean edgeExists(Vertex source, Vertex destination)
    {

        int sourceIndex = vertices.indexOf(source);
        List<Edge> sourceEdges = adjacencyList.get(sourceIndex);
        for (Edge sourceEdge : sourceEdges)
        {
            if (sourceEdge.getDestination() == destination)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAnyEdge(Vertex vertex)
    {
        int vertexIndex = vertices.indexOf(vertex);

        if(!adjacencyList.get(vertexIndex).isEmpty())
        {
            return true;
        }

        for (var i = 0; i < numberOfVertices; i++)
        {
            for (var j = 0; j < adjacencyList.get(i).size(); ++j)
            {
                if(adjacencyList.get(i).get(j).getDestination() == vertex)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getFirstConnectedVertexIndex(Vertex vertex)
    {
        if(adjacencyList.get(vertices.indexOf(vertex)).isEmpty())
            return -1;
        else
            return vertices.indexOf(adjacencyList.get(vertices.indexOf(vertex)).get(0).getDestination());
    }

    @Override
    public int getNextConnectedVertexIndex(Vertex vertex, int currentEdge)
    {
        int vertexIndex = vertices.indexOf(vertex);
        var currentAdjacentVertexIndex = 0;
        while(adjacencyList.get(vertexIndex).get(currentAdjacentVertexIndex).getDestination() != vertices.get(currentEdge))
        {
            currentAdjacentVertexIndex++;
        }
        currentAdjacentVertexIndex++;
        if(adjacencyList.get(vertexIndex).size() > currentAdjacentVertexIndex)
        {
            return vertices.indexOf(adjacencyList.get(vertexIndex).get(currentAdjacentVertexIndex).getDestination());
        }
        else
        {
            return -1;
        }
    }


    @Override
    public String toString() {
        var s = new StringBuilder();
        for (var i = 0; i < numberOfVertices; i++)
        {
            s.append(i).append(": ");
            for (var j = 0; j < adjacencyList.get(i).size(); ++j)
            {
                s.append(adjacencyList.get(i).get(j).getWeight()).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public void printInGraphViz(String fileName)
    {
        MutableGraph g = mutGraph("example1Digraph").setDirected(true);

        for (var i = 0; i < numberOfVertices; i++)
        {
            for (var j = 0; j < adjacencyList.get(i).size(); ++j)
            {
                int destinationIndex = vertices.indexOf(adjacencyList.get(i).get(j).getDestination());
                g.add(mutNode(vertices.get(i).getName()).addLink(vertices.get(destinationIndex).getName()));
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
