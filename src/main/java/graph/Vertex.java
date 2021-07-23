package graph;

public class Vertex
{

    private String name;
    private int inDegree;
    private int outDegree;

    public Vertex(String name)
    {
        inDegree = 0;
        outDegree = 0;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getOutDegree()
    {
        return outDegree;
    }

    public void incrementInDegree()
    {
        inDegree++;
    }

    public void incrementOutDegree()
    {
        outDegree++;
    }

    public void decrementInDegree()
    {
        inDegree--;
    }

    public void decrementOutDegree()
    {
        outDegree--;
    }

    public int getInDegree()
    {
        return inDegree;
    }

}
