package graph;

import java.awt.*;
import java.awt.Rectangle;
public class Room extends Vertex
{
    public Rectangle getRoom()
    {
        return room;
    }

    private Rectangle room;
    public Room(Point point, Dimension dimension)
    {
        super("("+ point.getX() + "," + point.getY() + ")");
        room = new Rectangle(point, dimension);
    }

    public Room(Rectangle rectangle)
    {
        super("("+ rectangle.getX() + "," + rectangle.getY() + ")");
        room = rectangle;
    }
    public Point getPoint()
    {
        return new Point((int)room.getX(), (int)room.getY());
    }
}
