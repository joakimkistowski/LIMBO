/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.extractor.dialogs.BennosOfLaunchExtractionDialog;
import tools.descartes.dlim.generator.ArrivalRateTuple;


/**
 * Extracts a DLIM instance based on the Simple Extraction Model.
 *
 * @author Joakim von Kistowski
 */
public class BennosOfSimpleProcessExtractor implements IDlimExtractor {
	//EXPECTEDMAXPEAKSPERSEASONAL kopiert aus ModelExtractor
	private static final int EXPECTEDMAXPEAKSPERSEASONAL = 8;
	static int numberOfCorrAndLags=200;
	static double[] corrSaver=new double[numberOfCorrAndLags];

	/**
	 * Extracts a DLIM instance based on the Simple Extraction Model.
	 *
	 * @param root the root
	 * @param readArrivalRates the read arrival rates
	 */
	@Override
	public void extractIntoSequence(Sequence root,
			List<ArrivalRateTuple> arrList) {
		
		//getPeriodFromAutocorr() errechnet mit Hilfe von Autokorrelationen
		//eine Periode
		int lagOfMax=getPeriodFromAutocorr( arrList);
		
		
		//hier kann ich coden
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getShell();
		
		BennosOfLaunchExtractionDialog dialog = new BennosOfLaunchExtractionDialog(shell, root,
				arrList);
		
		//Falls die Periode (lagOfMax) gut genug
		//(Erklärung in der Methode periodGood)ist,
		//kann man sie setzen
		if(periodGood(corrSaver,lagOfMax)){
			dialog.setSeasonalPeriod(lagOfMax);
		}
		
		//falls die Periode nicht ausreichend gut ist,
		//muss man das gleiche Verfahren für den
		//mit einem Gauss-Filter geglätteten
		//Trace betrachten.
		else{
			System.out.println(" ");
			System.out.println("normales Verfahren mit Autokorrelation war nicht ausreichend.");
			System.out.println("Führe eine Glättung mit Gaußfilter durch");
			System.out.println("und wiederhole das Verfahren");
			System.out.println(" ");
			//Kopiere Tracedaten in eine neue Liste arrListGauss und führe dort
			//die Glättung mit dem Gauß-Filter durch.
			List<ArrivalRateTuple>arrListGauss= arrList;
			reduceArrivalRateListNoise(arrListGauss, lagOfMax);
			//System.out.println(arrList.toString());
			//lagOfMax wird jetzt überschrieben (hoffentlich mit einer besseren Periode)
			//corrSaver wurde auch bei den Berechnungen in reduceArrivalRateListNoise
			//überschrieben. Aber diese Überschreibungen sind ok, weil wir in diesem
			//Fall entweder die Periode von der geglätteten Liste nehmen 
			//oder wenn alle Stricke reißen seasonalPeriod auf den Standardwert 24 setzen.
			lagOfMax=getPeriodFromAutocorr( arrListGauss);
			
			//Periode gut? falls ja kann man sie setzen, sonst
			//wird jetzt der Standardwert gesetzt.
			if(periodGood(corrSaver,lagOfMax)){
				dialog.setSeasonalPeriod(lagOfMax);
			}
			else{
				dialog.setSeasonalPeriod(24);
			}
		}
		

		dialog.open();

		
	}
	
	
	//copied 4 methods that have to do with Gauß-Filter from ModelExtractor
	
	/*
	 * Create a gaussian filter with a given kernel width.
	 */
	private static double[] createGaussianFilter(int width) {
		int filterWidth = width;
		if (filterWidth % 2 == 0) {
			filterWidth++;
		}
		filterWidth = Math.max(1, filterWidth);
		double[] filter = new double[filterWidth];
		double sigma = Math.sqrt((filterWidth * filterWidth - 1.0) / 12.0);
		int mean = filterWidth / 2;
		double filterSum = 0.0;
		Gaussian gaussian = new Gaussian(mean, sigma);
		for (int i = 0; i < filterWidth; i++) {
			filter[i] = gaussian.value(i);
			filterSum += filter[i];
		}

		// normalize to 1
		for (int i = 0; i < filterWidth; i++) {
			filter[i] = filter[i] / filterSum;
		}

		return filter;
	}
	
