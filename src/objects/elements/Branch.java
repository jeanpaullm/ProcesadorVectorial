package objects.elements;

import java.util.ArrayList;

import objects.buses.BranchBus;
import objects.buses.Bus;

public class Branch extends Element {
	
	public Branch() {
		super("branch");
	}

	@Override
	public void signalChanged() {
		if (input.size() > 0 && output.size() > 0) {
			int inputState = input.get(0).getState();
		    for(int i = 0; i < output.size(); i++) {
		        output.get(i).setState(inputState);
		    }
		}
		else {
			System.out.println("Error: Branch conections not defined properly.");
		}
	}

}
