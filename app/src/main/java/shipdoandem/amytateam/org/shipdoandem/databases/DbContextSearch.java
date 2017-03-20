package shipdoandem.amytateam.org.shipdoandem.databases;

import java.util.List;
import java.util.Vector;

import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;

/**
 * Created by hieutran on 3/19/17.
 */

public class DbContextSearch {
    private static final String TAG=DbContext.class.toString();
    public static final DbContextSearch instance = new DbContextSearch();
    private List<Food> foods;

    public DbContextSearch() {
        foods = new Vector<>();
    }

    public List<Food> allFoods() {
        return foods;
    }

    public void addFood(Food food){
        this.foods.add(food);
    }

    public void clean(){
        this.foods.clear();
    }
}
