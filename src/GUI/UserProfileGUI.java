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
import java.util.ArrayList;


public class UserProfileGUI implements GUI {

    private View view;
    private Controller controller;
    private final BasicGUI basicGUI;
    private User user;
    private JList<String> dictionariesJList;
    private JLabel label;
    private ArrayList<Dictionary> userDictionaries;
    private Dictionary currentDictionary;

    public UserProfileGUI(BasicGUI basicGUI, Controller controller, User currentUser, View view) {
        this.basicGUI = basicGUI;
        this.controller = controller;
        user = currentUser;
        this.view = view;
        userDictionaries = currentUser.getDictionaries();
        createDictionariesJList();
        currentDictionary = null;
    }


    public void go(){
        basicGUI.clear();
        view.setCurrentGUI(this);
        basicGUI.frame.setTitle(user.getName() + " profile");

        label = new JLabel("Your Dictionaries");

        createDictionariesJList();
        dictionariesJList.setVisibleRowCount(16);
        dictionariesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dictionariesJList.addListSelectionListener(new JListListener());

        JScrollPane scroller = new JScrollPane(dictionariesJList);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        basicGUI.mainPanel.add(BorderLayout.NORTH, label);
        basicGUI.mainPanel.add(BorderLayout.CENTER, scroller);
        basicGUI.mainPanel.add(scroller);

        JButton changeUserButton = new JButton("Change User");
        JButton startTrainingButton = new JButton("Start Training");
        JButton editDictionaryButton = new JButton("Edit Dictionary");
        JButton deleteDictionaryButton = new JButton("Delete Dictionary");
        JButton startMultiTrainingButton = new JButton("Start MultiTraining");
        JButton createNewDictionaryButton = new JButton("Create new Dictionary");
        JButton startFailsRepetitionButton = new JButton("Start fails repetition");

        changeUserButton.addActionListener(new ChangeUserListener());
        startTrainingButton.addActionListener(new StartTrainingListener());
        editDictionaryButton.addActionListener(new EditDictionaryListener());
        startMultiTrainingButton.addActionListener(new MultiTrainingListener());
        deleteDictionaryButton.addActionListener(new DeleteDictionaryListener());
        createNewDictionaryButton.addActionListener(new CreateNewDictionaryListener());
        startFailsRepetitionButton.addActionListener(new StartFailsRepetitionListener());


        basicGUI.buttonsPanel.add(createNewDictionaryButton);
        basicGUI.buttonsPanel.add(editDictionaryButton);
        basicGUI.buttonsPanel.add(deleteDictionaryButton);
        basicGUI.buttonsPanel.add(startTrainingButton);
        basicGUI.buttonsPanel.add(startMultiTrainingButton);
        basicGUI.buttonsPanel.add(startFailsRepetitionButton);
        basicGUI.buttonsPanel.add(changeUserButton);

        basicGUI.go();
    }


    private void createDictionariesJList(){
        String[] dictionariesNames = new String[userDictionaries.size()];

        for (int i = 0; i < dictionariesNames.length; i++) {
            String[] arr = userDictionaries.get(i).getName().split(".txt");
            dictionariesNames[i] = arr[0];
        }

        dictionariesJList = new JList<>(dictionariesNames);
    }


    private class JListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (! e.getValueIsAdjusting()){
                String selectedDictionaryName = dictionariesJList.getSelectedValue();

                for (Dictionary dic : userDictionaries){
                    if (dic.getName().equals(selectedDictionaryName + ".txt")){
                        currentDictionary = dic;
                        return;
                    }
                }

            }
        }
    }


    private class EditDictionaryListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentDictionary != null) {
                DictionaryGUI dictionaryGUI = new DictionaryGUI(basicGUI, currentDictionary, user, view, controller);
                dictionaryGUI.go();
            }
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
            if (currentDictionary != null) {
                if (currentDictionary.getName().equals("FailsList.txt")){
                 // here should be run fails repetition training
                    TrainingGUI failsTraining = new TrainingGUI(basicGUI, view, controller, view.getCurrentGUI(), user);
                    failsTraining.startFailsRepetition();
                } else {
                    TrainingGUI trainingGUI = new TrainingGUI(basicGUI, view, controller, currentDictionary, view.getCurrentGUI(), user);
                    trainingGUI.trainingGO();
                }
            }
        }
    }


    private class MultiTrainingListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            TrainingGUI trainingGUI = new TrainingGUI(basicGUI, view, controller, userDictionaries, view.getCurrentGUI(), user);
            trainingGUI.trainingGO();
        }
    }


    private class DeleteDictionaryListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.deleteDictionary(user, currentDictionary);
            go();
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
            TrainingGUI trainingGUI = new TrainingGUI(basicGUI,view, controller, view.getCurrentGUI(), user);
            trainingGUI.startFailsRepetition();
        }
    }
}
