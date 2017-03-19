package shipdoandem.amytateam.org.shipdoandem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.adapter.viewholder.FoodViewHolder;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.databases.models.FoodRespon;

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
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        final Food food = DbContext.instance.allFoods().get(position);
        holder.bind(food);
    }

    @Override
    public int getItemCount() {
        return DbContext.instance.allFoods().size();
    }
}
