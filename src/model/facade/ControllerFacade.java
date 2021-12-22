package model.facade;

import basicClasses.User;

import java.util.ArrayList;

public class ControllerFacade extends Facade{


    public void createUser(User user){
        model.createUser(user);
    }


    public ArrayList<User> getUsersList(){
       return model.getAvailableUsersList();
    }


    public User getUser(String userName){
       return model.getUser(userName);
    }


    public boolean deleteUser(User user){
        return model.deleteUser(user);
    }


    public void createDictionary(User user, String dictionaryName){
        model.createDictionary(user, dictionaryName);
    }
}
