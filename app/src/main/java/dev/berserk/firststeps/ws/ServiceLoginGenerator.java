package dev.berserk.firststeps.ws;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceLoginGenerator {

    private static String BASE_URL = "http://uble.mx/buckner/ws/login.php";

    private static HttpLoggingInterceptor loggin = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httClient = new OkHttpClient.Builder()
            .addInterceptor(loggin);

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public  static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httClient.build()).build();

        return retrofit.create(serviceClass);
    }
}
