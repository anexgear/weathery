package mu.zz.axin.weathery.chart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import mu.zz.axin.weathery.R;
import mu.zz.axin.weathery.model.database.WeatherObjectBox;

import static mu.zz.axin.weathery.model.Environment.PRESSURE_DIAGRAM_TAG;
import static mu.zz.axin.weathery.model.Environment.TEMPERATURE_DIAGRAM_TAG;
import static mu.zz.axin.weathery.model.Environment.WIND_SPEED_DIAGRAM_TAG;


public class ChartDrawer {

    public ChartDrawer(LineChart chart, List<WeatherObjectBox> mWeatherObjectBoxList, String label, int color) {
        List<Entry> entries = new ArrayList<>();
        selectChartDataSet(chart, mWeatherObjectBoxList, label, entries);
        LineDataSet dataSet = new LineDataSet(entries, label);
        setDataSetParams(color, dataSet);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        animateChart(chart, label);
        chart.invalidate();
    }

    private void animateChart(LineChart chart, String label) {
        switch (label) {
            case TEMPERATURE_DIAGRAM_TAG:
                chart.animateXY(1000, 1000);
                break;
            case PRESSURE_DIAGRAM_TAG:
                chart.animateX(1000);
                break;
            case WIND_SPEED_DIAGRAM_TAG:
                chart.animateY(1000);
                break;
        }
    }

    private void setDataSetParams(int color, LineDataSet dataSet) {
        dataSet.setColor(color);
        dataSet.setDrawCircles(true);
        dataSet.setDrawCircleHole(true);
        dataSet.setCircleRadius(5f);
        dataSet.setCircleHoleRadius(3f);
        dataSet.setLineWidth(3f);
        dataSet.setCircleColor(R.color.secondaryDarkColor);
        dataSet.setCircleColorHole(Color.YELLOW);
        dataSet.setValueTextSize(15f);
        dataSet.setValueTextColor(Color.BLACK);
    }

    private void selectChartDataSet(LineChart chart, List<WeatherObjectBox> mWeatherObjectBoxList, String label, List<Entry> entries) {
        switch (label) {
            case TEMPERATURE_DIAGRAM_TAG:
                for (WeatherObjectBox weatherObject : mWeatherObjectBoxList) {
                    float temperature = Float.valueOf(weatherObject.getTemperature());
                    int date = Integer.valueOf(weatherObject.getDateTime());
                    entries.add(new Entry(date, temperature));
                    DiagramDateFormatter xAxisFormatter = new DiagramDateFormatter(date);
                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(xAxisFormatter);
                }
                break;
            case PRESSURE_DIAGRAM_TAG:
                for (WeatherObjectBox weatherObject : mWeatherObjectBoxList) {
                    float temperature = Float.valueOf(weatherObject.getPressure());
                    int date = Integer.valueOf(weatherObject.getDateTime());
                    entries.add(new Entry(date, temperature));
                    DiagramDateFormatter xAxisFormatter = new DiagramDateFormatter(date);
                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(xAxisFormatter);
                }
                break;
            case WIND_SPEED_DIAGRAM_TAG:
                for (WeatherObjectBox weatherObject : mWeatherObjectBoxList) {
                    float temperature = Float.valueOf(weatherObject.getWind());
                    int date = Integer.valueOf(weatherObject.getDateTime());
                    entries.add(new Entry(date, temperature));
                    DiagramDateFormatter xAxisFormatter = new DiagramDateFormatter(date);
                    XAxis xAxis = chart.getXAxis();
                    xAxis.setValueFormatter(xAxisFormatter);
                }
                break;
        }
    }

}
