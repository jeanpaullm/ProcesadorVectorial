package objects.elements;

import java.util.ArrayList;

import objects.buses.Bus;

public class Register extends Element implements Runnable {

	private ArrayList<Integer> data;
	
	public Register() {
		super("register");
		this.data = new ArrayList<Integer>();
	}

	@Override
	public void signalChanged() {
		if(this.clock != null && this.input.size() > 0 &&
		   this.output.size() > 0 && this.input.size() == this.output.size()) {
			if(clock.getState() == 1) { //save data
				System.out.println("Register: storing data.");
				data.clear();
				for(int i = 0; i < input.size(); i++) {
					this.data.add(this.input.get(i).getState());
				}
			}
			else {//write data
				System.out.println("Register: sending data.");
				for(int i = 0; i < output.size(); i++) {
					this.output.get(i).setState(data.get(i));
				}
			}
		}
		else {
			connectionError();
		}
		
	}

	@Override
	public void addInput(Bus bus) {
		this.input.add(bus);
	}
	
	@Override
	public void printState() {
		System.out.println("\n");
		System.out.println("--Register state--");
		System.out.print(" Inputs:  ");
		for(int i = 0; i < input.size(); i++) {
			System.out.print(this.input.get(i).getState() + " ");
		}
		System.out.print(" Data:  ");
		for(int i = 0; i < data.size(); i++) {
			System.out.print(this.data.get(i) + " ");
		}
		System.out.print(" Outputs:  ");
		for(int i = 0; i < output.size(); i++) {
			System.out.print(this.output.get(i).getState() + " ");
		}
	}

	@Override
	public void run() {
		signalChanged();
	}

	public ArrayList<Bus> getInputs() {
	  return input;
  }

  public ArrayList<Integer> getData() {
	  return data;
  }

}
