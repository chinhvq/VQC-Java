package test.savableinterface;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerManagement {

	public static void main(String[] args) {
		Player tim = new Player("Tim", 10, 15);
		System.out.println(tim.toString());
		saveObject(tim);
		System.out.println(tim + "\tSize =\t" + tim.write().size());
		System.out.println();
		tim.setnHitPoints(8);
		System.out.println(tim);
		System.out.println();
		tim.setsWeapon("Stormbringer");
		saveObject(tim);
		System.out.println();
		loadObject(tim);
		System.out.println(tim);
		
		System.out.println();
		ISavable dracular = new Monster("dracula", 50, 80);		
		System.out.println(dracular);
		System.out.println(((Monster) dracular).getsName() + " has Strength of "  + ((Monster) dracular).getnStrength());		
		saveObject(dracular);
		
		

	}

	public static ArrayList<String> readValues() {
		ArrayList<String> listValues = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		boolean bExit = true;
		int nIndex = 0;
		int nChoice;
		System.out.println("Choose\n" + "1 to enter a string\n" + "0 to quit");
		while (bExit) {
			System.out.print("Please input your choice:\t");
			nChoice = scanner.nextInt();
			scanner.nextLine();

			switch (nChoice) {
			case 0:
				bExit = false;
				break;

			case 1:
				System.out.print("Enter String:\t");
				String sInput = scanner.nextLine();
				listValues.add(nIndex, sInput);
				nIndex++;
				break;
			default:
				System.out.println("Please choose (0 or 1)");
				break;
			}

		}

		scanner.close();
		return listValues;
	}

	public static void saveObject(ISavable objectToSave) {
		for (int i = 0; i < objectToSave.write().size(); i++) {
			System.out.println("Saving " + objectToSave.write().get(i) + " to storage media");
		}		
	}

	public static void loadObject(ISavable objectToLoad) {
		ArrayList<String> listValues = readValues();
		objectToLoad.read(listValues);
	}

}
