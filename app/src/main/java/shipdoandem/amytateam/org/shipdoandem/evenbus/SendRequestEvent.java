package shipdoandem.amytateam.org.shipdoandem.evenbus;

import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;

/**
 * Created by ToanTV on 3/20/2017.
 */

public class SendRequestEvent {
    private TypeRequestEvent typeRequest;
    private Food food;

    public Food getFood() {
        return food;
    }

    public TypeRequestEvent getTypeRequest() {
        return typeRequest;
    }

    public SendRequestEvent(TypeRequestEvent typeRequest, Food food) {
        this.typeRequest = typeRequest;
        this.food = food;
    }

}
