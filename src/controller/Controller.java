package controller;

import GUI.ExceptionsGUI;
import basicClasses.User;
import model.facade.ControllerFacade;
import model.Model;
import view.View;

import java.io.File;
import java.util.ArrayList;

public class Controller {

    private View view;
    private ControllerFacade facade;
    private final String allUsersListPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/AllUsersList.txt";
    private final String usersFolderPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/Users";


    public void addModel(Model model){
        facade = new ControllerFacade();
        facade.setModel(model);
    }


    public void addView(View view){
        this.view = view;
    }


    public boolean addNewUser(User user){
        File root = new File(usersFolderPath);
        File[] files = root.listFiles();
        boolean alreadyExists = false;

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }

        for (File file : files) {
            if (file.getName().equals(user.getName())) {
                alreadyExists = true;
            }
        }

        if (alreadyExists){
            view.printException("User with this name already exist! Please choose another user  name");
            return false;
        } else {
            facade.createUser(user);
            return true;
        }
    }


    public ArrayList<User> getUsersList(){
        return facade.getUsersList();
    }
}
