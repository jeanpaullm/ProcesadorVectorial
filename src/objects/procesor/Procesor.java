package objects.procesor;

import java.util.ArrayList;

import objects.buses.Bus;
import objects.buses.BranchBus;
import objects.buses.Clk;
import objects.elements.*;

public class Procesor {
	
	private Bus clock;
	private ArrayList<Element> registers;
	
	public Procesor() {
		registers = new ArrayList<Element>();
		
		ArrayList<Integer> instructions = new ArrayList<Integer> ();
		instructions.add(0b00001_0001_00000000000000000000010);
		instructions.add(0b00000_0000_00000000000000000000000);
		instructions.add(0b00000_0000_00000000000000000000000);
		instructions.add(0b00000_0000_00000000000000000000000);
		instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);

        instructions.add(0b10000_0001_0001_0000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);

        instructions.add(0b10001_0000_0001_0001_000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);

        instructions.add(0b10010_0000_0001_0001_000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);
        instructions.add(0b00000_0000_00000000000000000000000);

        ArrayList<Integer> data = new ArrayList<Integer> ();
        data.add(0);
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(6);
        data.add(7);
        data.add(8);
        data.add(9);
        data.add(10);

		//this.clock = new Clock();
		this.clock = new Clk();
		//clock.addOutput(clockBus);
		
		//FETCH
		Element pc = new Register();
		Element adder = new Adder();
		Element instructionMemory = new InstructionMemory(instructions);
		
		Bus pcOutBus = new Bus();
		Bus adderOutBus  = new Bus();
		Bus instMemOutBus  = new Bus(); //->
		
		pc.addClock(clock);
		pc.addInput(adderOutBus);
		pc.addOutput(pcOutBus);
		
		adder.addInput(pcOutBus);
		adder.addOutput(adderOutBus);
		
		instructionMemory.addInput(pcOutBus);
		instructionMemory.addOutput(instMemOutBus);
		
		//DECODE
		Element fdRegister = new Register();
		Element branch = new Branch();
		Element registerMemory = new RegisterMemory();
		Element controlUnit = new ControlUnit();
		
		Bus fdRegisterOutBus = new Bus();
		Bus immediateDBus = new BranchBus(0,22); //->
		Bus a1DBus = new BranchBus(19,22);
        Bus a2DBus = new BranchBus(15,18);
		Bus a3DBus = new BranchBus(23,26);  //->
		Bus controlUnitInBus = new BranchBus(27,31);

		Bus writeEnableRegisterDBus = new Bus(); //->
        Bus writeEnableVectorDBus = new Bus(); //->
        Bus memWEDBus = new Bus(); //->
        Bus aluResultDBus = new Bus(); //->
        Bus aluControlDBus = new Bus(); //->

		Bus r1DBus = new Bus(); //->
        Bus rv10DBus = new Bus(); //->
        Bus rv11DBus = new Bus(); //->
        Bus rv12DBus = new Bus(); //->
        Bus rv13DBus = new Bus(); //->
        Bus rv14DBus = new Bus(); //->
        Bus rv15DBus = new Bus(); //->
        Bus rv16DBus = new Bus(); //->
        Bus rv17DBus = new Bus(); //->

        Bus r2DBus = new Bus(); //->
        Bus rv20DBus = new Bus(); //->
        Bus rv21DBus = new Bus(); //->
        Bus rv22DBus = new Bus(); //->
        Bus rv23DBus = new Bus(); //->
        Bus rv24DBus = new Bus(); //->
        Bus rv25DBus = new Bus(); //->
        Bus rv26DBus = new Bus(); //->
        Bus rv27DBus = new Bus(); //->
		
		fdRegister.addClock(clock);
		fdRegister.addInput(instMemOutBus);
		fdRegister.addOutput(fdRegisterOutBus);
		
