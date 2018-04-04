package test.savableinterface;

import java.util.ArrayList;
import java.util.List;

public class Player implements ISavable {
	private String sName;
	private int nHitPoints;
	private int nStrength;
	private String sWeapon;
	
	protected Player(String sName, int nHitPoints, int nStrength) {
		this.sName = sName;
		this.nHitPoints = nHitPoints;
		this.nStrength = nStrength;
		this.sWeapon = "Sword";
	}

	protected String getsName() {
		return sName;
	}

	protected void setsName(String sName) {
		this.sName = sName;
	}

	protected int getnHitPoints() {
		return nHitPoints;
	}

	protected void setnHitPoints(int nHitPoints) {
		this.nHitPoints = nHitPoints;
	}

	protected int getnStrength() {
		return nStrength;
	}

	protected void setnStrength(int nStrength) {
		this.nStrength = nStrength;
	}

	protected String getsWeapon() {
		return sWeapon;
	}

	protected void setsWeapon(String sWeapon) {
		this.sWeapon = sWeapon;
	}

	@Override
	public String toString() {
		return "Player [sName=" + sName + ", nHitPoints=" + nHitPoints + ", nStrength=" + nStrength + ", sWeapon="
				+ sWeapon + "]";
	}

	@Override
	public List<String> write() {
		List<String> listValues = new ArrayList<>();
		listValues.add(0, this.sName);
		listValues.add(1, "" + this.nHitPoints);
		listValues.add(2, "" + this.nStrength);
		listValues.add(3, "" + this.sWeapon);
		return listValues;
	}

	@Override
	public void read(List<String> savedValues) {
		if(savedValues != null && !savedValues.isEmpty()) {
			this.sName = savedValues.get(0);
			this.nHitPoints = Integer.parseInt(savedValues.get(1));
			this.nStrength = Integer.parseInt(savedValues.get(2));
			this.sWeapon = savedValues.get(3);
		}		
	}
	
	
	

}
