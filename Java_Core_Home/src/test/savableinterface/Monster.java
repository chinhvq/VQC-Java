package test.savableinterface;

import java.util.ArrayList;
import java.util.List;

public class Monster implements ISavable{
	private String sName;
	private int nHitPoints;
	private int nStrength;
	
	protected Monster(String sName, int nHitPoints, int nStrength) {
		this.sName = sName;
		this.nHitPoints = nHitPoints;
		this.nStrength = nStrength;
	}

	protected String getsName() {
		return sName;
	}

	protected int getnHitPoints() {
		return nHitPoints;
	}

	protected int getnStrength() {
		return nStrength;
	}

	@Override
	public String toString() {
		return "Monster [sName=" + sName + ", nHitPoints=" + nHitPoints + ", nStrength=" + nStrength + "]";
	}
	
	@Override
	public List<String> write() {
		List<String> listValues = new ArrayList<>();
		listValues.add(0, this.sName);
		listValues.add(1, "" + this.nHitPoints);
		listValues.add(2, "" + this.nStrength);
		return listValues;
	}

	@Override
	public void read(List<String> savedValues) {
		if(savedValues != null && !savedValues.isEmpty()) {
			this.sName = savedValues.get(0);
			this.nHitPoints = Integer.parseInt(savedValues.get(1));
			this.nStrength = Integer.parseInt(savedValues.get(2));
		}		
	}
	

}
