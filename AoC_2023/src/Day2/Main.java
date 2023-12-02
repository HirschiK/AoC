package Day2;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        //partOne();
        //partTwo();
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
            currentTrue = true;
            lines[i] = lines[i].substring(lines[i].indexOf(":")+1);

            lines[i] = lines[i].replaceAll(",",";");
            System.out.println(lines[i]);
            String[] linePart = lines[i].split(";");


            for (int counter = 0; counter<linePart.length;counter++) {
                String numbersColor = linePart[counter];

                numbersColor = numbersColor.replaceAll(" ", "");

                if (numbersColor.contains("red")) {
                    numbersColor = numbersColor.replaceAll("[a-z]", "");


                    int numRed = Integer.parseInt(numbersColor.replaceAll("\\D", ""));
                    if (numRed > redMax) {
                        currentTrue = false;
                        System.out.println("too many red: " + numRed);
                        break;
                    }



                }
                if (numbersColor.contains("blue")){
                    numbersColor = numbersColor.replaceAll("[a-z]", "");
                    if (numbersColor.isEmpty()) numbersColor = "0";

                    int numBlue = Integer.parseInt(numbersColor.replaceAll("\\D", ""));
                    if (numBlue > blueMax) {
                        currentTrue = false;
                        System.out.println("too many blue: " + numBlue);
                        break;
                    }

                }

                if (numbersColor.contains("green")){
                    numbersColor = numbersColor.replaceAll("[a-z]", "");
                    if (numbersColor.isEmpty()) numbersColor = "0";

                    int numGreen = Integer.parseInt(numbersColor.replaceAll("\\D", ""));
                    if (numGreen > greenMax) {
                        currentTrue = false;
                        System.out.println("too many green: " + numGreen);
                        break;
                    }

                }


            }



            if (currentTrue) {
                result += i + 1;
                System.out.println("true: " + (i+1));
            }
            else System.out.println("false: " + (i+1));
        }

        System.out.println(result);
    }
    public static void partTwo(){
        String input = loadFile("inputTwo.txt");

        String[] lines = input.split("\n");
        int result = 0;
        boolean currentTrue;
        for (int i = 0;i<lines.length;i++){
            int redMax = 0;
            int blueMax  = 0;
            int greenMax  =0;
            currentTrue = true;
            lines[i] = lines[i].substring(lines[i].indexOf(":")+1);

            lines[i] = lines[i].replaceAll(",",";");
            System.out.println(lines[i]);
            String[] linePart = lines[i].split(";");


            for (int counter = 0; counter<linePart.length;counter++) {
                String numbersColor = linePart[counter];

                numbersColor = numbersColor.replaceAll(" ", "");

                if (numbersColor.contains("red")) {
                    numbersColor = numbersColor.replaceAll("[a-z]", "");


                    int numRed = Integer.parseInt(numbersColor.replaceAll("\\D", ""));
                    if (numRed > redMax) {
                        redMax = numRed;
                        continue;
                    }



                }
                if (numbersColor.contains("blue")){
                    numbersColor = numbersColor.replaceAll("[a-z]", "");
                    if (numbersColor.isEmpty()) numbersColor = "0";

                    int numBlue = Integer.parseInt(numbersColor.replaceAll("\\D", ""));
                    if (numBlue > blueMax) {
                        blueMax = numBlue;
                        continue;
                    }

                }

                if (numbersColor.contains("green")){
                    numbersColor = numbersColor.replaceAll("[a-z]", "");
                    if (numbersColor.isEmpty()) numbersColor = "0";

                    int numGreen = Integer.parseInt(numbersColor.replaceAll("\\D", ""));
                    if (numGreen > greenMax) {
                        greenMax = numGreen;
                        continue;
                    }

                }


            }


            result += (redMax*greenMax*blueMax);
        }


        System.out.println(result);
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
