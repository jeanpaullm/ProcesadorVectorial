package objects.elements;

import java.util.ArrayList;

import objects.buses.Bus;

/*
 * input op code
 * output WER
 * output WEV
 * 
 */
public class ControlUnit extends Element {

	public ControlUnit() {
		super("control unit");
	}

	@Override
	public void signalChanged() {
		if(input.size() == 1 && output.size()  == 5) {
			int opCode = input.get(0).getState();
			if(opCode == 0) {
				System.out.println("NOP");
				this.output.get(0).setState(0); //WER
				this.output.get(1).setState(0); //WEV
                this.output.get(2).setState(0); //WED
				this.output.get(3).setState(0); //ALU_RESULT
				this.output.get(4).setState(0); //ALU_OP
			}	
			else if(opCode == 1) {
				System.out.println("DEF");
				this.output.get(0).setState(1); //WER
				this.output.get(1).setState(0); //WEV
                this.output.get(2).setState(0); //WED
				this.output.get(3).setState(0); //ALU_RESULT
				this.output.get(4).setState(0); //ALU_OP
			}
			else if(opCode == 16) {
				System.out.println("LV");
				this.output.get(0).setState(0); //WER
				this.output.get(1).setState(1); //WEV
                this.output.get(2).setState(0); //WED
				this.output.get(3).setState(0); //ALU_RESULT
				this.output.get(4).setState(0); //ALU_OP
			}
            else if(opCode == 17) {
                System.out.println("SV");
                this.output.get(0).setState(0); //WER
                this.output.get(1).setState(0); //WEV
                this.output.get(2).setState(1); //WED
				this.output.get(3).setState(0); //ALU_RESULT
				this.output.get(4).setState(0); //ALU_OP
            }
			else if(opCode == 18) {
				System.out.println("XORV");
				this.output.get(0).setState(0); //WER
				this.output.get(1).setState(1); //WEV
				this.output.get(2).setState(0); //WED
				this.output.get(3).setState(1); //ALU_RESULT
				this.output.get(4).setState(4); //ALU_OP
			}
			else {
				System.out.println("Error: control unit invalid op code");
			}	
		}	
		else {
			connectionError();
		}	
	}
	
}
	
