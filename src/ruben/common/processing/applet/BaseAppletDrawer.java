package ruben.common.processing.applet;

public abstract class BaseAppletDrawer implements IAppletDrawer {

	protected boolean _is_active = true;
	
	public boolean is_active() { return _is_active; }	
	public void set_active_state(boolean active) { _is_active = active; }
	
	public BaseAppletDrawer()
	{
	}
	
	
	
	
	
}
