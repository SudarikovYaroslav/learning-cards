package main;

import basicClasses.GUI.CardBuilder;

public class LearningCards {

    public static void main(String[] args) {
        LearningCards learningCards = new LearningCards();
        learningCards.run();
    }

    public void run(){
        CardBuilder cardBuilder = new CardBuilder();
        cardBuilder.go();
    }
}
