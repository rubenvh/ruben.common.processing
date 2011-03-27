package ruben.common.processing.drawing;


import ruben.common.drawing.GraphicComposite;
import ruben.common.drawing.IDrawingSystem;
import ruben.common.drawing.IGraphicObject;
import ruben.common.drawing.KillerVisitor;
import ruben.common.drawing.Line;
import ruben.common.drawing.Point;
import ruben.common.drawing.Text;
import ruben.common.processing.applet.*;

/**
 * Class DrawingSystem
 */
public class DrawingSystem implements IDrawingSystem 
{
	private BasePApplet _pApplet;
	
	private GraphicComposite _graphics;
	private Point _pen;
	

	//
	// Constructors
	//
	public DrawingSystem(BasePApplet pApplet)
	{
		_graphics = new GraphicComposite();		
		_pen = new Point(0, 0);
		_pApplet = pApplet;
	};

	/* (non-Javadoc)
	 * @see autoperspective.core.IDrawingSystem#SetDrawingPen(autoperspective.gobjects.Point)
	 */
	public void SetDrawingPen(Point p) {
		_pen = p;
	}
	
	/* (non-Javadoc)
	 * @see autoperspective.core.IDrawingSystem#DrawingAid(autoperspective.gobjects.Point)
	 */
	public void DrawingAid(Point target)
	{
		_pApplet.stroke(128);
		_pApplet.strokeWeight(1);
		_pApplet.line(_pen.getX(), _pen.getY(), target.getX(), target.getY());
	}

	/* (non-Javadoc)
	 * @see autoperspective.core.IDrawingSystem#AddPoint(autoperspective.gobjects.Point)
	 */
	public Point AddPoint(Point target)
	{
		_graphics.Add(target);
		SetDrawingPen(target);
		return target;
	}
	
	/* (non-Javadoc)
	 * @see autoperspective.core.IDrawingSystem#AddText(java.lang.String, autoperspective.gobjects.Point)
	 */
	public Text AddText(String text, Point target)
	{
		Text t = new Text(text, target.getX(), target.getY());
		return AddText(t);		
	}
	
	
	public Text AddText(Text text)
	{
		_graphics.Add(text);
		return text;
	}
	
	
	public Point AddLine(Point target) 
	{
		Line line = new Line(_pen.getX(), _pen.getY(), target.getX(), target.getY());
		line.SetColor(target.GetColor()).SetLineSize(target.GetLineSize());
		_graphics.Add(line);
		SetDrawingPen(target);
		return target;
	}
	
	public Line AddLine(Line target) 
	{
		_graphics.Add(target);
		SetDrawingPen(target.getP2());
		return target;
	}
	
	public void Wipe(Point target)
	{
		KillerVisitor v = new KillerVisitor(5, target);
		_graphics.Accept(v);
	}

	
	public void Undo()
	{
		if (_graphics != null) 
		{
			_graphics.RemoveLast();
		}
	}

	@Override
	public IGraphicObject GetGraphics() 
	{
		return _graphics;
	}

	@Override
	public Point GetPen() 
	{
		return _pen;
	}

	@Override
	public void Remove(IGraphicObject g) 
	{
		_graphics.Remove(g);
		
	}

}
