
package mu.zz.axin.weathery.model.weathermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain {

    @SerializedName("3h")
    @Expose
    private String _3h;

    public String get3h() {
        return _3h;
    }

    public void set3h(String _3h) {
        this._3h = _3h;
    }

    @Override
    public String toString() {
        return "Rain{" +
                "_3h=" + _3h +
                "}";
    }

}
