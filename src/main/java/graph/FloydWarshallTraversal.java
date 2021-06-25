package graph;

import java.text.DecimalFormat;
import java.util.logging.Logger;

public class FloydWarshallTraversal extends TraversalStrategyInterface
{
    private static final Logger LOGGER = Logger.getLogger("FloydWarshallTraversal.class");

    private float [][]distanceMatrix;

    public float[][] getDistanceMatrix()
    {
        return distanceMatrix;
    }

    public void setDistanceMatrix(float[][] distanceMatrix)
    {
        this.distanceMatrix = distanceMatrix;
    }

    public FloydWarshallTraversal(AbstractGraph graph)
    {
        super(graph);
        distanceMatrix = new float[graph.getNumberOfVertices()][graph.getNumberOfVertices()];
    }

    @Override
    void traverseGraph(Vertex source)
    {
        for (var i = 0; i < getGraph().getNumberOfVertices(); i++)
        {
            for (var j = 0; j < getGraph().getNumberOfVertices(); j++)
            {
                Vertex origin = getGraph().getVertices().get(i);
                Vertex destination = getGraph().getVertices().get(j);
                distanceMatrix[i][j] = getGraph().getDistance(origin, destination);
            }
        }

        for (var k = 0; k < getGraph().getNumberOfVertices(); k++)
        {
            for (var i = 0; i < getGraph().getNumberOfVertices(); i++)
            {
                for (var j = 0; j < getGraph().getNumberOfVertices(); j++)
                {
                    if((distanceMatrix[i][k] + distanceMatrix[k][j]) < distanceMatrix[i][j])
                    {
                        distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
                    }
                }
            }
        }
        printDistanceMatrix();
    }

    void printDistanceMatrix()
    {
        var decimalFormat = new DecimalFormat("00.00");
        var distanceMatrixStringBuilder = new StringBuilder();
        distanceMatrixStringBuilder.append('\n');
        for (float[] row : distanceMatrix)
        {
            for (float v : row)
            {
                distanceMatrixStringBuilder.append(decimalFormat.format(v)).append(' ');
            }
            distanceMatrixStringBuilder.append('\n');
        }
        var distanceMatrixString = distanceMatrixStringBuilder.toString();
        LOGGER.info(distanceMatrixString);
    }
}
