package graph;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DungeonGraphic extends JFrame
{
    AbstractGraph graph;
    List<Vertex> traversalPath;
    public DungeonGraphic(AbstractGraph graph, @Nullable List<Vertex> traversalPath)
    {
        super("Dungeon");
        this.graph = graph;
        this.traversalPath = traversalPath;
        getContentPane().setBackground(Color.black);
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.repaint();
    }

    private void drawDungeon(Graphics g)
    {
        Graphics2D graphics2D = (Graphics2D) g;
        List<Vertex> shortestPath;
        Vertex start = null;
        Vertex end = null;
        if(graph != null)
        {
            for (int i = 0; i < graph.getNumberOfVertices(); i++)
            {
                graphics2D.setColor(Color.BLUE);
                Vertex currentVertex = graph.getVertices().get(i);
                graphics2D.draw(((Room)currentVertex).getRoom());
                if(((Room)currentVertex).isCheckpoint())
                {
                    graphics2D.setColor(Color.YELLOW);
                    graphics2D.fill(((Room) currentVertex).getRoom());
                }
                else if(((Room)currentVertex).isExit())
                {
                    graphics2D.setColor(Color.RED);
                    graphics2D.fill(((Room) currentVertex).getRoom());
                }
                else if(((Room)currentVertex).isEntrance())
                {
                    graphics2D.setColor(Color.GREEN);
                    graphics2D.fill(((Room) currentVertex).getRoom());
                }
                Vertex adjacentVertex = graph.getFirstConnectedVertex(currentVertex);
                while(adjacentVertex != null)
                {
                    g.setColor(Color.PINK);
                    if(traversalPath != null)
                    {
                        if(traversalPath.contains(adjacentVertex) && traversalPath.contains(currentVertex))
                        {
                            g.setColor(Color.CYAN);
                        }
                    }
                    g.drawLine((int)((Room) currentVertex).getPoint().getX(), (int)((Room) currentVertex).getPoint().getY(),
                            (int)((Room) adjacentVertex).getPoint().getX(),
                            (int) ((Room) adjacentVertex).getPoint().getY());
                    adjacentVertex = graph.getNextConnectedVertex(currentVertex, adjacentVertex);
                }
            }
        }
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        drawDungeon(g);
    }
}
