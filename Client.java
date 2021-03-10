import java.net.*;
import java.io.*;
import java.util.Scanner;

/*
A simple Java TCP client
*/


public class Client {

    private static String getMessageToSend(Scanner keyboard) {
        System.out.println("Enter a message to send:");
        return keyboard.nextLine();
    }


    public static void main(String[] args) {

        //check that the user has provided a host and port as Command Line Arguments
        if (args.length < 2){
            System.err.println("Please provide two command line arguments:");
            System.err.println("    - Server IP Address");
            System.err.println("    - Port Number");
            //The port is hard coded to 43125 in Server.java
            return;
        }

        final String endMessage = "END";

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(hostname, port)) {

            //create scanner to read from the command line
            Scanner keyboard = new Scanner(System.in);

            //create streams to talk to the server
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Connected to the server, ready to send messages!");
            System.out.println("Enter 'END' to disconnect and exit");

            String textToSend;


            //while the client is still connected
            while (!(socket.isClosed())) {
                //read the message from the command prompt
                textToSend = getMessageToSend(keyboard);

                //check if the message is the end message and the server is still connected
                if (!(textToSend.equals(endMessage))){
                    //Send the message and read the response
                    out.println(textToSend);
                    System.out.println("Sent!");

                    String response = in.readLine();
                    System.out.println("Server responded " + response);

                } else {
                    //close the connection
                    out.println(endMessage);
                    System.out.println("Close the socket");
                    socket.close();
                }

            }

            //close the streams
            in.close();
            out.close();


        } catch (UnknownHostException e) {
            System.err.println("There was an UnknownHostException!");
            e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("There was an IOException!");
            e.getMessage();
            e.printStackTrace();
        }
    }
}
