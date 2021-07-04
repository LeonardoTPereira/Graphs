package graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DelaunayTriangulation
{
    private AbstractGraph graph;

    public DelaunayTriangulation()
    {
    }

    public AbstractGraph createDungeonAsGraph(int roomMaxWidth, int roomMaxHeight, int nRooms)
    {
        createGraphWithRooms(roomMaxWidth, roomMaxHeight, nRooms);
        createTriangles();
        return graph;
    }
    private void createTriangles()
    {
        List<Triangle> triangleList = new ArrayList<>();
        for (var i = 0; i < graph.getNumberOfVertices(); i++)
        {
            for (int j = (i+1); j < graph.getNumberOfVertices(); j++)
            {
                for (int k = (j+1); k < graph.getNumberOfVertices(); k++)
                {
                    var isTriangle = true;
                    var triangle = new Triangle(((Room)graph.getVertices().get(i)).getPoint(), ((Room)graph.getVertices().get(j)).getPoint(),
                            ((Room)graph.getVertices().get(k)).getPoint());
                    for (var a = 0; (a < graph.getNumberOfVertices()) && isTriangle; a++)
                    {
                        if ((a != i) && (a != j) && (a != k))
                        {
                            Point point = ((Room)graph.getVertices().get(a)).getPoint();
                            if (triangle.contains(point))
                            {
                                isTriangle = false;
                            }
                        }
                    }
                    if(isTriangle)
                    {
                        if(triangleDoesNotOverlap(triangle, triangleList))
                        {
                            graph.addEdge(graph.getVertices().get(i), graph.getVertices().get(j));
                            graph.addEdge(graph.getVertices().get(i), graph.getVertices().get(k));
                            graph.addEdge(graph.getVertices().get(j), graph.getVertices().get(k));
                            triangleList.add(triangle);
                        }
                    }
                }
            }
        }
    }

    private boolean triangleDoesNotOverlap(Triangle newTriangle, List<Triangle> existingTriangles)
    {
        Point []pointsNewTriangle = {newTriangle.getP1(), newTriangle.getP2(), newTriangle.getP3(), newTriangle.getP1()};
        for (Triangle triangle : existingTriangles)
        {
            Point []pointsCurrentTriangle = {triangle.getP1(), triangle.getP2(), triangle.getP3(), triangle.getP1()};
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if(isCrossIntersect((int)pointsNewTriangle[i].getX(), (int)pointsNewTriangle[i].getY(),
                            (int)pointsNewTriangle[i+1].getX(), (int)pointsNewTriangle[i+1].getY(),
                            (int)pointsCurrentTriangle[j].getX(), (int)pointsCurrentTriangle[j].getY(),
                            (int)pointsCurrentTriangle[j+1].getX(), (int)pointsCurrentTriangle[j+1].getY()))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isCrossIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int z1 = (x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1);
        int z2 = (x4 - x1) * (y2 - y1) - (y4 - y1) * (x2 - x1);
        if (z1 < 0 && z2 < 0 || z1 > 0 && z2 > 0 || z1 == 0 || z2 == 0)
            return false;
        int z3 = (x1 - x3) * (y4 - y3) - (y1 - y3) * (x4 - x3);
        int z4 = (x2 - x3) * (y4 - y3) - (y2 - y3) * (x4 - x3);
        if (z3 < 0 && z4 < 0 || z3 > 0 && z4 > 0 || z3 == 0 || z4 == 0)
            return false;
        return true;
    }

    private void createGraphWithRooms(int maxWidth, int maxHeight, int nRooms)
    {
        graph = new GraphList();
        for (var i = 0; i < nRooms; i++)
        {
            var newRectangle = createRandomRectangle(maxWidth, maxHeight);
            var roomIsValid = true;
            for (var j = 0; (j < graph.getNumberOfVertices()) && roomIsValid; j++)
            {
                if(newRectangle.intersects(((Room)graph.getVertices().get(j)).getRoom()))
                {
                    roomIsValid = false;
                }
            }
            graph.addVertex(new Room(newRectangle));
        }
    }

    private Rectangle createRandomRectangle(int maxWidth, int maxHeight)
    {
        var random = new Random();
        int width = Math.max(random.nextInt(maxWidth), 10);
        int height = Math.max(random.nextInt(maxHeight), 10);
        var x = random.nextInt(800);
        var y = random.nextInt(800);
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }
}
