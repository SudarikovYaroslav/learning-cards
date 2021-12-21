package view;

import GUI.ExceptionsGUI;
import GUI.GUI;
import controller.Controller;
import model.facade.ViewFacade;
import model.Model;
import GUI.BasicGUI;
import GUI.UserSelectGUI;


public class View {

    private ViewFacade facade;
    private Controller controller;
    private BasicGUI basicGUI;
    private GUI currentGUI;


    public void addModel(Model model){
        facade = new ViewFacade();
        facade.setModel(model);
        basicGUI = new BasicGUI(facade, this);
    }


    public void addController(Controller controller){
        this.controller = controller;
    }


    public void go(){
        UserSelectGUI userSelectGUI = new UserSelectGUI(basicGUI, controller, this);
        userSelectGUI.go();
    }


    public void printException(String message){
        ExceptionsGUI exceptionsGUI = new ExceptionsGUI(basicGUI, currentGUI, message, this);
        exceptionsGUI.go();
    }

    public void setCurrentGUI(GUI gui){
        currentGUI = gui;
    }
}
