package graph;

import java.util.Random;

public class RandomSingleton
{
    private static final int DEFAULT_SEED = 42;
    private static RandomSingleton instance;
    private final Random random;

    private RandomSingleton()
    {
        random = new Random(DEFAULT_SEED);
    }

    private RandomSingleton(int seed)
    {
        random = new Random(seed);
    }

    public static RandomSingleton getInstance()
    {
        if (instance == null)
        {
            instance = new RandomSingleton();
        }
        return instance;
    }

    public static RandomSingleton getInstance(int seed)
    {
        if(instance == null)
        {
            instance = new RandomSingleton(seed);
        }
        return instance;
    }

    public int nextInt(int max)
    {
        return random.nextInt(max);
    }
}
