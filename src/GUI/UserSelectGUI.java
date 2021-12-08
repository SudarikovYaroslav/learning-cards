package GUI;
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

public class UserSelectGUI implements GUI {

    private final BasicGUI basicGUI;
    private final Controller controller;
    private View view;

    public UserSelectGUI(BasicGUI basicGUI, Controller controller, View view) {
        this.basicGUI = basicGUI;
        this.controller = controller;
        this.view = view;
    }

    public void go(){
        view.setCurrentGUI(this);
        basicGUI.clear();

        basicGUI.frame.setTitle("Learning Cars");

        // наверное нужно убрать ViewFacade из BasicGIU и вообще удалить этот класс, по идее его вообще не должно было бы быть, а view жолжна получать всё информацию
        // от модели с помощью паттерна наблюдатель;
        ArrayList<User> users = basicGUI.viewFacade.getUsersList();
        String[] availableUsers = getUsersNames(users);

        JList<String> usersJList = new JList<>(availableUsers);
        usersJList.setVisibleRowCount(10);
        usersJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usersJList.addListSelectionListener(new UsersSelectionListener());

        JLabel label = new JLabel("Chose User ore create a new one");

        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        basicGUI.mainPanel = new JPanel();
        basicGUI.mainPanel.setBackground(Color.LIGHT_GRAY);
        basicGUI.mainPanel.setFont(bigFont);
        JScrollPane scroller = new JScrollPane();
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        basicGUI.mainPanel.add(scroller);
        basicGUI.mainPanel.add(BorderLayout.CENTER, usersJList);
        basicGUI.mainPanel.add(BorderLayout.NORTH, label);

        JButton selectUserButton = new JButton("Select User");
        JButton addNewUserButton = new JButton("Add new User");
        JButton deleteUserButton = new JButton("Delete User");

        selectUserButton.addActionListener(new SelectUserListener());
        addNewUserButton.addActionListener(new AddNewUserListener());
        deleteUserButton.addActionListener(new DeleteUserListener());

        basicGUI.buttonsPanel = new JPanel();
        basicGUI.buttonsPanel.setBackground(Color.CYAN);
        basicGUI.buttonsPanel.setLayout(new BoxLayout(basicGUI.buttonsPanel, BoxLayout.Y_AXIS));
        basicGUI.buttonsPanel.add(selectUserButton);
        basicGUI.buttonsPanel.add(addNewUserButton);
        basicGUI.buttonsPanel.add(deleteUserButton);

        basicGUI.go();
    }


    private String[] getUsersNames(ArrayList<User> users){
        String[] usersNames = new String[users.size()];

        for (int i = 0; i < users.size(); i++) {
            usersNames[i] = users.get(i).getName();
        }

        return usersNames;
    }


    private class UsersSelectionListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }

    private class SelectUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class AddNewUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            UserBuilderGUI userBuilderGUI = new UserBuilderGUI(basicGUI, controller, view);
            userBuilderGUI.go();
        }
    }

    private class DeleteUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}