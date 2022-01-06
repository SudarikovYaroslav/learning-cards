package controller;

import basicClasses.Card;
import basicClasses.Dictionary;
import basicClasses.User;
import model.facade.ControllerFacade;
import model.Model;
import view.View;

import java.io.File;
import java.util.*;

public class Controller {

    private View view;
    private ControllerFacade facade;
    private final String allUsersListPath = "C:/Users/Yaroslav/Desktop/Repo/LearningCards/data/AllUsersList.txt";
    private final String usersFolderPath = "C:/Users/Yaroslav/Desktop/Repo/LearningCards/data/Users";


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


        for (File file : files) {
            if (file.getName().equals(user.getName())) {
                alreadyExists = true;
                break;
            }
        }

        if (alreadyExists){
            view.printException("User with this name already exist! Please choose another user name");
            return false;
        } else if(user.getName().length() == 0){
            view.printException("User name should contain at list a one symbol!");
            return false;
        } else {
            facade.createUser(user);
            return true;
        }
    }


    public boolean deleteUser(User user){
       return facade.deleteUser(user);
    }

    public ArrayList<User> getUsersList(){
        return facade.getUsersList();
    }


    public User getUser(String userName){
        return facade.getUser(userName);
    }


    public boolean createDictionary(User user, String newDictionaryName){
        ArrayList<Dictionary> existsDictionaries = user.getDictionaries();
        boolean alreadyExist = false;
        boolean success = false;


        for (Dictionary dictionary : existsDictionaries){
            if (dictionary.getName().equals(newDictionaryName + ".txt")){
                alreadyExist = true;
                break;
            }
        }

        if (newDictionaryName.length() == 0){
            view.printException("The Dictionary name should contains at list one symbol!");
        } else if (alreadyExist){
            view.printException("The Dictionary with this name already exist! Choose another name please.");
        } else {
           success = facade.createDictionary(user, newDictionaryName);
        }

        return success;
    }


    public boolean deleteDictionary(User user, Dictionary dictionary){
        boolean checkExistence = false;
        boolean success = false;

        File userFolder = new File(usersFolderPath + "/" + user.getName());
        File[] usersDictionaries = userFolder.listFiles();

        for (File file : usersDictionaries){
            if (file.getName().equals(dictionary.getName())){
                checkExistence = true;
                break;
            }
        }

        if (checkExistence){
            success = facade.deleteDictionary(user, dictionary);
        }

        return success;
    }


    public boolean createCard(User user, Dictionary dictionary, String frontSide, String backSide){
        boolean isExist = false;
        boolean success = false;

        for (Card card : dictionary.getCards()){
            if (card.getFront().equals(frontSide)){
                isExist = true;
                break;
            }
        }

        if (isExist){
            view.printException("The card with such question already exists! Please, choose the other question");
        } else {
            success = facade.createCard(user, dictionary, frontSide, backSide);
        }

        return success;
    }

}
