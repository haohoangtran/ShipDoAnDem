package shipdoandem.amytateam.org.shipdoandem.databases.models;

import android.util.Log;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tranh on 3/18/2017.
 */

public class Food extends RealmObject{
    private String url;
    @PrimaryKey
    private String id;
    private int coutRate;
    private float rate;
    private String priceNew;
    private String priceOld;
    private String name;
    private int percent;

    public int getPercent() {
        return percent;
    }

    public Food() {
    }

    @Override
    public String toString() {
        return "Food{" +
                ", url='" + url + '\'' +
                ", coutRate=" + coutRate +
                ", rate=" + rate +
                ", priceNew='" + priceNew + '\'' +
                ", priceOld='" + priceOld + '\'' +
                ", name='" + name + '\'' +
                ", percent=" + percent +
                '}';
    }

    public String getId() {
        return id;
    }

    public Food(FoodRespon foodRespon) {
        this.url = foodRespon.getUrl();
        this.coutRate = foodRespon.getCoutRate();
        this.rate = foodRespon.getRate();
        this.priceNew = foodRespon.getCointNew();
        this.priceOld = foodRespon.getCointOld();
        this.name = foodRespon.getName();
        this.id=foodRespon.getId().getId();
        try {
            double priceO = Integer.valueOf(priceOld);
            double priceN = Integer.valueOf(priceNew);
            int per = (int) (((priceN - priceO) / priceO) * 100);
            if (per > 0) {
                this.percent = -per;

            }else
                this.percent=per;
        } catch (Exception e) {
            this.percent=0;
          //  Log.e(TAG, String.format("Food: %s", e.toString()) );
        }
    }


    public String getUrl() {
        return url;
    }

    public int getCoutRate() {
        return coutRate;
    }

    public float getRate() {
        return rate;
    }

    public String getPriceNew() {
        return priceNew;
    }

    public String getPriceOld() {
        return priceOld;
    }

    public String getName() {
        return name;
    }
}
