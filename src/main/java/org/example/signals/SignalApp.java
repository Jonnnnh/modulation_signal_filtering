package org.example.signals;

import org.example.signals.processing.SignalProcessor;
import org.example.signals.visualization.ChartUtils;

import javax.swing.*;
import java.awt.*;

public class SignalApp {

    private final SignalManager signalManager;

    public SignalApp(SignalManager signalManager) {
        this.signalManager = signalManager;
    }

    public void createAndShowGui() {
        JFrame frame = new JFrame("Модуляция сигналов и визуализация спектра");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));

        double[] t = signalManager.getT();
        double[] frequencies = SignalProcessor.lenspace(0, 512, signalManager.getAmSpectrum().length / 2);
        double[] frequencyRange = {0, 140};
        frame.add(ChartUtils.createChartPanel(t, signalManager.getAmSignal(), "Амплитудная модуляция", "Время (с)", "Амплитуда"));
        frame.add(ChartUtils.createChartPanel(t, signalManager.getFmSignal(), "Частотная модуляция", "Время (с)", "Амплитуда"));
        frame.add(ChartUtils.createChartPanel(t, signalManager.getPmSignal(), "Фазовая модуляция", "Время (с)", "Амплитуда"));

        frame.add(ChartUtils.createChartPanel(frequencies, signalManager.getAmSpectrum(), "Спектр амплитудной модуляции", "Частота (Гц)", "Амплитуда", frequencyRange));
        frame.add(ChartUtils.createChartPanel(frequencies, signalManager.getFmSpectrum(), "Спектр частотной модуляции", "Частота (Гц)", "Амплитуда", frequencyRange));
        frame.add(ChartUtils.createChartPanel(frequencies, signalManager.getPmSpectrum(), "Спектр фазовой модуляции", "Частота (Гц)", "Амплитуда", frequencyRange));

        frame.add(ChartUtils.createChartPanel(frequencies, signalManager.getTruncatedSpectrum(), "Обрезанный спектр амплитудной модуляции", "Частота (Гц)", "Амплитуда", frequencyRange));
        frame.add(ChartUtils.createChartPanel(t, signalManager.getSyntzSignal(), "Синтезированный сигнал", "Время (с)", "Амплитуда"));
        frame.add(ChartUtils.createChartPanel(t, signalManager.getFilterSignal(), "Отфильтрованный сигнал", "Время (с)", "Амплитуда"));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
