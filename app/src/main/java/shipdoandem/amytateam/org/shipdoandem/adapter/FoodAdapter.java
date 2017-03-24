package shipdoandem.amytateam.org.shipdoandem.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.activities.FoodInformationActivity;
import shipdoandem.amytateam.org.shipdoandem.activities.MainActivity;
import shipdoandem.amytateam.org.shipdoandem.adapter.viewholder.FoodViewHolder;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.databases.models.OrderFoodRespon;
import shipdoandem.amytateam.org.shipdoandem.evenbus.IncreaseCountCartEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.OnClickItemEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SentFood;
import shipdoandem.amytateam.org.shipdoandem.network.FoodService;
import shipdoandem.amytateam.org.shipdoandem.network.NetContext;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by DUC THANG on 3/16/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {
    private Context context;
    private Context context1;

    Dialog dialogBuy;
    ImageButton ibIncrease;
    ImageButton ibDecrease;
    Button ibBuy;
    Button ibCancel;
    TextView tvSl;
    int count = 1;
    final Date today = Calendar.getInstance().getTime();

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
        context1 = parent.getContext();
        dialogBuy = new Dialog(parent.getContext());
        dialogBuy.setContentView(R.layout.content_buy);
        dialogBuy.setTitle("Đặt hàng");
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
                    order(food);
                }
            }
        });
        if(DbContext.instance.findFood(food) == null){
            holder.getBtAddToCart().setText("Đặt hàng");
        }else {
            holder.getBtAddToCart().setText("Đã đặt");

        }
    }

    @Override
    public int getItemCount() {
        return DbContext.instance.allFoods().size();
    }

    public void order(final Food food){

        dialogBuy.show();
        ibDecrease = (ImageButton) dialogBuy.findViewById(R.id.btn_giam_sl);
        ibIncrease = (ImageButton) dialogBuy.findViewById(R.id.btn_tang_sl);
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
                tvSl.setText(count + "");

            }
        });

        ibDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 30) {
                    Toast.makeText(context1, "Vượt quá số lượng cho phép !", Toast.LENGTH_SHORT).show();
                }else {
                    count++;
                    tvSl.setText(count + "");
                }

            }
        });

        ibBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().post(new IncreaseCountCartEvent(food,count));
                food.setQuantityInCart(count);
                DbContext.instance.addOrUpdate(food);
                Log.d(TAG, String.format("onClick: %s", DbContext.instance.findFood(food) == null));
                Toast.makeText(context1, "Đặt hàng thành công !", Toast.LENGTH_SHORT).show();
                dialogBuy.dismiss();


//                OrderFoodRespon orderFoodRespon = new OrderFoodRespon("Hieukaka", "123abcdef",
//                        food.getName(), today.toString(), food.getRate(), count);
//                FoodService foodService = NetContext.instance.create(FoodService.class);
//                foodService.addOrderFood(orderFoodRespon).enqueue(new Callback<OrderFoodRespon>() {
//                    @Override
//                    public void onResponse(Call<OrderFoodRespon> call, Response<OrderFoodRespon> response) {
//                        Log.d(FoodInformationActivity.class.toString(), String.format("onResponse: %s", response.body().toString()));
//                        Toast.makeText(context, "Đặt hàng thành công !", Toast.LENGTH_SHORT).show();
//                        dialogBuy.dismiss();
//                    }
//
//                    @Override
//                    public void onFailure(Call<OrderFoodRespon> call, Throwable t) {
//
//                    }
//                });
            }
        });

        ibCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuy.dismiss();
            }
        });
    }
}
