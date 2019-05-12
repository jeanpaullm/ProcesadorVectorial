package objects.buses;

import objects.elements.DataMemory;
import objects.elements.Element;
import objects.elements.Register;
import objects.elements.RegisterMemory;

import java.util.ArrayList;

public class Clk extends Bus {

    private boolean threaded;
    private ArrayList<Thread> threads;

    public Clk() {
        super();
        this.state = 0;
        this.threads = new ArrayList<Thread>();
        this.threaded = false;
    }
    public void createThreads() {
        for(int i = 0; i < this.output.size(); i++) {
            Element element = output.get(i);
            if(element.getType().equals("register")) {
                threads.add(new Thread((Register)element));
            }
            else if(element.getType().equals("register memory")){
                threads.add(new Thread((RegisterMemory)element));
            }
            else if(element.getType().equals("data memory")){
                threads.add(new Thread((DataMemory)element));
            }
        }
        this.threaded = true;
    }

    public void tickTack() {
        tick();
        tick();
    }

    public void tick() {
        if(this.state == 0 ) {
            this.state = 1;
            clockChanged();
        }
        else {
            this.state = 0;
            clockChanged();
        }
    }

    public void clockChanged() {
        if (output.size() > 0) {
            System.out.println("\n");
            System.out.println("----- clock tick " + state + " -----");
            if (threaded) {
                for (int i = 0; i < threads.size(); i++) {
                    threads.get(i).run();
                }
                for (int i = 0; i < threads.size(); i++) {
                    try {
                        threads.get(i).join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                for (int i = 0; i < output.size(); i++) {
                    output.get(i).signalChanged();
                }
            }
        }
        else {
            System.out.println("Error: Clk conections not defined properly");
        }
    }

}
