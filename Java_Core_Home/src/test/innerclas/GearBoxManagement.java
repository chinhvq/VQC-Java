package test.innerclas;

import java.util.Scanner;

public class GearBoxManagement {
	private static Scanner scanner = new Scanner(System.in);
	private static Button btnPrint = new Button("Print");

	public static void main(String[] args) {
		
		//local class implement another inner interface
		//local class is settle in a block of code only
//		class ClickListener implements Button.OnClickListener {
//			public ClickListener() {
//				System.out.println("ClickListener is attached");
//			}
//
//			@Override
//			public void onClick(String sTitle) {
//				System.out.println(sTitle + " is pressed");				
//			}			
//		}
//		
//		btnPrint.setOnClickListener(new ClickListener());
		
		//anomyous class has no name
		btnPrint.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(String sTitle) {
				System.out.println(sTitle + " is pressed");				
			}
		});
		listen();
		
		//GearBox test and enjoy the speed
		GearBox gearbox = new GearBox(6);
		gearbox.addGear(1, 10);
		gearbox.addGear(2, 20);
		gearbox.addGear(3, 30);
		gearbox.addGear(4, 40);
		gearbox.addGear(5, 50);
		gearbox.addGear(6, 60);
		gearbox.operateClutch(true);
		gearbox.changeGear(2);
		gearbox.operateClutch(false);
		System.out.println("Current Wheel Speed = " + gearbox.wheelSpeed(100));
		gearbox.changeGear(3);
		gearbox.operateClutch(true);
		gearbox.changeGear(3);
		gearbox.operateClutch(false);
		System.out.println("Current Wheel Speed = " + gearbox.wheelSpeed(100));

	}
	
	protected static void listen() {
		boolean bExit = true;
		while (bExit) {
			System.out.println("\nPlease select 0 or 1");
			int nChoice = scanner.nextInt();
			scanner.nextLine();
			
			switch (nChoice) {
			case 0 :
				bExit = false;
				break;
				
			case 1:
				btnPrint.onClick();
				break;
			
			default:
				System.out.println("\nPlease select 0 or 1 - THANK YOU");
				break;
			}
		}
		
	}

}
