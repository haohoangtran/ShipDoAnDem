package shipdoandem.amytateam.org.shipdoandem.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;

/**
 * Created by DUC THANG on 3/16/2017.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = FoodViewHolder.class.toString();
    @BindView(R.id.iv_food)
    ImageView ivfood;

    @BindView(R.id.tv_price)
    TextView price;

    @BindView(R.id.bt_add_to_cart)
    Button btAddToCart;
    //adapter dau?

    public ImageView getivFood() {
        return ivfood;
    }

    public TextView getPrice() {
        return price;
    }

    public Button getBtAddToCart() {
        return btAddToCart;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);
        ivfood= (ImageView) itemView.findViewById(R.id.iv_food);
        price= (TextView) itemView.findViewById(R.id.tv_price);
        btAddToCart = (Button) itemView.findViewById(R.id.bt_add_to_cart);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Food food) {
        Log.e(TAG, String.format("bind: %s", food));
        price.setText(String.format("%s", food.getPrice()));//int
        ivfood.setBackgroundResource(R.drawable.logo);// chac thang food null
    }
}
