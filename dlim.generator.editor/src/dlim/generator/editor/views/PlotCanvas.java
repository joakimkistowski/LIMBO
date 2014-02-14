package dlim.generator.editor.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.ibm.icu.math.BigDecimal;

import dlim.Sequence;
import dlim.generator.ArrivalRateTuple;
import dlim.generator.IGeneratorConstants;
import dlim.generator.ModelEvaluator;

/**
 * Canvas within the PlotView. The arrival rate variations are plotted to this canvas.
 * @author Jóakim G. v. Kistowski
 */
public class PlotCanvas extends Canvas {

	private double samplingStep = 1.0;
	
	private final static int YMARGIN = 10;
	private final static int XMARGIN = 10;
	private final static int AXISMARKERSIZE = 4;
	
	private int yTopMargin = 0;
	private int yAxisMargin = 0;
	private int xAxisMargin = 0;
	
	private int xRightMargin = 0;
	
	private Sequence rootSequence;
	private ArrayList<ArrivalRateTuple> arrivalRateList = new ArrayList<ArrivalRateTuple>();
	private ArrayList<ArrivalRateTuple> innerArrivalRateList = new ArrayList<ArrivalRateTuple>();
	private List<ArrivalRateTuple> arrivalRateFileList;

	private boolean drawAxes = false;
	private boolean drawCombinatorImpact = false;
	private boolean plottingFile = false;
	private boolean drawLegend = true;


