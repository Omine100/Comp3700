import java.net.*;
import java.io.*;
import java.util.Scanner;

public class client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 4999;

    public static void main(String[] args) throws IOException {
        //Setup
        Socket s = new Socket(SERVER_IP, SERVER_PORT);
        System.out.println("[CLIENT] Connected to server.");

        Scanner sc = new Scanner(System.in);
        System.out.println("[CLIENT] Enter a product ID: ");
        int productID = sc.nextInt();

        //Sending input to server
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        pr.println(productID);

        //Receiving input from server
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("[CLIENT] Product information: " + str);

        System.out.println("[CLIENT] Closing...");
        s.close();
    }
}