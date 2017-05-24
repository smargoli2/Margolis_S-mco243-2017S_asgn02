package processor;

public class SimProcessor implements IProcessor
{
	IRandomValueGenerator rand;
	IProcess currentProc;
	int[] registers;
	int currInstruction;
	
	public SimProcessor(IProcess proc, IRandomValueGenerator ran)
	{
		rand = ran;
		this.currentProc = proc;
		registers = new int[4];
	}
	
	@Override
	public IProcess getCurrentProcess()
	{
		return currentProc;
	}
	
	@Override
	public void setCurrentProcess(IProcess p)
	{
		this.currentProc = p;
		
	}
	
	@Override
	public int getCurInstruction()
	{
		return this.currInstruction;
	}
	
	@Override
	public void setCurInstruction(int c)
	{
		this.currInstruction = c;
		
	}
	
	@Override
	public ProcessState executeNextInstruction()
	{
		ProcessState s = currentProc.execute(currInstruction);
		currInstruction++;
		return s;
	}
	
	@Override
	public void setRegisterValue(int i, int val)
	{
		this.registers[i] = val;
	}
	
	@Override
	public int getRegisterValue(int i)
	{
		return rand.getNextInt();
	}
	
}
