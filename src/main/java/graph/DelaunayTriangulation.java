package graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DelaunayTriangulation
{
    private DelaunayTriangulation(){}

    public static void triangulateGraphVertices(AbstractGraph graph)
    {
        List<Triangle> triangleList = new ArrayList<>();
        for (var i = 0; i < graph.getNumberOfVertices(); i++)
        {
            for (var j = (i+1); j < graph.getNumberOfVertices(); j++)
            {
                for (var k = (j+1); k < graph.getNumberOfVertices(); k++)
                {
                    var isTriangle = true;
                    var triangle = new Triangle(((Room)graph.getVertices().get(i)).getPoint(), ((Room)graph.getVertices().get(j)).getPoint(),
                            ((Room)graph.getVertices().get(k)).getPoint());
                    isTriangle = hasNoVerticesInsideTriangle(graph, triangle);
                    if(isTriangle && triangleDoesNotOverlap(triangle, triangleList))
                    {
                        float weight;
                        weight = (float)Point.distance(triangle.getP1().getX(), triangle.getP1().getY(), triangle.getP2().getX(), triangle.getP2().getY());
                        graph.addEdge(graph.getVertices().get(i), graph.getVertices().get(j), weight);
                        weight = (float)Point.distance(triangle.getP1().getX(), triangle.getP1().getY(), triangle.getP3().getX(), triangle.getP3().getY());
                        graph.addEdge(graph.getVertices().get(i), graph.getVertices().get(k), weight);
                        weight = (float)Point.distance(triangle.getP2().getX(), triangle.getP2().getY(), triangle.getP3().getX(), triangle.getP3().getY());
                        graph.addEdge(graph.getVertices().get(j), graph.getVertices().get(k), weight);
                        triangleList.add(triangle);
                    }
                }
            }
        }
    }


    private static boolean hasNoVerticesInsideTriangle(AbstractGraph graph, Triangle triangle)
    {
        for (var a = 0; (a < graph.getNumberOfVertices()); a++)
        {
            var point = ((Room) graph.getVertices().get(a)).getPoint();
            if (isNotTriangleVertex(triangle, point) && triangle.contains(point))
            {
                return false;
            }
        }
        return true;
    }

    private static boolean isNotTriangleVertex(Triangle triangle, Point point)
    {
        if (!point.equals(triangle.getP1()) && !point.equals(triangle.getP2()))
        {
            return !point.equals(triangle.getP3());
        }
        return false;
    }

    private static boolean triangleDoesNotOverlap(Triangle newTriangle, List<Triangle> existingTriangles)
    {
        var pointsNewTriangle = new Point[]{newTriangle.getP1(), newTriangle.getP2(), newTriangle.getP3(), newTriangle.getP1()};
        for (Triangle triangle : existingTriangles)
        {
            var pointsCurrentTriangle = new Point[]{triangle.getP1(), triangle.getP2(), triangle.getP3(), triangle.getP1()};
            for (var i = 0; i < 3; i++)
            {
                for (var j = 0; j < 3; j++)
                {
                    if(isCrossIntersect(pointsNewTriangle[i], pointsNewTriangle[i+1],
                            pointsCurrentTriangle[j], pointsCurrentTriangle[j+1]))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Check if two points from different line segments intersect
    private static boolean isCrossIntersect(Point p1, Point p2, Point p3, Point p4) {
        int z1 = (int)((p3.getX() - p1.getX()) * (p2.getY() - p1.getY()) - (p3.getY() - p1.getY()) * (p2.getX() - p1.getX()));
        int z2 = (int)((p4.getX() - p1.getX()) * (p2.getY() - p1.getY()) - (p4.getY() - p1.getY()) * (p2.getX() - p1.getX()));
        if (productsTNotIntersect(z1, z2))
        {
            return false;
        }
        int z3 = (int)((p1.getX() - p3.getX()) * (p4.getY() - p3.getY()) - (p1.getY() - p3.getY()) * (p4.getX() - p3.getX()));
        int z4 = (int)((p2.getX() - p3.getX()) * (p4.getY() - p3.getY()) - (p2.getY() - p3.getY()) * (p4.getX() - p3.getX()));
        return productsUNotIntersect(z3, z4);
    }

    private static boolean productsUNotIntersect(int z3, int z4)
    {
        return (z3 >= 0 || z4 >= 0) && (z3 <= 0 || z4 <= 0) && z3 != 0 && z4 != 0;
    }

    private static boolean productsTNotIntersect(int z1, int z2)
    {
        return z1 < 0 && z2 < 0 || z1 > 0 && z2 > 0 || z1 == 0 || z2 == 0;
    }
}
