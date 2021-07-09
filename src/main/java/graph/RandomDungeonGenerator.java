package graph;

import java.awt.*;

public class RandomDungeonGenerator
{
    private AbstractGraph dungeon;

    public AbstractGraph getDungeon()
    {
        return dungeon;
    }

    public RandomDungeonGenerator(int nRooms, int roomMaxWidth, int roomMaxHeight)
    {
        createGraphWithRooms(roomMaxWidth, roomMaxHeight, nRooms);
    }

    private void createGraphWithRooms(int maxWidth, int maxHeight, int nRooms)
    {
        dungeon = new GraphList();
        for (var i = 0; i < nRooms; i++)
        {
            boolean roomIsValid;
            Rectangle newRectangle;
            do
            {
                roomIsValid = true;
                newRectangle = createRandomRectangle(maxWidth, maxHeight);
                for (var j = 0; (j < dungeon.getNumberOfVertices()) && roomIsValid; j++)
                {
                    if (newRectangle.intersects(((Room) dungeon.getVertices().get(j)).getRoom()))
                    {
                        roomIsValid = false;
                    }
                }
            }while(!roomIsValid);
            dungeon.addVertex(new Room(newRectangle));
        }
    }

    private Rectangle createRandomRectangle(int maxWidth, int maxHeight)
    {
        var randomSingleton = RandomSingleton.getInstance();
        int width = Math.max(randomSingleton.nextInt(maxWidth), 10);
        int height = Math.max(randomSingleton.nextInt(maxHeight), 10);
        var x = randomSingleton.nextInt(800);
        var y = randomSingleton.nextInt(800);
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }
}
