package dlim.exporter.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import com.ibm.icu.math.BigDecimal;

import dlim.generator.ArrivalRateTuple;
import dlim.generator.IGeneratorConstants;
import dlim.generator.ModelEvaluator;

/**
 * Creates a time-stamp file from an arrival rate list.
 * @author Jóakim G. v. Kistowski
 *
 */
public class TimeStampWriter {
	
	private static ModelEvaluator evaluator;
	private static IPath projectPath;
	private static String samplingMode = IGeneratorConstants.EQUALDISTANCESAMPLING;
	private static int decimalplaces = 3;
	private static double stretch = 1.0;
	private static double arDevisor = 1.0;
	
	//The list of time-stamps within each interval.
	private static List<Double> timeStampList = new ArrayList<Double>();
	
	/**
	 * Generate a time-stamp file.
	 * @param evaluator The ModelEvaluator for the current Descartes Load Intensity Model.
	 * 						Needed to access Model meta-information.
	 * @param projectPath The path of the model's Eclipse project.
	 * 						All output will be produced relative to this path.
	 * @param arrRates The list of arrival rates as provided by the ArrivalRateGenerator.
	 * @param samplingMode The mode, with which the time-stamps are to be sampled within each interval.
	 * 						This is either IGeneratorConstants.EQUALDISTANCESAMPLING or
	 * 						IGeneratorConstants.UNIFORMDISTRIBUTIONSAMPLING.
	 * @param decimalPlaces The amount of decimal places a time-stamp is allowed to have.
	 * @param stretch The factor by which to stretch the times of the arrival rate tuples.
	 * 					Using a value < 1 compresses the time.
	 * @param arDevisor Divide the arrival rates from the arrival rate tuples by this to produce less time-stamps.
	 * 					Using a value < 1 produces more time stamps
	 */
	public static void generateTimeStampsFromArrivalRates(ModelEvaluator evaluator, String projectPath, List<ArrivalRateTuple> arrRates, String samplingMode,
			int decimalPlaces, double stretch, double arDevisor) {
		generateTimeStampsFromArrivalRates(evaluator, projectPath, arrRates, samplingMode, decimalPlaces, stretch, arDevisor, ";", "txt");
	}
	
	
	/**
	 * Generate a time-stamp file.
	 * @param evaluator The ModelEvaluator for the current Descartes Load Intensity Model.
	 * 						Needed to access Model meta-information.
	 * @param projectPath The path of the model's Eclipse project.
	 * 						All output will be produced relative to this path.
	 * @param arrRates The list of arrival rates as provided by the ArrivalRateGenerator.
	 * @param samplingMode The mode, with which the time-stamps are to be sampled within each interval.
	 * 						This is either IGeneratorConstants.EQUALDISTANCESAMPLING or
	 * 						IGeneratorConstants.UNIFORMDISTRIBUTIONSAMPLING.
	 * @param decimalPlaces The amount of decimal places a time-stamp is allowed to have.
	 * @param stretch The factor by which to stretch the times of the arrival rate tuples.
	 * 					Using a value < 1 compresses the time.
	 * @param arDevisor Divide the arrival rates from the arrival rate tuples by this to produce less time-stamps.
	 * 					Using a value < 1 produces more time stamps
	 * @param endOfLineCharacter The character before the end of a line in the output file. Note: the "\n" is always
	 * 					printed after this character. It does not have to be included here.
	 * @param suffix The file Suffix ("txt","csv" ...)
	 */
	public static void generateTimeStampsFromArrivalRates(ModelEvaluator evaluator, String projectPath, List<ArrivalRateTuple> arrRates, String samplingMode,
			int decimalPlaces, double stretch, double arDevisor, String endOfLineCharacter, String suffix) {
		TimeStampWriter.evaluator = evaluator;
		TimeStampWriter.projectPath = new Path(projectPath);
		timeStampList.clear();
		TimeStampWriter.stretch = stretch;
		TimeStampWriter.samplingMode = samplingMode;
		TimeStampWriter.decimalplaces = decimalPlaces;
		TimeStampWriter.arDevisor = arDevisor;
		
		try {
			IPath timeStampFolderPath = TimeStampWriter.projectPath.append("timeStamps");
			File timeStampFolder = timeStampFolderPath.toFile();
			if (!timeStampFolder.exists()){
				timeStampFolder.mkdir();
			}
			IPath timeStampTxtPath = timeStampFolderPath.append(evaluator.getName() + "TimeStamps." + suffix);
			
			PrintWriter timeStampWriter = new PrintWriter(timeStampTxtPath.toString(), "UTF-8");
			
			ArrivalRateTuple lastTupel = null;
			for (ArrivalRateTuple tuple : arrRates) {
				generateTimeStamps(tuple,tuple.getStep(lastTupel),timeStampWriter,endOfLineCharacter);
				lastTupel = tuple;
			}
			timeStampWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not write time stamps.");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Could not write time stamps.");
			e.printStackTrace();
		}
	}
	
	//Generate the time stamps for one arrival rate tuple
	private static void generateTimeStamps(ArrivalRateTuple tuple, double step, PrintWriter writer, String endOfLineCharacter) {
		double arrRate = tuple.getArrivalRate()/arDevisor;
		double time = tuple.getTimeStamp();
		timeStampList.clear();
		double tmpStep = step*stretch;
		double tmpTime = time*stretch;
		if (samplingMode == IGeneratorConstants.EQUALDISTANCESAMPLING) {
			int loops = (int)(arrRate*tmpStep);
			for (double j = 0; j < loops; j++) {
				BigDecimal bd = new BigDecimal(tmpTime+ (j/loops)*tmpStep - tmpStep/2.0);
				bd = bd.setScale(decimalplaces, BigDecimal.ROUND_HALF_UP);
		    	writer.println(bd.doubleValue() + endOfLineCharacter);
			}
		} else {
			for (double j = 0; j < (int)(arrRate*tmpStep); j++) {
				double randomNumber = evaluator.getRandomNumber() - 0.5;
				BigDecimal bd = new BigDecimal(tmpTime + randomNumber*(step*stretch));
				addBDToList(bd);
			}
			printList(writer);
		}
	}
	
	//Adds the time-stamp to the list of time-stamps for this arrival rate tuple
	private static void addBDToList(BigDecimal bd) {
		if (bd != null) {
			//clamp value
			if (bd.doubleValue() > evaluator.getDuration()*stretch) {
				bd = new BigDecimal(evaluator.getDuration()*stretch);
			} else if (bd.doubleValue() < 0.0) {
				bd = new BigDecimal(0.0);
			}
			bd = bd.setScale(decimalplaces, BigDecimal.ROUND_HALF_UP);
			timeStampList.add(bd.doubleValue());
		}
	}
	
	//Print the time-stamp list to the .txt file
	//Don't forget to sort first.
	private static void printList(PrintWriter writer) {
		Collections.sort(timeStampList);
		for (Double timeStamp : timeStampList) {
			writer.println(timeStamp + ";");
		}
	}
}
