package objects.elements;

import objects.buses.Bus;

import java.util.ArrayList;

public class VectorialMux extends Element {

    public VectorialMux() {
        super("vectorial mux");
        this.control = new ArrayList<Bus>();
    }

    @Override
    public void signalChanged(){
		if(control.size() == 1 && input.size() % 8 == 0  && output.size() == 8) {
		    if(control.get(0).getState() < input.size()/16) {
		        int offset = control.get(0).getState() * 8;
		        for(int i = 0; i < 8; i++) {
		            output.get(i).setState(input.get(i + offset).getState());
                }
            }
		    else {
                System.out.println("Error: vectorial mux control out of bounds");
            }
		}
		else {
			connectionError();
		}
    }
}
