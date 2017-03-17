package shipdoandem.amytateam.org.shipdoandem.decorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by DUC THANG on 3/17/2017.
 */

public class FoodListDeco extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = 16;
        outRect.right = 16;
    }
}
