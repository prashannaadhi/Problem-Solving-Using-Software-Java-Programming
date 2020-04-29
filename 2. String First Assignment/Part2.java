
/**
 * Different approach to find genes
 * @author (Prashanna Adhikari) 
 * @version (1)
 */
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon){
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1){
            return "";
        }
        int length = stopIndex - startIndex;
        if (length % 3 == 0){
            return dna.substring(startIndex, stopIndex + 3);
        }
        return ("Invalid codon but the gene is " + dna.substring(startIndex, stopIndex + 3));
    }
    
    public void testSimpleGene(){
        String dna;
        dna = "ATCATATAA";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna, "ATG", "TAA"));
        
        dna = "ATCATGCATCGA";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna, "ATG", "TAA"));
        
        dna = "ATCTCGCGAGAT";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna, "ATG", "TAA"));
        
        dna = "ATCTCGATGCGAGATTAA";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna, "ATG", "TAA"));
        
        dna = "ATCTCGATGGCAGATAA";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna, "ATG", "TAA"));
        
        dna = "atgctataa";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna, "atg", "taa"));
        
    }

}
