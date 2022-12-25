package GUI;

import controller.Controller;
import model.basicClasses.Dictionary;
import model.basicClasses.User;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenameDictionaryGUI implements GUI {

    public static final int POSTFIX_LENGTH = 4;

    private final BasicGUI basicGUI;
    private final Dictionary dictionary;
    private final User currentUser;
    private final View view;
    private final Controller controller;
    private String newDictionaryName = null;
    private JTextField textField;
    private JLabel label;

    public RenameDictionaryGUI(BasicGUI basicGUI,
                               Dictionary dictionary,
                               User currentUser,
                               View view,
                               Controller controller) {
        this.basicGUI = basicGUI;
        this.dictionary = dictionary;
        this.currentUser = currentUser;
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void go() {
        basicGUI.clear();
        view.setCurrentGUI(this);

        basicGUI.frame.setTitle("Dictionary: "
                + dictionary.getName().substring(0, dictionary.getName().length() - POSTFIX_LENGTH));

        label = new JLabel("Enter new Dictionary name please and push \"Enter\" button:");

        textField = new JTextField(20);
        textField.requestFocus();
        textField.addActionListener(new TextFieldListener());

        JButton setNameButton = new JButton("Set new name");
        setNameButton.addActionListener(new SetNameButtonListener());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelButtonListener());

        basicGUI.mainPanel.add(BorderLayout.NORTH, label);
        basicGUI.mainPanel.add(textField);
        basicGUI.buttonsPanel.add(setNameButton);
        basicGUI.buttonsPanel.add(cancelButton);

        basicGUI.go();
    }

    private class TextFieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            newDictionaryName = textField.getText();
            label.setText("Dictionary name accepted!");
            basicGUI.go();
        }
    }

    private class SetNameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean success;

            if (newDictionaryName != null) {
                success = controller.setDictionaryName(currentUser, dictionary, newDictionaryName);

                if (success) {
                    view.printMessage("Dictionary has been successfully renamed!");
                    DictionaryGUI dictionaryGUI = new DictionaryGUI(basicGUI, dictionary, currentUser, view,
                            controller);
                    dictionaryGUI.go();
                } else {
                    view.printMessage("Dictionary Rename Error!");
                }

            } else {
                label.setText("PUSH \"ENTER\"BUTTON !!!");
                basicGUI.go();
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DictionaryGUI dictionaryGUI = new DictionaryGUI(basicGUI, dictionary, currentUser, view, controller);
            dictionaryGUI.go();
        }
    }
}
