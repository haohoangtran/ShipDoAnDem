package shipdoandem.amytateam.org.shipdoandem;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ToanTV on 3/20/2017.
 */

public class SharePref {
    private SharedPreferences sharedPreferences;
    private SharePref(Context context){
        sharedPreferences = context.getSharedPreferences(
                "SP", Context.MODE_PRIVATE
        );
    }
    public static SharePref instance;
    public static void setInstance(Context context){
        instance = new SharePref(context);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
