import edu.duke.*;
import java.io.*;
/**
 * GrayScaling images.
 * 
 * @author (Prashanna Adhikari) 
 * @version (a version number or a date)
 */
public class GrayScale {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    
    public void testmakeGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        String fname = ir.getFileName();
        String newName = "copy- " + fname;
        gray.setFileName(newName);
        gray.save();
        gray.draw();
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            String fname = inImage.getFileName();
            String newName = "gray/copy- " + fname;
            gray.setFileName(newName);
            gray.save();
            gray.draw();
        }
    }

}
