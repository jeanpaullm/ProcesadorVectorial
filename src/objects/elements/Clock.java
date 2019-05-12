package objects.elements;

import java.util.ArrayList;

import objects.buses.Bus;

public class Clock extends Element {

	private int state;
	
	public Clock() {
		super("clock");
		this.state = 0;
	}
	
	@Override
	public void signalChanged() {
		if (output.size() > 0) {
			System.out.println("\n");
			System.out.println("----- clock tick " + state + " -----");
			for (int i = 0; i < output.size(); i++) {
				output.get(i).setState(this.state);
			}
		}
		else {
			connectionError();
		}
	}
	
	public void tick() {
		if(this.state == 0 ) {
			this.state = 1;
			signalChanged();
		}
		else {
			this.state = 0;
			signalChanged();
		}
	}
	
	public void tickTack() {
		tick();
		tick();
	}
	
}
