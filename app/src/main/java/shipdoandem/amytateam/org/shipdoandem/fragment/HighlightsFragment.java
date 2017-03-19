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

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HighlightsFragment extends Fragment {
    @BindView(R.id.rv_highlights)
    RecyclerView rvHighlights;

    private ProgressDialog progress;

    private FoodAdapter foodAdapter = new FoodAdapter(this.getContext());

    public HighlightsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_highlights, container, false);
        setupUI(view);
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        Log.e(TAG, String.format("setupUI: %s", DbContext.instance.allFoods().size()) );

        if(DbContext.instance.allFoods().size()==0) {
            DbContext.instance.getAllFood();
            progress = ProgressDialog.show(this.getContext(), "Xin chờ",
                    "Đang tải", true);
            progress.show();
        }else {
            rvHighlights.setAdapter(foodAdapter);
            rvHighlights.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        }
    }

    @Subscribe
    public void onLoadFoodSuccus(GetAllFoodSuccusEvent event) {
        progress.dismiss();
        rvHighlights.setAdapter(foodAdapter);
        rvHighlights.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
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
