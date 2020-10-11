package Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class screen_main extends JFrame{
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField tableTextField;
    private JButton displayButton;
    private JList contentList;
    private DefaultListModel listModel = new DefaultListModel();

    public screen_main(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
                printRows(tableTextField.getText(), listModel);
                contentList.setModel(listModel);
                contentList.repaint();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new screen_main("Mr. Smith's Store");
        frame.setVisible(true);
    }

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

    public DefaultListModel printRows(String table, DefaultListModel listModel) {
        String Customers = "SELECT CUSTOMERID, CUSTOMERNAME, CUSTOMERADDRESS, CUSTOMERPHONE FROM CUSTOMERS";
        String Products = "SELECT PRODUCTID, PRODUCTNAMEANDDESCRIPTION, PRODUCTPRICE, PRODUCTAVAILABLEQUANTITY FROM PRODUCTS";
        String Purchases = "SELECT CUSTOMERID, PRODUCTID, QUANTITY FROM PURCHASES";

        try (Connection connection = this.connect()) {
            Statement CustomersStatement = connection.createStatement();
            ResultSet CustomersSet = CustomersStatement.executeQuery(Customers);
            Statement ProductsStatements = connection.createStatement();
            ResultSet ProductsSet = ProductsStatements.executeQuery(Products);
            Statement PurchasesStatements = connection.createStatement();
            ResultSet PurchasesSet = PurchasesStatements.executeQuery(Purchases);

            switch (table) {
                case "Customers":
                    while (CustomersSet.next()) {
                        listModel.addElement(CustomersSet.getString("CUSTOMERID") + ", " +
                                CustomersSet.getString("CUSTOMERNAME") + ", " +
                                CustomersSet.getString("CUSTOMERADDRESS") + ", " +
                                CustomersSet.getString("CUSTOMERPHONE"));
                    }
                    break;
                case "Products":
                    while (ProductsSet.next()) {
                        listModel.addElement(ProductsSet.getString("PRODUCTID") + ", " +
                                ProductsSet.getString("PRODUCTNAMEANDDESCRIPTION") + ", " +
                                ProductsSet.getString("PRODUCTPRICE") + ", " +
                                ProductsSet.getString("PRODUCTAVAILABLEQUANTITY"));
                    }
                    break;
                case "Purchases":
                    while (PurchasesSet.next()) {
                        listModel.addElement(PurchasesSet.getString("CUSTOMERID") + ", " +
                                PurchasesSet.getString("PRODUCTID") + ", " +
                                PurchasesSet.getString("QUANTITY"));
                    }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listModel;
    }
}
