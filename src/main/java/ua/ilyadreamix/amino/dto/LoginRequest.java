package ua.ilyadreamix.amino.dto;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("email")
    public String email;
    @SerializedName("secret")
    public String secret;
    @SerializedName("clientType")
    public int clientType;
    @SerializedName("deviceID")
    public String deviceId;
    @SerializedName("action")
    public String action;
    @SerializedName("v")
    public int v;
    @SerializedName("timestamp")
    public long timestamp;

    public LoginRequest(String email, String secret, String deviceId) {
        this.email = email;
        this.secret = secret;
        this.clientType = 100;
        this.deviceId = deviceId;
        this.action = "normal";
        this.v = 2;
        this.timestamp = System.currentTimeMillis();
    }
}
