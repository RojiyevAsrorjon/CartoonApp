package uz.gita.cartoonapp.di

import android.content.Context
import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import uz.gita.cartoonapp.BuildConfig.BASE_URL
import uz.gita.cartoonapp.data.remote.apies.CartoonApi
import uz.gita.cartoonapp.utils.addLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @[Provides Singleton]
    fun getApi(retrofit : Retrofit) : CartoonApi = retrofit.create(CartoonApi::class.java)

    @[Provides Singleton]
    fun getRetrofit(client : OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @[Provides Singleton]
    fun getOkHttpClient(@ApplicationContext context : Context) :OkHttpClient =
        OkHttpClient.Builder()
            .addLoggingInterceptor(context)
            .build()

    @[Provides Singleton]
    fun getConfig() :PagingConfig = PagingConfig(5)
}