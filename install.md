
# Guide to install the server and client

### How to build the Basic-Java-Instant-Messenger source code?
- Make sure you have Maven Java 15 or later on the machine where you want to build the source code
- Build the source code using the following command:
  - `mvn clean install -U`
- You should now be able to see a Jar file created `${PROJECT_HOME_DIRECTORY}/target` named `basic-java-instant-messenger-1.0.0-SNAPSHOT.jar`

### How to run the server?
- Run the following Java command:
  - `java -cp basic-java-instant-messenger-1.0.0-SNAPSHOT.jar bjim.ServerApplication`

### How to run the client?
- Run the following Java command:
  - `java -cp basic-java-instant-messenger-1.0.0-SNAPSHOT.jar bjim.ClientApplication`
- You can run as many client applications as needed using the above command.