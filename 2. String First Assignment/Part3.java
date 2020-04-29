
/**
 * Finding occurences in strings
 * @author (Prashanna Adhikari) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int index1 = stringb.indexOf(stringa);
        if(index1 == -1){
            return false;
        }
        int count = 0;
        int lengthb = stringb.length();
        for (int i = -1; i < lengthb;){
            index1 = stringb.indexOf(stringa, i+1);
            if(index1 != -1){
                count++;
                i = index1;
            }
            else{
                i = lengthb;
            }
        }
        if (count >= 2){
            return true;
        }
        return false;
    }
    
    public String lastPart(String stringa, String stringb){
        int index = stringb.indexOf(stringa);
        if (index != -1){
            String lastPart = stringb.substring(index + stringa.length(), stringb.length());
            return lastPart;
        }
        return stringb;
    }
    
    public void testing(){
        String stringa = "by";
        String stringb = "A story by Abby Long";
        System.out.println("First string is => " + stringa);
        System.out.println("Second string is => " + stringb);
        System.out.println("Two Occurrences => " +twoOccurrences(stringa, stringb));
        System.out.println("Last part => " + lastPart(stringa, stringb));
        
        stringa = "a";
        stringb = "banana";
        System.out.println("First string is => " + stringa);
        System.out.println("Second string is => " + stringb);
        System.out.println("Two Occurrences => " +twoOccurrences(stringa, stringb));
        System.out.println("Last part => " + lastPart(stringa, stringb));
        
        stringa = "atg";
        stringb = "ctgatgta";
        System.out.println("First string is => " + stringa);
        System.out.println("Second string is => " + stringb);
        System.out.println("Two Occurrences => " +twoOccurrences(stringa, stringb));
        System.out.println("Last part => " + lastPart(stringa, stringb));
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println("First string is => " + stringa);
        System.out.println("Second string is => " + stringb);
        System.out.println("Two Occurrences => " +twoOccurrences(stringa, stringb));
        System.out.println("Last part => " + lastPart(stringa, stringb));
        
    }
}
