package mu.zz.axin.weathery.ui.settings;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import mu.zz.axin.weathery.App;
import mu.zz.axin.weathery.R;
import mu.zz.axin.weathery.model.Environment;
import mu.zz.axin.weathery.model.api.NetworkFetcher;
import mu.zz.axin.weathery.permissions.MultiplePermissionListener;
import mu.zz.axin.weathery.permissions.PermissionErrorListener;

import static mu.zz.axin.weathery.model.Environment.WEATHER_UNITS;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private static String WEATHER_UNITS_METRIC = "metric";
    private static String WEATHER_UNITS_IMPERIAL = "imperial";
    private static String CURRENT_LOCATION_LATITUDE = Environment.LOCATION_LATITUDE;
    private static String CURRENT_LOCATION_LONGITUDE = Environment.LOCATION_LONGITUDE;
    private static String TAG = Environment.LOG_TAG_SETTINGS_ACTIVITY;
    private static String GET_CITY = "GetCity";
    Toolbar toolbar;
    EditText enterCityEditText;
    RadioButton metricRadioButton, imperialRadioButton, setCityRadioButton, locationRadioButton;
    private GoogleApiClient mGoogleApiClient;
    private MultiplePermissionsListener allPermissionsListener;
    private PermissionRequestErrorListener errorListener;
    private View contentView;
    private FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.d(TAG, App.getPreferences(this, WEATHER_UNITS, "default"));
        initToolbar();
        initView();
        Log.d(TAG, SharedPreferencesManager.getInstance().getValue(WEATHER_UNITS, String.class) + " " + WEATHER_UNITS_METRIC);
        initRadioButtonState();
        createPermissionListeners();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(allPermissionsListener)
                .withErrorListener(errorListener)
                .check();

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }

    private void setOnClick() {
        metricRadioButton.setOnClickListener(this);
        imperialRadioButton.setOnClickListener(this);
        setCityRadioButton.setOnClickListener(this);
        locationRadioButton.setOnClickListener(this);
    }

    private void initView() {
        enterCityEditText = findViewById(R.id.enterCityEditText);
        metricRadioButton = findViewById(R.id.metricRadioButton);
        imperialRadioButton = findViewById(R.id.imperialRadioButton);
        setCityRadioButton = findViewById(R.id.setCityRadioButton);
        locationRadioButton = findViewById(R.id.locationRadioButton);
        contentView = findViewById(android.R.id.content);
        setOnClick();
    }

    private void createPermissionListeners() {
        MultiplePermissionsListener feedbackViewMultiplePermissionListener =
                new MultiplePermissionListener(this);

        allPermissionsListener =
                new CompositeMultiplePermissionsListener(feedbackViewMultiplePermissionListener,
                        SnackbarOnAnyDeniedMultiplePermissionsListener.Builder.with(contentView,
                                "All those permissions are needed for doing some magic")
                                .withOpenSettingsButton("SETTINGS")
                                .build());
        errorListener = new PermissionErrorListener();
    }

    private void initRadioButtonState() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            metricRadioButton.setChecked(Objects.equals(SharedPreferencesManager.getInstance().getValue(WEATHER_UNITS, String.class), WEATHER_UNITS_METRIC));
            imperialRadioButton.setChecked(Objects.equals(SharedPreferencesManager.getInstance().getValue(WEATHER_UNITS, String.class), WEATHER_UNITS_IMPERIAL));
        } else {
            imperialRadioButton.setChecked(SharedPreferencesManager.getInstance().getValue(WEATHER_UNITS, String.class) == WEATHER_UNITS_IMPERIAL);
            imperialRadioButton.setChecked(SharedPreferencesManager.getInstance().getValue(WEATHER_UNITS, String.class) == WEATHER_UNITS_IMPERIAL);
        }
        setCityRadioButton.setChecked(!(SharedPreferencesManager.getInstance().getValue(GET_CITY, Boolean.class)));
        locationRadioButton.setChecked(SharedPreferencesManager.getInstance().getValue(GET_CITY, Boolean.class));
        Log.d(TAG, SharedPreferencesManager.getInstance().getValue(WEATHER_UNITS, String.class) + " " + WEATHER_UNITS_IMPERIAL);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.metricRadioButton:
                App.setPreferences(this, WEATHER_UNITS, WEATHER_UNITS_METRIC);
                NetworkFetcher.getWeatherFromNetwork(getApplication(), App.getPreferences(this, WEATHER_UNITS, "default"),
                        Double.valueOf(App.getPreferences(this, Environment.LOCATION_LATITUDE, "default")),
                        Double.valueOf(App.getPreferences(this, Environment.LOCATION_LONGITUDE, "default")));

                Log.d(TAG, WEATHER_UNITS_METRIC + " " + App.getPreferences(this, Environment.LOCATION_LATITUDE, "default")
                        + " " + App.getPreferences(this, Environment.LOCATION_LONGITUDE, "default"));
                break;
            case R.id.imperialRadioButton:
                App.setPreferences(this, WEATHER_UNITS, WEATHER_UNITS_IMPERIAL);
                NetworkFetcher.getWeatherFromNetwork(getApplication(), App.getPreferences(this, WEATHER_UNITS, "default"),
                        Double.valueOf(App.getPreferences(this, Environment.LOCATION_LATITUDE, "default")),
                        Double.valueOf(App.getPreferences(this, Environment.LOCATION_LONGITUDE, "default")));
                Log.d(TAG, WEATHER_UNITS_METRIC + " " + App.getPreferences(this, Environment.LOCATION_LATITUDE, "default")
                        + " " + App.getPreferences(this, Environment.LOCATION_LONGITUDE, "default"));
                break;
            case R.id.setCityRadioButton:
                App.setPreferences(getParent(), GET_CITY, false);
                break;
            case R.id.locationRadioButton:
                App.setPreferences(getParent(), GET_CITY, true);
                Log.d(TAG, "locationRadioButton");
                mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            Log.d(TAG, String.valueOf(latitude) + String.valueOf(longitude));
                            App.setPreferences(getParent(), CURRENT_LOCATION_LATITUDE, String.valueOf(latitude));
                            App.setPreferences(getParent(), CURRENT_LOCATION_LONGITUDE, String.valueOf(longitude));
                            try {
                                getCountryAndCity(latitude, longitude);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                break;


        }
    }

    private void getCountryAndCity(double latitude, double longitude) throws IOException {
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses = gcd.getFromLocation(latitude, longitude, 1);
        if (addresses.size() > 0) {
            Log.d(TAG, addresses.get(0).getLocality());
            Log.d(TAG, addresses.get(0).getCountryName());
            App.setPreferences(this, Environment.CITY_NAME, addresses.get(0).getLocality());
            App.setPreferences(this, Environment.COUNTRY_NAME, addresses.get(0).getCountryName());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, App.getPreferences(this, WEATHER_UNITS, "default"));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void showPermissionGranted(String permissionName) {
        Log.d(TAG, "Granted" + " " + permissionName);
        setClickableBtn(true);

    }

    public void showPermissionDenied(String permissionName, boolean permanentlyDenied) {
        Log.d(TAG, "Denied");
        setClickableBtn(false);
    }

    private void setClickableBtn(boolean focusable) {
        enterCityEditText.setFocusable(focusable);
        setCityRadioButton.setFocusable(focusable);
        locationRadioButton.setFocusable(focusable);
        enterCityEditText.setClickable(focusable);
        setCityRadioButton.setClickable(focusable);
        locationRadioButton.setClickable(focusable);
    }

    public void showPermissionRationale(final PermissionToken token) {
        new AlertDialog.Builder(this).setTitle("We need this permission")
                .setMessage("This permission is needed for doing some fancy stuff so please, allow it!")
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.cancelPermissionRequest();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.continuePermissionRequest();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        token.cancelPermissionRequest();
                    }
                })
                .show();
    }

}
