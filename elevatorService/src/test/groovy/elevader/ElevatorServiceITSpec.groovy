package elevader

import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status

import spock.lang.Specification

class ElevatorServiceITSpec extends Specification {
	ElevatorService elevatorService
	
	def setup() {
		elevatorService = new ElevatorService()
	}
	
	def gotoFloors() {
		when:
		Response response = elevatorService.gotoFloors("42")
		
		then: 
		Response.Status.fromStatusCode(response.status) == Response.Status.INTERNAL_SERVER_ERROR
		response.entity.equals(String.format(ElevatorService.INVALID_INPUT_MSG,"42",32))
	}
}
