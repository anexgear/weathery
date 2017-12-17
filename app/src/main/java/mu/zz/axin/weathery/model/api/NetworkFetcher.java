package mu.zz.axin.weathery.model.api;


import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import mu.zz.axin.weathery.App;
import mu.zz.axin.weathery.model.Environment;
import mu.zz.axin.weathery.model.database.LocationObjectBox;
import mu.zz.axin.weathery.model.database.WeatherObjectBox;
import mu.zz.axin.weathery.model.weathermodel.List;
import mu.zz.axin.weathery.model.weathermodel.WeatherModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NetworkFetcher {

    private static Box<LocationObjectBox> locationBox;
    private static Box<WeatherObjectBox> weatherBox;
    private static String TAG = Environment.LOG_TAG_NETWORK_FETCH;


    public static void getWeatherFromNetwork(final Application application, String weatherUnits, final double latitude, double longitude) {
        BoxStore boxStore = ((App) application).getBoxStore();
        locationBox = boxStore.boxFor(LocationObjectBox.class);
        weatherBox = boxStore.boxFor(WeatherObjectBox.class);

        OpenWeatherApi retrofitOpenWeatherApi = ApiHelper.getApiWeatherHelper();
        Log.d(TAG, "Retrofit weather units - " + weatherUnits);
        retrofitOpenWeatherApi.getWeatherForecast(latitude, longitude, weatherUnits, Environment.OPEN_WEATHER_API_KEY).enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                locationBox.removeAll();
                weatherBox.removeAll();
                Log.d(TAG, "Retrofit onResponse: remove all from DB");

                Log.d(TAG, "Retrofit onResponse: " + response.body().getCity().toString());

                ArrayList<List> weatherList = (ArrayList<List>) response.body().getList();
                for (int index = 0; index < weatherList.size(); index++) {
                    WeatherObjectBox weatherObjectBox = new WeatherObjectBox(0,
                            weatherList.get(index).getMain().getTemp(),
                            weatherList.get(index).getMain().getHumidity(),
                            weatherList.get(index).getWind().getSpeed(),
                            weatherList.get(index).getMain().getPressure(),
                            weatherList.get(index).getWeather().get(0).getDescription(),
                            weatherList.get(index).getDt(),
                            weatherList.get(index).getWeather().get(0).getIcon()
                    );
                    weatherBox.put(weatherObjectBox);
                    Log.d(TAG, "Retrofit onResponse: " + "id: " + weatherObjectBox.getId() + " temperature = " + weatherObjectBox.getTemperature());
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Log.d(TAG, "Retrofit onFailure: " + t.toString());
            }
        });
    }
}