	/*
	 * Reduce noise within the read arrival rate list by applying a gaussian
	 * filter.
	 */
	public static void reduceArrivalRateListNoise(List<ArrivalRateTuple> arrList, int lagOfMax) {
		//double[] filter = createGaussianFilter((int) (lagOfMax / EXPECTEDMAXPEAKSPERSEASONAL));
		double[] filter = createGaussianFilter(9);
		double[] arrivalRates = new double[arrList.size()];
		int index = 0;
		for (ArrivalRateTuple t : arrList) {
			arrivalRates[index] = t.getArrivalRate();
			index++;
		}
		index = 0;
		for (ArrivalRateTuple t : arrList) {
			t.setArrivalRate(getFilteredValueAtIndex(arrivalRates, index,
					filter));
			index++;
		}
	}
	/*
	 * Apply gaussian filter to arrival rate at index index.
	 */
	private static double getFilteredValueAtIndex(double[] arrivalRateArray,
			int index, double[] filter) {
		int filterCenter = filter.length / 2;

		double filteredValue = 0.0;
		for (int i = 0; i < filter.length; i++) {
			filteredValue += filter[i]
					* getArrivalRateFromArray(arrivalRateArray, index
							+ (i - filterCenter));
		}
		return filteredValue;
	}
	
	
	/*
	 * Comfort function. Returns 0 for out of bound array indices.
	 */
	private static double getArrivalRateFromArray(double[] array, int index) {
		if (index < 0 || index >= array.length) {
			return 0.0;
		}
		return array[index];
	}
	
	//Bestimmt mit Autokorrelation einen Kandidaten für die Periode.
	private static int getPeriodFromAutocorr(List<ArrivalRateTuple> arrList){
		
		
		
				//Abschnitt: Autokorrelation zum bestimmen von dominanten Perioden
				
						
						
						double [] arrRateArray=new double[arrList.size()];
						//befülle Array nun mit den Daten aus der Liste.
						int j=0;//Zählvariable für das Array
						for(ArrivalRateTuple art: arrList){
							arrRateArray[j]=art.getArrivalRate();
							j++;
						}
						
						// weiteres Array zum Befüllen mit Daten
						//aus der Liste arrList zu verschiedenen Lags k.
						double [] arrRateArrayLag=new double[arrList.size()];
						//zum Speicher der Korrelationswerte. (Später suchen wir den größten
						//Korrelationswert und
						//probieren Vielfache von ihm aus)
						
						//k ist Lag-Variable. Wir versuchen mehrere Lags aus
						//und suchen Korrelation zwischen Original-Trace
						//und Lag-Trace nahe dem Wert 1.
						for(int k=0;k<numberOfCorrAndLags;k++){
							int l=0;//Zählvariable für das Array
							for(ArrivalRateTuple art: arrList){
									arrRateArrayLag[(l+k)%(arrList.size())]=art.getArrivalRate();
								l++;
							}
							
							//compute Pearson product-moment correlation coefficient.
							//(A number in the intervall [-1,1])
							PearsonsCorrelation corr=new PearsonsCorrelation();
							double correlationTraceLagTrace=corr.correlation(arrRateArray, arrRateArrayLag);
							//speicher Korrelationswert im Array
							corrSaver[k]=correlationTraceLagTrace;
							
						}
						
						
						//Variablen zum Speichern der maximalen Korrelation
						// und des zugehörigen Lags
						double maxCorr=0;
						int lagOfMax=0;
						//zu geringe Lags produzieren hohe Korrelationen wegen zu großer Ähnlichkeit
						//zum ursprünglichen Trace. Deswegen Start bei Lag k=10.
						//29.05.16 schaue traces an und versuche nun startlag 75, weil
						// das globale maxima in den Korrelationen häufig von der Ähnlichkeit der
						// Traces bei geringem Lag rührt
						for(int k=75;k<corrSaver.length;k++){
							if(corrSaver[k]>maxCorr){
								maxCorr=corrSaver[k];
								lagOfMax=k;
							}
						}
						
						System.out.println("maximale Korrelation bei Lag "+lagOfMax+" entspricht "+maxCorr);
						
						//liefern Vielfache des Lags der maximalen Korrelation auch hohe Korrelationswerte?
						for(int i=1;i<10;i++){
							System.out.println("Korrelation bei "+i+"-fachen Lag");
							System.out.println("corrSaver[lagOfMax*"+ i+"] ="
									+ " "+corrSaver[(lagOfMax*i)%corrSaver.length]);
						}
						
						
						
						
						return lagOfMax;
	}
	
	
	
	
	//bekommt in corrSaver alle Korrelationen übergeben, wobei
	//corrSaver[k] der Korrelation zwischen normalem Trace und Trace
	// mit Lag k entspricht
	private static boolean periodGood(double[] corrSaver, int lagOfMax ){
		//Prüfung ob Lag gut gewählt ist.
				//Es wird geprüft ob der errechnete Lag und seine k-fachen mit 0<k<6
				// zu Korrelationen> 50% führen.
				//Unter diesem  Kriterium benutzen zum Beispiel der wikipedia_trace,
				//der ru.wikipedia.org_trace und der WorldCup98_trace die durch die Autokorrelation
				//errechnete Periode, während bibsonomy_2011_05-07_nospammer und
				//IBM_Transactions_S-MIEP_Trendlength1_Noise_ignored den Standardwert 24 für
				//seasonalPeriod nutzen.
				for(int k=1;k<6;k++){
					if(corrSaver[(lagOfMax*k)%corrSaver.length]<=0.33){
						//versuche mit 33% statt 50%
						return false;
					}
				}
		
		return true;
	}
	
	

}