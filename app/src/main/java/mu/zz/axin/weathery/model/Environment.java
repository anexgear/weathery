package mu.zz.axin.weathery.model;

public class Environment {

    //OpenWeather
    //http://api.openweathermap.org/data/2.5/forecast?q=Kiev&units=metric&appid=d77b2dfd27d6de8624f0381045f68e98
    public static final String OPEN_WEATHER_API_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String OPEN_WEATHER_API_KEY = "d77b2dfd27d6de8624f0381045f68e98";
    public static final String KIEV_CITY_LATITUDE = "50.4021368";
    public static final String KIEV_CITY_LONGITUDE = "30.2525114";

    //GooglePlaces
    public static final String LOCATION_LATITUDE = "latitude";
    public static final String LOCATION_LONGITUDE = "longitude";
    public static final String CITY_NAME = "city";
    public static final String COUNTRY_NAME = "country";


    //Logging
    public static final boolean ENABLE_LOG = false;
    public static final String LOG_TAG_MAIN_ACTIVITY = "MainActivity";
    public static final String LOG_TAG_SPLASH_ACTIVITY = "SplashActivity";
    public static final String LOG_TAG_FORECAST_ACTIVITY = "ForecastActivity";
    public static final String LOG_TAG_SETTINGS_ACTIVITY = "SettingsActivity";

    public static final String LOG_TAG_NETWORK_FETCH = "NetworkFetch";


    //ChartTags
    public static final String TEMPERATURE_DIAGRAM_TAG = "Temperature";
    public static final String PRESSURE_DIAGRAM_TAG = "Pressure";
    public static final String WIND_SPEED_DIAGRAM_TAG = "WindSpeed";

    //Settings
    public static final String WEATHER_UNITS = "WeatherUnits";

}
