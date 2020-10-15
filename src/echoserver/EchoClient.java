package echoserver;
import java.net.*;
import java.io.*;

public class EchoClient {
  public static final int portNumber = 6013;

  public static void main(String[] args) throws IOException {
    String server;
    // Use "127.0.0.1", i.e., localhost, if no server is specified.
    if (args.length == 0) {
      server = "127.0.0.1";
    } else {
      server = args[0];
    }

    try {
      // Connect to the server
      Socket socket = new Socket(server, portNumber);

      // Get the input stream so we can read from that socket
      InputStream input = socket.getInputStream();
      OutputStream output = socket.getOutputStream();

      // Print all the input we receive from the server
    int echoByte;
      while ((echoByte = System.in.read()) != -1) {
        output.write(echoByte);
        System.out.write(echoByte);

        //Flushing output and System.out because they are both outputs
        output.flush();
        System.out.flush();
      }

      // Close the socket, input and output
      input.close();
      output.close();
      socket.close();

    // Provide some minimal error handling.
    } catch (ConnectException ce) {
      System.out.println("We were unable to connect to " + server);
      System.out.println("Check if the Server is running or the correct IP was given.");
    } catch (IOException ioe) {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);
    }
  }
}
