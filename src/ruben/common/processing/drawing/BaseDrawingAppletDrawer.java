package ruben.common.processing.drawing;

import ruben.common.drawing.Point;
import ruben.common.processing.applet.BaseAppletDrawer;
import ruben.common.repository.ITargetRepository;

public abstract class BaseDrawingAppletDrawer extends BaseAppletDrawer
{
	
	private ITargetRepository _targetRepo;
	
	public BaseDrawingAppletDrawer(ITargetRepository targetRepo) 
	{
		_targetRepo = targetRepo;		
	}
	
	protected Point get_target(){
		return _targetRepo.get_target();
	}

	

	

}
