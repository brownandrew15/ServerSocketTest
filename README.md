# ServerSocketTests

### About
A Java TCP server and client.
The server uses the ServerSocket class and the client uses the Socket class.

### Known issues
- When the server program stops before the client, and the client tries to send a message the server, the client will throw an IOException that states that the connection has been reset.
