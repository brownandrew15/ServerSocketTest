# ServerSocketTests

### About
A Java TCP server and client.
The server uses the ServerSocket class and the client uses the Socket class.

### How to use
1. Run ```$ java Server```
2. Run ```$ java Client <IP Address> <Port>```
3. The server will return the message that is entered into the client
4. To end the program, type ```END``` as the message in the client

The port is hard coded to 43125 in Server.java

### Known issues
- When the server program stops before the client, and the client tries to send a message the server, the client will throw an IOException that states that the connection has been reset.
