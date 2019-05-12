package objects.elements;

import java.util.ArrayList;

import objects.buses.Bus;

/** Alu
 *  two input 
 * 	  n output
 * 
 * @author jeanpaul
 *
 */
public class Alu extends Element {
	
	public Alu() {
		super("alu");

	}

	@Override
	public void signalChanged() {
	    int state;
	    if(true) {
	        state = input.get(0).getState() + input.get(1).getState();
	    }
	    for(int i = 0; i < output.size(); i++) {
	        output.get(i).setState(state);
	    }
	}
	
}
