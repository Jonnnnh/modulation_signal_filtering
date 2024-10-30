package org.example.signals;

public class MainApp {

    public static void main(String[] args) {
        double samplingRate = 1024;
        double duration = 1;
        double carrierFreq = 50;
        double modulationFreq = 5;

        SignalManager signalManager = new SignalManager(samplingRate, duration, carrierFreq, modulationFreq);
        SignalApp app = new SignalApp(signalManager);
        app.createAndShowGui();
    }
}
