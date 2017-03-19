package shipdoandem.amytateam.org.shipdoandem.databases;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.databases.models.FoodRespon;
import shipdoandem.amytateam.org.shipdoandem.evenbus.GetAllFoodFaileEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.GetAllFoodSuccusEvent;
import shipdoandem.amytateam.org.shipdoandem.network.FoodService;
import shipdoandem.amytateam.org.shipdoandem.network.NetContext;

/**
 * Created by DUC THANG on 3/16/2017.
 */

public class DbContext {
    private static final String TAG=DbContext.class.toString();
    public static final DbContext instance = new DbContext();
    private List<Food> foods;

    public DbContext() {
        foods = new Vector<>();
    }

    public List<Food> allFoods() {
        return foods;
    }

    public void getAllFood() {
        FoodService foodService = NetContext.instance.create(FoodService.class);
        foodService.getAllFood().enqueue(new Callback<List<FoodRespon>>() {
            @Override
            public void onResponse(Call<List<FoodRespon>> call, Response<List<FoodRespon>> response) {
                List<FoodRespon> foodRes=response.body();
                if (foodRes!=null) {
                    foods.clear();
                    for (int i = 0; i < foodRes.size(); i++) {
                        foods.add(new Food(foodRes.get(i)));
                    }
                    Log.e(TAG, "onResponse:load xong " );
                    EventBus.getDefault().post(new GetAllFoodSuccusEvent(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<FoodRespon>> call, Throwable t) {
                EventBus.getDefault().post(new GetAllFoodFaileEvent());
                Log.e(TAG, "onFailure: lỗi" );
            }
        });
    }
}
