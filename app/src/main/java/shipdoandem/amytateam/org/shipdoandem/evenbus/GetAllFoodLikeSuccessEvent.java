package shipdoandem.amytateam.org.shipdoandem.evenbus;

import java.util.List;

import shipdoandem.amytateam.org.shipdoandem.databases.models.FoodRespon;

/**
 * Created by DUC THANG on 3/26/2017.
 */

public class GetAllFoodLikeSuccessEvent {
    private List<FoodRespon> foodRespons;

    public List<FoodRespon> getFoodRespons() {
        return foodRespons;
    }

    public GetAllFoodLikeSuccessEvent(List<FoodRespon> foodRespons) {

        this.foodRespons = foodRespons;
    }
}
