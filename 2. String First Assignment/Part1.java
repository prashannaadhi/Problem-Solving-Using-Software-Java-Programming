/*
 * Finding gene from DNA string
 */
public class Part1 {
    public String findSimpleGene (String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
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
        System.out.println("The gene is "+ findSimpleGene(dna));
        
        dna = "ATCATGCATCGA";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna));
        
        dna = "ATCTCGCGAGAT";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna));
        
        dna = "ATCTCGATGCGAGATTAA";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna));
        
        dna = "ATCTCGATGGCAGATAA";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna));
        
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("The dna string is " + dna);
        System.out.println("The gene is "+ findSimpleGene(dna));
    
    }
}
