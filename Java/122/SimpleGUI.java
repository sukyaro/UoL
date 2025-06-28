import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("My First GUI");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Using absolute layout for simplicity

        // Create a label
        JLabel label = new JLabel("Hello, click the button!", SwingConstants.CENTER);
        label.setBounds(50, 20, 200, 20);
        frame.add(label);

        // Create a button
        JButton button = new JButton("Click Me");
        button.setBounds(90, 60, 100, 30);
        frame.add(button);

        // Add action listener to the button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Button clicked!");
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}
