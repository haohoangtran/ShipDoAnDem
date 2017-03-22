package shipdoandem.amytateam.org.shipdoandem;

import android.app.Application;

import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;

/**
 * Created by ToanTV on 3/22/2017.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbContext.setInstance(this);
        SharePref.setInstance(this);
        SharePref.instance.getSharedPreferences().edit().putInt("COUNT", 0).commit();
    }
}
