package Day5;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //partOne();
        partTwo();
    }

    public static void partOne(){
        String inputRaw = loadFile("inputFive");

        String seedsline = inputRaw.substring(7,inputRaw.indexOf("\n"));
        seedsline += " ;";
        ArrayList<Long> seeds = new ArrayList<Long>();
        while (true){
            if(seedsline.charAt(0) == ';')break;
            seeds.add((Long.parseLong(seedsline.substring(0,seedsline.indexOf(" ")))));
            seedsline = seedsline.substring(seedsline.indexOf(" ")+1);
        }
        
        String seedToSoilMap = inputRaw.substring(inputRaw.indexOf("seed-to-soil map:"),inputRaw.indexOf("soil-to-fertilizer map:"));
        seedToSoilMap = seedToSoilMap.substring(seedToSoilMap.indexOf("\n")+1);
        ArrayList<Long> seedToSoil = new ArrayList<>();
        for (long i: seeds) seedToSoil.add(i);
        for(long i: seedToSoil){
            System.out.println(i);
        }
        String lines[] = seedToSoilMap.split("\n");
        for (String line : lines){
            long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long rangeLength = Long.parseLong(line);


            for(int i = 0; i<seeds.size();i++){
                long seed = seeds.get(i);
                if(sourceRangeStart <= seed && seed <= sourceRangeStart+rangeLength){
                    seedToSoil.set(seedToSoil.indexOf(seed),seed+(destinationRangeStart-sourceRangeStart));
                }
            }
        }
        System.out.println();
        for(long i: seedToSoil){
            System.out.println(i);
        }

        String soilToFertilizerMap = inputRaw.substring(inputRaw.indexOf("soil-to-fertilizer map:"),inputRaw.indexOf("fertilizer-to-water map:"));
        soilToFertilizerMap = soilToFertilizerMap.substring(soilToFertilizerMap.indexOf("\n")+1);
        ArrayList<Long> soilToFertilizer = new ArrayList<>();
        for (long i: seedToSoil) soilToFertilizer.add(i);
        lines = soilToFertilizerMap.split("\n");
        for (String line : lines){
            long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long rangeLength = Long.parseLong(line);

            for(int i = 0; i<soilToFertilizer.size();i++){
                long soil = seedToSoil.get(i);
                if(sourceRangeStart <= soil && soil <= sourceRangeStart+rangeLength){
                    soilToFertilizer.set(soilToFertilizer.indexOf(soil),soil+(destinationRangeStart-sourceRangeStart));
                }
            }
        }

        System.out.println();
        for(long i: soilToFertilizer){
            System.out.println(i);
        }

        String fertilizerToWaterMap = inputRaw.substring(inputRaw.indexOf("fertilizer-to-water map:"),inputRaw.indexOf("water-to-light map:"));
        fertilizerToWaterMap = fertilizerToWaterMap.substring(fertilizerToWaterMap.indexOf("\n")+1);
        ArrayList<Long> fertilizerToWater = new ArrayList<>();
        for (long i: soilToFertilizer) fertilizerToWater.add(i);
        lines = fertilizerToWaterMap.split("\n");
        for (String line : lines){
            long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long rangeLength = Long.parseLong(line);
            for(int i = 0; i<fertilizerToWater.size();i++){
                long fertilizer = soilToFertilizer.get(i);
                if(sourceRangeStart <= fertilizer && fertilizer <= sourceRangeStart+rangeLength){
                    fertilizerToWater.set(fertilizerToWater.indexOf(fertilizer),fertilizer+(destinationRangeStart-sourceRangeStart));
                }
            }
        }

        System.out.println();
        for(long i: fertilizerToWater ){
            System.out.println(i);
        }

        String waterToLightMap = inputRaw.substring(inputRaw.indexOf("water-to-light map:"),inputRaw.indexOf("light-to-temperature map:"));
        waterToLightMap = waterToLightMap.substring(waterToLightMap.indexOf("\n")+1);
        ArrayList<Long> waterToLight = new ArrayList<>();
        for (long i: fertilizerToWater) waterToLight.add(i);
        lines = waterToLightMap.split("\n");
        for (String line : lines){
            long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long rangeLength = Long.parseLong(line);
            for(int i = 0; i<waterToLight.size();i++){
                long water = fertilizerToWater.get(i);
                if(sourceRangeStart <= water && water <= sourceRangeStart+rangeLength){
                    waterToLight.set(waterToLight.indexOf(water),water+(destinationRangeStart-sourceRangeStart));
                }
            }
        }

        String lightToTemperatureMap = inputRaw.substring(inputRaw.indexOf("light-to-temperature map:"),inputRaw.indexOf("temperature-to-humidity map:"));
        lightToTemperatureMap = lightToTemperatureMap.substring(lightToTemperatureMap.indexOf("\n")+1);
        ArrayList<Long> lightToTemperature = new ArrayList<>();
        for (long i: waterToLight) lightToTemperature.add(i);
        lines = lightToTemperatureMap.split("\n");
        for (String line : lines){
            long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long rangeLength = Long.parseLong(line);
            for(int i = 0; i<lightToTemperature.size();i++){
                long temperature = waterToLight.get(i);
                if(sourceRangeStart <= temperature && temperature <= sourceRangeStart+rangeLength){
                    lightToTemperature.set(lightToTemperature.indexOf(temperature),temperature+(destinationRangeStart-sourceRangeStart));
                }
            }
        }



        String temperatureToHumidityMap = inputRaw.substring(inputRaw.indexOf("temperature-to-humidity map:"),inputRaw.indexOf("humidity-to-location map:"));
        temperatureToHumidityMap = temperatureToHumidityMap.substring(temperatureToHumidityMap.indexOf("\n")+1);
        ArrayList<Long> temperatureToHumidity = new ArrayList<>();
        for (long i: lightToTemperature) temperatureToHumidity.add(i);
        lines = temperatureToHumidityMap.split("\n");
        for (String line : lines){
            long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long rangeLength = Long.parseLong(line);
            for(int i = 0; i<temperatureToHumidity.size();i++){
                long humidity = lightToTemperature.get(i);
                if(sourceRangeStart <= humidity && humidity <= sourceRangeStart+rangeLength){
                    temperatureToHumidity.set(temperatureToHumidity.indexOf(humidity),humidity+(destinationRangeStart-sourceRangeStart));
                }
            }
        }

        String humidityToLocationMap = inputRaw.substring(inputRaw.indexOf("humidity-to-location map:"));
        humidityToLocationMap = humidityToLocationMap.substring(humidityToLocationMap.indexOf("\n")+1);
        ArrayList<Long> humidityToLocation = new ArrayList<>();
        for (long i: temperatureToHumidity) humidityToLocation.add(i);
        lines = humidityToLocationMap.split("\n");
        for (String line : lines){
            long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
            line = line.substring(line.indexOf(" ")+1);
            long rangeLength = Long.parseLong(line);
            for(int i = 0; i<humidityToLocation.size();i++){
                long location = temperatureToHumidity.get(i);
                if(sourceRangeStart <= location && location <= sourceRangeStart+rangeLength){
                    humidityToLocation.set(humidityToLocation.indexOf(location),location+(destinationRangeStart-sourceRangeStart));
                }
            }
        }

        

        long lowest = humidityToLocation.get(0);
        for(long i: humidityToLocation){
            if(i < lowest) lowest = i;
        }
        System.out.println();
        System.out.print(lowest);

    }

    public static void partTwo(){
        {
            String inputRaw = loadFile("inputFive");
            inputRaw = inputRaw.replaceAll("\r\n","\n");

            String seedsline = inputRaw.substring(7,inputRaw.indexOf("\n"));
            seedsline += " ;";
            System.out.println(seedsline);
            ArrayList<Long> seedsInitial = new ArrayList<Long>();
            ArrayList<Long> seedsEnd = new ArrayList<Long>();
            ArrayList<Long> seeds = new ArrayList<Long>();
            while (true){
                if(seedsline.charAt(0) == ';')break;
                long initialSeedNumber = Long.parseLong(seedsline.substring(0,seedsline.indexOf(" ")));
                seedsInitial.add(initialSeedNumber);
                seedsline = seedsline.substring(seedsline.indexOf(" ")+1);
                //System.out.println(seedsline);
                long seedsRange = Long.parseLong(seedsline.substring(0,seedsline.indexOf(" ")));
                seedsEnd.add(seedsRange);
                seedsline = seedsline.substring(seedsline.indexOf(" ")+1);
            }


            System.out.println();


            String seedToSoilMap = inputRaw.substring(inputRaw.indexOf("seed-to-soil map:"),inputRaw.indexOf("soil-to-fertilizer map:"));
            seedToSoilMap = seedToSoilMap.substring(seedToSoilMap.indexOf("\n")+1);
            ArrayList<Long> seedToSoilInitial = new ArrayList<>();
            ArrayList<Long> seedToSoilEnd = new ArrayList<>();
            //for (long i: seeds) seedToSoil.add(i);
            String lines[] = seedToSoilMap.split("\n");
            for (String line : lines){
                long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long rangeLength = Long.parseLong(line);
                long sourceEnd = sourceRangeStart + rangeLength;


                for(int i = 0; i<seedsInitial.size();i++){
                    long currentIntialSeed = seedsInitial.get(i);
                    long currenEndSeed = seedsInitial.get(i)+seedsEnd.get(i)-1;
                    if(currentIntialSeed>sourceEnd)continue;
                    if(currenEndSeed<sourceRangeStart)continue;
                    System.out.println("Intital Seed: "+currentIntialSeed + " EndSeed: "+currenEndSeed);
                    System.out.println("Source Range Start: "+sourceRangeStart + " SourceEnd:" + sourceEnd);
                    if(currentIntialSeed>sourceRangeStart)seedToSoilInitial.add(currentIntialSeed);
                    else seedToSoilInitial.add(sourceRangeStart);
                    if(currenEndSeed>sourceEnd) seedToSoilEnd.add(sourceEnd);
                    else seedToSoilEnd.add(currenEndSeed);
                }
            }
            for(long i: seedToSoilInitial) System.out.println(i);


            String soilToFertilizerMap = inputRaw.substring(inputRaw.indexOf("soil-to-fertilizer map:"),inputRaw.indexOf("fertilizer-to-water map:"));
            soilToFertilizerMap = soilToFertilizerMap.substring(soilToFertilizerMap.indexOf("\n")+1);
            ArrayList<Long> soilToFertilizerInitial = new ArrayList<>();
            ArrayList<Long> soilToFertilizerEnd = new ArrayList<>();
            lines = soilToFertilizerMap.split("\n");
            for (String line : lines){
                long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long rangeLength = Long.parseLong(line);
                long sourceEnd = sourceRangeStart + rangeLength;

                for(int i = 0; i<seedToSoilInitial.size();i++){
                    long currentIntial = seedToSoilInitial.get(i);
                    long currenEnd = seedToSoilInitial.get(i)+seedsEnd.get(i);

                    if(currentIntial>sourceEnd)continue;
                    if(currenEnd<sourceRangeStart)continue;

                    if(currentIntial>sourceRangeStart)soilToFertilizerInitial.add(currentIntial);
                    else soilToFertilizerInitial.add(sourceRangeStart);
                    if(currenEnd>sourceEnd) soilToFertilizerEnd.add(sourceEnd);
                    else soilToFertilizerEnd.add(currenEnd);
                }
            }
            for(long i: soilToFertilizerInitial) System.out.println(i);

            String fertilizerToWaterMap = inputRaw.substring(inputRaw.indexOf("fertilizer-to-water map:"),inputRaw.indexOf("water-to-light map:"));
            fertilizerToWaterMap = fertilizerToWaterMap.substring(fertilizerToWaterMap.indexOf("\n")+1);
            ArrayList<Long> fertilizerToWaterInitial = new ArrayList<>();
            ArrayList<Long> fertilizerToWaterEnd = new ArrayList<>();
            lines = fertilizerToWaterMap.split("\n");
            for (String line : lines){
                long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long rangeLength = Long.parseLong(line);
                long sourceEnd = sourceRangeStart + rangeLength;

                for(int i = 0; i<soilToFertilizerInitial.size();i++){
                    long currentIntial = soilToFertilizerInitial.get(i);
                    long currenEnd = soilToFertilizerEnd.get(i)+seedsEnd.get(i);

                    if(currentIntial>sourceEnd)continue;
                    if(currenEnd<sourceRangeStart)continue;

                    if(currentIntial>sourceRangeStart)fertilizerToWaterInitial.add(currentIntial);
                    else fertilizerToWaterInitial.add(sourceRangeStart);
                    if(currenEnd>sourceEnd) fertilizerToWaterEnd.add(sourceEnd);
                    else fertilizerToWaterEnd.add(currenEnd);
                }
            }

            String waterToLightMap = inputRaw.substring(inputRaw.indexOf("water-to-light map:"),inputRaw.indexOf("light-to-temperature map:"));
            waterToLightMap = waterToLightMap.substring(waterToLightMap.indexOf("\n")+1);
            ArrayList<Long> waterToLightInitial = new ArrayList<>();
            ArrayList<Long> waterToLightEnd = new ArrayList<>();
            lines = waterToLightMap.split("\n");
            for (String line : lines){
                long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long rangeLength = Long.parseLong(line);
                long sourceEnd = sourceRangeStart + rangeLength;

                for(int i = 0; i<fertilizerToWaterInitial.size();i++){
                    long currentIntial = fertilizerToWaterInitial.get(i);
                    long currenEnd = fertilizerToWaterEnd.get(i)+seedsEnd.get(i);

                    if(currentIntial>sourceEnd)continue;
                    if(currenEnd<sourceRangeStart)continue;

                    if(currentIntial>sourceRangeStart)waterToLightInitial.add(currentIntial);
                    else waterToLightInitial.add(sourceRangeStart);
                    if(currenEnd>sourceEnd) waterToLightEnd.add(sourceEnd);
                    else waterToLightEnd.add(currenEnd);
                }
            }

            String lightToTemperatureMap = inputRaw.substring(inputRaw.indexOf("light-to-temperature map:"),inputRaw.indexOf("temperature-to-humidity map:"));
            lightToTemperatureMap = lightToTemperatureMap.substring(lightToTemperatureMap.indexOf("\n")+1);
            ArrayList<Long> lightToTemperatureInitial = new ArrayList<>();
            ArrayList<Long> lightToTemperatureEnd = new ArrayList<>();
            lines = lightToTemperatureMap.split("\n");
            for (String line : lines){
                long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long rangeLength = Long.parseLong(line);
                long sourceEnd = sourceRangeStart + rangeLength;

                for(int i = 0; i<waterToLightInitial.size();i++){
                    long currentIntial = waterToLightInitial.get(i);
                    long currenEnd = waterToLightEnd.get(i)+seedsEnd.get(i);

                    if(currentIntial>sourceEnd)continue;
                    if(currenEnd<sourceRangeStart)continue;

                    if(currentIntial>sourceRangeStart)lightToTemperatureInitial.add(currentIntial);
                    else lightToTemperatureInitial.add(sourceRangeStart);
                    if(currenEnd>sourceEnd) lightToTemperatureEnd.add(sourceEnd);
                    else lightToTemperatureEnd.add(currenEnd);
                }
            }



            String temperatureToHumidityMap = inputRaw.substring(inputRaw.indexOf("temperature-to-humidity map:"),inputRaw.indexOf("humidity-to-location map:"));
            temperatureToHumidityMap = temperatureToHumidityMap.substring(temperatureToHumidityMap.indexOf("\n")+1);
            ArrayList<Long> temperatureToHumidityInitial = new ArrayList<>();
            ArrayList<Long> temperatureToHumidityEnd = new ArrayList<>();
            lines = temperatureToHumidityMap.split("\n");
            for (String line : lines){
                long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long rangeLength = Long.parseLong(line);
                long sourceEnd = sourceRangeStart + rangeLength;

                for(int i = 0; i<fertilizerToWaterInitial.size();i++){
                    long currentIntial = lightToTemperatureInitial.get(i);
                    long currenEnd = lightToTemperatureEnd.get(i)+seedsEnd.get(i);

                    if(currentIntial>sourceEnd)continue;
                    if(currenEnd<sourceRangeStart)continue;

                    if(currentIntial>sourceRangeStart)temperatureToHumidityInitial.add(currentIntial);
                    else temperatureToHumidityInitial.add(sourceRangeStart);
                    if(currenEnd>sourceEnd) temperatureToHumidityEnd.add(sourceEnd);
                    else temperatureToHumidityEnd.add(currenEnd);
                }
            }

            String humidityToLocationMap = inputRaw.substring(inputRaw.indexOf("humidity-to-location map:"));
            humidityToLocationMap = humidityToLocationMap.substring(humidityToLocationMap.indexOf("\n")+1);
            ArrayList<Long> humidityToLocationInitial = new ArrayList<>();
            ArrayList<Long> humidityToLocationEnd = new ArrayList<>();
            lines = humidityToLocationMap.split("\n");
            for (String line : lines){
                long destinationRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long sourceRangeStart = Long.parseLong(line.substring(0,line.indexOf(" ")));
                line = line.substring(line.indexOf(" ")+1);
                long rangeLength = Long.parseLong(line);
                long sourceEnd = sourceRangeStart + rangeLength;

                for(int i = 0; i<temperatureToHumidityInitial.size();i++){
                    long currentIntial = temperatureToHumidityInitial.get(i);
                    long currenEnd = temperatureToHumidityEnd.get(i)+seedsEnd.get(i);

                    if(currentIntial>sourceEnd)continue;
                    if(currenEnd<sourceRangeStart)continue;

                    if(currentIntial>sourceRangeStart)humidityToLocationInitial.add(currentIntial);
                    else humidityToLocationInitial.add(sourceRangeStart);
                    if(currenEnd>sourceEnd) humidityToLocationEnd.add(sourceEnd);
                    else humidityToLocationEnd.add(currenEnd);
                }
            }



            long lowest = humidityToLocationInitial.get(0);
            for(long i: humidityToLocationInitial){
                if(i < lowest) lowest = i;
            }
            System.out.println();
            System.out.print(lowest);

        }
    }

    public static String loadFile(String filename) {
        InputStream stream = Day5.Main.class.getResourceAsStream(filename);
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

