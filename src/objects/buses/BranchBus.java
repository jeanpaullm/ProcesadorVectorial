package objects.buses;

public class BranchBus extends Bus {
	
	private int lowerBitIndex;
	private int upperBitIndex;
	
	public BranchBus(int lowerBitIndex, int upperBitIndex) {
		super();
		if(lowerBitIndex <  0 ||
		   lowerBitIndex > 31 ||
		   upperBitIndex <  0 ||
		   upperBitIndex > 31 ||
		   lowerBitIndex > upperBitIndex) {
			System.out.println("Error: Segmental Bus indexes out of bounds.");
		}
		else {
			this.lowerBitIndex = lowerBitIndex;
			this.upperBitIndex = upperBitIndex + 1;
		}
	} 
	
	@Override
	public void setState(int state) {		
		String bits = Integer.toBinaryString(state);
		while(bits.length() < 32) {
			bits = "0" + bits;
		}
		bits = bits.substring(32 - upperBitIndex, 32 - lowerBitIndex);
		this.state = Integer.parseInt(bits,2);
		notifyChanges();
	}

}
