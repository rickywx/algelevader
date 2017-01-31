EleVADER application

EleVADER consists of a server and a client application. To run EleVADER, execute the following commands.

1)
cd to elevaderService directory, and then execute:
./gradlew clean build runServer

2)
cd to ../elevaderClient, and then execute:
./gradlew runClient

Step 1 will download dependencies, build the project, and then start the server. The server is now ready for client connections. You can also select floors and issue other commands directly in the server terminal window.

Step 2 executes 2 clients, 1 that is a web page that emulates an elevator call button, and a second that executes a command line client that allows you to select floors to move the elevator. 
