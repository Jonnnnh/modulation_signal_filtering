package org.example.signals.modulation;

import org.example.signals.processing.SignalProcessor;

public class PhaseModulation extends SignalModulator {

    public PhaseModulation(double carrierFreq, double modulationFreq, double duration, double samplingRate) {
        super(carrierFreq, modulationFreq, duration, samplingRate);
    }

    @Override
    public double[] modulate(double[] t) {
        double[] messageSignal = SignalProcessor.squareWave(modulationFreq, duration, samplingRate);
        double[] modulatedSignal = new double[t.length];
        double phaseDeviation = 0.5 * Math.PI;

        for (int i = 0; i < t.length; i++){
            modulatedSignal[i] = Math.sin(2 * Math.PI * carrierFreq * t[i] + phaseDeviation * messageSignal[i]);
        }
        return modulatedSignal;
    }
}
