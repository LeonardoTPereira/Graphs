package graph;

public class Edge
{
    private Vertex destination;
    private float weight;

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

    public Vertex getDestination()
    {
        return destination;
    }

    public final void setDestination(Vertex destination)
    {
        this.destination = destination;
    }

    public float getWeight()
    {
        return weight;
    }

    public final void setWeight(float weight)
    {
        this.weight = weight;
    }

}
