package mu.zz.axin.weathery;

import android.app.Activity;
import android.app.Application;

import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import mu.zz.axin.weathery.model.database.MyObjectBox;


public class App extends Application {


    private BoxStore boxStore;

    public static void setPreferences(Activity activity, String preferencesTag, String preferencesValue) {
        SharedPreferencesManager.getInstance().putValue(preferencesTag, preferencesValue);
    }

    public static void setPreferences(Activity activity, String preferencesTag, boolean preferencesValue) {
        SharedPreferencesManager.getInstance().putValue(preferencesTag, preferencesValue);
    }

    public static String getPreferences(Activity activity, String preferencesTag, String defaultValue) {
        return SharedPreferencesManager.getInstance().getValue(preferencesTag, String.class, defaultValue);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(this);
        }
        SharedPreferencesManager.init(this, true);
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
