package ruben.common.processing.video;


import processing.core.PImage;
import ruben.common.processing.applet.BasePApplet;
import ruben.common.processing.utils.FileLoadStrategy;
import ruben.common.state.IParameter;

public class ImageSource extends BaseImageSource implements IImageSource {

	//private String _filename;
	private PImage _image;
	private String _fileName;
	private BasePApplet _applet;
	
	public ImageSource(BasePApplet applet, IParameter<Integer> maxPixels)
	{
		super(new FileLoadStrategy(), maxPixels);
		_applet = applet;
	}

	@Override
	public PImage get_current_image() {
		
		return _image;
	}

	@Override
	public int get_height() {
		return _image.height;
	}

	@Override
	public int get_width() {
		return _image.width;
	}

	@Override
	public boolean has_next() {
		return false;
	}

	@Override
	public String get_source_name() {
		return _fileName;
	}

	@Override
	public boolean is_ready() {
		// TODO Auto-generated method stub
		return (_image != null);
	}

	@Override
	public void load(String source) {
		_fileName = source;
		_image = _applet.loadImage(source);		
	}

	@Override
	public void step() {
		super.step();
		
	}

}
