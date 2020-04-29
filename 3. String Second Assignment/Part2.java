
/**
 * Finding multiple occurences
 * @author (Prashanna Adhikari) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int startIndex = 0;
        int count  = 0;
        while (true){
            startIndex = stringb.indexOf(stringa, startIndex);
            if(startIndex == -1){
                break;
            }
            count++;
            //startIndex = stringb.indexOf(stringa, startIndex) + stringa.length();
            startIndex = startIndex + stringa.length();
        }
        return count;
    }
    
    public void testHowMany(){
        String stringa = "AA";
        String stringb = "ATAAAA";
        System.out.println("First string => " + stringa);
        System.out.println("Second string => " + stringb);
        System.out.println("How many => " + howMany(stringa, stringb));
        
        stringa = "GAA";
        stringb = "ATGAACGAATTGAATC";
        System.out.println("First string => " + stringa);
        System.out.println("Second string => " + stringb);
        System.out.println("How many => " + howMany(stringa, stringb));
    
    }
}
