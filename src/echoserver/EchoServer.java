package echoserver;
import java.net.*;
import java.io.*;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        try {
            // Start listening on the specified port
            ServerSocket socket = new ServerSocket(portNumber);


            // Run forever, which is common for server style services
            while (true) {
                // Wait until someone connects, thereby requesting a date
                Socket client = socket.accept();
                System.out.println("Got a request!");

                //Creating input and output streams so we are able to send and recieve stuff in the socket
                InputStream input = client.getInputStream();
                OutputStream output = client.getOutputStream();


                //Creating new array of bytes

                int inputByte;
                //Reading from the input stream
                while (true) {
                    inputByte = input.read(); //gives -1 if there's nothing left to read
                    if (inputByte != -1){
                        output.write(inputByte);
                    } else {
                        break;
                    }
                }
                // Close the client socket since we're done.
                client.close();
                socket.close();
            }
            // *Very* minimal error handling.
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}