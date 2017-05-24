package processor;

public class SimProcess implements IProcess
{
	
	int id;
	String name;
	RandomValueGenerator rand;
	int totalInstructions;
	
	public SimProcess(int i, String n, int total)
	{
		this.id = i;
		this.name = n;
		rand = new RandomValueGenerator();
		this.totalInstructions = total;
	}
	
	public int getPid()
	{
		return this.id;
	}
	
	public String getProcName()
	{
		return this.name;
	}
	
	public ProcessState execute(int i)
	{
		System.out.println("Process ID: " + this.id + " Name: " + this.name + " Instruction number " + i);
		if (i >= this.totalInstructions)
		{
			return ProcessState.FINISHED;
		} else
		{
			if (rand.getTrueWithProbability(.85))
			{
				return ProcessState.READY;
			} else
			{
				return ProcessState.BLOCKED;
			}
		}
		
	}
	
}
