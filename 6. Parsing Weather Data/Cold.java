import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;

/**
 * Parsing weather data to find cold weathers
 * @author (Prashanna Adhikari) 
 * @version (a version number or a date)
 */
public class Cold {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow : parser){
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }
    
    public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar){
        if(coldestSoFar == null){
                coldestSoFar = currentRow;
        }
        else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp != -9999){
                    if(currentTemp < coldestTemp){
                        coldestSoFar = currentRow;
                    }
                }
        }
        return coldestSoFar;
    }
    
    public void testcoldestInDay(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
    }
    
    
    public String fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        String filename = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
            if(Double.parseDouble(coldestSoFar.get("TemperatureF")) ==
                Double.parseDouble(currentRow.get("TemperatureF"))){
                    filename = f.getName();
                }
        }
        return filename;
    }
    
    public void testFileWithColdestTemp(){
        String filename = fileWithColdestTemperature();
        FileResource fr = new FileResource("nc_weather/2013/"+filename);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file named " + filename);
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the temperature on the coldest day were : ");
        for(CSVRecord currentRow : fr.getCSVParser()){
            System.out.println(currentRow.get("DateUTC") + " " + currentRow.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumiditySoFar = null;
        for (CSVRecord currentRow : parser){
            if(lowestHumiditySoFar == null){
                lowestHumiditySoFar = currentRow;
            }
            else{
                String currentHumidity = currentRow.get("Humidity");
                String lowestHumidity = lowestHumiditySoFar.get("Humidity");
                if(lowestHumidity.length() != 3 && currentHumidity.length() != 3){ 
                    int currentHumidityint = Integer.parseInt(currentHumidity);
                    int lowestHumidityint = Integer.parseInt(lowestHumidity);
                    if(currentHumidityint < lowestHumidityint){
                        lowestHumiditySoFar = currentRow;
                    }
                }
            }
        }
        return lowestHumiditySoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if(lowestHumiditySoFar == null){
                lowestHumiditySoFar = currentRow;
            }
            else{
                String currentHumidity = currentRow.get("Humidity");
                String lowestHumidity = lowestHumiditySoFar.get("Humidity");
                if(lowestHumidity.length() != 3 && currentHumidity.length() != 3){ 
                    int currentHumidityint = Integer.parseInt(currentHumidity);
                    int lowestHumidityint = Integer.parseInt(lowestHumidity);
                    if(currentHumidityint < lowestHumidityint){
                        lowestHumiditySoFar = currentRow;
                    }
                }
            }
        }
        return lowestHumiditySoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+lowestHumidity.get("Humidity")+" at "+lowestHumidity.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        int rowCount = 0;
        for(CSVRecord currentRow : parser){
            sum += Double.parseDouble(currentRow.get("TemperatureF"));
            rowCount++;
        }
        double averageTemp = sum/rowCount;
        return averageTemp;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+averageTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sumTemp = 0;
        int rowCount = 0;
        for(CSVRecord currentRow : parser){
            int humidity = Integer.parseInt(currentRow.get("Humidity"));
            if(humidity >= value){
                sumTemp += Double.parseDouble(currentRow.get("TemperatureF"));
                rowCount++;
            }
        }
        double averageTemp = sumTemp/rowCount;
        return averageTemp;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average Temp when high Humidity is "+averageTemp);
    }
    
                
}
