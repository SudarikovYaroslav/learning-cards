package GUI;

import controller.Controller;
import model.basicClasses.Card;
import model.basicClasses.Dictionary;
import model.basicClasses.User;
import view.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class DictionaryGUI implements GUI {

    private final BasicGUI basicGUI;
    private final Dictionary dictionary;
    private final View view;
    private final Controller controller;
    private final User currentUser;
    private final LinkedList<Card> cards;
    private JList<String> cardsJList;
    private Card currentCard;

    public DictionaryGUI(BasicGUI basicGUI, Dictionary dictionary, User currentUser, View view, Controller controller) {
        this.basicGUI = basicGUI;
        this.dictionary = dictionary;
        this.currentUser = currentUser;
        this.view = view;
        this.controller = controller;
        currentCard = null;
        cardsJList = null;
        cards = dictionary.getCards();
    }

    public void go() {
        view.setCurrentGUI(this);
        basicGUI.clear();

        basicGUI.frame.setTitle("Dictionary: " + dictionary.getName().substring(0, dictionary.getName().length() - 4));

        cardsJList = new JList<>(getJListEntry(dictionary));
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
        basicGUI.buttonsPanel.add(changeDictionaryName);
        basicGUI.buttonsPanel.add(cancelButton);

        basicGUI.go();
    }

    private String[] getCardText(Card[] cards) {
        String[] cardText = new String[cards.length];

        for (int i = 0; i < cards.length; i++) {
            cardText[i] = cards[i].getFront();
        }

        return cardText;
    }

    private String[] getJListEntry(Dictionary dictionary) {
        Card[] listEntries = dictionary.getCards().toArray(new Card[0]);
        return getCardText(listEntries);
    }

    private class CardListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String cardFront = cardsJList.getSelectedValue();

                for (Card card : cards) {
                    if (card.getFront().equals(cardFront)) {
                        currentCard = card;
                        return;
                    }
                }
            }
        }
    }

    private class CreateCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardBuilderGUI cardBuilderGUI = new CardBuilderGUI(basicGUI, currentUser, dictionary, view, controller);
            cardBuilderGUI.go();
        }
    }

    private class DeleteCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentCard != null) {

                boolean success = controller.deleteCard(currentUser, dictionary, currentCard);

                if (!success) {
                    view.printMessage("The card hasn't been deleted! Something wrong (((");
                } else {
                    dictionary.getCards().remove(currentCard);
                    go();
                }

            }
        }
    }

    private class EditCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentCard != null) {
                CardBuilderGUI cardBuilderGUI = new CardBuilderGUI(basicGUI, currentUser, dictionary, view, controller);
                cardBuilderGUI.editCard(currentCard);
            }
        }
    }

    private class StartTrainingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TrainingGUI trainingGUI = new TrainingGUI(basicGUI, view, controller, dictionary, view.getCurrentGUI(),
                    currentUser);
            trainingGUI.trainingGO();
        }
    }

    private class ChangeNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            RenameDictionaryGUI renameDictionaryGUI = new RenameDictionaryGUI(basicGUI, dictionary, currentUser, view,
                    controller);
            renameDictionaryGUI.go();
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