		branch.addInput(fdRegisterOutBus);
		branch.addOutput(immediateDBus);
		branch.addOutput(a1DBus);
        branch.addOutput(a2DBus);
		branch.addOutput(a3DBus);
		branch.addOutput(controlUnitInBus);

		registerMemory.addClock(clock);
		registerMemory.addInput(a1DBus);
		registerMemory.addInput(a2DBus);
		registerMemory.addOutput(r1DBus);
		registerMemory.addOutput(rv10DBus);
        registerMemory.addOutput(rv11DBus);
        registerMemory.addOutput(rv12DBus);
        registerMemory.addOutput(rv13DBus);
        registerMemory.addOutput(rv14DBus);
        registerMemory.addOutput(rv15DBus);
        registerMemory.addOutput(rv16DBus);
        registerMemory.addOutput(rv17DBus);
        registerMemory.addOutput(r2DBus);
        registerMemory.addOutput(rv20DBus);
        registerMemory.addOutput(rv21DBus);
        registerMemory.addOutput(rv22DBus);
        registerMemory.addOutput(rv23DBus);
        registerMemory.addOutput(rv24DBus);
        registerMemory.addOutput(rv25DBus);
        registerMemory.addOutput(rv26DBus);
        registerMemory.addOutput(rv27DBus);

		controlUnit.addInput(controlUnitInBus);
		controlUnit.addOutput(writeEnableRegisterDBus);
        controlUnit.addOutput(writeEnableVectorDBus);
        controlUnit.addOutput(memWEDBus);
        controlUnit.addOutput(aluResultDBus);
        controlUnit.addOutput(aluControlDBus);
		
		//EXECUTE
		Element deRegister = new Register();

        Bus writeEnableRegisterEBus = new Bus(); //->
        Bus writeEnableVectorEBus = new Bus(); //->
		Bus a3EBus = new Bus(); //->
        Bus immediateEBus = new Bus(); //->
        Bus aluResultEBus = new Bus(); //->
        Bus memWEEBus = new Bus(); //->
        Bus r1EBus = new Bus(); //->
        Bus rv20EBus = new Bus(); //->
        Bus rv21EBus = new Bus(); //->
        Bus rv22EBus = new Bus(); //->
        Bus rv23EBus = new Bus(); //->
        Bus rv24EBus = new Bus(); //->
        Bus rv25EBus = new Bus(); //->
        Bus rv26EBus = new Bus(); //->
        Bus rv27EBus = new Bus(); //->
        Bus aluControlEBus = new Bus();
        Bus rv10EBus = new Bus();
        Bus rv11EBus = new Bus();
        Bus rv12EBus = new Bus();
        Bus rv13EBus = new Bus();
        Bus rv14EBus = new Bus();
        Bus rv15EBus = new Bus();
        Bus rv16EBus = new Bus();
        Bus rv17EBus = new Bus();
        Bus r2EBus = new Bus();

        Bus aluV0EBus = new Bus(); //->
        Bus aluV1EBus = new Bus(); //->
        Bus aluV2EBus = new Bus(); //->
        Bus aluV3EBus = new Bus(); //->
        Bus aluV4EBus = new Bus(); //->
        Bus aluV5EBus = new Bus(); //->
        Bus aluV6EBus = new Bus(); //->
        Bus aluV7EBus = new Bus(); //->


        deRegister.addClock(clock);

        deRegister.addInput(writeEnableRegisterDBus);
        deRegister.addInput(writeEnableVectorDBus);
        deRegister.addInput(a3DBus);
        deRegister.addInput(immediateDBus);
        deRegister.addInput(aluResultDBus);
        deRegister.addInput(memWEDBus);
        deRegister.addInput(r1DBus);
        deRegister.addInput(rv20DBus);
        deRegister.addInput(rv21DBus);
        deRegister.addInput(rv22DBus);
        deRegister.addInput(rv23DBus);
        deRegister.addInput(rv24DBus);
        deRegister.addInput(rv25DBus);
        deRegister.addInput(rv26DBus);
        deRegister.addInput(rv27DBus);
        deRegister.addInput(aluControlDBus);
        deRegister.addInput(rv10DBus);
        deRegister.addInput(rv11DBus);
        deRegister.addInput(rv12DBus);
        deRegister.addInput(rv13DBus);
        deRegister.addInput(rv14DBus);
        deRegister.addInput(rv15DBus);
        deRegister.addInput(rv16DBus);
        deRegister.addInput(rv17DBus);
        deRegister.addInput(r2DBus);

