package GUI;
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


public class UserProfileGUI implements GUI {

    private View view;
    private Controller controller;
    private final BasicGUI basicGUI;
    private User user;
    private JList<Dictionary> dictionariesJList;
    private JLabel label;


    public UserProfileGUI(BasicGUI basicGUI, Controller controller, User currentUser, View view) {
        this.basicGUI = basicGUI;
        this.controller = controller;
        user = currentUser;
        this.view = view;
    }


    public void go(){
        basicGUI.clear();
        view.setCurrentGUI(this);
        basicGUI.frame.setTitle(user.getName() + " profile");

        label = new JLabel("Your Dictionaries");

        Dictionary[] listEntry = user.getDictionaries().toArray(new Dictionary[0]);
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
        JButton removeDictionaryButton = new JButton("Remove Dictionary");
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



    private class JListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }

    private class ChangeUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            UserSelectGUI userSelectGUI = new UserSelectGUI(basicGUI, controller, view);
            userSelectGUI.go();
        }
    }

    private class StartTrainingListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class RemoveDictionaryListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class CreateNewDictionaryListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            DictionaryBuilderGUI dictionaryBuilderGUI = new DictionaryBuilderGUI(basicGUI, controller,view, user);
            dictionaryBuilderGUI.go();
        }
    }

    private class StartFailsRepetitionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
}
