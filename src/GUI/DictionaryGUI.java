package GUI;

import basicClasses.Card;
import basicClasses.Dictionary;
import basicClasses.User;
import controller.Controller;
import view.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DictionaryGUI implements GUI {

    private final BasicGUI basicGUI;
    private Dictionary dictionary;
    private View view;
    private Controller controller;
    private User currentUser;

    public DictionaryGUI(BasicGUI basicGUI, Dictionary dictionary, User currentUser, View view, Controller controller) {
        this.basicGUI = basicGUI;
        this.dictionary = dictionary;
        this.currentUser = currentUser;
        this.view = view;
        this.controller = controller;
    }

    public void go(){
        view.setCurrentGUI(this);
        basicGUI.clear();

        basicGUI.frame.setTitle("Dictionary: " + dictionary.getName().substring(0, dictionary.getName().length() - 4));

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
        JButton cancelButton = new JButton("Cancel");

        createCardButton.addActionListener(new CreateCardListener());
        deleteCardButton.addActionListener(new DeleteCardListener());
        editCardButton.addActionListener(new EditCardListener());
        startTrainingButton.addActionListener(new StartTrainingListener());
        changeDictionaryName.addActionListener(new ChangeNameListener());
        cancelButton.addActionListener(new CancelListener());

        basicGUI.buttonsPanel.add(createCardButton);
        basicGUI.buttonsPanel.add(deleteCardButton);
        basicGUI.buttonsPanel.add(editCardButton);
        basicGUI.buttonsPanel.add(startTrainingButton);
        basicGUI.buttonsPanel.add(cancelButton);

        basicGUI.go();
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


    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserProfileGUI userProfileGUI = new UserProfileGUI(basicGUI, controller, currentUser, view);
            userProfileGUI.go();
        }
    }
}
