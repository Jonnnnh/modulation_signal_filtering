package org.example.signals.synthesizer;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.TransformType;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SignalSynthesizer {

    public static double[] synthesizeFromSpectrum(double[] spectrum, int lowCutoff, int highCutoff) {
        double[] truncSpectrum = spectrum.clone();
        for (int i = 0; i < lowCutoff; i++) {
            truncSpectrum[i] = 0;
        }
        for (int i = highCutoff; i < truncSpectrum.length; i++) {
            truncSpectrum[i] = 0;
        }
        return ifft(truncSpectrum);
    }

    private static double[] ifft(double[] spectrum) {
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] complexSpectrum = Arrays.stream(spectrum).mapToObj(v -> new Complex(v, 0))
                .toArray(Complex[]::new);

        Complex[] restoredSignal = fft.transform(complexSpectrum, TransformType.INVERSE);
        return Arrays.stream(restoredSignal).mapToDouble(Complex::getReal)
                .toArray();
    }
}
