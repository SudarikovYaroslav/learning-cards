package view.GUI;

import model.facade.ViewFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExceptionsGUI extends BasicGUI{

    JTextArea textArea;
    JButton okButton;


    public ExceptionsGUI(ViewFacade facade, String message) {
        super(facade);
        textArea = new JTextArea(message);
        okButton = new JButton("OK");
        okButton.addActionListener(new OKListener());
    }


    public void go(){
        mainPanel.add(BorderLayout.CENTER, textArea);
        mainPanel.add(BorderLayout.SOUTH, okButton);
        frameGo();
    }


    private static class OKListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
