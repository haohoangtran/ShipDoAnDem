package shipdoandem.amytateam.org.shipdoandem.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.SharePref;
import shipdoandem.amytateam.org.shipdoandem.adapter.FoodInCartAdapter;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SendRequestEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.TypeRequestEvent;

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

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void setupUI() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        rvFood.setAdapter(adapter);
        rvFood.setLayoutManager(new LinearLayoutManager(this));
    }
    @Subscribe
    public void deleteListener(final SendRequestEvent event){
        if(event.getTypeRequest() == TypeRequestEvent.DELETE){
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Xóa");
            dialog.setMessage("Bạn chắc chắn chứ?");
            dialog.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int count = SharePref.instance.getSharedPreferences().getInt("COUNT", 0);
                    count -= event.getFood().getQuantityInCart();
                    SharePref.instance.getSharedPreferences().edit().putInt("COUNT",count).commit();
                    DbContext.getInstance().delete(event.getFood());
                    adapter.notifyDataSetChanged();
                }
            });
            dialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            dialog.show();
        }
    }
}
