package shipdoandem.amytateam.org.shipdoandem.evenbus;

/**
 * Created by hieutran on 3/22/17.
 */

public class SentUserIdEvent {
    private String id;

    public SentUserIdEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
