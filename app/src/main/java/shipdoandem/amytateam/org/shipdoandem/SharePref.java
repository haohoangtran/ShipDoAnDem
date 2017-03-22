package shipdoandem.amytateam.org.shipdoandem;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ToanTV on 3/20/2017.
 */

public class SharePref {
    private final String KEY_PREF = "SP";
    private final String COUNT = "COUNT";
    private SharedPreferences sharedPreferences;
    private SharePref(Context context){
        sharedPreferences = context.getSharedPreferences(
                KEY_PREF, Context.MODE_PRIVATE
        );
    }
    public static SharePref instance;
    public static void setInstance(Context context){
        instance = new SharePref(context);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
    public int getCount(){
        return sharedPreferences.getInt(COUNT, 0);
    }
    public void setCount(int count){
        sharedPreferences.edit().putInt(COUNT, count).commit();
    }
}
