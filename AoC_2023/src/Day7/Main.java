package Day7;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        //partOne();
        partTwo();
    }

    public static void partOne(){
        String rawInput = loadFile("inputSeven.txt");
        int result = 0;

        rawInput = rawInput.replaceAll("A","P");
        rawInput = rawInput.replaceAll("K","O");
        rawInput = rawInput.replaceAll("Q","N");
        rawInput = rawInput.replaceAll("J","M");
        rawInput = rawInput.replaceAll("T","L");

        String[] lines = rawInput.split("\n");
        //P, O, N, M, L
        //A, K, Q, J, T

        ArrayList<String> handsWithValue = new ArrayList<>();

        //Adding handsWithValues to Array
        for(String line : lines){
            int currentValueDeck = 1;
            String deck = line.substring(0,5);
            //System.out.println("Current Deck: "+deck);
            /*
            String bidString = line.substring(6);
            int bid = Integer.parseInt(bidString);
             */

            char[] cardsIndividual = deck.toCharArray();
            Arrays.sort(cardsIndividual);
            /*
            for(char c:cardsIndividual){
                System.out.print(c);
            }
             */
            //System.out.println();



            char currentCard = cardsIndividual[0];
            int repeatCounts = 0;

            for(int i = 0;i<cardsIndividual.length;i++){
                char card = cardsIndividual[i];
                if (card == currentCard ) {
                    repeatCounts++;
                    if (i != cardsIndividual.length - 1) continue;
                }
                //Assign Value of prev Combo
                switch (repeatCounts){
                    case 2:
                        if(currentValueDeck <2){
                            currentValueDeck = 2;
                        }
                        else if (currentValueDeck == 2) {
                            currentValueDeck = 3;
                        }
                        else if(currentValueDeck == 4) {
                            currentValueDeck = 5;
                        }
                        break;
                    case 3:
                        if (currentValueDeck == 2){
                            currentValueDeck = 5;
                        } else if (currentValueDeck<4) {
                            currentValueDeck = 4;
                        }
                        break;
                    case 4:
                        if(currentValueDeck < 6) currentValueDeck = 6;
                        break;
                    case 5:
                        if(currentValueDeck <7)currentValueDeck =7;
                    default:
                        break;
                }
                repeatCounts = 1;
                currentCard = card;
            }
            //TUVWXYZ
            String currenValueDeckString ="";
            switch (currentValueDeck){
                case 1:
                    currenValueDeckString = "T";
                    break;
                case 2:
                    currenValueDeckString = "U";
                    break;
                case 3:
                    currenValueDeckString = "V";
                break;
                case 4:
                    currenValueDeckString = "W";
                    break;
                case 5:
                    currenValueDeckString = "X";
                    break;
                case 6:
                    currenValueDeckString = "Y";
                    break;
                case 7:
                    currenValueDeckString = "Z";
                    break;
                default:
                    break;
            }
            String lineWithValue = currenValueDeckString + line;
            //System.out.println(lineWithValue);
            handsWithValue.add(lineWithValue);
        }

        Collections.sort(handsWithValue,Collections.reverseOrder());
        for(String s : handsWithValue) System.out.println(s);
        //Value: Each Type of Combination has a Value from 7 to 1 7 being the best

        for(int i = 0;i<handsWithValue.size();i++){
            int bid = Integer.parseInt(handsWithValue.get(i).substring(7));
            result += bid * (handsWithValue.size()-i);
        }

        System.out.println(result);
    }

    public static void partTwo(){
        String rawInput = loadFile("inputSeven.txt");
        int result = 0;
        System.out.println("Raw Input:");
        System.out.println(rawInput);
        System.out.println();

        rawInput = rawInput.toUpperCase();
        rawInput = rawInput.replaceAll("A","P");
        rawInput = rawInput.replaceAll("K","O");
        rawInput = rawInput.replaceAll("Q","N");
        rawInput = rawInput.replaceAll("J","1");
        rawInput = rawInput.replaceAll("T","L");

        String[] lines = rawInput.split("\n");
        //P, O, N, 1, L
        //A, K, Q, J, T

        //TUVWXYZ
        //1234567

        ArrayList<String> handsWithValue = new ArrayList<>();

        //Adding handsWithValues to Array
        for(String line : lines){
            int currentValueDeck = 1;
            String deck = line.substring(0,5);
            int sizeBeforeJoker = deck.length();
            deck = deck.replaceAll("1","");
            int sizeAfterJoker = deck.length();
            int amountOfJokers = sizeBeforeJoker-sizeAfterJoker;
            if(amountOfJokers == 5){
                deck = "11111";
                currentValueDeck = 7;
            }
            if(amountOfJokers == 4){
                currentValueDeck = 7;
                System.out.println("Deck with 4 J: "+ line);
            }

            char[] cardsIndividual = deck.toCharArray();
            Arrays.sort(cardsIndividual);
            //M is Joker
            char currentCard = cardsIndividual[0];
            //System.out.println("initial Card:" + currentCard);
            int repeatCounts = amountOfJokers;
            /*
            for(char card: cardsIndividual){
                System.out.print(card);
            }
            System.out.println();
             */

            for(int i = 0;i<cardsIndividual.length;i++){
                char card = cardsIndividual[i];

                if (card == currentCard ) {
                    repeatCounts++;
                    if (i != cardsIndividual.length - 1) continue;
                }
                //System.out.println("\t"+"card: "+card + " currentValue: "+currentValueDeck + " AmountCards: " + repeatCounts);
                //Assign Value of prev Combo
                switch (repeatCounts){
                    case 2:
                        if(currentValueDeck <2){
                            currentValueDeck = 2;
                        }
                        else if (currentValueDeck == 2 && amountOfJokers ==0) {;
                            currentValueDeck = 3;
                        }
                        else if(currentValueDeck == 4 && amountOfJokers==0) {
                            currentValueDeck = 5;
                        }
                        break;
                    case 3:
                        if (currentValueDeck == 2 && amountOfJokers==0){
                            currentValueDeck = 5;
                        } else if (currentValueDeck == 2 && amountOfJokers >0) {
                            currentValueDeck = 4;
                        } else if (currentValueDeck<4) {
                            currentValueDeck = 4;
                        }
                        break;
                    case 4:
                        if(currentValueDeck < 6) currentValueDeck = 6;
                        break;
                    case 5:
                        if(currentValueDeck <7)currentValueDeck =7;
                    default:
                        break;
                }
                repeatCounts = amountOfJokers+1;
                currentCard = card;
            }
            //TUVWXYZ
            String currenValueDeckString ="";
            switch (currentValueDeck){
                case 1:
                    currenValueDeckString = "T";
                    break;
                case 2:
                    currenValueDeckString = "U";
                    break;
                case 3:
                    currenValueDeckString = "V";
                    break;
                case 4:
                    currenValueDeckString = "W";
                    break;
                case 5:
                    currenValueDeckString = "X";
                    break;
                case 6:
                    currenValueDeckString = "Y";
                    break;
                case 7:
                    currenValueDeckString = "Z";
                    break;
                default:
                    break;
            }
            String lineWithValue = currenValueDeckString + line;
            //System.out.println(lineWithValue);
            handsWithValue.add(lineWithValue);
        }

        Collections.sort(handsWithValue,Collections.reverseOrder());
        //Value: Each Type of Combination has a Value from 7 to 1 7 being the best

        for(int i = 0;i<handsWithValue.size();i++){
            int bid = Integer.parseInt(handsWithValue.get(i).substring(7));
            result += bid * (handsWithValue.size()-i);
            System.out.println(handsWithValue.get(i) + " \t Multiplier: "+(handsWithValue.size()-i));
            //System.out.println("added "+(bid*(handsWithValue.size()-i)) +" resulting in: "+ result + " Bid: "+bid + " Rank: "+(handsWithValue.size()-i));
        }

        System.out.println();
        System.out.println("Result:");
        System.out.println(result);
    }
    public static String loadFile(String filename) {
        InputStream stream = Day7.Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
