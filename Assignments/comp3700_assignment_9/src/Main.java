import org.sqlite.SQLiteException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private Connection connect() {
        //SQLite connection string
        String url = "jdbc:sqlite:/Users/matthewbrowning/Documents/Programming/Github/Comp3700/Assignments/comp3700_assignment_9/data/store.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void printRows() {
        String CustomerTable = "SELECT CUSTOMERID, CUSTOMERNAME, CUSTOMERADDRESS, CUSTOMERPHONE FROM CUSTOMERS";
        String ProductTable = "SELECT PRODUCTID, PRODUCTNAMEANDDESCRIPTION, PRODUCTPRICE, PRODUCTAVAILABLEQUANTITY FROM PRODUCTS";
        String PurchaseTable = "SELECT CUSTOMERID, PRODUCTID, QUANTITY FROM PURCHASES";

        try (Connection connection = this.connect()){
            System.out.println("\t\tSTORE DATABASE\n\t\tPRINT ROWS\n");

            Statement customerStatement = connection.createStatement();
            ResultSet customerSet = customerStatement.executeQuery(CustomerTable);
            Statement productStatement = connection.createStatement();
            ResultSet productSet = productStatement.executeQuery(ProductTable);
            Statement purchaseStatement = connection.createStatement();
            ResultSet purchaseSet = purchaseStatement.executeQuery(PurchaseTable);

            System.out.println("Customer Table Rows:");
            while (customerSet.next()) {
                System.out.println("\t" +
                        customerSet.getString("CUSTOMERID") + ", " +
                        customerSet.getString("CUSTOMERNAME") + ", " +
                        customerSet.getString("CUSTOMERADDRESS")+ ", " +
                        customerSet.getString("CUSTOMERPHONE"));
            }
            System.out.println("\nProduct Table Rows:");
            while (productSet.next()) {
                System.out.println("\t" +
                        productSet.getString("PRODUCTID") + ", " +
                        productSet.getString("PRODUCTNAMEANDDESCRIPTION") + ", " +
                        productSet.getString("PRODUCTPRICE")+ ", " +
                        productSet.getString("PRODUCTAVAILABLEQUANTITY"));
            }
            System.out.println("\nPurchase Table Rows:");
            while (purchaseSet.next()) {
                System.out.println("\t" +
                        purchaseSet.getString("CUSTOMERID") + ", " +
                        purchaseSet.getString("PRODUCTID") + ", " +
                        purchaseSet.getString("QUANTITY"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.printRows();
    }
}