        deRegister.addOutput(writeEnableRegisterEBus);
        deRegister.addOutput(writeEnableVectorEBus);
		deRegister.addOutput(a3EBus);
        deRegister.addOutput(immediateEBus);
        deRegister.addOutput(aluResultEBus);
        deRegister.addOutput(memWEEBus);
        deRegister.addOutput(r1EBus);
        deRegister.addOutput(rv20EBus);
        deRegister.addOutput(rv21EBus);
        deRegister.addOutput(rv22EBus);
        deRegister.addOutput(rv23EBus);
        deRegister.addOutput(rv24EBus);
        deRegister.addOutput(rv25EBus);
        deRegister.addOutput(rv26EBus);
        deRegister.addOutput(rv27EBus);
        deRegister.addOutput(aluControlEBus);
        deRegister.addOutput(rv10EBus);
        deRegister.addOutput(rv11EBus);
        deRegister.addOutput(rv12EBus);
        deRegister.addOutput(rv13EBus);
        deRegister.addOutput(rv14EBus);
        deRegister.addOutput(rv15EBus);
        deRegister.addOutput(rv16EBus);
        deRegister.addOutput(rv17EBus);
        deRegister.addOutput(r2EBus);

        Bus branchEOut0Bus = new Bus();
        Bus branchEOut1Bus = new Bus();
        Bus branchEOut2Bus = new Bus();
        Bus branchEOut3Bus = new Bus();
        Bus branchEOut4Bus = new Bus();
        Bus branchEOut5Bus = new Bus();
        Bus branchEOut6Bus = new Bus();
        Bus branchEOut7Bus = new Bus();

        Element branchE = new Branch();
        branchE.addInput(r2EBus);
        branchE.addOutput(branchEOut0Bus);
        branchE.addOutput(branchEOut1Bus);
        branchE.addOutput(branchEOut2Bus);
        branchE.addOutput(branchEOut3Bus);
        branchE.addOutput(branchEOut4Bus);
        branchE.addOutput(branchEOut5Bus);
        branchE.addOutput(branchEOut6Bus);
        branchE.addOutput(branchEOut7Bus);

        Element vectorialAlu = new VectorialAlu();
        vectorialAlu.addControl(aluControlEBus);
        vectorialAlu.addInput(rv10EBus);
        vectorialAlu.addInput(rv11EBus);
        vectorialAlu.addInput(rv12EBus);
        vectorialAlu.addInput(rv13EBus);
        vectorialAlu.addInput(rv14EBus);
        vectorialAlu.addInput(rv15EBus);
        vectorialAlu.addInput(rv16EBus);
        vectorialAlu.addInput(rv17EBus);
        vectorialAlu.addInput(branchEOut0Bus);
        vectorialAlu.addInput(branchEOut1Bus);
        vectorialAlu.addInput(branchEOut2Bus);
        vectorialAlu.addInput(branchEOut3Bus);
        vectorialAlu.addInput(branchEOut4Bus);
        vectorialAlu.addInput(branchEOut5Bus);
        vectorialAlu.addInput(branchEOut6Bus);
        vectorialAlu.addInput(branchEOut7Bus);
        vectorialAlu.addOutput(aluV0EBus);
        vectorialAlu.addOutput(aluV1EBus);
        vectorialAlu.addOutput(aluV2EBus);
        vectorialAlu.addOutput(aluV3EBus);
        vectorialAlu.addOutput(aluV4EBus);
        vectorialAlu.addOutput(aluV5EBus);
        vectorialAlu.addOutput(aluV6EBus);
        vectorialAlu.addOutput(aluV7EBus);

