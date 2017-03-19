package shipdoandem.amytateam.org.shipdoandem.evenbus;

import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;

/**
 * Created by Hieu It on 3/18/2017.
 */

public class SentFood {
    private Food food;

    public SentFood(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
