package org.example.signals.modulation;

import org.example.signals.processing.SignalProcessor;

public class AmplitudeModulation extends SignalModulator {

    public AmplitudeModulation(double carrierFreq, double modulationFreq, double duration, double samplingRate) {
        super(carrierFreq, modulationFreq, duration, samplingRate);
    }

    @Override
    public double[] modulate(double[] t) {
        double[] messageSignal = SignalProcessor.squareWave(modulationFreq, duration, samplingRate);
        double[] modulatedSignal = new double[t.length];

        for (int i = 0; i < t.length; i++) {
            modulatedSignal[i] = (1 + 0.5 * messageSignal[i]) * Math.sin(2 * Math.PI * carrierFreq * t[i]);
        }
        return modulatedSignal;
    }
}
