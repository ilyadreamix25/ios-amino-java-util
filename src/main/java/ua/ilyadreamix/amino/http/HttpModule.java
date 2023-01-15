package ua.ilyadreamix.amino.http;

import okhttp3.OkHttpClient;

public final class HttpModule {
    private static HttpModule INSTANCE;
    public static OkHttpClient client;

    private HttpModule() {
        client = new OkHttpClient.Builder()
                // .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                // .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public static HttpModule getInstance() {
        if (INSTANCE == null)
            INSTANCE = new HttpModule();

        return INSTANCE;
    }
}
