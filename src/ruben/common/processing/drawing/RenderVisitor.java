package ruben.common.processing.drawing;

import ruben.common.drawing.*;
import ruben.common.processing.applet.*;

/**
 * Class RenderVisitor
 */
public class RenderVisitor extends  GraphicObjectVisitor {

	//
	// Fields
	//

	private BasePApplet pApplet;
	private boolean _overrideColor = true;

	//
	// Constructors
	//
	public RenderVisitor(BasePApplet pApplet) 
	{
		this.pApplet = pApplet;
	}

	
	/**
	 * @param g
	 */
	public void Visit(Point g) 
	{
		pApplet.stroke(g.GetColor());
		pApplet.strokeWeight(g.GetLineSize());
		pApplet.point(g.getX(), g.getY());
	}

	/**
	 * @param g
	 */
	public void Visit(Line g) 
	{
		if (_overrideColor)
		{
			pApplet.stroke(g.GetColor());
			pApplet.strokeWeight(g.GetLineSize());
		}
		pApplet.line(g.getP1().getX(), g.getP1().getY(), g.getP2().getX(), g
				.getP2().getY());
	}

	

	/**
	 * @param g
	 */
	public void Visit(Polygon g) 
	{
		pApplet.strokeWeight(4);
		if (g.GetSelected()) pApplet.stroke(pApplet.color(0,0,255));
		_overrideColor = !g.GetSelected();
		super.Visit(g);
		pApplet.strokeWeight(1);
		_overrideColor = true;
	}


	@Override
	public void Visit(Text g) 
	{
		pApplet.fill(g.GetColor());
		pApplet.strokeWeight(g.GetLineSize());
		pApplet.text(g.getText(), g.getX(), g.getY());		
	}
	

}
