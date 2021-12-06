package controller;

import model.facade.ControllerFacade;
import model.Model;

public class Controller {

    private ControllerFacade facade;

    public void addModel(Model model){
        facade = new ControllerFacade();
        facade.setModel(model);
    }

}
