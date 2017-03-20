package shipdoandem.amytateam.org.shipdoandem.databases;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Vector;

import io.realm.Realm;
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
    private Realm realm;
    private static final String TAG=DbContext.class.toString();
    private static DbContext instance;
    public static DbContext getInstance(){
        return instance;
    }
    public static void setInstance(Context context){
        instance = new DbContext(context);
    }
    public List<Food> allFoodsInCart(){
        return realm.where(Food.class).findAll();
    }
    private List<Food> foods;
    private DbContext(Context context) {
        foods = new Vector<>();
        Realm.init(context);
        realm = Realm.getDefaultInstance();
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
                if (foodRes != null) {
                    for (int i = 0; i < foodRes.size(); i++) {
                        foods.add(new Food(foodRes.get(i)));
                    }
                    Log.e(TAG, "onResponse:load xong ");
                    EventBus.getDefault().post(new GetAllFoodSuccusEvent(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<FoodRespon>> call, Throwable t) {
                EventBus.getDefault().post(new GetAllFoodFaileEvent());
                Log.e(TAG, String.format("onFailure: lỗi %s", t.toString()) );
            }
        });
    }
    public void addOrUpdate(Food food){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(food);
        realm.commitTransaction();
    }

    public void deleteAll(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public void delete(Food food){
        realm.beginTransaction();
        realm.where(Food.class).equalTo("id",food.getId()).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }
    public void changeQuanlity(Food food, int a){

        realm.beginTransaction();
        food.setQuantityInCart(food.getQuantityInCart() + a);
        realm.copyToRealmOrUpdate(food);
        realm.commitTransaction();
    }
}
