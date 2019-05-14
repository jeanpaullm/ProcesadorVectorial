package objects.elements;

import java.util.ArrayList;

import objects.buses.Bus;

/**	needs control for Write-Enable
 * 	input A1
 * 	input A2
 *  input A3
 *  input WD3
 *  input vect0
 *  input vect1
 *  input vect2
 *  input vect3
 *  input vect4
 *  input vect5
 *  input vect6
 *  input vect7
 *  
 *  control WER
 *  control WEV
 *  
 *  output RD1
 *  output RD2
 *	
 * @author jeanpaul
 *
 */
public class RegisterMemory extends Element implements Runnable {

	private boolean debug;
	private ArrayList<Integer> registers; //8 registros
	private ArrayList<ArrayList<Integer>> vectorialRegisters; // 8 VectorialRegister

	public RegisterMemory() {
		super("register memory");
		this.control = new ArrayList<Bus>();
		this.registers = new ArrayList<Integer>();
		this.debug = false;
		for (int i = 0; i < 8; i++) {
			registers.add(0);
		}
		this.vectorialRegisters = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 8; i++) {
			ArrayList<Integer> vector = new ArrayList<>();
			for (int j = 0; j < 8; j++) {
				vector.add(0);
			}
			vectorialRegisters.add(vector);
		}
	}

	@Override
	public void signalChanged() {
		if(this.input.size() == 12 && this.output.size() == 18 && this.control.size() == 2 && this.clock != null) {
			//storing in registers
			if(this.clock.getState() == 1) {
				if(this.control.get(0).getState() == 1) {
					registers.set(input.get(2).getState(), input.get(3).getState());
					if(debug) {
						System.out.println("Register Memory: value " + input.get(3).getState() + " written in register " + input.get(2).getState());
					}
				}
				if(this.control.get(1).getState() == 1) {
					int vectorialIndex = input.get(2).getState();
					for(int i = 0; i < 8; i++) {
						vectorialRegisters.get(vectorialIndex-8).set(i, input.get(4 + i).getState());
						if(debug) {
							System.out.println("Register Memory: value written in vectorial reg " + input.get(2).getState());
						}
					}
				}
			}
			//reading from registers
			if (this.input.get(0).getState() < 8) {
				this.output.get(0).setState(registers.get(input.get(0).getState())); //output R1D
				for (int i = 0; i < 8; i++) {
					this.output.get(i + 1).setState(0);                              //set RV1D to zero
				}
			}
			else if(this.input.get(0).getState() < 16){
				this.output.get(0).setState(0); 									// set R1D to zero
				for (int i = 0; i < 8; i++) {
					this.output.get(i + 1).setState(vectorialRegisters.get(input.get(0).getState()-8).get(i)); //output RV1D
				}
			}
			else {
				System.out.println("Register Memory: A1 out of bounds");
			}
			if (this.input.get(1).getState() < 8) {
				this.output.get(9).setState(registers.get(input.get(1).getState())); //output R2D
				for (int i = 0; i < 8; i++) {
					this.output.get(i + 10).setState(0);                              //set RV2D to zero
				}
			}
			else if(this.input.get(1).getState() < 16){
				this.output.get(9).setState(0); 									// set R2D to zero
				for (int i = 0; i < 8; i++) {
					this.output.get(i + 10).setState(vectorialRegisters.get(input.get(1).getState() - 8).get(i)); //output RV2D
				}
			}
			else {
				System.out.println("Register Memory: A2 out of bounds");
			}
		}
		else {
			connectionError();
		}
	}

	public ArrayList<Integer> getRegisters() {
		return this.registers;
	}

	public ArrayList<ArrayList<Integer>> getVectorialRegisters() {
		return this.vectorialRegisters;
	}

	@Override
	public void run() {
		signalChanged();
	}

	public void setDebug(boolean debug){
		this.debug = debug;
	}

}
