package view.GUI;
import basicClasses.Card;
import basicClasses.Dictionary;
import model.Model;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class DictionaryGUI extends BasicGUI{

    private Model model;
    private Dictionary dictionary;

    public DictionaryGUI(Model model, Dictionary dictionary) {
        this.model = model;
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
        JButton editCardButton = new JButton("Edit Card");
        JButton deleteCardButton = new JButton("Delete Card");

        buttonsPanel.add(createCardButton);
        buttonsPanel.add(editCardButton);
        buttonsPanel.add(deleteCardButton);

        this.frameGo();
    }


    private class CardListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }
}
