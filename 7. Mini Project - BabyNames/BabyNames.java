import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Mini project to find Baby names.
 * 
 * @author (Prashanna Adhikari) 
 * @version (a version number or a date)
 */
public class BabyNames {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord rec: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 100){
            System.out.println("Name " + rec.get(0) +
                               " Gender " + rec.get(1)+
                               " Num Born " + rec.get(2));
            }
         }
    }
    
    public void totalBirths(FileResource fr){
        int totalBorn = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int totalBoysNames = 0;
        int totalGirlsNames = 0;
        for(CSVRecord rec: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBorn += numBorn;
            if(rec.get(1).equals("M")){
                totalBoys += numBorn;
                totalBoysNames++;
            }
            else{
                totalGirls += numBorn;
                totalGirlsNames++;
            }
            totalNames++;
        }
        System.out.println("total births = " + totalBorn);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total names = " + totalNames);
        System.out.println("total boys names = " + totalBoysNames);
        System.out.println("total girls names = " + totalGirlsNames);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        String filename = "us_babynames/us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        int rank = 0;
        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testgetRank(){
        System.out.println(getRank(1971,"Frank", "M"));
    }
    
    public String getName(int year, int rank, String gender){
        String filename = "us_babynames/us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        int countRank = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                countRank++;
                if(countRank == rank){
                    String name = rec.get(0);
                    return name;
                }
            }
        }
        return "NO NAME";          
    }
    
    public void testgetName(){
        System.out.println(getName(1982,450, "M"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name+" born in  "+year+" would be "+newName+" if she was born in "+newYear);
    }
    
    public void testwhatIsNameInYear(){
        whatIsNameInYear("Owen",1974,2014,"M");
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int year = 0;
        int highestRank = 1;
        int rank = 0;
        for(File f : dr.selectedFiles()){
            int currRank = 0;
            FileResource fr = new FileResource(f);
            String filename = f.getName();
            int currYear = Integer.parseInt(filename.substring(3,7));
            for(CSVRecord rec: fr.getCSVParser(false)){
                if(rec.get(1).equals(gender)){
                    currRank++;
                    if(rec.get(0).equals(name)){
                        rank = currRank;
                        break;
                    }
                }
            if(rank <= highestRank){
                highestRank = rank;
                year = currYear;
            }
            }
        }
              
        if(year != 0){
            return  year;
        }
        return -1;
    }
    
    public void testyearOfHighestRank(){
        System.out.println(yearOfHighestRank("Genevieve", "F"));
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int sum = 0;
        int filecounts = 0;
        for(File f : dr.selectedFiles()){
            int currRank = 0;
            FileResource fr = new FileResource(f);
            for(CSVRecord rec: fr.getCSVParser(false)){
                if(rec.get(1).equals(gender)){
                    currRank++;
                    if(rec.get(0).equals(name)){
                        sum += currRank;
                        break;
                    }
                }
            }
            filecounts++;
        }
        if(sum ==0){
            return -1;
        }
        double averageRank = (double)sum/filecounts;
        return averageRank;
    }
    
    public void testgetAverageRank(){
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String filename = "us_babynames/us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        int totalBirths = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                int currBirth = Integer.parseInt(rec.get(2));
                totalBirths += currBirth;
                if(rec.get(0).equals(name)){
                    return totalBirths-currBirth;
                }
            }
        }
        return 0;
    }
    
    public void testgetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
}
