package Day10;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        //partOne();
        partTwo();
    }

    public static void partOne(){
        String inputRaw = loadFile("inputTen.txt");
        System.out.println("InputRaw: \n"+inputRaw);

        int startX= 0;
        int startY = 0;
        String[] lines = inputRaw.split("\n");
        String[][] map = new String[lines[0].length()][lines.length];
        for(int i = 0; i< lines.length;i++){
            String line = lines[i];
            for(int j = 0; j<line.length();j++){
                map[i][j] = String.valueOf(line.charAt(j));
                if(String.valueOf(line.charAt(j)).equals("S")) {
                    startX = j;
                    startY = i;
                }
            }
        }
        int[][] mapValues = new int[lines[0].length()][lines.length];
        for(int i = 0; i<mapValues.length;i++){
            Arrays.fill(mapValues[i],0);
        }

        int startTile1X=0;
        int startTile1Y=0;
        int startTile2X=0;
        int startTile2Y=0;
        if(Objects.equals(map[startY][startX + 1], "-") || Objects.equals(map[startY][startX + 1], "J") || Objects.equals(map[startY][startX + 1], "7")){
            startTile1X = startX + 1;
            startTile1Y = startY;

        }
        if(Objects.equals(map[startY][startX - 1], "-") || Objects.equals(map[startY][startX - 1], "L") || Objects.equals(map[startY][startX - 1], "F")){
            if(startTile1Y == 0 && startTile1X == 0){
                startTile1X = startX-1;
                startTile1Y = startY;
            }
            else{
                startTile2X = startX-1;
                startTile2Y = startY;
            }
        }
        if(Objects.equals(map[startY + 1][startX], "|") || Objects.equals(map[startY + 1][startX], "J") || Objects.equals(map[startY + 1][startX], "L")){
            if(startTile1Y == 0 && startTile1X == 0){
                startTile1X = startX;
                startTile1Y = startY+1;
            }
            else{
                startTile2X = startX;
                startTile2Y = startY+1;
            }
        }
        if(Objects.equals(map[startY - 1][startX], "|") || Objects.equals(map[startY - 1][startX], "F") || Objects.equals(map[startY - 1][startX], "7")){
            if(startTile1Y == 0 && startTile1X == 0){
                startTile1X = startX;
                startTile1Y = startY-1;
            }
            else{
                startTile2X = startX;
                startTile2Y = startY-1;
            }
        }

        int prevY = startY;
        int prevX = startX;
        int currentY = startTile1Y;
        int currentX = startTile1X;
        int xDistance = currentX-prevX;
        int yDistance = currentY-prevY;
        String current = map[startTile1Y][startTile1X];

        int counter = 1;
        while (!Objects.equals(current,"S")){
                mapValues[currentY][currentX] = counter;
                switch (current){
                    case "-":
                        if(xDistance == 1){
                            currentX++;
                            xDistance = 1;
                            yDistance = 0;
                            current= map[currentY][currentX];
                        }
                        else {
                            currentX--;
                            xDistance = -1;
                            yDistance = 0;
                            current = map[currentY][currentX];
                        }
                        break;
                    case"L":
                        if(xDistance == -1) {
                            currentY--;
                            yDistance = -1;
                            xDistance = 0;
                            current = map[currentY][currentX];
                        }
                        else{
                            currentX++;
                            xDistance = 1;
                            yDistance = 0;
                            current = map[currentY][currentX];
                        }
                        break;
                    case"F":
                        if(xDistance == -1){
                            currentY++;
                            yDistance = 1;
                            xDistance = 0;
                            current = map[currentY][currentX];
                        }
                        else {
                            currentX++;
                            xDistance = 1;
                            yDistance = 0;
                            current = map[currentY][currentX];
                        }
                        break;
                    case "7":
                        if(xDistance == 1){
                            currentY++;
                            yDistance = 1;
                            xDistance = 0;
                            current = map[currentY][currentX];
                        }
                        else {
                            currentX--;
                            xDistance = -1;
                            yDistance = 0;
                            current = map[currentY][currentX];
                        }
                        break;
                    case "J":
                        if(xDistance==1){
                            currentY--;
                            yDistance = -1;
                            xDistance = 0;
                            current = map[currentY][currentX];
                        }
                        else {
                            currentX--;
                            xDistance = -1;
                            yDistance = 0;
                            current = map[currentY][currentX];
                        }
                        break;
                    case "|":
                        if(yDistance == 1){
                            currentY++;
                            yDistance = 1;
                            xDistance = 0;
                            current = map[currentY][currentX];
                        }
                        else {
                            currentY--;
                            yDistance = -1;
                            xDistance = 0;
                            current = map[currentY][currentX];
                        }
                        break;
                    default:
                        System.out.println("ERROR F*CK");
                }
                counter++;
        }

        prevY = startY;
        prevX = startX;
        currentY = startTile2Y;
        currentX = startTile2X;
        xDistance = currentX-prevX;
        yDistance = currentY-prevY;
        current = map[startTile2Y][startTile2X];
        counter = 1;
        while (!Objects.equals(current,"S")){
            if(counter <mapValues[currentY][currentX])mapValues[currentY][currentX] = counter;
            switch (current){
                case "-":
                    if(xDistance == 1){
                        currentX++;
                        xDistance = 1;
                        yDistance = 0;
                        current= map[currentY][currentX];
                    }
                    else {
                        currentX--;
                        xDistance = -1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case"L":
                    if(xDistance == -1) {
                        currentY--;
                        yDistance = -1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else{
                        currentX++;
                        xDistance = 1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case"F":
                    if(xDistance == -1){
                        currentY++;
                        yDistance = 1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentX++;
                        xDistance = 1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case "7":
                    if(xDistance == 1){
                        currentY++;
                        yDistance = 1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentX--;
                        xDistance = -1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case "J":
                    if(xDistance==1){
                        currentY--;
                        yDistance = -1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentX--;
                        xDistance = -1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case "|":
                    if(yDistance == 1){
                        currentY++;
                        yDistance = 1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentY--;
                        yDistance = -1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                default:
                    System.out.println("ERROR F*CK");
            }
            counter++;
        }

        System.out.println();
        int result = 0;
        for(int[] i : mapValues){
            for(int j :i){
                System.out.print(j);
                if(j > result)result = j;
            }
            System.out.println();;
        }

        System.out.println("\nRESULT:");
        System.out.println("\t"+result);
    }
    public static void partTwo(){
        String inputRaw = loadFile("inputTen.txt");
        System.out.println("InputRaw: \n"+inputRaw);

        int startX= 0;
        int startY = 0;
        String[] lines = inputRaw.split("\n");
        String[][] map = new String[lines.length][lines[0].length()];
        System.out.println(lines.length);
        for(int i = 0; i< lines.length;i++){
            String line = lines[i];
            for(int j = 0; j<line.length();j++){
                map[i][j] = String.valueOf(line.charAt(j));
                if(String.valueOf(line.charAt(j)).equals("S")) {
                    startX = j;
                    startY = i;
                }
            }
        }
        int[][] mapValues = new int[lines.length][lines[0].length()];
        for(int i = 0; i<mapValues.length;i++){
            Arrays.fill(mapValues[i],0);
        }

        int startTile1X=0;
        int startTile1Y=0;
        int startTile2X=0;
        int startTile2Y=0;
        if(Objects.equals(map[startY][startX + 1], "-") || Objects.equals(map[startY][startX + 1], "J") || Objects.equals(map[startY][startX + 1], "7")){
            startTile1X = startX + 1;
            startTile1Y = startY;

        }
        System.out.println(startY);
        if(Objects.equals(map[startY][startX - 1], "-") || Objects.equals(map[startY][startX - 1], "L") || Objects.equals(map[startY][startX - 1], "F")){
            if(startTile1Y == 0 && startTile1X == 0){
                startTile1X = startX-1;
                startTile1Y = startY;
            }
            else{
                startTile2X = startX-1;
                startTile2Y = startY;
            }
        }
        if(Objects.equals(map[startY + 1][startX], "|") || Objects.equals(map[startY + 1][startX], "J") || Objects.equals(map[startY + 1][startX], "L")){
            if(startTile1Y == 0 && startTile1X == 0){
                startTile1X = startX;
                startTile1Y = startY+1;
            }
            else{
                startTile2X = startX;
                startTile2Y = startY+1;
            }
        }
        if(startY == 0);
        else if(Objects.equals(map[startY - 1][startX], "|") || Objects.equals(map[startY - 1][startX], "F") || Objects.equals(map[startY - 1][startX], "7")){
            if(startTile1Y == 0 && startTile1X == 0){
                startTile1X = startX;
                startTile1Y = startY-1;
            }
            else{
                startTile2X = startX;
                startTile2Y = startY-1;
            }
        }

        int prevY = startY;
        int prevX = startX;
        int currentY = startTile1Y;
        int currentX = startTile1X;
        int xDistance = currentX-prevX;
        int yDistance = currentY-prevY;
        String current = map[startTile1Y][startTile1X];

        int counter = 1;
        while (!Objects.equals(current,"S")){
            mapValues[currentY][currentX] = counter;
            switch (current){
                case "-":
                    if(xDistance == 1){
                        currentX++;
                        xDistance = 1;
                        yDistance = 0;
                        current= map[currentY][currentX];
                    }
                    else {
                        currentX--;
                        xDistance = -1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case"L":
                    if(xDistance == -1) {
                        currentY--;
                        yDistance = -1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else{
                        currentX++;
                        xDistance = 1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case"F":
                    if(xDistance == -1){
                        currentY++;
                        yDistance = 1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentX++;
                        xDistance = 1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case "7":
                    if(xDistance == 1){
                        currentY++;
                        yDistance = 1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentX--;
                        xDistance = -1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case "J":
                    if(xDistance==1){
                        currentY--;
                        yDistance = -1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentX--;
                        xDistance = -1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case "|":
                    if(yDistance == 1){
                        currentY++;
                        yDistance = 1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentY--;
                        yDistance = -1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                default:
                    System.out.println("ERROR F*CK");
            }
            counter++;
        }

        prevY = startY;
        prevX = startX;
        currentY = startTile2Y;
        currentX = startTile2X;
        xDistance = currentX-prevX;
        yDistance = currentY-prevY;
        current = map[startTile2Y][startTile2X];
        counter = 1;
        while (!Objects.equals(current,"S")){
            if(counter <mapValues[currentY][currentX])mapValues[currentY][currentX] = counter;
            switch (current){
                case "-":
                    if(xDistance == 1){
                        currentX++;
                        xDistance = 1;
                        yDistance = 0;
                        current= map[currentY][currentX];
                    }
                    else {
                        currentX--;
                        xDistance = -1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case"L":
                    if(xDistance == -1) {
                        currentY--;
                        yDistance = -1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else{
                        currentX++;
                        xDistance = 1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case"F":
                    if(xDistance == -1){
                        currentY++;
                        yDistance = 1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentX++;
                        xDistance = 1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case "7":
                    if(xDistance == 1){
                        currentY++;
                        yDistance = 1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentX--;
                        xDistance = -1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case "J":
                    if(xDistance==1){
                        currentY--;
                        yDistance = -1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentX--;
                        xDistance = -1;
                        yDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                case "|":
                    if(yDistance == 1){
                        currentY++;
                        yDistance = 1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    else {
                        currentY--;
                        yDistance = -1;
                        xDistance = 0;
                        current = map[currentY][currentX];
                    }
                    break;
                default:
                    System.out.println("ERROR F*CK");
            }
            counter++;
        }

        mapValues = virusSpread(mapValues,0,0);

        //rember to add -1
        int result = 0;

        for(int[] i : mapValues){
            for(int j :i){
                System.out.print(j);
                if(j == 0)result++;
            }
            System.out.println();;
        }


        int[][] printMap= new int[mapValues.length][mapValues[0].length];
        for(int i = 0; i< mapValues.length;i++){
            printMap[i]= Arrays.copyOf(mapValues[i],mapValues[i].length);
        }

        for(int i = 0;i<printMap.length;i++){
            for(int j = 0; j<printMap[i].length;j++){
                if(printMap[i][j]>0)printMap[i][j] = 1;
                if(printMap[i][j]<0)printMap[i][j] = 2;
            }
        }

        for(int[] mapLine : printMap){
            for(int character: mapLine) System.out.print(character);
            System.out.println();
        }
        result--;

        System.out.println("\nRESULT:");
        System.out.println("\t"+result);
    }

    public static int[][] virusSpread(int [][] map,int yCurrent,int xCurrent){
        map[yCurrent][xCurrent] = -1;
        if(xCurrent!=0&&map[yCurrent][xCurrent-1]==0)map = virusSpread(map,yCurrent,xCurrent-1);
        if(xCurrent != map[yCurrent].length-1&&map[yCurrent][xCurrent+1]==0)map = virusSpread(map,yCurrent,xCurrent+1);
        if(yCurrent!=0&&map[yCurrent-1][xCurrent] == 0)map = virusSpread(map,yCurrent-1,xCurrent);
        if(yCurrent!= map.length-1&& map[yCurrent+1][xCurrent]==0) map = virusSpread(map,yCurrent+1,xCurrent);
        return  map;
    }

    public static String loadFile(String filename) {
        InputStream stream = Day10.Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
