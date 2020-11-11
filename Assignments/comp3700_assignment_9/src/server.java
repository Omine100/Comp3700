import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class server {
    private static final int PORT = 4999;

    public static void main(String[] args) throws IOException {
        //Setup
        ServerSocket ss = new ServerSocket(PORT);
        System.out.println("[SERVER] Waiting for client connection...");
        Socket s = ss.accept();
        System.out.println("[SERVER] Connected to client.");

        //Receiving input from client
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String productID = bf.readLine();
        System.out.println("[SERVER] Product ID: " + productID);

        //Connecting to database
        System.out.println("[SERVER] Connecting to database...");
        String productInformation = getProduct(productID, s);

        //Sending input to server
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        pr.println(productInformation);

        System.out.println("[SERVER] Closing...");
        s.close();
        ss.close();
    }

    public static Connection connect() {
        //SQLite connection string
        String url = "jdbc:sqlite:/Users/matthewbrowning/Documents/Programming/Github/Comp3700/Assignments/comp3700_assignment_9/data/store.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
            System.out.println("[SERVER] Database connected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static String getProduct(String productID, Socket s) {
        String Products = "SELECT PRODUCTID, NAME, PRICE, QUANTITY FROM PRODUCTS";
        String name = "", price = "", quantity = "";

        try (Connection connection = connect()) {
            Statement ProductsStatements = connection.createStatement();
            ResultSet ProductsSet = ProductsStatements.executeQuery(Products);

            name = ProductsSet.getString("NAME");
            price = ProductsSet.getString("PRICE");
            quantity = ProductsSet.getString("QUANTITY");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String result = "Name: " + name + " Price: " + price + " Quantity: " + quantity;
        return result;
    }
}