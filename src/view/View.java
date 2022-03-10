package view;

import GUI.BasicGUI;
import GUI.GUI;
import GUI.MessagesGUI;
import GUI.UserSelectGUI;
import controller.Controller;
import model.Model;
import model.ViewFacade;

public class View {
    private ViewFacade facade;
    private Controller controller;
    private BasicGUI basicGUI;
    private GUI currentGUI;

    public void addModel(Model model) {
        facade = new ViewFacade();
        facade.setModel(model);
        basicGUI = new BasicGUI(facade, this);
    }

    public void addController(Controller controller) {
        this.controller = controller;
    }

    public void go() {
        UserSelectGUI userSelectGUI = new UserSelectGUI(basicGUI, controller, this);
        userSelectGUI.go();
    }

    public void printMessage(String message) {
        MessagesGUI messagesGUI = new MessagesGUI(basicGUI, currentGUI, message, this);
        messagesGUI.go();
    }

    public GUI getCurrentGUI() {
        return currentGUI;
    }

    public void setCurrentGUI(GUI gui) {
        currentGUI = gui;
    }
}
