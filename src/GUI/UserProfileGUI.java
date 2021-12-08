package GUI;
import basicClasses.Dictionary;
import basicClasses.User;
import view.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfileGUI implements GUI {

    private View view;
    private final BasicGUI basicGUI;
    private User user;
    private JList<Dictionary> dictionariesJList;
    private JLabel label;


    public UserProfileGUI(BasicGUI basicGUI, User currentUser, View view) {
        this.basicGUI = basicGUI;
        user = currentUser;
        this.view = view;
    }


    public void go(){
        view.setCurrentGUI(this);
        basicGUI.frame.setTitle(user.getName() + " profile");

        label = new JLabel("Your Dictionaries");

        Dictionary[] listEntry = basicGUI.viewFacade.getUserDictionaries().toArray(new Dictionary[0]);
        dictionariesJList = new JList<>(listEntry);
        dictionariesJList.setVisibleRowCount(10);
        dictionariesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dictionariesJList.addListSelectionListener(new JListListener());

        JScrollPane scroller = new JScrollPane();
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        basicGUI.mainPanel.add(BorderLayout.NORTH, label);
        basicGUI.mainPanel.add(BorderLayout.CENTER, dictionariesJList);
        basicGUI.mainPanel.add(scroller);

        JButton changeUserButton = new JButton("Change User");
        JButton startTrainingButton = new JButton("Start Training");
        JButton removeDictionaryButton = new JButton("Create new Dictionary");
        JButton createNewDictionaryButton = new JButton("Create new Dictionary");
        JButton startFailsRepetitionButton = new JButton("Start fails repetition");

        changeUserButton.addActionListener(new ChangeUserListener());
        startTrainingButton.addActionListener(new StartTrainingListener());
        removeDictionaryButton.addActionListener(new RemoveDictionaryListener());
        createNewDictionaryButton.addActionListener(new CreateNewDictionaryListener());
        startFailsRepetitionButton.addActionListener(new StartFailsRepetitionListener());

        basicGUI.buttonsPanel.add(createNewDictionaryButton);
        basicGUI.buttonsPanel.add(removeDictionaryButton);
        basicGUI.buttonsPanel.add(startTrainingButton);
        basicGUI.buttonsPanel.add(startFailsRepetitionButton);
        basicGUI.buttonsPanel.add(changeUserButton);

        basicGUI.go();
    }



    private static class JListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }

    private static class ChangeUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class StartTrainingListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class RemoveDictionaryListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class CreateNewDictionaryListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class StartFailsRepetitionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
}
