package ruben.common.processing.applet;

public interface IAppletDrawer {
	
	boolean is_active();
	
	void set_active_state(boolean active);
	
	void draw();

	void mouseReleased();
	
	void mousePressed();

	void keyPressed();

	void keyReleased();
	
	void cleanup();
	
}
