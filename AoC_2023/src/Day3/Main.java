package Day3;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        //partOne();
        partTwo();
    }

    public static void partOne(){
        String inputRaw = loadFile("inputThree.txt");
        System.out.println(inputRaw);
        String[] lines = inputRaw.split("\n");
        ArrayList<Integer> resultArray = new ArrayList<Integer>();
        int result = 0;


        for(int line =0;line < lines.length;line++){
            //System.out.println(line);
            for(int index = 0; index <lines[line].length();index++){
                int indexStartDigit = -1;
                int indexEndDigit = -1;
                if (Character.isDigit(lines[line].charAt(index))){
                    if(indexStartDigit == -1) indexStartDigit = index;
                    for(int indexNum = index; indexNum<lines[line].length();indexNum++){
                        if(indexNum == lines[line].length()-1){
                            indexEndDigit = indexNum;
                            break;
                        }
                        if(Character.isDigit(lines[line].charAt(indexNum+1))){
                            continue;
                        }
                        indexEndDigit = indexNum;
                        break;
                    }

                    int num = Integer.parseInt(lines[line].substring(indexStartDigit,indexEndDigit+1));
                    //if(num == 921) System.out.println("starti " + indexStartDigit + " endi " + indexEndDigit);
                    if(num==467) System.out.println("prevline");

                    if(indexStartDigit!=0){
                        if (lines[line].charAt(indexStartDigit-1)!='.') {
                            resultArray.add(num);

                            index = indexEndDigit+1;
                            indexStartDigit = -1;
                            indexEndDigit = -1;
                            continue;
                        }
                    }
                    if(indexEndDigit != lines[line].length()-1){
                        if (lines[line].charAt(indexEndDigit+1)!= '.') {
                            resultArray.add(num);
                            index = indexEndDigit+1;
                            continue;
                        }
                    }
                    if(indexStartDigit != 0){
                        if(line != 0){
                            String prevline;
                            if(indexEndDigit == lines[line].length()-1) prevline = lines[line-1].substring(indexStartDigit-1,indexEndDigit+1);
                            else prevline = lines[line-1].substring(indexStartDigit-1,indexEndDigit+2);
                            prevline = prevline.replaceAll("\\.","");
                            prevline = prevline.replaceAll("[0-9]","");

                            if(!prevline.isBlank()){
                                resultArray.add(num);
                                index = indexEndDigit+1;
                                continue;
                            }
                        }
                        if(line != lines.length-1){
                            String nextline;
                            if(indexEndDigit == lines[line].length()-1) nextline = lines[line+1].substring(indexStartDigit-1,indexEndDigit+1);
                            else nextline = lines[line+1].substring(indexStartDigit-1,indexEndDigit+2);
                            if(num == 1) System.out.println(nextline);

                            nextline = nextline.replaceAll("\\.","");
                            nextline = nextline.replaceAll("[0-9]","");

                            if(!nextline.isBlank()){
                                resultArray.add(num);
                                index = indexEndDigit+1;
                                continue;
                            }
                        }
                    }

                    if(indexStartDigit == 0){
                        if(line != 0){
                            String prevline;
                            if(indexEndDigit == lines[line].length()-1) prevline = lines[line-1].substring(indexStartDigit,indexEndDigit);
                            else prevline = lines[line-1].substring(indexStartDigit,indexEndDigit+2);

                            prevline = prevline.replaceAll("\\.","");
                            prevline = prevline.replaceAll("[0-9]","");

                            if(!prevline.isBlank()){
                                resultArray.add(num);
                                index = indexEndDigit+1;
                                continue;
                            }
                        }
                        if(line != lines.length-1){
                            String nextline;
                            if(indexEndDigit == lines[line].length()-1) nextline = lines[line-1].substring(indexStartDigit,indexEndDigit);
                            else {
                                nextline = lines[line+1].substring(indexStartDigit,indexEndDigit+2);
                            }
                            nextline = nextline.replaceAll("\\.","");
                            nextline = nextline.replaceAll("[0-9]","");

                            if(!nextline.isBlank()){
                                resultArray.add(num);
                                index = indexEndDigit+1;
                                continue;
                            }
                        }
                    }
                    index = indexEndDigit+1;
                }
            }
        }
        System.out.println();
        for(int i = 0;i<resultArray.size();i++){
            result += resultArray.get(i);
            System.out.println(resultArray.get(i));
        }
        System.out.println(result);
    }
    public static void partTwo(){
        String rawInput = loadFile("inputThree.txt");
        rawInput = rawInput.replaceAll("\\$",".");
        rawInput = rawInput.replaceAll("=",".");
        rawInput = rawInput.replaceAll("%",".");
        rawInput = rawInput.replaceAll("/",".");
        rawInput = rawInput.replaceAll("\\+",".");
        rawInput = rawInput.replaceAll("@",".");
        rawInput = rawInput.replaceAll("#",".");
        rawInput = rawInput.replaceAll("-",".");
        rawInput = rawInput.replaceAll("&",".");

        System.out.println("rawInput:");
        System.out.println(rawInput);
        System.out.println();

        int result = 0;

        String[]lines = rawInput.split("\n");

        String[][] map = new String[lines[0].length()][lines.length];
        for (int i = 0; i< lines.length;i++){
            String currentLine = lines[i];
            for(int j = 0; j< currentLine.length();j++){
                char currentEntry = currentLine.charAt(j);
                map[i][j] = String.valueOf(currentEntry);
            }
        }

        String currentNumber = "";
        int amountofNumbers = 0;

        ArrayList<Integer> numbers = new ArrayList<>();

        for(int i = 0; i<map.length;i++){
            String[] line = map[i];
            for(int j = 0; j <line.length;j++){
                char currentChar = line[j].charAt(0);
                if(currentChar == '.' || currentChar == '*'){
                    if (currentNumber.isBlank())continue;
                    int prevNumber = Integer.parseInt(currentNumber);
                    numbers.add(prevNumber);
                    currentNumber = "";
                    amountofNumbers += 1;
                    continue;

                }
                if(Character.isDigit(currentChar)){
                    currentNumber += currentChar;
                    map[i][j] = String.valueOf(amountofNumbers);
                }
            }
        }

        for(int i = 0; i<map.length;i++) {
            String[] line = map[i];
            for (int j = 0; j < line.length; j++) {
                char currentChar = line[j].charAt(0);
                ArrayList<Integer> numbersNextToStar = new ArrayList<>();

                if(currentChar == '*'){
                    if(!Objects.equals(map[i - 1][j - 1], ".")&& !Objects.equals(map[i - 1][j - 1], "*")){
                        numbersNextToStar.add(Integer.parseInt(map[i-1][j-1]));
                    }
                    if(!Objects.equals(map[i - 1][j], ".")&& !Objects.equals(map[i - 1][j], "*")){
                        if(!numbersNextToStar.contains(Integer.parseInt(map[i-1][j])))numbersNextToStar.add(Integer.parseInt(map[i-1][j]));
                    }
                    if(!Objects.equals(map[i - 1][j+1], ".")&& !Objects.equals(map[i - 1][j+1], "*")){
                        if(!numbersNextToStar.contains(Integer.parseInt(map[i-1][j+1])))numbersNextToStar.add(Integer.parseInt(map[i-1][j+1]));
                    }
                    if(!Objects.equals(map[i][j-1], ".")&& !Objects.equals(map[i][j-1], "*")){
                        if(!numbersNextToStar.contains(Integer.parseInt(map[i][j-1])))numbersNextToStar.add(Integer.parseInt(map[i][j-1]));
                    }
                    if(!Objects.equals(map[i][j+1], ".")&& !Objects.equals(map[i][j+1], "*")){
                        if(!numbersNextToStar.contains(Integer.parseInt(map[i][j+1])))numbersNextToStar.add(Integer.parseInt(map[i][j+1]));
                    }
                    if(!Objects.equals(map[i+1][j-1], ".")&& !Objects.equals(map[i+1][j-1], "*")){
                        if(!numbersNextToStar.contains(Integer.parseInt(map[i+1][j-1])))numbersNextToStar.add(Integer.parseInt(map[i+1][j-1]));
                    }
                    if(!Objects.equals(map[i+1][j], ".")&& !Objects.equals(map[i+1][j], "*")){
                        if(!numbersNextToStar.contains(Integer.parseInt(map[i+1][j])))numbersNextToStar.add(Integer.parseInt(map[i+1][j]));
                    }
                    if(!Objects.equals(map[i+1][j+1], ".")&& !Objects.equals(map[i+1][j+1], "*")){
                        if(!numbersNextToStar.contains(Integer.parseInt(map[i+1][j+1])))numbersNextToStar.add(Integer.parseInt(map[i+1][j+1]));
                    }

                }
                if(numbersNextToStar.size() == 2)result += numbers.get(numbersNextToStar.get(0))*numbers.get(numbersNextToStar.get(1));
            }
        }


        System.out.println(result);
    }
    public static String loadFile(String filename) {
        InputStream stream = Day3.Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
