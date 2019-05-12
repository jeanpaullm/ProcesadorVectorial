package objects.elements;

import objects.buses.Bus;

import java.util.ArrayList;

public class VectorialAlu extends Element {

    public VectorialAlu() {
        super("vectorial alu");
        this.control = new ArrayList<Bus>();
    }

    @Override
    public void signalChanged() {
        if(control.size() == 1 && input.size() == 16 && output.size() == 8){
            if(control.get(0).getState() == 1) { //ADDV
                for(int i = 0; i < 8; i++) {
                    output.get(i).setState((input.get(i).getState()+input.get(i+8).getState())%256);
                }
            }
            if(control.get(0).getState() == 1) { //SUBV
                for(int i = 0; i < 8; i++) {
                    output.get(i).setState((input.get(i).getState()-input.get(i+8).getState())%256);
                }
            }
            if(control.get(0).getState() == 4) { //XOR
                for(int i = 0; i < 8; i++) {
                    output.get(i).setState(input.get(i).getState()^input.get(i+8).getState());
                }
            }
            else {
                //System.out.println("Vectorial Alu: invalid control signal");
            }
        }
        else {
            connectionError();
        }
    }

}
