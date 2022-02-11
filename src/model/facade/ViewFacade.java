package model.facade;

import basicClasses.User;

import java.util.ArrayList;

public class ViewFacade extends Facade {

    public ArrayList<User> getUsersList() {
        return model.getAvailableUsersList();
    }
}
