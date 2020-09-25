package screens;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class store extends JFrame {
    private JPanel purchasePanel;
    private JLabel purchaseLabel;
    private JTextField purchaseNameTextField;
    private JTextField purchaseQuantityTextField;
    private JTextField purchasePriceTextField;
    private JButton purchaseCalculateButton;
    private JLabel purchaseTotalLabel;

    public store(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(purchasePanel);
        this.pack();

        purchaseCalculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity;
                double price;

                quantity = (int)(Integer.parseInt(purchaseQuantityTextField.getText()));
                price = Double.parseDouble(purchasePriceTextField.getText());
                purchaseTotalLabel.setText("Total: " + quantity * price);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new store("Mr. Smith's Store");
        frame.setVisible(true);
    }
}
