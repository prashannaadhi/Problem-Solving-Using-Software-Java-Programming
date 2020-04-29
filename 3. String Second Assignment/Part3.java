
/**
 * Counting strands of genes
 * @author (Prashanna Adhikari) 
 * @version (a version number or a date)
 */
public class Part3 {
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1){
            return null;
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex != dna.length()){
            String result = dna.substring(startIndex, minIndex + 3);
            return result;
        }
        return null;
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while (true){
            String currGene = findGene(dna, startIndex);
            if (currGene == null){
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(stopIndex != -1){
            if((stopIndex - startIndex) % 3 == 0){
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
            }
        }
        return dna.length();
    }
    
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while (true){
            String currGene = findGene(dna, startIndex);
            if (currGene == null){
                break;
            }
            count++;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return count;
    }
    
    public void testCountGenes(){
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println("The dna is => " + dna);
        System.out.println("The gene is => " + countGenes(dna));
    }
}
