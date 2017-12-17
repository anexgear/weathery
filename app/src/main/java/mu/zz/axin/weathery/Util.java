package mu.zz.axin.weathery;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {
    public static void setWeatherIcon(ImageView imageView, String iconString) {
        switch (iconString) {
            case "01d":
                imageView.setImageResource(R.drawable.ic_01d);
                break;
            case "01n":
                imageView.setImageResource(R.drawable.ic_01n);
                break;
            case "02d":
                imageView.setImageResource(R.drawable.ic_02d);
                break;
            case "02n":
                imageView.setImageResource(R.drawable.ic_02n);
                break;
            case "03d":
                imageView.setImageResource(R.drawable.ic_03d);
                break;
            case "03n":
                imageView.setImageResource(R.drawable.ic_03n);
                break;
            case "04d":
                imageView.setImageResource(R.drawable.ic_04d);
                break;
            case "04n":
                imageView.setImageResource(R.drawable.ic_04n);
                break;
            case "09d":
                imageView.setImageResource(R.drawable.ic_09d);
                break;
            case "09n":
                imageView.setImageResource(R.drawable.ic_09n);
                break;
            case "10d":
                imageView.setImageResource(R.drawable.ic_10d);
                break;
            case "10n":
                imageView.setImageResource(R.drawable.ic_10n);
                break;
            case "11d":
                imageView.setImageResource(R.drawable.ic_11d);
                break;
            case "11n":
                imageView.setImageResource(R.drawable.ic_11n);
                break;
            case "13d":
                imageView.setImageResource(R.drawable.ic_13d);
                break;
            case "13n":
                imageView.setImageResource(R.drawable.ic_13n);
                break;
            case "50d":
                imageView.setImageResource(R.drawable.ic_50d);
                break;
            case "50n":
                imageView.setImageResource(R.drawable.ic_50n);
                break;
        }
    }


    public static String getDateFromString(String weatherDT) {
        Date date = getDateFromTS(weatherDT);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }

    public static String getTimeFromString(String weatherDT) {
        Date date = getDateFromTS(weatherDT);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }


    public static Date getDateFromTS(String weatherDT) {
        Date time = new Date(((Long.valueOf(weatherDT) - 7200) * 1000L));
        return time;
    }


    public static void startNewActivity(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }


}
