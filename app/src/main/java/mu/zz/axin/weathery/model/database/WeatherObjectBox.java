package mu.zz.axin.weathery.model.database;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;


@Entity
public class WeatherObjectBox implements Serializable {

    @Id
    long id;
    String temperature;
    String humidity;
    String wind;
    String pressure;
    String description;
    String dateTime;
    String icon;

    public WeatherObjectBox(long id, String temperature, String humidity, String wind, String pressure, String description, String dateTime, String icon) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.pressure = pressure;
        this.description = description;
        this.dateTime = dateTime;
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
