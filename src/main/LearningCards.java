package main;

import controller.Controller;
import model.Model;
import view.View;

public class LearningCards {
    Model model;
    View view;
    Controller controller;

    public static void main(String[] args) {
        LearningCards learningCards = new LearningCards();
        learningCards.run();
    }

    public void run(){
        controller.setModel(model);
        model.setView(view);
        view.setController(controller);
    }
}
