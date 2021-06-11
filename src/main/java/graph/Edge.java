package graph;

public class Edge
{
    Vertex destination;
    float weight;

    public Edge(Vertex destination, float weight)
    {
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(float weight)
    {
        this.destination = null;
        this.weight = weight;
    }
}
