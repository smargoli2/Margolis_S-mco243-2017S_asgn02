package processor;

import java.util.ArrayList;
import java.util.Stack;

public class RunProcessor
{
	public static void main(String[] args)
	{
		
		IRandomValueGenerator rand = new RandomValueGenerator();
		
		final int QUANTUM = 5;
		
		ArrayList<ProcessControlBlock> readyList = new ArrayList<ProcessControlBlock>();
		int val = 300;
		for (int i = 0; i < 10; i++)
		{
			readyList.add(new ProcessControlBlock(new SimProcess(i, "Proc " + i, val)));
			if (i % 2 == 0)
			{
				val += i * i;
			} else
			{
				val -= i * i;
			}
		}
		
		ArrayList<ProcessControlBlock> blockedList = new ArrayList<ProcessControlBlock>();
		
		ProcessControlBlock currentP = readyList.get(0);
		readyList.remove(0);
		
		IProcessor processor = new SimProcessor(currentP.proc, rand);//TODO changed...
		
		ProcessState state = ProcessState.READY;
		
		int cycles = QUANTUM;
		
		for (int i = 0; i < 3000; i++)
		{
			
			System.out.print("Step " + (i + 1) + " ");
			if (state.equals(ProcessState.BLOCKED))
			{
				blockedList.add(currentP);
				
				currentP.setReg1(rand.getNextInt());
				currentP.setReg2(rand.getNextInt());
				currentP.setReg3(rand.getNextInt());
				currentP.setReg4(rand.getNextInt());
				// TODO all processes are being blocked and a context switch
				// happens very time
				System.out.println("Context switch: Saving process " + currentP.getProcess().getPid());
				if (readyList.isEmpty())
				{
					System.out.println("Processes complete.");
					return;
				}
				currentP = readyList.get(0);
				readyList.remove(0);
				processor.setCurrentProcess(currentP.getProcess());
				processor.setCurInstruction(currentP.currentInstruction);
				processor.setRegisterValue(0, currentP.reg1);
				processor.setRegisterValue(1, currentP.reg2);
				processor.setRegisterValue(2, currentP.reg3);
				processor.setRegisterValue(3, currentP.reg4);
				cycles = 5;
				
				System.out.println("\tInstruction: " + currentP.currentInstruction + " R1: " + currentP.getReg1()
						+ " R2: " + currentP.getReg2() + " R3: " + currentP.getReg3() + " R4: " + currentP.getReg4()
						+ "\nRestoring process " + currentP.getProcess().getPid() + " Instruction: "
						+ currentP.currentInstruction + " R1: " + currentP.getReg1() + " R2: " + currentP.getReg2()
						+ " R3: " + currentP.getReg3() + " R4: " + currentP.getReg4());
				
			} else if (cycles < 1)// quantum expired
			{
				System.out.println("***Quantum expired**");
				readyList.add(currentP);
				// context switch
				currentP.setReg1(rand.getNextInt());
				currentP.setReg2(rand.getNextInt());
				currentP.setReg3(rand.getNextInt());
				currentP.setReg4(rand.getNextInt());
				System.out.println("Context switch: Saving process " + currentP.getProcess().getPid());
				if (readyList.isEmpty())
				{
					System.out.println("Processes complete.");
					return;
				}
				currentP = readyList.get(0);
				readyList.remove(0);
				processor.setCurrentProcess(currentP.getProcess());
				processor.setCurInstruction(currentP.currentInstruction);
				processor.setRegisterValue(0, currentP.reg1);
				processor.setRegisterValue(1, currentP.reg2);
				processor.setRegisterValue(2, currentP.reg3);
				processor.setRegisterValue(3, currentP.reg4);
				cycles = 5;
			} else if (state.equals(ProcessState.FINISHED))
			{
				// context switch
				currentP.setReg1(rand.getNextInt());
				currentP.setReg2(rand.getNextInt());
				currentP.setReg3(rand.getNextInt());
				currentP.setReg4(rand.getNextInt());
				System.out.println("Context switch: Saving process " + currentP.getProcess().getPid());
				if (readyList.isEmpty())
				{
					System.out.println("Processes complete.");
					return;
				}
				currentP = readyList.get(0);
				readyList.remove(0);
				processor.setCurrentProcess(currentP.getProcess());
				processor.setCurInstruction(currentP.currentInstruction);
				processor.setRegisterValue(0, currentP.reg1);
				processor.setRegisterValue(1, currentP.reg2);
				processor.setRegisterValue(2, currentP.reg3);
				processor.setRegisterValue(3, currentP.reg4);
				cycles = 5;
			} else
			{
				cycles--;
				System.out.println("Proc " + currentP.getProcess().getProcName() + " ID: "
						+ currentP.getProcess().getPid() + " Executing instruction " + processor.getCurInstruction());
				state = processor.executeNextInstruction();
			}
			// wake up blocked processes by 30%
			for (int j = 0; j < blockedList.size(); j++)
			{
				if (rand.getTrueWithProbability(.30))
				{
					readyList.add(blockedList.remove(j));
					j--;// if you remove something, everything is shifted down
						// and you end up skipping
				}
			}
		}
		
	}
	
}
