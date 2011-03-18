package ruben.common.processing.domain;

import ruben.common.datastructures.Location;
import ruben.common.processing.applet.BasePApplet;


public class Region {
	Location _start;
	Location _end;
	
	public Region(){
		this(0, 0, 0, 0);
	}
	
	public Region(int x1, int y1, int x2, int y2){
		_start = new Location(x1, y1);
		_end = new Location(x2, y2);
	}
	public Region(float x1, float y1, float x2, float y2) {
		_start = new Location(Math.round(x1), Math.round(y1));
		_end = new Location(Math.round(x2), Math.round(y2));
	}
	
	
	public Location get_start() { return _start; }
	public Location get_end() { return _end; }
	
	public int width(){
		return Math.abs(_start.get_x() - _end.get_x());
	}
	
	public int height(){
		return Math.abs(_start.get_y() - _end.get_y());
	}
	
	public void draw(BasePApplet pApplet){		
		pApplet.stroke(0, 255, 0);
		pApplet.strokeWeight(1);
		pApplet.noFill();
		int x = this.get_start().get_x();
		int y = this.get_start().get_y();
		pApplet.rect(x, y, this.get_end().get_x()-x, this.get_end().get_y()-y);
	}

}
