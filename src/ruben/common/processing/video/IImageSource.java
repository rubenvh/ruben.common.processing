package ruben.common.processing.video;

import processing.core.PImage;

public interface IImageSource {
	
	String get_source_name();
	boolean is_ready();
	boolean has_next();
	void load(String source);
	void load();
	PImage get_current_image();	
	void step();
	void update();
	
	int get_width();
	int get_height();
	public PImage get_current_small_image();

}


