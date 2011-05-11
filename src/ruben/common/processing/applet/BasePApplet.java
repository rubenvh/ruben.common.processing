package ruben.common.processing.applet;

import java.util.Iterator;
import java.util.Vector;

import processing.core.PApplet;

@SuppressWarnings("serial")
public abstract class BasePApplet extends PApplet implements IPApplet {

	protected Vector<IAppletDrawer>  _drawers;
	
	@Override
	public PApplet get_papplet() {
		return this;
	}
	
		
	public void setup() {
		
		load_applet_drawers();
	
	}
	
	public void draw() {
		Iterator<IAppletDrawer> it = _drawers.iterator();
		while (it.hasNext())
		{
			IAppletDrawer drawer = it.next();
			
			if (drawer.is_active()) drawer.draw();
		}
	}
	

	public void mousePressed() {
		Iterator<IAppletDrawer> it = _drawers.iterator();
		while (it.hasNext())
		{
			IAppletDrawer drawer = it.next();
			
			if (drawer.is_active()) drawer.mousePressed();
		}
	}
	
	public void mouseReleased() {
		Iterator<IAppletDrawer> it = _drawers.iterator();
		while (it.hasNext())
		{
			IAppletDrawer drawer = it.next();
			
			if (drawer.is_active()) drawer.mouseReleased();
		}
	}

	public void keyPressed() {
		Iterator<IAppletDrawer> it = _drawers.iterator();
		while (it.hasNext())
		{
			IAppletDrawer drawer = it.next();
			
			if (drawer.is_active()) drawer.keyPressed();
		}
	}

	public void keyReleased() {
		Iterator<IAppletDrawer> it = _drawers.iterator();
		while (it.hasNext())
		{
			IAppletDrawer drawer = it.next();
			
			if (drawer.is_active()) drawer.keyReleased();
		}
	}
	
	public void stop() {
		
		Iterator<IAppletDrawer> it = _drawers.iterator();
		while (it.hasNext())
		{
			IAppletDrawer drawer = it.next();
			
			if (drawer.is_active()) drawer.cleanup();
		}
		
		super.stop();
	}

	
	protected abstract void load_applet_drawers();
	
}
