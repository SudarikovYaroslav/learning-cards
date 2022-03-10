package model.basicClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Dictionary implements Serializable {

    private String name;
    private final LinkedList<Card> cards;
    private int counter;
    private int hintCounter;
    private final ArrayList<Card> missedCards;
    private boolean isCardsRunOut;
    private boolean isAnswerShown;

    public Dictionary(String name) {
        this.name = name;
        cards = new LinkedList<>();
        missedCards = new ArrayList<>();
        counter = 0;
        hintCounter = 0;
        isCardsRunOut = false;
        isAnswerShown = false;
    }

    /**
     * This constructor used to build clone of dicForCloning
     */
    public Dictionary(Dictionary dicForCloning) {
        name = dicForCloning.getName();
        cards = new LinkedList<>();
        cards.addAll(dicForCloning.getCards());
        missedCards = new ArrayList<>();
        counter = 0;
        hintCounter = 0;
        isCardsRunOut = false;
        isAnswerShown = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Card> getCards() {
        return cards;
    }

    /**
     * perhaps I should delete this method because it won't be used
     */
    public void createNewCard() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String front = null;
        String back = null;

        try {
            front = reader.readLine();
            back = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Card newCard = new Card(front, back);
        cards.add(newCard);
    }

    public void editCard(Card changedCard, String front, String back) {
        for (Card card : cards) {
            if (card.getFront().equals(changedCard.getFront())) {
                card.setFront(front);
                card.setBack(back);
                return;
            }
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public String nextCard() {
        hintCounter = 0;
        isAnswerShown = false;
        String front = "";

        if (counter >= cards.size()) {
            if (!missedCards.isEmpty()) {
                cards.clear();
                cards.addAll(missedCards);
                missedCards.clear();
                counter = 0;
            }
        }

        if (counter < cards.size()) {
            front = cards.get(counter).getFront();
            counter++;
        }

        if (front.length() == 0) {
            isCardsRunOut = true;
            front = "All cards run out";
        }

        return front;
    }

    public String giveAnswer() {
        isAnswerShown = true;
        if (!isCardsRunOut) {
            if (counter > cards.size()) {
                return "";
            } else {
                return cards.get(counter - 1).getBack();
            }
        } else {
            return "";
        }
    }

    public void missCard() {
        missedCards.add(cards.get(counter - 1));
    }

    // Метод рабочий, но конструкция не очень удачная, надо бы избавиться от вложенных ифов. Может исправлю позже.
    /**
     * This method gives a different hints in different cases:
     * - shot word: a hint is one next letter;
     * - long word: a hint is three next letters;
     * - shot text: a hint is one word;
     * - long text: a hint is three next words; // НУЖНО ДОДЕЛАТЬ ЭТОТ ВАРИАНТ, ОН ВООБЩЕ НЕ РЕАЛИЗОВАН!!!!!!
     * */
    public String giveHint() {
        String hint = "";

        if ((!isCardsRunOut) && (!isAnswerShown)) {
            String[] arr = giveAnswer().split(" ");
            isAnswerShown = false;

            // if answer consists of one word
            if (arr.length == 1) {
                hintCounter++;
                String word = arr[0];

                if (word.length() < 6) { // if answer's a short word

                    hint = word.substring(0, hintCounter);

                } else { //if answer's one long word

                    int pos = 2;

                    while (hintCounter + pos > word.length()) {
                        pos--;
                    }

                    hint = word.substring(0, hintCounter += pos);
                }

            } else {
                StringBuilder answerBuilder = new StringBuilder();
                int nextWordIndex = 0;

                if (hintCounter < arr.length) {
                    hintCounter++;
                    while (nextWordIndex < hintCounter) {
                        answerBuilder.append(arr[nextWordIndex]);
                        if (nextWordIndex < arr.length) {
                            answerBuilder.append(" ");
                        }
                        nextWordIndex++;
                    }
                }
                if (nextWordIndex == arr.length) isAnswerShown = true;
                return answerBuilder.toString();
            }
        } else {
            if (isCardsRunOut) {
                return "";
            } else {
                return giveAnswer();
            }
        }

        if (hint.length() != giveAnswer().length()) {
            isAnswerShown = false;
            return hint;
        } else {
            return giveAnswer();
        }
    }

    public int getSize() {
        return cards.size();
    }

    public Card getCurrentCard() {
        return cards.get(counter - 1);
    }
}
