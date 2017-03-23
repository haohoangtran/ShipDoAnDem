package shipdoandem.amytateam.org.shipdoandem.evenbus;

import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;

/**
 * Created by ToanTV on 3/18/2017.
 */

public class IncreaseCountCartEvent {
    private Food food;
    private int count;

    public IncreaseCountCartEvent(Food food, int count) {
        this.food = food;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Food getFood() {
        return food;
    }
}
