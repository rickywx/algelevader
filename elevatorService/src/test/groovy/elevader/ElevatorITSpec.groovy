package elevader

import spock.lang.Specification;

class ElevatorITSpec extends Specification {
	Elevator elevator
	
	def setup() {
		elevator = new Elevator()
	}
	
	def "should move floors"() {
		when:
		elevator.activate(5)
		
		then:
		elevator.currentFloor == 5
	}

	def "should continue in the same direction even if a higher and lower floor is selected"() {
		given:
		elevator.activate(1)
		
		when:
		elevator.selectFloors(4,7,3,8)
		Boolean[] floors = elevator.floors
		
		then:
		floors[3]
		floors[6]
		floors[2]
		floors[7]
	}	
}
