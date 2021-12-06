package view.GUI;

import model.facade.ViewFacade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserBuilderGUI extends BasicGUI {

    JButton createUserButton;
    JButton cancelButton;
    JTextField nameField;
    JLabel label;


    public UserBuilderGUI(ViewFacade facade) {
        super(facade);
    }


    public void go(){

        createUserButton = new JButton("Create User");
        cancelButton = new JButton("Cancel");

        createUserButton.addActionListener(new CreateUserListener());
        cancelButton.addActionListener(new CancelListener());

        nameField = new JTextField(20);
        label = new JLabel("Please enter your name:");

        buttonsPanel.add(createUserButton);
        buttonsPanel.add(cancelButton);

        mainPanel.add(BorderLayout.WEST, label);
        mainPanel.add(BorderLayout.CENTER, nameField);

        this.frameGo();
    }


    private static class CreateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*UserSelectGUI usGUI = new UserSelectGUI();
            usGUI.go();*/
        }
    }

    private static class CancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
