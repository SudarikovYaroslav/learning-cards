package main;

import controller.Controller;
import model.Model;
import view.View;

public class LearningCards {

    public static void main(String[] args) {
        LearningCards learningCards = new LearningCards();
        learningCards.run();
    }

    public void run(){
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();

        view.setModel(model);
        view.go();
    }
}
