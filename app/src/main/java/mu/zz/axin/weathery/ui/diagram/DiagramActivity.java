package mu.zz.axin.weathery.ui.diagram;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.LineChart;

import mu.zz.axin.weathery.R;
import mu.zz.axin.weathery.chart.ChartDrawer;
import mu.zz.axin.weathery.model.database.WeatherObjectBox;
import mu.zz.axin.weathery.ui.base.BaseActivity;

import static mu.zz.axin.weathery.model.Environment.PRESSURE_DIAGRAM_TAG;
import static mu.zz.axin.weathery.model.Environment.TEMPERATURE_DIAGRAM_TAG;
import static mu.zz.axin.weathery.model.Environment.WIND_SPEED_DIAGRAM_TAG;


public class DiagramActivity extends BaseActivity {
    private ImageButton temperatureDiagramButton, pressureDiagramButton, windSpeedDiagramButton;
    private java.util.List<WeatherObjectBox> mWeatherObjectBoxList;
    private LineChart graph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherObjectBoxList = getWeatherListFromDatabase();

        graph = findViewById(R.id.chart);
        temperatureDiagramButton = findViewById(R.id.temperatureDiagramButton);
        pressureDiagramButton = findViewById(R.id.pressureDiagramButton);
        windSpeedDiagramButton = findViewById(R.id.windSpeedDiagramButton);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_diagram;
    }

    @Override
    protected boolean showHomeButton() {
        return true;
    }

    @Override
    protected void onResume() {
        mWeatherObjectBoxList = getWeatherListFromDatabase();

        temperatureDiagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ChartDrawer(graph, mWeatherObjectBoxList, TEMPERATURE_DIAGRAM_TAG, Color.RED);
            }
        });
        pressureDiagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ChartDrawer(graph, mWeatherObjectBoxList, PRESSURE_DIAGRAM_TAG, Color.MAGENTA);
            }
        });
        windSpeedDiagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ChartDrawer(graph, mWeatherObjectBoxList, WIND_SPEED_DIAGRAM_TAG, Color.BLUE);
            }
        });
        graph.invalidate();
        temperatureDiagramButton.callOnClick();
        super.onResume();
    }
}
