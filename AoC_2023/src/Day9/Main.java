package Day9;

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
        String rawInput = loadFile("inputNine.txt");
        String[] lines = rawInput.split("\n");
        long result = 0;

        for(String line : lines){
            System.out.println("Current Line:");
            System.out.println("\t"+ line);
            String[] numbersAsString = line.split(" ");
            ArrayList<Integer> numbers = new ArrayList<>();
            for(String numberAsString : numbersAsString){
                    numbers.add(Integer.parseInt(numberAsString));
            }

            int currentResult = recursionPartOne(numbers) + numbers.getLast();
            result += currentResult;
            System.out.println("\tCurrent Result: "+ currentResult);
        }
        System.out.println(result);
    }
    public static int recursionPartOne(ArrayList<Integer> numbers){
        ArrayList<Integer> numbersAfterChange = new ArrayList<>();
        for(int i = numbers.size()-2;i>=0;i--){
            int num = numbers.get(i+1)-numbers.get(i);
            numbersAfterChange.add(0,num);
        }

        //ArrayList<Integer> checkForend = numbersAfterChange;
        ArrayList<Integer> checkForend = new ArrayList<>(numbersAfterChange);
        checkForend.removeAll(Collections.singleton(0));
        if(checkForend.isEmpty())return 0;
        return numbersAfterChange.getLast() + recursionPartOne(numbersAfterChange);
    }
    public static void partTwo(){
        String rawInput = loadFile("inputNine.txt");
        String[] lines = rawInput.split("\n");
        long result = 0;

        for(String line : lines){
            System.out.println("Current Line:");
            System.out.println("\t"+ line);
            String[] numbersAsString = line.split(" ");
            ArrayList<Integer> numbers = new ArrayList<>();
            for(String numberAsString : numbersAsString){
                numbers.add(Integer.parseInt(numberAsString));
            }

            int currentResult =numbers.getFirst()- recursionPartTwo(numbers);
            result += currentResult;
            System.out.println("\tCurrent Result: "+ currentResult);
        }
        System.out.println(result);
    }
    public static int recursionPartTwo(ArrayList<Integer> numbers){
        ArrayList<Integer> numbersAfterChange = new ArrayList<>();
        for(int i = numbers.size()-2;i>=0;i--){
            int num = numbers.get(i+1)-numbers.get(i);
            numbersAfterChange.add(0,num);
        }

        //ArrayList<Integer> checkForend = numbersAfterChange;
        ArrayList<Integer> checkForend = new ArrayList<>(numbersAfterChange);
        checkForend.removeAll(Collections.singleton(0));
        if(checkForend.isEmpty())return 0;
        return numbersAfterChange.getFirst() - recursionPartTwo(numbersAfterChange);
    }

    public static String loadFile(String filename) {
        InputStream stream = Day9.Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
