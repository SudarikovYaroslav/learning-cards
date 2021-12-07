package view.GUI;

import basicClasses.Card;
import basicClasses.Dictionary;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DictionaryGUI {

    private final BasicGUI basicGUI;
    private Dictionary dictionary;

    public DictionaryGUI(BasicGUI basicGUI, Dictionary dictionary) {
        this.basicGUI = basicGUI;
        this.dictionary = dictionary;
    }

    public void go(){
        basicGUI.frame.setTitle("Dictionary: " + dictionary.getName());

        Card[] listEntries = dictionary.getCards().toArray(new Card[0]);
        JList<Card> cardsJList = new JList<>(listEntries);
        JScrollPane listScroller = new JScrollPane(cardsJList);
        listScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cardsJList.setVisibleRowCount(10);
        cardsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cardsJList.addListSelectionListener(new CardListSelectionListener());

        JLabel label = new JLabel(dictionary.getName());

        basicGUI.mainPanel.add(BorderLayout.SOUTH, cardsJList);
        basicGUI.mainPanel.add(BorderLayout.NORTH, label);

        JButton createCardButton = new JButton("Create new Card");
        JButton deleteCardButton = new JButton("Delete Card");
        JButton editCardButton = new JButton("Edit Card");
        JButton startTrainingButton = new JButton("Start Training");
        JButton changeDictionaryName = new JButton("Change Dictionary name");

        createCardButton.addActionListener(new CreateCardListener());
        deleteCardButton.addActionListener(new DeleteCardListener());
        editCardButton.addActionListener(new EditCardListener());
        startTrainingButton.addActionListener(new StartTrainingListener());
        changeDictionaryName.addActionListener(new ChangeNameListener());

        basicGUI.buttonsPanel.add(createCardButton);
        basicGUI.buttonsPanel.add(deleteCardButton);
        basicGUI.buttonsPanel.add(editCardButton);
        basicGUI.buttonsPanel.add(startTrainingButton);

        basicGUI.frameGo();
    }


    private static class CardListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }


    private static class CreateCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    private static class DeleteCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    private static class EditCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    private static class StartTrainingListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    private static class ChangeNameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
