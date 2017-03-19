package shipdoandem.amytateam.org.shipdoandem.evenbus;

import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;

/**
 * Created by DUC THANG on 3/19/2017.
 */

public class SentFoodEvent {
    private Food food;

    public SentFoodEvent(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
