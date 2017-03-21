package shipdoandem.amytateam.org.shipdoandem.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import shipdoandem.amytateam.org.shipdoandem.databases.models.FoodRespon;
import shipdoandem.amytateam.org.shipdoandem.databases.models.OrderFoodRespon;

/**
 * Created by tranh on 3/18/2017.
 */

public interface FoodService {
    @GET("food")
    Call<List<FoodRespon>> getAllFood();

    @POST("foodRespon")
    Call<FoodRespon> addNewFood(@Body FoodRespon foodRespon);

    @POST("order/{userid}")
    Call<FoodRespon> addOrderListFood(@Path("userid") String userId, @Body List<OrderFoodRespon> body);

    @POST("order")
    Call<OrderFoodRespon> addOrderFood(@Body OrderFoodRespon body);

}
