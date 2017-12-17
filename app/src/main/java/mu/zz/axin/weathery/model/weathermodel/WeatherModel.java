
package mu.zz.axin.weathery.model.weathermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WeatherModel {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cnt")
    @Expose
    private String cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<mu.zz.axin.weathery.model.weathermodel.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public java.util.List<mu.zz.axin.weathery.model.weathermodel.List> getList() {
        return list;
    }

    public void setList(java.util.List<mu.zz.axin.weathery.model.weathermodel.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "cod=" + cod +
                ", message=" + message +
                ", cnt=" + cnt +
                ", list=" + list +
                ", city=" + city +
                "}";
    }

}
