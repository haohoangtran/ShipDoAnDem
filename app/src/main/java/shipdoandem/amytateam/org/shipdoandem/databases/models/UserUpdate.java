package shipdoandem.amytateam.org.shipdoandem.databases.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hieutran on 3/24/17.
 */

public class UserUpdate {
    @SerializedName("address")
    private String address;
    @SerializedName("name")
    private String name;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("_id")
    private Id Id;


    public class Id {
        @SerializedName("$oid")
        public String $oid;

        public String get$oid() {
            return $oid;
        }
    }

    public UserUpdate(String address, String name, String phoneNumber) {
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserUpdate{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
