package Day4;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        partOne();
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

            /*
            for(String s: winningNumbersString){
                System.out.println(s);
            }
             */

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

            /*
            System.out.println("Winningnumbers");
            for (int print : winningNumbers){
                System.out.print(print);
            }
            System.out.println("actualNumbers");
            for (int print : actualNumbers){
                System.out.println(print);
            }

             */

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
