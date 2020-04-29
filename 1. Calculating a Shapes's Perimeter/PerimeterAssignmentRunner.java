/*
 * Calculating a Shape's Perimeter from different data files.
 * 
 * Has following methods
 * 1. getPerimeter() return the perimeter of shape.
 * 2. testPerimeter() tests different methods.
 * 3. triangle() to test the different methods made.
 * 4. printFileNames()
 * 5. main()
 * 6. getNumPoints() that returns an integer that is the number of points in Shape s
 * 7. getAverageLength() returns a number of type double that is the calculated average of all the sides’ lengths in the Shape S.
 * 8. getLargestSide()  returns a number of type double that is the longest side in the Shape S.
 * 9. getLargestX() returns a number of type double that is the largest x value over all the points in the Shape s.
 * 10.getLargestPerimeterMultipleFiles() calculates the shapes perimeter in multiple files.
 * 11.testPerimeterMultipleFiles() to test getLargestPerimeterMultipleFiles()
 * 12.getFileWithLargestPerimeter() returns the file that has the largest perimeter.
 * 13.testFileWithLargestPerimeter() to call getFileWithLargestPerimeter() 
*/

import edu.duke.*;
import java.io.File;


public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for (Point countPt : s.getPoints()){
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double average = 0.0;
        double perimeter = getPerimeter(s);
        int count = getNumPoints(s);
        average = perimeter/count;
        return average;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double currDist = 0.0;
        double largDist = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()){
            currDist = prevPt.distance(currPt);
            if(currDist>=largDist){
                largDist = currDist;
            }
            prevPt = currPt;
        }
        return largDist;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double x = 0.0;
        for(Point currPt : s.getPoints()){
            double i = currPt.getX();
            if(i >= x){
                x = i;
            }
        }
        return x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largePerim = 0.0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (largePerim <= length){
                largePerim = length;
            }
        }
        return largePerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double largePerim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (largePerim <= length){
                largePerim = length;
                temp = f;
            }
    }
    return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        int count = getNumPoints(s);
        double length = getPerimeter(s);
        double average = getAverageLength(s);
        double largDist = getLargestSide(s);
        double largX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + count);
        System.out.println("average of all sides = " + average);
        System.out.println("Largest side = " + largDist);
        System.out.println("Largest X = " + largX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter of all " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String filename = getFileWithLargestPerimeter();
        System.out.println("The file with the largest perimeter is " + filename);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
        //pr.printFileNames();
    }
}
