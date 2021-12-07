package model.facade;

import basicClasses.Dictionary;
import basicClasses.User;

import java.util.ArrayList;

public class ViewFacade extends Facade{

    public ArrayList<Dictionary> getUserDictionaries(){
        return null;
    }

    public ArrayList<User> getUsersList(){
        return model.getAvailableUsersList();
    }

}
