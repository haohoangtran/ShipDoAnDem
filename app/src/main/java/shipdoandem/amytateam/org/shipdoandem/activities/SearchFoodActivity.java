package shipdoandem.amytateam.org.shipdoandem.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.adapter.FoodAdapter;
import shipdoandem.amytateam.org.shipdoandem.adapter.SearchFoodAdapter;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContextSearch;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.evenbus.OnClickItemEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.OnClickItemSearchEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.UpdateSearchFood;
import shipdoandem.amytateam.org.shipdoandem.utils.Utils;

import static java.security.AccessController.getContext;

public class SearchFoodActivity extends AppCompatActivity {

    @BindView(R.id.sv_search)
    SearchView svFood;
    @BindView(R.id.rv_search)
    RecyclerView rvSreach;
    Context context;
    SearchFoodAdapter foodAdapter = new SearchFoodAdapter(this);
    FoodAdapter food = new FoodAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        ButterKnife.bind(this);
        setupUI();
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void update(UpdateSearchFood updateSearchFood){
        rvSreach.setAdapter(food);
        rvSreach.setLayoutManager(new GridLayoutManager(context, 2));
    }

    private void setupUI() {
        context = this;
        EventBus.getDefault().register(this);
        Utils.setTitleActionBar(this, "Tìm Kiếm Món Ăn");
        svFood.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (DbContextSearch.instance.allFoods().size() == 0) {
                    Toast.makeText(context, "Không tìm thấy kết quả !", Toast.LENGTH_SHORT).show();
                }
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                CharSequence name = svFood.getQuery();
                String nameFood = String.format(name + "");
                List<Food> list = DbContext.instance.allFoods();
                DbContextSearch.instance.clean();

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equalName(nameFood)) {
                        Log.d(SearchFoodActivity.class.toString(), "onQueryTextChange: tìm thấy");
                        DbContextSearch.instance.addFood(list.get(i));
                    }
                }

                if (DbContextSearch.instance.allFoods().size() > 0) {
                    rvSreach.setVisibility(View.VISIBLE);
                    rvSreach.setAdapter(foodAdapter);
                    rvSreach.setLayoutManager(new GridLayoutManager(context, 2));

                } else {
                    rvSreach.setVisibility(View.INVISIBLE);
                }
                Log.d(SearchFoodActivity.class.toString(), String.format("onQueryTextChange: %s", name));
                return false;
            }
        });

    }

    @Subscribe
    void OnClickItem(OnClickItemSearchEvent onClickItemSearchEvent){
        Intent intent = new Intent(context,FoodInformationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
