package graph;

import java.awt.*;
import java.awt.Rectangle;
public class Room extends Vertex
{
    private boolean isEntrance;
    private boolean isExit;
    private boolean isCheckpoint;
    private int keyID;

    public boolean isEntrance()
    {
        return isEntrance;
    }

    public void setEntrance(boolean entrance)
    {
        isEntrance = entrance;
    }

    public boolean isExit()
    {
        return isExit;
    }

    public void setExit(boolean exit)
    {
        isExit = exit;
    }

    public boolean isCheckpoint()
    {
        return isCheckpoint;
    }

    public void setCheckpoint(boolean checkpoint)
    {
        isCheckpoint = checkpoint;
    }

    public Rectangle getRoom()
    {
        return room;
    }

    private Rectangle room;
    public Room(Point point, Dimension dimension)
    {
        super("("+ point.getX() + "," + point.getY() + ")");
        isEntrance = false;
        isExit = false;
        isCheckpoint = false;
        room = new Rectangle(point, dimension);
        keyID = -1;
    }

    public Room(Rectangle rectangle)
    {
        super("("+ rectangle.getX() + "," + rectangle.getY() + ")");
        isEntrance = false;
        isExit = false;
        isCheckpoint = false;
        room = rectangle;
        keyID = -1;
    }
    public Point getPoint()
    {
        return new Point((int)room.getX(), (int)room.getY());
    }

    @Override
    public String toString()
    {
        return "Room{" +
                "(X, Y)= (" + room.getX() + ", " + room.getY() + ")"+
                '}';
    }

    public int getKeyID()
    {
        return keyID;
    }

    public void setKeyID(int keyID)
    {
        if(keyID < 0)
        {
            System.out.println("CHAVE NÃO PODE SER NEGATIVA");
        }
        this.keyID = keyID;
    }
}
