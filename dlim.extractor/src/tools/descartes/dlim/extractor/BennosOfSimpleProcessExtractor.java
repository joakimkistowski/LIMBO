/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor;

import java.util.List;

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

	/**
	 * Extracts a DLIM instance based on the Simple Extraction Model.
	 *
	 * @param root the root
	 * @param readArrivalRates the read arrival rates
	 */
	@Override
	public void extractIntoSequence(Sequence root,
			List<ArrivalRateTuple> arrList) {
		
		//Benno Code
		
		
		//Abschnitt: Autokorrelation zum bestimmen von dominanten Perioden
		
				long t=System.currentTimeMillis();
				int numberOfCorrAndLags=300;
				//wie viele Lags (und damit auch Korrelationen) sollen betrachtet werden?
				double [] arrRateArray=new double[arrList.size()];
				//befülle Array nun mit den Daten aus der Liste.
				int j=0;//Zählvariable für das Array
				for(ArrivalRateTuple art: arrList){
					arrRateArray[j]=art.getArrivalRate();
					//Print values before FFT
//					System.out.println("Wert vor Autokorrelation (ohne Lag)= "+arrRateArray[j]);
//					System.out.println("arrRateArray["+j+"] = "
//							+arrRateArray[j]);
//					System.out.println("");
					j++;
				}
				
				//befülle weiteres Array mit Daten aus der Liste zum Lag k.
				double [] arrRateArrayLag=new double[arrList.size()];
				//zum Speicher der Korrelationswerte. (Später suchen wir den größten
				//Korrelationswert und
				//probieren Vielfache von ihm aus)
				double[] corrSaver=new double[numberOfCorrAndLags];
				
				//k ist Lag-Variable. Wir versuchen mehrere Lags aus
				//und suchen Korrelation zwischen Original-Trace
				//und Lag-Trace nahe dem Wert 1.
				for(int k=0;k<numberOfCorrAndLags;k++){
					int l=0;//Zählvariable für das Array
					for(ArrivalRateTuple art: arrList){
							arrRateArrayLag[(l+k)%(arrList.size())]=art.getArrivalRate();
//						System.out.println("Wert vor Autokorrelation (mit Lag "+k+")  ");
//						System.out.println("arrRateArrayLag["+(l+k)%(arrList.size())+"] = "
//							+art.getArrivalRate());
//						System.out.println("");
						l++;
					}
					
					//compute Pearson product-moment correlation coefficient. (A number in the intervall [-1,1])
					PearsonsCorrelation corr=new PearsonsCorrelation();
					double correlationTraceLagTrace=corr.correlation(arrRateArray, arrRateArrayLag);
					//speicher Korrelationswert im Array
					corrSaver[k]=correlationTraceLagTrace;
					
				}
//				System.out.println("Alle errechneten Korrelationen für Lags zwischen 0 und 199");
//				System.out.println(Arrays.toString(corrSaver));
//				
				//Variablen zum Speichern der maximalen Korrelation
				// und des zugehörigen Lags
				double maxCorr=0;
				int lagOfMax=0;
				//zu geringe Lags produzieren hohe Korrelationen wegen zu großer Ähnlichkeit
				//zum ursprünglichen Trace. Deswegen Start bei Lag k=4.
				for(int k=4;k<corrSaver.length;k++){
					if(corrSaver[k]>maxCorr){
						maxCorr=corrSaver[k];
						lagOfMax=k;
					}
				}
				
				System.out.println("maximale Korrelation bei Lag "+lagOfMax+" entspricht "+maxCorr);
				
				//liefern Vielfache des Lags der maximalen Korrelation auch hohe Korrelationswerte?
				for(int i=1;i<10;i++){
					System.out.println("Korrelation bei "+i+"-fachen Lag");
					System.out.println("corrSaver[lagOfMax*"+ i+"] = "+corrSaver[(lagOfMax*i)%corrSaver.length]);
				}
				
				t=System.currentTimeMillis()-t;
				System.out.println(t);
				//ende benno
		
		//hier kann ich coden
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getShell();
		//errechnete werte übergeben
		BennosOfLaunchExtractionDialog dialog = new BennosOfLaunchExtractionDialog(shell, root,
				arrList);
		dialog.open();

		/*
		 * if (!dialog.wasCanceled()) { try {
		 * ModelExtractor.extractArrivalRateFileIntoSequence(root,
		 * readArrivalRates,dialog.getSeasonalPeriod(),
		 * dialog.getSeasonalsPerTrend
		 * (),dialog.getSeasonalShape(),dialog.getTrendShape(),
		 * dialog.getOperatorLiteral(), dialog.isExtractNoise()); } catch
		 * (ExtractionParameterException e) { MessageDialog.openError( shell,
		 * "Extraction Error", "Extraction Error: " + e.getMessage()); } }
		 */
	}

}
