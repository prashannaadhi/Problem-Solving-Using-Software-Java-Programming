
/**
 * Finding many genes
 * @author (Prashanna Adhikari) 
 * @version (a version number or a date)
 */
public class Part1 {
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
    
    public void testFindStopCodon(){
        //            0123456789           21
        String dna = "xxxyyyzzzTAAzzzyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if(dex !=9 ){
            System.out.println("Error at 9");
        }
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21){
            System.out.println("Error at 21");
        }
        dex = findStopCodon(dna, 21, "TAA");
        if (dex != 26){
            System.out.println("Error at 26");
        }   
        System.out.println("Test finished");
    }
    
    public void testFindGene(){
        //DNA with no ATG
        String dna = "ATCTCGCGATAATCCTGGGAAGTTGTCGTAGCTTAA";
        System.out.println("The dna is => " + dna);
        System.out.println("The valid gene is => " + findGene(dna, 0));
        
        //DNA with ATG and one valid stop codon
        dna = "ATCATGTCGCGATCCTGGGAAGTTGTCGTATAGGCT";
        System.out.println("The dna is => " + dna);
        System.out.println("The valid gene is => " + findGene(dna, 0));
        
        //DNA with ATG and multiple valid stop codon
        dna = "ATCTCGCGATAATCCTGGATGGAAGTTGTCTGAGTAGCTTAA";
        System.out.println("The dna is => " + dna);
        System.out.println("The valid gene is => " + findGene(dna, 0));
        
        //DNA with ATG and no valid stop codon
        dna = "ATCTCGCGATAAATGTCCTGGGAAGTTGTCGTAGCT";
        System.out.println("The dna is => " + dna);
        System.out.println("The valid gene is => " + findGene(dna, 0));   
        
        //DNA with ATG and no valid stop codon
        dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("The dna is => " + dna);
        System.out.println("The valid gene is => " + findGene(dna, 0));
    }
    
    public void testPrineAllGenes(){
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        printAllGenes(dna);
    }
    
    
    
}
