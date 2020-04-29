import edu.duke.*;
/**
 * Finding web links
 * @author (Prashanna Adhikari) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void readURL(String url){
        URLResource res = new URLResource(url);
        String doubleQuote = "\"";
        for (String word: res.words()){
            int index = word.toLowerCase().indexOf("youtube.com");
            if (index != -1){
                int firstQuote = word.lastIndexOf(doubleQuote, index);
                int lastQuote = word.indexOf(doubleQuote, firstQuote+1);
                String result = word.substring(firstQuote, lastQuote + 1);
                System.out.println(result);
        }
    }
}
}
