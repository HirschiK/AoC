package Day11;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        partOne();
    }

    public static void partOne(){
        String inputRaw = loadFile("inputEleven.txt");
        System.out.println(inputRaw + "\n");

        String[] lines = inputRaw.split("\n");
        String[][]  map = mapBuilder(lines,-1,-1);
        String[][] originalMap = map;
        boolean empty;
        for(int i = 0; i<originalMap.length;i++){
            String[] line = originalMap[i];
            empty = true;
            for(int j = 0;j<line.length;j++){
                if(line[j] == null)continue;
                if(line[j].charAt(0) != '.'){
                    empty = false;
                    break;
                }
            }
            if(empty){
                System.out.println("Before mapBuilder: ");
                for(String []liness : map){
                    for(String character : liness){
                        System.out.print(character);
                    }
                    System.out.println();
                }
                map = mapBuilder(lines,i,-1);
                System.out.println("After mapBuilder: " + i + " "+ "-1");
                for(String []liness : map){
                    for(String character : liness){
                        System.out.print(character);
                    }
                    System.out.println();
                }

            }
        }
        for(String []liness : map){
            for(String character : liness){
                System.out.print(character);
            }
            System.out.println();
        }
    }

    public static String[][] mapBuilder(String[] lines, int duplicateRow,int duplicateColoumn){
        String[][]  map;
        //if(duplicateRow == -1 && duplicateColoumn == -1)map = new String[lines.length][lines[0].length()];
        map = new String[lines.length][lines[0].length()];
        if(duplicateRow >= 0)map = new String[lines.length+1][lines[0].length()];
        if(duplicateColoumn >=0)map = new String[lines.length][lines[0].length()+1];
        System.out.println(map.length +" should be: " + (lines.length+1));
        if(duplicateRow == -1 && duplicateColoumn == -1) {
            for (int i = 0; i < lines.length; i++) {
                for (int j = 0; j < lines[i].length(); j++) {
                    map[i][j] = String.valueOf(lines[i].charAt(j));
                }
            }
        }
        if(duplicateRow >= 0){
            for(int i = 0; i< lines.length;i++){
                for(int j = 0; j<lines[i].length();j++){
                    if (i <= duplicateRow) map[i][j]= String.valueOf(lines[i].charAt(j));
                    if (i == duplicateRow+1)map[i][j] = ".";
                    if(i> duplicateRow){
                        map[i][j] = String.valueOf(lines[i-1].charAt(j));
                        //System.out.println(String.valueOf(lines[i-1].charAt(j)));
                    }
                }
            }
            /*for(String []liness : map){
                for(String character : liness){
                    System.out.print(character);
                }
                System.out.println();
            }

             */
        }
        if(duplicateColoumn >=0){
            for(int i = 0; i< lines.length;i++){
                for(int j = 0; j<lines[j].length();j++){
                    if (j <= duplicateColoumn) map[i][j]= String.valueOf(lines[i].charAt(j));
                    if (j == duplicateColoumn+1)map[i][j] = ".";
                    if(j> duplicateColoumn) map[i][j] = String.valueOf(lines[i].charAt(j-1));
                }
            }
        }
        return map;
    }

    public static String loadFile(String filename) {
        InputStream stream = Day11.Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

