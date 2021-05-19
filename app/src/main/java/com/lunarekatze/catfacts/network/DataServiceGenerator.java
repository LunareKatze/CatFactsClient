package com.lunarekatze.catfacts.network;

import com.lunarekatze.catfacts.interfaces.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataServiceGenerator {
        public static <S> S createService(Class<S> serviceClass) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Api.BASE_URL);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .readTimeout(90, TimeUnit.SECONDS)
                    .connectTimeout(90, TimeUnit.SECONDS)
                    .writeTimeout(90, TimeUnit.SECONDS)
                    .cache(null);

            builder.client(httpClient.build());
            Retrofit retrofit = builder.build();
            return  retrofit.create(serviceClass);
        }
}
