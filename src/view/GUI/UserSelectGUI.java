package view.GUI;
import basicClasses.User;
import model.facade.ViewFacade;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserSelectGUI extends BasicGUI{

    public UserSelectGUI(ViewFacade facade) {
        super(facade);
    }

    public void go(){
        frame.setTitle("Learning Cars");

        ArrayList<User> users = facade.getUsersList();
        User[] listEntry = users.toArray(new User[0]);

        JList<User> usersJList = new JList<>(listEntry);
        usersJList.setVisibleRowCount(10);
        usersJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usersJList.addListSelectionListener(new UsersSelectionListener());

        JLabel label = new JLabel("Chose User ore create a new one");

        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setFont(bigFont);
        JScrollPane scroller = new JScrollPane();
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scroller);
        mainPanel.add(BorderLayout.CENTER, usersJList);
        mainPanel.add(BorderLayout.NORTH, label);

        JButton chooseUserButton = new JButton("Select User");
        JButton addNewUserButton = new JButton("Add new User");
        JButton deleteUserButton = new JButton("Delete User");

        chooseUserButton.addActionListener(new ChooseUserListener());
        addNewUserButton.addActionListener(new AddNewUserListener());
        deleteUserButton.addActionListener(new DeleteUserListener());

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.CYAN);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(chooseUserButton);
        buttonsPanel.add(addNewUserButton);
        buttonsPanel.add(deleteUserButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Main menu");
        JMenuItem exit = new JMenuItem("Exit");
        mainMenu.add(exit);
        menuBar.add(mainMenu);

        this.frameGo();
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
            frameGo();
            //userBuilderGUI.go();
        }
    }

    private static class DeleteUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}