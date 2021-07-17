package graph;

public class Edge
{
    private Vertex destination;
    private float weight;
    private int lockID;

    public Edge(Vertex destination, float weight)
    {
        this.destination = destination;
        this.weight = weight;
        this.lockID = -1;
    }

    public Edge(float weight)
    {
        this.destination = null;
        this.weight = weight;
        this.lockID = -1;
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

    public int getLockID()
    {
        return lockID;
    }

    public void setLockID(int lockID)
    {
        this.lockID = lockID;
    }
}
