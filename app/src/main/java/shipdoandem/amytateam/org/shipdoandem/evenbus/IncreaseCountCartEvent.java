package shipdoandem.amytateam.org.shipdoandem.evenbus;

import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;

/**
 * Created by ToanTV on 3/18/2017.
 */

public class IncreaseCountCartEvent {
    private Food food;

    public IncreaseCountCartEvent(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }
}
