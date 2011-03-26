package ruben.common.processing.drawing;

import processing.core.PConstants;
import ruben.common.drawing.*;
import ruben.common.processing.applet.*;
import ruben.common.repository.*;

public class MouseDrawingAppletDrawer extends BaseDrawingAppletDrawer
{
	private BasePApplet _applet;
	private IDrawingSystem _drawingSystem;
	private boolean _newLine = true;

	public MouseDrawingAppletDrawer(BasePApplet applet,
			IDrawingSystem drawingSystem, ITargetRepository targetRepo)
	{
		super(targetRepo);
		_applet = applet;
		_drawingSystem = drawingSystem;
	}

	public void draw()
	{
		Point target = get_target();

		// trigger undo
		if (_applet.mousePressed && _applet.mouseButton == PConstants.RIGHT)
			undoAction(target);

		// trigger drawing action
		if (_applet.mousePressed && (_applet.mouseButton == PConstants.LEFT))
			drawAction(target);

		if (IsDrawing())
		{
			_drawingSystem.DrawingAid(target);
		}
	}

	public void keyPressed()
	{
	}

	public void keyReleased()
	{
	}

	public void mousePressed()
	{
	}

	public void mouseReleased()
	{
		_newLine = true;
	}

	private void undoAction(Point target)
	{

		_drawingSystem.Wipe(target);
	}

	private boolean IsDrawing()
	{

		return (_applet.keyPressed && _applet.keyCode == PConstants.SHIFT);
	}

	private void drawAction(Point target)
	{
		target.SetColor(255).SetLineSize(3);
		
		if (IsDrawing())
		{
			target = _drawingSystem.AddLine(target);
		}
		else
		{
			if (_newLine)
			{
				_drawingSystem.AddPoint(target);
				_newLine = false;
			}
			else
			{
				target = _drawingSystem.AddLine(target);
			}
		}
	}

}
