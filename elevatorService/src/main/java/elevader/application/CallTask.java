package elevader.application;

import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import elevader.Consts;

/*
 * schedule a task periodically after a random # of seconds between 1-30 has elapsed 
 */
public class CallTask extends TimerTask {
	Boolean[] floors;
	ScheduledExecutorService executorService;
	
	public CallTask(Boolean[] floors, ScheduledExecutorService executorService) {
		this.floors = floors;
		this.executorService = executorService;
	}
	
	@Override public void run() {
		if(!this.executorService.isShutdown()) {
			Random r = new Random();
			int floorIdx = r.nextInt(floors.length);
			if(!floors[floorIdx]) {
				System.out.printf("\nEleVADER has been called from %dF\n", floorIdx+1);
				System.out.println(Consts.PROMPT);
			}
			floors[floorIdx] = true;
			executorService.schedule(new CallTask(floors, executorService), 
					new Random().nextInt(29) + 1, TimeUnit.SECONDS);
		}
	}
}