import java.io.*;
import java.net.*;
import java.util.Date;

/*
A simple Java TCP server using the ServerSocket class
*/


public class Server {

    public static void main(String[] args) {

        //server settings
        final int port = 43125;
        final String endMessage = "END";

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            //blocks until a client connects
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            //while the client is still connected
            while (!(socket.isClosed())) {
                //read the message
                System.out.println("Reading message");
                String message = in.readLine();
                System.out.println("recieved: " + message);

                //check if the message is the end message
                if (!(message.equals(endMessage))){
                    //Respond to the message
                    System.out.println("Respond to the message");

                    //generate the response
                    String response = message;

                    //send response
                    out.writeBytes(message + "\n");
                    out.flush();
                } else {
                    //close the connection
                    System.out.println("Close the socket");
                    socket.close();
                }

            }
        } catch (IOException e) {
        	System.err.println("An IOException occured!");
            e.getMessage();
        	e.printStackTrace();
        }


    }
}
