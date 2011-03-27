package ruben.common.processing.video;

import processing.core.PImage;
import ruben.common.datastructures.IWindowedStack;
import ruben.common.datastructures.WindowedStack;
import ruben.common.processing.applet.IPApplet;
import ruben.common.processing.domain.Region;
import ruben.common.processing.utils.FileLoadStrategy;
import ruben.common.processing.utils.ILoadStrategy;
import ruben.common.processing.utils.NullLoadStrategy;
import ruben.common.state.IParameter;
import hypermedia.video.OpenCV;

public class OpenCVVideoSource extends BaseWindowedImageSource {
	
	public static OpenCVVideoSource Create(IPApplet applet, int windowSize, int width, int height, int cameraNumber, IParameter<Integer> maxPixels)
	{
		IWindowedStack<PImage> window = new WindowedStack<PImage>(windowSize);
		
		return new OpenCVVideoSource(applet, window, width, height, cameraNumber, maxPixels, new NullLoadStrategy());		
	}
	
	public static OpenCVVideoSource Create(IPApplet applet, int windowSize, int width, int height, int cameraNumber, IParameter<Integer> maxPixels, ILoadStrategy strat)
	{
		IWindowedStack<PImage> window = new WindowedStack<PImage>(windowSize);
		
		return new OpenCVVideoSource(applet, window, width, height, cameraNumber, maxPixels, strat);		
	}
	
	public static OpenCVVideoSource Create(IPApplet applet, int windowSize, int width, int height, String movieFile, IParameter<Integer> maxPixels)
	{
		IWindowedStack<PImage> window = new WindowedStack<PImage>(windowSize);
		
		return new OpenCVVideoSource(applet, window, width, height, movieFile, maxPixels);		
	}
		
	
	
	private IPApplet _applet;
	public OpenCV cv = null;	// OpenCV object
	
	public OpenCVVideoSource(IPApplet applet, IWindowedStack<PImage> w, int width, int height, int camera_number,IParameter<Integer> maxPixels) {
		this(applet, w, width, height, camera_number, maxPixels, new NullLoadStrategy());
	}
	
	public OpenCVVideoSource(IPApplet applet, IWindowedStack<PImage> w, int width, int height, int camera_number,IParameter<Integer> maxPixels, ILoadStrategy strat) {
		super(w, strat, maxPixels);
	
		_applet = applet;
		// OpenCV setup
		cv = new OpenCV( applet.get_papplet() );
		
		cv.capture( width, height, camera_number);
	}
	
	public OpenCVVideoSource(IPApplet applet, IWindowedStack<PImage> w, int width, int height, String filename, IParameter<Integer> maxPixels) {
		super(w, new FileLoadStrategy(), maxPixels);
		
		// OpenCV setup
		cv = new OpenCV( applet.get_papplet() );
		
		cv.movie( filename, width, height );    // load movie file
	}

	public void step() {

		cv.remember();
		cv.read();
		window.push(cv.image());
		
		super.step();

	}
	
	public PImage diff() 
	{
		cv.absDiff();   
		return cv.image();
	}
	public PImage diff(Region regionOfInterest)
	{
		int x1 = regionOfInterest.get_start().get_x();
		int y1 = regionOfInterest.get_start().get_y();
		int x2 = regionOfInterest.get_end().get_x();
		int y2 = regionOfInterest.get_end().get_y();
		
		cv.ROI(x1, y1, x2, y2);
		return diff();
	}

	public int get_height() {
		return cv.height;
	}

	public int get_width() {
		return cv.width;
	}

	@Override
	public String get_source_name() {
		
		return cv.toString();
	}

	@Override
	public boolean is_ready() {
		// TODO Auto-generated method stub
		return get_current_image() != null;
	}

	@Override
	public void load(String source) {
		
		int h = get_height();
		int w = get_width();
		
		try
		{
			cv.dispose();
			// OpenCV setup
			cv = new OpenCV( _applet.get_papplet() );
			
			cv.capture( w, h, Integer.parseInt(source));
		}
		catch (NumberFormatException e) {
			cv.movie(source, w, h);
		}
		
		//step();
	}

	
	
}
