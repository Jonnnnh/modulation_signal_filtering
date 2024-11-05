package org.example.signals.processing;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.TransformType;
import java.util.Arrays;

public class SignalProcessor {

    public static double[] lenspace(double start, double end, int numPoints) {
        double[] result = new double[numPoints];
        double step = (end - start) / (numPoints - 1);
        for (int i = 0; i < numPoints; i++) {
            result[i] = start + (i * step);
        }
        return result;
    }

    public static double[] squareWave(double frequency, double duration, double samplingRate){
        double[] t = lenspace(0, duration, (int) (samplingRate * duration));
        double[] signal = new double[t.length];
        for (int i = 0; i < t.length; i++) {
            signal[i] = Math.signum(Math.sin(2 * Math.PI * frequency * t[i]));
        }
        return signal;
    }

    public static double[] fft(double[] signal, int samplingRate) {
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] spectrum = fft.transform(signal, TransformType.FORWARD);
        return Arrays.stream(spectrum).mapToDouble(Complex::abs).toArray();
    }
}
