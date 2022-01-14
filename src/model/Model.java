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

    private final String allUsersListPath = "C:/Users/Yaroslav/Desktop/Repo/LearningCards/data/AllUsersList.txt";
    private final String usersFolderPath = "C:/Users/Yaroslav/Desktop/Repo/LearningCards/data/Users";


    public Model() {
        availableUsersList = new ArrayList<>();
        loadUsersList();
    }


    public ArrayList<User> getAvailableUsersList() {
        return availableUsersList;
    }


    private void loadUsersList(){
        availableUsersList.clear();
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


    public void createUser(User user){
        // Create a user.name folder if it doesn't exist
        File newUser = new File(usersFolderPath + "/" + user.getName());
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(allUsersListPath, true));
            writer.write(newUser.getName() + "\n");
            writer.close();

            newUser.mkdir();
            availableUsersList.add(user);
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


    public boolean deleteUser(User user){
        boolean successful = false;
        File userToDelete = new File(usersFolderPath + "/" + user.getName());
        File[] dictionaries = userToDelete.listFiles();

       if (dictionaries.length != 0) {
           for (File dictionary : dictionaries) {
               dictionary.delete();
           }
       }

        successful = userToDelete.delete();

        if (successful){

            //availableUsersList.remove(user); не понимаю в чём проблема, но пользователь user не удаляется этой строчкой!!
            //!! CROOKED CRUTCH!! But it's worked correctly!!
            for (int i = 0; i < availableUsersList.size(); i++){
                if (availableUsersList.get(i).getName().equals(user.getName())){
                    availableUsersList.remove(i);
                }
            }
            //

            if (availableUsersList.size() != 0) {
                BufferedWriter writer;
                try {
                    writer = new BufferedWriter(new FileWriter(allUsersListPath, false));

                    for (User u : availableUsersList){
                        writer.write(u.getName() + "\n");
                    }

                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            } else {
                recreateAvailableUsersListFile();
            }
        }

        return successful;
    }


    public boolean createDictionary(User user,String dictionaryName){
        boolean success = false;
        Dictionary dictionary = new Dictionary(dictionaryName + ".txt");
        user.addDictionary(dictionary);

        File newDictionaryTXT = new File(usersFolderPath + "/" + user.getName() + "/" + dictionaryName + ".txt");

        try {
           newDictionaryTXT.createNewFile();
           success = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return success;
    }




    public boolean deleteDictionary(User user, Dictionary dictionary){
        File removingDictionary = new File(usersFolderPath + "/" + user.getName() + "/" + dictionary.getName());
        ArrayList<Dictionary> dictionaries = user.getDictionaries();

        for (Dictionary dic : dictionaries) {
            if (dic.getName().equals(dictionary.getName())) {
                dictionaries.remove(dic);
                break;
            }
        }

        return removingDictionary.delete();
    }


     private void recreateAvailableUsersListFile(){
        File toDelete = new File(allUsersListPath);
        toDelete.delete();
        File newUserList = new File(allUsersListPath);

        try {
            newUserList.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean createCard(User user, Dictionary dictionary, String frontSide, String backSide){
        File dicTXT = new File(usersFolderPath + "/" + user.getName() + "/" + dictionary.getName());
        BufferedWriter writer;
        boolean success = false;

        if (!dicTXT.exists()){
            try {
                dicTXT.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        Card card = new Card(frontSide, backSide);
        dictionary.addCard(card);

        try {
            writer = new BufferedWriter(new FileWriter(dicTXT, true));
            writer.write(frontSide + dividerKey + backSide + "\n");
            success = true;
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return success;
    }


    public boolean deleteCard(File dicTXT, Card card){
        boolean success = false;
        ArrayList<String> strCards = new ArrayList<>();
        BufferedReader reader;
        BufferedWriter writer;

        try {
            reader = new BufferedReader(new FileReader(dicTXT));
            String line;
            while ((line = reader.readLine()) != null){

                String[] arr = line.split(dividerKey);

                if (!arr[0].equals(card.getFront())) {
                    strCards.add(line);
                }
            }
            reader.close();

            writer = new BufferedWriter(new FileWriter(dicTXT, false));
            for (String str : strCards){
                writer.write(str + "\n");
            }
            writer.close();
            success = true;
        } catch (IOException e){
            e.printStackTrace();
        }

        return success;
    }


    public boolean editCard(File dictionaryTXT, String front, String back){
        boolean success = false;
        ArrayList<String> cardsStr = new ArrayList<>();
        BufferedReader reader;
        BufferedWriter writer;
        String line;

        try {
            reader = new BufferedReader(new FileReader(dictionaryTXT));

            while ((line = reader.readLine()) != null){
                String[] arr = line.split(dividerKey);

                if (arr[0].equals(front)){
                    line = front + dividerKey + back;
                }

                cardsStr.add(line);
            }

            reader.close();

            writer = new BufferedWriter(new FileWriter(dictionaryTXT, false));

            for (String data : cardsStr){
                writer.write(data + "\n");
            }

            writer.close();
            success = true;
        } catch (IOException e){
            e.printStackTrace();
        }

        return success;
    }


    public boolean addToTheFailsList(File failsListTXT, Card card){
        BufferedWriter writer;
        boolean success = false;

        try {
            writer = new BufferedWriter(new FileWriter(failsListTXT, true));
            writer.write(card.getFront() + dividerKey + card.getBack());
            writer.close();
            success = true;
        } catch (IOException e){
            e.printStackTrace();
        }

        return success;
    }
}
