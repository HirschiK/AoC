package Day4;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //partOne();
        partTwo();
    }

    public static void partOne(){
        String inputRaw = loadFile("inputFour.txt");
        System.out.println(inputRaw);
        int result = 0;

        String[] lines = inputRaw.split("\n");
        for (int i =0; i<lines.length;i++){
            int index = lines[i].indexOf(':');
            lines[i] = lines[i].substring(index+1);
            System.out.println(lines[i]);
        }

        for (int i=0;i< lines.length;i++){
            String[] split = lines[i].split("\\|");

            String[] winningNumbersString = split[0].split(" ");

            int[] winningNumbers = new int[winningNumbersString.length];
            for(int j = 0; j<winningNumbersString.length;j++){
                if(winningNumbersString[j].isBlank())continue;
                winningNumbers[j] = Integer.parseInt(winningNumbersString[j]);
            }

            String[] actualNumbersString = split[1].split(" ");
            int[] actualNumbers = new int[actualNumbersString.length];
            for(int j = 0; j<actualNumbersString.length;j++){
                if(actualNumbersString[j].isBlank())continue;
                actualNumbers[j] = Integer.parseInt(actualNumbersString[j]);
            }

            int currentResult = 1;

            for(int number : actualNumbers){
                for(int checkWinningNumber: winningNumbers){
                    if(number == 0)break;
                    if (number == checkWinningNumber){
                        currentResult *=2;
                    }
                }
            }

            if (currentResult == 1)currentResult =0;
            System.out.println("Line: " + (i+1) + " Result: " + currentResult);
            result += (currentResult/2);
        }
        System.out.println(result);

    }

    public static void partTwo(){
        String inputRaw = loadFile("inputFour.txt");
        System.out.println(inputRaw);
        int result = 0;

        String[] lines = inputRaw.split("\n");
        for (int i =0; i<lines.length;i++){
            int index = lines[i].indexOf(':');
            lines[i] = lines[i].substring(index+1);
            System.out.println(lines[i]);
        }

        int[] amountOfCards = new int[lines.length];
        Arrays.fill(amountOfCards,1);

        for (int i=0;i< lines.length;i++){
            String[] split = lines[i].split("\\|");

            String[] winningNumbersString = split[0].split(" ");

            int[] winningNumbers = new int[winningNumbersString.length];
            for(int j = 0; j<winningNumbersString.length;j++){
                if(winningNumbersString[j].isBlank())continue;
                winningNumbers[j] = Integer.parseInt(winningNumbersString[j]);
            }

            String[] actualNumbersString = split[1].split(" ");
            int[] actualNumbers = new int[actualNumbersString.length];
            for(int j = 0; j<actualNumbersString.length;j++){
                if(actualNumbersString[j].isBlank())continue;
                actualNumbers[j] = Integer.parseInt(actualNumbersString[j]);
            }

            int currentResult = 0;

            for(int number : actualNumbers){
                for(int checkWinningNumber: winningNumbers){
                    if(number == 0)break;
                    if (number == checkWinningNumber){
                        currentResult +=1;
                    }
                }
            }

            System.out.println("Line: " + (i+1) + " Current_Result: " + currentResult+ " Amount of Cards: "+amountOfCards[i]);
            if (currentResult == 0){
                result += amountOfCards[i];
                continue;
            }

            for(int x = 1;x<=currentResult;x++){
                if(i+x ==amountOfCards.length)break;
                System.out.println("Added "+amountOfCards[i]+ " to " + (i+x));
                amountOfCards[i+x] += amountOfCards[i];
            }
            result += amountOfCards[i];
        }
        System.out.println(result);

    }
    public static String loadFile(String filename) {
        InputStream stream = Day4.Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
