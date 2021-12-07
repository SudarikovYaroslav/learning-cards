package model.facade;

import basicClasses.User;

public class ControllerFacade extends Facade{


    public void createUser(User user){
        model.createUser(user);
    }


}
