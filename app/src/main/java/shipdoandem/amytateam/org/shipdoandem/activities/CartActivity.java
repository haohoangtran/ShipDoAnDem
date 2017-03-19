package shipdoandem.amytateam.org.shipdoandem.activities;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.adapter.FoodInCartAdapter;
import shipdoandem.amytateam.org.shipdoandem.adapter.viewholder.FoodInCartViewHolder;

public class CartActivity extends AppCompatActivity {
    ActionBarDrawerToggle toggle;
    @BindView(R.id.rv_food_in_cart)
    RecyclerView rvFood;
    private FoodInCartAdapter adapter = new FoodInCartAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setupUI();
        getSupportActionBar().setTitle("Giỏ hàng");
    }

    private void setupUI() {
        ButterKnife.bind(this);
        rvFood.setAdapter(adapter);
        rvFood.setLayoutManager(new LinearLayoutManager(this));
    }

}
