package mu.zz.axin.weathery.ui.forecast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Formatter;

import mu.zz.axin.weathery.App;
import mu.zz.axin.weathery.R;
import mu.zz.axin.weathery.Util;
import mu.zz.axin.weathery.model.Environment;
import mu.zz.axin.weathery.model.database.WeatherObjectBox;


public class ForecastFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private ImageView iconImageView;
    private TextView countryNameTextView;
    private TextView cityNameTextView;
    private TextView dateTimeTextView;
    private TextView weatherDescriptionTextView;
    private TextView temperatureTextView;
    private TextView windSpeedTextView;
    private TextView pressureTextView;
    private TextView humidityTextView;
    private WeatherObjectBox mWeatherObjectBox;

    public ForecastFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        initViews(view);
        mWeatherObjectBox = (WeatherObjectBox) getArguments().getSerializable(ForecastFragment.ARG_ITEM_ID);
        formatText(countryNameTextView, App.getPreferences(getActivity(), Environment.COUNTRY_NAME, "Ukraine"));
        formatText(cityNameTextView, App.getPreferences(getActivity(), Environment.CITY_NAME, "Kyiv"));
        setWeather();
        return view;


    }

    private void setWeather() {
        Util.setWeatherIcon(iconImageView, mWeatherObjectBox.getIcon());
        formatText(dateTimeTextView, (Util.getDateFromString(mWeatherObjectBox.getDateTime()) + " "
                + (Util.getTimeFromString(mWeatherObjectBox.getDateTime()))));
        formatText(weatherDescriptionTextView, mWeatherObjectBox.getDescription());
        formatText(temperatureTextView, mWeatherObjectBox.getTemperature());
        formatText(windSpeedTextView, mWeatherObjectBox.getWind());
        formatText(pressureTextView, mWeatherObjectBox.getPressure());
        formatText(humidityTextView, mWeatherObjectBox.getHumidity());
    }

    private void initViews(View view) {
        iconImageView = view.findViewById(R.id.iconImageView);
        countryNameTextView = view.findViewById(R.id.countryNameTextView);
        cityNameTextView = view.findViewById(R.id.cityNameTextView);
        dateTimeTextView = view.findViewById(R.id.dateTimeTextView);
        weatherDescriptionTextView = view.findViewById(R.id.weatherDescriptionTextView);
        temperatureTextView = view.findViewById(R.id.temperatureTextView);
        windSpeedTextView = view.findViewById(R.id.windSpeedTextView);
        pressureTextView = view.findViewById(R.id.pressureTextView);
        humidityTextView = view.findViewById(R.id.humidityTextView);
    }

    private void formatText(TextView textView, String string) {
        Formatter formatter = new Formatter();
        textView.setText((formatter.format(textView.getText().toString(), string)).toString());
    }

}
