package shipdoandem.amytateam.org.shipdoandem.activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SentFood;
import shipdoandem.amytateam.org.shipdoandem.utils.Utils;

public class FoodInformationActivity extends AppCompatActivity {
    Dialog dialogBuy;
    String[] sl = new String[]{
              "1", "2", "3", "4", "5", "6", "7", "8", "9","10","11","12","13","14","15","16"
    };
    int count = 1;
    @BindView(R.id.iv_food_ift)
    ImageView ivFood;
    @BindView(R.id.tv_name_food)
    TextView tvName;
    @BindView(R.id.tv_price_new_food)
    TextView tvPriceNew;
    @BindView(R.id.tv_price_old_food)
    TextView tvPriceOld;
    @BindView(R.id.tv_percent_food)
    TextView tvPercent;
    @BindView(R.id.rb_food)
    RatingBar rbFood;
    private Food food;
    @BindView(R.id.ib_favorite_while)
    ImageButton ibFavoriteWhile;
    @BindView(R.id.ib_favorite_black)
    ImageButton ibFavoriteBlack;
    @BindView(R.id.ib_share)
    ImageButton ibShare;
    ImageButton ibIncrease;
    ImageButton ibDecrease;
    Button ibBuy;
    Button ibCancel;

    @BindView(R.id.btn_buy)
    Button btnBuy;
    TextView tvSl;
    int visiable = View.INVISIBLE;
    private Context context;


    public int setFavorite() {
        if (visiable == View.INVISIBLE) {
            visiable = View.VISIBLE;
        } else {
            visiable = View.INVISIBLE;
        }
        return visiable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_information);

        EventBus.getDefault().register(this);
        ButterKnife.bind(this);

        dialogBuy = new Dialog(this);
        dialogBuy.setContentView(R.layout.content_buy);
        dialogBuy.setTitle("Đặt hàng");

        context = this;
        Picasso.with(this).load(food.getUrl()).into(ivFood);
        tvName.setText(food.getName());
        Utils.setTitleActionBar(this, tvName.getText().toString());
        tvPriceNew.setText(food.getPriceNew() + " VND ");
        tvPriceOld.setText(food.getPriceOld() + " VND");
        tvPriceOld.setPaintFlags(tvPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvPercent.setText(food.getPercent() + "%");
        rbFood.setRating(food.getRate());
        ibFavoriteWhile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibFavoriteBlack.setVisibility(View.VISIBLE);
                ibFavoriteWhile.setVisibility(View.INVISIBLE);
            }
        });

        ibFavoriteBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibFavoriteWhile.setVisibility(View.VISIBLE);
                ibFavoriteBlack.setVisibility(View.INVISIBLE);
            }
        });


        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuy.show();
                ibDecrease = (ImageButton) dialogBuy.findViewById(R.id.btn_giam_sl);
                ibIncrease = (ImageButton) dialogBuy.findViewById(R.id.btn_tang_sl);

//                ibBuy = (ImageButton) dialogBuy.findViewById(R.id.btn_buy);
//                ibCancel = (ImageButton) dialogBuy.findViewById(R.id.btn_cancel);
                tvSl = (TextView) dialogBuy.findViewById(R.id.tv_sl_food);
                tvSl.setText(count + "");
                ibBuy = (Button) dialogBuy.findViewById(R.id.btn_buy_food);

                ibCancel = (Button) dialogBuy.findViewById(R.id.btn_cancel_food);

                ibIncrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (count > 0) {
                            count--;
                        }
                        Log.d("TAG", String.format("onClick: %s", count));
                        tvSl.setText(count+"");

                    }
                });

                ibDecrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (count >= 0&&count<30) {
                            count++;
                        }else {
                            Toast.makeText(context,"Vượt quá số lượng",Toast.LENGTH_SHORT);
                        }
                        tvSl.setText(count+"");

                    }
                });
                ibBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                ibCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuy.dismiss();
                    }
                });

            }
        });


    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    void receiveFood(SentFood sentFood) {
        this.food = sentFood.getFood();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


//    public void replace(Fragment fragment, boolean addToBackstack) {
//        if (addToBackstack) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fl_main, fragment)
//                    .addToBackStack(null)
//                    .commit();
//        }else {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fl_main, fragment)
//                    .commit();
//        }
//    }

}
