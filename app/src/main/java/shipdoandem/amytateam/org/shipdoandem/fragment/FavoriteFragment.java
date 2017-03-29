package shipdoandem.amytateam.org.shipdoandem.fragment;


import android.app.ProgressDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.adapter.FoodAdapter;
import shipdoandem.amytateam.org.shipdoandem.databases.DbContext;
import shipdoandem.amytateam.org.shipdoandem.databases.models.Food;
import shipdoandem.amytateam.org.shipdoandem.evenbus.GetAllFoodFaileEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.GetAllFoodLikeFailedEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.GetAllFoodLikeSuccessEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.GetAllFoodSuccusEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SendRequestEvent;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SentFood;
import shipdoandem.amytateam.org.shipdoandem.evenbus.TypeRequestEvent;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    @BindView(R.id.rv_favorite)
    RecyclerView rvFavorite;

    @BindView(R.id.rl_favorite)
    RelativeLayout rlFavorite;

    @BindView(R.id.iv_oops)
    ImageView ivOops;

    @BindView(R.id.swipeRefreshLayout)
    PullRefreshLayout layout;

    private ProgressDialog progress;

    private FoodAdapter foodAdapter = new FoodAdapter(this.getContext());

    public FavoriteFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        setupUI(view);
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        ivOops.setBackgroundResource(R.drawable.ic_oops);
        Log.e(TAG, String.format("setupUI: %s", DbContext.instance.getFoodsLike().size()) );

        loadAllFood();

        // listen refresh event
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DbContext.instance.getAllFoodLike();
                    }
                }, 3000);
            }
        });



        rlFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContext.instance.getAllFoodLike();
                layout.setRefreshing(true);
            }
        });
    }

    public void loadAllFood() {
        if(DbContext.instance.getFoodsLike().size()==0) {
            DbContext.instance.getAllFoodLike();
            progress = ProgressDialog.show(this.getContext(), "Xin chờ",
                    "Đang tải", true);
            progress.show();
        }else {
            rvFavorite.setAdapter(foodAdapter);
            rvFavorite.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        }
    }


    @Subscribe
    public void onLoadFoodSuccess(GetAllFoodLikeSuccessEvent event) {
        if(progress != null)
            progress.dismiss();
        layout.setRefreshing(false);
        rvFavorite.setAdapter(foodAdapter);
        rvFavorite.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        ivOops.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onLoadDataFailed(GetAllFoodLikeFailedEvent event) {
        ivOops.setVisibility(View.VISIBLE);
        Toast.makeText(this.getContext(), "Lỗi kết nối. Kiểm tra đường truyền internet!", Toast.LENGTH_SHORT).show();
        progress.dismiss();
        layout.setRefreshing(false);
    }
    @Subscribe
    public void changeButtonMode(SendRequestEvent event){
        if(event.getTypeRequest() == TypeRequestEvent.CHANGE_BT_ADDTOCART){
            foodAdapter.notifyDataSetChanged();
        }
    }
}
