package ruben.common.processing.utils;

import processing.core.PImage;
import ruben.common.processing.applet.BasePApplet;

public class Pixelizer {
	
	private int _wbins;
	private int _hbins;
	private int _xbins;
	private int _ybins;
	private int _r;
	private int _g;
	private int _b;
	private int _x;
	private int _y;
	private int _index;
	
	private BasePApplet _applet;
	
	public Pixelizer(BasePApplet applet)
	{
		_applet = applet;
	}
	
	public PImage pixelize(int pixels, PImage i, PImage target)
	{
		_wbins = Math.max(1, i.width / pixels);
		_hbins = Math.max(1, i.height / pixels);
		_xbins = i.width/_wbins;
		_ybins = i.height/_hbins;
		
		i.loadPixels();
		target.loadPixels();
		
		for (int xbin = 0; xbin < _xbins; xbin++) {
			for (int ybin = 0; ybin < _ybins; ybin++) {
				
				_r = 0;
				_g = 0;
				_b = 0;
				
				//calculate average color
				for (int offsetx = 0; offsetx < _wbins; offsetx++ ) {
					for (int offsety = 0; offsety < _hbins; offsety++) {
						
						_x = xbin*_wbins+offsetx;
						_y = ybin*_hbins+offsety;
												
						_index = _y*i.width+_x;
						
						if (_index >= i.pixels.length) continue;
						
				    	_r += _applet.red(i.pixels[_index]);
						_g += _applet.green(i.pixels[_index]);
						_b += _applet.blue(i.pixels[_index]);
						
					}
				}
				
				int avgColor = _applet.color(_r/(_wbins*_hbins),_g/(_wbins*_hbins),_b/(_wbins*_hbins)); 
			
				
				// set to average color
				for (int offsetx = 0; offsetx < _wbins; offsetx++ ) {
					for (int offsety = 0; offsety < _hbins; offsety++) {
						
						_x = xbin*_wbins+offsetx;
						_y = ybin*_hbins+offsety;
						
						_index = _y*target.width+_x;
						
						if (_index >= target.pixels.length) continue;
						target.pixels[_index] = avgColor;
						
						
					}
				}
				
				target.updatePixels();
				
			}
		}
		
		return target;		
	}

}
