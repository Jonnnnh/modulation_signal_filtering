package org.example.signals;

import org.example.signals.filter.SignalFilter;
import org.example.signals.modulation.AmplitudeModulation;
import org.example.signals.modulation.FrequencyModulation;
import org.example.signals.modulation.PhaseModulation;
import org.example.signals.processing.SignalProcessor;
import org.example.signals.synthesizer.SignalSynthesizer;

public class SignalManager {

    private final double[] t;
    private final double samplingRate;
    private final double[] amSignal;
    private final double[] fmSignal;
    private final double[] pmSignal;
    private final double[] amSpectrum;
    private final double[] fmSpectrum;
    private final double[] pmSpectrum;
    private final double[] syntzSignal;
    private final double[] filterSignal;
    private final double[] truncatedSpectrum;

    public SignalManager(double samplingRate, double duration, double carrierFreq, double modulationFreq) {
        this.samplingRate = samplingRate;
        this.t = SignalProcessor.lenspace(0, duration, (int) (samplingRate * duration));

        this.amSignal = new AmplitudeModulation(carrierFreq, modulationFreq, duration, samplingRate).modulate(t);
        this.fmSignal = new FrequencyModulation(carrierFreq, modulationFreq, duration, samplingRate).modulate(t);
        this.pmSignal = new PhaseModulation(carrierFreq, modulationFreq, duration, samplingRate).modulate(t);
        this.amSpectrum = SignalProcessor.fft(amSignal, (int) samplingRate);
        this.fmSpectrum = SignalProcessor.fft(fmSignal, (int) samplingRate);
        this.pmSpectrum = SignalProcessor.fft(pmSignal, (int) samplingRate);
        this.truncatedSpectrum = truncateSpectrum(amSpectrum, 20, 90);

        this.syntzSignal = SignalSynthesizer.synthesizeFromSpectrum(truncatedSpectrum, 10, 100);

        double[] envelope = SignalFilter.hilbertEnvelope(syntzSignal);
        this.filterSignal = SignalFilter.applyThreshold(envelope, 0.5);
    }

    public double[] getT() {
        return t;
    }

    public double[] getAmSignal() {
        return amSignal;
    }

    public double[] getFmSignal() {
        return fmSignal;
    }

    public double[] getPmSignal() {
        return pmSignal;
    }

    public double[] getAmSpectrum() {
        return amSpectrum;
    }

    public double[] getFmSpectrum() {
        return fmSpectrum;
    }

    public double[] getPmSpectrum() {
        return pmSpectrum;
    }

    public double[] getSyntzSignal(){
        return syntzSignal;
    }

    public double[] getFilterSignal(){
        return filterSignal;
    }

    public double[] getTruncatedSpectrum() {
        return truncatedSpectrum;
    }
    public double getSamplingRate() {
        return samplingRate;
    }
    private static double[] truncateSpectrum(double[] spectrum, int lowCutoff, int highCutoff) {
        double[] truncatedSpectrum = spectrum.clone();
        for (int i = 0; i < lowCutoff; i++) truncatedSpectrum[i] = 0;
        for (int i = highCutoff; i < truncatedSpectrum.length; i++) truncatedSpectrum[i] = 0;
        return truncatedSpectrum;
    }

}
