package graph;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static guru.nidi.graphviz.model.Factory.*;

public class DigraphMatrix extends AbstractGraph
{
    private Edge[][] adjacencyMatrix;

    private static final Logger LOGGER = Logger.getLogger("DigraphMatrix.class");

    public DigraphMatrix(List<Vertex> vertices)
    {
        super(vertices);
        initializeAdjacencyMatrix();
    }

    public DigraphMatrix()
    {
        super();
    }

    @Override
    public void addVertex(Vertex vertex)
    {
        super.addVertex(vertex);
        expandAdjacencyMatrix();
    }

    private void initializeAdjacencyMatrix()
    {
        setAdjacencyMatrix(new Edge[getNumberOfVertices()][getNumberOfVertices()]);
        for (var i = 0; i < getNumberOfVertices(); i++)
        {
            for (var j = 0; j < getNumberOfVertices(); j++)
            {
                getAdjacencyMatrix()[i][j] = null;
            }
        }
    }

    private void expandAdjacencyMatrix()
    {
        var newAdjacencyMatrix = new Edge[getNumberOfVertices()][getNumberOfVertices()];
        System.arraycopy(adjacencyMatrix, 0, newAdjacencyMatrix, 0, getNumberOfVertices()-1);
        for (var i = 0; i < (getNumberOfVertices()-1); i++)
        {
            System.arraycopy(adjacencyMatrix[i], 0, newAdjacencyMatrix[i], 0, getNumberOfVertices()-1);
            newAdjacencyMatrix[i][getNumberOfVertices()-1] = null;
            newAdjacencyMatrix[getNumberOfVertices()-1][i] = null;
        }
        newAdjacencyMatrix[getNumberOfVertices()-1][getNumberOfVertices()-1] = null;
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
            getAdjacencyMatrix()[getVertices().indexOf(source)][getVertices().indexOf(destination)] = new Edge(weight);
        }
    }

    @Override
    public void removeEdge(Vertex source, Vertex destination)
    {
        int sourceIndex = getVertices().indexOf(source);
        int destinationIndex = getVertices().indexOf(destination);
        if(edgeExists(source, destination))
        {
            getAdjacencyMatrix()[sourceIndex][destinationIndex] = null;
        }
    }

    @Override
    public boolean edgeExists(Vertex source, Vertex destination)
    {
        int sourceIndex = getVertices().indexOf(source);
        int destinationIndex = getVertices().indexOf(destination);
        return getAdjacencyMatrix()[sourceIndex][destinationIndex] != null;
    }

    @Override
    public boolean hasAnyEdge(Vertex vertex)
    {
        int vertexIndex = getVertices().indexOf(vertex);
        for (var i = 0; i < getNumberOfVertices(); i++)
        {
            if(getAdjacencyMatrix()[vertexIndex][i] != null)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Vertex getFirstConnectedVertex(Vertex vertex)
    {
        if(!hasAnyEdge(vertex))
        {
            return null;
        }
        else
        {
            var currentVertexIndex = 0;
            Vertex connected;
            do
            {
                connected = getVertices().get(currentVertexIndex++);
            }while(!edgeExists(vertex, connected));
            return connected;
        }
    }

    @Override
    public Vertex getNextConnectedVertex(Vertex source, Vertex currentConnection)
    {
        Vertex newConnection;
        for (int i = getVertices().indexOf(currentConnection)+1; i < getNumberOfVertices(); i++)
        {
            newConnection = getVertices().get(i);
            if(edgeExists(source, newConnection))
            {
                return newConnection;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        var s = new StringBuilder();
        for (var i = 0; i < getNumberOfVertices(); i++)
        {
            s.append(i).append(": ");
            for (var j = 0; j < getNumberOfVertices(); ++j)
            {
                if(edgeExists(getVertices().get(i), getVertices().get(j)))
                {
                    s.append(getAdjacencyMatrix()[i][j].getWeight()).append(" ");
                }
                else
                {
                    s.append(0.0 + " ");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public void printInGraphViz(String fileName)
    {
        MutableGraph g = mutGraph("example1Digraph").setDirected(true);

        for (var i = 0; i < getNumberOfVertices(); ++i)
        {
            for (var j = 0; j < getNumberOfVertices(); ++j)
            {
                if(edgeExists(getVertices().get(i), getVertices().get(j)))
                {
                    float weight = adjacencyMatrix[i][j].getWeight();
                    g.add(mutNode(getVertices().get(i).getName()).addLink(to((mutNode(getVertices().get(j).getName()))).add(Label.of(String.valueOf(weight)))));
                }
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
        var edge = adjacencyMatrix[getVertices().indexOf(source)][getVertices().indexOf(destination)];
        if(edge != null)
        {
            return edge.getWeight();
        }
        else
        {
            return Float.POSITIVE_INFINITY;
        }
    }

    public Edge[][] getAdjacencyMatrix()
    {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(Edge[][] adjacencyMatrix)
    {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    @Override
    protected DigraphMatrix clone() throws CloneNotSupportedException
    {
        DigraphMatrix cloneGraph = (DigraphMatrix) super.clone();
        cloneGraph.cloneAdjacencyMatrix(this);
        return cloneGraph;
    }

    private void cloneAdjacencyMatrix(DigraphMatrix cloneTarget)
    {
        for(var i = 0; i < cloneTarget.getAdjacencyMatrix().length; i++)
        {
            for (var j = 0; j < cloneTarget.getAdjacencyMatrix().length; j++)
            {
                if(cloneTarget.getAdjacencyMatrix()[i][j] != null)
                {
                    addEdge(getVertices().get(i), getVertices().get(j), cloneTarget.getAdjacencyMatrix()[i][j].getWeight());
                }
            }
        }
    }

    public void removeAllEdges()
    {
        for(var i = 0; i < getAdjacencyMatrix().length; i++)
        {
            for (var j = 0; j < getAdjacencyMatrix().length; j++)
            {
                getAdjacencyMatrix()[i][j] = null;
            }
        }
    }
}
