package GUI;

import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagesGUI implements GUI {

    private final BasicGUI basicGUI;
    private final String message;
    private final GUI previousGUI;
    private final View view;

    public MessagesGUI(BasicGUI basicGUI, GUI previousGUI, String message, View view) {
        this.basicGUI = basicGUI;
        this.previousGUI = previousGUI;
        this.message = message;
        this.view = view;
    }

    /**
     * Важно! При запуске MessagesGUI не передавать во view.currentGUI на него ссылку, иначе не получиться
     * вернуться на предыдущий GUI
     */
    public void go() {
        basicGUI.clear();
        JTextArea textArea = new JTextArea(message);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new OKListener());
        basicGUI.mainPanel.add(BorderLayout.CENTER, textArea);
        basicGUI.buttonsPanel.add(BorderLayout.SOUTH, okButton);
        basicGUI.go();
    }

    private class OKListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            previousGUI.go();
        }
    }
}
