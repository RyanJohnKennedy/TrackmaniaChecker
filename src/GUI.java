import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField outputTextField;

    public GUI() {
        frame = new JFrame("Trackmania Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        outputTextField = new JTextField(20);
        outputTextField.setEditable(false);
        panel.add(outputTextField);

        JButton navigateButton = new JButton("Navigate");
        navigateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigateToInputUI();
            }
        });
        panel.add(navigateButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void navigateToInputUI() {
        frame.getContentPane().removeAll();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JTextField inputTextField = new JTextField(20);
        inputPanel.add(inputTextField);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JSONObject obj = HttpConnection.HttpGETPlayer(inputTextField.getText());
                if (obj != null){
                    Player player = new Player(inputTextField.getText(), obj.getString(inputTextField.getText()));
                    System.out.println("0");
                    System.out.println("Player Found: " + player.Name + " ID: " + player.ID);
                    System.out.println("1");
                    Main.players = player;
                    System.out.println("2");
                    System.out.println(HttpConnection.HttpGETCurrentCampaign());

                }
                //navigateBack();
            }
        });
        inputPanel.add(enterButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigateBack();
            }
        });
        inputPanel.add(backButton);

        frame.add(inputPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void navigateBack() {
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
}
