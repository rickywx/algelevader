package elevader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import elevader.api.Elevatable;

public class Elevator implements Elevatable {
	private Integer currentIdx = 0;
	private Boolean[] floors;
	private boolean up = true;
	
	public Elevator() {
		this(32);
	}
	
	public Elevator(Integer floorCount) {
		floors = new Boolean[floorCount == null ? 32 : floorCount];
		Arrays.fill(floors, false);
	}
	
	@Override
	public Elevator selectFloors(Integer... selectedFloors) {
		for(Integer selectedFloor : selectedFloors) {
			Integer floorIdx = selectedFloor-1;
			if(!floors[floorIdx]) {
				floors[floorIdx] = true;
			}
		}
		return this;
	}

	private void delay(Integer millis) {
		for(int j=0;j<3;j++) try {
			Thread.sleep(millis / 3);
			System.out.print(".");
		} catch (InterruptedException e) { /* carry on */ }
	}

	/**
	 * @return next floor selected starting in the current direction, or 0 if no floors selected
	 */
	//TODO use a logger instead of System.out	
	private Integer goToNextSelectedFloor() {
		if(!isAnyFloorSelected()) 
			return 0;
		
		if(up && !isUpperFloorSelected()) {
			up = false;
		}
		if(!up && !isLowerFloorSelected()) {
			up = true;
		}
		
		Integer inc = up ? 1 : -1;
		System.out.printf("Going %s!\n", up ? "up" : "down");
		for(; !floors[currentIdx]; currentIdx+=inc) {
			System.out.print(currentIdx+1);
			delay(300);
		}
		System.out.print("Stay on target");
		delay(100);
		System.out.print("Stay on target");
		delay(100);
		System.out.print("Almost there");
		delay(100);
		floors[currentIdx] = false;
		return currentIdx+1;
	}
	
	@Override
	public Elevator activate(Integer... floors) {
		selectFloors(floors);
		while(isAnyFloorSelected()) {
			goToNextSelectedFloor();
			System.out.printf("Now on %dF. ", currentIdx+1);
		}
		return this;
	}
	
	private boolean isUpperFloorSelected() {
		return IntStream.range(currentIdx+1, floors.length).anyMatch(i -> floors[i]);
	}
	
	private boolean isLowerFloorSelected() {
		return IntStream.range(0,currentIdx).anyMatch(i -> floors[i]);
	}
	
	private boolean isAnyFloorSelected() {
		return IntStream.range(0, floors.length).anyMatch(i -> floors[i]);
	}
	
	@Override
	public Integer getCurrentFloor() {
		return currentIdx+1;
	}
	
	@Override
	public Boolean[] getFloors() {
		return floors;
	}
	
	@Override
	public boolean isUp() {
		return up;
	}
	
	@Override
	public Integer[] getSelectedFloors() {
		List<Integer> result = new ArrayList<>();
		for(int i=0;i<floors.length;i++) {
			if(floors[i]) {
				result.add(new Integer(i));
			}
		}
		return result.toArray(new Integer[0]); 
	}
}
