package objects.elements;

import java.util.ArrayList;

import objects.buses.Bus;

public abstract class Element {

	protected String type;
	protected ArrayList<Bus> control;
	protected Bus clock;
	protected ArrayList<Bus> input;
	protected ArrayList<Bus> output;

	public Element(String type) {
		this.type = type;
		this.input   = new ArrayList<Bus>();
		this.output  = new ArrayList<Bus>();
		this.control = null;
		this.clock   = null;
	}
	
	public String getType() {
		return this.type;
	}
	
	public boolean hasClock() {
		return clock != null;
	}
	
	public void addClock(Bus bus) {
		this.clock = bus;
		bus.addOutput(this);
	}
	
	public void addControl(Bus bus) {
		this.control.add(bus);
		bus.addOutput(this);
	}

	public void addRegisterControl(Bus bus) {
		this.control.add(bus);
	}
	
	public void addInput(Bus bus) {
	    this.input.add(bus);
	    bus.addOutput(this);	    
	}

	public void addRegisterInput(Bus bus) {
		this.input.add(bus);
	}

	public void addOutput(Bus bus) {
		this.output.add(bus);
	}
	
	public abstract void signalChanged();

	public void connectionError() {
		System.out.println("Error: " + this.type + " connections not defined properly.");
	}

	public void printState() {
		System.out.println("Element: printState not implemented for " + this.type);
	}
		
}
