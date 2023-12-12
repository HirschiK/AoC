package Day8;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //partOne();
        partTwo();
    }

    public static void partOne(){
        String rawInput = loadFile("inputEight.txt");
        System.out.println("Raw Input:");
        System.out.println(rawInput);
        System.out.println();
        String directions = rawInput.substring(0,rawInput.indexOf("\n"));

        ArrayList<Boolean> directionsArray = new ArrayList<>();
        for(int i = 0; i<directions.length();i++)directionsArray.add(directions.charAt(i) == 'R');

        ArrayList<String> field = new ArrayList<>();
        ArrayList<String> fieldToLeft = new ArrayList<>();
        ArrayList<String> fieldToRight = new ArrayList<>();

        String inputWithoutDirection = rawInput.substring(rawInput.indexOf("\n"));
        String[] lines = inputWithoutDirection.split("\n");
        for(String line: lines){
            if(line.isBlank())continue;
            field.add(line.substring(0,3));
            fieldToLeft.add(line.substring(7,10));
            fieldToRight.add(line.substring(12,15));
        }

        int currentField = field.indexOf("DVA");
        int endField = field.indexOf("XDZ");
        int steps = 0;

        while (currentField != endField){
            if(steps != 0 && steps%directionsArray.size()==0){
                for(int i = 0; i<directions.length();i++)directionsArray.add(directions.charAt(i) == 'R');
            }
            if(directionsArray.get(steps)){
                steps++;
                currentField = field.indexOf(fieldToRight.get(currentField));
            }
            else {
                steps++;
                currentField = field.indexOf(fieldToLeft.get(currentField));
            }
        }
        System.out.println(steps);

    }

    public static void partTwo(){
        String rawInput = loadFile("inputEight.txt");
        System.out.println("Raw Input:");
        System.out.println(rawInput);
        System.out.println();
        String directions = rawInput.substring(0,rawInput.indexOf("\n"));

        ArrayList<Boolean> directionsArray = new ArrayList<>();
        for(int i = 0; i<directions.length();i++)directionsArray.add(directions.charAt(i) == 'R');

        ArrayList<String> field = new ArrayList<>();
        ArrayList<String> fieldToLeft = new ArrayList<>();
        ArrayList<String> fieldToRight = new ArrayList<>();
        ArrayList<Integer> timesToLoop = new ArrayList<>();

        String inputWithoutDirection = rawInput.substring(rawInput.indexOf("\n"));
        String[] lines = inputWithoutDirection.split("\n");
        for(String line: lines){
            if(line.isBlank())continue;
            field.add(line.substring(0,3));
            fieldToLeft.add(line.substring(7,10));
            fieldToRight.add(line.substring(12,15));
        }

        //int currentField = field.indexOf("AAA");
        ArrayList<Integer> currentField = new ArrayList<>();
        ArrayList<Integer> endField = new ArrayList<>();
        for(int i = 0; i< field.size();i++){
            if(field.get(i).charAt(2) == 'A')currentField.add(i);
            if(field.get(i).charAt(2)=='Z')endField.add(i);
        }

        for(int i : currentField) System.out.println("Starting Value: "+field.get(i));
        for(int i : endField) System.out.println("End Value: "+field.get(i)+ " Index: "+i);
        int steps = 0;
        int counter = 0;

        /*
        while (!checkEnd(currentField,endField,field)){
            counter++;
            //if(counter == 44399)return;
            if (steps != 0 && steps % directionsArray.size() == 0) {
                for (int i = 0; i < directions.length(); i++) directionsArray.add(directions.charAt(i) == 'R');
                System.out.println("Did one loop");
            }
            for(int j = 0;j< currentField.size();j++) {
                //System.out.println(directionsArray.get(steps));
                if (directionsArray.get(steps)) {
                    currentField.set(j,field.indexOf(fieldToRight.get(currentField.get(j))));
                } else {
                    currentField.set(j,field.indexOf(fieldToLeft.get(currentField.get(j))));
                }
                //System.out.println("Element: "+ j + " Field: "+field.get(currentField.get(j)));
            }
            steps++;
        }

         */
        for(int i = 0 ;i< currentField.size();i++){
            int current = currentField.get(i);
            steps = 0;
            directionsArray.clear();
            for(int j = 0; j<directions.length();j++)directionsArray.add(directions.charAt(j) == 'R');
            while (!endField.contains(currentField.get(i))){
                if(steps != 0 && steps%directionsArray.size()==0){
                    for(int j = 0; j<directions.length();j++)directionsArray.add(directions.charAt(j) == 'R');
                }
                if(directionsArray.get(steps)){
                    steps++;
                    currentField.set(i,field.indexOf((fieldToRight.get(currentField.get(i)))));
                }
                else {
                    steps++;
                    currentField.set(i,field.indexOf(fieldToLeft.get(currentField.get(i)))) ;
                }
            }
            timesToLoop.add(steps);


        }

        long result ;
        result = lcm(timesToLoop.get(0),lcm(timesToLoop.get(1),lcm(timesToLoop.get(2),lcm(timesToLoop.get(3),lcm(timesToLoop.get(4), timesToLoop.get(5))))));
        System.out.println(result);
    }
    public static boolean checkEnd(ArrayList<Integer> currentfield, ArrayList<Integer> endField,ArrayList<String>field){
        System.out.println();
        for(int current: currentfield){
            System.out.println(field.get(current));
            if(!endField.contains(current)) return false;
        }
        return true;
    }
    private static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }
    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }


    public static String loadFile(String filename) {
        InputStream stream = Day8.Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
