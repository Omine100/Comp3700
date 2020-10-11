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

    public screen_main(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String table = tableTextField.getText();
                printRows(table, contentList);
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

    public JList printRows(String table, JList contentList) {
        String CUSTOMERS = "SELECT CUSTOMERID, CUSTOMERNAME, CUSTOMERADDRESS, CUSTOMERPHONE FROM CUSTOMERS";
        String PRODUCTS = "SELECT PRODUCTID, PRODUCTNAMEANDDESCRIPTION, PRODUCTPRICE, PRODUCTAVAILABLEQUANTITY FROM PRODUCTS";
        String PURCHASES = "SELECT CUSTOMERID, PRODUCTID, QUANTITY FROM PURCHASES";

        try (Connection connection = this.connect()) {
            Statement Statement = connection.createStatement();
            ResultSet Set = Statement.executeQuery(table);

            switch (table) {
                case "CUSTOMERS":
                    while (Set.next()) {
                        contentList.add(new Label(Set.getString("CUSTOMERID") + ", " +
                                Set.getString("CUSTOMERNAME") + ", " +
                                Set.getString("CUSTOMERADDRESS") + ", " +
                                Set.getString("CUSTOMERPHONE")));
                    }
                case "PRODUCTS":
                    while (Set.next()) {
                        contentList.add(new Label(Set.getString("PRODUCTID") + ", " +
                                Set.getString("PRODUCTNAMEANDDESCRIPTION") + ", " +
                                Set.getString("PRODUCTPRICE") + ", " +
                                Set.getString("PRODUCTAVAILABLEQUANTITY")));
                    }
                case "PURCHASES":
                    while (Set.next()) {
                        contentList.add(new Label(Set.getString("CUSTOMERID") + ", " +
                                Set.getString("PRODUCTID") + ", " +
                                Set.getString("QUANTITY")));
                    }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return contentList;
    }
}
