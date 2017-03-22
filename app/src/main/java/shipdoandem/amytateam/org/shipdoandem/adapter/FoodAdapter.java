package shipdoandem.amytateam.org.shipdoandem.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.activities.FoodInformationActivity;
import shipdoandem.amytateam.org.shipdoandem.adapter.viewholder.FoodViewHolder;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.evenbus.IncreaseCountCartEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.OnClickItemEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SentFood;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by DUC THANG on 3/16/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {
    private Context context;

    public FoodAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {

        return context;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = android.view.LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_food, parent, false);
        FoodViewHolder foodViewHolder = new FoodViewHolder(itemView);
        return foodViewHolder;
    }

    @Override
    public void onBindViewHolder(final FoodViewHolder holder, int position) {
        final Food food = DbContext.instance.allFoods().get(position);
        holder.bind(food);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new SentFood(food));
                EventBus.getDefault().post(new OnClickItemEvent());
                Log.d("abcd", "onClick: ");

            }
        });
        holder.getBtAddToCart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DbContext.instance.findFood(food) == null) {
                    EventBus.getDefault().post(new IncreaseCountCartEvent(food));
                    food.setQuantityInCart(1);
                    holder.getBtAddToCart().setText("Đã thêm");
                    DbContext.instance.addOrUpdate(food);
                    Log.d(TAG, String.format("onClick: %s", DbContext.instance.findFood(food) == null));
                }
            }
        });
        if(DbContext.instance.findFood(food) == null){
            holder.getBtAddToCart().setText("Đặt hàng");
        }
    }

    @Override
    public int getItemCount() {
        return DbContext.instance.allFoods().size();
    }
}
