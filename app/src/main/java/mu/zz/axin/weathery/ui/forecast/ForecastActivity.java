package mu.zz.axin.weathery.ui.forecast;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import mu.zz.axin.weathery.R;
import mu.zz.axin.weathery.model.Environment;
import mu.zz.axin.weathery.model.database.WeatherObjectBox;
import mu.zz.axin.weathery.ui.base.BaseActivity;
import mu.zz.axin.weathery.ui.recyclerview.WeatherRecyclerAdapter;

public class ForecastActivity extends BaseActivity {
    private String TAG = Environment.LOG_TAG_FORECAST_ACTIVITY;
    private WeatherRecyclerAdapter mWeatherRecyclerAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private java.util.List<WeatherObjectBox> mWeatherObjectBoxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = findViewById(R.id.recyclerView);
        mWeatherObjectBoxList = getWeatherListFromDatabase();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        if (mWeatherRecyclerAdapter == null) {
            setRecyclerAdapter();
        }

    }

    private void setRecyclerAdapter() {
        mWeatherRecyclerAdapter = new WeatherRecyclerAdapter(mWeatherObjectBoxList, new WeatherRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WeatherObjectBox weatherObjectBox) {
                replaceFragment(weatherObjectBox);
                Log.d(TAG, weatherObjectBox.getTemperature());
            }
        });
        mRecyclerView.setAdapter(mWeatherRecyclerAdapter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_forecast;
    }

    @Override
    protected boolean showHomeButton() {
        return true;
    }

    private void replaceFragment(WeatherObjectBox weatherObjectBox) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ForecastFragment.ARG_ITEM_ID, weatherObjectBox);
        ForecastFragment forecastFragment = new ForecastFragment();
        forecastFragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.forecastDetailFrame, forecastFragment).commit();
    }


    @Override
    protected void onResume() {
        mWeatherObjectBoxList = getWeatherListFromDatabase();
        setRecyclerAdapter();
        replaceFragment(mWeatherObjectBoxList.get(0));
        Log.d(TAG, "notifyDataSetChanged");
        super.onResume();
    }

}
