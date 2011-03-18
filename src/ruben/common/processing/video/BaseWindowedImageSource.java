package ruben.common.processing.video;


import processing.core.PImage;
import ruben.common.datastructures.IWindowedStack;
import ruben.common.processing.utils.ILoadStrategy;
import ruben.common.state.IParameter;

public abstract class BaseWindowedImageSource extends BaseImageSource implements IWindowedImageSource {

	
	protected IWindowedStack<PImage> window;
	
	public BaseWindowedImageSource(IWindowedStack<PImage> w, ILoadStrategy strat, IParameter<Integer> maxPixels)
	{
		super(strat, maxPixels);
		window = w;
	}
	
	public PImage get_current_image() {
		
		return window.current();
	}

	public PImage get_previous_image(int n) {
		
		return window.get(n);
	}
	
	public boolean has_next() {
		return true;
	}


}
