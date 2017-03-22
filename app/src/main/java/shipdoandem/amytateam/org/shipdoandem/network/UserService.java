package shipdoandem.amytateam.org.shipdoandem.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import shipdoandem.amytateam.org.shipdoandem.databases.models.FoodRespon;
import shipdoandem.amytateam.org.shipdoandem.databases.models.OrderFoodRespon;
import shipdoandem.amytateam.org.shipdoandem.databases.models.UserRespon;

/**
 * Created by hieutran on 3/22/17.
 */

public interface UserService {

    @GET("userinfo/{userInfo_id}")
    Call<UserRespon> getUserInfo(@Path("userInfo_id") String userId);

    @POST("userinfo")
    Call<UserRespon> postUserInfo( @Body UserRespon body);
}
