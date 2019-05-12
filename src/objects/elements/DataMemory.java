package objects.elements;

import objects.buses.Bus;

import java.util.ArrayList;

/*
 *
 * input r1m
 *
 * output vec0
 * output vec1
 * output vec2
 * output vec3
 * output vec4
 * output vec5
 * output vec6
 * output vec7
 *
 */
public class DataMemory extends Element {

    private ArrayList<Integer> data;

    public DataMemory() {
        super("data memory");
        this.data = null;
        this.control = new ArrayList<Bus>();
    }

    public DataMemory(ArrayList<Integer> data) {
        super("data memory");
        this.data = data;
        this.control = new ArrayList<Bus>();
    }

    void setInstructionData(ArrayList<Integer> data) {
        this.data = data;
    }

    @Override
    public void signalChanged() {
        if (data == null) {
            System.out.println("Error: data memory not initialized");
        }
        else if(input.size() == 9 && output.size() == 8 && control.size() == 1 && clock != null) {
            int baseIndex = input.get(0).getState();
            for(int i = 0; i < 8; i++) {
                if(baseIndex + 1 < data.size()) {
                    output.get(i).setState(data.get(baseIndex + i));
                }
                else {
                    System.out.println("Error: data memory accessing instruction data out of bounds");
                }
            }

            if(clock.getState() == 1 && control.get(0).getState() == 1) { //SV
                System.out.println(this.type  + " storing vector at " + baseIndex);
                for(int i = 0; i < 8; i++) {
                    data.set(baseIndex + 1, input.get(i + 1).getState());
                }
            }

        }
        else {
            connectionError();
        }
    }

}
