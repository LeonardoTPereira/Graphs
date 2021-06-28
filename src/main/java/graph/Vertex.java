package graph;

public class Vertex
{

    private String name;
    public Vertex(String name)
    {
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
}
