package mu.zz.axin.weathery.model.api;


import mu.zz.axin.weathery.model.weathermodel.WeatherModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface OpenWeatherApi {

    @Headers("Content-Type: application/json")
    @GET("forecast?mode=json")
    Call<WeatherModel> getWeatherForecast(@Query("q") String city, @Query("units") String units, @Query("appid") String apiKey);

    @Headers("Content-Type: application/json")
    @GET("forecast?mode=json")
    Call<WeatherModel> getWeatherForecast(@Query("lat") double latitude, @Query("lon") double longitude, @Query("units") String units, @Query("appid") String apiKey);

}
