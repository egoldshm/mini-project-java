/**
 * 
 */
package UnitTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.imageio.ImageIO;

import java.nio.file.Path;
import org.junit.Test;

import renderer.ImageWriter;
import sun.misc.FpUtils;

/**
 * @author ifriedma
 *
 */
public class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#writeToimage()}.
	 */
	@Test
	public void testWriteToimage() {
		ImageWriter imageWriter = new ImageWriter("tests/testWriteToimageFile",150,250,400,300);
		imageWriter.writeToimage();
		File f = new File(System.getProperty("user.dir") + "/" + "tests/testWriteToimageFile" + ".jpg");
		assertEquals(f.exists(),true);

	}

	/**
	 * Test method for {@link renderer.ImageWriter#writePixel(int, int, int, int, int)}.
	 */
	@Test
	public void testWritePixelIntIntIntIntInt() {
		
		ImageWriter imageWriter = new ImageWriter("tests/testWritePixelIntIntIntIntIntFile",1000,1000,200,200);
		for(int i = 0;i<500;i++)
			for(int j = 0;j<500;j++)
				imageWriter.writePixel(i, j, 128, 255, 0);
		imageWriter.writeToimage();
		File f1 = new File(System.getProperty("user.dir") + "/" + "tests/testWritePixelIntIntIntIntIntFile" + ".jpg");
		File f2 = new File(System.getProperty("user.dir") + "/" + "tests/testWritePixelIntIntColorFile" + ".jpg");
		try {
			assertEquals(sameContent(f1,f2),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	boolean sameContent(File fileA, File fileB) throws IOException {
	    try {
	        // take buffer data from botm image files //
	        BufferedImage biA = ImageIO.read(fileA);
	        DataBuffer dbA = biA.getData().getDataBuffer();
	        int sizeA = dbA.getSize();                      
	        BufferedImage biB = ImageIO.read(fileB);
	        DataBuffer dbB = biB.getData().getDataBuffer();
	        int sizeB = dbB.getSize();
	        // compare data-buffer objects //
	        if(sizeA == sizeB) {
	            for(int i=0; i<sizeA; i++) { 
	                if(dbA.getElem(i) != dbB.getElem(i)) {
	                    return false;
	                }
	            }
	            return true;
	        }
	        else {
	            return false;
	        }
	    } 
	    catch (Exception e) { 
	        System.out.println("Failed to compare image files ...");
	        return  false;
	    }

	}


	/**
	 * Test method for {@link renderer.ImageWriter#writePixel(int, int, int[])}.
	 */
	@Test
	public void testWritePixelIntIntIntArray() {
		int[] array = new int[3];
		array[0] =128;
		array[1] = 255;
		array[2] = 0;
		ImageWriter imageWriter = new ImageWriter("tests/testWritePixelIntIntIntArrayFile",1000,1000,200,200);
		for(int i = 0;i<500;i++)
			for(int j = 0;j<500;j++)
				imageWriter.writePixel(i, j,array);
		imageWriter.writeToimage();
		File f1 = new File(System.getProperty("user.dir") + "/" + "tests/testWritePixelIntIntIntArrayFile" + ".jpg");
		File f2 = new File(System.getProperty("user.dir") + "/" + "tests/testWritePixelIntIntIntIntIntFile" + ".jpg");
		try {
			assertEquals(sameContent(f1,f2),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

	/**
	 * Test method for {@link renderer.ImageWriter#writePixel(int, int, java.awt.Color)}.
	 */
	@Test
	public void testWritePixelIntIntColor() {
		Color c = new Color(128,255,0);
		ImageWriter imageWriter = new ImageWriter("tests/testWritePixelIntIntColorFile",1000,1000,200,200);
		for(int i = 0;i<500;i++)
			for(int j = 0;j<500;j++)
				imageWriter.writePixel(i, j,c);
		imageWriter.writeToimage();
		File f1 = new File(System.getProperty("user.dir") + "/" + "tests/testWritePixelIntIntColorFile" + ".jpg");
		File f2 = new File(System.getProperty("user.dir") + "/" + "tests/testWritePixelIntIntIntIntIntFile" + ".jpg");
		try {
			assertEquals(sameContent(f1,f2),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
