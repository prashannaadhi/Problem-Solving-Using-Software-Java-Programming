import edu.duke.*;
import java.io.*;
/**
 * Inversion of images.
 * 
 * @author (Prashanna Adhikari) 
 * @version (a version number or a date)
 */
public class BatchInversion {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int redPixel = 255 - inPixel.getRed();
            int bluePixel = 255 - inPixel.getBlue();
            int greenPixel = 255 - inPixel.getGreen();
            pixel.setRed(redPixel);
            pixel.setGreen(greenPixel);
            pixel.setBlue(bluePixel);
        }
        return outImage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource invert = makeInversion(inImage);
            String fname = inImage.getFileName();
            String newName = "invert/copy- " + fname;
            invert.setFileName(newName);
            invert.save();
            invert.draw();
        }
    }


}
