package elevader;

import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class ClientMain {
	
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/elevader");
		Scanner in = new Scanner(System.in);
		try {
			String nextLine = "";
			while(nextLine != null && !nextLine.equalsIgnoreCase("q")) {
				System.out.println("Enter a comma-separated list of floors or 'q' to quit > ");
				nextLine = in.nextLine();
				if(!nextLine.equalsIgnoreCase("q")) {
					Form form = new Form("floors",nextLine);
					Response response = webTarget.path("elevader").request().post(Entity.form(form));
					System.out.printf("Selected floors: %s\n", response.readEntity(String.class));
					System.out.printf("Elevator is now on %sF\n", webTarget.path("elevader").request().get(String.class));
				}
			}
			System.out.println("Exiting...");
		} finally {
			in.close();
		}
		System.out.println("Thank you for riding EleVADER.");
	}
}
