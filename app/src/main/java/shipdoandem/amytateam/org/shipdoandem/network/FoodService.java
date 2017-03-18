package shipdoandem.amytateam.org.shipdoandem.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import shipdoandem.amytateam.org.shipdoandem.databases.models.FoodRespon;

/**
 * Created by tranh on 3/18/2017.
 */

public interface FoodService {
    @GET("food")
    Call<List<FoodRespon>> getAllFood();

    @POST("foodRespon")
    Call<FoodRespon> addNewFood(@Body FoodRespon foodRespon);

}
