package shipdoandem.amytateam.org.shipdoandem.databases.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hieutran on 3/22/17.
 */

public class UserRespon {
    @SerializedName("username")
    private String userName;
    @SerializedName("address")
    private String address;
    @SerializedName("phone_number")
    private String phoneNumber;

    public UserRespon(String userName, String address, String phoneNumber) {
        this.userName = userName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
