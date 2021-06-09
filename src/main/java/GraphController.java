import java.util.ArrayList;

public class GraphController
{

    public static void main(String[] args)
    {
        AbstractGraph g = new Digraph_Matrix(new ArrayList<>()
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

        System.out.println();
        System.out.println();
        g.printInGraphViz("Digraph");

        g = new Graph_Matrix(new ArrayList<>()
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
