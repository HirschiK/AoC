package Day1;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        //partOne();
        partTwo();
    }
    public static void partOne(){
        String input = loadFile("inputOne.txt");
        input = input.replaceAll("([a-z])","");
        String[] lines = input.split("\n");
        int result = 0;

        for (String content :lines){
            result += 10*Character.getNumericValue(content.charAt(0)) + Character.getNumericValue(content.charAt(content.length()-1));
        }
        System.out.println(result);
    }
    public static void partTwo(){

        String input = loadFile("inputOne.txt");

        input = input.replaceAll("one","o1e");
        input = input.replaceAll("two","t2o");
        input = input.replaceAll("three","t3e");
        input = input.replaceAll("four","f4r");
        input = input.replaceAll("five","f5e");
        input = input.replaceAll("six","s6x");
        input = input.replaceAll("seven","s7n");
        input = input.replaceAll("eight","e8t");
        input = input.replaceAll("nine","n9e");
        input = input.replaceAll("([a-z])","");

        int result = 0;
        String[] lines = input.split("\n");

        for (String content :lines){
            result += 10*Character.getNumericValue(content.charAt(0)) + Character.getNumericValue(content.charAt(content.length()-1));
        }
        System.out.println(result);

    }


    public static String loadFile(String filename) {
        InputStream stream = Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }
}

