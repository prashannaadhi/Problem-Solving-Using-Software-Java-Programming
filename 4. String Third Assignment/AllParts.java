import edu.duke.*;
/**
 * Using storage resource
 * @author (Prashanna Adhikari) 
 * @version (a version number or a date)
 */
public class AllParts {
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
    
    public StorageResource getAllGenes(String dna){
        StorageResource res = new StorageResource();
        int startIndex = 0;
        while (true){
            String currGene = findGene(dna, startIndex);
            if (currGene == null){
                break;
            }
            res.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return res;
    }
    
    public float cgRatio(String dna){
        int countC = 0;
        int countG = 0;
        int startIndexC = 0;
        int startIndexG = 0;
        while(true){
            startIndexC = dna.indexOf("C", startIndexC);
            if(startIndexC != -1){
                countC++;
                startIndexC++;
            }
           startIndexG = dna.indexOf("G", startIndexG);
            if(startIndexG != -1){
                countG++;
                startIndexG++;
            }
            if(startIndexC == -1 && startIndexG == -1){
                break;
            }
        }
        float ratio = (float)(countC + countG)/ dna.length();
        return ratio;
    }
    
    public int countCTG(String dna){
        int startIndex = 0;
        int countCTG = 0;
        while(true){
            startIndex = dna.indexOf("CTG", startIndex);
            System.out.println("StartIndex => " + startIndex);
            if (startIndex != -1){
                countCTG++;
                System.out.println("Count => "+ countCTG);
                startIndex = startIndex + 3;
            }
            else{
                break;
            }
        }
        return countCTG;
    }
    
    public void processGenes(StorageResource sr){
        int count9 = 0;
        int countCG = 0;
        int countGenes = 0;
        int length = 0;
        int startIndex = 0;
        
        for(String item : sr.data()){
            while(true){
                String currGene = findGene(item, startIndex);
                if(currGene == null){
                    break;
                }
                countGenes++;
                if(currGene.length()>60){
                    System.out.println("Gene with length greater than 9");
                    System.out.println(currGene);
                    count9++;
                }
                if(cgRatio(currGene) > 0.35){
                    System.out.println("Gene with CG ratio greater than 0.35");
                    System.out.println(currGene);
                    countCG++;
                }
                if(currGene.length() >= length){
                    length = currGene.length();
                }
                startIndex = item.indexOf(currGene, startIndex) + currGene.length();
            }
        }
        System.out.println("Number of genes greater than 60 characters => " + count9);
        System.out.println("Number of genes with CG ratio higher than 0.35 => " + countCG);
        System.out.println("The length of the longest gene is => " + length);
        System.out.println("Total number of genes => " + countGenes);
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
    
    public void testGetAllGenes(){
        StorageResource res= getAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        for(String item : res.data()){
            System.out.println(item);
        }
    }
    public void testcgRatio(){
        System.out.println("Ratio => " + cgRatio("ATGCCATAG"));
    }
    public void testCountCTG(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        System.out.println("Count CTG => " + countCTG(dna));
    }
    public void testProcessGenes(){
        StorageResource sr = new StorageResource();
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        sr.add(dna);
        //fr = new FileResource("brca1.fa");
        //dna = fr.asString();
        //sr.add(dna);
        //sr.add("ATCTCGCGAATGATCTCGTAATACACGCGTATGTACGACGTATGA");
        processGenes(sr);
        sr.clear();
    }
}
