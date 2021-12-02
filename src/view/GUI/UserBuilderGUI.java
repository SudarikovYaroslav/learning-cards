package view.GUI;

import facade.ViewFacade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserBuilderGUI extends BasicGUI {

    JButton createUserButton = new JButton("Create User");
    JButton cancelButton = new JButton("Cancel");
    JTextField nameField = new JTextField(20);
    JLabel label = new JLabel("Please enter your name:");


    public UserBuilderGUI(ViewFacade facade) {
        super(facade);
    }


    public void go(){
        cancelButton.addActionListener(new CreateUserListener());

        buttonsPanel.add(createUserButton);
        buttonsPanel.add(cancelButton);

        mainPanel.add(BorderLayout.WEST, label);
        mainPanel.add(BorderLayout.CENTER, nameField);

        this.frameGo();
    }


    // Listeners:
    static class CreateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*UserSelectGUI usGUI = new UserSelectGUI();
            usGUI.go();*/
        }
    }
}
