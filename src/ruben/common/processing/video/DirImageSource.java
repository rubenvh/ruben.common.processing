package ruben.common.processing.video;

import java.io.File;
import java.io.FilenameFilter;

import processing.core.PApplet;
import processing.core.PImage;
import ruben.common.processing.applet.BasePApplet;
import ruben.common.processing.utils.DirLoadStrategy;
import ruben.common.state.IParameter;

public class DirImageSource extends BaseImageSource {

	private BasePApplet _applet;
	private String _path;
	private PImage _curImage;
	private String _curFileName;
	private File[] _images;
	
	public DirImageSource(BasePApplet applet, IParameter<Integer> maxPixels)
	{
		super(new DirLoadStrategy(), maxPixels);
		_applet = applet;
	}
	
	@Override
	public PImage get_current_image() {
		
		return _curImage;
	}

	@Override
	public int get_height() {
		return get_current_image().height;
	}

	@Override
	public String get_source_name() {
		
		return _curFileName;
	}

	@Override
	public int get_width() {
		return get_current_image().width;
	}

	@Override
	public boolean has_next() {
		
		return false;
	}

	@Override
	public boolean is_ready() {
		
		return _curImage != null;
	}

	@Override
	public void load(String source) {
		_path = source;
	
		File dir = new File(_path);
		
		if (dir.isDirectory()) {
			// TODO load images from directory in list
			_images = dir.listFiles(new FilenameFilter(){
				
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".tif");
				}
			});
			
			PApplet.println(String.format("Images found in directory: %d", _images.length));
			
			step();
		}
	}
	
	private int index = 0;

	@Override
	public void step() {

		if (index >= _images.length) index = 0;
		
		if (index < _images.length) {
			File file = _images[index];
			_curFileName = file.getAbsolutePath();
			_curImage = _applet.loadImage(_curFileName);
			index++;
		}
		
		super.step();
		
	}

	

}
