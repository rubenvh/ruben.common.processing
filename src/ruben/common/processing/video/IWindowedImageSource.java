package ruben.common.processing.video;

import processing.core.PImage;
import ruben.common.processing.domain.Region;

public interface IWindowedImageSource extends IImageSource {

	PImage get_previous_image(int n);
	
	PImage diff(Region regionOfInterest);
	
	PImage diff();
	
}
