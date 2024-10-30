package org.example.signals.filter;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.TransformType;

public class SignalFilter {

    public static double[] hilbertEnvelope(double[] signal) {
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        int n = signal.length;

        Complex[] spectrum = fft.transform(signal, TransformType.FORWARD);
        for (int i = 1; i < n / 2; i++) {
            spectrum[i] = spectrum[i].multiply(2);}
        for (int i = n / 2; i < n; i++) {
            spectrum[i] = new Complex(0, 0);}
        Complex[] analyticSignal = fft.transform(spectrum, TransformType.INVERSE);
        double[] envelope = new double[n];
        for (int i = 0; i < n; i++) {
            envelope[i] = analyticSignal[i].abs();}
        return envelope;
    }

    public static double[] applyThreshold(double[] signal, double threshold) {
        double[] filteredSignal = new double[signal.length];
        for (int i = 0; i < signal.length; i++) {
            filteredSignal[i] = signal[i] > threshold ? 1 : 0;
        }
        return filteredSignal;
    }
}
