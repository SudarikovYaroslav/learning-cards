package view;

import GUI.ExceptionsGUI;
import GUI.GUI;
import basicClasses.Dictionary;
import basicClasses.User;
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

    private User testUser = new User("Yar"); // line should be deleted after test
    private Dictionary currentDictionary = new Dictionary("English Cards"); // line only for tests. Should be deleted


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
        // Во Viw в переменную currentGUI передавать ссылку при каждом переходе на новый GUI
        // Передать ссылку на View во все GUI
        ExceptionsGUI exceptionsGUI = new ExceptionsGUI(basicGUI, currentGUI, message, this);
        exceptionsGUI.go();
    }

    public void setCurrentGUI(GUI gui){
        currentGUI = gui;
    }
}
