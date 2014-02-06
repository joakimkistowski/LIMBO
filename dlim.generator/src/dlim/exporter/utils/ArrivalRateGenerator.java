package dlim.exporter.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import dlim.generator.ArrivalRateTuple;
import dlim.generator.ModelEvaluator;

/**
 * Generates an arrival rate file and chart by sampling a Descartes Load Intensity
 * Model using a given ModelEvaluator.
 * @author Jóakim G. v. Kistowski
 *
 */
public class ArrivalRateGenerator {

	
	
	/**
	 * Run the ArrivalRateGenerator.
	 * @param step The width of the sampling interval.
	 * @return A list of ArrivalRateTuples (Time-stamps and arrival rates) sampled from the model.
	 */
	public static List<ArrivalRateTuple> generateArrivalRates(ModelEvaluator evaluator, double step) {
		ArrayList<ArrivalRateTuple> arrRates = new ArrayList<ArrivalRateTuple>();
		for (double i = step/2; i < evaluator.getDuration(); i+=step) {
			double arrRate = evaluator.getArrivalRateAtTime(i);
			arrRates.add(new ArrivalRateTuple(i,arrRate));
		}
		return arrRates;
	}
	
	/**
	 * Run the ArrivalRateGenerator and write the results to a .txt file and an arrival rate plot.
	 * @param step The width of the sampling interval.
	 * @param endOfLineCharacter The character before the end of a line in the output file. Note: the "\n" is always
	 * 					printed after this character. It does not have to be included here.
	 * @param fileSuffix The file suffix ("txt","csv" ...)
	 */
	public static void writeArrivalRates(List<ArrivalRateTuple> arrRates, String projectPath, String modelName, String endOfLineCharacter, String fileSuffix) {
		try {
			IPath arrivalRateFolderPath = new Path(projectPath).append("arrivalRates");
			File arrivalRateFolder = arrivalRateFolderPath.toFile();
			if (!arrivalRateFolder.exists()){
				arrivalRateFolder.mkdir();
			}
			IPath arrivalRateTxtPath = arrivalRateFolderPath.append(modelName + "ArrivalRates." + fileSuffix);
			PrintWriter arrRateWriter;
			arrRateWriter = new PrintWriter(arrivalRateTxtPath.toString(), "UTF-8");
			
			for (ArrivalRateTuple tuple : arrRates) {
				double arrRate = tuple.getArrivalRate();
				double time = tuple.getTimeStamp();
				arrRateWriter.println(time + "," + arrRate + endOfLineCharacter);
			}
			arrRateWriter.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("IO error writing Arrival Rate file.");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("IO error writing Arrival Rate file.");
			e.printStackTrace();
		}
	}
}
