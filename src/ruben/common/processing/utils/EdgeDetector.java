package ruben.common.processing.utils;

import processing.core.PConstants;
import processing.core.PImage;
import ruben.common.processing.applet.BasePApplet;

public class EdgeDetector
{
	private BasePApplet _applet;
	private float[][] kernel = { { -1, -1, -1 },
            { -1,  9, -1 },
            { -1, -1, -1 } };

	public EdgeDetector(BasePApplet applet) {
		_applet = applet;
	}
	
	public PImage detect_edges(PImage img) {
		PImage edgeImg = _applet.createImage(img.width, img.height, PConstants.RGB);
		// Loop through every pixel in the image.
		for (int y = 1; y < img.height-1; y++) { // Skip top and bottom edges
		  for (int x = 1; x < img.width-1; x++) { // Skip left and right edges
		    float sum = 0; // Kernel sum for this pixel
		    for (int ky = -1; ky <= 1; ky++) {
		      for (int kx = -1; kx <= 1; kx++) {
		        // Calculate the adjacent pixel for this kernel point
		        int pos = (y + ky)*img.width + (x + kx);
		        // Image is grey-scale, red/green/blue are identical
		        float val = _applet.red(img.pixels[pos]);
		        // Multiply adjacent pixels based on the kernel values
		        sum += kernel[ky+1][kx+1] * val;
		      }
		    }
		    // For this pixel in the new image, set the gray value
		    // based on the sum from the kernel
		    edgeImg.pixels[y*img.width + x] = _applet.color(sum);
		  }
		}
		// State that there are changes to edgeImg.pixels[]
		edgeImg.updatePixels();
		return edgeImg; // Draw the new image
	}
}
