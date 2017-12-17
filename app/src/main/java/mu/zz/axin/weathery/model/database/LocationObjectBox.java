package mu.zz.axin.weathery.model.database;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;


@Entity
public class LocationObjectBox {
    @Id
    long id;
    String City;
    String Country;
    String Latitude;
    String Longitude;

    public LocationObjectBox(long id, String city, String country, String latitude, String longitude) {

        this.id = id;
        City = city;
        Country = country;
        Latitude = latitude;
        Longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }
}
