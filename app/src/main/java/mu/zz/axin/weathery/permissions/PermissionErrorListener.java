package mu.zz.axin.weathery.permissions;

import android.util.Log;

import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequestErrorListener;

public class PermissionErrorListener implements PermissionRequestErrorListener {
    @Override
    public void onError(DexterError error) {
        Log.e("Dexter", "There was an error: " + error.toString());
    }
}
