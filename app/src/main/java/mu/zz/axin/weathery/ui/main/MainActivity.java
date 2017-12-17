package mu.zz.axin.weathery.ui.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mu.zz.axin.weathery.R;
import mu.zz.axin.weathery.Util;
import mu.zz.axin.weathery.model.Environment;
import mu.zz.axin.weathery.model.database.WeatherObjectBox;
import mu.zz.axin.weathery.ui.base.BaseActivity;
import mu.zz.axin.weathery.ui.diagram.DiagramActivity;
import mu.zz.axin.weathery.ui.forecast.ForecastActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static String TAG = Environment.LOG_TAG_MAIN_ACTIVITY;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.weatherForecastBtn)
    ImageView weatherForecastBtn;
    @BindView(R.id.weatherDiagramBtn)
    ImageView weatherDiagramBtn;

    private java.util.List<WeatherObjectBox> mWeatherObjectBoxList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mWeatherObjectBoxList = getWeatherListFromDatabase();
        weatherForecastBtn.setOnClickListener(this);
        weatherDiagramBtn.setOnClickListener(this);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean showHomeButton() {
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            Util.setWeatherIcon(weatherForecastBtn, mWeatherObjectBoxList.get(0).getIcon());
        } catch (Exception e) {
            Log.d(TAG, "onResume" + " " + e);
            weatherForecastBtn.setImageResource(R.drawable.ic_01d);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weatherForecastBtn:
                Util.startNewActivity(this, ForecastActivity.class);
                break;

            case R.id.weatherDiagramBtn:
                Util.startNewActivity(this, DiagramActivity.class);
                break;
        }

    }
}
