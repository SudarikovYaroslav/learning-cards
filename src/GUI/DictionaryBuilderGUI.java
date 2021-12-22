package GUI;

import basicClasses.Dictionary;
import basicClasses.User;
import controller.Controller;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DictionaryBuilderGUI implements GUI{

    private final BasicGUI basicGUI;
    private final Controller controller;
    private final View view;
    private final User currentUser;
    private String dictionaryName;
    private JTextField jTextField;

    public DictionaryBuilderGUI(BasicGUI basicGUI, Controller controller, View view, User currentUser) {
        this.basicGUI = basicGUI;
        this.controller = controller;
        this.view = view;
        this.currentUser = currentUser;
        dictionaryName = null;
    }

    @Override
    public void go() {
        basicGUI.clear();
        view.setCurrentGUI(this);

        JLabel label = new JLabel("Enter Dictionary name please:");

        jTextField = new JTextField(20);
        jTextField.requestFocus();
        jTextField.addActionListener(new TextFieldListener());

        JButton createButton = new JButton("Create new dictionary");
        JButton cancelButton = new JButton("Cancel");
        createButton.addActionListener(new CreateButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());

        basicGUI.mainPanel.add(BorderLayout.NORTH, label);
        basicGUI.mainPanel.add(BorderLayout.CENTER, jTextField);

        basicGUI.buttonsPanel.add(createButton);
        basicGUI.buttonsPanel.add(cancelButton);

        basicGUI.go();
    }


    private class TextFieldListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dictionaryName = jTextField.getText();
        }
    }


    private class CreateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (dictionaryName != null) {
                controller.createDictionary(currentUser, dictionaryName);
            }
        }
    }

    private class CancelButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            UserProfileGUI userProfileGUI = new UserProfileGUI(basicGUI,controller,currentUser,view);
            userProfileGUI.go();
        }
    }
}
