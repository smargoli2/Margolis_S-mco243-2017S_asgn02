package processor;

public class ProcessControlBlock
{
	IProcess proc;
	int currentInstruction;
	int reg1;
	int reg2;
	int reg3;
	int reg4;
	
	public ProcessControlBlock(IProcess p)
	{
		this.proc = p;
	}
	
	public IProcess getProcess()
	{
		return this.proc;
	}
	
	public int getCurrInstruction()
	{
		return this.currentInstruction;
	}
	
	public void setCurrInstruction(int i)
	{
		this.currentInstruction = i;
	}
	
	public int getReg1()
	{
		return this.reg1;
	}
	
	public int getReg2()
	{
		return this.reg2;
	}
	
	public int getReg3()
	{
		return this.reg3;
	}
	
	public int getReg4()
	{
		return this.reg4;
	}
	
	public void setReg1(int i)
	{
		this.reg1 = i;
	}
	
	public void setReg2(int i)
	{
		this.reg2 = i;
	}
	
	public void setReg3(int i)
	{
		this.reg3 = i;
	}
	
	public void setReg4(int i)
	{
		this.reg4 = i;
	}
	
}
