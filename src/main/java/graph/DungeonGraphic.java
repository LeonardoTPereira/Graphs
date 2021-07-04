package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DungeonGraphic extends JFrame
{
    private final BufferedImage image;
    AbstractGraph graph;
    public DungeonGraphic(AbstractGraph graph)
    {
        super("Dungeon");
        image = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        this.graph = graph;
        getContentPane().setBackground(Color.black);
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.repaint();
    }

    private void drawDungeon(Graphics g)
    {
        Graphics2D graphics2D = (Graphics2D) g;
        if(graph != null)
        {
            for (int i = 0; i < graph.getNumberOfVertices(); i++)
            {
                graphics2D.setColor(Color.BLUE);
                Vertex currentVertex = graph.getVertices().get(i);
                graphics2D.draw(((Room) currentVertex).getRoom());
                g.setColor(Color.RED);
                var adjacentVertex = graph.getFirstConnectedVertex(currentVertex);
                while(adjacentVertex != null)
                {
                    /*g.drawLine((int)((Room) currentVertex).getPoint().getX(), (int)((Room) currentVertex).getPoint().getY(),
                            (int)((Room) currentVertex).getPoint().getX(),(int) ((Room) adjacentVertex).getPoint().getY());
                    g.drawLine((int)((Room) currentVertex).getPoint().getX(), (int)((Room) adjacentVertex).getPoint().getY(),
                            (int)((Room) adjacentVertex).getPoint().getX(),(int) ((Room) adjacentVertex).getPoint().getY());*/
                    g.drawLine((int)((Room) currentVertex).getPoint().getX(), (int)((Room) currentVertex).getPoint().getY(),
                            (int)((Room) adjacentVertex).getPoint().getX(),(int) ((Room) adjacentVertex).getPoint().getY());
                    adjacentVertex = graph.getNextConnectedVertex(currentVertex, adjacentVertex);
                }
            }
        }
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawDungeon(g);
    }
}
