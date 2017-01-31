package elevader;

import java.util.Arrays;
import java.util.stream.IntStream;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import elevader.api.Elevatable;

@Singleton 
@Path("/elevader")
public class ElevatorService {
	private final Elevatable elevator = new Elevator();
	
	@Context
	private UriInfo context;

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response gotoFloors(@FormParam("floors") String floors) {
		Integer[] selectedFloors = Arrays.stream(floors.split("[, ]")).map(i -> Integer.parseInt(i)).toArray(Integer[]::new);
		Boolean[] allFloors = elevator.selectFloors(selectedFloors).getFloors();
		int[] allSelectedFloors = IntStream.range(0, allFloors.length).filter(i -> allFloors[i]).map(i -> i+1).toArray();
		System.out.println("The force awakens...EleVADER has been activated.");
		elevator.activate(selectedFloors);
		System.out.println("\nEleVADER has reached all selected floors. This journey to the dark side is complete.");
		System.out.println("");
		return Response.ok(Arrays.toString(allSelectedFloors)).build();
	}
	
	/* spent hours trying to get the post method above working from the browser, but couldn't get around the No 'Access-Control-Allow-Origin' header
	 * Not with a filter (CORS) or jsonp. But the filter works for @get. ??? So this is a hack to allow it to work from the browser.
	 * */
	@GET
	@Path("floor/{floor}")
	public Response gotoFloor(@PathParam("floor") String floor) {
		this.gotoFloors(floor);
		return Response.ok(elevator.getCurrentFloor()).build();
	}
	
	@GET
	@Path("/selectedFloors")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSelectedFloors() {
		return Arrays.toString(elevator.getSelectedFloors());
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCurrentFloor() {
		return Integer.toString(elevator.getCurrentFloor());
	}
	
	@GET
	@Path("/direction")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDirection() {
		return elevator.isUp() ? "UP" : "DOWN";
	}
	
}
