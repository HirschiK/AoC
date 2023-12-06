package Day6;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //partOne();
        partTwo();
    }

    public static void partOne(){
        String inputRaw = loadFile("inputSix.txt");
        String[] lines = inputRaw.split("\n");

        ArrayList<Integer> time = new ArrayList<>();
        lines[0] = lines[0].substring(lines[0].indexOf(':')+1);
        String[] split = lines[0].split(" ");
        for(String s : split){
            if(s.isBlank())continue;
            time.add(Integer.parseInt(s));
        }

        ArrayList<Integer> distance = new ArrayList<>();
        lines[1] = lines[1].substring(lines[1].indexOf(':')+1);
        split = lines[1].split(" ");
        for(String s : split){
            if(s.isBlank())continue;
            distance.add(Integer.parseInt(s));
        }

        int timesWonTotal = 1;

        for(int i = 0;i<time.size();i++){
            int timesWonCurrent = 0;
            int timeCurrent = time.get(i);
            for(int j = 0; j<timeCurrent;j++){
                int distanceCurrent = j * (timeCurrent-j);
                if(distanceCurrent>distance.get(i)){
                    timesWonCurrent++;
                }
            }
            System.out.println(timesWonCurrent);
            timesWonTotal *= timesWonCurrent;
        }

        System.out.println(timesWonTotal);

    }
    public static void partTwo(){
        String inputRaw = loadFile("inputSix.txt");
        String[] lines = inputRaw.split("\n");

        long time;
        lines[0] = lines[0].substring(lines[0].indexOf(':')+1);
        String timeString = lines[0].replaceAll(" ","");
        time = Long.parseLong(timeString);


        long distance;
        lines[1] = lines[1].substring(lines[1].indexOf(':')+1);
        String distanceString = lines[1].replaceAll(" ","");
        distance = Long.parseLong(distanceString);

        long timesWonTotal = 0;

            for(int j = 0; j<time;j++) {
                long distanceCurrent = j * (time - j);
                if (distanceCurrent > distance) {
                    timesWonTotal++;
                }
            }

        System.out.println(timesWonTotal);
    }

    public static String loadFile(String filename) {
        InputStream stream = Day6.Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
