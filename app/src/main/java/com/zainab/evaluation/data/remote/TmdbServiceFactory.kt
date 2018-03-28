package com.zainab.evaluation.data.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zainab.evaluation.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Factory responsible for creating [TmdbService] and it's components
 */
object TmdbServiceFactory {
    fun buildTmdbService(url:String): TmdbService {
        return tmdbService(gson(), client(httpLogginginterceptor(BuildConfig.DEBUG)), url)
    }

    private fun tmdbService(gson: Gson, client: OkHttpClient, url: String): TmdbService {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
                .create(TmdbService::class.java)
    }

    private fun client(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiKeyInterceptor())
                .build()

    private fun apiKeyInterceptor() = Interceptor { chain ->
        val original = chain.request()

        chain.proceed(original
                .newBuilder()
                .url(original.url()
                        .newBuilder()
                        .setQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                        .build())
                .build())
    }

    private fun gson() = GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd")
            .create()

    private fun httpLogginginterceptor(isDebug: Boolean) = HttpLoggingInterceptor().apply {
        level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

}