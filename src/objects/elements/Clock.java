package objects.elements;

import java.util.ArrayList;

import objects.buses.Bus;

import javax.xml.crypto.Data;

public class Clock extends Element {

	private int state;
	private ArrayList<Thread> threads;
	
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

	public void createThreads() {
		for(int i = 0; i < this.output.get(0).getOutput().size(); i++) {
			Element element = output.get(0).getOutput().get(i);
			if(element.getType().equals("register")) {
				threads.add(new Thread((Register)element));
			}
			else if(element.getType().equals("register memory")){
				threads.add(new Thread((RegisterMemory)element));
			}
			else if(element.getType().equals("data memory")){
				threads.add(new Thread((DataMemory)element));
			}
		}
	}

	public void notifyRegisters() {
		for (int i = 0; i < output.size(); i++) {
			output.get(i).setState(this.state);
		}
	}

	public void threadTick() {
		if(threads == null) {
			createThreads();
		}
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
