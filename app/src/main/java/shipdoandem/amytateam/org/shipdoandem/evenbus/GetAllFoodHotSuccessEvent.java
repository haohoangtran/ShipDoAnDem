package shipdoandem.amytateam.org.shipdoandem.evenbus;

import java.util.List;

import shipdoandem.amytateam.org.shipdoandem.databases.models.FoodRespon;

/**
 * Created by DUC THANG on 3/26/2017.
 */

public class GetAllFoodHotSuccessEvent {
    private List<FoodRespon> foodRespons;

    public List<FoodRespon> getFoodRespons() {
        return foodRespons;
    }

    public GetAllFoodHotSuccessEvent(List<FoodRespon> foodRespons) {

        this.foodRespons = foodRespons;
    }
}
