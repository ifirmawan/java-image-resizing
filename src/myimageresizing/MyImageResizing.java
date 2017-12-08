/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myimageresizing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Limbangan Kulon
 */
public class MyImageResizing {
    private static final int IMG_WIDTH = 200;
    private static final int IMG_HEIGHT = 200;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String dataset_1 = "\\001";
        new MyImageResizing().getFiles(dataset_1);
    }
    public void getFiles(String dataset){
        String path         = String.valueOf(System.getProperty("user.dir")).toString();
        String originalPath = path +"\\dataset\\original\\";
        String hasilPath    = path +"\\dataset\\hasil\\";
        String filesPath     = originalPath+dataset;
        File folder = new File(filesPath);
        File[] listOfFiles = folder.listFiles();
           
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName()+" ");//+listOfFiles[i].getPath()
                
                String imagePath = String.valueOf(listOfFiles[i].getPath()).toString();
                String resultPath = hasilPath+dataset;
                try{
                    BufferedImage originalImage = ImageIO.read(new File(imagePath));
                    int imageType = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                    
                    
                    BufferedImage resizeImageJpg = resizeImage(originalImage, imageType);
                    resultPath +="\\" + listOfFiles[i].getName();
                    ImageIO.write(resizeImageJpg, "png", new File(resultPath));

                }catch(IOException e){
                    System.out.println(e.getMessage());
                }
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
                path +="\\"+listOfFiles[i].getName();
                System.out.println(path);
                //this.getFiles(path);
            }
        }
    }
    
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();

	return resizedImage;
    }
}
