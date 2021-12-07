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

        ArrayList<User> users = basicGUI.facade.getUsersList();
        User[] listEntry = users.toArray(new User[0]);

        JList<User> usersJList = new JList<>(listEntry);
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

        JButton chooseUserButton = new JButton("Select User");
        JButton addNewUserButton = new JButton("Add new User");
        JButton deleteUserButton = new JButton("Delete User");

        chooseUserButton.addActionListener(new ChooseUserListener());
        addNewUserButton.addActionListener(new AddNewUserListener());
        deleteUserButton.addActionListener(new DeleteUserListener());

        basicGUI.buttonsPanel = new JPanel();
        basicGUI.buttonsPanel.setBackground(Color.CYAN);
        basicGUI.buttonsPanel.setLayout(new BoxLayout(basicGUI.buttonsPanel, BoxLayout.Y_AXIS));
        basicGUI.buttonsPanel.add(chooseUserButton);
        basicGUI.buttonsPanel.add(addNewUserButton);
        basicGUI.buttonsPanel.add(deleteUserButton);

        /*JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Main menu");
        JMenuItem exit = new JMenuItem("Exit");
        mainMenu.add(exit);
        menuBar.add(mainMenu);*/

        basicGUI.go();
    }

    private static class UsersSelectionListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            // should open a selected user profile
        }
    }

    private static class ChooseUserListener implements ActionListener{
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

    private static class DeleteUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}