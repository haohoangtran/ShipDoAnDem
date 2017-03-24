package shipdoandem.amytateam.org.shipdoandem.databases.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hieutran on 3/22/17.
 */

public class UserRegisterRespon {
    @SerializedName("id")
    private String idUser;
    @SerializedName("total_spend")
    private String totalSpend;
    @SerializedName("_id")
    private Id Id;


    public class Id {
        @SerializedName("$oid")
        public String $oid;

        public String get$oid() {
            return $oid;
        }
    }

    public UserRegisterRespon(String idUser, String totalSpend, UserRegisterRespon.Id id) {
        this.idUser = idUser;
        this.totalSpend = totalSpend;
        Id = id;
    }

    public UserRegisterRespon(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(String totalSpend) {
        this.totalSpend = totalSpend;
    }

    public UserRegisterRespon.Id getId() {
        return Id;
    }

    public void setId(UserRegisterRespon.Id id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "UserRegisterRespon{" +
                "idUser='" + idUser + '\'' +
                ", totalSpend='" + totalSpend + '\'' +
                ", Id=" + Id +
                '}';
    }
}
