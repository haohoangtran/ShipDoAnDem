package shipdoandem.amytateam.org.shipdoandem.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import shipdoandem.amytateam.org.shipdoandem.R;
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
    public void onBindViewHolder(FoodInCartViewHolder holder, final int position) {
        final Food food = DbContext.getInstance().allFoodsInCart().get(position);

        holder.getBtIncrease().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(food.getQuantityInCart() < 10) {
                    Log.d(TAG, "onClick: ");
                    DbContext.getInstance().changeQuanlity(food, 1);
                    notifyItemChanged(position);
                }
            }
        });
        holder.getBtDecrease().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(food.getQuantityInCart() > 1) {
                    DbContext.getInstance().changeQuanlity(food, -1);
                    notifyItemChanged(position);
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
        return DbContext.getInstance().allFoodsInCart().size();
    }
}
