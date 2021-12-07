package view.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserBuilderGUI {

    private final BasicGUI basicGUI;
    private JButton createUserButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JLabel label;


    public UserBuilderGUI(BasicGUI basicGUI) {
        this.basicGUI = basicGUI;
    }


    public void go(){

        createUserButton = new JButton("Create User");
        cancelButton = new JButton("Cancel");

        createUserButton.addActionListener(new CreateUserListener());
        cancelButton.addActionListener(new CancelListener());

        nameField = new JTextField(20);
        label = new JLabel("Please enter your name:");

        basicGUI.buttonsPanel.add(createUserButton);
        basicGUI.buttonsPanel.add(cancelButton);

        basicGUI.mainPanel.add(BorderLayout.WEST, label);
        basicGUI.mainPanel.add(BorderLayout.CENTER, nameField);

        basicGUI.frameGo();
    }


    private static class CreateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class CancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
