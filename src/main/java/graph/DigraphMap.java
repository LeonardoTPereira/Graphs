package graph;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static guru.nidi.graphviz.model.Factory.*;

public class DigraphMap extends AbstractGraph
{
    private static final Logger LOGGER = Logger.getLogger("DigraphMap.class");

    private Map<Vertex, List<Edge>> adjacencyMap;

    public Map<Vertex, List<Edge>> getAdjacencyMap()
    {
        return adjacencyMap;
    }

    public void setAdjacencyMap(Map<Vertex, List<Edge>> adjacencyMap)
    {
        this.adjacencyMap = adjacencyMap;
    }

    public DigraphMap(List<Vertex> vertices)
    {
        super(vertices);
        initializeAdjacencyMap();
    }

    private void initializeAdjacencyMap()
    {
        adjacencyMap = new HashMap<>();
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
    public void addEdge(Vertex source, Vertex destination, float weight)
    {
        if(!edgeExists(source, destination))
        {
            if(!getAdjacencyMap().containsKey(source))
            {
                getAdjacencyMap().put(source, new ArrayList<>());
            }
            getAdjacencyMap().get(source).add(new Edge(destination, weight));
        }
    }

    @Override
    public void removeEdge(Vertex source, Vertex destination)
    {
        List<Edge> sourceEdges = getAdjacencyMap().get(source);
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
        if(getAdjacencyMap().containsKey(source))
        {
            List<Edge> sourceEdges = getAdjacencyMap().get(source);
            for (Edge sourceEdge : sourceEdges)
            {
                if (sourceEdge.getDestination() == destination)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasAnyEdge(Vertex vertex)
    {

        if(getAdjacencyMap().containsKey(vertex))
        {
            return true;
        }

        for (var i = 0; i < getNumberOfVertices(); i++)
        {
            for (var j = 0; j < getAdjacencyMap().get(getVertices().get(i)).size(); ++j)
            {
                if(getAdjacencyMap().get(getVertices().get(i)).get(j).getDestination() == vertex)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Vertex getFirstConnectedVertex(Vertex vertex)
    {
        if(!getAdjacencyMap().containsKey(vertex))
        {
            return null;
        }
        else
        {
            return getAdjacencyMap().get(vertex).get(0).getDestination();
        }
    }

    @Override
    public Vertex getNextConnectedVertex(Vertex source, Vertex currentConnection)
    {
        var currentAdjacentVertexIndex = 0;
        while(getAdjacencyMap().get(source).get(currentAdjacentVertexIndex).getDestination() != currentConnection)
        {
            currentAdjacentVertexIndex++;
        }
        currentAdjacentVertexIndex++;
        if(getAdjacencyMap().get(source).size() > currentAdjacentVertexIndex)
        {
            return getAdjacencyMap().get(source).get(currentAdjacentVertexIndex).getDestination();
        }
        else
        {
            return null;
        }
    }

    @Override
    public String toString() {
        var s = new StringBuilder();
        for(Map.Entry<Vertex, List<Edge>> pair : getAdjacencyMap().entrySet())
        {
            s.append(getVertices().indexOf(pair.getKey())).append(": ");
            for (var j = 0; j < pair.getValue().size(); ++j)
            {
                s.append(pair.getValue().get(j).getWeight()).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public void printInGraphViz(String fileName)
    {
        MutableGraph g = mutGraph("example1Digraph").setDirected(true);

        for(Map.Entry<Vertex, List<Edge>> pair : getAdjacencyMap().entrySet())
        {
            for (var j = 0; j < pair.getValue().size(); ++j)
            {
                int destinationIndex = getVertices().indexOf(pair.getValue().get(j).getDestination());
                float weight = pair.getValue().get(j).getWeight();
                g.add(mutNode(pair.getKey().getName()).addLink(to((mutNode(getVertices().get(destinationIndex).getName()))).add(Label.of(String.valueOf(weight)))));
            }
        }
        try
        {
            Graphviz.fromGraph(g).width(GRAPHVIZ_IMAGE_WIDTH).render(Format.PNG).toFile(new File(GRAPHVIZ_FOLDER+fileName+GRAPHVIZ_FILE_EXTENSION));
        }
        catch ( IOException e )
        {
            LOGGER.log(Level.SEVERE, "IO Exception thrown when saving Graphviz file", e);
        }
    }

    @Override
    public float getDistance(Vertex source, Vertex destination)
    {
        for(var i = 0; i < getAdjacencyMap().get(source).size(); i++)
        {
            if(getAdjacencyMap().get(source).get(i).getDestination() == destination)
            {
                return getAdjacencyMap().get(source).get(i).getWeight();
            }
        }
        return -1;
    }
}