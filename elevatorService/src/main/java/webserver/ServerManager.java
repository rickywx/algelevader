package webserver;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import elevader.Consts;
import elevader.Elevator;
import elevader.api.Elevatable;
import elevader.application.Application;

public class ServerManager {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/elevader";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startGrizzlyServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in $package package
        final ResourceConfig rc = new ResourceConfig().packages("elevader");

        //NEW: register custom ResponseFilter
        rc.register(elevader.CORSResponseFilter.class);
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI	
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        return server;
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startGrizzlyServer();
        System.out.println(Consts.VADER);
    	System.out.println("EleVADER Server started.");
        System.out.println(String.format("Hit enter to stop it...", BASE_URI));
        
        Application.main(args);
        
        server.shutdownNow();
    }

}
