package model;

import model.basicClasses.User;

import java.util.ArrayList;

public class ViewFacade extends Facade {

    public ArrayList<User> getUsersList() {
        return model.getAvailableUsersList();
    }
}
