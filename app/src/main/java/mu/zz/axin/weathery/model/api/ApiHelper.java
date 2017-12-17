package mu.zz.axin.weathery.model.api;


import android.support.annotation.NonNull;

import mu.zz.axin.weathery.model.Environment;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static mu.zz.axin.weathery.model.Environment.ENABLE_LOG;


class ApiHelper {
    private static OkHttpClient client;

    static OpenWeatherApi getApiWeatherHelper() {
        initHttpClient();
        Retrofit retrofit = getRetrofit(Environment.OPEN_WEATHER_API_BASE_URL);
        return retrofit.create(OpenWeatherApi.class);
    }

    @NonNull
    private static Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static void initHttpClient() {
        client = new OkHttpClient();
        if (ENABLE_LOG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(interceptor);
        }
    }
}
