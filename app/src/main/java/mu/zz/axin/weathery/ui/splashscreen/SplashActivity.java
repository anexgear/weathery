package mu.zz.axin.weathery.ui.splashscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import mu.zz.axin.weathery.App;
import mu.zz.axin.weathery.Util;
import mu.zz.axin.weathery.model.Environment;
import mu.zz.axin.weathery.model.api.NetworkFetcher;
import mu.zz.axin.weathery.ui.main.MainActivity;


public class SplashActivity extends AppCompatActivity {
    private static String TAG = Environment.LOG_TAG_SPLASH_ACTIVITY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        NetworkFetcher.getWeatherFromNetwork(getApplication(),
                App.getPreferences(this, Environment.WEATHER_UNITS, "metric"),
                Double.valueOf(App.getPreferences(this, Environment.LOCATION_LATITUDE, Environment.KIEV_CITY_LATITUDE)),
                Double.valueOf(App.getPreferences(this, Environment.LOCATION_LONGITUDE, Environment.KIEV_CITY_LONGITUDE)));
        Util.startNewActivity(this, MainActivity.class);
        finish();
    }


}
