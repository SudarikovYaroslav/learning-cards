package main;

import basicClasses.GUI.CardBuilder;
import basicClasses.GUI.Training;

public class LearningCards {

    public static void main(String[] args) {
        LearningCards learningCards = new LearningCards();
        learningCards.run();
    }

    public void run(){
        /*CardBuilder cardBuilder = new CardBuilder();
        cardBuilder.go();*/
        Training training = new Training();
        training.go();
    }
}
