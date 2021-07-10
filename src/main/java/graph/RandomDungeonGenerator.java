package graph;

import java.awt.*;

public class RandomDungeonGenerator
{
    private AbstractGraph dungeon;

    private static final int ROOM_MAX_WIDTH = 100;
    private static final int ROOM_MAX_HEIGHT = 100;

    public AbstractGraph getDungeon()
    {
        return dungeon;
    }

    public RandomDungeonGenerator(int nRooms)
    {
        createGraphWithRooms(nRooms);
    }

    private void createGraphWithRooms(int nRooms)
    {
        dungeon = new GraphList();
        for (int i = 0; i < nRooms; i++)
        {
            boolean roomIsValid;
            Rectangle newRectangle;
            do
            {
                roomIsValid = true;
                newRectangle = createRandomRectangle(ROOM_MAX_WIDTH, ROOM_MAX_HEIGHT);
                for (int j = 0; (j < dungeon.getNumberOfVertices()) && roomIsValid; j++)
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
        RandomSingleton randomSingleton = RandomSingleton.getInstance();
        int width = Math.max(randomSingleton.nextInt(maxWidth), 10);
        int height = Math.max(randomSingleton.nextInt(maxHeight), 10);
        int x = randomSingleton.nextInt(800);
        int y = randomSingleton.nextInt(800);
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }
}
