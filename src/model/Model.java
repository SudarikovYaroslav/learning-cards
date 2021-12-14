package model;

import basicClasses.Card;
import basicClasses.Dictionary;
import basicClasses.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Model {

    private static final String dividerKey = "151-De.V,i,D.eR-546"; // Used for dived Card's size, when write it in file and for mapping when read it from file
    private ArrayList<User> availableUsersList;
    private Set<User> loadedUsersHash = new HashSet<>();

    private final String allUsersListPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/AllUsersList.txt";
    private final String usersFolderPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/Users";


    public Model() {
        availableUsersList = new ArrayList<>();
        loadUsersList();
    }


    public ArrayList<User> getAvailableUsersList() {
        return availableUsersList;
    }


    private void loadUsersList(){
        BufferedReader reader;
        String line;

        try {
            reader = new BufferedReader(new FileReader(allUsersListPath));

            while ((line = reader.readLine()) != null){
                availableUsersList.add(new User(line));
            }

            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    // ВАЖНО !!! При сохранении состояния пользователей, заменить логику:
    // * Новый пользователь должен добавляться в список доступных пользователей
    // * Создание на ПК папки пользователя и ТХТ словарей должно происходить только
    //   во время сохранения данных, при выходе из программы
    public void createUser(User user){
        // Create a user.name folder if it doesn't exist
        File newUser = new File(usersFolderPath + "/" + user.getName());
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(allUsersListPath, true));
            writer.write(newUser.getName() + "\n");
            writer.close();

            newUser.mkdir();
            availableUsersList.add(user); // trying to solve bug with updating users list *** There is big problem with selecting brand new User. Suppose problem with Dictionaries init
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    public User getUser(String userName){

        for(User user : loadedUsersHash){
            if (user.getName().equals(userName)){
                return user;
            }
        }

        User user = loadUser(userName);
        loadedUsersHash.add(user);
        return user;
    }

    public void saveUsers(){}


    private User loadUser(String userName){
        User user = new User(userName);
        File usersDir = new File(usersFolderPath);
        File dictionariesDir = new File(usersFolderPath + "/" + userName + "/");
        File[] allUsers = usersDir.listFiles();

        if (allUsers != null) {
            for (File file : allUsers){
                if (file.getName().equals(userName)){
                    File[] userDictionariesTXT = dictionariesDir.listFiles();

                    if (userDictionariesTXT != null) {
                        for (File dictionaryTXT : userDictionariesTXT){
                            Dictionary currentDictionary = new Dictionary(dictionaryTXT.getName());
                            readTxtCardsToDic(dictionaryTXT, currentDictionary);
                            user.addDictionary(currentDictionary);
                        }
                    }

                }
            }
        }

        return user;
    }


    private void readTxtCardsToDic(File dictionaryTXT, Dictionary dictionary){
        BufferedReader reader;
        Card currentCard;
        String frontSize;
        String backSize;

        try {
            reader = new BufferedReader(new FileReader(dictionaryTXT));
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(dividerKey);
                frontSize = arr[0];
                backSize = arr[1];
                currentCard = new Card(frontSize, backSize);
                dictionary.addCard(currentCard);
            }

            reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

// C:/Users/Ярослав/Desktop/Repo/LearningCards/data/AllUsersList.txt