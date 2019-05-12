package objects.buses;

import java.util.ArrayList;

import objects.elements.Element;

public class Bus {
	
	protected int state;
	protected ArrayList<Element> output;
	
	public Bus() {
		output = new ArrayList<Element>();
	}
	
	public int getState() {
		return this.state;
	}
	
	public void setState(int state) {
		this.state = state;
		notifyChanges();
	}
	
	protected void notifyChanges() {
		for(int i = 0; i < output.size(); i++) {
			//System.out.println("Bus: notifiyng " + output.get(i).getType());
			output.get(i).signalChanged();
		}
		//System.out.println();
	}
	
	public void addOutput(Element element) {
		output.add(element);
	}

	public ArrayList<Element> getOutput() {
		return this.output;
	}

}