        // -------------------------------- MEM  --------------------------------
 		Element emRegister = new Register();

        Bus writeEnableRegisterMBus = new Bus(); //->
        Bus writeEnableVectorMBus = new Bus(); //->
        Bus a3MBus = new Bus(); //->
        Bus immediateMBus = new Bus(); //->
        Bus aluControlMBus = new Bus(); //->
        Bus memWEMBus = new Bus();
        Bus r1MBus = new Bus();
        Bus rv20MBus = new Bus();
        Bus rv21MBus = new Bus();
        Bus rv22MBus = new Bus();
        Bus rv23MBus = new Bus();
        Bus rv24MBus = new Bus();
        Bus rv25MBus = new Bus();
        Bus rv26MBus = new Bus();
        Bus rv27MBus = new Bus();

        Bus dataMemV0MBus = new Bus(); //->
        Bus dataMemV1MBus = new Bus(); //->
        Bus dataMemV2MBus = new Bus(); //->
        Bus dataMemV3MBus = new Bus(); //->
        Bus dataMemV4MBus = new Bus(); //->
        Bus dataMemV5MBus = new Bus(); //->
        Bus dataMemV6MBus = new Bus(); //->
        Bus dataMemV7MBus = new Bus(); //->

        Bus aluV0MBus = new Bus(); //->
        Bus aluV1MBus = new Bus(); //->
        Bus aluV2MBus = new Bus(); //->
        Bus aluV3MBus = new Bus(); //->
        Bus aluV4MBus = new Bus(); //->
        Bus aluV5MBus = new Bus(); //->
        Bus aluV6MBus = new Bus(); //->
        Bus aluV7MBus = new Bus(); //->

        emRegister.addClock(clock);

        emRegister.addInput(writeEnableRegisterEBus);
        emRegister.addInput(writeEnableVectorEBus);
        emRegister.addInput(a3EBus);
        emRegister.addInput(immediateEBus);
        emRegister.addInput(aluControlEBus);
        emRegister.addInput(memWEEBus);
        emRegister.addInput(r1EBus);
        emRegister.addInput(rv20EBus);
        emRegister.addInput(rv21EBus);
        emRegister.addInput(rv22EBus);
        emRegister.addInput(rv23EBus);
        emRegister.addInput(rv24EBus);
        emRegister.addInput(rv25EBus);
        emRegister.addInput(rv26EBus);
        emRegister.addInput(rv27EBus);
        emRegister.addInput(aluV0EBus);
        emRegister.addInput(aluV1EBus);
        emRegister.addInput(aluV2EBus);
        emRegister.addInput(aluV3EBus);
        emRegister.addInput(aluV4EBus);
        emRegister.addInput(aluV5EBus);
        emRegister.addInput(aluV6EBus);
        emRegister.addInput(aluV7EBus);

        emRegister.addOutput(writeEnableRegisterMBus);
        emRegister.addOutput(writeEnableVectorMBus);
        emRegister.addOutput(a3MBus);
        emRegister.addOutput(immediateMBus);
        emRegister.addOutput(aluControlMBus);
        emRegister.addOutput(memWEMBus);
        emRegister.addOutput(r1MBus);
        emRegister.addOutput(rv20MBus);
        emRegister.addOutput(rv21MBus);
        emRegister.addOutput(rv22MBus);
        emRegister.addOutput(rv23MBus);
        emRegister.addOutput(rv24MBus);
        emRegister.addOutput(rv25MBus);
        emRegister.addOutput(rv26MBus);
        emRegister.addOutput(rv27MBus);
        emRegister.addOutput(aluV0MBus);
        emRegister.addOutput(aluV1MBus);
        emRegister.addOutput(aluV2MBus);
        emRegister.addOutput(aluV3MBus);
        emRegister.addOutput(aluV4MBus);
        emRegister.addOutput(aluV5MBus);
        emRegister.addOutput(aluV6MBus);
        emRegister.addOutput(aluV7MBus);

