package model.facade;

import model.basicClasses.Card;
import model.basicClasses.Dictionary;
import model.basicClasses.User;

import java.io.File;
import java.util.ArrayList;

public class ControllerFacade extends Facade {

    public void createUser(User user) {
        model.createUser(user);
    }

    public ArrayList<User> getUsersList() {
        return model.getAvailableUsersList();
    }

    public User getUser(String userName) {
        return model.getUser(userName);
    }

    public boolean deleteUser(User user) {
        return model.deleteUser(user);
    }

    public boolean createDictionary(User user, String dictionaryName) {
        return model.createDictionary(user, dictionaryName);
    }

    public boolean deleteDictionary(User user, Dictionary dictionary) {
        return model.deleteDictionary(user, dictionary);
    }

    public boolean createCard(User user, Dictionary dictionary, String frontSide, String backSide) {
        return model.createCard(user, dictionary, frontSide, backSide);
    }

    public boolean deleteCard(File dicTXT, Card card) {
        return model.deleteCard(dicTXT, card);
    }

    public boolean editCard(File dictionaryTXT, String front, String back) {
        return model.editCard(dictionaryTXT, front, back);
    }

    public boolean addToTheFailsList(File failsListTXT, Card card) {
        return model.addToTheFailsList(failsListTXT, card);
    }

    public Dictionary loadFailsDictionary(File failsTXT) {
        return model.loadFailsDictionary(failsTXT);
    }

    public String deleteFromFailsList(File failsListTXT, Card card) {
        return model.deleteFromFailsList(failsListTXT, card);
    }

    public String getUsersFolderPath() {
        return model.getUsersFolderPath();
    }
}
