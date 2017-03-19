package shipdoandem.amytateam.org.shipdoandem.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.adapter.FoodAdapter;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.evenbus.GetAllFoodFaileEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.GetAllFoodSuccusEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostViewFragment extends Fragment{
    private static final String TAG = MostViewFragment.class.toString();
    @BindView(R.id.rv_food)
    RecyclerView rvFood;

    private ProgressDialog progress;

    private FoodAdapter foodAdapter = new FoodAdapter(this.getContext());

    public MostViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_most_view, container, false);
        setupUI(view);
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        Log.e(TAG, String.format("setupUI: %s", DbContext.getInstance().allFoods().size()) );

        if(DbContext.getInstance().allFoods().size()==0) {
            DbContext.getInstance().getAllFood();
            progress = ProgressDialog.show(this.getContext(), "Xin chờ",
                    "Đang tải", true);
            progress.show();
        }else {
            rvFood.setAdapter(foodAdapter);
            rvFood.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        }

        foodAdapter.setItemClickListener(new FoodAdapter.ItemClickListener() {
            @Override
            public void clickItem() {
                Log.d(TAG, "Vào");
                FoodInformationFragment foodInformationFragment = new FoodInformationFragment();
                (new SceneFragment(getActivity().getSupportFragmentManager(),
                        R.id.fl_main)).replaceFragment(foodInformationFragment, true);
            }
        });
    }

    @Subscribe
    public void onLoadFoodSuccus(GetAllFoodSuccusEvent event) {
        progress.dismiss();
        rvFood.setAdapter(foodAdapter);
        rvFood.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onLoadDataFailed(GetAllFoodFaileEvent event) {
        Toast.makeText(this.getContext(), "Load thất bại, mạng mẽo như beep", Toast.LENGTH_SHORT).show();
        progress.dismiss();
    }
}
