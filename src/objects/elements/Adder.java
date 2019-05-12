package objects.elements;

import java.util.ArrayList;

import objects.buses.Bus;

public class Adder extends Element {
	
	public Adder() {
		super("adder");
	}

	@Override
	public void signalChanged() {
		if (input.size() > 0 && output.size() > 0) {
			int incrementedInput = input.get(0).getState() + 1;
			System.out.println("Adder: output " + incrementedInput);
			output.get(0).setState(incrementedInput);
		}
		else {
			connectionError();
		}
	}
	
}
