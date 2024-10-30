package org.example.signals.modulation;

import org.example.signals.processing.SignalProcessor;

public class FrequencyModulation extends SignalModulator {

    public FrequencyModulation(double carrierFreq, double modulationFreq, double duration, double samplingRate) {
        super(carrierFreq, modulationFreq, duration, samplingRate);
    }

    @Override
    public double[] modulate(double[] t) {
        double[] messageSignal = SignalProcessor.squareWave(modulationFreq, duration, samplingRate);
        double[] modulatedSignal = new double[t.length];
        double deviation = 200;
        double integral = 0;

        for (int i = 0; i < t.length; i++) {
            integral += messageSignal[i];
            modulatedSignal[i] = Math.sin(2 * Math.PI * carrierFreq * t[i] + deviation * integral / samplingRate);
        }
        return modulatedSignal;
    }
}
