package shipdoandem.amytateam.org.shipdoandem.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import shipdoandem.amytateam.org.shipdoandem.databases.models.FoodRespon;
import shipdoandem.amytateam.org.shipdoandem.databases.models.OrderFoodRespon;
import shipdoandem.amytateam.org.shipdoandem.databases.models.UserRegisterRespon;
import shipdoandem.amytateam.org.shipdoandem.databases.models.UserUpdate;

/**
 * Created by hieutran on 3/22/17.
 */

public interface UserService {

    @GET("user/{id}")
    Call<UserUpdate> getUserInfo(@Path("id") String userId);

    @POST("user")
    Call<UserRegisterRespon> postUserRegister(@Body UserRegisterRespon body);

    @PUT("user/{id}")
    Call<UserUpdate> putUserUpdate(@Path("id") String id,@Body UserUpdate body);
}
