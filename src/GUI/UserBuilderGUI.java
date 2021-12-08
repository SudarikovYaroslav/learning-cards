package GUI;

import basicClasses.User;
import controller.Controller;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserBuilderGUI implements GUI {

    private View view;
    private final BasicGUI basicGUI;
    private final Controller controller;
    private JTextField nameField;


    public UserBuilderGUI(BasicGUI basicGUI, Controller controller, View view) {
        this.basicGUI = basicGUI;
        this.controller = controller;
        this.view = view;
    }


    public void go(){
        view.setCurrentGUI(this);
        basicGUI.clear();

        JButton createUserButton = new JButton("Create User");
        JButton cancelButton = new JButton("Cancel");

        createUserButton.addActionListener(new CreateUserListener());
        cancelButton.addActionListener(new CancelListener());

        nameField = new JTextField(20);
        JLabel label = new JLabel("Please enter your name:");

        basicGUI.buttonsPanel.add(createUserButton);
        basicGUI.buttonsPanel.add(cancelButton);

        basicGUI.mainPanel.add(BorderLayout.WEST, label);
        basicGUI.mainPanel.add(BorderLayout.CENTER, nameField);

        basicGUI.go();
    }


    private class CreateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User newUser = new User(nameField.getText());

            if (controller.addNewUser(newUser)) {
                UserSelectGUI userSelectGUI = new UserSelectGUI(basicGUI, controller, view);
                userSelectGUI.go();
            }
        }
    }

    private class CancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            UserSelectGUI userSelectGUI = new UserSelectGUI(basicGUI, controller, view);
            userSelectGUI.go();
        }
    }
}
