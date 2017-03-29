package shipdoandem.amytateam.org.shipdoandem.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.SharePref;
import shipdoandem.amytateam.org.shipdoandem.adapter.FoodInCartAdapter;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SendRequestEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.TypeRequestEvent;
import shipdoandem.amytateam.org.shipdoandem.utils.Utils;

public class CartActivity extends AppCompatActivity {
    private final String TAG = "CartActivity";
    @BindView(R.id.rv_food_in_cart)
    RecyclerView rvFood;
    private FoodInCartAdapter adapter = new FoodInCartAdapter();
    @BindView(R.id.tv_payment)
    TextView tvPayment;
    @BindView(R.id.bt_payment)
    Button btPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24px));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setupUI();
        Utils.setTitleActionBar(this, "Giỏ Hàng");
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
        tvPayment.setText(displayPayment()+" VND");
    }

    @Subscribe
    public void deleteListener(final SendRequestEvent event) {
        if (event.getTypeRequest() == TypeRequestEvent.DELETE) {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Xóa");
            dialog.setMessage("Bạn chắc chắn chứ?");
            dialog.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int count = SharePref.instance.getCount();
                    count -= event.getFood().getQuantityInCart();
                    SharePref.instance.setCount(count);
                    DbContext.instance.delete(event.getFood());
                    adapter.notifyDataSetChanged();
                    EventBus.getDefault().post(new SendRequestEvent(TypeRequestEvent.CHANGE_BT_ADDTOCART,
                            event.getFood()));
                    EventBus.getDefault().post(new SendRequestEvent(TypeRequestEvent.CHANGE_PAYMENT));

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

    public long payment() {
        long s = 0;
        for (Food food : DbContext.instance.allFoodsInCart()) {
            s += Long.parseLong(food.getPriceNew()) * food.getQuantityInCart();
        }
        return s;
    }

    public String displayPayment() {
        String s = new String(String.valueOf(payment()));
        int n = s.length();
        int temp = n;
        Vector<String> sub = new Vector<>();
        for (int i = 3; ; i += 3) {
            if(n-i >=0) {
                sub.add(s.substring(n-i,temp));
                Log.d(TAG, String.format("displayPayment: %s", s.substring(n - i,temp)));
            }
            else{
                sub.add(s.substring(0,temp));
                break;
            }
            temp -= 3;
            if(temp == 0) break;
        }
        s = "";
        for(int i = sub.size()-1 ; i>=0;i--){
            if(i!=sub.size()-1){
                s+=".";
            }
            s+=sub.get(i);
        }
        return s;
    }

    @Subscribe
    public void setPayment(SendRequestEvent event) {
        if (event.getTypeRequest() == TypeRequestEvent.CHANGE_PAYMENT) {
            Log.d(TAG, "setPayment: hi");
            tvPayment.setText(displayPayment()+" VND");
        }
    }
}
