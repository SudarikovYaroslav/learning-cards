package view.GUI;
import basicClasses.Dictionary;
import basicClasses.User;
import facade.FacadeForView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class UserProfileGUI extends BasicGUI{

    private User user;
    private JList<Dictionary> dictionariesJList;
    private JLabel label;


    public UserProfileGUI(FacadeForView facade, User currentUser) {
        super(facade);
        user = currentUser;
    }


    public void go(){
        frame.setTitle(user.getName() + " profile");

        label = new JLabel("Your Dictionaries");

        Dictionary[] listEntry = facade.getUserDictionaries().toArray(new Dictionary[0]);
        dictionariesJList = new JList<>(listEntry);
        dictionariesJList.setVisibleRowCount(10);
        dictionariesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dictionariesJList.addListSelectionListener(new JListListener());

        JScrollPane scroller = new JScrollPane();
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(BorderLayout.NORTH, label);
        mainPanel.add(BorderLayout.CENTER, dictionariesJList);
        mainPanel.add(scroller);

        JButton changeUserButton = new JButton("Change User");
        JButton startTrainingButton = new JButton("Start Training");
        JButton removeDictionaryButton = new JButton("Create new Dictionary");
        JButton createNewDictionaryButton = new JButton("Create new Dictionary");
        JButton startFailsRepetitionButton = new JButton("Start fails repetition");

        buttonsPanel.add(createNewDictionaryButton);
        buttonsPanel.add(removeDictionaryButton);
        buttonsPanel.add(startTrainingButton);
        buttonsPanel.add(startFailsRepetitionButton);
        buttonsPanel.add(changeUserButton);

        this.frameGo();
    }



    private class JListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }
}
