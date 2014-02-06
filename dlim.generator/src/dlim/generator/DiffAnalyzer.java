package dlim.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import com.dtw.FastDTW;
import com.ibm.icu.math.BigDecimal;
import com.timeseries.TimeSeries;
import com.timeseries.TimeSeriesPoint;
import com.util.EuclideanDistance;


/**
 * Calculates the difference between a DLIM and an arrival rate .txt file.
 * @author Jóakim G. v. Kistowski
 *
 */
public class DiffAnalyzer {

	private ModelEvaluator evaluator;
	private IPath projectPath;
	
	private TimeSeries fileTS;
	private TimeSeries modelTS;
	
	//This list holds the differences for each sampled point in time
	private ArrayList<Double> diffList;
	private ArrayList<Double> relativeDiffList;
	
	/**
	 * Create a new DiffAnalyzer for the model that is being evaluated by the evaluator.
	 * All output will be written into the Eclipse project, described by its root path (projectPath).
	 * @param evaluator
	 * @param projectPath
	 */
	public DiffAnalyzer(ModelEvaluator evaluator, String projectPath) {
		this.evaluator = evaluator;
		this.projectPath = new Path(projectPath);
		diffList = new ArrayList<Double>();
		relativeDiffList = new ArrayList<Double>();
		fileTS = new TimeSeries(2);
		modelTS = new TimeSeries(2);
	}
	
	/**
	 * Calculate the difference.
	 * @param txtFilePath The file to which the model is to be compared.
	 * @return A list of difference metrices: 1. absolute mean, 2. absolute median,
	 * 	3. DTW based difference, 4. relative mean, 5. relative median
	 */
	public List<Double> calculateDiff(String txtFilePath, double offset) {
		try {
			double maxArrivalRate = 0.0;
			IPath diffFolderPath = projectPath.append("diffs");
			File diffFolder = diffFolderPath.toFile();
			if (!diffFolder.exists()){
				diffFolder.mkdir();
			}

			IPath diffTxtPath = diffFolderPath.append(evaluator.getName() + "Diff.txt");
			PrintWriter diffWriter = new PrintWriter(diffTxtPath.toString(), "UTF-8");
			BufferedReader br;
			
			br = new BufferedReader(new FileReader(txtFilePath));
			String line;
			double lastTimeStamp = 0;
			double diffSum = 0;
			double relativeDiffSum= 0;
			while ((line = br.readLine()) != null) {
				line = line.substring(0, line.length() -1);
				String[] numbers = line.split(",");
				if (numbers.length >= 2) {
					double timeStamp = Double.parseDouble(numbers[0].trim());
					if (lastTimeStamp == 0) {
						lastTimeStamp = -timeStamp;
					}
					double readArrivalRate = Double.parseDouble(numbers[1].trim());
					double modelTimeStamp = timeStamp - offset;
					if (modelTimeStamp > 0 && modelTimeStamp < evaluator.getDuration()) {
						double fileTSPoint[] = {modelTimeStamp,readArrivalRate};
						fileTS.addLast(modelTimeStamp, new TimeSeriesPoint(fileTSPoint));
						double functionArrivalRate = evaluator.getArrivalRateAtTime(modelTimeStamp);
						double modelTSPoint[] = {modelTimeStamp,functionArrivalRate};
						modelTS.addLast(modelTimeStamp, new TimeSeriesPoint(modelTSPoint));
						double diff = Math.abs(readArrivalRate - functionArrivalRate);
						diffWriter.println(modelTimeStamp + "," + diff +";");
						diffList.add(diff);
						double relativeDiff = 0.0;
						if (readArrivalRate != 0.0) {
							relativeDiff = diff/readArrivalRate;
						//} else if (functionArrivalRate != 0.0) {
						//	relativeDiff = diff/functionArrivalRate;
						}
						relativeDiffList.add(relativeDiff);
						
						relativeDiffSum += relativeDiff;
						diffSum += diff;
						
						//set maximum arrival rate
						if (readArrivalRate > maxArrivalRate) {
							maxArrivalRate = readArrivalRate;
						}
					}
					lastTimeStamp = timeStamp;
				}
			}
			br.close();
			diffWriter.close();
			
			if (diffList.size() == 0) {
				System.out.println("ERROR: file has an incorrect format. Only files "
					+ "with the correct Arrival Rate format can be read.");
			}
			
			Collections.sort(diffList);
			Collections.sort(relativeDiffList);
			double dtwDist = FastDTW.getWarpInfoBetween(fileTS, modelTS, 10, new EuclideanDistance()).getDistance();
			//System.out.println("DTWDist: " + dtwDist + ", diffSum: " + diffSum);
			//this devision should result in the distance between 0 and a function being 1
			dtwDist = dtwDist/(double)diffList.size();
			dtwDist = dtwDist/maxArrivalRate;
			
			double mean = diffSum/diffList.size();
			double median = diffList.get(diffList.size()/2);
			
			
			//System.out.println("Median diff: " + median);
			//System.out.println("Mean diff: " + mean);
			LinkedList<Double> statisticalValues = new LinkedList<Double>();
			statisticalValues.add(mean);
			statisticalValues.add(median);
			statisticalValues.add(dtwDist);
			
			//relative mean and median
			statisticalValues.add(relativeDiffSum/relativeDiffList.size());
			statisticalValues.add(relativeDiffList.get(relativeDiffList.size()/2));
			
			printToConsole(evaluator.getName(), statisticalValues);
			//System.out.println(evaluator.getName() + " & " +mean + " & " + statisticalValues.get(3)*100
			//		+ " & " + median  + " & " + statisticalValues.get(4)*100
			//		+ " & " + dtwDist + " \\\\");
			
			return statisticalValues;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new LinkedList<Double>();
	}
	
	private static void printToConsole(String name, List<Double> stats) {
		String firstCol = "";
		if (name.contains("HLDLIM")) {
			firstCol += "HLDLIM\\\\ ";
		} else if (name.contains("_simple_")) {
			firstCol += "DLIM Simple\\\\ ";
		} else {
			firstCol = "DLIM Periodic\\\\ ";
		}
		if (name.contains("_Trendlength1_")) {
			firstCol += "Trend length 1\\\\ ";
		} else if (name.contains("_Trendlength2_")) {
			firstCol += "Trend length 2\\\\ ";
		} else if (name.contains("_Trendlength3_")) {
			firstCol += "Trend length 3\\\\ ";
		}
		if (name.contains("_NoiseEliminated")) {
			firstCol += "noise eliminated ";
		} else if (name.contains("_Noise")) {
			firstCol += "noise extracted ";
		} else {
			firstCol += "noise ignored ";
		}
		
		System.out.println(firstCol + " & " + roundTo(stats.get(0),3) + " & " + roundTo(stats.get(3)*100,3)
				+ " & " + roundTo(stats.get(1),3)  + " & " + roundTo(stats.get(4)*100,3)
				+ " & " + roundTo(stats.get(2),6) + " \\\\");
		System.out.println("\\hline");
	}
	private static double roundTo(double val, int places) {
		BigDecimal bd = new BigDecimal(val);
		bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
}
