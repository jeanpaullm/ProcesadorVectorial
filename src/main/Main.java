package main;

import objects.procesor.Procesor;

public class Main {

	public static void main(String[] args) {
		
		/*
	     
		Bus aluin1 = new Bus();
	    Bus aluin2 = new Bus();
	    Bus aluout = new Bus();
	    Element alu = new Alu();
	    alu.addInput(aluin1);
	    alu.addInput(aluin2);
	    alu.addOutput(aluout);

	    aluin1.setState(10);
	    System.out.println(aluout.getState());
	    
	    aluin2.setState(20);
	    System.out.println(aluout.getState());
	    
		Bus mux0 = new Bus();
	    Bus mux1 = new Bus();
	    Bus mux2 = new Bus();
	    Bus muxControl = new Bus();
	    Bus muxOut = new Bus();
	    Element mux = new MUX();
	    mux.addInput(mux0);
	    mux.addInput(mux1);
	    mux.addInput(mux2);
	    mux.addControl(muxControl);
	    mux.addOutput(muxOut);
	    
	    mux0.setState(-50);
	    System.out.println(muxOut.getState());
	    
	    mux1.setState(50);
	    System.out.println(muxOut.getState());
	    
	    mux2.setState(100);
	    System.out.println(muxOut.getState());
	    
	    muxControl.setState(0);
	    System.out.println(muxOut.getState());
	    
	    muxControl.setState(1);
	    System.out.println(muxOut.getState());
	    
	    muxControl.setState(2);
	    System.out.println(muxOut.getState());
	    
		Bus clk = new Bus();
	    Bus pcIn = new Bus();
	    Bus pcOut = new Bus();

	    
	    pc.addInput(pcIn);
	    pc.addOutput(pcOut);
	    pc.addClock(clk);
	    
	    System.out.println();
	    System.out.println(clk.getState());
	    System.out.println(pcIn.getState());
	    System.out.println(pcOut.getState());
	    
	    pcIn.setState(123);
	    
	    System.out.println();
	    System.out.println(clk.getState());
	    System.out.println(pcIn.getState());
	    System.out.println(pcOut.getState());
	    
	    clk.setState(1);
	    
	    System.out.println();
	    System.out.println(clk.getState());
	    System.out.println(pcIn.getState());
	    System.out.println(pcOut.getState());
	    
	    clk.setState(0);
	    
	    System.out.println();
	    System.out.println(clk.getState());
	    System.out.println(pcIn.getState());
	    System.out.println(pcOut.getState());*/
		/*
		Element adder1 = new Adder();

		Element adder2 = new Adder();
		
		Bus pcAdder1Bus = new Bus();
		Bus adderPC1Bus  = new Bus();
		
		Bus pcAdder2Bus = new Bus();
		Bus adderPC2Bus  = new Bus();
		
		pc1.addOutput(pcAdder1Bus);
		
		adder1.addInput(pcAdder1Bus);
		adder1.addOutput(adderPC1Bus);
		
		pc2.addInput(adderPC1Bus);
		pc2.addOutput(pcAdder2Bus);
		
		adder2.addInput(pcAdder2Bus);
		adder2.addOutput(adderPC2Bus);
		
		pc1.addInput(adderPC2Bus);
				
		Element clock = new Clock();
		clock.addOutput(clockBus);
		
		pc1.addClock(clockBus);
		pc2.addClock(clockBus);
		
	    System.out.println(clockBus.getState());
	    System.out.println(pcAdder1Bus.getState());
	    System.out.println(adderPC1Bus.getState());
	    System.out.println(pcAdder2Bus.getState());
	    System.out.println(adderPC2Bus.getState());
	    System.out.println();
	    
	    ((Clock) clock).tick();
	    System.out.println(clockBus.getState());
	    System.out.println(pcAdder1Bus.getState());
	    System.out.println(adderPC1Bus.getState());
	    System.out.println(pcAdder2Bus.getState());
	    System.out.println(adderPC2Bus.getState());
	    System.out.println();
	    
	    ((Clock) clock).tick();
	    System.out.println(clockBus.getState());
	    System.out.println(pcAdder1Bus.getState());
	    System.out.println(adderPC1Bus.getState());
	    System.out.println(pcAdder2Bus.getState());
	    System.out.println(adderPC2Bus.getState());
	    System.out.println();
	    
	    ((Clock) clock).tick();
	    System.out.println(clockBus.getState());
	    System.out.println(pcAdder1Bus.getState());
	    System.out.println(adderPC1Bus.getState());
	    System.out.println(pcAdder2Bus.getState());
	    System.out.println(adderPC2Bus.getState());
	    System.out.println();
	    
	    ((Clock) clock).tick();
	    System.out.println(clockBus.getState());
	    System.out.println(pcAdder1Bus.getState());
	    System.out.println(adderPC1Bus.getState());
	    System.out.println(pcAdder2Bus.getState());
	    System.out.println(adderPC2Bus.getState());
	    System.out.println();
	    
	    ((Clock) clock).tick();
	    System.out.println(clockBus.getState());
	    System.out.println(pcAdder1Bus.getState());
	    System.out.println(adderPC1Bus.getState());
	    System.out.println(pcAdder2Bus.getState());
	    System.out.println(adderPC2Bus.getState());
	    System.out.println();	
	    
	    ((Clock) clock).tick();
	    System.out.println(clockBus.getState());
	    System.out.println(pcAdder1Bus.getState());
	    System.out.println(adderPC1Bus.getState());
	    System.out.println(pcAdder2Bus.getState());
	    System.out.println(adderPC2Bus.getState());
	    System.out.println();	
	    
	    
	    //System.out.println(Integer.toBinaryString(Integer.reverse(123)));
	    //System.out.println(Integer.toBinaryString(Integer.reverse(123)).substring(0, 4));
	   // System.out.println(Integer.parseInt(Integer.toBinaryString(123).substring(5, 8),2));
	    
	    Bus segBus = new SelectorBus(4,7);
	    System.out.println(Integer.toBinaryString(0b11110000));
	    segBus.setState(0b11110000);
	    */
	    //Integer
		
		Procesor procesor = new Procesor();
		procesor.debug();
		procesor.getClock().createThreads();
		procesor.getClock().tickTack();
		procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();
        procesor.getClock().tickTack();
        procesor.debug();

	}

}
