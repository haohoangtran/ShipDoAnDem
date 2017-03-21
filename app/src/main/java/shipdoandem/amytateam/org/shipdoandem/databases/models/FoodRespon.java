package shipdoandem.amytateam.org.shipdoandem.databases.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DUC THANG on 3/16/2017.
 */

public class FoodRespon {

    @SerializedName("url")
    private String url;
    @SerializedName("cout_rate")
    private int coutRate;
    @SerializedName("rate")
    private float rate;
    @SerializedName("coint_new")
    private String cointNew;
    @SerializedName("_id")
    private Id Id;
    @SerializedName("coint_old")
    private String cointOld;
    @SerializedName("name")
    private String name;

    public class Id {
        @SerializedName("$oid")
        public String $oid;
    }

    public FoodRespon(String url, int rate, String cointNew, String cointOld, String name) {
        this.url = url;
        this.rate = rate;
        this.cointNew = cointNew;
        this.cointOld = cointOld;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCoutRate() {
        return coutRate;
    }

    public void setCoutRate(int coutRate) {
        this.coutRate = coutRate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCointNew() {
        return cointNew;
    }

    public void setCointNew(String cointNew) {
        this.cointNew = cointNew;
    }

    public FoodRespon.Id getId() {
        return Id;
    }

    public void setId(FoodRespon.Id id) {
        Id = id;
    }

    public String getCointOld() {
        return cointOld;
    }

    public void setCointOld(String cointOld) {
        this.cointOld = cointOld;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
