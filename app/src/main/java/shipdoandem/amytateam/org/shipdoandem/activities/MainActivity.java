package shipdoandem.amytateam.org.shipdoandem.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.adapter.FoodAdapter;
import shipdoandem.amytateam.org.shipdoandem.decorations.FoodListDeco;
import shipdoandem.amytateam.org.shipdoandem.utils.BottomNavigationHelper;
import shipdoandem.amytateam.org.shipdoandem.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    private TextView mTextMessage;
    @BindView(R.id.rv_food)
    RecyclerView rvFood;
    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationHelper.disableShiftMode(navigation);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar_layout);
        setupUI();
    }

    private void setupUI() {
        ButterKnife.bind(this);
        rvFood= (RecyclerView) findViewById(R.id.rv_food);
        foodAdapter = new FoodAdapter();
        rvFood.setAdapter(foodAdapter);
        rvFood.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
