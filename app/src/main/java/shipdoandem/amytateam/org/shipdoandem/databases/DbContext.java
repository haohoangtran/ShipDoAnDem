package shipdoandem.amytateam.org.shipdoandem.databases;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;

/**
 * Created by DUC THANG on 3/16/2017.
 */

public class DbContext {
    public static final DbContext instance = new DbContext();
    public List<Food> allFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("100.000 đ"));
        foods.add(new Food("100.000 đ"));
        foods.add(new Food("100.000 đ"));
        foods.add(new Food("100.000 đ"));
        foods.add(new Food("100.000 đ"));
        foods.add(new Food("100.000 đ"));
        foods.add(new Food("100.000 đ"));
        foods.add(new Food("100.000 đ"));
        foods.add(new Food("100.000 đ"));

        return foods;
    }
}