        Element dataMemory = new DataMemory(data);
        dataMemory.addClock(clock);
        dataMemory.addRegisterControl(memWEMBus);

        dataMemory.addInput(r1MBus);
        dataMemory.addRegisterInput(rv20MBus);
        dataMemory.addRegisterInput(rv21MBus);
        dataMemory.addRegisterInput(rv22MBus);
        dataMemory.addRegisterInput(rv23MBus);
        dataMemory.addRegisterInput(rv24MBus);
        dataMemory.addRegisterInput(rv25MBus);
        dataMemory.addRegisterInput(rv26MBus);
        dataMemory.addRegisterInput(rv27MBus);

        dataMemory.addOutput(dataMemV0MBus);
        dataMemory.addOutput(dataMemV1MBus);
        dataMemory.addOutput(dataMemV2MBus);
        dataMemory.addOutput(dataMemV3MBus);
        dataMemory.addOutput(dataMemV4MBus);
        dataMemory.addOutput(dataMemV5MBus);
        dataMemory.addOutput(dataMemV6MBus);
        dataMemory.addOutput(dataMemV7MBus);

		// -------------------------------- WRITEBACK  --------------------------------
		Element mwRegister = new Register();

        Bus writeEnableRegisterWBus = new Bus(); //->
        Bus writeEnableVectorWBus = new Bus(); //->
		Bus a3WBus = new Bus();
        Bus immediateWBus = new Bus();
        Bus aluControlWBus = new Bus();

        Bus dataMemV0WBus = new Bus();
        Bus dataMemV1WBus = new Bus();
        Bus dataMemV2WBus = new Bus();
        Bus dataMemV3WBus = new Bus();
        Bus dataMemV4WBus = new Bus();
        Bus dataMemV5WBus = new Bus();
        Bus dataMemV6WBus = new Bus();
        Bus dataMemV7WBus = new Bus();

        Bus aluV0WBus = new Bus();
        Bus aluV1WBus = new Bus();
        Bus aluV2WBus = new Bus();
        Bus aluV3WBus = new Bus();
        Bus aluV4WBus = new Bus();
        Bus aluV5WBus = new Bus();
        Bus aluV6WBus = new Bus();
        Bus aluV7WBus = new Bus();
		
		mwRegister.addClock(clock);

        mwRegister.addInput(writeEnableRegisterMBus);
        mwRegister.addInput(writeEnableVectorMBus);
        mwRegister.addInput(a3MBus);
        mwRegister.addInput(immediateMBus);
        mwRegister.addInput(aluControlMBus);
        mwRegister.addInput(dataMemV0MBus);
        mwRegister.addInput(dataMemV1MBus);
        mwRegister.addInput(dataMemV2MBus);
        mwRegister.addInput(dataMemV3MBus);
        mwRegister.addInput(dataMemV4MBus);
        mwRegister.addInput(dataMemV5MBus);
        mwRegister.addInput(dataMemV6MBus);
        mwRegister.addInput(dataMemV7MBus);
        mwRegister.addInput(aluV0MBus);
        mwRegister.addInput(aluV1MBus);
        mwRegister.addInput(aluV2MBus);
        mwRegister.addInput(aluV3MBus);
        mwRegister.addInput(aluV4MBus);
        mwRegister.addInput(aluV5MBus);
        mwRegister.addInput(aluV6MBus);
        mwRegister.addInput(aluV7MBus);

