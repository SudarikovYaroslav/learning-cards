package GUI;
import basicClasses.Dictionary;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardBuilderGUI implements GUI {

    private final BasicGUI basicGUI;
    private Dictionary dictionary;
    private JTextArea frontSide;
    private JTextArea backSide;
    private View view;

    public CardBuilderGUI(BasicGUI basicGUI, Dictionary dictionary, View view) {
        this.basicGUI = basicGUI;
        this.dictionary = dictionary;
        this.view = view;
    }

    public void go(){
        view.setCurrentGUI(this);
        basicGUI.frame.setTitle("Card Builder");
        frontSide = new JTextArea(6,20);
        frontSide.setLineWrap(true);
        frontSide.setWrapStyleWord(true);
        frontSide.setFont(basicGUI.bigFont);
        JScrollPane frontScroller = new JScrollPane(frontSide);
        frontScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frontScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        backSide = new JTextArea(6 ,20);
        backSide.setLineWrap(true);
        backSide.setWrapStyleWord(true);
        backSide.setFont(basicGUI.bigFont);
        JScrollPane backScroller = new JScrollPane(backSide);
        backScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        backScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel frontLabel = new JLabel("Question: ");
        JLabel backLabel = new JLabel("Answer: ");

        basicGUI.mainPanel.add(frontLabel);
        basicGUI.mainPanel.add(frontScroller);
        basicGUI.mainPanel.add(backLabel);
        basicGUI.mainPanel.add(backScroller);

        JButton createCardButton = new JButton("Create Card");
        JButton editCardButton = new JButton("Edit Card");
        JButton deleteCardButton = new JButton("Delete Card");
        JButton cancelButton = new JButton("Cancel");

        createCardButton.addActionListener(new CreateCardListener());
        editCardButton.addActionListener(new EditCardListener());
        deleteCardButton.addActionListener(new DeleteCardListener());
        cancelButton.addActionListener(new CancelListener());

        basicGUI.buttonsPanel.add(createCardButton);
        basicGUI.buttonsPanel.add(editCardButton);
        basicGUI.buttonsPanel.add(deleteCardButton);
        basicGUI.buttonsPanel.add(cancelButton);

        basicGUI.go();
    }


    private void clearCard(){
        frontSide.setText("");
        backSide.setText("");
        frontSide.requestFocus();
    }


    private static class CreateCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    private static class EditCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    private static class DeleteCardListener implements ActionListener{
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
