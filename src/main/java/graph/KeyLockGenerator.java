package graph;

public final class KeyLockGenerator extends BreadthFirstTraversal
{

    private int nVisitedVertices;
    private int nCreatedKeys;
    private int nCreatedLocks;
    private int nTotalLocks;
    private static final int CHANCE_TO_CREATE_LOCK_OR_KEY = 30;

    public KeyLockGenerator(AbstractGraph graph, int nLocks)
    {
        super(graph);
        nCreatedKeys = 0;
        nCreatedLocks = 0;
        nTotalLocks = nLocks;
        nVisitedVertices = 0;
    }

    @Override
    protected void updateTraversalInfoForVertex(int newVertexIndex, int previousVertexIndex)
    {
        super.updateTraversalInfoForVertex(newVertexIndex, previousVertexIndex);
        nVisitedVertices++;
        tryToCreateLockOrKey(newVertexIndex, previousVertexIndex);
    }

    private void tryToCreateLockOrKey(int newVertexIndex, int previousVertexIndex)
    {
        if(needsMoreLocks(nCreatedLocks, nTotalLocks))
        {
            if (hasEnoughUnvisitedRooms())
            {
                if (roomHasBeenSelected())
                {
                    placeKeyOrLock(newVertexIndex, previousVertexIndex);
                }
            }
            else
            {
                placeKeyOrLock(newVertexIndex, previousVertexIndex);
            }
        }
    }

    private boolean hasEnoughUnvisitedRooms()
    {
        return getGraph().getNumberOfVertices() > (nVisitedVertices + nTotalLocks * 2 - nCreatedLocks - nCreatedKeys);
    }

    private void placeKeyOrLock(int newVertexIndex, int previousVertexIndex)
    {
        if (nCreatedKeys > nCreatedLocks)
        {
            placeLockInEdge(previousVertexIndex, newVertexIndex);
        } else
        {
            placeKeyInRoom(newVertexIndex);
        }
    }

    private boolean roomHasBeenSelected()
    {
        return RandomSingleton.getInstance().nextInt(100) < CHANCE_TO_CREATE_LOCK_OR_KEY;
    }

    private boolean needsMoreLocks(int nCreatedLocks, int nTotalLocks)
    {
        return nCreatedLocks < nTotalLocks;
    }

    private void placeLockInEdge(int previousVertexIndex, int newVertexIndex)
    {
        Vertex source = getGraph().getVertices().get(previousVertexIndex);
        Vertex destination = getGraph().getVertices().get(newVertexIndex);
        getGraph().lockEdge(source, destination, nCreatedLocks++);
    }

    private void placeKeyInRoom(int newVertexIndex)
    {
        Room keyRoom = (Room)getGraph().getVertices().get(newVertexIndex);
        keyRoom.setKeyID(nCreatedKeys++);
    }

}
