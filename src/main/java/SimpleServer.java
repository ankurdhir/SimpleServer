import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class SimpleServer {

    protected static int serverPort   = 8080;
    protected static ServerSocket serverSocket = null;

    public static void main(String args[]){

        getServerSocket();

        while(true){
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Error accepting client connection" + e);
            }
            new Thread( new WorkerRunnable( clientSocket ) ).start();
        }

    }

    private static void getServerSocket() {
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            System.out.println("Cannot open port "+ serverPort + e);
        }
    }

}