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
            if(control.get(0).getState() == 2) { //SUBV
                for(int i = 0; i < 8; i++) {
                    output.get(i).setState((input.get(i).getState()-input.get(i+8).getState())%256);
                }
            }
            if(control.get(0).getState() == 4) { //XOR
                for(int i = 0; i < 8; i++) {
                    output.get(i).setState(input.get(i).getState()^input.get(i+8).getState());
                }
            }
            if(control.get(0).getState() == 9) { //SRCV
                for(int i = 0; i < 8; i++) {
                    String bits = Integer.toBinaryString(input.get(i).getState());
                    while(bits.length() < 8) {
                        bits = "0" + bits;
                    }
                    for(int j = 0; j < input.get(i+8).getState(); j++) {//derecha
                        if(bits.charAt(7) == '0'){
                            bits = "0" + bits;
                        }
                        else {
                            bits = "1" + bits;
                        }
                        bits = bits.substring(0,8);
                    }
                    output.get(i).setState(Integer.parseInt(bits,2));
                }
            }
            if(control.get(0).getState() == 10) { //SLCV
                for(int i = 0; i < 8; i++) {
                    String bits = Integer.toBinaryString(input.get(i).getState());
                    while(bits.length() < 8) {
                        bits = "0" + bits;
                    }
                    for(int j = 0; j < input.get(i+8).getState(); j++) {//izquierda
                        if(bits.charAt(0) == '0'){
                            bits = bits + "0";
                        }
                        else {
                            bits = bits + "1";
                        }
                        bits = bits.substring(1,9);
                    }
                    output.get(i).setState(Integer.parseInt(bits,2));
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
