package objects.elements;

import java.util.ArrayList;

import objects.buses.Bus;

public class InstructionMemory extends Element{
	
	private ArrayList<Integer> instructionData;
	
	public InstructionMemory() {
		super("instruction memory");
		this.instructionData = null; 
	}
	
	public InstructionMemory(ArrayList<Integer> instructionData) {
		super("instruction memory");
		this.instructionData = instructionData;
	}
	
	void setInstructionData(ArrayList<Integer> instructionData) {
		this.instructionData = instructionData;
	}

	@Override
	public void signalChanged() {
		if (instructionData == null) {
			System.out.println("Error: instruction memory not initialized");
		}
		else if(input.get(0).getState() >= instructionData.size()) {
			System.out.println("Error: instruction memory accessing instruction data out of bounds");
		}
		else {
			for (int i = 0; i < output.size(); i++) {
				System.out.println("Intruction Memory: accesed instruction at " + input.get(0).getState());
				output.get(i).setState(instructionData.get(input.get(0).getState()));
			}	
		}
	}

}
