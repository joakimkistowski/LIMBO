/*******************************************************************************
 * Copyright (c) 2016 Benno Heilmann, Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.utils;

import java.util.List;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import tools.descartes.dlim.extractor.ModelExtractor;
import tools.descartes.dlim.generator.ArrivalRateTuple;


/**
 * Utility class for finding seasonal periods using auto-correlation
 * 
 * @author Benno Heilmann, Joakim von Kistowski
 *
 */
public final class Autocorrelation {

	//assumed filtering period, if no period is known
	private static final int DUMMY_PERIOD = 9*8;
	
	private static final int MAX_LAGS_TO_CONSIDER = 200;
	
	// utility class has a private constructor
	private Autocorrelation() {

	}

	public static double seasonalPeriodUsingAutocorrelation(List<ArrivalRateTuple> arrivalRates) {
		CorrelationResult result = getPeriodFromAutocorr(arrivalRates, new CorrelationResult(0, new double[MAX_LAGS_TO_CONSIDER]));

		// Falls die Periode (lagOfMax) gut genug
		// (Erklärung in der Methode periodGood)ist,
		// kann man sie setzen
		if (periodGood(result.getCorrSaver(), result.getMaxLag())) {
			return result.getMaxLag();
		}

		// falls die Periode nicht ausreichend gut ist,
		// muss man das gleiche Verfahren für den
		// mit einem Gauss-Filter geglätteten
		// Trace betrachten.
		else {
			// System.out.println(" ");
			// System.out.println("normales Verfahren mit Autokorrelation war
			// nicht ausreichend.");
			// System.out.println("Führe eine Glättung mit Gaußfilter durch");
			// System.out.println("und wiederhole das Verfahren");
			// System.out.println(" ");
			// Kopiere Tracedaten in eine neue Liste arrListGauss und führe dort
			// die Glättung mit dem Gauß-Filter durch.
			List<ArrivalRateTuple> arrListGauss = arrivalRates;
			ModelExtractor.reduceArrivalRateListNoise(arrListGauss, DUMMY_PERIOD);
			// System.out.println(arrList.toString());
			// lagOfMax wird jetzt überschrieben (hoffentlich mit einer besseren
			// Periode)
			// corrSaver wurde auch bei den Berechnungen in
			// reduceArrivalRateListNoise
			// überschrieben. Aber diese Überschreibungen sind ok, weil wir in
			// diesem
			// Fall entweder die Periode von der geglätteten Liste nehmen
			// oder wenn alle Stricke reißen seasonalPeriod auf den Standardwert
			// 24 setzen.
			result = getPeriodFromAutocorr(arrListGauss, result);

			// Periode gut? falls ja kann man sie setzen, sonst
			// wird jetzt der Standardwert gesetzt.
			if (periodGood(result.getCorrSaver(), result.getMaxLag())) {
				return result.getMaxLag();
			} else {
				return 0;
			}
		}
	}

	// Bestimmt mit Autokorrelation einen Kandidaten für die Periode.
	private static CorrelationResult getPeriodFromAutocorr(List<ArrivalRateTuple> arrList, CorrelationResult previousResult) {

		double[] corrSaver = previousResult.getCorrSaver();

		// Abschnitt: Autokorrelation zum bestimmen von dominanten Perioden

		double[] arrRateArray = new double[arrList.size()];
		// befülle Array nun mit den Daten aus der Liste.
		int j = 0;// Zählvariable für das Array
		for (ArrivalRateTuple art : arrList) {
			arrRateArray[j] = art.getArrivalRate();
			j++;
		}

		// weiteres Array zum Befüllen mit Daten
		// aus der Liste arrList zu verschiedenen Lags k.
		double[] arrRateArrayLag = new double[arrList.size()];
		// zum Speicher der Korrelationswerte. (Später suchen wir den größten
		// Korrelationswert und
		// probieren Vielfache von ihm aus)

		// k ist Lag-Variable. Wir versuchen mehrere Lags aus
		// und suchen Korrelation zwischen Original-Trace
		// und Lag-Trace nahe dem Wert 1.
		for (int k = 0; k < MAX_LAGS_TO_CONSIDER; k++) {
			int l = 0;// Zählvariable für das Array
			for (ArrivalRateTuple art : arrList) {
				arrRateArrayLag[(l + k) % (arrList.size())] = art.getArrivalRate();
				l++;
			}

			// compute Pearson product-moment correlation coefficient.
			// (A number in the intervall [-1,1])
			PearsonsCorrelation corr = new PearsonsCorrelation();
			double correlationTraceLagTrace = corr.correlation(arrRateArray, arrRateArrayLag);
			// speicher Korrelationswert im Array
			corrSaver[k] = correlationTraceLagTrace;

		}
		// lokale Maxima in corrSaver die größer
		// als Nachbarn in range (zweites Argument) sind
		findLocalMaxima(corrSaver, 5);

		// Variablen zum Speichern der maximalen Korrelation
		// und des zugehörigen Lags
		double maxCorr = 0;
		int lagOfMax = 0;
		// zu geringe Lags produzieren hohe Korrelationen wegen zu großer
		// Ähnlichkeit
		// zum ursprünglichen Trace. Deswegen Start bei Lag k=10.
		// 29.05.16 schaue traces an und versuche nun startlag 75, weil
		// das globale maxima in den Korrelationen häufig von der Ähnlichkeit
		// der
		// Traces bei geringem Lag rührt
		for (int k = 10; k < corrSaver.length; k++) {
			if (corrSaver[k] > maxCorr) {
				maxCorr = corrSaver[k];
				lagOfMax = k;
			}
		}

		 System.out.println("maximale Korrelation bei Lag "+lagOfMax+
		 "entspricht "+maxCorr);

		// liefern Vielfache des Lags der maximalen Korrelation auch hohe
		// Korrelationswerte?
		 for(int i=1;i<10;i++){
		 System.out.println("Korrelation bei "+i+"-fachen Lag");
		 System.out.println("corrSaver[lagOfMax*"+ i+"] ="
		 + " "+corrSaver[(lagOfMax*i)%corrSaver.length]);
		 }

		return new CorrelationResult(lagOfMax, corrSaver);
	}

