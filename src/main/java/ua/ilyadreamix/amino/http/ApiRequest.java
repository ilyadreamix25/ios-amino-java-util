package ua.ilyadreamix.amino.http;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ua.ilyadreamix.amino.dto.LoginRequest;
import ua.ilyadreamix.amino.util.HashUtility;

import java.io.IOException;

public class ApiRequest {
    @SuppressWarnings("AccessStaticViaInstance")
    private final static OkHttpClient client = HttpModule.getInstance().client;
    private final static Gson gson = new Gson();

    private final static String BASE_URL = "https://service.narvii.com/api/v1";

    public Response login(LoginRequest body) throws IOException {
        String jsonBody = gson.toJson(body);
        RequestBody requestBody = RequestBody.create(jsonBody, MediaTypes.JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + "/g/s/auth/login")
                .post(requestBody)
                .header(Headers.DEVICE_ID, body.deviceId)
                .header(Headers.SIG, HashUtility.generateSignature(jsonBody.getBytes()))
                .header(Headers.USER_AGENT, Headers.Values.USER_AGENT)
                .build();

        return client.newCall(request).execute();
    }
}
