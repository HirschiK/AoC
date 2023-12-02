package Day2;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        partOne();
    }

    public static void partOne(){
        String input = loadFile("inputTwo.txt");
        String[] lines = input.split("\n");
        int result = 0;
        int redMax = 12;
        int blueMax  = 14;
        int greenMax  =13;
        boolean currentTrue;

        for (int i = 0;i<lines.length;i++){
            System.out.println(lines[i]);
            currentTrue = true;
            lines[i] = lines[i].substring(8);

            lines[i] = lines[i].replaceAll(",",";");
            String[] linePart = lines[i].split(";");
            for (String numbersColor : linePart) {
                if (numbersColor.contains("red")) {
                    numbersColor = numbersColor.replaceAll(" red", "");
                    numbersColor = numbersColor.replaceAll(" ", "");
                    int numRed = Integer.parseInt(numbersColor);
                    System.out.println(numRed);
                    if (numRed > redMax) {
                        currentTrue = false;
                        continue;
                    }

                }
                if (numbersColor.contains("blue")){
                    numbersColor = numbersColor.replaceAll(" blue", "");
                    numbersColor = numbersColor.replaceAll(" ", "");
                    int numBlue = Integer.parseInt(numbersColor);
                    System.out.println(numBlue);
                    if (numBlue > blueMax) {
                        currentTrue = false;
                        continue;
                    }
                }

                if (numbersColor.contains("green")){
                    numbersColor = numbersColor.replaceAll(" green", "");
                    numbersColor = numbersColor.replaceAll(" ", "");
                    int numGreen = Integer.parseInt(numbersColor);
                    System.out.println(greenMax);
                    if (numGreen > greenMax) {
                        currentTrue = false;
                        continue;
                    }
                }
            }

            if (currentTrue) result += i+1;
        }



        //System.out.println(result);
    }

    public static String loadFile(String filename) {
        InputStream stream = Day2.Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
