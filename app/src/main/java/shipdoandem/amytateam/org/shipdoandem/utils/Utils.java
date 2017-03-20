package shipdoandem.amytateam.org.shipdoandem.utils;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import shipdoandem.amytateam.org.shipdoandem.R;

/**
 * Created by DUC THANG on 3/19/2017.
 */

public class Utils {
    public static void setTitleActionBar(AppCompatActivity activity, String title) {
        final ActionBar abar = activity.getSupportActionBar();
        View viewActionBar = activity.getLayoutInflater().inflate(R.layout.custom_actionbar_layout, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.tv_title);
        textviewTitle.setText(title);
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayHomeAsUpEnabled(true);
    }
}
