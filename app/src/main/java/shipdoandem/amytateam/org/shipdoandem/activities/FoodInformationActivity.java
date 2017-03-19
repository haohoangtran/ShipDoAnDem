package shipdoandem.amytateam.org.shipdoandem.activities;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SentFoodEvent;
import shipdoandem.amytateam.org.shipdoandem.utils.Utils;

public class FoodInformationActivity extends AppCompatActivity {
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

    int visiable = View.INVISIBLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_information);
        setupUI();
    }

    private void setupUI() {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);

        tvName.setText(food.getName());

        Utils.setTitleActionBar(this, tvName.getText().toString());

        Picasso.with(this).load(food.getUrl()).into(ivFood);
        tvPriceNew.setText(food.getPriceNew()+" VND ");
        tvPriceOld.setText(food.getPriceOld()+" VND");
        tvPriceOld.setPaintFlags(tvPriceOld.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        tvPercent.setText(food.getPercent()+"%");

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
    }

    public int setFavorite(){
        if (visiable == View.INVISIBLE) {
            visiable = View.VISIBLE;
        }else {
            visiable = View.INVISIBLE;
        }
        return visiable;
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    void receiveFood(SentFoodEvent sentFood){
        this.food = sentFood.getFood();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
