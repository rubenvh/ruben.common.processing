package ruben.common.processing.video;

import processing.core.PImage;
import ruben.common.processing.utils.ILoadStrategy;
import ruben.common.state.IParameter;

public abstract class BaseImageSource implements IImageSource {

	protected ILoadStrategy _strat;
	PImage _smallImage = null;
	IParameter<Integer> _maxPixels;
	
	public BaseImageSource(ILoadStrategy strat, IParameter<Integer> max_pixels) {
		_strat = strat;
		_maxPixels = max_pixels;
	}
	
	@Override
	public void load() {		
		
		String file = _strat.get_source();
		if (file != "") { 
			load(file);
			updateSmallImage();
		}
	}
	
	public PImage get_current_small_image() {
		if (_smallImage == null) updateSmallImage();
		return _smallImage;
	}
	
	public void step() {
		update();
	}
	
	public void update() {
		updateSmallImage();
	}
	
	private void updateSmallImage() {
		PImage i = get_current_image();
		
		_smallImage = i.get();
		
		if (_smallImage.width > _maxPixels.get_value())
			_smallImage.resize(_maxPixels.get_value(), 0);
		if (_smallImage.height > _maxPixels.get_value())
			_smallImage.resize(0, _maxPixels.get_value());
	
	}
}
