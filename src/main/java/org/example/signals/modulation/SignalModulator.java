package org.example.signals.modulation;

public abstract class SignalModulator {
    protected double carrierFreq;
    protected double modulationFreq;
    protected double duration;
    protected double samplingRate;

    public SignalModulator(double carrierFreq, double modulationFreq, double duration, double samplingRate) {
        this.carrierFreq = carrierFreq;
        this.modulationFreq = modulationFreq;
        this.duration = duration;
        this.samplingRate = samplingRate;
    }

    public abstract double[] modulate(double[] t);
}