	/**
	 * Create a new plot canvas.
	 * @param parent
	 * @param style
	 * @param drawAxis set this to true, if axes are to be drawn.
	 */
	public PlotCanvas(Composite parent, int style, boolean drawAxis) {
		super(parent, style);
		rootSequence = null;
		this.drawAxes = drawAxis;
		
		if (drawAxes) {
			yTopMargin = 10;
			yAxisMargin = 34;
			xAxisMargin = 40;
		}
		
		setLayoutData(new GridData(SWT.FILL,SWT.CENTER));
		
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				paintOnDisplay(e.display,PlotCanvas.this,(PlotCanvas.this).getSize().x,(PlotCanvas.this).getSize().y);
			}
		});
			
	}
	
	
	public void setRightMargin(int margin) {
		xRightMargin = margin;
	}
	
	public void setRootSequence(Sequence rootSequence) {
		this.rootSequence = rootSequence;
	}
	
	public void setDecompositionMode(boolean decompose) {
		drawCombinatorImpact = decompose;
	}

	/**
	 * Set to true if an arrival rate trace is to be plotted on the canvas.
	 * @param plottingFile
	 */
	public void setPlottingFile(boolean plottingFile) {
		this.plottingFile = plottingFile;
	}
	
	/**
	 * The in-memory arrival rate trace.
	 * @param arrivalRateFileList
	 */
	public void setArrivalRateFileList(List<ArrivalRateTuple> arrivalRateFileList) {
		this.arrivalRateFileList = arrivalRateFileList;
	}
	
	/**
	 * Is true, if an explanation of the Combinator Impact visualization is to be drawn.
	 * @return
	 */
	public boolean isDrawLegend() {
		return drawLegend;
	}


	/**
	 * Set to true, if an explanation of the Combinator Impact visualization is to be drawn.
	 * @param drawLegend
	 */
	public void setDrawLegend(boolean drawLegend) {
		this.drawLegend = drawLegend;
	}
	
	/**
	 * Save the plot canvas' graph to an image file.
	 * @param path
	 * @param width
	 * @param height
	 */
	public void savePlotCanvasImage(String path, int width, int height) {
		Display display = this.getDisplay();
		Image image = new Image(display,width,height);
		//Font font = new Font(display, "Arial", 24, SWT.BOLD);
		
		paintOnDisplay(display,image,width,height);
		
		ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[] {image.getImageData()};
		loader.save(path, SWT.IMAGE_PNG);
		
		//font.dispose();
		image.dispose();
		//display.dispose();
	}

	/**
	 * The painter. Paints the plot onto a given display.
	 * @param display
	 * @param parent
	 * @param width
	 * @param height
	 */
	protected void paintOnDisplay(Display display, Drawable parent, int width, int height) {
		arrivalRateList.clear();
		innerArrivalRateList.clear();
		double maxArrivalRate = 0;
		
		//height = parent.getSize().y;
		//width =  parent.getParent().getSize().x;
		int maxY = height - YMARGIN - yAxisMargin;
		int yHeight = maxY - yTopMargin - YMARGIN;
		int xOffset = XMARGIN + xAxisMargin;
		int xWidth = width - xOffset - XMARGIN - xRightMargin;
		GC gc = new GC(parent);
		gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		gc.fillRectangle(0, 0, width, height);
		if (rootSequence != null) {
			gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
			
			int lastY = maxY;
			int lastInnerY = maxY;
			
			ModelEvaluator evaluator = new ModelEvaluator(rootSequence, 5, IGeneratorConstants.EVALUATION);
			//force 1 sampling per pixel
			samplingStep = evaluator.getDuration()/(double)xWidth;
			for (double i = samplingStep/2.0; i < evaluator.getDuration(); i+=samplingStep) {
				double y = evaluator.getArrivalRateAtTime(i);
				arrivalRateList.add(new ArrivalRateTuple(i,y));
				if (y > maxArrivalRate) {
					maxArrivalRate = y;
				}
				
				if (drawCombinatorImpact) {
					double innerY = evaluator.getArrivalRateDelta(i, -1, new boolean[1]);
					innerArrivalRateList.add(new ArrivalRateTuple(i,innerY));
					if (innerY > maxArrivalRate) {
						maxArrivalRate = innerY;
					}
				}
			}
			
			if (plottingFile && arrivalRateFileList != null && arrivalRateFileList.size() > 1) {
				for (ArrivalRateTuple t : arrivalRateFileList) {
					if (t.getArrivalRate() > maxArrivalRate) {
						if (t.getTimeStamp() > evaluator.getDuration()) {
							break;
						}
						maxArrivalRate = t.getArrivalRate();
					}
				}
			}
			
			
			for (ArrivalRateTuple t : arrivalRateList) {
				int y = (int)(maxY - t.getArrivalRate()*yHeight/maxArrivalRate);
				int x1 = xOffset + (int)((t.getTimeStamp()-samplingStep/2.0)*xWidth/evaluator.getDuration());
				int x2 = xOffset + (int)((t.getTimeStamp()+samplingStep/2.0)*xWidth/evaluator.getDuration());
				
				//paint combinator impact
				if (drawCombinatorImpact) {
					
					//double innerSequenceArrRate = evaluator.getArrivalRateDelta(t.getTimeStamp(), -1, new boolean[1]);
					double innerSequenceArrRate = innerArrivalRateList.get(arrivalRateList.indexOf(t)).getArrivalRate();
					double cl = innerSequenceArrRate;
					double cs = innerSequenceArrRate;
					for (int i = 0; i < rootSequence.getCombine().size(); i++) {
						int grayScale = 170+(i+1)*85/(rootSequence.getCombine().size()+1);
						Color rectColor;
						boolean[] isMult = new boolean[1];
						double delta = evaluator.getArrivalRateDelta(t.getTimeStamp(), i, isMult);
						if (isMult[0]) {
							rectColor = new Color(display,grayScale,grayScale,0);
						} else {
							rectColor = new Color(display,grayScale,0,0);
						}
						gc.setBackground(rectColor);
						
						int innerY = (int)(maxY - innerSequenceArrRate*yHeight/maxArrivalRate);
						if (delta < 0) {
							
							int csY = (int)(maxY - cs*yHeight/maxArrivalRate);
							int rectHeight = (int)(maxY - (cs+delta)*yHeight/maxArrivalRate) - csY;
							gc.fillRectangle(x1, csY, x2-x1, rectHeight);
							cs = cs + delta;
						} else if (delta > 0) {
							int clY = (int)(maxY - cl*yHeight/maxArrivalRate);
							int rectHeight = (int)(maxY - (cl+delta)*yHeight/maxArrivalRate) - clY;
							gc.fillRectangle(x1, clY, x2-x1, rectHeight);
							cl = cl + delta;
						}
						rectColor.dispose();
						gc.setLineStyle(SWT.LINE_DOT);
						Color functionColor = new Color(display,120,120,190);
						gc.setForeground(functionColor);
						gc.drawLine(x1,lastInnerY,x1,innerY);
						gc.drawLine(x1 , innerY, x2, innerY);
						gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
						functionColor.dispose();
						lastInnerY = innerY;
					}
				}
				
				gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
				gc.setLineStyle(SWT.LINE_SOLID);
				//paint final function
				gc.drawLine(x1,lastY,x1,y);
				gc.drawLine(x1 , y, x2, y);
				lastY = y;
			}
			
			//draw Arrival Rate file
			if (plottingFile && arrivalRateFileList != null && arrivalRateFileList.size() > 1) {
				double step = arrivalRateFileList.get(0).getStep(arrivalRateFileList.get(1));
				for (ArrivalRateTuple t : arrivalRateFileList) {
					
					int y = (int)(maxY - t.getArrivalRate()*yHeight/maxArrivalRate);
					int x1 = xOffset + (int)((t.getTimeStamp()-step/2.0)*xWidth/evaluator.getDuration());
					int x2 = xOffset + (int)((t.getTimeStamp()+step/2.0)*xWidth/evaluator.getDuration());
					
					gc.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
					gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
					gc.setLineStyle(SWT.LINE_SOLID);
					//paint final function
					gc.drawLine(x1,lastY,x1,y);
					gc.drawLine(x1 , y, x2, y);
					lastY = y;
				}
			}
			
			
			//draw axes
			if (drawAxes) {
				gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
				gc.setLineStyle(SWT.LINE_SOLID);
				gc.drawText(rootSequence.getName(), 10, height - YMARGIN - 4);
				gc.drawText("0", xOffset - 16, maxY - 7);
				
				gc.drawText("0", xOffset - 2, maxY + 10);
				
				//axes
				gc.drawLine(xOffset-AXISMARKERSIZE, maxY, width - XMARGIN - xRightMargin, maxY);
				gc.drawLine(xOffset, maxY+AXISMARKERSIZE, xOffset, YMARGIN+yTopMargin);
				
				//axis markers
				gc.drawLine(xOffset+xWidth, maxY-AXISMARKERSIZE,
						xOffset+xWidth, maxY+AXISMARKERSIZE);
				gc.drawLine(xOffset-AXISMARKERSIZE, YMARGIN+yTopMargin,
						xOffset+AXISMARKERSIZE, YMARGIN+yTopMargin);
				gc.drawText("arrival",2,maxY - yHeight/2 - 15);
				gc.drawText(" rates ",2,maxY - yHeight/2 + 1);
				gc.drawText("time", xOffset + xWidth/2 - 8, maxY + 6);
				
				BigDecimal bd = new BigDecimal(evaluator.getDuration());
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				String durationString = String.valueOf(bd.doubleValue());
				bd = new BigDecimal(maxArrivalRate);
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				String maxArrivalRateString = String.valueOf(bd.doubleValue());
				gc.drawText(durationString, width - xRightMargin - durationString.length()*6 - 2, maxY + 10);
				gc.drawText(maxArrivalRateString, Math.max(0, xOffset-12-6*maxArrivalRateString.length()),
						YMARGIN+yTopMargin - 7, true);
				
				//legend
				if (plottingFile) {
					gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
					gc.drawText("arrival rates from file", width- XMARGIN - 120, height-YMARGIN-8,true);
					
					gc.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
					gc.setLineStyle(SWT.LINE_SOLID);
					gc.drawLine(width- XMARGIN - 124, height-YMARGIN-1, width- XMARGIN - 148, height-YMARGIN-1);
				}
				
				if (drawCombinatorImpact && drawLegend) {
					int grayScale = 170+42;
					gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
					gc.drawText("impact of additive Combinator", width- XMARGIN - 340, height-YMARGIN-8,true);
					gc.drawText("impact of multiplicative Combinator", width- XMARGIN - 593, height-YMARGIN-8,true);
					gc.drawText("original function", width- XMARGIN - 736, height-YMARGIN-8,true);
					
					Color functionColor = new Color(display,grayScale,0,0);
					gc.setBackground(functionColor);
					gc.fillRectangle(width-XMARGIN-358, height-YMARGIN-6, 12, 12);
					functionColor.dispose();
					
					functionColor = new Color(display,grayScale,grayScale,0);
					gc.setBackground(functionColor);
					gc.fillRectangle(width-XMARGIN-611, height-YMARGIN-6, 12, 12);
					functionColor.dispose();
					gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
					
					gc.setLineStyle(SWT.LINE_DOT);
					functionColor = new Color(display,120,120,190);
					gc.setForeground(functionColor);
					gc.drawLine(width- XMARGIN - 740,height-YMARGIN-1,width- XMARGIN - 764,height-YMARGIN-1);
					gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					functionColor.dispose();
					gc.setLineStyle(SWT.LINE_SOLID);
				}
			}
		}
		gc.dispose();
	}
}
