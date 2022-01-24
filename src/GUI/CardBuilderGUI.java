package GUI;
import basicClasses.Card;
import basicClasses.Dictionary;
import basicClasses.User;
import controller.Controller;
import view.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardBuilderGUI implements GUI {

    private final BasicGUI basicGUI;
    private final Font font = new Font("TimesRoman", Font.BOLD, 16);
    private Dictionary dictionary;
    private JTextArea frontSide;
    private JTextArea backSide;
    private View view;
    private Controller controller;
    private User currentUser;
    private Card editableCard;

    public CardBuilderGUI(BasicGUI basicGUI, User currentUser, Dictionary dictionary, View view, Controller controller) {
        this.basicGUI = basicGUI;
        this.dictionary = dictionary;
        this.view = view;
        this.currentUser = currentUser;
        this.controller = controller;
        editableCard = null;
    }


    public void go(){
        view.setCurrentGUI(this);
        basicGUI.clear();
        basicGUI.frame.setTitle("Card Builder");

        frontSide = new JTextArea(9,30);
        frontSide.setLineWrap(true);
        frontSide.setWrapStyleWord(true);
        frontSide.setFont(font);
        //frontSide.setFont(basicGUI.bigFont);
        JScrollPane frontScroller = new JScrollPane(frontSide);
        frontScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frontScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        backSide = new JTextArea(9 ,30);
        backSide.setLineWrap(true);
        backSide.setWrapStyleWord(true);
        backSide.setFont(font);
        //backSide.setFont(basicGUI.bigFont);
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


    private class CreateCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String front = frontSide.getText();
            String back = backSide.getText();

            if ((front.length() > 0) && (back.length() > 0)) {
                if (controller.createCard(currentUser, dictionary, front, back)) {
                    DictionaryGUI dictionaryGUI = new DictionaryGUI(basicGUI, dictionary, currentUser, view, controller);
                    dictionaryGUI.go();
                } else {
                    view.printMessage("Can't create new card (((");
                }
            }
        }
    }


    private class EditCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String front = frontSide.getText();
            String back = backSide.getText();

            if ((front.length() > 0) && (back.length() > 0)) {
                boolean success = controller.editCard(currentUser, dictionary, front, back);
                if (success){
                    dictionary.editCard(editableCard, front, back);
                    DictionaryGUI dictionaryGUI = new DictionaryGUI(basicGUI, dictionary, currentUser, view, controller);
                    dictionaryGUI.go();
                } else {
                    view.printMessage("Edit doesn't success! There' some troubles in process ((");
                }
            }
        }
    }


    private class DeleteCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    private class CancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            DictionaryGUI dictionaryGUI = new DictionaryGUI(basicGUI, dictionary, currentUser, view, controller);
            dictionaryGUI.go();
        }
    }


    //This method called from DictionaryGUI to start editCard process
    protected void editCard(Card card){
        editableCard = card;
        go();
        frontSide.setText(card.getFront());
        backSide.setText(card.getBack());
    }
}
