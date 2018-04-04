package test.innerclas;

import java.util.ArrayList;

public class GearBox {
	private ArrayList<Gear> listGears;
	private int nCurrentGear = 0;
	private int nMaxGear;
	private boolean bClutchIsIn;
	
	protected GearBox(int nMaxGear) {
		this.nMaxGear = nMaxGear;
		this.listGears = new ArrayList<>();
		Gear neutral = new Gear(0, 0.0);
		this.listGears.add(neutral);		
	}
	
	protected void operateClutch(boolean in) {
		this.bClutchIsIn = in;
	}
	
	protected void addGear(int nNumber, double dRatio) {
		if(nNumber > 0 && nNumber < this.nMaxGear) {
			this.listGears.add(new Gear(nNumber, dRatio));
		}
	}
	
	protected void changeGear(int newGear) {
		if(newGear > 0 && newGear < this.listGears.size() && this.bClutchIsIn) {
			this.nCurrentGear = newGear;
			System.out.println("Gear\t" + newGear + "\t selected");			
		} else {
			System.out.println("Grind!!!");
			this.nCurrentGear = 0;
		}
	}
	
	protected double wheelSpeed(int revs) {
		if(bClutchIsIn) {
			System.out.println("Scream!!!");
			return 0.0;
		} else {
			return revs * this.listGears.get(nCurrentGear).getdRatio();
		}		
	}
	
	//inner Gear Class
	private class Gear {
		private int nGearNumber;
		private double dRatio;
		
		protected Gear(int nGearNumber, double dRatio) {
			this.nGearNumber = nGearNumber;
			this.dRatio = dRatio;
		}			
		
		protected int getnGearNumber() {
			return nGearNumber;
		}

		protected double getdRatio() {
			return dRatio;
		}

		protected double driveSpeed(int nRevs) {
			return (nRevs * this.dRatio);
		}
	}
	
	
	
	

}
