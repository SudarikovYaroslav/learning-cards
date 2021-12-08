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
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller();

        view.addModel(model);
        view.addController(controller);

        controller.addModel(model);
        controller.addView(view);

        view.go();
    }
}
