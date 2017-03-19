package shipdoandem.amytateam.org.shipdoandem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.adapter.viewholder.FoodInCartViewHolder;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;

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
    public void onBindViewHolder(FoodInCartViewHolder holder, int position) {
        Food food = DbContext.getInstance().allFoodsInCart().get(position);
        holder.bind(food);
    }

    @Override
    public int getItemCount() {
        return DbContext.getInstance().allFoodsInCart().size();
    }
}
