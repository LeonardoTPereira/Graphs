package graph;

public class Quest extends Vertex
{
    private static int idCounter = 0;

    private int questId;
    private String questDescription;

    public Quest(String questName, String questDescription)
    {
        super(questName);
        this.questId = idCounter++;
        this.questDescription = questDescription;
    }

    public int getQuestId()
    {
        return questId;
    }

    public void setQuestId(int questId)
    {
        this.questId = questId;
    }

    @Override
    public String toString()
    {
        return "Quest{" +
                "\n\tID= '" + questId + '\'' +
                "\n\tname= '" + getName() + '\'' +
                "\n\tdescription= '" + questDescription + '\'' +
                "\n}\n";
    }

    public String getQuestDescription()
    {
        return questDescription;
    }

    public void setQuestDescription(String questDescription)
    {
        this.questDescription = questDescription;
    }
}
