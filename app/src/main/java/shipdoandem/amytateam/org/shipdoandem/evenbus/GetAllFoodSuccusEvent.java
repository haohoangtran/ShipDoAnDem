package shipdoandem.amytateam.org.shipdoandem.evenbus;

import java.util.List;

import shipdoandem.amytateam.org.shipdoandem.databases.models.FoodRespon;

/**
 * Created by tranh on 3/18/2017.
 */

public class GetAllFoodSuccusEvent {
    private List<FoodRespon> foodRespons;

    public List<FoodRespon> getFoodRespons() {
        return foodRespons;
    }

    public GetAllFoodSuccusEvent(List<FoodRespon> foodRespons) {

        this.foodRespons = foodRespons;
    }
}
