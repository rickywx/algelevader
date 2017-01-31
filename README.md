EleVADER application

EleVADER consists of a server and a client application. To run EleVADER, execute the following commands.

1)
cd to elevaderService directory, and then execute:
./gradlew clean build runServer

2)
Open a new terminal tab/window, then
cd to elevaderClient directory, and then execute:
./gradlew openClientPage runClient
The client was already built when the service application was built.

Step 1 will download dependencies, build the project, and then start the server. The server is now ready for client connections. You can also select floors and issue other commands directly in the server terminal window.

Step 2 executes 2 clients, 1 that is a web page that emulates an elevator call button, and a second that executes a command line client that allows you to select floors to move the elevator. 

You can watch the output for the elevator movement in the elevator terminal as you enter commands through either the web page or the client terminal.

A RESTful service may not be the best choice for an elevator server, but I wanted some more practice with RESTful services - which is why I chose to implement it this way.

TODO: I still need to check inputs; putting in a high number causes an arrayidxOOB error
