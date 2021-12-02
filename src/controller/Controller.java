package controller;

import facade.FacadeForController;
import model.Model;

public class Controller {

    private FacadeForController facade;

    public void addModel(Model model){
        facade = new FacadeForController();
        facade.setModel(model);
    }

}
