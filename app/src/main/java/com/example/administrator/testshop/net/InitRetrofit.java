package com.example.administrator.testshop.net;

import com.example.administrator.testshop.HttpUrl;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.GzipSink;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mw on 16/4/26.
 */
public class InitRetrofit {


    private static OkHttpClient client = new OkHttpClient
            .Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
            //.addInterceptor(new GzipRequsetInterceptor())
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                            .addHeader("Accept-Encoding", "gzip, deflate")
                            .addHeader("Connection", "keep-alive")
                            .addHeader("Accept", "*/*")
                            .addHeader("Cookie", "add cookies here")
                            .build();
                    return chain.proceed(request);
                }
            })
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HttpUrl.Base_Url)
            .client(client)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    private InitRetrofit() {

    }

    public static <T> T createApi(Class<T> mClass) {

        return retrofit.create(mClass);
    }

}
