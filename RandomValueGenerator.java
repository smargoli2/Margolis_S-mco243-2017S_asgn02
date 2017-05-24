package processor;

import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator
{
	
	Random rand = new Random();
	
	public int getNextInt()
	{
		return rand.nextInt();
	}
	
	public boolean getTrueWithProbability(double p)
	{
		int decide = rand.nextInt(101);// a num between 1 and 100
		// if it's between 1 and the value passed in
		if (decide <= (p * 100))
		{
			return true;
		} else
		{
			return false;
		}
	}
}
