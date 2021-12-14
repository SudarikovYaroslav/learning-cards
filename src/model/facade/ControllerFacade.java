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


    public void saveUsers(){
        model.saveUsers();
    }

    public User getUser(String userName){
       return model.getUser(userName);
    }
}
