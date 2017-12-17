package mu.zz.axin.weathery.ui.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import mu.zz.axin.weathery.App;
import mu.zz.axin.weathery.R;
import mu.zz.axin.weathery.Util;
import mu.zz.axin.weathery.model.database.WeatherObjectBox;
import mu.zz.axin.weathery.model.database.WeatherObjectBox_;
import mu.zz.axin.weathery.ui.settings.SettingsActivity;


public abstract class BaseActivity extends AppCompatActivity {
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        configureToolbar();
    }

    protected abstract int getLayoutResource();

    protected abstract boolean showHomeButton();

    private void configureToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeButton());
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        }
    }

    protected java.util.List<WeatherObjectBox> getWeatherListFromDatabase() {
        BoxStore boxStore = ((App) getApplication()).getBoxStore();
        Box<WeatherObjectBox> mWeatherObjectBox = boxStore.boxFor(WeatherObjectBox.class);
        Query<WeatherObjectBox> weatherQuery = mWeatherObjectBox.query().order(WeatherObjectBox_.dateTime).build();
        return weatherQuery.find();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getSupportFragmentManager();
                if (fm != null && fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    finish();
                }
                return true;
            case R.id.menuSettings:
                Util.startNewActivity(this, SettingsActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
