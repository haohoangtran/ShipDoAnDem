package shipdoandem.amytateam.org.shipdoandem.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.SharePref;
import shipdoandem.amytateam.org.shipdoandem.adapter.viewholder.FoodInCartViewHolder;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SendRequestEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.TypeRequestEvent;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by ToanTV on 3/19/2017.
 */

public class FoodInCartAdapter extends RecyclerView.Adapter<FoodInCartViewHolder> {

    @Override
    public FoodInCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.food_in_cart_item, parent, false);
        return new FoodInCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FoodInCartViewHolder holder, final int position) {
        final Food food = DbContext.instance.allFoodsInCart().get(position);

        holder.getBtIncrease().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (food.getQuantityInCart() < 10) {
                    Log.d(TAG, "onClick: ");
                    DbContext.instance.changeQuanlity(food, 1);
                    int quanlity = Integer.parseInt(holder.getTvCount().getText().toString()) + 1;
                    holder.getTvCount().setText("" + quanlity);
                    int count = SharePref.instance.getCount();
                    SharePref.instance.setCount(count + 1);
                }
            }
        });
        holder.getBtDecrease().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (food.getQuantityInCart() > 1) {
                    DbContext.instance.changeQuanlity(food, -1);
                    int quanlity = Integer.parseInt(holder.getTvCount().getText().toString()) - 1;
                    holder.getTvCount().setText("" + quanlity);
                    int count = SharePref.instance.getCount();
                    SharePref.instance.setCount(count - 1);
                }
            }
        });
        holder.getIvDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new SendRequestEvent(TypeRequestEvent.DELETE, food));
            }
        });
        holder.bind(food);
    }

    @Override
    public int getItemCount() {
        return DbContext.instance.allFoodsInCart().size();
    }
}
