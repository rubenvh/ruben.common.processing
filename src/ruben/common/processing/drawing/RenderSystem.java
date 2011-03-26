package ruben.common.processing.drawing;

import ruben.common.drawing.*;
import ruben.common.processing.applet.*;

/**
 * Class RenderSystem
 */
public class RenderSystem implements IRenderSystem {

	//private PApplet _pApplet;
	private RenderVisitor _visitor;
	private IDrawingSystem _drawingSystem;

	public RenderSystem(IDrawingSystem drawingSystem, BasePApplet pApplet) 
	{
		_visitor = new RenderVisitor(pApplet);
		_drawingSystem = drawingSystem;
		//_pApplet = pApplet;

	};

	/* (non-Javadoc)
	 * @see autoperspective.core.IRenderSystem#Render(autoperspective.gobjects.IGraphicObject)
	 */
	public void Render(IGraphicObject gob) 
	{
		gob.Accept(_visitor);		
	}
	
	
	/* (non-Javadoc)
	 * @see autoperspective.core.IRenderSystem#Render()
	 */
	public void Render() 
	{
		Render(_drawingSystem.GetGraphics());		
	}

}
