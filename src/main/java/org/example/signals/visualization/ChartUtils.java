package org.example.signals.visualization;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class ChartUtils {

    public static JPanel createChartPanel(double[] xData, double[] yData, String title, String xLabel, String yLabel){
        return createChartPanel(xData, yData, title, xLabel, yLabel, null);
    }

    public static JPanel createChartPanel(double[] xData, double[] yData, String title, String xLabel, String yLabel, double[] xRange){
        XYSeries series = new XYSeries(title);
        for (int i = 0; i < xData.length; i++){
            series.add(xData[i], yData[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                xLabel,
                yLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        plot.getRangeAxis().setAutoRange(true);

        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        if (xRange != null && xRange.length == 2){
            domain.setRange(xRange[0], xRange[1]);
        }else{
            domain.setRange(xData[0], xData[xData.length - 1]);
        }

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }
}
