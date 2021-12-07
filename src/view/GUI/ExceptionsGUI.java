package view.GUI;

import model.facade.ViewFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExceptionsGUI {

    private final BasicGUI basicGUI;
    private final String message;


    public ExceptionsGUI(BasicGUI basicGUI, String message) {
        this.basicGUI = basicGUI;
        this.message = message;
    }


    public void go(){
        JTextArea textArea = new JTextArea(message);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new OKListener());
        basicGUI.mainPanel.add(BorderLayout.CENTER, textArea);
        basicGUI.mainPanel.add(BorderLayout.SOUTH, okButton);
        basicGUI.frameGo();
    }


    private static class OKListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