        mwRegister.addOutput(writeEnableRegisterWBus);
        mwRegister.addOutput(writeEnableVectorWBus);
        mwRegister.addOutput(a3WBus);
        mwRegister.addOutput(immediateWBus);
        mwRegister.addOutput(aluControlWBus);
        mwRegister.addOutput(dataMemV0WBus);
        mwRegister.addOutput(dataMemV1WBus);
        mwRegister.addOutput(dataMemV2WBus);
        mwRegister.addOutput(dataMemV3WBus);
        mwRegister.addOutput(dataMemV4WBus);
        mwRegister.addOutput(dataMemV5WBus);
        mwRegister.addOutput(dataMemV6WBus);
        mwRegister.addOutput(dataMemV7WBus);
        mwRegister.addOutput(aluV0WBus);
        mwRegister.addOutput(aluV1WBus);
        mwRegister.addOutput(aluV2WBus);
        mwRegister.addOutput(aluV3WBus);
        mwRegister.addOutput(aluV4WBus);
        mwRegister.addOutput(aluV5WBus);
        mwRegister.addOutput(aluV6WBus);
        mwRegister.addOutput(aluV7WBus);

        Bus rv30WBus = new Bus();
        Bus rv31WBus = new Bus();
        Bus rv32WBus = new Bus();
        Bus rv33WBus = new Bus();
        Bus rv34WBus = new Bus();
        Bus rv35WBus = new Bus();
        Bus rv36WBus = new Bus();
        Bus rv37WBus = new Bus();

        Element vectorialMux = new VectorialMux();
        vectorialMux.addControl(aluControlWBus);
        vectorialMux.addInput(dataMemV0WBus);
        vectorialMux.addInput(dataMemV1WBus);
        vectorialMux.addInput(dataMemV2WBus);
        vectorialMux.addInput(dataMemV3WBus);
        vectorialMux.addInput(dataMemV4WBus);
        vectorialMux.addInput(dataMemV5WBus);
        vectorialMux.addInput(dataMemV6WBus);
        vectorialMux.addInput(dataMemV7WBus);
        vectorialMux.addInput(aluV0WBus);
        vectorialMux.addInput(aluV1WBus);
        vectorialMux.addInput(aluV2WBus);
        vectorialMux.addInput(aluV3WBus);
        vectorialMux.addInput(aluV4WBus);
        vectorialMux.addInput(aluV5WBus);
        vectorialMux.addInput(aluV6WBus);
        vectorialMux.addInput(aluV7WBus);
        vectorialMux.addOutput(rv30WBus);
        vectorialMux.addOutput(rv31WBus);
        vectorialMux.addOutput(rv32WBus);
        vectorialMux.addOutput(rv33WBus);
        vectorialMux.addOutput(rv34WBus);
        vectorialMux.addOutput(rv35WBus);
        vectorialMux.addOutput(rv36WBus);
        vectorialMux.addOutput(rv37WBus);

		registerMemory.addRegisterControl(writeEnableRegisterWBus);
        registerMemory.addRegisterControl(writeEnableVectorWBus);
		registerMemory.addRegisterInput(a3WBus);
		registerMemory.addRegisterInput(immediateWBus);
        registerMemory.addRegisterInput(rv30WBus);
        registerMemory.addRegisterInput(rv31WBus);
        registerMemory.addRegisterInput(rv32WBus);
        registerMemory.addRegisterInput(rv33WBus);
        registerMemory.addRegisterInput(rv34WBus);
        registerMemory.addRegisterInput(rv35WBus);
        registerMemory.addRegisterInput(rv36WBus);
        registerMemory.addRegisterInput(rv37WBus);

		registers.add(pc);
		registers.add(fdRegister);
		registers.add(deRegister);
		registers.add(emRegister);
		registers.add(mwRegister);
	}

	public Clk getClock() {
		return (Clk)(this.clock);
	}
	
	public void debug() {
		for(int i = 0; i < registers.size(); i++) {
			registers.get(i).printState();
		}
	}
	
}
