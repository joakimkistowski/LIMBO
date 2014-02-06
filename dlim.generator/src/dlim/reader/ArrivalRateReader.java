package dlim.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import dlim.generator.ArrivalRateTuple;

/**
 * Provides utility to get arrival rates from a file.
 * @author Jóakim G. v. Kistowski
 *
 */
public class ArrivalRateReader {

	/**
	 * Reads a file and gets the arrival rate at the given point in time.
	 * Uses linear interpolation if the exact point in time is not defined in the file.
	 * Use only for single arrival rates.
	 * @param time
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static double getArrivalRateAtTimeFromFile(double time, String filePath) throws IOException {
		//time is below the lower boundary of defined arrival rates
		if (time <= 0) {
			return 0.0;
		}
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		double lowerTimeStamp = 0;
		double lowerArrivalRate = 0;
		double upperTimeStamp = 0;
		double upperArrivalRate = 0;
		String line;
		while ((line = br.readLine()) != null && upperTimeStamp < time) {
			lowerTimeStamp = upperTimeStamp;
			lowerArrivalRate = upperArrivalRate;
			line = line.substring(0, line.length() -1);
			String[] numbers = line.split(",");
			if (numbers.length >= 2) {
				try {
					upperTimeStamp = Double.parseDouble(numbers[0].trim());
				} catch (NumberFormatException e) {
					upperTimeStamp = 0;
				}
				try {
					upperArrivalRate = Double.parseDouble(numbers[1].trim());
				} catch (NumberFormatException e) {
					upperArrivalRate = 0;
				}
				
			}
		}
		br.close();	
		
		//time passes the upper boundary of defined arrival rates
		if (upperTimeStamp < time) {
			return 0.0;
		}
		
		//linear interpolation
		return lowerArrivalRate
				+ (time-lowerTimeStamp)*(upperArrivalRate-lowerArrivalRate)/(upperTimeStamp-lowerTimeStamp);
	}
	
	/**
	 * Reads an arrival rate file and stores its arrival rate and time-stamp tuples into memory.
	 * @throws IOException 
	 */
	public static List<ArrivalRateTuple> readFileToList(String filePath, double offset) throws IOException {
		ArrayList<ArrivalRateTuple> arrRates = new ArrayList<ArrivalRateTuple>();
		
		IPath txtFilePath = new Path(filePath.trim());
		
		BufferedReader br = new BufferedReader(new FileReader(txtFilePath.toString()));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.substring(0, line.length() -1);
			String[] numbers = line.split(",");
			if (numbers.length >= 2) {
				try {
					double timeStamp = Double.parseDouble(numbers[0].trim());
					double readArrivalRate = Double.parseDouble(numbers[1].trim());
					timeStamp = timeStamp - offset;
					if (timeStamp > 0) {
						arrRates.add(new ArrivalRateTuple(timeStamp,readArrivalRate));
					}
				} catch (NumberFormatException e) {
					
				}
			}
		}
		
		br.close();
		
		return arrRates;
	}
	
}
