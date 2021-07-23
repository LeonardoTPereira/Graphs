package graph;

public class WebPage extends Vertex
{
    public void setRank(float rank)
    {
        this.rank = rank;
    }

    private float rank;

    public WebPage(String name, float rank)
    {
        super(name);
        this.rank = rank;
    }

    public float getPageRankToTransfer()
    {
        return rank/getOutDegree();
    }

    @Override
    public String toString()
    {
        return "WebPage{" +
                "rank=" + rank +
                '}';
    }
}
