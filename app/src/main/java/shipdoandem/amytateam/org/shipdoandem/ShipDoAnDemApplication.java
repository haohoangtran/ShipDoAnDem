package shipdoandem.amytateam.org.shipdoandem;

import android.app.Application;

import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;

/**
 * Created by ToanTV on 3/19/2017.
 */

public class ShipDoAnDemApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbContext.setInstance(this);
    }
}
