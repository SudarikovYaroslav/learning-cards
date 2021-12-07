package controller;

import GUI.ExceptionsGUI;
import basicClasses.User;
import model.facade.ControllerFacade;
import model.Model;
import view.View;

import java.io.File;

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

    public void addNewUser(User user){
        File root = new File(usersFolderPath);
        File[] files = root.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(user.getName())) {
                    view.printException("User with this name already exist! Please choose another user  name");
                }
            }
        }

        facade.createUser(user);
    }
}
