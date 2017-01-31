package elevader.application;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import elevader.Consts;
import elevader.Elevator;
import elevader.api.Elevatable;

public class Application {
	private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	
	public void run(Elevatable elevator) {
		System.out.println("Welcome to EleVADER! May the force be with you.");
		System.out.println(Consts.VADER);
		Scanner in = new Scanner(System.in);
		try {
			String nextLine = "";
			while(nextLine != null && !nextLine.equalsIgnoreCase("q")) {
				System.out.printf("Now on %dF. ", elevator.getCurrentFloor());
				System.out.println(Consts.PROMPT);
				nextLine = in.nextLine();
				if(nextLine.equalsIgnoreCase("q")) {
					System.out.println("Thank you for using EleVADER. Your journey to the dark side is complete.");
					System.out.println("");
					System.out.println(Consts.VADER);			
				} else if(nextLine.equalsIgnoreCase("callon")) { 
					System.out.println("Random elevator calls scheduled.");
					executorService.schedule(new CallTask(elevator.getFloors(), executorService), 1, TimeUnit.SECONDS);
				} else if(nextLine.equalsIgnoreCase("calloff")){ 
					System.out.println("Random elevator calls stopped - but some floors may still be selected.");
					executorService.shutdown();
				} else {
					try {
						Integer[] selectedFloors = Arrays.stream(nextLine.split("[, ]"))
								.map(i -> Integer.parseInt(i))
								.toArray(Integer[]::new);
						elevator.activate(selectedFloors);
					} catch(NumberFormatException | ArrayStoreException ex) {
						if(nextLine == null || nextLine.trim().isEmpty()) {
							elevator.activate();
						} else {
							System.out.println("Unknown floor - you have failed me for the last time.");
							System.out.println("Enter 1 or more whole numbers that represent floors where the EleVADER should stop.");
							System.out.println("Separate the numbers with a space.");
						}
					}
				}
				System.out.println("");
			}
		} finally {
			in.close();
		}
		executorService.shutdownNow();
	}
	
	public static void main(String[] args) {
		Elevatable elevator;
		try {
			elevator = new Elevator(Integer.valueOf(args[0]));
		} catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
			elevator = new Elevator(null);
		}
		Application main = new Application();
		main.run(elevator);
	}
}
