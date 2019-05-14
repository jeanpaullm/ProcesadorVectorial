package objects.procesor;

import java.util.ArrayList;
import java.util.Scanner;

import objects.buses.Bus;
import objects.buses.BranchBus;
import objects.buses.Clk;
import objects.elements.*;

public class Procesor {

  private boolean debug;
  private Bus clock;
  private Register pc;
  private Register fdRegister;
  private Register deRegister;
  private Register emRegister;
  private Register mwRegister;
  private RegisterMemory registerMemory;


  public Procesor(ArrayList<Integer> instructions, ArrayList<Integer> data) {

    this.debug = false;
    this.clock = new Clk();

    // -------------------------------- FETCH  --------------------------------
    this.pc = new Register();
    Element adder = new Adder();
    Element instructionMemory = new InstructionMemory(instructions);

    Bus pcOutBus = new Bus();
    Bus adderOutBus = new Bus();
    Bus instMemOutBus = new Bus(); //->

    pc.addClock(clock);
    pc.addInput(adderOutBus);
    pc.addOutput(pcOutBus);

    adder.addInput(pcOutBus);
    adder.addOutput(adderOutBus);

    instructionMemory.addInput(pcOutBus);
    instructionMemory.addOutput(instMemOutBus);

    // -------------------------------- DECODE  --------------------------------
    this.fdRegister = new Register();
    Element branch = new Branch();
    this.registerMemory = new RegisterMemory();
    Element controlUnit = new ControlUnit();

    Bus fdRegisterOutBus = new Bus();
    Bus immediateDBus = new BranchBus(0, 22); //->
    Bus a1DBus = new BranchBus(19, 22);
    Bus a2DBus = new BranchBus(15, 18);
    Bus a3DBus = new BranchBus(23, 26);  //->
    Bus controlUnitInBus = new BranchBus(27, 31);

    Bus writeEnableRegisterDBus = new Bus(); //->
    Bus writeEnableVectorDBus = new Bus(); //->
    Bus memWEDBus = new Bus(); //->
    Bus aluResultDBus = new Bus(); //->
    Bus aluControlDBus = new Bus(); //->
    Bus aluMuxDBus = new Bus();//->

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

    controlUnit.addClock(clock);
    controlUnit.addInput(controlUnitInBus);
    controlUnit.addOutput(writeEnableRegisterDBus);
    controlUnit.addOutput(writeEnableVectorDBus);
    controlUnit.addOutput(memWEDBus);
    controlUnit.addOutput(aluResultDBus);
    controlUnit.addOutput(aluControlDBus);
    controlUnit.addOutput(aluMuxDBus);

    // -------------------------------- EXECUTE  --------------------------------
    this.deRegister = new Register();

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
    Bus aluMuxEBus = new Bus();
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
    deRegister.addInput(aluMuxDBus);
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
    deRegister.addOutput(aluMuxEBus);
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

    Bus aluMux0Bus = new Bus();
    Bus aluMux1Bus = new Bus();
    Bus aluMux2Bus = new Bus();
    Bus aluMux3Bus = new Bus();
    Bus aluMux4Bus = new Bus();
    Bus aluMux5Bus = new Bus();
    Bus aluMux6Bus = new Bus();
    Bus aluMux7Bus = new Bus();

    Element aluMux = new VectorialMux();
    aluMux.addControl(aluMuxEBus);
    aluMux.addInput(branchEOut0Bus);
    aluMux.addInput(branchEOut1Bus);
    aluMux.addInput(branchEOut2Bus);
    aluMux.addInput(branchEOut3Bus);
    aluMux.addInput(branchEOut4Bus);
    aluMux.addInput(branchEOut5Bus);
    aluMux.addInput(branchEOut6Bus);
    aluMux.addInput(branchEOut7Bus);
    aluMux.addInput(rv20EBus);
    aluMux.addInput(rv21EBus);
    aluMux.addInput(rv22EBus);
    aluMux.addInput(rv23EBus);
    aluMux.addInput(rv24EBus);
    aluMux.addInput(rv25EBus);
    aluMux.addInput(rv26EBus);
    aluMux.addInput(rv27EBus);
    aluMux.addOutput(aluMux0Bus);
    aluMux.addOutput(aluMux1Bus);
    aluMux.addOutput(aluMux2Bus);
    aluMux.addOutput(aluMux3Bus);
    aluMux.addOutput(aluMux4Bus);
    aluMux.addOutput(aluMux5Bus);
    aluMux.addOutput(aluMux6Bus);
    aluMux.addOutput(aluMux7Bus);

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
    vectorialAlu.addInput(aluMux0Bus);
    vectorialAlu.addInput(aluMux1Bus);
    vectorialAlu.addInput(aluMux2Bus);
    vectorialAlu.addInput(aluMux3Bus);
    vectorialAlu.addInput(aluMux4Bus);
    vectorialAlu.addInput(aluMux5Bus);
    vectorialAlu.addInput(aluMux6Bus);
    vectorialAlu.addInput(aluMux7Bus);
    vectorialAlu.addOutput(aluV0EBus);
    vectorialAlu.addOutput(aluV1EBus);
    vectorialAlu.addOutput(aluV2EBus);
    vectorialAlu.addOutput(aluV3EBus);
    vectorialAlu.addOutput(aluV4EBus);
    vectorialAlu.addOutput(aluV5EBus);
    vectorialAlu.addOutput(aluV6EBus);
    vectorialAlu.addOutput(aluV7EBus);

    // -------------------------------- MEM  --------------------------------
    this.emRegister = new Register();

    Bus writeEnableRegisterMBus = new Bus(); //->
    Bus writeEnableVectorMBus = new Bus(); //->
    Bus a3MBus = new Bus(); //->
    Bus immediateMBus = new Bus(); //->
    Bus aluResultMBus = new Bus(); //->
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
    emRegister.addInput(aluResultEBus);
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
    emRegister.addOutput(aluResultMBus);
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
    this.mwRegister = new Register();

    Bus writeEnableRegisterWBus = new Bus(); //->
    Bus writeEnableVectorWBus = new Bus(); //->
    Bus a3WBus = new Bus();
    Bus immediateWBus = new Bus();
    Bus aluResultWBus = new Bus();

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
    mwRegister.addInput(aluResultMBus);
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
    mwRegister.addOutput(aluResultWBus);
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
    vectorialMux.addControl(aluResultWBus);
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
  }

