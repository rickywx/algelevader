package elevader.api;



public interface Elevatable {
	public Elevatable selectFloors(Integer... floors);
	public Elevatable activate(Integer... floors);
	public boolean isUp();
	public Boolean[] getFloors();
	public Integer getCurrentFloor();
	public Integer[] getSelectedFloors();
}
