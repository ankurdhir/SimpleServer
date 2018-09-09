import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;

    public WorkerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            InputStream inputToServer = clientSocket.getInputStream();
            OutputStream outputFromServer = clientSocket.getOutputStream();

            Scanner scanner = new Scanner(inputToServer, "UTF-8");
            PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);

            while(true) {
                String line = scanner.nextLine();
                if(line.equals("")){
                    break;
                }
                serverPrintOut.println("Echo from Ankur Server: " + line);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}