package graph;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class DungeonController
{
    private static final Logger LOGGER = Logger.getLogger("DungeonController.class");

    private AbstractGraph dungeon;

    private Room entrance;
    private Room exit;

    private DungeonController()
    {
    }

    public static void main(String[] args)
    {
        DungeonController dungeonController = new DungeonController();
        createRandomDungeon(dungeonController);
        DelaunayTriangulation.triangulateGraphVertices(dungeonController.dungeon);
        CreateDungeonGraphic(dungeonController);
        ReplaceDungeonWithMST(dungeonController);
        CreateDungeonGraphic(dungeonController);
        setSpecialRooms(dungeonController);
        List<Vertex> traversalPath = getPathFromEntranceToExit(dungeonController);
        CreateDungeonGraphic(dungeonController, traversalPath);
    }

    private static void CreateDungeonGraphic(DungeonController dungeonController)
    {
        SwingUtilities.invokeLater(() -> new DungeonGraphic(dungeonController.dungeon, null).setVisible(true));
    }

    private static void CreateDungeonGraphic(DungeonController dungeonController, List<Vertex> traversalPath)
    {
        SwingUtilities.invokeLater(() -> new DungeonGraphic(dungeonController.dungeon, traversalPath).setVisible(true));
    }

    private static void createRandomDungeon(DungeonController dungeonController)
    {
        Scanner scanner = new Scanner(System.in);
        int seed = Integer.parseInt(scanner.nextLine());
        RandomSingleton.getInstance(seed);
        int nRooms = Integer.parseInt(scanner.nextLine());
        RandomDungeonGenerator randomDungeonGenerator = new RandomDungeonGenerator(nRooms);
        dungeonController.dungeon = randomDungeonGenerator.getDungeon();
    }

    private static void ReplaceDungeonWithMST(DungeonController dungeonController)
    {
        AbstractGraph dungeon = dungeonController.dungeon;
        TraversalStrategyInterface traversalStrategy;
        traversalStrategy = new PrimMSTTraversal(dungeon);
        traversalStrategy.traverseGraph(dungeon.getVertices().get(0), null);
        dungeonController.dungeon = GraphConverter.predecessorListToGraph(dungeon, traversalStrategy.getPredecessorArray());
    }

    private static void setSpecialRooms(DungeonController dungeonController)
    {
        AbstractGraph dungeon = dungeonController.dungeon;
        TraversalStrategyInterface traversalStrategy = new FloydWarshallTraversal(dungeon);
        traversalStrategy.traverseGraph(dungeon.getVertices().get(0), null);
        Room center = (Room) dungeon.getCentermostVertex(((FloydWarshallTraversal)traversalStrategy).getDistanceMatrix());
        center.setCheckpoint(true);
        Room entrance = (Room) dungeon.getOuterMostVertex(((FloydWarshallTraversal)traversalStrategy).getDistanceMatrix());
        entrance.setEntrance(true);
        dungeonController.entrance = entrance;
        Room exit = (Room) dungeon.getMostDistantVertex(((FloydWarshallTraversal)traversalStrategy).getDistanceMatrix(), entrance);
        exit.setExit(true);
        dungeonController.exit = exit;
    }

    private static List<Vertex> getPathFromEntranceToExit(DungeonController dungeonController)
    {
        AbstractGraph dungeon = dungeonController.dungeon;
        TraversalStrategyInterface aStar = new AStartPathFind(dungeon);
        aStar.traverseGraph(dungeonController.entrance, dungeonController.exit);
        return aStar.getShortestPath(dungeonController.entrance, dungeonController.exit);
    }
}
