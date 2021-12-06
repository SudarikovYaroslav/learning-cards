package view.GUI;

import basicClasses.Card;
import basicClasses.Dictionary;
import model.facade.ViewFacade;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DictionaryGUI extends BasicGUI{

    private Dictionary dictionary;

    public DictionaryGUI(ViewFacade facade, Dictionary dictionary) {
        super(facade);
        this.dictionary = dictionary;
    }

    public void go(){
        frame.setTitle("Dictionary: " + dictionary.getName());

        Card[] listEntries = dictionary.getCards().toArray(new Card[0]);
        JList<Card> cardsJList = new JList<>(listEntries);
        JScrollPane listScroller = new JScrollPane(cardsJList);
        listScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cardsJList.setVisibleRowCount(10);
        cardsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cardsJList.addListSelectionListener(new CardListSelectionListener());

        JLabel label = new JLabel(dictionary.getName());

        mainPanel.add(BorderLayout.SOUTH, cardsJList);
        mainPanel.add(BorderLayout.NORTH, label);

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

        buttonsPanel.add(createCardButton);
        buttonsPanel.add(deleteCardButton);
        buttonsPanel.add(editCardButton);
        buttonsPanel.add(startTrainingButton);

        this.frameGo();
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