	// bekommt in corrSaver alle Korrelationen übergeben, wobei
	// corrSaver[k] der Korrelation zwischen normalem Trace und Trace
	// mit Lag k entspricht
	private static boolean periodGood(double[] corrSaver, int lagOfMax) {
		// Prüfung ob Lag gut gewählt ist.
		// Es wird geprüft ob der errechnete Lag und seine k-fachen mit 0<k<6
		// zu Korrelationen> 50% führen.
		// Unter diesem Kriterium benutzen zum Beispiel der wikipedia_trace,
		// der ru.wikipedia.org_trace und der WorldCup98_trace die durch die
		// Autokorrelation
		// errechnete Periode, während bibsonomy_2011_05-07_nospammer und
		// IBM_Transactions_S-MIEP_Trendlength1_Noise_ignored den Standardwert
		// 24 für
		// seasonalPeriod nutzen.
		if (corrSaver[(lagOfMax) % corrSaver.length] <= 0.50) {
			return false;
		}
		for (int k = 2; k < 6; k++) {
			if (corrSaver[(lagOfMax * k) % corrSaver.length] <= 0.00) {
				return false;
			}
		}

		return true;
	}

	// finds up to 20 local maxima that are bigger then their neighbours in
	// range 10
	private static double[][] findLocalMaxima(double[] corrSaver, int range) {

		// Größe 21 weil 21=2*range+1 (range=10)
		double[] window = new double[2 * range + 1];
		// speichert die gefundenen lokalen Maxima und ihre Lags
		// lagMax[0] sind lags, lagMax[1] sind zugehörige Maxima
		double[][] lagMax = new double[2][20];
		// zählvariable für lagMax
		int r = 0;
		// speichert das momentane Maximum in Window
		double maxCorr = 0;
		// Start bei 10, weil range 10
		// Ende bei corrSaver.length-10, weil range 10
		for (int i = range; i < corrSaver.length - range; i++) {
			// befülle window
			for (int j = 0; j < window.length; j++) {
				// corrSaver[i-10+j], weil range 10
				window[j] = corrSaver[i - range + j];
			}
			// ist der Wert in der Mitte von window der größte
			// im window-array?
			// nach der for-Schleife ist der größte Wert von Window in
			// maxCorr gespeichert.
			for (int n = 0; n < window.length; n++) {
				if (window[n] > maxCorr) {
					maxCorr = window[n];
				}
			}

			// jetzt noch maxCorr mit window[10] vergleichen. (10 weil range=10)
			// Bei Gleichheit ist window[10] bzw. corrSaver[i-10+j] ein lokales
			// Maximum.
			if (window[range] == maxCorr) {
				// der Lag wird abgespeichert
				lagMax[0][r] = i;
				// und der zugehörige Wert des Maximums
				lagMax[1][r] = maxCorr;
				// neuer freier Platz in lagMax für andere Maxima und deren Lag
				r++;
			}
			// maxCorr wieder reseten
			maxCorr = 0;
		}
		// System.out.println(" ");
		// System.out.println("gefundene lokale Maxima an Positionen");
		// System.out.println(Arrays.toString(lagMax[0]));
		// System.out.println(" ");
		// System.out.println("erste lokal maximale Korrelation bei Lag:
		// "+lagMax[0][0]);
		// System.out.println(" ");

		//int countMaxima = 0;
		// zählt wie viele lokale Maxima es gibt
		// for (int i = 0; i < lagMax[0].length; i++) {
		//
		// if (lagMax[0][i] != 0) {
		// countMaxima++;
		// }
		//
		// }

		// System.out.println("Die Anzahl lokaler Maxima beträgt:
		// "+countMaxima);
		// System.out.println(" ");

		return lagMax;
	}

	private static class CorrelationResult {

		private double[] corrSaver;
		private int maxLag;

		public CorrelationResult(int maxLag, double[] corrSaver) {
			this.maxLag = maxLag;
			this.corrSaver = corrSaver;
		}

		public double[] getCorrSaver() {
			return corrSaver;
		}

		public int getMaxLag() {
			return maxLag;
		}


	}
}
