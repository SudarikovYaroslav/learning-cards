package controller;

import model.Model;
import model.basicClasses.Card;
import model.basicClasses.Dictionary;
import model.basicClasses.User;
import model.facade.ControllerFacade;
import view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {

    /**
     * Used for dived Card's size, when write it in file and for mapping when read it from file
     */
    private static final String dividerKey = "151-De.V,i,D.eR-546";
    private View view;
    private ControllerFacade facade;
    private String usersFolderPath;

    public void addModel(Model model) {
        facade = new ControllerFacade();
        facade.setModel(model);
        usersFolderPath = facade.getUsersFolderPath();
    }

    public void addView(View view) {
        this.view = view;
    }

    public boolean addNewUser(User user) {
        File root = new File(usersFolderPath);
        File[] files = root.listFiles();
        boolean alreadyExists = false;

        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(user.getName())) {
                    alreadyExists = true;
                    break;
                }
            }
        }

        if (alreadyExists) {
            view.printMessage("User with this name already exist! Please choose another user name");
            return false;
        } else if (user.getName().length() == 0) {
            view.printMessage("User name should contain at list a one symbol!");
            return false;
        } else {
            facade.createUser(user);
            return true;
        }
    }

    public boolean deleteUser(User user) {
        return facade.deleteUser(user);
    }

    public ArrayList<User> getUsersList() {
        return facade.getUsersList();
    }

    public User getUser(String userName) {
        return facade.getUser(userName);
    }

    public boolean createDictionary(User user, String newDictionaryName) {
        ArrayList<Dictionary> existsDictionaries = user.getDictionaries();
        boolean alreadyExist = false;
        boolean success = false;


        for (Dictionary dictionary : existsDictionaries) {
            if (dictionary.getName().equals(newDictionaryName + ".txt")) {
                alreadyExist = true;
                break;
            }
        }

        if (newDictionaryName.length() == 0) {
            view.printMessage("The Dictionary name should contains at list one symbol!");
        } else if (alreadyExist) {
            view.printMessage("The Dictionary with this name already exist! Choose another name please.");
        } else {
            success = facade.createDictionary(user, newDictionaryName);
        }

        return success;
    }

    public boolean deleteDictionary(User user, Dictionary dictionary) {
        boolean checkExistence = false;
        boolean success = false;

        File userFolder = new File(usersFolderPath + "/" + user.getName());
        File[] usersDictionaries = userFolder.listFiles();

        if (usersDictionaries != null) {
            for (File file : usersDictionaries) {
                if (file.getName().equals(dictionary.getName())) {
                    checkExistence = true;
                    break;
                }
            }
        }

        if (checkExistence) {
            success = facade.deleteDictionary(user, dictionary);
        }

        return success;
    }

    public boolean createCard(User user, Dictionary dictionary, String frontSide, String backSide) {
        boolean isExist = false;
        boolean success = false;

        for (Card card : dictionary.getCards()) {
            if (card.getFront().equals(frontSide)) {
                isExist = true;
                break;
            }
        }

        if (isExist) {
            view.printMessage("The card with such question already exists! Please, choose the other question");
        } else {
            success = facade.createCard(user, dictionary, frontSide, backSide);
        }

        return success;
    }

    public boolean deleteCard(User user, Dictionary dictionary, Card card) {
        boolean success = false;
        File dicTXT = new File(usersFolderPath + "/" + user.getName() + "/" + dictionary.getName());

        if (dicTXT.exists()) {
            success = facade.deleteCard(dicTXT, card);
        }

        return success;
    }

    public boolean editCard(User user, Dictionary dictionary, String front, String back) {
        boolean success = false;
        File dicTXT = new File(usersFolderPath + "/" + user.getName() + "/" + dictionary.getName());

        if (dicTXT.exists()) {
            success = facade.editCard(dicTXT, front, back);
        }
        return success;
    }

    public String addToTheFailsList(User user, Card card) {
        File failsListTXT = new File(usersFolderPath + "/" + user.getName() + "/FailsList.txt");
        BufferedReader reader;
        boolean success = false;
        StringBuilder messageBuilder = new StringBuilder();

        if (!failsListTXT.exists()) {
            try {
                if (failsListTXT.createNewFile()) {
                    messageBuilder.append("File: ").append(failsListTXT.getAbsolutePath()).append(" has been " +
                            "successfully created\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                reader = new BufferedReader(new FileReader(failsListTXT));
                String data;

                while ((data = reader.readLine()) != null) {
                    String[] arr = data.split(dividerKey);

                    if (arr[0].equals(card.getFront())) {
                        messageBuilder.append("This card's already in Fails Repetition List");
                        return messageBuilder.toString();
                    }
                }

                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        success = facade.addToTheFailsList(failsListTXT, card);

        if (success) {
            messageBuilder.append("The Card has been successfully wrote in Fails List");
        } else {
            messageBuilder.append("Operation failed! (((");
        }
        return messageBuilder.toString();
    }

    public Dictionary loadFailsDictionary(User user) {
        File failsTXT = new File(usersFolderPath + "/" + user.getName() + "/FailsList.txt");
        Dictionary result = null;

        if (!failsTXT.exists()) {
            view.printMessage("User: " + user.getName() + " have no fails cards.");
        } else {
            result = facade.loadFailsDictionary(failsTXT);
        }
        return result;
    }

    public String deleteFromFailsList(User user, Card card) {
        File failsListTXT = new File(usersFolderPath + "/" + user.getName() + "/FailsList.txt");
        String message = "Operation failed ((";

        if (!failsListTXT.exists()) {
            message = "Fails list doesn't contains selected card";
        } else {
            message = facade.deleteFromFailsList(failsListTXT, card);
        }

        return message;
    }
}
