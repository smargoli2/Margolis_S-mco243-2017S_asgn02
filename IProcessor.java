package processor;

public interface IProcessor
{
	public IProcess getCurrentProcess();
	
	public void setCurrentProcess(IProcess p);
	
	public int getCurInstruction();
	
	public void setCurInstruction(int i);
	
	public ProcessState executeNextInstruction();
	
	public void setRegisterValue(int i, int val);
	
	public int getRegisterValue(int i);
}
