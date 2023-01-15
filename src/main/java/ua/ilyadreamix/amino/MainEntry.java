package ua.ilyadreamix.amino;

import okhttp3.Response;
import ua.ilyadreamix.amino.dto.LoginRequest;
import ua.ilyadreamix.amino.http.ApiRequest;
import ua.ilyadreamix.amino.util.HashUtility;

import java.io.IOException;

public class MainEntry {
    public static void main(String[] args) throws IOException {
        LoginRequest loginBody = new LoginRequest(
            "5botambsra@gmail.com",
            "0 12345678",
            HashUtility.generateDeviceId(null)
        );
        Response loginResponse = new ApiRequest().login(loginBody);

        assert loginResponse.body() != null;
        System.out.println(loginResponse.body().string());
    }
}
