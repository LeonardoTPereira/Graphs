import java.util.ArrayList;
import java.util.List;

public class Graph_Matrix extends Digraph_Matrix
{
    public Graph_Matrix(List<Vertex> vertices)
    {
        super(vertices);
    }

    @Override
    public void addEdge(int source, int destination)
    {
        super.addEdge(source, destination);
        super.addEdge(destination, source);
    }

    @Override
    public void removeEdge(int source, int destination)
    {
        super.removeEdge(source, destination);
        super.removeEdge(destination, source);
    }

    public static void main(String[] args)
    {
        AbstractGraph g = new Graph_Matrix(new ArrayList<>()
        {{
            add(new Vertex("Joao"));
            add(new Vertex("Maria"));
            add(new Vertex("José"));
            add(new Vertex("Marcos"));
            add(new Vertex("Pedro"));
        }});

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        System.out.print(g);
        g.printInGraphViz("Graph");
    }

}
