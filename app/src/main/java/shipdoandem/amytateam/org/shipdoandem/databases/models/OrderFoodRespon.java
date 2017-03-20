package shipdoandem.amytateam.org.shipdoandem.databases.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hieutran on 3/20/17.
 */

public class OrderFoodRespon {
    @SerializedName("username")
    private String userName;
    @SerializedName("userid")
    private String userId;
     @SerializedName("food_name")
    private String foodName;
     @SerializedName("orderdate")
    private String orderDate;
     @SerializedName("rate")
    private float rate;
    @SerializedName("food_number")
    private int foodNumber;
    @SerializedName("_id")
    private FoodRespon.Id Id;

    public class Id {
        @SerializedName("$oid")
        public String $oid;
    }

    public OrderFoodRespon(String userName, String userId, String foodName, String orderDate, float rate, int foodNumber) {
        this.userName = userName;
        this.userId = userId;
        this.foodName = foodName;
        this.orderDate = orderDate;
        this.rate = rate;
        this.foodNumber = foodNumber;
    }

    public FoodRespon.Id getId() {
        return Id;
    }

    public void setId(FoodRespon.Id id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(int foodNumber) {
        this.foodNumber = foodNumber;
    }
}
