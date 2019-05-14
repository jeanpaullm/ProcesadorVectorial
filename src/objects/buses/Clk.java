package objects.buses;

import objects.elements.DataMemory;
import objects.elements.Element;
import objects.elements.Register;
import objects.elements.RegisterMemory;

import java.util.ArrayList;

public class Clk extends Bus {


    int counter = 0;
    private boolean running;
    private boolean threaded;
    private boolean debug;
    private ArrayList<Thread> threads;

    public Clk() {
        super();
        this.state = 0;
        this.threads = new ArrayList<Thread>();
        this.threaded = false;
        this.running = true;
        this.debug = false;
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
        if(running) {
            if (this.state == 0) {
                this.state = 1;
                clockChanged();
            } else {
                this.state = 0;
                this.counter++;
                clockChanged();
            }
        }
        else {
            System.out.println("Clock: Stopped");
        }
    }

    public void clockChanged() {
        if (output.size() > 0) {
            if(debug) {
                System.out.println("----- clock tick " + state + " -----");
            }
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

    public void stop() {
        this.running = false;
        System.out.println("Execution ended in " + counter + " clock cicles");
        System.out.println("At 1GHz it would have taken " + counter * 0.000001  + " miliseconds");
    }

    public boolean isRunning() {
        return this.running;
    }

    public void setDebug(boolean debug){
        this.debug = debug;
    }


}
