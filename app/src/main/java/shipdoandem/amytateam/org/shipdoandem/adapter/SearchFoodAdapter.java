package shipdoandem.amytateam.org.shipdoandem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.adapter.viewholder.FoodViewHolder;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContextSearch;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.evenbus.OnClickItemEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.OnClickItemSearchEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SentFood;

/**
 * Created by hieutran on 3/19/17.
 */

public class SearchFoodAdapter extends RecyclerView.Adapter<FoodViewHolder>{
    private Context context;

    public SearchFoodAdapter(Context context) {
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
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        final Food food = DbContextSearch.instance.allFoods().get(position);
        holder.bind(food);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new SentFood(food));
                EventBus.getDefault().post(new OnClickItemSearchEvent());

            }
        });
    }

    @Override
    public int getItemCount() {
        return DbContextSearch.instance.allFoods().size();
    }
}