  public Clk getClock() {
    return (Clk) (this.clock);
  }

  public void debug() {

    //if(((Clk)clock).isRunning()) {
      System.out.printf("\n                 PC              |          fdRegister              |             deRegister           |              emRegister          |          mwRegister\n");
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "id", "in", "data", "id", "in", "data", "id", "in", "data", "id", "in", "data", "id", "in", "data");
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "pc", pc.getInputs().get(0).getState(), pc.getData().get(0), "instruction", fdRegister.getInputs().get(0).getState(), fdRegister.getData().get(0), "RegMemWEE", deRegister.getInputs().get(0).getState(), deRegister.getData().get(0), "RegMemWEE", emRegister.getInputs().get(0).getState(), emRegister.getData().get(0), "RegMemWEE", mwRegister.getInputs().get(0).getState() , mwRegister.getData().get(0));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RegMemWEV", deRegister.getInputs().get(1).getState(), deRegister.getData().get(2), "RegMemWEV", emRegister.getInputs().get(1).getState() , emRegister.getData().get(1), "RegMemWEV", mwRegister.getInputs().get(1).getState() , mwRegister.getData().get(1));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "A3", deRegister.getInputs().get(2).getState(), deRegister.getData().get(3), "A3", emRegister.getInputs().get(2).getState() , emRegister.getData().get(2), "A3", mwRegister.getInputs().get(2).getState() , mwRegister.getData().get(2));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "Immediate", deRegister.getInputs().get(3).getState(), deRegister.getData().get(4), "Immediate", emRegister.getInputs().get(3).getState() , emRegister.getData().get(3), "Immediate", mwRegister.getInputs().get(3).getState() , mwRegister.getData().get(3));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "AluResultMux", deRegister.getInputs().get(4).getState(), deRegister.getData().get(5), "AluResultMux", emRegister.getInputs().get(4).getState() , emRegister.getData().get(4), "AluResultMux", mwRegister.getInputs().get(4).getState() , mwRegister.getData().get(4));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "DataWE", deRegister.getInputs().get(5).getState(), deRegister.getData().get(6), "DataWE", emRegister.getInputs().get(5).getState() , emRegister.getData().get(5), "DataMemOut0", mwRegister.getInputs().get(5).getState() , mwRegister.getData().get(5));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "R1", deRegister.getInputs().get(6).getState(), deRegister.getData().get(7), "R1", emRegister.getInputs().get(6).getState() , emRegister.getData().get(6), "DataMemOut1", mwRegister.getInputs().get(6).getState() , mwRegister.getData().get(6));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV20", deRegister.getInputs().get(7).getState(), deRegister.getData().get(8), "RV20", emRegister.getInputs().get(7).getState() , emRegister.getData().get(7), "DataMemOut2", mwRegister.getInputs().get(7).getState() , mwRegister.getData().get(7));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV21", deRegister.getInputs().get(8).getState(), deRegister.getData().get(9), "RV21", emRegister.getInputs().get(8).getState() , emRegister.getData().get(8), "DataMemOut3", mwRegister.getInputs().get(8).getState() , mwRegister.getData().get(8));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV22", deRegister.getInputs().get(9).getState(), deRegister.getData().get(10), "RV22", emRegister.getInputs().get(9).getState() , emRegister.getData().get(9), "DataMemOut4", mwRegister.getInputs().get(9).getState() , mwRegister.getData().get(9));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV23", deRegister.getInputs().get(10).getState(), deRegister.getData().get(11), "RV23", emRegister.getInputs().get(10).getState() , emRegister.getData().get(10), "DataMemOut5", mwRegister.getInputs().get(10).getState() , mwRegister.getData().get(10));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV24", deRegister.getInputs().get(11).getState(), deRegister.getData().get(12), "RV24", emRegister.getInputs().get(11).getState() , emRegister.getData().get(11), "DataMemOut6", mwRegister.getInputs().get(11).getState() , mwRegister.getData().get(11));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV25", deRegister.getInputs().get(12).getState(), deRegister.getData().get(13), "RV25", emRegister.getInputs().get(12).getState() , emRegister.getData().get(12), "DataMemOut7", mwRegister.getInputs().get(12).getState() , mwRegister.getData().get(12));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV26", deRegister.getInputs().get(13).getState(), deRegister.getData().get(14), "RV26", emRegister.getInputs().get(13).getState() , emRegister.getData().get(13), "AluResult0", mwRegister.getInputs().get(13).getState() , mwRegister.getData().get(13));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV27", deRegister.getInputs().get(14).getState(), deRegister.getData().get(15), "RV27", emRegister.getInputs().get(14).getState() , emRegister.getData().get(14), "AluResult1", mwRegister.getInputs().get(14).getState() , mwRegister.getData().get(14));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "AluControl", deRegister.getInputs().get(15).getState(), deRegister.getData().get(16), "AluResult0", emRegister.getInputs().get(15).getState() , emRegister.getData().get(15), "AluResult2", mwRegister.getInputs().get(15).getState() , mwRegister.getData().get(15));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "AluVectorialMux", deRegister.getInputs().get(16).getState(), deRegister.getData().get(17), "AluResult1", emRegister.getInputs().get(16).getState() , emRegister.getData().get(16), "AluResult3", mwRegister.getInputs().get(16).getState() , mwRegister.getData().get(16));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV10", deRegister.getInputs().get(17).getState(), deRegister.getData().get(18), "AluResult2", emRegister.getInputs().get(17).getState() , emRegister.getData().get(17), "AluResult4", mwRegister.getInputs().get(17).getState() , mwRegister.getData().get(17));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV11", deRegister.getInputs().get(18).getState(), deRegister.getData().get(19), "AluResult3", emRegister.getInputs().get(18).getState() , emRegister.getData().get(18), "AluResult5", mwRegister.getInputs().get(18).getState() , mwRegister.getData().get(18));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV12", deRegister.getInputs().get(19).getState(), deRegister.getData().get(20), "AluResult4", emRegister.getInputs().get(19).getState() , emRegister.getData().get(19), "AluResult6", mwRegister.getInputs().get(19).getState() , mwRegister.getData().get(19));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV13", deRegister.getInputs().get(20).getState(), deRegister.getData().get(21), "AluResult5", emRegister.getInputs().get(20).getState() , emRegister.getData().get(20), "AluResult7", mwRegister.getInputs().get(20).getState() , mwRegister.getData().get(20));
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV14", deRegister.getInputs().get(21).getState(), deRegister.getData().get(22), "AluResult6", emRegister.getInputs().get(21).getState() , emRegister.getData().get(21), "", "", "");
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV15", deRegister.getInputs().get(22).getState(), deRegister.getData().get(23), "AluResult7", emRegister.getInputs().get(22).getState() , emRegister.getData().get(22), "", "", "");
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV16", deRegister.getInputs().get(23).getState(), deRegister.getData().get(24), "", "", "", "", "", "");
      System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "RV17", deRegister.getInputs().get(24).getState(), deRegister.getData().get(25), "", "", "", "", "", "");
    System.out.printf("%-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s | %-10.10s %-10.10s %-10.10s%n", "", "", "", "", "", "", "R2", deRegister.getInputs().get(25).getState(), deRegister.getData().get(25), "", "", "", "", "", "");
      System.out.printf("\n                 REGISTER MEMORY\n");
      System.out.printf("%-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s%n", "R0", registerMemory.getRegisters().get(0), "R1", registerMemory.getRegisters().get(1), "R2", registerMemory.getRegisters().get(2), "R3", registerMemory.getRegisters().get(3), "R4", registerMemory.getRegisters().get(4), "R5", registerMemory.getRegisters().get(5), "R6", registerMemory.getRegisters().get(6), "R7", registerMemory.getRegisters().get(7));
      System.out.printf("%-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s%n", "RV00", registerMemory.getVectorialRegisters().get(0).get(0), "RV10", registerMemory.getVectorialRegisters().get(1).get(0), "RV20", registerMemory.getVectorialRegisters().get(2).get(0), "RV30", registerMemory.getVectorialRegisters().get(3).get(0), "RV40", registerMemory.getVectorialRegisters().get(4).get(0), "RV50", registerMemory.getVectorialRegisters().get(5).get(0), "RV60", registerMemory.getVectorialRegisters().get(6).get(0), "RV70", registerMemory.getVectorialRegisters().get(7).get(0));
      System.out.printf("%-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s%n", "RV01", registerMemory.getVectorialRegisters().get(0).get(1), "RV11", registerMemory.getVectorialRegisters().get(1).get(1), "RV21", registerMemory.getVectorialRegisters().get(2).get(1), "RV31", registerMemory.getVectorialRegisters().get(3).get(1), "RV41", registerMemory.getVectorialRegisters().get(4).get(1), "RV51", registerMemory.getVectorialRegisters().get(5).get(1), "RV61", registerMemory.getVectorialRegisters().get(6).get(1), "RV71", registerMemory.getVectorialRegisters().get(7).get(1));
      System.out.printf("%-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s%n", "RV02", registerMemory.getVectorialRegisters().get(0).get(2), "RV12", registerMemory.getVectorialRegisters().get(1).get(2), "RV22", registerMemory.getVectorialRegisters().get(2).get(2), "RV32", registerMemory.getVectorialRegisters().get(3).get(2), "RV42", registerMemory.getVectorialRegisters().get(4).get(2), "RV52", registerMemory.getVectorialRegisters().get(5).get(2), "RV62", registerMemory.getVectorialRegisters().get(6).get(2), "RV72", registerMemory.getVectorialRegisters().get(7).get(2));
      System.out.printf("%-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s%n", "RV03", registerMemory.getVectorialRegisters().get(0).get(3), "RV13", registerMemory.getVectorialRegisters().get(1).get(3), "RV23", registerMemory.getVectorialRegisters().get(2).get(3), "RV33", registerMemory.getVectorialRegisters().get(3).get(3), "RV43", registerMemory.getVectorialRegisters().get(4).get(3), "RV53", registerMemory.getVectorialRegisters().get(5).get(3), "RV63", registerMemory.getVectorialRegisters().get(6).get(3), "RV73", registerMemory.getVectorialRegisters().get(7).get(3));
      System.out.printf("%-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s%n", "RV04", registerMemory.getVectorialRegisters().get(0).get(4), "RV14", registerMemory.getVectorialRegisters().get(1).get(4), "RV24", registerMemory.getVectorialRegisters().get(2).get(4), "RV34", registerMemory.getVectorialRegisters().get(3).get(4), "RV44", registerMemory.getVectorialRegisters().get(4).get(4), "RV54", registerMemory.getVectorialRegisters().get(5).get(4), "RV64", registerMemory.getVectorialRegisters().get(6).get(4), "RV74", registerMemory.getVectorialRegisters().get(7).get(4));
      System.out.printf("%-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s%n", "RV05", registerMemory.getVectorialRegisters().get(0).get(5), "RV15", registerMemory.getVectorialRegisters().get(1).get(5), "RV25", registerMemory.getVectorialRegisters().get(2).get(5), "RV35", registerMemory.getVectorialRegisters().get(3).get(5), "RV45", registerMemory.getVectorialRegisters().get(4).get(5), "RV55", registerMemory.getVectorialRegisters().get(5).get(5), "RV65", registerMemory.getVectorialRegisters().get(6).get(5), "RV75", registerMemory.getVectorialRegisters().get(7).get(5));
      System.out.printf("%-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s%n", "RV06", registerMemory.getVectorialRegisters().get(0).get(6), "RV16", registerMemory.getVectorialRegisters().get(1).get(6), "RV26", registerMemory.getVectorialRegisters().get(2).get(6), "RV36", registerMemory.getVectorialRegisters().get(3).get(6), "RV46", registerMemory.getVectorialRegisters().get(4).get(6), "RV56", registerMemory.getVectorialRegisters().get(5).get(6), "RV66", registerMemory.getVectorialRegisters().get(6).get(6), "RV76", registerMemory.getVectorialRegisters().get(7).get(6));
      System.out.printf("%-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s %-4.4s %-10.10s%n", "RV07", registerMemory.getVectorialRegisters().get(0).get(7), "RV17", registerMemory.getVectorialRegisters().get(1).get(7), "RV27", registerMemory.getVectorialRegisters().get(2).get(7), "RV37", registerMemory.getVectorialRegisters().get(3).get(7), "RV47", registerMemory.getVectorialRegisters().get(4).get(7), "RV57", registerMemory.getVectorialRegisters().get(5).get(7), "RV67", registerMemory.getVectorialRegisters().get(6).get(7), "RV77", registerMemory.getVectorialRegisters().get(7).get(7));
    //}

  }

  public void setPrivateKey(int key) {
    registerMemory.getRegisters().set(2, key);
  }

  public void setPrivateKey(ArrayList<Integer> vector) {
    registerMemory.getVectorialRegisters().set(2,vector);
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
    pc.setDebug(true);
    fdRegister.setDebug(true);
    deRegister.setDebug(true);
    emRegister.setDebug(true);
    mwRegister.setDebug(true);
    registerMemory.setDebug(true);
    getClock().setDebug(true);
  }

  public void runThreaded() {
    getClock().createThreads();
    Scanner scanner = new Scanner(System.in);
    while (getClock().isRunning()) {
      getClock().tickTack();
      if (debug) {
        debug();
        String line = scanner.nextLine();
      }
    }
  }

}
