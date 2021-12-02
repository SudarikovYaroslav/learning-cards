package view.GUI;

import javax.swing.*;
import java.awt.*;

public class UserBuilderGUI extends BasicGUI {

    JButton createUserButton = new JButton("Create User");
    JButton cancelButton = new JButton("Cancel");
    JTextField nameField = new JTextField(20);
    JLabel label = new JLabel("Please enter your name:");

    public void go(){
        buttonsPanel.add(createUserButton);
        buttonsPanel.add(cancelButton);

        mainPanel.add(BorderLayout.WEST, label);
        mainPanel.add(BorderLayout.CENTER, nameField);

        this.frameGo();
    }
}
