package shipdoandem.amytateam.org.shipdoandem.adapter.viewholder;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.databases.models.FoodRespon;
import shipdoandem.amytateam.org.shipdoandem.utils.Utils;

/**
 * Created by DUC THANG on 3/16/2017.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = FoodViewHolder.class.toString();
    @BindView(R.id.iv_food)
    ImageView ivfood;
    @BindView(R.id.tv_price)
    TextView price;
    @BindView(R.id.tv_price_old)
    TextView priceOld;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_count_rate)
    TextView tvCountRate;
    @BindView(R.id.rt_rate)
    RatingBar rtRate;
    @BindView(R.id.tv_percent)
    TextView tvPercent;
    @BindView(R.id.bt_add_to_cart)
    Button btAddToCart;

    public Button getBtAddToCart() {
        return btAddToCart;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Food food) {
        if (DbContext.instance.findFood(food) == null) {
            btAddToCart.setText("Đặt hàng");
        } else {
            btAddToCart.setText("Đã thêm");
        }
        if (food.getPercent() != 0) {
            priceOld.setVisibility(View.VISIBLE);
            tvPercent.setVisibility(View.VISIBLE);
            Log.e(TAG, String.format("bind: %s", food));
            Picasso.with(itemView.getContext()).load(food.getUrl()).into(ivfood);
            tvCountRate.setText((String.format("(%s nhận xét)", food.getCoutRate())));
            tvName.setText(food.getName());
            tvPercent.setText(String.format("%s", food.getPercent()) + "%");
            price.setText(String.format("Giá KM: %s", Utils.getPrice(food.getPriceNew())));
            priceOld.setText(String.format("Giá cũ: %s", Utils.getPrice(food.getPriceOld())));
            priceOld.setPaintFlags(priceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            rtRate.setRating(food.getRate());
        } else {

            priceOld.setVisibility(View.INVISIBLE);
            tvPercent.setVisibility(View.INVISIBLE);
            Picasso.with(itemView.getContext()).load(food.getUrl()).into(ivfood);
            tvCountRate.setText((String.format("(%s nhận xét)", food.getCoutRate())));
            tvName.setText(food.getName());
            price.setText(String.format("Giá : %s", Utils.getPrice(food.getPriceNew())));
            rtRate.setRating(food.getRate());
        }
    }
}
